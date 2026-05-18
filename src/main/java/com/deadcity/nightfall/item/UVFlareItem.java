package com.deadcity.nightfall.item;

import com.deadcity.nightfall.entity.BaseInfectedEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class UVFlareItem extends Item {
    private static final double RADIUS = 8.0D;

    public UVFlareItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        user.getItemCooldownManager().set(this, 60);

        if (!world.isClient && world instanceof ServerWorld serverWorld) {
            Box box = user.getBoundingBox().expand(RADIUS);
            List<BaseInfectedEntity> infected = serverWorld.getEntitiesByClass(
                    BaseInfectedEntity.class,
                    box,
                    entity -> entity.isAlive() && entity.distanceTo(user) <= RADIUS
            );

            for (BaseInfectedEntity target : infected) {
                target.applyUv(serverWorld, user, 2.2F);
            }

            serverWorld.spawnParticles(ParticleTypes.END_ROD, user.getX(), user.getY() + 1.0D, user.getZ(), 100, RADIUS / 2.0D, 1.2D, RADIUS / 2.0D, 0.02D);
            serverWorld.spawnParticles(ParticleTypes.FLASH, user.getX(), user.getY() + 1.0D, user.getZ(), 2, 0.2D, 0.2D, 0.2D, 0.01D);
            world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_BEACON_POWER_SELECT, SoundCategory.PLAYERS, 1.0F, 1.6F);
            stack.decrement(1);
        }

        return TypedActionResult.success(stack, world.isClient());
    }
}
