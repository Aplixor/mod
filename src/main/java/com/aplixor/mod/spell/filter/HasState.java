package com.aplixor.mod.spell.filter;

import com.aplixor.mod.spell.Mark;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;

public class HasState implements Filter{

    String managerName = "_null";

    @Override
    public void applyParameter(HashMap<String, String> map) {
        this.managerName = map.getOrDefault("ManagerName", managerName);
    }

    @Override
    public boolean apply(PlayerEntity cast, LivingEntity target) {
        var opt =  Mark.markComponentKey.get(target).pop(this.managerName, cast.getUuid());
        return opt.isPresent();
    }

}
