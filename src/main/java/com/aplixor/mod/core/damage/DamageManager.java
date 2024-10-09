package com.aplixor.mod.core.damage;

import com.aplixor.mod.entity.CustomDatatracker;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class DamageManager {

    public void apply(DamageInstance damage, PlayerEntity attacker, LivingEntity target) {

        var directDamage = this.convert(damage, attacker, target);
        this.directAttack(directDamage, attacker, target);
    }

    public DamageInstance convert(DamageInstance damage, PlayerEntity attacker, LivingEntity target) {

    }

    public void directAttack(DamageInstance damage, PlayerEntity attacker, LivingEntity target) {

        if (((CustomDatatracker) target).template_mod_template_1_20_5$getEnergyShield() >= 0) {
            var energy_shield = ((CustomDatatracker) target).template_mod_template_1_20_5$getEnergyShield();
            ((CustomDatatracker) target).template_mod_template_1_20_5$setEnergyShield(damage.raw().floatValue());
            return;
        }


        var health = ((CustomDatatracker) target).template_mod_template_1_20_5$getModHealth();
        ((CustomDatatracker) target).template_mod_template_1_20_5$setModHealth(damage.raw().floatValue());
    }
}
