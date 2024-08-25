package com.aplixor.mod.spell;

import com.aplixor.mod.TemplateMod;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;

public class ParameterHelper {

    public static <T> T get(Dynamic<?> dynamic, Codec<T> codec, T fallback) {
        var dataResult = dynamic.decode(codec);

        if (dataResult.isError()) {
            TemplateMod.LOGGER.warn(String.format("Error loading parameter %s, using provided fallback value %s", fallback.getClass(), fallback));
            TemplateMod.LOGGER.warn(String.format("DataResult error following: %s", dataResult.error().get().message()));
            return fallback;
        }

        return dataResult.result().get().getFirst();
    }
}
