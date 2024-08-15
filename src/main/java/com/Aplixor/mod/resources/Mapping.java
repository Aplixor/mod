package com.Aplixor.mod.resources;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public record Mapping(ArrayList<spells> spells, skill_tree skill_tree, damage_calculation damage_calculation) {

    public record spells(String name, List<capture> captures, List<interaction> interactions, effects effects) {
        public static Codec<spells> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.STRING.fieldOf("name").forGetter((spells o) -> o.name),
                capture.CODEC.listOf().fieldOf("captures").forGetter((spells o) -> o.captures),
                interaction.CODEC.listOf().fieldOf("interactions").forGetter((spells o) -> o.interactions)
        ).apply(instance, ((n, c, i) -> new spells(n, c, i, null))));


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
                    Mapping.mapCodec.fieldOf("parameter").forGetter((capture o) -> o.parameter)

            ).apply(instance, capture::new));
        }

        public record filter(String name, HashMap<String, String> parameter) {
            static Codec<filter> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    Codec.STRING.fieldOf("name").forGetter((filter o) -> o.name),
                    Mapping.mapCodec.fieldOf("parameter").forGetter((filter o) -> o.parameter)

            ).apply(instance, filter::new));
        }

        public record interaction(String name, HashMap<String, String> parameter) {
            static Codec<interaction> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    Codec.STRING.fieldOf("name").forGetter((interaction o) -> o.name),
                    Mapping.mapCodec.fieldOf("parameter").forGetter((interaction o) -> o.parameter)

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
    };
    record skill_tree() {};
    record damage_calculation() {};

    @Override
    public String toString() {
        return "Mapping{" +
                "spells=" + spells +
                ", skill_tree=" + skill_tree +
                ", damage_calculation=" + damage_calculation +
                '}';
    }

    static Codec<HashMap<String, String>> mapCodec = Codec.compoundList(Codec.STRING, Codec.STRING).xmap((pairs -> {
        HashMap<String, String> map = new HashMap<>();
        pairs.forEach((pair -> map.put(pair.getFirst(), pair.getSecond())));
        return map;
    }), (map -> map.entrySet().stream()
            .map((entry -> new Pair<String, String>(entry.getKey(), entry.getValue())))
            .toList()));

}
