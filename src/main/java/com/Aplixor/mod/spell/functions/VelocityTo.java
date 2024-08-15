package com.Aplixor.mod.spell.functions;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public class VelocityTo extends Function {

    private double strength = 1.0;

    public VelocityTo(Function deco) {
        super(deco);
    }


    @Override
    public void insturction(PlayerEntity cast, LivingEntity target) {
        Vec3d vec = target.getPos().add(cast.getPos().multiply(-1)).normalize().multiply(strength);
        cast.addVelocity(vec);
    }
}
