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
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class SafeZoneBeaconItem extends Item {
    public SafeZoneBeaconItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient && world instanceof ServerWorld serverWorld) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 260, 0, false, false));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 260, 0, false, false));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 420, 0, false, false));
            List<BaseInfectedEntity> infected = world.getEntitiesByClass(BaseInfectedEntity.class, user.getBoundingBox().expand(15.0D), BaseInfectedEntity::isAlive);
            for (BaseInfectedEntity mob : infected) {
                mob.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 180, 5, false, true));
                mob.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 180, 3, false, true));
                mob.damage(serverWorld.getDamageSources().magic(), 10.0F);
            }
            world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_BEACON_POWER_SELECT, SoundCategory.PLAYERS, 1.0F, 1.3F);
            user.sendMessage(Text.translatable("message.deadcity.safezone_active"), true);
            user.getItemCooldownManager().set(this, 1200);
            stack.damage(1, user, p -> p.sendToolBreakStatus(hand));
        }
        return TypedActionResult.success(stack, world.isClient);
    }
}
