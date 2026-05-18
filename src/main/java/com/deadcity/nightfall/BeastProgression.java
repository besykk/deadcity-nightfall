package com.deadcity.nightfall;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class BeastProgression {
    private static final int MAX_BEAST = 100;
    private static final Map<UUID, Integer> BEAST_BY_PLAYER = new ConcurrentHashMap<>();

    private BeastProgression() {
    }

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                if (player.age % 80 == 0 && get(player) > 0 && !hasBeastBuff(player)) {
                    set(player, get(player) - 1);
                }
                if (get(player) >= MAX_BEAST && player.age % 60 == 0) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 100, 0, false, false));
                }
            }
        });
    }

    public static int get(PlayerEntity player) {
        if (player == null) {
            return 0;
        }
        return Math.max(0, Math.min(MAX_BEAST, BEAST_BY_PLAYER.getOrDefault(player.getUuid(), 0)));
    }

    public static void set(PlayerEntity player, int value) {
        if (player == null) {
            return;
        }
        BEAST_BY_PLAYER.put(player.getUuid(), Math.max(0, Math.min(MAX_BEAST, value)));
    }

    public static void addKill(PlayerEntity player, int value) {
        int before = get(player);
        int after = Math.min(MAX_BEAST, before + Math.max(1, value));
        set(player, after);
        if (!player.getWorld().isClient && before < MAX_BEAST && after >= MAX_BEAST) {
            player.sendMessage(Text.translatable("message.deadcity.beast_ready").formatted(Formatting.GOLD), true);
            player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_WARDEN_HEARTBEAT, SoundCategory.PLAYERS, 0.9F, 1.15F);
        }
    }

    public static boolean consume(PlayerEntity player, int value) {
        if (get(player) < value) {
            return false;
        }
        set(player, get(player) - value);
        return true;
    }

    public static void triggerBeastMode(PlayerEntity player, int seconds) {
        if (player == null || player.getWorld().isClient) {
            return;
        }
        int duration = 20 * seconds;
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, duration, 1, false, true));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, duration, 1, false, true));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, duration, 0, false, true));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, duration + 60, 0, false, false));
        player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_WARDEN_HEARTBEAT, SoundCategory.PLAYERS, 1.0F, 0.85F);
    }

    private static boolean hasBeastBuff(PlayerEntity player) {
        return player.hasStatusEffect(StatusEffects.STRENGTH) && player.hasStatusEffect(StatusEffects.SPEED);
    }
}
