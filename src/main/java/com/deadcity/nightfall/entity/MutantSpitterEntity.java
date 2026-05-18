package com.deadcity.nightfall.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class MutantSpitterEntity extends BaseInfectedEntity {
    public MutantSpitterEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 22;
    }

    public static DefaultAttributeContainer.Builder createInfectedAttributes() {
        return ZombieEntity.createZombieAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 28.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 36.0D);
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity target = getTarget();
        if (!getWorld().isClient && age % 75 == 0 && target != null && squaredDistanceTo(target) < 196.0D) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 90, 0));
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 45, 1));
            getWorld().playSound(null, getBlockPos(), SoundEvents.ENTITY_SLIME_ATTACK, SoundCategory.HOSTILE, 1.0F, 0.7F);
        }
    }

    @Override
    protected float getUvDamage() {
        return 1.5F;
    }
}
