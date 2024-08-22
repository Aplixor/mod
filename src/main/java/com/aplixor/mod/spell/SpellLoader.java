package com.aplixor.mod.spell;

import com.aplixor.mod.TemplateMod;
import com.aplixor.mod.items.TriggerDelegator;
import com.aplixor.mod.items.TriggerTypes;
import com.aplixor.mod.spell.capturing.CaptureFactory;
import com.aplixor.mod.spell.filter.FilterFactory;
import com.aplixor.mod.spell.functions.FunctionFactory;

import java.util.ArrayList;
import java.util.Collections;

public class SpellLoader {

    ArrayList<SpellRepresentation> spellRepresentations = new ArrayList<>();

    public boolean addSpell(SpellMapping spell) {

        CaptureFactory captureFactory = new CaptureFactory();
        spell.interactions().forEach((i -> i.captures().forEach(captureFactory::addCaptures)));

        FilterFactory filterFactory = new FilterFactory();
        spell.interactions().forEach((i -> i.filters().forEach(filterFactory::add)));

        FunctionFactory functionFactory = new FunctionFactory();
        spell.interactions().forEach((i -> i.funcs().forEach(functionFactory::putFunction)));

        var instructionWrapper = new InstructionWrapper();

        instructionWrapper.add(captureFactory::get, filterFactory::check, functionFactory.build());
        for (SpellMapping.func k : spell.consequence()) {
            TemplateMod.LOGGER.debug(String.format("Consequence %s on spell %s", k, spell.name()));
            FunctionFactory functionFactory1 = new FunctionFactory();
            functionFactory1.putFunction(k);
            instructionWrapper.add(Collections::singletonList, ((ign1, ign2) -> true), functionFactory1.build());
        }

        this.spellRepresentations.add(new SpellRepresentation(spell.name(), instructionWrapper.get()));
        return true;

    }

    public void loadAll() {
        for (var spell : spellRepresentations) {
            TemplateMod.LOGGER.debug(String.format("Load spell %s", spell.name()));
            TriggerDelegator.getInstance().put(spell.name(), spell.useTrigger());
        }
    }

    record SpellRepresentation(String name, TriggerTypes.useTrigger useTrigger) {};
}
