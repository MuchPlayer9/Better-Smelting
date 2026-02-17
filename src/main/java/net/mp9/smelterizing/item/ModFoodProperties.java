package net.mp9.smelterizing.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties COCONUT_MILK =
            new FoodProperties.Builder()
            .nutrition(8)
            .saturationModifier(3.0f)
            .usingConvertsTo(ModItems.COCONUT_HUSK)
            .build();
}
