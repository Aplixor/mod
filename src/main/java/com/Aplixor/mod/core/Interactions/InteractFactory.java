package com.Aplixor.mod.core.Interactions;

import com.Aplixor.mod.attribute.Health;
import net.minecraft.entity.LivingEntity;

public class InteractFactory {

    public static BaseInteractImpl<Integer> entityHealth(LivingEntity target) {
        return new SetValue(target, new Health(), (Integer) new Health().getAttribute().get(target).get("What"));
    }
}
