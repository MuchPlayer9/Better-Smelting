package net.mp9.smelterizing.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.mp9.smelterizing.BetterSmelting;
import net.mp9.smelterizing.block.ModBlocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModsTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BetterSmelting.MOD_ID);

    public static final Supplier<CreativeModeTab> BETTER_SMELTING_BLOCKS_TAB = CREATIVE_MODE_TAB.register("better_smelting_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.AUSTRALIA_MAP.get()))
                    .title(Component.translatable("creativetab.bettersmelting.bettersmelting_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.CHEESE_ORE);
                        output.accept(ModBlocks.AUSTRALIA_MAP);

                    }).build());

    public static final Supplier<CreativeModeTab> BETTER_SMELTING_ITEMS_TAB = CREATIVE_MODE_TAB.register("better_smelting_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RUBIX_CUBE.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(BetterSmelting.MOD_ID, "better_smelting_blocks_tab"))
                    .title(Component.translatable("creativetab.bettersmelting.bettersmelting_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RUBIX_CUBE);
                        output.accept(ModItems.CALCULATOR);

                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
