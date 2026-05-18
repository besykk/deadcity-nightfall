package com.deadcity.nightfall.registry;

import com.deadcity.nightfall.DeadCityNightfall;
import com.deadcity.nightfall.item.BandageItem;
import com.deadcity.nightfall.item.BatteryCellItem;
import com.deadcity.nightfall.item.BeastInjectorItem;
import com.deadcity.nightfall.item.BuggyControllerItem;
import com.deadcity.nightfall.item.BuggyDeployKitItem;
import com.deadcity.nightfall.item.ElementalMeleeItem;
import com.deadcity.nightfall.item.FirearmItem;
import com.deadcity.nightfall.item.FuelCanItem;
import com.deadcity.nightfall.item.GliderRigItem;
import com.deadcity.nightfall.item.CombatVestItem;
import com.deadcity.nightfall.item.GrapplingHookItem;
import com.deadcity.nightfall.item.MedkitItem;
import com.deadcity.nightfall.item.NoiseMakerItem;
import com.deadcity.nightfall.item.ParkourHarnessItem;
import com.deadcity.nightfall.item.SafeZoneBeaconItem;
import com.deadcity.nightfall.item.StaminaBoosterItem;
import com.deadcity.nightfall.item.SurvivorRadioItem;
import com.deadcity.nightfall.item.ThrowingKnifeItem;
import com.deadcity.nightfall.item.UVFlareItem;
import com.deadcity.nightfall.item.UVFlashlightItem;
import com.deadcity.nightfall.item.UVTrapItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

public class DeadCityItems {
    // UV and survival
    public static final Item UV_FLASHLIGHT = register("uv_flashlight", new UVFlashlightItem(new Item.Settings().maxCount(1)));
    public static final Item BATTERY_CELL = register("battery_cell", new BatteryCellItem(new Item.Settings().maxCount(16)));
    public static final Item UV_FLARE = register("uv_flare", new UVFlareItem(new Item.Settings().maxCount(16)));
    public static final Item UV_TRAP = register("uv_trap", new UVTrapItem(new Item.Settings().maxCount(8)));
    public static final Item SAFE_ZONE_BEACON = register("safe_zone_beacon", new SafeZoneBeaconItem(new Item.Settings().maxCount(1).maxDamage(20)));
    public static final Item MEDKIT = register("medkit", new MedkitItem(new Item.Settings().maxCount(8)));
    public static final Item BANDAGE = register("bandage", new BandageItem(new Item.Settings().maxCount(16)));
    public static final Item STAMINA_BOOSTER = register("stamina_booster", new StaminaBoosterItem(new Item.Settings().maxCount(8)));
    public static final Item PARKOUR_HARNESS = register("parkour_harness", new ParkourHarnessItem(new Item.Settings().maxCount(1).maxDamage(480)));
    public static final Item GRAPPLING_HOOK = register("grappling_hook", new GrapplingHookItem(new Item.Settings().maxCount(1).maxDamage(360)));
    public static final Item LOCKPICK = register("lockpick", new Item(new Item.Settings().maxCount(32)));
    public static final Item NOISE_MAKER = register("noise_maker", new NoiseMakerItem(new Item.Settings().maxCount(16)));
    public static final Item THROWING_KNIFE = register("throwing_knife", new ThrowingKnifeItem(new Item.Settings().maxCount(16)));
    public static final Item SURVIVOR_RADIO = register("survivor_radio", new SurvivorRadioItem(new Item.Settings().maxCount(1)));
    public static final Item BEAST_INJECTOR = register("beast_injector", new BeastInjectorItem(new Item.Settings().maxCount(4)));
    public static final Item GLIDER_RIG = register("glider_rig", new GliderRigItem(new Item.Settings().maxCount(1).maxDamage(520)));
    public static final Item COMBAT_VEST = register("combat_vest", new CombatVestItem(new Item.Settings().maxCount(1).maxDamage(650)));

