package com.Aplixor.mod.mixin;

import com.Aplixor.mod.attribute.Health;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class healthMixin {

    @Shadow
    public abstract void kill();

    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(CallbackInfo ci) {
        if ((Integer) Health.attribute.get(this).get("test") <= 0) {
            this.kill();
        }
    }
}
