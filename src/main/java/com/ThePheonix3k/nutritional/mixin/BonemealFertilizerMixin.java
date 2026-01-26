package com.ThePheonix3k.nutritional.mixin;

import com.ThePheonix3k.nutritional.block.ModBlocks;
import com.ThePheonix3k.nutritional.block.entity.NutritionalFarmlandBlockBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({BoneMealItem.class})

public class BonemealFertilizerMixin {

    @Inject(at = @At("HEAD"), method = "useOnBlock", cancellable = true)
    private void bonemealNutritionalFarmland(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);

        if (state.isOf(ModBlocks.NUTRITIONAL_FARMLAND)) {
            context.getStack().decrement(1);
            world.syncWorldEvent(1505, pos, 0);
            var be = world.getBlockEntity(pos);
            if (be instanceof NutritionalFarmlandBlockBlockEntity nf) {
                nf.nitrogenLevel += 10.0f;
                nf.phosphorusLevel += 10.0f;
                nf.potassiumLevel += 10.0f;
                nf.markDirty();
            }
            context.getStack().decrement(1);

            cir.setReturnValue(ActionResult.success(world.isClient));
        }
    }

}
