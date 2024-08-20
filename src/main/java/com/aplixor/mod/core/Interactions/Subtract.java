package com.aplixor.mod.core.Interactions;

public class Subtract extends BaseInteractImpl<Integer> {

    BaseInteractImpl<Integer> deco;

    public Subtract(BaseInteractImpl<Integer> deco, Integer value) {
        this.deco = deco;
        deco.value -= value;
    }

    @Override
    public void execute() {
        deco.execute();
    }
}
