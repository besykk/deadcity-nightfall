package com.deadcity.nightfall.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class TraderSurvivorEntity extends CivilianEntity {
    public TraderSurvivorEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (!getWorld().isClient) {
            player.sendMessage(Text.translatable("message.deadcity.trader_hint"), false);
        }
        return ActionResult.success(getWorld().isClient);
    }
}
