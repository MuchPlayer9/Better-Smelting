package net.mp9.smelterizing;

import net.mp9.smelterizing.block.ModBlocks;
import net.mp9.smelterizing.item.ModCreativeModsTabs;
import net.mp9.smelterizing.item.ModItems;
import net.mp9.smelterizing.mana.ManaAttachment;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

@Mod(BetterSmelting.MOD_ID)
public class BetterSmelting {
    public static final String MOD_ID = "improvedsmelting";
    private static final Logger LOGGER = LogUtils.getLogger();

    public BetterSmelting(IEventBus modBus) {
        modBus.addListener(this::commonSetup);
        modBus.addListener(this::addCreative);

        ModCreativeModsTabs.register(modBus);
        ModItems.register(modBus);
        ModBlocks.register(modBus);

        ManaAttachment.ATTACHMENT_TYPES.register(modBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {}

    private void addCreative(BuildCreativeModeTabContentsEvent event) {}
}
