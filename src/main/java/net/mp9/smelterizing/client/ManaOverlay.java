package net.mp9.smelterizing.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.mp9.smelterizing.BetterSmelting;
import net.mp9.smelterizing.mana.ManaAttachment;
import net.mp9.smelterizing.mana.ManaData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.attachment.AttachmentType;

@EventBusSubscriber(modid = BetterSmelting.MOD_ID, value = Dist.CLIENT)
public class ManaOverlay {

    private static final ResourceLocation MANA_BAR =
            ResourceLocation.fromNamespaceAndPath(BetterSmelting.MOD_ID, "textures/gui/mana_bar.png");

    private static final ResourceLocation CHAT_LAYER =
            ResourceLocation.withDefaultNamespace("chat");

    @SubscribeEvent
    public static void registerGuiLayers(RegisterGuiLayersEvent event) {
        event.registerBelow(
                CHAT_LAYER,
                ResourceLocation.fromNamespaceAndPath(BetterSmelting.MOD_ID, "mana_bar"),
                (guiGraphics, partialTick) -> {

                    Minecraft mc = Minecraft.getInstance();
                    if (mc.player == null) return;

                    AttachmentType<ManaData> manaType = ManaAttachment.MANA.get();
                    ManaData mana = mc.player.getData(manaType); // returns ManaData

                    if (mana == null) return;

                    // Read values from ManaData
                    int current = mana.currentMana();
                    int max = mana.maxMana();

                    // Debug print to see client-side values
                    System.out.println("GUI sees mana: " + current + "/" + max);

                    int texW = 16;
                    int texH = 64;
                    float scale = 2.0f;

                    int x = 10;
                    int y = mc.getWindow().getGuiScaledHeight() - (int)(texH * scale) - 10;

                    RenderSystem.enableBlend();
                    guiGraphics.pose().pushPose();
                    guiGraphics.pose().scale(scale, scale, 1.0f);

                    int sx = (int)(x / scale);
                    int sy = (int)(y / scale);

                    // Draw background
                    guiGraphics.blit(MANA_BAR, sx, sy, 0, 0, texW, texH, texW, texH);

                    // Draw filled portion
                    float pct = (float) current / Math.max(1, max);
                    int filled = (int)(texH * pct);
                    guiGraphics.blit(MANA_BAR, sx, sy + (texH - filled), 0, texH - filled, texW, filled, texW, texH);

                    // Draw text
                    String text = current + " / " + max;
                    guiGraphics.drawString(mc.font, text, sx + texW + 4, sy + texH - 10, 0x00FFFF, false);

                    guiGraphics.pose().popPose();
                }
        );
    }
}
