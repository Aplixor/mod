package com.aplixor.mod.mixin;

import com.aplixor.mod.mod;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public class ServerTickMixin {
	@Inject(at = @At("TAIL"), method = "tick")
	private void onTick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
		mod.getInstance().getScheduler().tick();
	}
}