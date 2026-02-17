package net.mp9.smelterizing.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CoconutMilkItem extends Item {

    public CoconutMilkItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide && entity instanceof Player player) {
            if (level.random.nextFloat() < 0.2f) { // 1 in 5 roll
                player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 600));
                player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 300, 0));
            }
        }

        return result;
    }

}
