package com.aplixor.mod.mixin;

import com.aplixor.mod.Mod;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {

    @Inject(at = @At("TAIL"), method = "tickTime")
    private void tickTime(CallbackInfo ci) {
        Mod.getInstance().getScheduler().tick();
    }
}
