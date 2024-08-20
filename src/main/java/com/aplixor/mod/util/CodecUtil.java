package com.aplixor.mod.util;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;

import java.util.HashMap;

public class CodecUtil {

    public static Codec<HashMap<String, String>> mapCodec = Codec.compoundList(Codec.STRING, Codec.STRING).xmap((pairs -> {
        HashMap<String, String> map = new HashMap<>();
        pairs.forEach((pair -> map.put(pair.getFirst(), pair.getSecond())));
        return map;
    }), (map -> map.entrySet().stream()
            .map((entry -> new Pair<>(entry.getKey(), entry.getValue())))
            .toList()));
}
