package com.deadcity.nightfall.registry;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.SpawnReason;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class DeadCitySpawns {
    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                ServerWorld world = player.getServerWorld();
                if (!world.isNight() || world.getTime() % 200L != 0L) {
                    continue;
                }
                Random random = world.getRandom();
                if (random.nextFloat() > 0.28F) {
                    continue;
                }
                BlockPos pos = player.getBlockPos().add(random.nextBetween(-34, 34), 0, random.nextBetween(-34, 34));
                pos = world.getTopPosition(net.minecraft.world.Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos);
                if (player.squaredDistanceTo(pos.getX(), pos.getY(), pos.getZ()) < 81.0D) {
                    continue;
                }

                double sectorDistance = Math.sqrt(player.getX() * player.getX() + player.getZ() * player.getZ());
                int sector = sectorDistance > 1800.0D ? 3 : sectorDistance > 850.0D ? 2 : 1;
                int roll = random.nextBetween(0, 99);

                if (roll < 36) {
                    DeadCityEntities.INFECTED_RUNNER.spawn(world, pos, SpawnReason.EVENT);
                } else if (roll < 60) {
                    DeadCityEntities.INFECTED_BITER.spawn(world, pos, SpawnReason.EVENT);
                } else if (roll < 72) {
                    DeadCityEntities.SCREAMER.spawn(world, pos, SpawnReason.EVENT);
                } else if (roll < 84) {
                    DeadCityEntities.MUTANT_SPITTER.spawn(world, pos, SpawnReason.EVENT);
                } else if (roll < 92) {
                    spawnVolatileBySector(world, pos, sector);
                    notifySector(player, sector);
                } else if (world.getTimeOfDay() % 24000L > 15500L) {
                    spawnHardNightPack(world, pos, sector, random);
                    notifySector(player, sector);
                }
            }
        });
    }

    private static void spawnVolatileBySector(ServerWorld world, BlockPos pos, int sector) {
        if (sector <= 1) {
            DeadCityEntities.NIGHT_VOLATILE.spawn(world, pos, SpawnReason.EVENT);
        } else if (sector == 2) {
            DeadCityEntities.VOLATILE_STALKER.spawn(world, pos, SpawnReason.EVENT);
        } else {
            DeadCityEntities.ARMORED_VOLATILE.spawn(world, pos, SpawnReason.EVENT);
        }
    }

    private static void spawnHardNightPack(ServerWorld world, BlockPos pos, int sector, Random random) {
        spawnVolatileBySector(world, pos, sector);
        if (sector >= 2) {
            DeadCityEntities.NIGHT_VOLATILE.spawn(world, pos.add(random.nextBetween(-3, 3), 0, random.nextBetween(-3, 3)), SpawnReason.EVENT);
        }
        if (sector >= 3) {
            DeadCityEntities.ALPHA_VOLATILE.spawn(world, pos.add(random.nextBetween(-4, 4), 0, random.nextBetween(-4, 4)), SpawnReason.EVENT);
        }
    }

    private static void notifySector(ServerPlayerEntity player, int sector) {
        if (player.age % 600 != 0) {
            return;
        }
        if (sector == 2) {
            player.sendMessage(Text.translatable("message.deadcity.sector_volatile_stalker"), true);
        } else if (sector >= 3) {
            player.sendMessage(Text.translatable("message.deadcity.sector_armored_volatile"), true);
        }
    }
}
