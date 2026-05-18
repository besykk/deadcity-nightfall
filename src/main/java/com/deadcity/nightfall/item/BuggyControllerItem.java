package com.deadcity.nightfall.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BuggyControllerItem extends Item {
    private static final String FUEL_KEY = "BuggyFuel";
    private static final String ENGINE_KEY = "BuggyEngine";
    public static final int MAX_FUEL = 2400;

    public BuggyControllerItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (world.isClient) {
            return TypedActionResult.success(stack);
        }

        if (getFuel(stack) <= 0) {
            setEngine(stack, false);
            user.sendMessage(Text.translatable("message.deadcity.buggy_no_fuel"), true);
            world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.PLAYERS, 0.7F, 0.5F);
            return TypedActionResult.fail(stack);
        }

        boolean next = !isEngineOn(stack);
        setEngine(stack, next);
        user.sendMessage(Text.translatable(next ? "message.deadcity.buggy_engine_on" : "message.deadcity.buggy_engine_off"), true);
        world.playSound(null, user.getBlockPos(), next ? SoundEvents.BLOCK_PISTON_EXTEND : SoundEvents.BLOCK_PISTON_CONTRACT, SoundCategory.PLAYERS, 0.8F, next ? 1.2F : 0.8F);
        return TypedActionResult.consume(stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world.isClient || !(entity instanceof PlayerEntity player) || !selected) {
            return;
        }
        if (!isEngineOn(stack)) {
            return;
        }
        int fuel = getFuel(stack);
        if (fuel <= 0) {
            setEngine(stack, false);
            player.sendMessage(Text.translatable("message.deadcity.buggy_no_fuel"), true);
            return;
        }

        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 12, player.isSprinting() ? 2 : 1, false, false));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 12, 0, false, false));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 12, 0, false, false));

        int drainRate = player.isSprinting() ? 4 : 10;
        if (player.age % drainRate == 0) {
            setFuel(stack, fuel - 1);
        }
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return getFuel(stack) < MAX_FUEL;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return Math.round(13.0F * getFuel(stack) / MAX_FUEL);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return MathHelper.hsvToRgb(0.33F, 0.9F, 1.0F);
    }

    public static boolean refuel(ItemStack stack, int amount) {
        if (!(stack.getItem() instanceof BuggyControllerItem)) {
            return false;
        }
        int before = getFuel(stack);
        if (before >= MAX_FUEL) {
            return false;
        }
        setFuel(stack, before + amount);
        return true;
    }

    public static int getFuelPercent(ItemStack stack) {
        return Math.round(100.0F * getFuel(stack) / MAX_FUEL);
    }

    public static int getFuel(ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateNbt();
        if (!nbt.contains(FUEL_KEY)) {
            nbt.putInt(FUEL_KEY, MAX_FUEL);
        }
        return MathHelper.clamp(nbt.getInt(FUEL_KEY), 0, MAX_FUEL);
    }

    public static boolean isEngineOn(ItemStack stack) {
        return stack.hasNbt() && stack.getNbt().getBoolean(ENGINE_KEY);
    }

    private static void setFuel(ItemStack stack, int fuel) {
        stack.getOrCreateNbt().putInt(FUEL_KEY, MathHelper.clamp(fuel, 0, MAX_FUEL));
    }

    private static void setEngine(ItemStack stack, boolean value) {
        stack.getOrCreateNbt().putBoolean(ENGINE_KEY, value);
    }
}
