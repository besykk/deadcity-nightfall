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

public class DemolisherTitanEntity extends BaseInfectedEntity {
    public DemolisherTitanEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 45;
    }

    public static DefaultAttributeContainer.Builder createInfectedAttributes() {
        return ZombieEntity.createZombieAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 95.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.20D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 12.0D)
                .add(EntityAttributes.GENERIC_ARMOR, 10.0D)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.75D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40.0D);
    }

    @Override
    public boolean tryAttack(Entity target) {
        boolean result = super.tryAttack(target);
        if (result && target instanceof LivingEntity living) {
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 1));
            living.takeKnockback(1.6D, getX() - living.getX(), getZ() - living.getZ());
        }
        return result;
    }

    @Override
    protected int getNightStrengthAmplifier() {
        return 1;
    }

    @Override
    protected float getUvDamage() {
        return 0.65F;
    }
}
