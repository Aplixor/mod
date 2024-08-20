package com.aplixor.mod.spell.capturing;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Collections;
import java.util.List;

public class Self extends Capture {

    @Override
    public List<LivingEntity> execute(PlayerEntity cast) {
        return Collections.singletonList(cast);
    }

}
