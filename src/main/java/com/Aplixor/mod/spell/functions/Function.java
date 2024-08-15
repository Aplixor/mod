package com.Aplixor.mod.spell.functions;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;

public class Function {
    Function deco;
    String name = "base";

    public Function(Function deco) {
        this.deco = deco;
    }

    public static Function getEmpty() {
        return new Function(null);
    }

    public void applyParameter(HashMap<String, String> param) {};

    public void insturction(PlayerEntity cast, LivingEntity target) {};

    public void execute(PlayerEntity cast, LivingEntity target) {
        this.insturction(cast, target);
        if (this.deco != null) {
            this.deco.execute(cast, target);
        }
    }
}
