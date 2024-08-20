package com.aplixor.mod.items;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.HashMap;

public class TriggerDelegator {
    private static TriggerDelegator delegator = null;
    private final HashMap<String, TriggerTypes.useTrigger> proxyMapping = new HashMap<>();
    private TriggerDelegator() {
    }

    public static TriggerDelegator getInstance() {
        if (delegator == null) {
            delegator = new TriggerDelegator();
        }
        return delegator;
    }

    public void get(String str, World world, PlayerEntity user, Hand hand) {
        proxyMapping.getOrDefault(str, (var1, var2, var3)-> {return null;}).use(world, user, hand);
    }

    public void put(String str, TriggerTypes.useTrigger func) {
        this.proxyMapping.put(str, func);
    }

}
