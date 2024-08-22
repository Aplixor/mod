package com.aplixor.mod.spell;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mojang.serialization.JsonOps;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

import java.lang.reflect.Type;
import java.util.*;

public final class Mark implements Component, EntityComponentInitializer, AutoSyncedComponent {
    public static ComponentKey<Mark> markComponentKey = ComponentRegistry.getOrCreate(new Identifier("tutorial", "mark_key"), Mark.class);

    Type type = new TypeToken<HashMap<String, StateManager>>() {}.getType();
    HashMap<String, StateManager> managers = new HashMap<>();
    Gson gson = new GsonBuilder().create();
    String NBTKey = "mark_data";
    public Object provider;


    // class initialization crashes the game for some reason
    public static Mark getMark(Object provider) {
        var m = new Mark();
        m.provider = provider;
        return m;
    }

    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return true;
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        var jsonElement = NbtOps.INSTANCE.convertTo(JsonOps.INSTANCE, tag.get(this.NBTKey));
        this.managers = this.gson.fromJson(jsonElement, type);
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        var nbtElement = JsonOps.INSTANCE.convertTo(NbtOps.INSTANCE, this.gson.toJsonTree(this.managers));
        tag.put(this.NBTKey, nbtElement);
    }

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(LivingEntity.class, markComponentKey, (livingEntity -> Mark.getMark(livingEntity)));
    }

    public void put(String managerName, UUID owner_id, Integer expireAfter) {
        this.managers.putIfAbsent(managerName, new StateManager(managerName, new HashMap<>()));
        this.managers.get(managerName).states.putIfAbsent(owner_id, new State(owner_id, new ArrayList<>()));
        this.managers.get(managerName).states.get(owner_id).expireTickEntry.add(expireAfter);

//        markComponentKey.sync(this.provider);
    }

    /**
     * @return aa
     */
    public Optional<Integer> pop(String managerName, UUID owner_id) {

        if (this.managers.getOrDefault(managerName, null) == null) return Optional.empty();
        var manager = this.managers.get(managerName);
        if (!manager.states.containsKey(owner_id)) return Optional.empty();
        var entries = manager.states.get(owner_id).expireTickEntry;
        if (entries.isEmpty()) return  Optional.empty();

        var value = entries.getFirst();
        entries.removeFirst();
//        markComponentKey.sync(this.provider);
        return Optional.of(value);
    }


    record StateManager(String id, HashMap<UUID, State> states){};
    record State(UUID owner, List<Integer> expireTickEntry){};
}
