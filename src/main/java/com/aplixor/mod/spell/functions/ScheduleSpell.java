package com.aplixor.mod.spell.functions;

import com.aplixor.mod.Mod;
import com.aplixor.mod.items.TriggerDelegator;
import com.aplixor.mod.spell.ParameterHelper;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.random.Random;

import java.util.HashMap;
import java.util.function.BiConsumer;

public class ScheduleSpell implements Function<ScheduleSpell.ScheduleSpellParameter> {

    @Override
    public void direct(PlayerEntity cast, LivingEntity target, ScheduleSpellParameter parameter) {
        Random random = Random.create();
        Runnable spell = (() -> TriggerDelegator.getInstance().get(parameter.spellName, cast.getWorld(), cast, cast.getActiveHand()));
        if (parameter.afterTicks > 0) {
            Mod.getInstance().getScheduler().addTask(String.valueOf(random.nextFloat()), spell, parameter.afterTicks);
            return;
        }
        spell.run();
    }

    @Override
    public BiConsumer<PlayerEntity, LivingEntity> get(Dynamic<?> parameterSource) {
        var param = ParameterHelper.get(parameterSource, CODEC, new ScheduleSpellParameter("_null", 20));
        return (cast, target) -> this.direct(cast, target, param);
    }

    record ScheduleSpellParameter(String spellName, Integer afterTicks) {};
    static Codec<ScheduleSpellParameter> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("spellName").forGetter((ScheduleSpellParameter o) -> o.spellName),
            Codec.INT.fieldOf("afterTicks").forGetter((ScheduleSpellParameter o) -> o.afterTicks)
    ).apply(instance, ScheduleSpellParameter::new));
}
