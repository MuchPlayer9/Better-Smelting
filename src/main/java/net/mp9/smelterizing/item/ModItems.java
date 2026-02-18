package net.mp9.smelterizing.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.mp9.smelterizing.BetterSmelting;
import net.mp9.smelterizing.item.custom.CoconutMilkItem;
import net.mp9.smelterizing.item.custom.FuelItem;
import net.mp9.smelterizing.item.custom.MidasItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BetterSmelting.MOD_ID);

    // fuel items
    public static final DeferredItem<Item> COCONUT_HUSK = ITEMS.register("coconut_husk",
            () -> new FuelItem(new Item.Properties(), 200));

    // items
    public static final DeferredItem<Item> CALCULATOR = ITEMS.register("calculator",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RUBIX_CUBE = ITEMS.register("rubix_cube",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_CHEESE = ITEMS.register("raw_cheese",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CHEESE = ITEMS.register("cheese",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COCONUT = ITEMS.register("coconut",
            () -> new Item(new Item.Properties()) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.bettersmelting.coconut.tooltip"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    // food items
    public static final DeferredItem<Item> COCONUT_MILK =
            ITEMS.register("coconut_milk", () -> new CoconutMilkItem(new Item.Properties()
                    .food(ModFoodProperties.COCONUT_MILK).stacksTo(16)));

    // usable items
    public static final DeferredItem<Item> MIDAS = ITEMS.register("midas",
            () -> new MidasItem(new Item.Properties().durability(32)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
