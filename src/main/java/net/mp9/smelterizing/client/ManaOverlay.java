package net.mp9.smelterizing.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.mp9.smelterizing.BetterSmelting;
import net.mp9.smelterizing.mana.ManaAttachment;
import net.mp9.smelterizing.mana.ManaData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;

@EventBusSubscriber(modid = BetterSmelting.MOD_ID, value = Dist.CLIENT)
public class ManaOverlay {

    private static final ResourceLocation MANA_BAR =
            ResourceLocation.fromNamespaceAndPath(
                    BetterSmelting.MOD_ID,
                    "textures/gui/mana_bar.png"
            );

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

                    ManaData mana = mc.player.getData(ManaAttachment.MANA.get());

                    int current = mana.currentMana();
                    int max     = mana.maxMana();

                    int texW = 16;
                    int texH = 64;
                    float scale = 2.0f;

                    int scaledW = (int)(texW * scale);
                    int scaledH = (int)(texH * scale);

                    int x = 10;
                    int y = mc.getWindow().getGuiScaledHeight() - scaledH - 10;

                    RenderSystem.enableBlend();

                    // Scale pose
                    guiGraphics.pose().pushPose();
                    guiGraphics.pose().scale(scale, scale, 1.0f);

                    int sx = (int)(x / scale);
                    int sy = (int)(y / scale);

                    // Draw the bar background
                    guiGraphics.blit(
                            MANA_BAR,
                            sx, sy,
                            0, 0,
                            texW, texH,
                            texW, texH
                    );

                    float pct = (float)current / Math.max(1, max);
                    int filled = (int)(texH * pct);

                    // Draw filled portion
                    guiGraphics.blit(
                            MANA_BAR,
                            sx,
                            sy + (texH - filled),
                            0,
                            texH - filled,
                            texW,
                            filled,
                            texW,
                            texH
                    );

                    // Draw text
                    String text = current + " / " + max;
                    guiGraphics.drawString(
                            mc.font,
                            text,
                            sx + texW + 4,
                            sy + texH - 10,
                            0x00FFFF
                    );

                    guiGraphics.pose().popPose();
                }
        );
    }
}
