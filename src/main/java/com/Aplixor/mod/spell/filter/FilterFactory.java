package com.Aplixor.mod.spell.filter;

import com.Aplixor.mod.resources.Mapping;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

public class FilterFactory {

    HashMap<String, Supplier<Filter>> filterMap = new HashMap<>();
    List<Filter> filterList = new ArrayList<>();

    public FilterFactory() {
        filterMap.put("HasState", (HasState::new));
    }

    public void add(Mapping.spells.filter filter) {
        var filterInstance = filterMap.get(filter.name()).get();
        filterInstance.applyParameter(filter.parameter());
        this.filterList.add(filterInstance);
    }

    public boolean check(PlayerEntity cast, LivingEntity target) {
        for (var filter : filterList) {
            if (!filter.apply(cast, target)) return false;
        }
        return true;
    }

}