    // Resources
    public static final Item SCRAP = register("scrap", new Item(new Item.Settings()));
    public static final Item METAL_PARTS = register("metal_parts", new Item(new Item.Settings()));
    public static final Item DUCT_TAPE = register("duct_tape", new Item(new Item.Settings()));
    public static final Item ELECTRONICS = register("electronics", new Item(new Item.Settings()));
    public static final Item INFECTED_TISSUE = register("infected_tissue", new Item(new Item.Settings()));
    public static final Item CLOTH = register("cloth", new Item(new Item.Settings()));
    public static final Item ALCOHOL = register("alcohol", new Item(new Item.Settings()));
    public static final Item CHEMICALS = register("chemicals", new Item(new Item.Settings()));
    public static final Item WIRING = register("wiring", new Item(new Item.Settings()));
    public static final Item BLADE_PARTS = register("blade_parts", new Item(new Item.Settings()));
    public static final Item GUNPOWDER_BLEND = register("gunpowder_blend", new Item(new Item.Settings()));
    public static final Item BLUEPRINT_FRAGMENT = register("blueprint_fragment", new Item(new Item.Settings()));
    public static final Item TITAN_BONE = register("titan_bone", new Item(new Item.Settings()));
    public static final Item MUTAGEN_SAMPLE = register("mutagen_sample", new Item(new Item.Settings()));
    public static final Item CIRCUIT_BOARD = register("circuit_board", new Item(new Item.Settings()));

    // One handed melee
    public static final Item RUSTY_PIPE = register("rusty_pipe", new SwordItem(ToolMaterials.IRON, 3, -2.0F, new Item.Settings().maxDamage(180)));
    public static final Item POLICE_BATON = register("police_baton", new SwordItem(ToolMaterials.IRON, 4, -1.9F, new Item.Settings().maxDamage(260)));
    public static final Item KITCHEN_KNIFE = register("kitchen_knife", new SwordItem(ToolMaterials.IRON, 2, -1.4F, new Item.Settings().maxDamage(120)));
    public static final Item HATCHET = register("hatchet", new SwordItem(ToolMaterials.IRON, 5, -2.4F, new Item.Settings().maxDamage(220)));
    public static final Item IMPROVISED_CLEAVER = register("improvised_cleaver", new SwordItem(ToolMaterials.IRON, 6, -2.6F, new Item.Settings().maxDamage(240)));
    public static final Item REINFORCED_MACHETE = register("reinforced_machete", new SwordItem(ToolMaterials.IRON, 5, -2.2F, new Item.Settings().maxDamage(320)));
    public static final Item NAIL_PLANK = register("nail_plank", new SwordItem(ToolMaterials.WOOD, 4, -2.4F, new Item.Settings().maxDamage(110)));
    public static final Item MILITARY_KNIFE = register("military_knife", new SwordItem(ToolMaterials.IRON, 4, -1.35F, new Item.Settings().maxDamage(260)));
    public static final Item ELECTRIFIED_BATON = register("electrified_baton", new SwordItem(ToolMaterials.DIAMOND, 5, -1.8F, new Item.Settings().maxDamage(420)));
    public static final Item UV_BLADE = register("uv_blade", new ElementalMeleeItem(ToolMaterials.DIAMOND, 6, -1.9F, new Item.Settings().maxDamage(460), "uv"));
    public static final Item ROOFTOP_WRENCH = register("rooftop_wrench", new SwordItem(ToolMaterials.IRON, 4, -2.0F, new Item.Settings().maxDamage(240)));
    public static final Item TACTICAL_TOMAHAWK = register("tactical_tomahawk", new SwordItem(ToolMaterials.DIAMOND, 6, -2.15F, new Item.Settings().maxDamage(430)));
    public static final Item SHOCK_MACHETE = register("shock_machete", new ElementalMeleeItem(ToolMaterials.DIAMOND, 6, -2.05F, new Item.Settings().maxDamage(480), "electric"));
    public static final Item FLAME_CLEAVER = register("flame_cleaver", new ElementalMeleeItem(ToolMaterials.DIAMOND, 7, -2.45F, new Item.Settings().maxDamage(460), "fire"));
    public static final Item BEAST_CLAW_KNIFE = register("beast_claw_knife", new ElementalMeleeItem(ToolMaterials.DIAMOND, 5, -1.25F, new Item.Settings().maxDamage(410), "bleed"));

