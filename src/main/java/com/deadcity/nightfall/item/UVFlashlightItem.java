package com.deadcity.nightfall.item;

import com.deadcity.nightfall.entity.BaseInfectedEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class UVFlashlightItem extends Item {
    private static final String BATTERY_KEY = "UvBattery";
    public static final int MAX_BATTERY = 1800;
    private static final int PASSIVE_RECHARGE_PER_SECOND = 2;
    private static final double BEAM_RANGE = 15.0D;
    private static final double BEAM_DOT = 0.82D;

    public UVFlashlightItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (getBattery(stack) <= 0) {
            return TypedActionResult.fail(stack);
        }

        user.setCurrentHand(hand);
        if (!world.isClient) {
            world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS, 0.25F, 1.75F);
        }
        return TypedActionResult.consume(stack);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (world.isClient || !(user instanceof PlayerEntity player)) {
            return;
        }

        int battery = getBattery(stack);
        if (battery <= 0) {
            player.stopUsingItem();
            return;
        }

        if (player.age % 2 == 0) {
            setBattery(stack, battery - 1);
        }

        if (world instanceof ServerWorld serverWorld) {
            applyUvBeam(serverWorld, player, 1.0F);
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world.isClient || !(entity instanceof PlayerEntity player)) {
            return;
        }

        boolean active = player.isUsingItem() && player.getActiveItem().isOf(this);
        if (!active && player.age % 20 == 0) {
            setBattery(stack, getBattery(stack) + PASSIVE_RECHARGE_PER_SECOND);
        }
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return getBattery(stack) < MAX_BATTERY;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return Math.round(13.0F * getBattery(stack) / MAX_BATTERY);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return MathHelper.hsvToRgb(0.52F, 1.0F, 1.0F);
    }

    public static int getBatteryPercent(ItemStack stack) {
        return Math.round(100.0F * getBattery(stack) / MAX_BATTERY);
    }

    public static int getBattery(ItemStack stack) {
        if (!stack.hasNbt() || !stack.getNbt().contains(BATTERY_KEY)) {
            return MAX_BATTERY;
        }
        return MathHelper.clamp(stack.getNbt().getInt(BATTERY_KEY), 0, MAX_BATTERY);
    }

    public static boolean recharge(ItemStack stack, int amount) {
        if (!(stack.getItem() instanceof UVFlashlightItem)) {
            return false;
        }
        int before = getBattery(stack);
        if (before >= MAX_BATTERY) {
            return false;
        }
        setBattery(stack, before + amount);
        return true;
    }

    private static void setBattery(ItemStack stack, int battery) {
        stack.getOrCreateNbt().putInt(BATTERY_KEY, MathHelper.clamp(battery, 0, MAX_BATTERY));
    }

    private static void applyUvBeam(ServerWorld world, PlayerEntity player, float intensity) {
        Vec3d start = player.getEyePos();
        Vec3d look = player.getRotationVec(1.0F).normalize();
        Box searchBox = player.getBoundingBox().stretch(look.multiply(BEAM_RANGE)).expand(3.2D);
        List<BaseInfectedEntity> targets = world.getEntitiesByClass(
                BaseInfectedEntity.class,
                searchBox,
                entity -> entity.isAlive() && !entity.isSpectator()
        );

        for (BaseInfectedEntity infected : targets) {
            Vec3d targetCenter = infected.getBoundingBox().getCenter();
            Vec3d toTarget = targetCenter.subtract(start);
            double distance = toTarget.length();

            if (distance > BEAM_RANGE || distance <= 0.001D) {
                continue;
            }

            double dot = look.dotProduct(toTarget.normalize());
            if (dot < BEAM_DOT) {
                continue;
            }

            infected.applyUv(world, player, intensity);

            if (world.getTime() % 16L == 0L) {
                world.playSound(null, infected.getBlockPos(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.HOSTILE, 0.15F, 1.8F);
            }
        }
    }
}
