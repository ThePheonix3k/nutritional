package com.ThePheonix3k.nutritional.Blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;

public class NutritionalFarmlandBlockBlockEntity extends BlockEntity {
    public float nitrogenLevel;
    public float phosphorusLevel;
    public float potassiumLevel;

    private double hydrationLevel;

    public NutritionalFarmlandBlockBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.NUTRITIONAL_FARMLAND_BLOCK_ENTITY, pos, state);
        this.nitrogenLevel = 0.0f;
        this.phosphorusLevel = 0.0f;
        this.potassiumLevel = 0.0f;
        this.hydrationLevel = 0.0;
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.hydrationLevel = findNearestWaterDistance(world, pos);
        markDirty();
    }

    public double getHydrationLevel() {
        return this.hydrationLevel;
    }

    public double findNearestWaterDistance(WorldView world, BlockPos pos) {
        double minWaterDist = Double.MAX_VALUE;
        boolean foundWater = false;
        int SEARCH_DIST = 10;

        for(int i = -SEARCH_DIST; i <= SEARCH_DIST; ++i) {
            for(int j = -SEARCH_DIST; j <= SEARCH_DIST; ++j) {
                for(int k = -SEARCH_DIST; k <= SEARCH_DIST; ++k) {
                    BlockPos p = pos.add(i, k, j);
                    if (world.getFluidState(p).isIn(FluidTags.WATER)) {
                        double dx = (p.getX()) - (pos.getX());
                        double dy = (p.getY()) - (pos.getY());
                        double dz = (p.getZ()) - (pos.getZ());
                        double dist = Math.abs(dx) + Math.abs(dy) + Math.abs(dz);
                        if (dist < minWaterDist) {
                            minWaterDist = dist;
                            foundWater = true;
                        }
                    }
                }
            }
        }
        return foundWater ? minWaterDist : 0;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putFloat("nitrogenLevel", nitrogenLevel);
        nbt.putFloat("phosphorusLevel", phosphorusLevel);
        nbt.putFloat("potassiumLevel", potassiumLevel);
        nbt.putDouble("hydrationLevel", hydrationLevel);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("nitrogenLevel")) {
            this.nitrogenLevel = nbt.getFloat("nitrogenLevel");
        }
        if (nbt.contains("phosphorusLevel")) {
            this.phosphorusLevel = nbt.getFloat("phosphorusLevel");
        }
        if (nbt.contains("potassiumLevel")) {
            this.potassiumLevel = nbt.getFloat("potassiumLevel");
        }
        if (nbt.contains("hydrationLevel")) {
            this.hydrationLevel = nbt.getDouble("hydrationLevel");
        }
    }
}