    // Two handed melee
    public static final Item FIRE_AXE = register("fire_axe", new SwordItem(ToolMaterials.DIAMOND, 7, -3.1F, new Item.Settings().maxDamage(480)));
    public static final Item SLEDGEHAMMER = register("sledgehammer", new SwordItem(ToolMaterials.DIAMOND, 9, -3.5F, new Item.Settings().maxDamage(540)));
    public static final Item REBAR_HAMMER = register("rebar_hammer", new SwordItem(ToolMaterials.IRON, 8, -3.4F, new Item.Settings().maxDamage(420)));
    public static final Item HEAVY_KATANA = register("heavy_katana", new SwordItem(ToolMaterials.DIAMOND, 6, -2.1F, new Item.Settings().maxDamage(520)));
    public static final Item CONCRETE_MAUL = register("concrete_maul", new SwordItem(ToolMaterials.DIAMOND, 10, -3.65F, new Item.Settings().maxDamage(580)));
    public static final Item LONG_MACHETE = register("long_machete", new SwordItem(ToolMaterials.DIAMOND, 7, -2.35F, new Item.Settings().maxDamage(520)));
    public static final Item RAIL_SPIKE_MACE = register("rail_spike_mace", new SwordItem(ToolMaterials.IRON, 9, -3.45F, new Item.Settings().maxDamage(430)));
    public static final Item TITAN_CRUSHER = register("titan_crusher", new SwordItem(ToolMaterials.DIAMOND, 12, -3.75F, new Item.Settings().maxDamage(760)));
    public static final Item STREET_SIGN_HALBERD = register("street_sign_halberd", new SwordItem(ToolMaterials.IRON, 8, -3.0F, new Item.Settings().maxDamage(380)));
    public static final Item BEAST_BONE_AXE = register("beast_bone_axe", new ElementalMeleeItem(ToolMaterials.DIAMOND, 9, -2.9F, new Item.Settings().maxDamage(620), "bleed"));
    public static final Item UV_GREATSWORD = register("uv_greatsword", new ElementalMeleeItem(ToolMaterials.DIAMOND, 10, -3.1F, new Item.Settings().maxDamage(680), "uv"));

    // Ammo first, then firearms
    public static final Item AMMO_9MM = register("ammo_9mm", new Item(new Item.Settings().maxCount(64)));
    public static final Item SHOTGUN_SHELLS = register("shotgun_shells", new Item(new Item.Settings().maxCount(32)));
    public static final Item RIFLE_ROUNDS = register("rifle_rounds", new Item(new Item.Settings().maxCount(48)));
    public static final Item MAGNUM_ROUNDS = register("magnum_rounds", new Item(new Item.Settings().maxCount(32)));
    public static final Item ASSAULT_ROUNDS = register("assault_rounds", new Item(new Item.Settings().maxCount(64)));
    public static final Item HEAVY_ROUNDS = register("heavy_rounds", new Item(new Item.Settings().maxCount(32)));
    public static final Item BOLT_PACK = register("bolt_pack", new Item(new Item.Settings().maxCount(32)));

    public static final Item MAKESHIFT_PISTOL = register("makeshift_pistol", new FirearmItem(new Item.Settings().maxCount(1).maxDamage(180), AMMO_9MM, 6, 5.0D, 2.8F, 18, 1, 1.1F));
    public static final Item POLICE_PISTOL = register("police_pistol", new FirearmItem(new Item.Settings().maxCount(1).maxDamage(300), AMMO_9MM, 12, 6.0D, 3.2F, 12, 1, 0.75F));
    public static final Item SMG = register("smg", new FirearmItem(new Item.Settings().maxCount(1).maxDamage(420), AMMO_9MM, 24, 4.0D, 3.0F, 4, 1, 1.4F));
    public static final Item PUMP_SHOTGUN = register("pump_shotgun", new FirearmItem(new Item.Settings().maxCount(1).maxDamage(360), SHOTGUN_SHELLS, 6, 3.0D, 2.6F, 28, 7, 5.5F));
    public static final Item HUNTING_RIFLE = register("hunting_rifle", new FirearmItem(new Item.Settings().maxCount(1).maxDamage(360), RIFLE_ROUNDS, 5, 11.0D, 4.2F, 32, 1, 0.15F));
    public static final Item REVOLVER = register("revolver", new FirearmItem(new Item.Settings().maxCount(1).maxDamage(320), MAGNUM_ROUNDS, 6, 9.0D, 3.6F, 18, 1, 0.55F));
    public static final Item ASSAULT_RIFLE = register("assault_rifle", new FirearmItem(new Item.Settings().maxCount(1).maxDamage(520), ASSAULT_ROUNDS, 30, 5.5D, 3.7F, 5, 1, 0.95F));
    public static final Item MILITARY_RIFLE = register("military_rifle", new FirearmItem(new Item.Settings().maxCount(1).maxDamage(560), ASSAULT_ROUNDS, 25, 7.0D, 4.0F, 7, 1, 0.45F));
    public static final Item DOUBLE_BARREL = register("double_barrel", new FirearmItem(new Item.Settings().maxCount(1).maxDamage(260), SHOTGUN_SHELLS, 2, 4.5D, 3.0F, 34, 10, 6.5F));
    public static final Item COMPACT_SMG = register("compact_smg", new FirearmItem(new Item.Settings().maxCount(1).maxDamage(460), AMMO_9MM, 32, 3.6D, 3.1F, 3, 1, 1.65F));
    public static final Item BURST_RIFLE = register("burst_rifle", new FirearmItem(new Item.Settings().maxCount(1).maxDamage(540), ASSAULT_ROUNDS, 27, 5.2D, 3.9F, 8, 3, 0.65F));
    public static final Item MARKSMAN_RIFLE = register("marksman_rifle", new FirearmItem(new Item.Settings().maxCount(1).maxDamage(500), HEAVY_ROUNDS, 8, 13.0D, 4.5F, 34, 1, 0.08F));
    public static final Item AUTO_SHOTGUN = register("auto_shotgun", new FirearmItem(new Item.Settings().maxCount(1).maxDamage(430), SHOTGUN_SHELLS, 8, 2.8D, 3.2F, 14, 8, 5.4F));

