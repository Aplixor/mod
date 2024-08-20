package com.Aplixor.mod.spell.functions;

import com.Aplixor.mod.spell.Mark;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;
import java.util.UUID;

public class AddState extends Function {

    String managerName = null;
    Integer expireAfter = null;

    public AddState(Function deco) {
        super(deco);
    }

    @Override
    public void applyParameter(HashMap<String, String> param) {
        this.managerName = param.get("ManagerName");
        this.expireAfter = Integer.valueOf(param.get("ExpireAfter"));
    }

    @Override
    public void insturction(PlayerEntity cast, LivingEntity target) {
        UUID owner_id = cast.getUuid();
        Mark.markComponentKey.get(target).put(this.managerName, owner_id, Math.toIntExact(cast.getWorld().getTime()) + this.expireAfter);
    }
}
