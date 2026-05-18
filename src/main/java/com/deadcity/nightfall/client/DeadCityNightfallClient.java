package com.deadcity.nightfall.client;

import com.deadcity.nightfall.DeadCityNightfall;
import com.deadcity.nightfall.registry.DeadCityEntities;
import com.deadcity.nightfall.registry.DeadCityItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;

public class DeadCityNightfallClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerZombieLikeRenderers();
        EntityRendererRegistry.register(DeadCityEntities.SCOUT_BUGGY, context -> new BoatEntityRenderer(context, false));

        ModelPredicateProviderRegistry.register(
                DeadCityItems.UV_FLASHLIGHT,
                DeadCityNightfall.id("active"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem().isOf(DeadCityItems.UV_FLASHLIGHT) ? 1.0F : 0.0F
        );

        DeadCityHud.register();
    }

    private void registerZombieLikeRenderers() {
        EntityRendererRegistry.register(DeadCityEntities.INFECTED_RUNNER, ctx -> texturedZombie(ctx, "infected_runner"));
        EntityRendererRegistry.register(DeadCityEntities.INFECTED_BITER, ctx -> texturedZombie(ctx, "infected_biter"));
        EntityRendererRegistry.register(DeadCityEntities.NIGHT_VOLATILE, ctx -> texturedZombie(ctx, "night_volatile"));
        EntityRendererRegistry.register(DeadCityEntities.VOLATILE_STALKER, ctx -> texturedZombie(ctx, "volatile_stalker"));
        EntityRendererRegistry.register(DeadCityEntities.ARMORED_VOLATILE, ctx -> texturedZombie(ctx, "armored_volatile"));
        EntityRendererRegistry.register(DeadCityEntities.SCREAMER, ctx -> texturedZombie(ctx, "screamer"));
        EntityRendererRegistry.register(DeadCityEntities.MUTANT_SPITTER, ctx -> texturedZombie(ctx, "mutant_spitter"));
        EntityRendererRegistry.register(DeadCityEntities.DEMOLISHER_TITAN, ctx -> texturedZombie(ctx, "demolisher_titan"));
        EntityRendererRegistry.register(DeadCityEntities.ALPHA_VOLATILE, ctx -> texturedZombie(ctx, "alpha_volatile"));
        EntityRendererRegistry.register(DeadCityEntities.RAIDER_SOLDIER, ctx -> texturedZombie(ctx, "raider_soldier"));
        EntityRendererRegistry.register(DeadCityEntities.RAIDER_BRUTE, ctx -> texturedZombie(ctx, "raider_brute"));
        EntityRendererRegistry.register(DeadCityEntities.CIVILIAN, ctx -> texturedZombie(ctx, "civilian"));
        EntityRendererRegistry.register(DeadCityEntities.TRADER_SURVIVOR, ctx -> texturedZombie(ctx, "trader_survivor"));
    }

    private ZombieEntityRenderer texturedZombie(net.minecraft.client.render.entity.EntityRendererFactory.Context context, String texture) {
        return new ZombieEntityRenderer(context) {
            private final Identifier id = DeadCityNightfall.id("textures/entity/" + texture + ".png");

            @Override
            public Identifier getTexture(ZombieEntity zombieEntity) {
                return id;
            }
        };
    }
}
