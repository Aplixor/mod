package com.aplixor.mod;

import com.aplixor.mod.command.SDynamicRegisterContentCommand;
import com.aplixor.mod.items.GenericItem;
import com.aplixor.mod.spell.SpellMapping;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
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

        SDynamicRegisterContentCommand.register();
//        var loader = new SpellLoader();
//
//        DynamicRegistrySetupCallback.EVENT.register(registryView -> {
//            System.out.println(registryView.asDynamicRegistryManager());
//            registryView.registerEntryAdded(key, ((rawId, id, object) -> {
//                loader.addSpell(object);
//                loader.loadAll();
//            }));
//        });

    }
}
