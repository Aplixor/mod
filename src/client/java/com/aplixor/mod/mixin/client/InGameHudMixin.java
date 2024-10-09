package com.aplixor.mod.mixin.client;

import com.aplixor.mod.entity.CustomDatatracker;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LayeredDrawer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.text.Text;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Shadow
    private final LayeredDrawer layeredDrawer;

    @Shadow @Final private MinecraftClient client;

    protected InGameHudMixin(LayeredDrawer layeredDrawer) {
        this.layeredDrawer = layeredDrawer;
    }

    @Inject(method = "<init>",at = @At(value = "TAIL"))
    private void init(MinecraftClient client, CallbackInfo ci) {
        this.layeredDrawer.addLayer(this::renderHotbar);
    };


    @Unique
    private void renderHotbar(DrawContext context, float tickDelta) {
        Text healthText = Text.literal("Health: " + ((CustomDatatracker)client.player).template_mod_template_1_20_5$getModHealth());
        Text manaText = Text.literal(String.format("Mana: %f.1", ((CustomDatatracker)client.player).template_mod_template_1_20_5$getModMana()));

        var healthTextHeight = context.getScaledWindowHeight()/2;
        var manaTextHeight = healthTextHeight +  client.textRenderer.fontHeight;

        context.drawTextWithShadow(client.textRenderer, healthText, 0, healthTextHeight, ColorHelper.Argb.getArgb(255, 255, 255, 255));
        context.drawTextWithShadow(client.textRenderer, manaText, 0, manaTextHeight, ColorHelper.Argb.getArgb(255, 255, 255, 255));

    }
}
