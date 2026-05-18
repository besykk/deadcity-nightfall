package com.deadcity.nightfall.entity;

import com.deadcity.nightfall.BeastProgression;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BaseInfectedEntity extends ZombieEntity {
    public BaseInfectedEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 8;
    }

    public static DefaultAttributeContainer.Builder createBaseInfectedAttributes() {
        return ZombieEntity.createZombieAttributes();
    }

    protected int getNightSpeedAmplifier() {
        return 0;
    }

    protected int getNightStrengthAmplifier() {
        return 0;
    }

    protected float getUvDamage() {
        return 1.0F;
    }

    protected double getUvKnockback() {
        return 0.12D;
    }

    public void applyUv(ServerWorld world, PlayerEntity source, float intensity) {
        int duration = 35;
        this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, duration, 2, false, true));
        this.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, duration, 1, false, true));
        this.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 25, 0, false, false));
        this.setTarget(null);

        if (world.getTime() % 8L == 0L) {
            this.damage(world.getDamageSources().magic(), Math.max(0.5F, getUvDamage() * intensity));
        }

        Vec3d away = this.getPos().subtract(source.getPos());
        if (away.lengthSquared() > 0.001D) {
            Vec3d push = away.normalize().multiply(getUvKnockback() * intensity);
            this.addVelocity(push.x, 0.035D * intensity, push.z);
            this.velocityModified = true;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.getWorld().isClient && this.age % 40 == 0) {
            if (this.getWorld().isNight()) {
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 70, getNightSpeedAmplifier(), false, false));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 70, getNightStrengthAmplifier(), false, false));
            } else {
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 80, 0, false, false));
            }
        }
    }

    @Override
    public boolean tryAttack(net.minecraft.entity.Entity target) {
        boolean attacked = super.tryAttack(target);
        if (attacked && target instanceof LivingEntity living && this.getWorld().isNight()) {
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 120, 0));
        }
        return attacked;
    }


    protected int getBeastKillValue() {
        return 6;
    }

    protected int getBeastFinisherValue() {
        return 14;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (!this.getWorld().isClient && source.getAttacker() instanceof PlayerEntity player && shouldTriggerFinisher(player, amount)) {
            BeastProgression.addKill(player, getBeastFinisherValue());
            if (BeastProgression.get(player) >= 100) {
                BeastProgression.consume(player, 35);
                BeastProgression.triggerBeastMode(player, 18);
            }
            player.sendMessage(Text.translatable("message.deadcity.finisher").formatted(Formatting.RED), true);
            this.getWorld().playSound(null, this.getBlockPos(), SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, SoundCategory.PLAYERS, 1.0F, 0.75F);
            return super.damage(source, Math.max(amount, this.getMaxHealth() + 30.0F));
        }
        return super.damage(source, amount);
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
        if (!this.getWorld().isClient && damageSource.getAttacker() instanceof PlayerEntity player) {
            BeastProgression.addKill(player, getBeastKillValue());
        }
    }

    private boolean shouldTriggerFinisher(PlayerEntity player, float amount) {
        ItemStack stack = player.getMainHandStack();
        boolean melee = stack.getItem() instanceof SwordItem || stack.getItem() instanceof AxeItem;
        if (!melee || this.isDead() || this.getHealth() <= 0.0F) {
            return false;
        }
        boolean lowHealth = this.getHealth() <= Math.max(7.0F, this.getMaxHealth() * 0.28F);
        boolean heavyState = player.isSneaking() || player.isSprinting() || player.fallDistance > 1.2F;
        boolean beastReady = BeastProgression.get(player) >= 100 && this.getHealth() <= this.getMaxHealth() * 0.55F;
        return (lowHealth && heavyState) || beastReady || amount >= this.getHealth() + 2.0F;
    }

    @Override
    protected boolean burnsInDaylight() {
        return false;
    }
}