    // Buggy / vehicle
    public static final Item BUGGY_CONTROLLER = register("buggy_controller", new BuggyControllerItem(new Item.Settings().maxCount(1)));
    public static final Item BUGGY_DEPLOY_KIT = register("buggy_deploy_kit", new BuggyDeployKitItem(new Item.Settings().maxCount(1)));
    public static final Item FUEL_CAN = register("fuel_can", new FuelCanItem(new Item.Settings().maxCount(8)));
    public static final Item BUGGY_REPAIR_KIT = register("buggy_repair_kit", new Item(new Item.Settings().maxCount(8)));
    public static final Item BUGGY_PARTS = register("buggy_parts", new Item(new Item.Settings()));
    public static final Item CAR_BATTERY = register("car_battery", new Item(new Item.Settings().maxCount(4)));
    public static final Item REINFORCED_TIRES = register("reinforced_tires", new Item(new Item.Settings().maxCount(4)));
    public static final Item ENGINE_PARTS = register("engine_parts", new Item(new Item.Settings()));
    public static final Item UV_HEADLIGHTS = register("uv_headlights", new Item(new Item.Settings().maxCount(2)));
    public static final Item TURBOCHARGER = register("turbocharger", new Item(new Item.Settings().maxCount(1)));
    public static final Item BUGGY_ARMOR_PLATES = register("buggy_armor_plates", new Item(new Item.Settings().maxCount(4)));
    public static final Item SUSPENSION_KIT = register("suspension_kit", new Item(new Item.Settings().maxCount(2)));
    public static final Item NITRO_BOTTLE = register("nitro_bottle", new Item(new Item.Settings().maxCount(4)));
    public static final Item SPIKED_BUMPER = register("spiked_bumper", new Item(new Item.Settings().maxCount(1)));
    public static final Item OFFROAD_ENGINE = register("offroad_engine", new Item(new Item.Settings().maxCount(1)));
    public static final Item BUGGY_UV_CORE = register("buggy_uv_core", new Item(new Item.Settings().maxCount(1)));

    // City / quests / progression starter items
    public static final Item SAFEHOUSE_KIT = register("safehouse_kit", new Item(new Item.Settings().maxCount(4)));
    public static final Item LOOT_CACHE = register("loot_cache", new Item(new Item.Settings().maxCount(16)));
    public static final Item QUEST_BOARD = register("quest_board", new Item(new Item.Settings().maxCount(1)));
    public static final Item FACTION_TOKEN = register("faction_token", new Item(new Item.Settings().maxCount(64)));

