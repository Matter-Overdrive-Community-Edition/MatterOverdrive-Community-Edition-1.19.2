/**
 * Credit to AurilisDev for this
 */
package matteroverdrive.common.tile.generic;

import matteroverdrive.DeferredRegisters;
import matteroverdrive.core.block.GenericEntityBlock;
import matteroverdrive.core.block.multiblock.IMultiblockTileNode;
import matteroverdrive.core.block.multiblock.Subnode;
import matteroverdrive.core.tile.GenericTile;
import matteroverdrive.core.utils.misc.Location;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TileMultiSubnode extends GenericTile {

	public Location nodePos;
	public VoxelShape shapeCache;

	public TileMultiSubnode(BlockPos worldPosition, BlockState blockState) {
		super(DeferredRegisters.TILE_MULTI_SUBNODE.get(), worldPosition, blockState);
	}

	@Override
	public void saveAdditional(CompoundTag compound) {
		if (nodePos != null) {
			nodePos.writeToNBT(compound, "node");
		}
		super.saveAdditional(compound);
	}

	@Override
	public void load(CompoundTag compound) {
		super.load(compound);
		nodePos = Location.readFromNBT(compound, "node");
	}

	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag superTag = super.getUpdateTag();
		if (nodePos != null) {
			nodePos.writeToNBT(superTag, "node");
		}
		return superTag;
	}

	@Override
	public void handleUpdateTag(CompoundTag tag) {
		super.handleUpdateTag(tag);
		nodePos = Location.readFromNBT(tag, "node");
	}

	public VoxelShape getShape() {
		if (shapeCache != null) {
			return shapeCache;
		}
		if (nodePos != null) {
			BlockEntity tile = nodePos.getTile(level);
			if (tile instanceof IMultiblockTileNode node) {
				BlockState state = level.getBlockState(tile.getBlockPos());
				Direction facing = state.hasProperty(GenericEntityBlock.FACING)
						? state.getValue(GenericEntityBlock.FACING)
						: Direction.UP;
				BlockPos tp = tile.getBlockPos();
				BlockPos offset = new BlockPos(worldPosition.getX() - tp.getX(), worldPosition.getY() - tp.getY(),
						worldPosition.getZ() - tp.getZ());
				for (Subnode sub : node.getSubNodes()) {
					if (offset.equals(sub.pos)) {
						shapeCache = sub.shape.apply(facing);
						return shapeCache;
					}
				}
			}
		}
		return Shapes.block();
	}

}