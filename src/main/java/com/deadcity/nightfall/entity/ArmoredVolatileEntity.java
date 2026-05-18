package com.deadcity.nightfall.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

public class ArmoredVolatileEntity extends NightVolatileEntity {
    public ArmoredVolatileEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 48;
    }

    public static DefaultAttributeContainer.Builder createInfectedAttributes() {
        return ZombieEntity.createZombieAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 115.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.32D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 11.0D)
                .add(EntityAttributes.GENERIC_ARMOR, 9.0D)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.55D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 58.0D);
    }

    @Override
    public boolean tryAttack(Entity target) {
        boolean result = super.tryAttack(target);
        if (result && target instanceof LivingEntity living) {
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 70, 0));
        }
        return result;
    }

    @Override
    protected int getNightSpeedAmplifier() {
        return 1;
    }

    @Override
    protected int getNightStrengthAmplifier() {
        return 2;
    }

    @Override
    protected float getUvDamage() {
        return 1.15F;
    }

    @Override
    protected double getUvKnockback() {
        return 0.08D;
    }

    @Override
    protected int getBeastKillValue() {
        return 18;
    }
}
