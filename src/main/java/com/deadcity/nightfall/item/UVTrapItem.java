package com.deadcity.nightfall.item;

import com.deadcity.nightfall.entity.BaseInfectedEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class UVTrapItem extends Item {
    public UVTrapItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient && world instanceof ServerWorld serverWorld) {
            List<BaseInfectedEntity> infected = world.getEntitiesByClass(BaseInfectedEntity.class, user.getBoundingBox().expand(10.0D), BaseInfectedEntity::isAlive);
            for (BaseInfectedEntity mob : infected) {
                mob.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 140, 0, false, false));
                mob.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 140, 4, false, true));
                mob.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 140, 2, false, true));
                mob.damage(serverWorld.getDamageSources().magic(), 8.0F);
            }
            world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS, 1.0F, 1.65F);
            if (!user.getAbilities().creativeMode) {
                stack.decrement(1);
            }
            user.getItemCooldownManager().set(this, 160);
        }
        return TypedActionResult.success(stack, world.isClient);
    }
}
