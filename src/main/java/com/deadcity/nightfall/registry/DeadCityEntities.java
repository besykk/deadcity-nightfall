package com.deadcity.nightfall.registry;

import com.deadcity.nightfall.DeadCityNightfall;
import com.deadcity.nightfall.entity.AlphaVolatileEntity;
import com.deadcity.nightfall.entity.ArmoredVolatileEntity;
import com.deadcity.nightfall.entity.CivilianEntity;
import com.deadcity.nightfall.entity.DemolisherTitanEntity;
import com.deadcity.nightfall.entity.InfectedBiterEntity;
import com.deadcity.nightfall.entity.InfectedRunnerEntity;
import com.deadcity.nightfall.entity.MutantSpitterEntity;
import com.deadcity.nightfall.entity.NightVolatileEntity;
import com.deadcity.nightfall.entity.RaiderBruteEntity;
import com.deadcity.nightfall.entity.RaiderSoldierEntity;
import com.deadcity.nightfall.entity.ScoutBuggyEntity;
import com.deadcity.nightfall.entity.ScreamerEntity;
import com.deadcity.nightfall.entity.TraderSurvivorEntity;
import com.deadcity.nightfall.entity.VolatileStalkerEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class DeadCityEntities {
    public static final EntityType<InfectedRunnerEntity> INFECTED_RUNNER = Registry.register(
            Registries.ENTITY_TYPE,
            DeadCityNightfall.id("infected_runner"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, InfectedRunnerEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6F, 1.95F)).trackRangeBlocks(10).build()
    );

    public static final EntityType<InfectedBiterEntity> INFECTED_BITER = Registry.register(
            Registries.ENTITY_TYPE,
            DeadCityNightfall.id("infected_biter"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, InfectedBiterEntity::new)
                    .dimensions(EntityDimensions.fixed(0.65F, 1.95F)).trackRangeBlocks(10).build()
    );

    public static final EntityType<NightVolatileEntity> NIGHT_VOLATILE = Registry.register(
            Registries.ENTITY_TYPE,
            DeadCityNightfall.id("night_volatile"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, NightVolatileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.75F, 2.15F)).trackRangeBlocks(12).build()
    );

    public static final EntityType<VolatileStalkerEntity> VOLATILE_STALKER = Registry.register(
            Registries.ENTITY_TYPE,
            DeadCityNightfall.id("volatile_stalker"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, VolatileStalkerEntity::new)
                    .dimensions(EntityDimensions.fixed(0.72F, 2.1F)).trackRangeBlocks(14).build()
    );

    public static final EntityType<ArmoredVolatileEntity> ARMORED_VOLATILE = Registry.register(
            Registries.ENTITY_TYPE,
            DeadCityNightfall.id("armored_volatile"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ArmoredVolatileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.85F, 2.25F)).trackRangeBlocks(14).build()
    );

    public static final EntityType<ScreamerEntity> SCREAMER = Registry.register(
            Registries.ENTITY_TYPE,
            DeadCityNightfall.id("screamer"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ScreamerEntity::new)
                    .dimensions(EntityDimensions.fixed(0.65F, 1.95F)).trackRangeBlocks(12).build()
    );

    public static final EntityType<MutantSpitterEntity> MUTANT_SPITTER = Registry.register(
            Registries.ENTITY_TYPE,
            DeadCityNightfall.id("mutant_spitter"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, MutantSpitterEntity::new)
                    .dimensions(EntityDimensions.fixed(0.7F, 1.95F)).trackRangeBlocks(12).build()
    );

    public static final EntityType<DemolisherTitanEntity> DEMOLISHER_TITAN = Registry.register(
            Registries.ENTITY_TYPE,
            DeadCityNightfall.id("demolisher_titan"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, DemolisherTitanEntity::new)
                    .dimensions(EntityDimensions.fixed(1.35F, 2.75F)).trackRangeBlocks(14).build()
    );

    public static final EntityType<AlphaVolatileEntity> ALPHA_VOLATILE = Registry.register(
            Registries.ENTITY_TYPE,
            DeadCityNightfall.id("alpha_volatile"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, AlphaVolatileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.95F, 2.45F)).trackRangeBlocks(16).build()
    );

    public static final EntityType<RaiderSoldierEntity> RAIDER_SOLDIER = Registry.register(
            Registries.ENTITY_TYPE,
            DeadCityNightfall.id("raider_soldier"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, RaiderSoldierEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6F, 1.95F)).trackRangeBlocks(12).build()
    );

    public static final EntityType<RaiderBruteEntity> RAIDER_BRUTE = Registry.register(
            Registries.ENTITY_TYPE,
            DeadCityNightfall.id("raider_brute"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, RaiderBruteEntity::new)
                    .dimensions(EntityDimensions.fixed(0.78F, 2.15F)).trackRangeBlocks(12).build()
    );

    public static final EntityType<CivilianEntity> CIVILIAN = Registry.register(
            Registries.ENTITY_TYPE,
            DeadCityNightfall.id("civilian"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CivilianEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6F, 1.85F)).trackRangeBlocks(10).build()
    );

    public static final EntityType<TraderSurvivorEntity> TRADER_SURVIVOR = Registry.register(
            Registries.ENTITY_TYPE,
            DeadCityNightfall.id("trader_survivor"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, TraderSurvivorEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6F, 1.85F)).trackRangeBlocks(10).build()
    );

    public static final EntityType<ScoutBuggyEntity> SCOUT_BUGGY = Registry.register(
            Registries.ENTITY_TYPE,
            DeadCityNightfall.id("scout_buggy"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, ScoutBuggyEntity::new)
                    .dimensions(EntityDimensions.fixed(1.65F, 0.95F)).trackRangeBlocks(12).build()
    );

    public static void register() {
        FabricDefaultAttributeRegistry.register(INFECTED_RUNNER, InfectedRunnerEntity.createInfectedAttributes());
        FabricDefaultAttributeRegistry.register(INFECTED_BITER, InfectedBiterEntity.createInfectedAttributes());
        FabricDefaultAttributeRegistry.register(NIGHT_VOLATILE, NightVolatileEntity.createInfectedAttributes());
        FabricDefaultAttributeRegistry.register(VOLATILE_STALKER, VolatileStalkerEntity.createInfectedAttributes());
        FabricDefaultAttributeRegistry.register(ARMORED_VOLATILE, ArmoredVolatileEntity.createInfectedAttributes());
        FabricDefaultAttributeRegistry.register(SCREAMER, ScreamerEntity.createInfectedAttributes());
        FabricDefaultAttributeRegistry.register(MUTANT_SPITTER, MutantSpitterEntity.createInfectedAttributes());
        FabricDefaultAttributeRegistry.register(DEMOLISHER_TITAN, DemolisherTitanEntity.createInfectedAttributes());
        FabricDefaultAttributeRegistry.register(ALPHA_VOLATILE, AlphaVolatileEntity.createInfectedAttributes());
        FabricDefaultAttributeRegistry.register(RAIDER_SOLDIER, RaiderSoldierEntity.createRaiderAttributes());
        FabricDefaultAttributeRegistry.register(RAIDER_BRUTE, RaiderBruteEntity.createRaiderAttributes());
        FabricDefaultAttributeRegistry.register(CIVILIAN, CivilianEntity.createCivilianAttributes());
        FabricDefaultAttributeRegistry.register(TRADER_SURVIVOR, CivilianEntity.createCivilianAttributes());
    }
}
