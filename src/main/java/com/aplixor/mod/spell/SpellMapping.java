package com.aplixor.mod.spell;

import com.aplixor.mod.util.CodecUtil;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public record SpellMapping(String name, List<Interaction> interactions, List<func> consequence, effects effects) {
    public static Codec<SpellMapping> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("name").forGetter((SpellMapping o) -> o.name),
            Interaction.CODEC.listOf().fieldOf("interactions").forGetter((SpellMapping o ) -> o.interactions),
            func.CODEC.listOf().fieldOf("consequence").forGetter((SpellMapping o) -> o.consequence)
    ).apply(instance, ((n, i, inv) -> new SpellMapping(n, i, inv, null))));

    public record Interaction(List<capture> captures, List<filter> filters, List<func> funcs) {
        public static Codec<Interaction> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                capture.CODEC.listOf().fieldOf("captures").forGetter((Interaction o) -> o.captures),
                filter.CODEC.listOf().fieldOf("filters").forGetter((Interaction o) -> o.filters),
                func.CODEC.listOf().fieldOf("funcs").forGetter((Interaction o) -> o.funcs)
        ).apply(instance, (Interaction::new)));
    }


    public record effects(ArrayList<HashMap<String, String>> sound, ArrayList<HashMap<String, String>> particles) {

        public String toString() {
            ArrayList<String> strs = new ArrayList<>();
            strs.add("sound: " + sound);
            strs.add("particles: " + particles);
            return String.join("\n", strs);
        }
    }

    public record capture(String name, HashMap<String, String> parameter) {
        static Codec<capture> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.STRING.fieldOf("name").forGetter((capture o) -> o.name),
                CodecUtil.mapCodec.fieldOf("parameter").forGetter((capture o) -> o.parameter)

        ).apply(instance, capture::new));
    }

    public record filter(String name, HashMap<String, String> parameter) {
        static Codec<filter> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.STRING.fieldOf("name").forGetter((filter o) -> o.name),
                CodecUtil.mapCodec.fieldOf("parameter").forGetter((filter o) -> o.parameter)

        ).apply(instance, filter::new));
    }

    public record func(String name, HashMap<String, String> parameter) {
        static Codec<func> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.STRING.fieldOf("name").forGetter((func o) -> o.name),
                CodecUtil.mapCodec.fieldOf("parameter").forGetter((func o) -> o.parameter)

        ).apply(instance, func::new));
    }

}

