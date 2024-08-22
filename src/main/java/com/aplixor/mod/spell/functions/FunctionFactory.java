package com.aplixor.mod.spell.functions;

import com.aplixor.mod.spell.SpellMapping;

import java.util.ArrayList;
import java.util.HashMap;

public class FunctionFactory {

    HashMap<String, java.util.function.Function<Function, Function>> functionMap = new HashMap<>();
    ArrayList<SpellMapping.func> funcionList = new ArrayList<>();

    public FunctionFactory() {
        addFunctions();
    }

    public void putFunction(SpellMapping.func func) {
        funcionList.add(func);
    }

    public Function build() {
        Function base = Function.getEmpty();

        for (int i = 0; i< funcionList.size(); i++) {
            var interact = funcionList.get(i);
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
        functionMap.put("AddState", (AddState::new));
        functionMap.put("ScheduleSpell", (ScheduleSpell::new));
    }
}
