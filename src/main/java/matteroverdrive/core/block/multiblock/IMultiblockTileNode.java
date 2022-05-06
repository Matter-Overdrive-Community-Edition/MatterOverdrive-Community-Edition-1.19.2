/**
 * Credit to AurilisDev for this
 */
package matteroverdrive.core.block.multiblock;

import java.util.HashSet;
import java.util.Iterator;

import matteroverdrive.DeferredRegisters;
import matteroverdrive.common.tile.generic.TileMultiSubnode;
import matteroverdrive.core.utils.misc.Location;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public interface IMultiblockTileNode {

	default void onNodeReplaced(Level world, BlockPos pos, boolean update) {
		Iterator<Subnode> it = getSubNodes().iterator();
		while (it.hasNext()) {
			BlockPos inPos = it.next().pos;
			BlockPos offset = pos.offset(inPos);
			BlockState inState = world.getBlockState(offset);
			Block inBlock = inState.getBlock();
			if (update) {
				if (inState.getMaterial().isReplaceable()) {
					world.setBlockAndUpdate(offset, DeferredRegisters.BLOCK_MULTI_SUBNODE.get().defaultBlockState());
				}
				TileMultiSubnode subnode = (TileMultiSubnode) world.getBlockEntity(offset);
				if (subnode != null) {
					subnode.nodePos = new Location(pos);
				}
			} else if (inBlock instanceof IMultiblockSubnode) {
				TileMultiSubnode subnode = (TileMultiSubnode) world.getBlockEntity(offset);
				if (subnode != null && subnode.nodePos.toBlockPos().equals(pos)) {
					world.setBlockAndUpdate(offset, Blocks.AIR.defaultBlockState());
				}
			}
		}
	}

	default void onNodePlaced(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		onNodeReplaced(world, pos, true);
	}

	HashSet<Subnode> getSubNodes();
}
