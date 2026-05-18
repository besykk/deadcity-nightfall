package com.deadcity.nightfall.entity;

import com.deadcity.nightfall.registry.DeadCityItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;

public class ScoutBuggyEntity extends BoatEntity {
    private static final int MAX_FUEL = 7200;
    private static final int MAX_DURABILITY = 100;
    private int fuel = MAX_FUEL / 2;
    private int durability = MAX_DURABILITY;

    public ScoutBuggyEntity(EntityType<? extends BoatEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.isOf(DeadCityItems.FUEL_CAN)) {
            if (!getWorld().isClient) {
                fuel = Math.min(MAX_FUEL, fuel + 1800);
                if (!player.getAbilities().creativeMode) {
                    stack.decrement(1);
                }
                getWorld().playSound(null, getBlockPos(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.NEUTRAL, 1.0F, 0.85F);
            }
            return ActionResult.success(getWorld().isClient);
        }
        if (stack.isOf(DeadCityItems.BUGGY_REPAIR_KIT)) {
            if (!getWorld().isClient) {
                durability = Math.min(MAX_DURABILITY, durability + 35);
                if (!player.getAbilities().creativeMode) {
                    stack.decrement(1);
                }
                getWorld().playSound(null, getBlockPos(), SoundEvents.ENTITY_IRON_GOLEM_REPAIR, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            }
            return ActionResult.success(getWorld().isClient);
        }
        return super.interact(player, hand);
    }

    @Override
    public void tick() {
        super.tick();
        if (getWorld().isClient) {
            return;
        }
        if (hasPassengers()) {
            if (fuel > 0 && age % 8 == 0 && getVelocity().horizontalLengthSquared() > 0.0004D) {
                fuel--;
            }
            if (fuel <= 0) {
                setVelocity(getVelocity().multiply(0.85D, 1.0D, 0.85D));
            }
            if (age % 20 == 0 && fuel > 0) {
                uvHeadlights();
            }
        }
        if (durability <= 0) {
            discard();
        }
    }

    private void uvHeadlights() {
        List<BaseInfectedEntity> infected = getWorld().getEntitiesByClass(BaseInfectedEntity.class, getBoundingBox().expand(8.0D), BaseInfectedEntity::isAlive);
        for (BaseInfectedEntity mob : infected) {
            mob.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 40, 0, false, false));
            mob.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 50, 2, false, true));
            mob.damage(getWorld().getDamageSources().magic(), 1.5F);
        }
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("DeadCityFuel", fuel);
        nbt.putInt("DeadCityDurability", durability);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        fuel = nbt.contains("DeadCityFuel") ? nbt.getInt("DeadCityFuel") : MAX_FUEL / 2;
        durability = nbt.contains("DeadCityDurability") ? nbt.getInt("DeadCityDurability") : MAX_DURABILITY;
    }
}