    // Spawn eggs
    public static final Item INFECTED_RUNNER_SPAWN_EGG = register("infected_runner_spawn_egg", new SpawnEggItem(DeadCityEntities.INFECTED_RUNNER, 0x1f1f1f, 0x63f7ff, new Item.Settings()));
    public static final Item INFECTED_BITER_SPAWN_EGG = register("infected_biter_spawn_egg", new SpawnEggItem(DeadCityEntities.INFECTED_BITER, 0x2b2b2b, 0x74804f, new Item.Settings()));
    public static final Item NIGHT_VOLATILE_SPAWN_EGG = register("night_volatile_spawn_egg", new SpawnEggItem(DeadCityEntities.NIGHT_VOLATILE, 0x111111, 0x8b1e2d, new Item.Settings()));
    public static final Item VOLATILE_STALKER_SPAWN_EGG = register("volatile_stalker_spawn_egg", new SpawnEggItem(DeadCityEntities.VOLATILE_STALKER, 0x0c121a, 0x3fa7ff, new Item.Settings()));
    public static final Item ARMORED_VOLATILE_SPAWN_EGG = register("armored_volatile_spawn_egg", new SpawnEggItem(DeadCityEntities.ARMORED_VOLATILE, 0x08080a, 0x9a9a9a, new Item.Settings()));
    public static final Item SCREAMER_SPAWN_EGG = register("screamer_spawn_egg", new SpawnEggItem(DeadCityEntities.SCREAMER, 0x292319, 0xe6cf86, new Item.Settings()));
    public static final Item MUTANT_SPITTER_SPAWN_EGG = register("mutant_spitter_spawn_egg", new SpawnEggItem(DeadCityEntities.MUTANT_SPITTER, 0x31402a, 0x93ff62, new Item.Settings()));
    public static final Item DEMOLISHER_TITAN_SPAWN_EGG = register("demolisher_titan_spawn_egg", new SpawnEggItem(DeadCityEntities.DEMOLISHER_TITAN, 0x2a2521, 0xa8492b, new Item.Settings()));
    public static final Item ALPHA_VOLATILE_SPAWN_EGG = register("alpha_volatile_spawn_egg", new SpawnEggItem(DeadCityEntities.ALPHA_VOLATILE, 0x090909, 0xff324e, new Item.Settings()));
    public static final Item RAIDER_SOLDIER_SPAWN_EGG = register("raider_soldier_spawn_egg", new SpawnEggItem(DeadCityEntities.RAIDER_SOLDIER, 0x23272c, 0xb14431, new Item.Settings()));
    public static final Item RAIDER_BRUTE_SPAWN_EGG = register("raider_brute_spawn_egg", new SpawnEggItem(DeadCityEntities.RAIDER_BRUTE, 0x171717, 0x7b2d23, new Item.Settings()));
    public static final Item CIVILIAN_SPAWN_EGG = register("civilian_spawn_egg", new SpawnEggItem(DeadCityEntities.CIVILIAN, 0x44565f, 0xd6c095, new Item.Settings()));
    public static final Item TRADER_SURVIVOR_SPAWN_EGG = register("trader_survivor_spawn_egg", new SpawnEggItem(DeadCityEntities.TRADER_SURVIVOR, 0x223d2d, 0xe0b35b, new Item.Settings()));

