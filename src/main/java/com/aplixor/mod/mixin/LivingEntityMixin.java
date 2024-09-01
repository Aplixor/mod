package com.aplixor.mod.mixin;

import com.aplixor.mod.attribute.AttributeList;
import com.aplixor.mod.entity.ModHealth;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements ModHealth {

    @Shadow public abstract double getAttributeValue(RegistryEntry<EntityAttribute> attribute);

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Unique
    private static final TrackedData<Float> MOD_HEALTH = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.FLOAT);
    @Unique
    private static final TrackedData<Float> MOD_MANA = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.FLOAT);


    @Inject(method = "createLivingAttributes", at = @At("TAIL"))
    private static void createLivingAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        AttributeList.Entry_List.forEach(cir.getReturnValue()::add);
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    public void initDataTracker(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(MOD_HEALTH, 20f);
        builder.add(MOD_MANA, 100f);
    }

    @Inject(method = "onTrackedDataSet", at = @At("TAIL"))
    public void onTrackedDataSet(TrackedData<?> data, CallbackInfo ci) {
        if (MOD_HEALTH.equals(data) & this.dataTracker.get(MOD_HEALTH) <= 0) {
            this.kill();
        }
    }


    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(CallbackInfo ci) {
        var value = Math.clamp(this.dataTracker.get(MOD_HEALTH) + this.getAttributeValue(AttributeList.health_regenerate), 0, this.getAttributeValue(AttributeList.max_health));
        this.dataTracker.set(MOD_HEALTH, (float) value);

        var mana = Math.clamp(this.dataTracker.get(MOD_MANA) + this.getAttributeValue(AttributeList.mana_regenerate), 0, this.getAttributeValue(AttributeList.max_mana));
        this.dataTracker.set(MOD_MANA, (float) mana);

    }

    @Unique
    public void template_mod_template_1_20_5$setModHealth(Float value) {
        this.dataTracker.set(MOD_HEALTH, value);
    }

    @Unique
    public float template_mod_template_1_20_5$getModHealth() {
        return this.dataTracker.get(MOD_HEALTH);
    }

    @Override
    public void template_mod_template_1_20_5$setModMana(Float value) {
        this.dataTracker.set(MOD_MANA, value);
    }

    @Override
    public float template_mod_template_1_20_5$getModMana() {
        return this.dataTracker.get(MOD_MANA);
    }
}
