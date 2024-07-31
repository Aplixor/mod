package com.Aplixor.mod.spell.pike;

import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;

import java.util.Optional;

public interface PointComponent extends Component {
    ComponentKey<PointComponent> pointComp = ComponentRegistry.getOrCreate(Identifier.of("tutorial", "point"), PointComponent.class);


    Optional<Pair<String, Integer>> get(String criteria);

    void put(String key, Integer tick);
}
