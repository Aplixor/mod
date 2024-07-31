package com.Aplixor.mod;

import com.Aplixor.mod.items.GenericItem;
import com.Aplixor.mod.items.TriggerDelegator;
import com.Aplixor.mod.spell.mage.Fireball;
import com.Aplixor.mod.spell.pike.ReturnStab;
import com.Aplixor.mod.spell.pike.stabby;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class RegisterHelper {

    final String namespace = "tutorial";

    public void registerItems() {
        Registry.register(Registries.ITEM, new Identifier(namespace, "testb"), new GenericItem());
    }

    public void registerComponents() {
    }

    public void registerAll() {
        this.registerItems();
        TriggerDelegator.getInstance().put("1", ((world, user, hand) -> new Fireball().useTrigger(world, user, hand)));
        TriggerDelegator.getInstance().put("lol", ((world, user, hand) -> new ReturnStab().useTrigger(world, user, hand)));
        TriggerDelegator.getInstance().put("pike", ((world, user, hand) -> new stabby().useTrigger(world, user, hand)));

    }
}
