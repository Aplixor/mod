package com.aplixor.mod.spell.functions;

import com.aplixor.mod.Mod;
import com.aplixor.mod.items.TriggerDelegator;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.random.Random;

import java.util.HashMap;

public class ScheduleSpell extends Function {

    Integer afterTicks = 20;
    String spellName = "null";


    public ScheduleSpell(Function deco) {
        super(deco);
    }

    @Override
    public void applyParameter(HashMap<String, String> param) {
        this.afterTicks = Integer.valueOf(param.get("AfterTicks"));
        this.spellName = param.get("SpellName");
    }

    @Override
    public void insturction(PlayerEntity cast, LivingEntity target) {
        Random random = Random.create();
        Runnable spell = (() -> TriggerDelegator.getInstance().get(this.spellName, cast.getWorld(), cast, cast.getActiveHand()));
        if (this.afterTicks > 0) {
            Mod.getInstance().getScheduler().addTask(String.valueOf(random.nextFloat()), spell, this.afterTicks);
            return;
        }

        spell.run();
    }
}
