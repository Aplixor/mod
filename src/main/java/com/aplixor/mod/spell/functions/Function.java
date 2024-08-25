package com.aplixor.mod.spell.functions;

import com.mojang.serialization.Dynamic;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.function.BiConsumer;

public interface Function<T> {

    void direct(PlayerEntity cast, LivingEntity target, T parameter);

    BiConsumer<PlayerEntity, LivingEntity> get(Dynamic<?> parameterSource);
}
