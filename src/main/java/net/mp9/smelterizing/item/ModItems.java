package net.mp9.smelterizing.item;

import net.minecraft.world.item.Item;
import net.mp9.smelterizing.BetterSmelting;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BetterSmelting.MOD_ID);

    public static final DeferredItem<Item> CALCULATOR = ITEMS.register("calculator",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RUBIX_CUBE = ITEMS.register("rubix_cube",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_CHEESE = ITEMS.register("raw_cheese",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CHEESE = ITEMS.register("cheese",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
