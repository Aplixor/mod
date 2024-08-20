package com.Aplixor.mod;

import com.Aplixor.mod.items.GenericItem;
import com.Aplixor.mod.items.TriggerDelegator;
import com.Aplixor.mod.resources.Mapping;
import com.Aplixor.mod.spell.capturing.CaptureFactory;
import com.Aplixor.mod.spell.filter.FilterFactory;
import com.Aplixor.mod.spell.functions.FunctionFactory;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;


public class RegisterHelper {

    final String namespace = "tutorial";

    public void registerItems() {
        Registry.register(Registries.ITEM, new Identifier(namespace, "generic_item1"), new GenericItem());
    }


    public void registerAll() {
        this.registerItems();


//        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new ResourceLoader());

        RegistryKey<Registry<Mapping.spells>> key = RegistryKey.ofRegistry(new Identifier("tutorial", "spells"));

        DynamicRegistries.registerSynced(key, Mapping.spells.CODEC);

        DynamicRegistrySetupCallback.EVENT.register((registryView -> {
            registryView.registerEntryAdded(key, ((rawId, id, spell) -> {
                System.out.println("id: " + id.toString());
                System.out.println("spell: " + spell.toString());
                FunctionFactory functionFactory = new FunctionFactory();
                spell.interactions().forEach((functionFactory::putFunction));

                CaptureFactory captureFactory = new CaptureFactory();
                spell.captures().forEach(captureFactory::addCaptures);

                FilterFactory filterFactory = new FilterFactory();
                spell.filters().forEach(filterFactory::add);

                TriggerDelegator.getInstance().put(spell.name(), ((world, user, hand) -> {
                    for (LivingEntity target : captureFactory.get(user)) {
                        if (!filterFactory.check(user, target)) continue;
                        functionFactory.build().execute(user, target);
                    }
                    return null;
                }));
            }));
        }));

    }
}
