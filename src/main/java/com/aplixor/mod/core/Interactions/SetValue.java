package com.aplixor.mod.core.Interactions;

import com.aplixor.mod.attribute.AttributeBase;
import net.minecraft.entity.LivingEntity;

public class SetValue extends BaseInteractImpl<Integer> {

    public SetValue(LivingEntity target, AttributeBase<Integer> attribute, Integer value) {
        this.attribute = attribute;
        this.value = value;
        this.target = target;
    }

    @Override
    public void execute() {
        this.attribute.getAttribute().get(this.target).put("test", this.value);
    }
}
