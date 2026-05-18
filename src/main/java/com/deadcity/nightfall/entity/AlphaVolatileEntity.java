package com.deadcity.nightfall.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

public class AlphaVolatileEntity extends BaseInfectedEntity {
    public AlphaVolatileEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 80;
    }

    public static DefaultAttributeContainer.Builder createInfectedAttributes() {
        return ZombieEntity.createZombieAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 160.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.38D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 13.0D)
                .add(EntityAttributes.GENERIC_ARMOR, 6.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 56.0D);
    }

    @Override
    public void tick() {
        super.tick();
        if (!getWorld().isClient && getWorld().isNight() && age % 40 == 0) {
            addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 60, 0));
            addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 60, 1));
        }
    }

    @Override
    protected int getBeastKillValue() {
        return 25;
    }

    @Override
    protected int getNightSpeedAmplifier() {
        return 2;
    }

    @Override
    protected int getNightStrengthAmplifier() {
        return 2;
    }

    @Override
    protected float getUvDamage() {
        return 2.4F;
    }
}
