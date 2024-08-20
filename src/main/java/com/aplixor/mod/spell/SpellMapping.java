package com.aplixor.mod.spell;

import com.aplixor.mod.util.CodecUtil;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public record SpellMapping(String name, List<capture> captures, List<filter> filters, List<interaction> interactions,
                           effects effects) {
    public static Codec<SpellMapping> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("name").forGetter((SpellMapping o) -> o.name),
            capture.CODEC.listOf().fieldOf("captures").forGetter((SpellMapping o) -> o.captures),
            filter.CODEC.listOf().fieldOf("filters").forGetter((SpellMapping o) -> o.filters),
            interaction.CODEC.listOf().fieldOf("interactions").forGetter((SpellMapping o) -> o.interactions)
    ).apply(instance, ((n, c, f, i) -> new SpellMapping(n, c, f, i, null))));


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

    public record interaction(String name, HashMap<String, String> parameter) {
        static Codec<interaction> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.STRING.fieldOf("name").forGetter((interaction o) -> o.name),
                CodecUtil.mapCodec.fieldOf("parameter").forGetter((interaction o) -> o.parameter)

        ).apply(instance, interaction::new));

        @Override
        public String toString() {
            return "interaction{" +
                    "name='" + name + '\'' +
                    ", parameter=" + parameter +
                    '}';
        }
    }

    @Override
    public String toString() {
        ArrayList<String> strs = new ArrayList<>();
        strs.add("name: " + name);
        strs.add("capture: " + captures);
        strs.add("interactions: " + interactions);
        strs.add("effects: " + effects);
        return String.join("\n", strs);
    }

}

