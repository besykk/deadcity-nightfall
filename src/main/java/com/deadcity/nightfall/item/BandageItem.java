package com.deadcity.nightfall.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BandageItem extends Item {
    public BandageItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (user.getHealth() >= user.getMaxHealth()) {
            return TypedActionResult.fail(stack);
        }
        user.getItemCooldownManager().set(this, 50);
        if (!world.isClient) {
            user.heal(3.0F);
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 60, 0));
            world.playSound(null, user.getBlockPos(), SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.PLAYERS, 0.8F, 1.0F);
            stack.decrement(1);
        }
        return TypedActionResult.success(stack, world.isClient());
    }
}
