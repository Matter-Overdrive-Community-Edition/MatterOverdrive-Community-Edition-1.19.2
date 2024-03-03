package matteroverdrive.common.tile;

import matteroverdrive.core.tile.types.GenericMachineTile;
import matteroverdrive.registry.TileRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileHoloSign extends GenericMachineTile {
	public TileHoloSign(BlockPos pos, BlockState state) {
		super(TileRegistry.TILE_HOLO_SIGN.get(), pos, state);
	}

	public boolean shouldRender() {
		return true;
	}

	public String getText() {
		return "Some text.";
	}
}
