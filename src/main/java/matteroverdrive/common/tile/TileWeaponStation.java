package matteroverdrive.common.tile;

import matteroverdrive.core.tile.GenericTile;
import matteroverdrive.registry.TileRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileWeaponStation extends GenericTile {
	public TileWeaponStation(BlockPos pos, BlockState state) {
		super(TileRegistry.TILE_WEAPON_STATION.get(), pos, state);
	}
}
