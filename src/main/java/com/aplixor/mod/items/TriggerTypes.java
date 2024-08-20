package com.aplixor.mod.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public record TriggerTypes() {

    @FunctionalInterface
    public interface useTrigger {
        TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand);
    }

}
