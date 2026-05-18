package com.deadcity.nightfall.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CombatVestItem extends Item {
    public CombatVestItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient && entity instanceof PlayerEntity player && player.age % 20 == 0) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 35, 0, false, false));
        }
    }
}
