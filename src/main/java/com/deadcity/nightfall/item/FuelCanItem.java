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

public class FuelCanItem extends Item {
    private static final int FUEL_AMOUNT = 900;

    public FuelCanItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack fuelCan = user.getStackInHand(hand);
        for (int i = 0; i < user.getInventory().size(); i++) {
            ItemStack stack = user.getInventory().getStack(i);
            if (stack.isOf(DeadCityItems.BUGGY_CONTROLLER) && BuggyControllerItem.refuel(stack, FUEL_AMOUNT)) {
                if (!world.isClient) {
                    fuelCan.decrement(1);
                    world.playSound(null, user.getBlockPos(), SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.PLAYERS, 0.8F, 0.9F);
                    user.sendMessage(Text.translatable("message.deadcity.buggy_refueled"), true);
                }
                return TypedActionResult.success(fuelCan, world.isClient());
            }
        }
        if (!world.isClient) {
            user.sendMessage(Text.translatable("message.deadcity.no_buggy_controller"), true);
        }
        return TypedActionResult.fail(fuelCan);
    }
}
