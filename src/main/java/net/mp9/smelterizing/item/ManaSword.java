package net.mp9.smelterizing.item;

import net.mp9.smelterizing.mana.ManaAttachment;
import net.mp9.smelterizing.mana.ManaData;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class ManaSword extends SwordItem {

    public ManaSword() {
        super(Tiers.IRON, new net.minecraft.world.item.Item.Properties().stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        ManaData mana = player.getData(ManaAttachment.MANA.get());

        int cost = 40;
        if (mana.currentMana() >= cost) {
            // Consume mana and set back
            ManaData newMana = mana.consume(cost);
            player.setData(ManaAttachment.MANA.get(), newMana);

            // Give Speed II for 20 seconds
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 20, 1));

            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
        }

        return InteractionResultHolder.pass(stack);
    }
}
