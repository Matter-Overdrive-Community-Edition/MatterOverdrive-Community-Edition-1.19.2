package matteroverdrive.common.block;

import matteroverdrive.common.tile.TileHoloSign;
import matteroverdrive.core.block.GenericEntityBlock;
import matteroverdrive.core.block.OverdriveBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.Nullable;

public class BlockHoloSign extends GenericEntityBlock {
	public BlockHoloSign(OverdriveBlockProperties properties) {
		super(properties);

		registerDefaultState(
			getStateDefinition().any().setValue(
				OverdriveBlockStates.HOLO_SIGN_SIDES,
				OverdriveBlockStates.HoloSignSides.NORMAL
			)
		);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);

		System.out.println("Has Omnidirectional: " + getStateDefinition().getProperty(OverdriveBlockStates.VERTICAL_FACING.getName()));

		builder.add(OverdriveBlockStates.HOLO_SIGN_SIDES);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new TileHoloSign(blockPos, blockState);
	}
}
