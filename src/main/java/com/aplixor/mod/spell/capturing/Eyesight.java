package com.aplixor.mod.spell.capturing;

import com.aplixor.mod.spell.ParameterHelper;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class Eyesight implements Capture<Eyesight.EyesightParameter> {


    @Override
    public Function<PlayerEntity, List<LivingEntity>> get(Dynamic<?> dynamic) {
        var param = ParameterHelper.get(dynamic, CODEC, new EyesightParameter(5));
        return (cast) -> this.direct(cast, param);
    }

    @Override
    public List<LivingEntity> direct(PlayerEntity cast, EyesightParameter parameter) {

        var radius = parameter.radius;

        Vec3d lookingVec = Vec3d.fromPolar(cast.getPitch(), cast.getYaw()).normalize();
        Vec3d pos1 = new Vec3d(cast.getX() + radius, cast.getY() + radius, cast.getZ() + radius);
        Vec3d pos2 = new Vec3d(cast.getX() - radius, cast.getY() - radius, cast.getZ() - radius);
        Box box = new Box(pos1, pos2);

        List<LivingEntity> list = cast.getWorld()
                .getEntitiesByClass(LivingEntity.class, box, (livingEntity -> !cast.equals(livingEntity)))
                .stream()
                .sorted(Comparator.comparingDouble(o -> cast.getPos().add(o.getPos().multiply(-1)).length()))
                .toList();

        for (var ent : list) {
            var result = ent.getBoundingBox().raycast(cast.getEyePos(), cast.getEyePos().add(lookingVec.multiply(radius)));
            if (result.isPresent()) {
                return Collections.singletonList(ent);
            }
        }

        return Collections.emptyList();
    }

    private boolean canSee(PlayerEntity player, LivingEntity entity) {
        // todo: find a better implementation as this method only checks eye positions.
        return player.canSee(entity);
    }

    record EyesightParameter(Integer radius) {};
    static Codec<EyesightParameter> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("radius").forGetter((EyesightParameter o) -> o.radius)
    ).apply(instance, EyesightParameter::new));
}
