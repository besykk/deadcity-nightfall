package com.deadcity.nightfall.item;

import com.deadcity.nightfall.entity.ScoutBuggyEntity;
import com.deadcity.nightfall.registry.DeadCityEntities;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BuggyDeployKitItem extends Item {
    public BuggyDeployKitItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        HitResult hit = user.raycast(6.0D, 0.0F, false);
        if (hit.getType() != HitResult.Type.BLOCK) {
            return TypedActionResult.fail(stack);
        }
        if (!world.isClient && world instanceof ServerWorld serverWorld) {
            BlockHitResult blockHit = (BlockHitResult) hit;
            BlockPos pos = blockHit.getBlockPos().offset(blockHit.getSide());
            ScoutBuggyEntity buggy = DeadCityEntities.SCOUT_BUGGY.create(serverWorld);
            if (buggy != null) {
                buggy.refreshPositionAndAngles(pos.getX() + 0.5D, pos.getY() + 0.05D, pos.getZ() + 0.5D, user.getYaw(), 0.0F);
                serverWorld.spawnEntity(buggy);
                world.playSound(null, pos, SoundEvents.ENTITY_IRON_GOLEM_REPAIR, SoundCategory.PLAYERS, 1.0F, 1.2F);
                if (!user.getAbilities().creativeMode) {
                    stack.decrement(1);
                }
            }
        }
        return TypedActionResult.success(stack, world.isClient);
    }
}
