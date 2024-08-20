package com.aplixor.mod.core.Interactions;

import com.aplixor.mod.attribute.AttributeBase;
import net.minecraft.entity.LivingEntity;

public abstract class BaseInteractImpl<T> implements BaseInteract{

    protected AttributeBase<T> attribute;
    protected T value;
    protected LivingEntity target;


    void dosoemthing(LivingEntity target, Object parameters) {
        parameters.toString();
    }
}
