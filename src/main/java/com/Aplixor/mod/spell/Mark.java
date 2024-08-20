package com.Aplixor.mod.spell;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

import java.util.*;

public final class Mark implements Component, EntityComponentInitializer, AutoSyncedComponent {
    public static ComponentKey<Mark> markComponentKey = ComponentRegistry.getOrCreate(new Identifier("tutorial", "mark_key"), Mark.class);

    // Set<StateManager> at runtime
    TypeToken<?> token = TypeToken.getParameterized(HashMap.class, String.class, StateManager.class);
    HashMap<String, StateManager> managers = new HashMap<>();
    Gson gson = new GsonBuilder().create();
    String NBTKey = "data";

    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return true;
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {

        // Stringified nbt is not json string
//        String json = new StringNbtWriter().apply(tag.get("data"));
//        this.managers = (HashMap<String, StateManager>) gson.fromJson(json, token);

        this.managers = (HashMap<String, StateManager>) this.gson.fromJson(tag.getString(this.NBTKey), token);
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        tag.putString(this.NBTKey, this.gson.toJson(this.managers));
    }

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(LivingEntity.class, markComponentKey, (livingEntity -> new Mark()));
    }

    public void put(String managerName, UUID owner_id, Integer expireAfter) {
        this.managers.putIfAbsent(managerName, new StateManager(managerName, new HashMap<>()));
        this.managers.get(managerName).states.putIfAbsent(owner_id, new State(owner_id, new ArrayList<>()));
        this.managers.get(managerName).states.get(owner_id).expireTickEntry.add(expireAfter);
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
        return Optional.of(value);
    }


    record StateManager(String id, HashMap<UUID, State> states){};
    record State(UUID owner, List<Integer> expireTickEntry){};
}
