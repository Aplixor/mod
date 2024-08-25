package com.aplixor.mod.spell.functions;

import com.aplixor.mod.spell.Mark;
import com.aplixor.mod.spell.ParameterHelper;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.BiConsumer;

public class AddState implements Function<AddState.AddStateParameter> {

    @Override
    public void direct(PlayerEntity cast, LivingEntity target, AddStateParameter parameter) {
        UUID owner_id = cast.getUuid();
        Mark.markComponentKey.get(target).put(parameter.managerName, owner_id, Math.toIntExact(cast.getWorld().getTime()) + parameter.expireAfter);
    }

    @Override
    public BiConsumer<PlayerEntity, LivingEntity> get(Dynamic<?> parameterSource) {
        var param = ParameterHelper.get(parameterSource, CODEC, new AddStateParameter("_null", 20));
        return (cast, target) -> this.direct(cast, target, param);
    }

    record AddStateParameter(String managerName, Integer expireAfter) {};
    static Codec<AddStateParameter> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("managerName").forGetter((AddStateParameter o) -> o.managerName),
            Codec.INT.fieldOf("expireAfter").forGetter((AddStateParameter o) -> o.expireAfter)
    ).apply(instance, AddStateParameter::new));
}
