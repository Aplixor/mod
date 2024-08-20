package com.aplixor.mod.spell.filter;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;

public interface Filter {

    boolean apply(PlayerEntity cast, LivingEntity target);

    void applyParameter(HashMap<String, String> map);
}