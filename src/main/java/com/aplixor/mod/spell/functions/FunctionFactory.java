package com.aplixor.mod.spell.functions;

import com.aplixor.mod.items.TriggerTypes;
import com.aplixor.mod.spell.SpellMapping;
import com.google.gson.Gson;
import com.mojang.serialization.JsonOps;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;

public class FunctionFactory {

    HashMap<String, Function<?>> functionMap = new HashMap<>();
    ArrayList<SpellMapping.func> funcionList = new ArrayList<>();

    public FunctionFactory() {
        addFunctions();
    }

    public void putFunction(SpellMapping.func func) {
        funcionList.add(func);
    }

    public BiConsumer<PlayerEntity, LivingEntity> build() {

        ArrayList<BiConsumer<PlayerEntity, LivingEntity>> functions = new ArrayList<>();

        for (int i = 0; i< funcionList.size(); i++) {
            var interact = funcionList.get(i);
            var func = this.functionMap.get(interact.name());
            if (func == null) {
                System.out.printf("Function %s is null%n", interact.name());
                continue;
            }
            functions.add(func.get(interact.parameter()));
        }

        return (cast, target) -> functions.forEach(f -> f.accept(cast, target));
    }

    void addFunctions() {
        functionMap.put("Hurt", new Hurt());
        functionMap.put("VelocityTo", new VelocityTo());
        functionMap.put("Parabola", new Parabola());
//        functionMap.put("Test", (Test::new));
        functionMap.put("AddState", new AddState());
        functionMap.put("ScheduleSpell", new ScheduleSpell());
    }
}
