package net.mp9.smelterizing.item.custom;

// importing modblocks
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

public class MidasItem  extends Item {

    private static final Map<Block, Block> MIDAS_MAP =
            Map.of(
                    Blocks.STONE, Blocks.GOLD_BLOCK,
                    Blocks.GRASS_BLOCK, Blocks.GOLD_BLOCK,
                    Blocks.OAK_LOG, Blocks.GOLD_BLOCK,
                    Blocks.OAK_LEAVES, Blocks.GOLD_BLOCK,
                    Blocks.OAK_PLANKS, Blocks.GOLD_BLOCK,
                    Blocks.DIRT, Blocks.GOLD_BLOCK
            );

    public MidasItem(Properties properties) {
        super(properties);
    }

    private static boolean isUsable(ItemStack stack) {
        return stack.getDamageValue() < stack.getMaxDamage() - 1;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        ItemStack stack = context.getItemInHand();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        // 🔴 Stop functionality at 1 durability
        if (!isUsable(stack)) {
            return InteractionResult.FAIL;
        }

        if (MIDAS_MAP.containsKey(clickedBlock)) {
            if (!level.isClientSide()) {

                // Turn block to gold
                level.setBlockAndUpdate(
                        context.getClickedPos(),
                        MIDAS_MAP.get(clickedBlock).defaultBlockState()
                );

                // ✅ Only damage if still usable
                stack.hurtAndBreak(1, (ServerLevel) level, context.getPlayer(),
                        item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND)
                );

                level.playSound(null,
                        context.getClickedPos(),
                        SoundEvents.AMETHYST_BLOCK_CHIME,
                        SoundSource.BLOCKS
                );
            }
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}