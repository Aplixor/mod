package com.aplixor.mod.mixin.client;

import com.aplixor.mod.Mod;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
public class ExampleClientMixin {
	@Inject(at = @At("TAIL"), method = "tickTime")
	private void init(CallbackInfo info) {
		Mod.getInstance().getScheduler().tick();
	}
}