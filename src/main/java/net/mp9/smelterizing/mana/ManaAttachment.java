package net.mp9.smelterizing.mana;

import net.mp9.smelterizing.BetterSmelting;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.attachment.AttachmentType;

import java.util.function.Supplier;

public class ManaAttachment {

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, BetterSmelting.MOD_ID);

    public static final Supplier<AttachmentType<ManaData>> MANA =
            ATTACHMENT_TYPES.register("mana", () ->
                    AttachmentType.<ManaData>builder(() -> new ManaData(100, 100))
                            .serialize(ManaData.CODEC)
                            .build()
            );

}
