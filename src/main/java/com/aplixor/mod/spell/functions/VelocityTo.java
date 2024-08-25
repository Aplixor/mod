package com.aplixor.mod.spell.functions;

import com.aplixor.mod.spell.ParameterHelper;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.util.function.BiConsumer;

public class VelocityTo implements Function<VelocityTo.VelocityToParameter> {

    @Override
    public void direct(PlayerEntity cast, LivingEntity target, VelocityToParameter parameter) {
        Vec3d vec = target.getPos().add(cast.getPos().multiply(-1)).normalize().multiply(parameter.strength);
        cast.addVelocity(vec);
    }

    @Override
    public BiConsumer<PlayerEntity, LivingEntity> get(Dynamic<?> parameterSource) {
        var param = ParameterHelper.get(parameterSource, CODEC, new VelocityToParameter(1.0));
        return (cast, target) -> this.direct(cast, target, param);
    }

    record VelocityToParameter(Double strength) {};
    static Codec<VelocityToParameter> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.DOUBLE.fieldOf("strength").forGetter((VelocityToParameter o) -> o.strength)
    ).apply(instance, VelocityToParameter::new));
}
