package net.mp9.smelterizing.item;

import net.mp9.smelterizing.mana.ManaAttachment;
import net.mp9.smelterizing.mana.ManaData;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

/**
 * A sword that uses mana to grant Speed II when right‑clicked.
 */
public class ManaSword extends SwordItem {

    public ManaSword() {
        // No .tab(...) in NeoForge 1.21.1 Item.Properties
        super(Tiers.IRON, new Item.Properties().stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // Get the player's mana attachment
        ManaData mana = player.getData(ManaAttachment.MANA.get());

        int cost = 40;
        if (mana.currentMana() >= cost) {

            // Deduct mana
            ManaData newMana = mana.consume(cost);
            player.setData(ManaAttachment.MANA.get(), newMana);

            // Apply Speed II for 20 seconds (20 ticks = 1 second)
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 20, 1));

            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
        }

        return InteractionResultHolder.pass(stack);
    }
}
