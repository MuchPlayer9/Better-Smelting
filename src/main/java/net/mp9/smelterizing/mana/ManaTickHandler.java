package net.mp9.smelterizing.mana;

import net.mp9.smelterizing.BetterSmelting;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.attachment.AttachmentType;

@EventBusSubscriber(modid = BetterSmelting.MOD_ID, value = Dist.CLIENT)
public class ManaTickHandler {

    private static final int REGEN_TICKS = 20; // 1 second

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        if (player.level().isClientSide()) return; // server-side only

        AttachmentType<ManaData> manaType = ManaAttachment.MANA.get();
        ManaData mana = player.getData(manaType);
        if (mana == null) return;

        // -----------------------------
        // 1️⃣ Regenerate mana every second
        if (player.tickCount % REGEN_TICKS == 0) {
            ManaData newMana = mana.add(1); // generate 1 mana
            player.setData(ManaAttachment.MANA.get(), newMana);
            player.syncData(ManaAttachment.MANA.get()); // send updated mana to client
            System.out.println("Step 3: Regenerated mana for " + player.getName().getString() + " = " + newMana.currentMana());
        }

        // -----------------------------
        // 2️⃣ Test ability: sword gives Speed II for 20s, costs 40 mana
        ItemStack mainHand = player.getMainHandItem();
        if (mainHand.getItem() == Items.DIAMOND_SWORD) { // Replace with your Mana Sword
            if (player.isUsingItem()) { // Example trigger: right-click
                if (mana.currentMana() >= 40) {
                    ManaData newMana = mana.consume(40);
                    player.setData(ManaAttachment.MANA.get(), newMana);
                    player.syncData(ManaAttachment.MANA.get());
                    player.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                            net.minecraft.world.effect.MobEffects.MOVEMENT_SPEED,
                            20 * 20, // 20 seconds
                            1 // Speed II
                    ));
                    System.out.println("Step 3: Used Mana Sword ability, new mana = " + newMana.currentMana());
                } else {
                    System.out.println("Step 3: Not enough mana for ability!");
                }
            }
        }
    }
}
