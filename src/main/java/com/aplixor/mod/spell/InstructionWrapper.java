package com.aplixor.mod.spell;

import com.aplixor.mod.items.TriggerTypes;
import com.aplixor.mod.spell.functions.Function;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public class InstructionWrapper {

    ArrayList<instruction> instructionArrayList = new ArrayList<>();

    public void add(java.util.function.Function<PlayerEntity, List<LivingEntity>> entityGetter, BiPredicate<PlayerEntity, LivingEntity> checker, BiConsumer<PlayerEntity, LivingEntity> func) {
        this.instructionArrayList.add(new instruction(entityGetter, checker, func));
    }

    public TriggerTypes.useTrigger get() {
        return this::execute;
    }

    TypedActionResult<ItemStack> execute(World world, PlayerEntity user, Hand hand) {
        for (var ins : instructionArrayList) {
            for (LivingEntity target : ins.entityGetter.apply(user)) {
                if (!ins.checker.test(user, target)) continue;
                ins.func.accept(user, target);
            }
        }
        return null;
    };

    record instruction(java.util.function.Function<PlayerEntity, List<LivingEntity>> entityGetter, BiPredicate<PlayerEntity, LivingEntity> checker, BiConsumer<PlayerEntity, LivingEntity> func) {};

}
