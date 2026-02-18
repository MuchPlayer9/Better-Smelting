package net.mp9.smelterizing.mana;

import net.mp9.smelterizing.BetterSmelting;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@net.neoforged.fml.common.EventBusSubscriber(modid = BetterSmelting.MOD_ID, value = Dist.CLIENT)
public class ManaTickHandler {

    private static final int REGEN_TICKS = 20; // 1 second

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.level().isClientSide()) return; // server-side only

        ManaData mana = player.getData(ManaAttachment.MANA.get());

        // Regenerate 1 mana per second
        if (player.tickCount % REGEN_TICKS == 0) {
            // NeoForge attachments are immutable — add mana returns new instance
            ManaData newMana = mana.add(1);
            player.setData(ManaAttachment.MANA.get(), newMana);
        }

        // -----------------------------
        // 🔹 Test ability: Sword gives Speed II for 20 seconds, costs 40 mana
        ItemStack mainHand = player.getMainHandItem();
        // You can add checks here for right-click or keybind triggers
        // e.g., if mainHand.getItem() instanceof ManaSword
    }
}
