package com.aplixor.mod.items;


import com.aplixor.mod.spell.SpellLoader;
import com.aplixor.mod.spell.SpellMapping;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.HashMap;

public class TriggerDelegator {
    private static TriggerDelegator delegator = null;
    public final HashMap<String, TriggerTypes.useTrigger> proxyMapping = new HashMap<>();
    private TriggerDelegator() {
    }

    public static TriggerDelegator getInstance() {
        if (delegator == null) {
            delegator = new TriggerDelegator();
        }
        return delegator;
    }

    public void get(String key, World world, PlayerEntity user, Hand hand) {
        // really stupid method
        this.update(world.getRegistryManager());
        proxyMapping.getOrDefault(key, (var1, var2, var3)-> {return null;}).use(world, user, hand);
    }

    public void put(String str, TriggerTypes.useTrigger func) {
        this.proxyMapping.put(str, func);
    }

    private void update(DynamicRegistryManager manager) {
        RegistryKey<Registry<SpellMapping>> key = RegistryKey.ofRegistry(new Identifier("tutorial", "spells"));
        SpellLoader loader = new SpellLoader();
        manager.get(key).forEach((loader::addSpell));
        loader.loadAll();
    }

}
