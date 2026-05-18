package com.deadcity.nightfall.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

public class VolatileStalkerEntity extends NightVolatileEntity {
    public VolatileStalkerEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 34;
    }

    public static DefaultAttributeContainer.Builder createInfectedAttributes() {
        return ZombieEntity.createZombieAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 78.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.42D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0D)
                .add(EntityAttributes.GENERIC_ARMOR, 4.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 62.0D);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.getWorld().isClient && this.getWorld().isNight() && this.age % 45 == 0) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 65, 2, false, false));
        }
    }

    @Override
    protected int getNightSpeedAmplifier() {
        return 2;
    }

    @Override
    protected int getNightStrengthAmplifier() {
        return 1;
    }

    @Override
    protected float getUvDamage() {
        return 2.0F;
    }

    @Override
    protected int getBeastKillValue() {
        return 13;
    }
}
