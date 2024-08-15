package com.Aplixor.mod.spell.functions;

import com.Aplixor.mod.spell.capturing.Capture;
import com.Aplixor.mod.spell.capturing.Eyesight;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;

public class Test extends Function{

    public Test(Function deco) {
        super(deco);
    }

    @Override
    public void insturction(PlayerEntity cast, LivingEntity target) {
        Capture eyesight = new Eyesight();
        HashMap<String, String> map = new HashMap<>();
        map.put("radius", "10");
        eyesight.setParameter(map);
        eyesight.execute(cast);

    }
}
