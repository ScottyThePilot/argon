package com.scottythepilot.argon.blocks;

import java.util.Random;

import com.scottythepilot.argon.ArgonRegistryHandler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class FertilizedFarmlandBlock extends FarmlandBlock {
	public FertilizedFarmlandBlock() {
		super(Block.Properties.from(Blocks.FARMLAND));
	}

	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return !this.getDefaultState().isValidPosition(context.getWorld(), context.getPos()) ? getDirt().getDefaultState()
				: super.getStateForPlacement(context);
	}

	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (!state.isValidPosition(worldIn, pos)) {
			turnToDirt(state, worldIn, pos);
		} else {
			int i = state.get(MOISTURE);
			if (!hasWater(worldIn, pos) && !worldIn.isRainingAt(pos.up())) {
				if (i > 0) {
					worldIn.setBlockState(pos, state.with(MOISTURE, Integer.valueOf(i - 1)), 2);
				} else if (!hasCrops(worldIn, pos)) {
					turnToDirt(state, worldIn, pos);
				}
			} else if (i < 7) {
				worldIn.setBlockState(pos, state.with(MOISTURE, Integer.valueOf(7)), 2);
			}
		}
	}

	private boolean hasCrops(IBlockReader worldIn, BlockPos pos) {
		BlockState state = worldIn.getBlockState(pos.up());
		return state.getBlock() instanceof net.minecraftforge.common.IPlantable
				&& canSustainPlant(state, worldIn, pos, Direction.UP, (net.minecraftforge.common.IPlantable) state.getBlock());
	}

	private static boolean hasWater(IWorldReader worldIn, BlockPos pos) {
		for (BlockPos blockpos: BlockPos.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4))) {
			if (worldIn.getFluidState(blockpos).isTagged(FluidTags.WATER)) {
				return true;
			}
		}

		return net.minecraftforge.common.FarmlandWaterManager.hasBlockWaterTicket(worldIn, pos);
	}

	public static void turnToDirt(BlockState state, World worldIn, BlockPos pos) {
		worldIn.setBlockState(pos, nudgeEntitiesWithNewState(state, getDirt().getDefaultState(), worldIn, pos));
	}

	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		if (!worldIn.isRemote && net.minecraftforge.common.ForgeHooks.onFarmlandTrample(worldIn, pos,
				Blocks.DIRT.getDefaultState(), fallDistance, entityIn)) { // Forge: Move logic to Entity#canTrample
			turnToDirt(worldIn.getBlockState(pos), worldIn, pos);
		}

		super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
	}

	private static Block getDirt() {
		return ArgonRegistryHandler.FERTILIZED_DIRT_BLOCK.get();
	}
}
