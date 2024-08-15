package com.Aplixor.mod.spell.capturing;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Eyesight extends Capture {

    Integer radius;

    @Override
    public void setParameter(HashMap<String, String> map) {
        this.radius = Integer.valueOf(map.get("radius"));
    }

    @Override
    public List<LivingEntity> execute(PlayerEntity cast) {

        Vec3d lookingVec = Vec3d.fromPolar(cast.getPitch(), cast.getYaw()).normalize();
        Vec3d pos1 = new Vec3d(cast.getX() + radius, cast.getY() + radius, cast.getZ() + radius);
        Vec3d pos2 = new Vec3d(cast.getX() - radius, cast.getY() - radius, cast.getZ() - radius);
        Box box = new Box(pos1, pos2);

        List<LivingEntity> list = cast.getWorld()
                .getEntitiesByClass(LivingEntity.class, box, (livingEntity -> !cast.equals(livingEntity)))
                .stream()
                .sorted(Comparator.comparingDouble(o -> cast.getPos().add(o.getPos().multiply(-1)).length()))
                .toList();

        for (var ent : list) {
            var result = ent.getBoundingBox().raycast(cast.getEyePos(), cast.getEyePos().add(lookingVec.multiply(this.radius)));
            if (result.isPresent()) {
                return Collections.singletonList(ent);
            }
        }

        return Collections.emptyList();
    }

    private boolean canSee(PlayerEntity player, LivingEntity entity) {
        // todo: find a better implementation as this method only checks eye positions.
        return player.canSee(entity);
    }
}
