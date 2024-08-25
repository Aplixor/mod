package com.aplixor.mod.command;

import com.aplixor.mod.spell.SpellMapping;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class SDynamicRegisterContentCommand implements com.mojang.brigadier.Command<ServerCommandSource> {

    public static void register() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("sdrc").executes(new SDynamicRegisterContentCommand()));
        }));
    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        RegistryKey<Registry<SpellMapping>> key = RegistryKey.ofRegistry(new Identifier("tutorial", "spells"));

        context.getSource().getServer().getRegistryManager().get(key).stream().forEach((spellMapping -> context.getSource().sendMessage(Text.literal(String.valueOf(spellMapping)))));
        return 0;
    }
}
