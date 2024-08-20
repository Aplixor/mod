package com.aplixor.mod.attribute;

import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;


public interface AttributeBase<T> extends CommonTickingComponent {

    T get(String key);

    void put(String key, T value);

    ComponentKey<AttributeBase> getAttribute();
}
