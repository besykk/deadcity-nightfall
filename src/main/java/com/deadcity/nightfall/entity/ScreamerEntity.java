package com.deadcity.nightfall.entity;

import com.deadcity.nightfall.registry.DeadCityEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class ScreamerEntity extends BaseInfectedEntity {
    public ScreamerEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 18;
    }

    public static DefaultAttributeContainer.Builder createInfectedAttributes() {
        return ZombieEntity.createZombieAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 22.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.29D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48.0D);
    }

    @Override
    public void tick() {
        super.tick();
        if (!getWorld().isClient && age % 140 == 0) {
            PlayerEntity target = getWorld().getClosestPlayer(this, 18.0D);
            if (target != null) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100, 0));
                getWorld().playSound(null, getBlockPos(), SoundEvents.ENTITY_GHAST_SCREAM, SoundCategory.HOSTILE, 1.6F, 1.45F);
                if (getWorld() instanceof ServerWorld serverWorld && getWorld().isNight()) {
                    InfectedRunnerEntity runner = DeadCityEntities.INFECTED_RUNNER.create(serverWorld);
                    if (runner != null) {
                        runner.refreshPositionAndAngles(getX() + 2.0D, getY(), getZ() + 2.0D, getYaw(), getPitch());
                        serverWorld.spawnEntity(runner);
                    }
                }
            }
        }
    }

    @Override
    protected float getUvDamage() {
        return 1.8F;
    }
}
