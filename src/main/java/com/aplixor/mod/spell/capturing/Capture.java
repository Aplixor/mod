package com.aplixor.mod.spell.capturing;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;
import java.util.List;

public abstract class Capture {

    public void setParameter(HashMap<String, String> map) {

    }

    public abstract List<LivingEntity> execute(PlayerEntity cast);
}
