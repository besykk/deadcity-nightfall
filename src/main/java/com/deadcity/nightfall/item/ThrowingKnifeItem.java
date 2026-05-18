package com.deadcity.nightfall.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ThrowingKnifeItem extends Item {
    public ThrowingKnifeItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        user.getItemCooldownManager().set(this, 25);
        if (!world.isClient && world instanceof ServerWorld serverWorld) {
            ArrowEntity arrow = new ArrowEntity(serverWorld, user);
            arrow.setDamage(5.0D);
            arrow.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 2.2F, 0.6F);
            serverWorld.spawnEntity(arrow);
            world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 0.8F, 1.2F);
            if (!user.getAbilities().creativeMode) {
                stack.decrement(1);
            }
        }
        return TypedActionResult.success(stack, world.isClient());
    }
}
