package com.deadcity.nightfall.item;

import com.deadcity.nightfall.entity.BaseInfectedEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class ElementalMeleeItem extends SwordItem {
    private final String element;

    public ElementalMeleeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings, String element) {
        super(material, attackDamage, attackSpeed, settings);
        this.element = element;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient) {
            if ("fire".equals(element)) {
                target.setOnFireFor(4);
            } else if ("electric".equals(element)) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 80, 2));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 80, 1));
            } else if ("uv".equals(element)) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 80, 0));
                if (target instanceof BaseInfectedEntity) {
                    target.damage(attacker.getWorld().getDamageSources().magic(), 3.0F);
                }
            } else if ("bleed".equals(element)) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 60, 0));
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
