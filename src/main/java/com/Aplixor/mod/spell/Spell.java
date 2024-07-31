package com.Aplixor.mod.spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public interface Spell {
    TypedActionResult<ItemStack> useTrigger(World world, PlayerEntity user, Hand hand);
}
