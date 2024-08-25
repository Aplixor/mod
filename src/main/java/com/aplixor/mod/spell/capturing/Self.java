package com.aplixor.mod.spell.capturing;

import com.mojang.serialization.Dynamic;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Self implements Capture<Object> {

    @Override
    public Function<PlayerEntity, List<LivingEntity>> get(Dynamic<?> dynamic) {
        return (cast) -> this.direct(cast, null);
    }

    @Override
    public List<LivingEntity> direct(PlayerEntity cast, Object ignore) {
        return Collections.singletonList(cast);
    }
}
