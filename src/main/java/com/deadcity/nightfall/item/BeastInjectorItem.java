package com.deadcity.nightfall.item;

import com.deadcity.nightfall.BeastProgression;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BeastInjectorItem extends Item {
    public BeastInjectorItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            BeastProgression.set(user, 100);
            BeastProgression.triggerBeastMode(user, 55);
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 20 * 55, 1));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20 * 55, 1));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 20 * 40, 0));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 20 * 80, 0));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 20 * 20, 0));
            user.getItemCooldownManager().set(this, 20 * 70);
            world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_WARDEN_HEARTBEAT, SoundCategory.PLAYERS, 0.85F, 1.35F);
            user.sendMessage(Text.translatable("message.deadcity.beast_injector"), true);
            if (!user.getAbilities().creativeMode) {
                stack.decrement(1);
            }
        }
        return TypedActionResult.success(stack, world.isClient);
    }
}
