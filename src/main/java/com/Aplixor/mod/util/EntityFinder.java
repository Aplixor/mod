package com.Aplixor.mod.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

public class EntityFinder {

    public static final Logger LOGGER = LoggerFactory.getLogger("template-mod");

    public static ArrayList<Entity> getEntities(Integer radius, World world, PlayerEntity player) {

        Vec3d pos1 = new Vec3d(player.getX() + radius, player.getY() + radius, player.getZ() + radius);
        Vec3d pos2 = new Vec3d(player.getX() - radius, player.getY() - radius, player.getZ() - radius);
        Box box = new Box(pos1, pos2);

        return (ArrayList<Entity>) world.getOtherEntities(player, box);

    }

    public static ArrayList<Entity> getEntities(Integer radius, World world, PlayerEntity player, Integer angle) {

        ArrayList<Entity> entityList = getEntities(radius, world, player);

        Vec3d lookingVector = Vec3d.fromPolar(player.getPitch(), player.getYaw()).normalize();
        ArrayList<Entity> returnList = new ArrayList<>();

        for (Entity entity : entityList) {
            if (!(entity instanceof LivingEntity)) continue;
            double angleToEntity = entity.getPos().subtract(player.getPos()).normalize().dotProduct(lookingVector);
            if (angle > Math.toDegrees(Math.acos(angleToEntity))) {
                returnList.add(entity);
            };
        }

        Comparator<? super Entity> comp = ((entityA, entityB) -> {
            double entityAngleA = entityA.getPos().subtract(player.getPos()).normalize().dotProduct(lookingVector);
            double entityAngleB = entityB.getPos().subtract(player.getPos()).normalize().dotProduct(lookingVector);
            // prior smaller angel from player's view
            double delta = entityAngleB - entityAngleA;
            return (int) Math.signum(delta);
        });

        returnList.sort(comp);

        return returnList;
    }

    public static ArrayList<Entity> getFacingEntities(World world, PlayerEntity user, Integer radius, Predicate<Entity> check) {
        ArrayList<Entity> fetched = EntityFinder.getEntities(radius,world,user);
        ArrayList<Entity> entityArryList = new ArrayList<>();
        for (Entity ent : fetched) {
            if (!(ent instanceof LivingEntity)) continue;
            if (!check.test(ent)) continue;

            Vec3d newVec = ent.getPos().subtract(user.getPos());
            Vec3d lookingVec = Vec3d.fromPolar(user.getPitch(), user.getYaw());
            if (newVec.dotProduct(lookingVec) < 0) continue;
            entityArryList.add(ent);
        }
        return entityArryList;
    }
}
