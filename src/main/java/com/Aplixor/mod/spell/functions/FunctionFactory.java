package com.Aplixor.mod.spell.functions;

import com.Aplixor.mod.resources.Mapping;

import java.util.ArrayList;
import java.util.HashMap;

public class FunctionFactory {

    HashMap<String, java.util.function.Function<Function, Function>> functionMap = new HashMap<>();
    ArrayList<Mapping.spells.interaction> interactions = new ArrayList<>();

    public FunctionFactory() {
        addFunctions();
    }

    public void putFunction(Mapping.spells.interaction interaction) {
        interactions.add(interaction);
    }

    public Function build() {
        Function base = Function.getEmpty();

        for (int i=0;i<interactions.size();i++) {
            var interact = interactions.get(i);
            base = this.functionMap.get(interact.name()).apply(base);
            base.applyParameter(interact.parameter());
        }

        return base;
    }

    void addFunctions() {
        functionMap.put("Hurt", (Hurt::new));
        functionMap.put("VelocityTo", (VelocityTo::new));
        functionMap.put("Parabola", (Parabola::new));
        functionMap.put("Test", (Test::new));
    }
}
