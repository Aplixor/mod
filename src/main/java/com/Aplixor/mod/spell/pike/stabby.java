package com.Aplixor.mod.spell.pike;

import com.Aplixor.mod.spell.Spell;
import com.Aplixor.mod.util.EntityFinder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class stabby implements Spell {

    @Override
    public TypedActionResult<ItemStack> useTrigger(World world, PlayerEntity user, Hand hand) {
        LivingEntity target = (LivingEntity) EntityFinder.getFacingEntities(world, user, 15, (entity -> entity instanceof LivingEntity)).getFirst();
        if (target == null) return null;

        PointComponent.pointComp.get(target).put("test", 20);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 20 * 3));
        return null;
    }
}
