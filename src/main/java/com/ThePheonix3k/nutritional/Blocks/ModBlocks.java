package com.ThePheonix3k.nutritional.Blocks;

import com.ThePheonix3k.nutritional.Nutritional;
import com.ThePheonix3k.nutritional.blockentites.FarmlandBlockEntity;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static void initialize() {}

    public static Block register(Block block, String name, boolean shouldRegisterItem) {
        Identifier id = new Identifier(Nutritional.MOD_ID, name);

        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Settings());
            Registry.register(Registries.ITEM, id, blockItem);
        }

        return Registry.register(Registries.BLOCK, id, block);
    }

    public static final Block NUTRITIONAL_FARMLAND = register(
            new NutritionalFarmlandBlock(AbstractBlock.Settings.create().mapColor(MapColor.DIRT_BROWN).ticksRandomly().strength(0.6f).sounds(BlockSoundGroup.GRAVEL).blockVision(Blocks::always).suffocates(Blocks::always)),
            "farmland",
            true
    );
}
