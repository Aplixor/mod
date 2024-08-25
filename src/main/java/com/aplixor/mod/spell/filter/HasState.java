package com.aplixor.mod.spell.filter;

import com.aplixor.mod.spell.Mark;
import com.aplixor.mod.spell.ParameterHelper;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;
import java.util.function.BiPredicate;

public class HasState implements Filter<HasState.HasStateParameter> {

    @Override
    public BiPredicate<PlayerEntity, LivingEntity> get(Dynamic<?> dynamic) {
        var param = ParameterHelper.get(dynamic, CODEC, new HasStateParameter("_null"));
        return (cast, target) -> this.direct(cast, target, param);
    }

    @Override
    public boolean direct(PlayerEntity cast, LivingEntity target, HasStateParameter parameter) {
        var opt =  Mark.markComponentKey.get(target).pop(parameter.managerName, cast.getUuid());
        return opt.isPresent();
    }

    record HasStateParameter(String managerName) {};
    static Codec<HasStateParameter> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("managerName").forGetter((HasStateParameter o) -> o.managerName)
    ).apply(instance, HasStateParameter::new));
}
