package com.aplixor.mod.resources;

import com.aplixor.mod.spell.SpellMapping;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;

import java.util.*;

public record Mapping(ArrayList<SpellMapping> spells, skill_tree skill_tree, damage_calculation damage_calculation) {

    ;
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
}
