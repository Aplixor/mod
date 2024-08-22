package com.aplixor.mod;

import com.aplixor.mod.items.GenericItem;
import com.aplixor.mod.spell.SpellLoader;
import com.aplixor.mod.spell.SpellMapping;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
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

        RegistryKey<Registry<SpellMapping>> key = RegistryKey.ofRegistry(new Identifier("tutorial", "spells"));
        DynamicRegistries.registerSynced(key, SpellMapping.CODEC);

        var loader = new SpellLoader();

        DynamicRegistrySetupCallback.EVENT.register(registryView -> {
            registryView.registerEntryAdded(key, ((rawId, id, object) -> {
                loader.addSpell(object);
                loader.loadAll();
            }));
        });

    }
}
