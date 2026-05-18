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

public class BatteryCellItem extends Item {
    private static final int RECHARGE_AMOUNT = 900;

    public BatteryCellItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack battery = user.getStackInHand(hand);

        for (int i = 0; i < user.getInventory().size(); i++) {
            ItemStack stack = user.getInventory().getStack(i);
            if (stack.isOf(DeadCityItems.UV_FLASHLIGHT) && UVFlashlightItem.recharge(stack, RECHARGE_AMOUNT)) {
                if (!world.isClient) {
                    battery.decrement(1);
                    world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.PLAYERS, 0.8F, 1.4F);
                    user.sendMessage(Text.translatable("message.deadcity.flashlight_recharged"), true);
                }
                return TypedActionResult.success(battery, world.isClient());
            }
        }

        if (!world.isClient) {
            user.sendMessage(Text.translatable("message.deadcity.no_flashlight_to_recharge"), true);
        }
        return TypedActionResult.fail(battery);
    }
}
