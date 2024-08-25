package com.aplixor.mod.spell.capturing;

import com.aplixor.mod.spell.SpellMapping;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class CaptureFactory {

    HashMap<String, Capture<?>> mapping = new HashMap<>();
    ArrayList<Function<PlayerEntity, List<LivingEntity>>> list = new ArrayList<>();

    public CaptureFactory() {
        mapping.put("Eyesight", new Eyesight());
        mapping.put("Self", new Self());

    }

    public void addCaptures(SpellMapping.capture capture) {
        var capture1 = mapping.get(capture.name());
        list.add(capture1.get(capture.parameter()));
    }

    public List<LivingEntity> get(PlayerEntity cast) {
        return list.stream().map(cap -> cap.apply(cast)).flatMap(List::stream).toList();
    }
}
