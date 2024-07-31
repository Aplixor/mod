package com.Aplixor.mod.spell.pike;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Pair;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public final class point implements PointComponent, EntityComponentInitializer {

    private ArrayList<String> targetList = new ArrayList<>();
    private ArrayList<Integer> targetExpireTickList = new ArrayList<>();
    private final String delimiter = "|";
    private final String expireTickKey = "expireTickList";
    private final String targetStringKey = "targetString";
    private Integer expireTick = 3;


    public String getPlayer() {
        return this.targetList.toString();
    }

    private void validate() {
        ;
    }

    @Override
    public Optional<Pair<String, Integer>> get(String criteria) {
        this.validate();

        Pair<String, Integer> pair = new Pair<>(null, null);
        int i = 0;
        for (String target : this.targetList) {
            if (!target.equals(criteria)) {
                i++;
                continue;
            }

            pair.setLeft(target);
            pair.setRight(this.targetExpireTickList.get(i));
        }

        if (pair.equals(new Pair<>(null, null))) return Optional.empty();
        return Optional.of(pair);
    }

    @Override
    public void put(String key, Integer tick) {
        this.targetList.add(key);
        this.targetExpireTickList.add(tick);
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        this.targetExpireTickList = new ArrayList<>(Arrays.stream(tag.getIntArray(this.expireTickKey)).boxed().toList());
        this.targetList = new ArrayList<>(Arrays.asList(tag.getString(this.targetStringKey).split(this.delimiter)));
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        tag.putIntArray(this.expireTickKey, this.targetExpireTickList);
        tag.putString(this.targetStringKey, String.join(this.delimiter, this.targetList));
    }

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(LivingEntity.class, pointComp, (livingEntity -> new point()));
//        registry.registerForPlayers(pointComp, (playerEntity -> new point()), RespawnCopyStrategy.NEVER_COPY);
    }

}
