package com.deadcity.nightfall.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ParkourHarnessItem extends Item {
    public ParkourHarnessItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world.isClient || !(entity instanceof PlayerEntity player)) {
            return;
        }
        if (player.age % 10 == 0) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 24, player.isSprinting() ? 1 : 0, false, false));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 24, 0, false, false));
        }
        if (player.fallDistance > 4.0F) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 35, 0, false, false));
        }
    }
}
