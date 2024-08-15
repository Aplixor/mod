package com.Aplixor.mod.spell.capturing;

import com.Aplixor.mod.resources.Mapping;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

public class CaptureFactory {

    HashMap<String, Supplier<Capture>> mapping = new HashMap<>();
    ArrayList<Capture> list = new ArrayList<>();

    public CaptureFactory() {
        mapping.put("Eyesight", Eyesight::new);
        mapping.put("Self", Self::new);

    }

    public void addCaptures(Mapping.spells.capture capture) {
        var a = mapping.get(capture.name()).get();
        a.setParameter(capture.parameter());
        list.add(a);
    }

    public List<LivingEntity> get(PlayerEntity cast) {
        ArrayList<LivingEntity> result = new ArrayList<>();
        for (var cap : list) {
            result.addAll(cap.execute(cast));
        }
        System.out.println((list.stream().map(capture -> capture.execute(cast)).flatMap(List::stream).toList()));
        return result;
    }
}
