package com.ThePheonix3k.nutritional.Blocks;

import com.ThePheonix3k.nutritional.Nutritional;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    // Declare block first
    public static final Block NUTRITIONAL_FARMLAND = register(
            new NutritionalFarmlandBlock(AbstractBlock.Settings.create().mapColor(MapColor.DIRT_BROWN).ticksRandomly().strength(0.6f).sounds(BlockSoundGroup.GRAVEL).blockVision(Blocks::always).suffocates(Blocks::always)),
            "farmland",
            true
    );
    
    // Block entity type will be initialized in initialize()
    public static BlockEntityType<NutritionalFarmlandBlockBlockEntity> NUTRITIONAL_FARMLAND_BLOCK_ENTITY;
    
    public static void initialize() {
        // Register block entity type after block is registered
        NUTRITIONAL_FARMLAND_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(Nutritional.MOD_ID, "farmland_block_entity"),
            BlockEntityType.Builder.create(NutritionalFarmlandBlockBlockEntity::new, NUTRITIONAL_FARMLAND).build(null)
        );
    }

    public static Block register(Block block, String name, boolean shouldRegisterItem) {
        Identifier id = new Identifier(Nutritional.MOD_ID, name);

        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Settings());
            Registry.register(Registries.ITEM, id, blockItem);
        }

        return Registry.register(Registries.BLOCK, id, block);
    }
}
