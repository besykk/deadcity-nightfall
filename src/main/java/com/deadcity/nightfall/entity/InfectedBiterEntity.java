package com.deadcity.nightfall.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

public class InfectedBiterEntity extends BaseInfectedEntity {
    public InfectedBiterEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 6;
    }

    public static DefaultAttributeContainer.Builder createInfectedAttributes() {
        return ZombieEntity.createZombieAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 28.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.21D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0D)
                .add(EntityAttributes.GENERIC_ARMOR, 3.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0D);
    }

    @Override
    protected int getNightStrengthAmplifier() {
        return 1;
    }

    @Override
    protected float getUvDamage() {
        return 0.85F;
    }

    @Override
    protected double getUvKnockback() {
        return 0.08D;
    }
}
