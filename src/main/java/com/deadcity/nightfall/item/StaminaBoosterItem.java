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

public class StaminaBoosterItem extends Item {
    public StaminaBoosterItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        user.getItemCooldownManager().set(this, 160);
        if (!world.isClient) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20 * 25, 0));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 20 * 25, 0));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 20 * 25, 0));
            world.playSound(null, user.getBlockPos(), SoundEvents.ITEM_HONEY_BOTTLE_DRINK, SoundCategory.PLAYERS, 0.75F, 1.3F);
            stack.decrement(1);
        }
        return TypedActionResult.success(stack, world.isClient());
    }
}
