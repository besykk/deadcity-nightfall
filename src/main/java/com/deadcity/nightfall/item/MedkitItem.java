package com.deadcity.nightfall.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class MedkitItem extends Item {
    public MedkitItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (user.getHealth() >= user.getMaxHealth()) {
            return TypedActionResult.fail(stack);
        }

        user.getItemCooldownManager().set(this, 80);
        if (!world.isClient) {
            user.heal(8.0F);
            world.playSound(null, user.getBlockPos(), SoundEvents.ITEM_HONEY_BOTTLE_DRINK, SoundCategory.PLAYERS, 0.7F, 1.0F);
            stack.decrement(1);
        }
        return TypedActionResult.success(stack, world.isClient());
    }
}
