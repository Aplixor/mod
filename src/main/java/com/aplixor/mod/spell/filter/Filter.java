package com.aplixor.mod.spell.filter;

import com.mojang.serialization.Dynamic;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;
import java.util.function.BiPredicate;

public interface Filter<T> {

    BiPredicate<PlayerEntity, LivingEntity> get(Dynamic<?> dynamic);

    boolean direct(PlayerEntity cast, LivingEntity target, T parameter);
}