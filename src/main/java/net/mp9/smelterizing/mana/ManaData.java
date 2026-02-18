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
        return new ManaData(Math.min(currentMana + amount, maxMana), maxMana);
    }

    public ManaData consume(int amount) {
        return new ManaData(Math.max(currentMana - amount, 0), maxMana);
    }

    public int currentMana() {
        return currentMana;
    }

    public int maxMana() {
        return maxMana;
    }
}
