package com.aplixor.mod.spell.functions;

import com.aplixor.mod.spell.ParameterHelper;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.util.function.BiConsumer;

public class Parabola implements Function<Parabola.ParabolaParameter> {

    @Override
    public BiConsumer<PlayerEntity, LivingEntity> get(Dynamic<?> parameterSource) {
        var param = ParameterHelper.get(parameterSource, Parabola.CODEC, new ParabolaParameter(1.0, 2.3));
        return (cast, target) -> this.direct(cast, target, param);
    }

    @Override
    public void direct(PlayerEntity cast, LivingEntity target, ParabolaParameter parabolaParameter) {

        Vec3d lookingVector = Vec3d.fromPolar(cast.getPitch(), cast.getYaw()).normalize();
        lookingVector = lookingVector.subtract(0, lookingVector.y, 0);
        lookingVector = lookingVector.multiply(Math.cos(parabolaParameter.angle)).multiply(parabolaParameter.length);
        Vec3d vec = new Vec3d(0,1,0).multiply(Math.sin(parabolaParameter.angle)).multiply(parabolaParameter.length).add(lookingVector);

        cast.addVelocity(vec);
    }

    record ParabolaParameter(Double length, Double angle) {};
    static Codec<ParabolaParameter> CODEC = RecordCodecBuilder.create((instance -> instance.group(
            Codec.DOUBLE.fieldOf("length").forGetter((ParabolaParameter o) -> o.length),
            Codec.DOUBLE.fieldOf("angle").forGetter((ParabolaParameter o) -> o.angle)
    ).apply(instance, ParabolaParameter::new)));
}
