package com.ThePheonix3k.nutritional;

import com.ThePheonix3k.nutritional.Blocks.ModBlocks;
import net.fabricmc.api.ModInitializer;

public class Nutritional implements ModInitializer {
    public static final String MOD_ID = "nutritional";

    @Override
    public void onInitialize() {
        ModBlocks.initialize();
    }
}
