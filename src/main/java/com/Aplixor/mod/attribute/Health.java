package com.Aplixor.mod.attribute;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

public final class Health implements AttributeBase<Integer>, EntityComponentInitializer {
    public static ComponentKey<AttributeBase> attribute = ComponentRegistry.getOrCreate(Identifier.of("tutorial", "health_attribute"), AttributeBase.class);
    Integer value = 20;
    Integer internal_tick = 0;
    @Override
    public Integer get(String key) {
        return this.value;
    }

    @Override
    public void put(String key, Integer value) {
        this.value = value;
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        this.value = tag.getInt("key");
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        tag.putInt("key", value);
    }

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(LivingEntity.class, attribute, (livingEntity -> new Health()));
    }

    @Override
    public void tick() {
        if (internal_tick >= 20 && this.value < 20) {
            this.value += 1;
        }
        internal_tick %= 20;
        internal_tick++;
    }

    public ComponentKey<AttributeBase> getAttribute() {
        return attribute;
    }
}
