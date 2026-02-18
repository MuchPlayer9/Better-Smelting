package net.mp9.smelterizing.mana;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record ManaData(int currentMana, int maxMana) {

    public static final Codec<ManaData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("currentMana").forGetter(ManaData::currentMana),
                    Codec.INT.fieldOf("maxMana").forGetter(ManaData::maxMana)
            ).apply(instance, ManaData::new)
    );

    public ManaData add(int amount) {
        int newVal = Math.min(currentMana + amount, maxMana);
        return new ManaData(newVal, maxMana);
    }

    public ManaData consume(int cost) {
        int newVal = Math.max(currentMana - cost, 0);
        return new ManaData(newVal, maxMana);
    }
}
