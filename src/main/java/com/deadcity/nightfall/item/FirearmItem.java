package com.deadcity.nightfall.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class FirearmItem extends Item {
    private static final String AMMO_KEY = "Ammo";

    private final Item ammoItem;
    private final int maxAmmo;
    private final double damage;
    private final float velocity;
    private final int cooldownTicks;
    private final int projectiles;
    private final float divergence;

    public FirearmItem(Settings settings, Item ammoItem, int maxAmmo, double damage, float velocity, int cooldownTicks, int projectiles, float divergence) {
        super(settings);
        this.ammoItem = ammoItem;
        this.maxAmmo = maxAmmo;
        this.damage = damage;
        this.velocity = velocity;
        this.cooldownTicks = cooldownTicks;
        this.projectiles = projectiles;
        this.divergence = divergence;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (user.isSneaking()) {
            return reload(world, user, stack);
        }

        if (world.isClient) {
            return TypedActionResult.success(stack);
        }

        int ammo = getAmmo(stack);
        if (ammo <= 0 && !user.getAbilities().creativeMode) {
            user.sendMessage(Text.translatable("message.deadcity.gun_empty"), true);
            world.playSound(null, user.getBlockPos(), SoundEvents.UI_BUTTON_CLICK.value(), SoundCategory.PLAYERS, 0.6F, 0.6F);
            return TypedActionResult.fail(stack);
        }

        shoot((ServerWorld) world, user, stack);
        user.getItemCooldownManager().set(this, cooldownTicks);

        if (!user.getAbilities().creativeMode) {
            setAmmo(stack, ammo - 1);
        }

        return TypedActionResult.consume(stack);
    }

    private TypedActionResult<ItemStack> reload(World world, PlayerEntity user, ItemStack gun) {
        if (world.isClient) {
            return TypedActionResult.success(gun);
        }

        int current = getAmmo(gun);
        if (current >= maxAmmo) {
            user.sendMessage(Text.translatable("message.deadcity.gun_full"), true);
            return TypedActionResult.fail(gun);
        }

        int need = maxAmmo - current;
        int loaded = 0;

        if (user.getAbilities().creativeMode) {
            loaded = need;
        } else {
            for (int i = 0; i < user.getInventory().size() && loaded < need; i++) {
                ItemStack invStack = user.getInventory().getStack(i);
                if (!invStack.isOf(ammoItem)) {
                    continue;
                }
                int take = Math.min(need - loaded, invStack.getCount());
                invStack.decrement(take);
                loaded += take;
            }
        }

        if (loaded <= 0) {
            user.sendMessage(Text.translatable("message.deadcity.no_ammo"), true);
            return TypedActionResult.fail(gun);
        }

        setAmmo(gun, current + loaded);
        world.playSound(null, user.getBlockPos(), SoundEvents.ITEM_CROSSBOW_LOADING_END, SoundCategory.PLAYERS, 0.9F, 1.1F);
        user.sendMessage(Text.translatable("message.deadcity.reloaded", getAmmo(gun), maxAmmo), true);
        return TypedActionResult.success(gun);
    }

    private void shoot(ServerWorld world, LivingEntity shooter, ItemStack gun) {
        for (int i = 0; i < projectiles; i++) {
            ArrowEntity arrow = new ArrowEntity(world, shooter);
            arrow.setDamage(damage);
            arrow.setCritical(true);
            float yawOffset = projectiles == 1 ? 0.0F : (world.getRandom().nextFloat() - 0.5F) * divergence * 2.0F;
            float pitchOffset = projectiles == 1 ? 0.0F : (world.getRandom().nextFloat() - 0.5F) * divergence;
            arrow.setVelocity(shooter, shooter.getPitch() + pitchOffset, shooter.getYaw() + yawOffset, 0.0F, velocity, divergence);
            world.spawnEntity(arrow);
        }

        world.playSound(null, shooter.getBlockPos(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 0.45F, 1.65F + world.getRandom().nextFloat() * 0.15F);
        gun.damage(1, shooter, entity -> entity.sendToolBreakStatus(shooter.getActiveHand()));
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return getAmmo(stack) < maxAmmo || stack.isDamaged();
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return Math.round(13.0F * getAmmo(stack) / maxAmmo);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return MathHelper.hsvToRgb(0.12F, 0.85F, 1.0F);
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public static int getAmmo(ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateNbt();
        if (!nbt.contains(AMMO_KEY)) {
            nbt.putInt(AMMO_KEY, 0);
        }
        return Math.max(0, nbt.getInt(AMMO_KEY));
    }

    private void setAmmo(ItemStack stack, int ammo) {
        stack.getOrCreateNbt().putInt(AMMO_KEY, MathHelper.clamp(ammo, 0, maxAmmo));
    }
}
