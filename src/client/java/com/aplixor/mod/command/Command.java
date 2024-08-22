package com.aplixor.mod.command;

import com.aplixor.mod.spell.SpellMapping;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class Command implements com.mojang.brigadier.Command<FabricClientCommandSource> {


    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register(((dispatcher1, registryAccess) -> {
            dispatcher1.register(ClientCommandManager.literal("getDynamicRegistryContent").executes(new Command()));
        }));
    }


    @Override
    public int run(CommandContext<FabricClientCommandSource> context) throws CommandSyntaxException {
        RegistryKey<Registry<SpellMapping>> key = RegistryKey.ofRegistry(new Identifier("tutorial", "spells"));

        context.getSource().getRegistryManager().get(key).stream().forEach((spells -> context.getSource().getClient().player.sendMessage(Text.literal(spells.toString()))));

        return 0;
    }
}
