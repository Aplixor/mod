package com.aplixor.mod.spell.functions;

import com.aplixor.mod.core.Interactions.InteractFactory;
import com.aplixor.mod.core.Interactions.Subtract;
import com.aplixor.mod.spell.ParameterHelper;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.function.BiConsumer;

public class Hurt implements Function<Hurt.HurtParameter> {

    @Override
    public void direct(PlayerEntity cast, LivingEntity target, HurtParameter hurtParameter) {
        new Subtract(InteractFactory.entityHealth(target), hurtParameter.amount).execute();
    }

    @Override
    public BiConsumer<PlayerEntity, LivingEntity> get(Dynamic<?> parameterSource) {
        var param = ParameterHelper.get(parameterSource, CODEC, new HurtParameter(1));
        return (cast, target) -> this.direct(cast, target, param);
    }

    record HurtParameter(Integer amount) {};
    static Codec<HurtParameter> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("amount").forGetter((HurtParameter o) -> o.amount)
    ).apply(instance, HurtParameter::new));
}
