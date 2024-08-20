package com.aplixor.mod.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class GenericItem extends Item {

    // item itself is a singleton
    TriggerDelegator delegator = TriggerDelegator.getInstance();

    public GenericItem() {
        super(new Item.Settings().maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        delegator.get(this.getKey(user, hand), world, user, hand);
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 1200;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        return stack;
    }

    String getKey(PlayerEntity user, Hand hand) {
        return user.getStackInHand(hand).getName().getString();
    }
}
