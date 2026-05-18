package com.deadcity.nightfall.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class GrapplingHookItem extends Item {
    private static final double RANGE = 30.0D;
    private static final double POWER = 1.65D;

    public GrapplingHookItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        HitResult hit = user.raycast(RANGE, 0.0F, false);

        if (hit.getType() != HitResult.Type.BLOCK) {
            if (!world.isClient) {
                user.sendMessage(Text.translatable("message.deadcity.grapple_miss"), true);
                world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_CHAIN_BREAK, SoundCategory.PLAYERS, 0.7F, 0.8F);
            }
            return TypedActionResult.fail(stack);
        }

        if (!world.isClient) {
            BlockHitResult blockHit = (BlockHitResult) hit;
            Vec3d target = blockHit.getPos().add(0.0D, 0.7D, 0.0D);
            Vec3d pull = target.subtract(user.getPos());
            if (pull.lengthSquared() > 1.0D) {
                Vec3d velocity = pull.normalize().multiply(POWER);
                user.addVelocity(velocity.x, Math.max(0.22D, velocity.y + 0.18D), velocity.z);
                user.velocityModified = true;
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 45, 0, false, false));
                user.getItemCooldownManager().set(this, 45);
                stack.damage(1, user, p -> p.sendToolBreakStatus(hand));
                world.playSound(null, user.getBlockPos(), SoundEvents.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYERS, 0.9F, 1.35F);
            }
        }

        return TypedActionResult.success(stack, world.isClient);
    }
}