    public static final ItemGroup DEAD_CITY_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            DeadCityNightfall.id("dead_city"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemGroup.deadcity.dead_city"))
                    .icon(() -> new ItemStack(UV_FLASHLIGHT))
                    .entries((context, entries) -> {
                        entries.add(UV_FLASHLIGHT); entries.add(BATTERY_CELL); entries.add(UV_FLARE); entries.add(UV_TRAP); entries.add(SAFE_ZONE_BEACON);
                        entries.add(MEDKIT); entries.add(BANDAGE); entries.add(STAMINA_BOOSTER); entries.add(PARKOUR_HARNESS); entries.add(GRAPPLING_HOOK);
                        entries.add(LOCKPICK); entries.add(NOISE_MAKER); entries.add(THROWING_KNIFE); entries.add(SURVIVOR_RADIO);
                        entries.add(BEAST_INJECTOR); entries.add(GLIDER_RIG); entries.add(COMBAT_VEST);

                        entries.add(RUSTY_PIPE); entries.add(POLICE_BATON); entries.add(KITCHEN_KNIFE); entries.add(HATCHET); entries.add(IMPROVISED_CLEAVER); entries.add(REINFORCED_MACHETE);
                        entries.add(NAIL_PLANK); entries.add(MILITARY_KNIFE); entries.add(ELECTRIFIED_BATON); entries.add(UV_BLADE);
                        entries.add(ROOFTOP_WRENCH); entries.add(TACTICAL_TOMAHAWK); entries.add(SHOCK_MACHETE); entries.add(FLAME_CLEAVER); entries.add(BEAST_CLAW_KNIFE);
                        entries.add(FIRE_AXE); entries.add(SLEDGEHAMMER); entries.add(REBAR_HAMMER); entries.add(HEAVY_KATANA); entries.add(CONCRETE_MAUL); entries.add(LONG_MACHETE); entries.add(RAIL_SPIKE_MACE);
                        entries.add(TITAN_CRUSHER); entries.add(STREET_SIGN_HALBERD); entries.add(BEAST_BONE_AXE); entries.add(UV_GREATSWORD);

                        entries.add(MAKESHIFT_PISTOL); entries.add(POLICE_PISTOL); entries.add(SMG); entries.add(PUMP_SHOTGUN); entries.add(HUNTING_RIFLE);
                        entries.add(REVOLVER); entries.add(ASSAULT_RIFLE); entries.add(MILITARY_RIFLE); entries.add(DOUBLE_BARREL);
                        entries.add(COMPACT_SMG); entries.add(BURST_RIFLE); entries.add(MARKSMAN_RIFLE); entries.add(AUTO_SHOTGUN);
                        entries.add(AMMO_9MM); entries.add(SHOTGUN_SHELLS); entries.add(RIFLE_ROUNDS); entries.add(MAGNUM_ROUNDS); entries.add(ASSAULT_ROUNDS); entries.add(HEAVY_ROUNDS); entries.add(BOLT_PACK);

                        entries.add(BUGGY_CONTROLLER); entries.add(BUGGY_DEPLOY_KIT); entries.add(FUEL_CAN); entries.add(BUGGY_REPAIR_KIT); entries.add(BUGGY_PARTS);
                        entries.add(CAR_BATTERY); entries.add(REINFORCED_TIRES); entries.add(ENGINE_PARTS); entries.add(UV_HEADLIGHTS); entries.add(TURBOCHARGER);
                        entries.add(BUGGY_ARMOR_PLATES); entries.add(SUSPENSION_KIT); entries.add(NITRO_BOTTLE); entries.add(SPIKED_BUMPER); entries.add(OFFROAD_ENGINE); entries.add(BUGGY_UV_CORE);

                        entries.add(SAFEHOUSE_KIT); entries.add(LOOT_CACHE); entries.add(QUEST_BOARD); entries.add(FACTION_TOKEN);

                        entries.add(SCRAP); entries.add(METAL_PARTS); entries.add(DUCT_TAPE); entries.add(ELECTRONICS); entries.add(INFECTED_TISSUE);
                        entries.add(CLOTH); entries.add(ALCOHOL); entries.add(CHEMICALS); entries.add(WIRING); entries.add(BLADE_PARTS); entries.add(GUNPOWDER_BLEND); entries.add(BLUEPRINT_FRAGMENT);
                        entries.add(TITAN_BONE); entries.add(MUTAGEN_SAMPLE); entries.add(CIRCUIT_BOARD);

                        entries.add(INFECTED_RUNNER_SPAWN_EGG); entries.add(INFECTED_BITER_SPAWN_EGG); entries.add(NIGHT_VOLATILE_SPAWN_EGG); entries.add(VOLATILE_STALKER_SPAWN_EGG); entries.add(ARMORED_VOLATILE_SPAWN_EGG);
                        entries.add(SCREAMER_SPAWN_EGG); entries.add(MUTANT_SPITTER_SPAWN_EGG); entries.add(DEMOLISHER_TITAN_SPAWN_EGG); entries.add(ALPHA_VOLATILE_SPAWN_EGG);
                        entries.add(RAIDER_SOLDIER_SPAWN_EGG); entries.add(RAIDER_BRUTE_SPAWN_EGG); entries.add(CIVILIAN_SPAWN_EGG); entries.add(TRADER_SURVIVOR_SPAWN_EGG);
                    })
                    .build()
    );

    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, DeadCityNightfall.id(name), item);
    }

    public static void register() {
        // Static initialization registers all items and the creative tab.
    }
}
