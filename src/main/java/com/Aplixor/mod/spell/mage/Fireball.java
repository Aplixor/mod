package com.Aplixor.mod.spell.mage;

import com.Aplixor.mod.spell.NullSpell;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.joml.Vector3f;

public class Fireball extends NullSpell {

    @Override
    public TypedActionResult<ItemStack> useTrigger(World world, PlayerEntity user, Hand hand) {
        System.out.println((user.getEyePos()));
        Vector3f direction = user.getFacing().getUnitVector().normalize(2);
        world.spawnEntity(new FireballEntity(world, user, direction.x, direction.y, direction.z,1));

        if (!world.isClient) {
            user.sendMessage(Text.literal(String.valueOf(world.getServer().getTicks())));
        }

        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
