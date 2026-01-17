package com.ThePheonix3k.nutritional.Blocks;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class NutritionalFarmlandBlock extends FarmlandBlock {
    public NutritionalFarmlandBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(NITROGEN, 0).with(PHOSPHORUS, 0).with(POTASSIUM, 0));
    }

    public static final IntProperty PHOSPHORUS = BlockProperties.PHOSPHORUS;
    public static final IntProperty NITROGEN = BlockProperties.NITROGEN;
    public static final IntProperty POTASSIUM = BlockProperties.POTASSIUM;



    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        // i HATE crop trampling. All my homies HATE crop trampling.
        // ~authors

//        if (!world.isClient && world.random.nextFloat() < fallDistance - 0.5F && entity instanceof LivingEntity && (entity instanceof PlayerEntity || world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) && entity.getWidth() * entity.getWidth() * entity.getHeight() > 0.512F) {
//            setToDirt(entity, state, world, pos);
//        }
//
//        super.onLandedUpon(world, state, pos, entity, fallDistance);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
//        int i = (Integer)state.get(MOISTURE);
//        if (!isWaterNearby(world, pos) && !world.hasRain(pos.up())) {
//            if (i > 0) {
//                world.setBlockState(pos, (BlockState)state.with(MOISTURE, i - 1), 2);
//            } else if (!hasCrop(world, pos)) {
//                setToDirt((Entity)null, state, world, pos);
//            }
//        } else if (i < 7) {
//            world.setBlockState(pos, (BlockState)state.with(MOISTURE, 7), 2);
//        }

    }

    private static boolean isWaterNearby(WorldView world, BlockPos pos) {
        for(BlockPos blockPos : BlockPos.iterate(pos.add(-4, 0, -4), pos.add(4, 1, 4))) {
            if (world.getFluidState(blockPos).isIn(FluidTags.WATER)) {
                return true;
            }
        }

        return false;
    }

    private static boolean hasCrop(BlockView world, BlockPos pos) {
        return world.getBlockState(pos.up()).isIn(BlockTags.MAINTAINS_FARMLAND);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PHOSPHORUS, NITROGEN, POTASSIUM, MOISTURE);
    }
}
