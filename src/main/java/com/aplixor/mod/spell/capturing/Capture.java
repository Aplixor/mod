package com.aplixor.mod.spell.capturing;

import com.mojang.serialization.Dynamic;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public interface Capture<T> {

    Function<PlayerEntity, List<LivingEntity>> get(Dynamic<?> dynamic);

    List<LivingEntity> direct(PlayerEntity cast, T parameter);
}
