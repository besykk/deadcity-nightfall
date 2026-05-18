package com.deadcity.nightfall.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

public class NightVolatileEntity extends BaseInfectedEntity {
    public NightVolatileEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 22;
    }

    public static DefaultAttributeContainer.Builder createInfectedAttributes() {
        return ZombieEntity.createZombieAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 68.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.36D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0D)
                .add(EntityAttributes.GENERIC_ARMOR, 6.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 54.0D);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.getWorld().isClient && this.getWorld().isNight() && this.age % 60 == 0) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 80, 0, false, false));
        }
    }

    @Override
    protected int getNightSpeedAmplifier() {
        return 1;
    }

    @Override
    protected int getNightStrengthAmplifier() {
        return 1;
    }

    @Override
    protected float getUvDamage() {
        return 1.75F;
    }

    @Override
    protected int getBeastKillValue() {
        return 10;
    }

    @Override
    protected double getUvKnockback() {
        return 0.16D;
    }
}
