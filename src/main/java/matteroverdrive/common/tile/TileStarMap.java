package matteroverdrive.common.tile;

import matteroverdrive.core.tile.types.GenericMachineTile;
import matteroverdrive.registry.TileRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileStarMap extends GenericMachineTile {
	public TileStarMap(BlockPos pos, BlockState state) {
		super(TileRegistry.TILE_STAR_MAP.get(), pos, state);
	}
}
