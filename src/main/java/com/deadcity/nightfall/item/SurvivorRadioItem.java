package com.deadcity.nightfall.item;

import com.deadcity.nightfall.registry.DeadCityItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SurvivorRadioItem extends Item {
    public SurvivorRadioItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            if (consume(user, DeadCityItems.SCRAP, 8) && consume(user, DeadCityItems.INFECTED_TISSUE, 2)) {
                give(user, new ItemStack(DeadCityItems.MEDKIT, 1));
                give(user, new ItemStack(DeadCityItems.AMMO_9MM, 12));
                user.sendMessage(Text.translatable("message.deadcity.quest_complete"), false);
                world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_VILLAGER_YES, SoundCategory.PLAYERS, 1.0F, 1.0F);
            } else {
                user.sendMessage(Text.translatable("message.deadcity.quest_hint"), false);
                world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_NOTE_BLOCK_BELL.value(), SoundCategory.PLAYERS, 0.8F, 0.8F);
            }
            user.getItemCooldownManager().set(this, 80);
        }
        return TypedActionResult.success(stack, world.isClient);
    }

    private boolean consume(PlayerEntity player, Item item, int amount) {
        if (player.getAbilities().creativeMode) {
            return true;
        }
        int found = 0;
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if (stack.isOf(item)) {
                found += stack.getCount();
            }
        }
        if (found < amount) {
            return false;
        }
        int left = amount;
        for (int i = 0; i < player.getInventory().size() && left > 0; i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if (stack.isOf(item)) {
                int take = Math.min(left, stack.getCount());
                stack.decrement(take);
                left -= take;
            }
        }
        return true;
    }

    private void give(PlayerEntity player, ItemStack stack) {
        if (!player.getInventory().insertStack(stack)) {
            player.dropItem(stack, false);
        }
    }
}
