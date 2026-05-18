package com.deadcity.nightfall.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class GliderRigItem extends Item {
    public GliderRigItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world.isClient || !(entity instanceof PlayerEntity player)) {
            return;
        }
        if (player.fallDistance > 2.0F && player.isSneaking()) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 30, 0, false, false));
            Vec3d v = player.getVelocity();
            player.setVelocity(v.x * 1.04D, Math.max(v.y, -0.18D), v.z * 1.04D);
            player.velocityModified = true;
            if (player.age % 30 == 0) {
                stack.damage(1, player, p -> p.sendToolBreakStatus(player.getActiveHand()));
            }
        }
    }
}
