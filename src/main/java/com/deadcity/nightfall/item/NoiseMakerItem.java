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

public class NoiseMakerItem extends Item {
    private static final double RADIUS = 28.0D;

    public NoiseMakerItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        user.getItemCooldownManager().set(this, 120);
        if (!world.isClient && world instanceof ServerWorld serverWorld) {
            List<BaseInfectedEntity> infected = serverWorld.getEntitiesByClass(
                    BaseInfectedEntity.class,
                    new Box(user.getBlockPos()).expand(RADIUS),
                    entity -> entity.isAlive()
            );
            for (BaseInfectedEntity target : infected) {
                target.setTarget(user);
            }
            serverWorld.spawnParticles(ParticleTypes.NOTE, user.getX(), user.getY() + 1.0D, user.getZ(), 24, 1.2D, 0.6D, 1.2D, 0.05D);
            world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_NOTE_BLOCK_BELL.value(), SoundCategory.PLAYERS, 1.4F, 0.7F);
            stack.decrement(1);
        }
        return TypedActionResult.success(stack, world.isClient());
    }
}
