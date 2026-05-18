package com.deadcity.nightfall.client;

import com.deadcity.nightfall.BeastProgression;
import com.deadcity.nightfall.item.BuggyControllerItem;
import com.deadcity.nightfall.item.FirearmItem;
import com.deadcity.nightfall.item.UVFlashlightItem;
import com.deadcity.nightfall.registry.DeadCityItems;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class DeadCityHud {
    public static void register() {
        HudRenderCallback.EVENT.register(DeadCityHud::render);
    }

    private static void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) {
            return;
        }

        int y = client.getWindow().getScaledHeight() - 54;
        ItemStack main = client.player.getMainHandStack();
        ItemStack off = client.player.getOffHandStack();

        ItemStack flashlight = main.isOf(DeadCityItems.UV_FLASHLIGHT) ? main : off;
        if (flashlight.isOf(DeadCityItems.UV_FLASHLIGHT)) {
            int percent = UVFlashlightItem.getBatteryPercent(flashlight);
            Text text = Text.translatable("hud.deadcity.uv_battery", percent);
            context.drawTextWithShadow(client.textRenderer, text, 8, y, 0x63F7FF);
            drawBar(context, 8, y + 10, 80, percent, 0x5500D5FF, 0xFF63F7FF);
            y -= 20;
        }

        if (main.getItem() instanceof FirearmItem firearm) {
            Text ammo = Text.translatable("hud.deadcity.ammo", FirearmItem.getAmmo(main), firearm.getMaxAmmo());
            context.drawTextWithShadow(client.textRenderer, ammo, 8, y, 0xFFD36A);
            y -= 12;
        }

        if (main.isDamageable()) {
            int durability = main.getMaxDamage() - main.getDamage();
            int max = main.getMaxDamage();
            Text durabilityText = Text.translatable("hud.deadcity.durability", durability, max);
            context.drawTextWithShadow(client.textRenderer, durabilityText, 8, y, 0xCFCFCF);
            y -= 12;
        }

        ItemStack buggy = main.isOf(DeadCityItems.BUGGY_CONTROLLER) ? main : off;
        if (buggy.isOf(DeadCityItems.BUGGY_CONTROLLER)) {
            int percent = BuggyControllerItem.getFuelPercent(buggy);
            Text fuel = Text.translatable(BuggyControllerItem.isEngineOn(buggy) ? "hud.deadcity.buggy_on" : "hud.deadcity.buggy_off", percent);
            context.drawTextWithShadow(client.textRenderer, fuel, 8, y, 0x7CFF6B);
            y -= 12;
        }

        if (main.isOf(DeadCityItems.GRAPPLING_HOOK)) {
            context.drawTextWithShadow(client.textRenderer, Text.translatable("hud.deadcity.grapple"), 8, y, 0xA7D8FF);
            y -= 12;
        }

        renderWeaponWheelHint(context, client);
        renderNightAndBeastBar(context, client);
    }

    private static void renderNightAndBeastBar(DrawContext context, MinecraftClient client) {
        boolean night = client.world.isNight();
        int width = client.getWindow().getScaledWidth();
        int x = width / 2 - 88;
        int y = 12;
        long time = client.world.getTimeOfDay() % 24000L;
        int danger = night ? (int) Math.min(100, Math.max(15, (time - 13000L) / 70L)) : 0;
        Text title = night ? Text.translatable("hud.deadcity.nightfall_release").formatted(Formatting.RED) : Text.translatable("hud.deadcity.day_safe").formatted(Formatting.GRAY);
        context.drawTextWithShadow(client.textRenderer, title, x, y, night ? 0xFF4444 : 0xAAAAAA);
        drawBar(context, x, y + 12, 176, danger, 0x55290000, 0xFFFF2E3D);

        int beast = BeastProgression.get(client.player);
        int beastColor = beast >= 100 ? 0xFFFF4040 : 0xFFFFB24D;
        context.drawTextWithShadow(client.textRenderer, beast >= 100 ? Text.translatable("hud.deadcity.beast_ready_bar") : Text.translatable("hud.deadcity.beast_bar"), x, y + 24, beastColor);
        drawBar(context, x, y + 36, 176, beast, 0x55301400, beast >= 100 ? 0xFFFF2E3D : 0xFFFF9A1F);
    }

    private static void renderWeaponWheelHint(DrawContext context, MinecraftClient client) {
        int width = client.getWindow().getScaledWidth();
        int y = client.getWindow().getScaledHeight() - 18;
        int selected = client.player.getInventory().selectedSlot;
        ItemStack selectedStack = client.player.getInventory().getStack(selected);
        Text text = Text.translatable("hud.deadcity.weapon_slot", selected + 1, selectedStack.isEmpty() ? Text.translatable("hud.deadcity.empty") : selectedStack.getName());
        int x = (width - client.textRenderer.getWidth(text)) / 2;
        context.drawTextWithShadow(client.textRenderer, text, x, y, 0xE8E8E8);
    }

    private static void drawBar(DrawContext context, int x, int y, int width, int percent, int bg, int fg) {
        context.fill(x, y, x + width, y + 5, bg);
        context.fill(x, y, x + Math.max(0, Math.min(width, width * percent / 100)), y + 5, fg);
    }
}
