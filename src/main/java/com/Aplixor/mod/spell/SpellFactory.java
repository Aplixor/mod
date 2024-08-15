package com.Aplixor.mod.spell;

import com.Aplixor.mod.spell.functions.Function;
import com.Aplixor.mod.spell.functions.Hurt;
import com.Aplixor.mod.spell.functions.VelocityTo;

import java.util.HashMap;
import java.util.function.Supplier;

public class SpellFactory {

    public Function get() {

        HashMap<String, Supplier<Function>> mapping = new HashMap<>();
        mapping.put("hurt", () -> new VelocityTo(new Hurt(Function.getEmpty())));
        return mapping.get("hurt").get();
    }
}
