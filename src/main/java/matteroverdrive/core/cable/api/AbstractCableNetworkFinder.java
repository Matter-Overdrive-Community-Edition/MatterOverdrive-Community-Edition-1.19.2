package matteroverdrive.core.cable.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import matteroverdrive.core.cable.AbstractNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class AbstractCableNetworkFinder {
	public Level worldObj;
	public BlockPos start;
	public AbstractNetwork<?, ?, ?, ?> net;
	public List<BlockEntity> iteratedTiles = new ArrayList<>();
	public List<BlockPos> toIgnore = new ArrayList<>();

	public AbstractCableNetworkFinder(Level world, BlockPos location, AbstractNetwork<?, ?, ?, ?> net,
			BlockPos... ignore) {
		worldObj = world;
		start = location;
		this.net = net;
		if (ignore.length > 0) {
			toIgnore = Arrays.asList(ignore);
		}
	}

	public void loopAll(BlockPos location) {
		BlockEntity curr = worldObj.getBlockEntity(location);
		if (net.isConductor(curr)) {
			iteratedTiles.add(curr);
		}
		for (Direction direction : Direction.values()) {
			BlockPos obj = new BlockPos(location).offset(direction.getNormal());
			if (!(toIgnore.size() == 1 ? toIgnore.get(0) == obj : toIgnore.contains(obj))) {
				if (worldObj.hasChunkAt(obj)) {
					BlockEntity tileEntity = worldObj.getBlockEntity(obj);
					if (!iteratedTiles.contains(tileEntity) && net.isConductor(tileEntity)) {
						loopAll((IAbstractCable) tileEntity);
					}
				}
			}
		}

	}

	public void loopAll(IAbstractCable conductor) {
		iteratedTiles.add((BlockEntity) conductor);
		for (BlockEntity connections : conductor.getAdjacentConnections()) {
			if (connections != null) {
				BlockPos pos = connections.getBlockPos();
				if (!iteratedTiles.contains(connections)
						&& !(toIgnore.size() == 1 ? toIgnore.get(0) == pos : toIgnore.contains(pos))) {
					if (net.isConductor(connections)) {
						loopAll((IAbstractCable) connections);
					}
				}
			}
		}
	}

	public List<BlockEntity> exploreNetwork() {
		loopAll(start);
		return iteratedTiles;
	}
}
