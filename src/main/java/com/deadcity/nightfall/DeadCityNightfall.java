package com.deadcity.nightfall;

import com.deadcity.nightfall.registry.DeadCityEntities;
import com.deadcity.nightfall.registry.DeadCityItems;
import com.deadcity.nightfall.registry.DeadCitySpawns;
import com.deadcity.nightfall.BeastProgression;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class DeadCityNightfall implements ModInitializer {
    public static final String MOD_ID = "deadcity";

    @Override
    public void onInitialize() {
        DeadCityEntities.register();
        DeadCityItems.register();
        DeadCitySpawns.register();
        BeastProgression.register();
    }

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }
}
