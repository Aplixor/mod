package com.aplixor.mod.attribute;

import com.aplixor.mod.TemplateMod;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class AttributeList {

    public static List<RegistryEntry<EntityAttribute>> Entry_List = new ArrayList<>();

    public static RegistryEntry<EntityAttribute> health_Attribute = register("health", new ClampedEntityAttribute("tutorial.bbcc", 20, 0, 10000));
    public static RegistryEntry<EntityAttribute> health_regenerate = register("health_regenerate", new ClampedEntityAttribute("tutorial.health_regenerate", 0, -100, 10000));
    public static RegistryEntry<EntityAttribute> mana_Attribute = register("mana", new ClampedEntityAttribute("tutorial.mana", 0, 0, 10000));
    public static RegistryEntry<EntityAttribute> mana_regenerate = register("mana_regenerate", new ClampedEntityAttribute("tutorial.mana_regenerate", 0, 0, 10000));
    public static RegistryEntry<EntityAttribute> max_health = register("max_health", new ClampedEntityAttribute(TemplateMod.NAMESPACE + ".max_health", 20, 1, 10000));
    public static RegistryEntry<EntityAttribute> max_mana = register("max_mana", new ClampedEntityAttribute(TemplateMod.NAMESPACE + ".max_mana", 20, 1, 10000));

    public static RegistryEntry<EntityAttribute> strength = register("strength", new ClampedEntityAttribute(TemplateMod.NAMESPACE + "strength", 0, 0, 10000));
    public static RegistryEntry<EntityAttribute> intelligence = register("intelligence", new ClampedEntityAttribute(TemplateMod.NAMESPACE + "intelligence", 0, 0, 10000));
    public static RegistryEntry<EntityAttribute> constitution = register("constitution", new ClampedEntityAttribute(TemplateMod.NAMESPACE + "constitution", 0, 0, 10000));
    public static RegistryEntry<EntityAttribute> agility = register("agility", new ClampedEntityAttribute(TemplateMod.NAMESPACE + "agility", 0, 0, 10000));
    public static RegistryEntry<EntityAttribute> spirit = register("spirit", new ClampedEntityAttribute(TemplateMod.NAMESPACE + "spirit", 0, 0, 10000));
    public static RegistryEntry<EntityAttribute> leadership = register("leadership", new ClampedEntityAttribute(TemplateMod.NAMESPACE + "leadership", 0, 0, 10000));

    public static RegistryEntry<EntityAttribute> basePhysicalDamage = register("base_physical_damage", new ClampedEntityAttribute(TemplateMod.NAMESPACE + "base_physical_damage", 0, 0, 10000));
    public static RegistryEntry<EntityAttribute> basePhysicalDefense = register("base_physical_defense", new ClampedEntityAttribute(TemplateMod.NAMESPACE + "base_physical_defense", 0, 0, 10000));
    public static RegistryEntry<EntityAttribute> baseMagicDamage = register("base_magic_damage", new ClampedEntityAttribute(TemplateMod.NAMESPACE + "base_magic_damage", 0, 0, 10000));
    public static RegistryEntry<EntityAttribute> maxEnergyShield = register("max_energy_shield", new ClampedEntityAttribute(TemplateMod.NAMESPACE + "max_energy_shield", 0, 0, 10000));

    public static RegistryEntry<EntityAttribute> evasion = register("evasion", new ClampedEntityAttribute(TemplateMod.NAMESPACE + "evasion", 0, 0, 10000));
    public static RegistryEntry<EntityAttribute> accuracy = register("accuracy", new ClampedEntityAttribute(TemplateMod.NAMESPACE + "accuracy", 0, 0, 10000));
    public static RegistryEntry<EntityAttribute> movementSpeed = register("movement_speed", new ClampedEntityAttribute(TemplateMod.NAMESPACE + "movement_speed", 0, 0, 10000));
    public static RegistryEntry<EntityAttribute> block = register("block", new ClampedEntityAttribute(TemplateMod.NAMESPACE + "block", 0, 0, 10000));
    public static RegistryEntry<EntityAttribute> blockRecovery = register("block_recovery", new ClampedEntityAttribute(TemplateMod.NAMESPACE + "block_recovery", 0, 0, 10000));
    public static RegistryEntry<EntityAttribute> perfectBlock = register("perfect_block", new ClampedEntityAttribute(TemplateMod.NAMESPACE + "perfect_block", 0, 0, 10000));
    public static RegistryEntry<EntityAttribute> criticalRate = register("critical_rate", new ClampedEntityAttribute(TemplateMod.NAMESPACE + "critical_rate", 0, 0, 10000));
    public static RegistryEntry<EntityAttribute> maxCriticalMultiplier = register("max_critical_multiplier", new ClampedEntityAttribute(TemplateMod.NAMESPACE + "max_critical_multiplier", 0, 0, 10000));


    public static RegistryEntry<EntityAttribute> resistance = register("resistance", new ClampedEntityAttribute(TemplateMod.NAMESPACE + "resistance", 0, 0, 10000));
    public static RegistryEntry<EntityAttribute> physicalResistance = register("physical_resistance", new ClampedEntityAttribute(TemplateMod.NAMESPACE + "physical_resistance", 0, 0, 10000));
    public static RegistryEntry<EntityAttribute> fireResistance = register("fire_resistance", new ClampedEntityAttribute(TemplateMod.NAMESPACE + "fire_resistance", 0, 0, 10000));
    public static RegistryEntry<EntityAttribute> coldResistance = register("cold_resistance", new ClampedEntityAttribute(TemplateMod.NAMESPACE + "cold_resistance", 0, 0, 10000));
    public static RegistryEntry<EntityAttribute> lightningResistance = register("lightning_resistance", new ClampedEntityAttribute(TemplateMod.NAMESPACE + "lightning_resistance", 0, 0, 10000));

    static RegistryEntry<EntityAttribute> register(String path, ClampedEntityAttribute clampedEntityAttribute) {
        var atr = Registry.registerReference(Registries.ATTRIBUTE, Identifier.of(TemplateMod.NAMESPACE, path), clampedEntityAttribute);
        Entry_List.add(atr);
        return atr;
    }
}
