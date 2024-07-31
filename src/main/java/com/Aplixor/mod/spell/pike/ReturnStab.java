package com.Aplixor.mod.spell.pike;

import com.Aplixor.mod.spell.StateSpell;
import com.Aplixor.mod.util.EntityFinder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Pair;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Optional;

public class ReturnStab extends StateSpell {
    @Override
    public TypedActionResult<ItemStack> useTrigger(World world, PlayerEntity user, Hand hand) {
        LivingEntity target = (LivingEntity) EntityFinder.getFacingEntities(world, user, 15, (entity -> entity instanceof LivingEntity)).getFirst();
        if (target == null) return null;
        Optional<Pair<String, Integer>> value = PointComponent.pointComp.get(target).get(target.toString());

        if (value.isEmpty()) return null;

        this.execute(user, target);

        return null;
    }

    private void execute(PlayerEntity attacker, LivingEntity target) {
        Double strength = .5;
        Vec3d vec = new Vec3d(target.getX() - attacker.getX(), target.getY() - attacker.getY(), target.getZ() - attacker.getZ()).normalize().multiply(strength);
        attacker.addVelocity(vec);
        target.damage(attacker.getDamageSources().generic(), 3.0f);
    }
}
