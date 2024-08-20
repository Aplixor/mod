package com.aplixor.mod.spell.functions;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class Test extends Function{

    public Test(Function deco) {
        super(deco);
    }

    @Override
    public void insturction(PlayerEntity cast, LivingEntity target) {

        cast.sendMessage(Text.literal(String.valueOf(cast.getWorld().getTime())));
    }
}
