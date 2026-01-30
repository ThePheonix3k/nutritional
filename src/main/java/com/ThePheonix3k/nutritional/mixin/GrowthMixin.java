package com.ThePheonix3k.nutritional.mixin;


import com.ThePheonix3k.nutritional.block.ModBlocks;
import net.minecraft.block.AttachedStemBlock;
import net.minecraft.block.CropBlock;
import net.minecraft.block.PitcherCropBlock;
import net.minecraft.block.StemBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.block.BlockState

@Mixin({PitcherCropBlock.class, CropBlock.class, AttachedStemBlock.class, StemBlock.class})

public class GrowthMixin {
    @Inject(at = @At("TAIL"), method = "canPlantOnTop", cancellable = true)
    private void pitcherCropBlockCanPlantOnTopOfRichSoil(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (floor.isOf(ModBlocks.NUTRITIONAL_FARMLAND)) {
            cir.setReturnValue(true);
        }
    }
}
