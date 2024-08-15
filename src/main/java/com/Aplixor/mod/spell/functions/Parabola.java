package com.Aplixor.mod.spell.functions;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;

public class Parabola extends Function {

    Double length = 1.0;
    Double angle = 0.785;

    public Parabola(Function deco) {
        super(deco);
    }

    @Override
    public void applyParameter(HashMap<String, String> param) {
        this.length = Double.valueOf(param.get("length"));
        this.angle = Double.valueOf(param.get("angle"));
    }

    @Override
    public void insturction(PlayerEntity cast, LivingEntity target) {

        Vec3d lookingVector = Vec3d.fromPolar(cast.getPitch(), cast.getYaw()).normalize();
        lookingVector = lookingVector.subtract(0, lookingVector.y, 0);
        lookingVector = lookingVector.multiply(Math.cos(this.angle)).multiply(length);
        Vec3d vec = new Vec3d(0,1,0).multiply(Math.sin(angle)).multiply(length).add(lookingVector);

        cast.addVelocity(vec);
    }

}
