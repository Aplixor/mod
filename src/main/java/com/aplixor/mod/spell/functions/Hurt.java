package com.aplixor.mod.spell.functions;

import com.aplixor.mod.core.Interactions.InteractFactory;
import com.aplixor.mod.core.Interactions.Subtract;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class Hurt extends Function {

    private Integer amount = 10;

    public Hurt(Function deco) {
        super(deco);
    }

    @Override
    public void insturction(PlayerEntity cast, LivingEntity target) {
        new Subtract(InteractFactory.entityHealth(target), amount).execute();
    }

}
