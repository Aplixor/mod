package com.aplixor.mod.spell.filter;

import com.aplixor.mod.spell.SpellMapping;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

public class FilterFactory {

    HashMap<String, Filter<?>> filterMap = new HashMap<>();
    List<BiPredicate<PlayerEntity, LivingEntity>> filterList = new ArrayList<>();

    public FilterFactory() {
        filterMap.put("HasState", new HasState());
    }

    public void add(SpellMapping.filter filter) {
        var filterInstance = filterMap.get(filter.name());
        this.filterList.add(filterInstance.get(filter.parameter()));
    }

    public boolean check(PlayerEntity cast, LivingEntity target) {
        for (var filter : filterList) {
            if (!filter.test(cast, target)) return false;
        }
        return true;
    }

}
