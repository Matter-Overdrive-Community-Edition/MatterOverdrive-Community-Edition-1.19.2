package matteroverdrive.common.block;

import matteroverdrive.common.tile.TileHoloSign;
import matteroverdrive.core.block.GenericEntityBlock;
import matteroverdrive.core.block.OverdriveBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BlockHoloSign extends GenericEntityBlock {
	protected BlockHoloSign(OverdriveBlockProperties properties) {
		super(properties);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new TileHoloSign(blockPos, blockState);
	}
}
