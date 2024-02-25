package matteroverdrive.common.tile;

import matteroverdrive.common.block.OverdriveBlockStates;
import matteroverdrive.common.block.OverdriveBlockStates.HoloSignSides;
import matteroverdrive.core.tile.types.GenericMachineTile;
import matteroverdrive.registry.BlockRegistry;
import matteroverdrive.registry.TileRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TileHoloSign extends GenericMachineTile {
	private Direction previousFacing = null;

	public TileHoloSign(BlockPos pos, BlockState state) {
		super(TileRegistry.TILE_HOLO_SIGN.get(), pos, state);
	}

	public boolean checkFacing(Direction original, BlockPos toCheck) {
		if (getLevel() == null) {
			return false;
		}

		if (getLevel().getBlockEntity(toCheck) instanceof TileHoloSign holoSign) {
			Direction otherFacing = holoSign.getFacing();

			return original == otherFacing;
		}

		return false;
	}

	public void updateState() {
		Set<BlockPos> visited = new HashSet<>();

		if (getLevel() != null) {
			updateState(getLevel(), this.getBlockPos(), visited, this.getFacing());
		}
	}

	public void updateSides(Level level, Direction facing, BlockPos pos) {
		Set<BlockPos> visited = new HashSet<>();

		System.out.println("Facing is: " + facing);

		switch (facing) {
			case NORTH, SOUTH:
				System.out.println("Updating west side.");

				updateState(level, pos.west(), visited, facing);

				visited.clear();

				System.out.println("Updating east side.");

				updateState(level, pos.east(), visited, facing);
			break;
			case EAST, WEST:
				System.out.println("Updating north side.");

				updateState(level, pos.north(), visited, facing);

				visited.clear();

				System.out.println("Updating south side.");

				updateState(level, pos.south(), visited, facing);
			break;
		}
	}

	public void updateState(Level level, BlockPos pos, Set<BlockPos> visited, Direction facing) {
		BlockState state = this.getBlockState();

		// Ignore non-holo-sign blocks.
		if (!(level.getBlockEntity(pos) instanceof TileHoloSign)) {
			return;
		}

		// Already visited.
		if (visited.contains(pos)) {
			return;
		}

		visited.add(pos);

		boolean leftThere = false;
		boolean rightThere = false;

		BlockState northState;
		BlockState southState;
		BlockState eastState;
		BlockState westState;

		switch (facing) {
			case NORTH:
				eastState = level.getBlockState(pos.relative(Direction.EAST));
				westState = level.getBlockState(pos.relative(Direction.WEST));

				leftThere = westState.is(BlockRegistry.BLOCK_HOLO_SIGN.get()) && checkFacing(facing, pos.relative(Direction.WEST));
				rightThere = eastState.is(BlockRegistry.BLOCK_HOLO_SIGN.get()) && checkFacing(facing, pos.relative(Direction.EAST));
				break;

			case SOUTH:
				eastState = level.getBlockState(pos.relative(Direction.EAST));
				westState = level.getBlockState(pos.relative(Direction.WEST));

				leftThere = eastState.is(BlockRegistry.BLOCK_HOLO_SIGN.get()) && checkFacing(facing, pos.relative(Direction.EAST));
				rightThere = westState.is(BlockRegistry.BLOCK_HOLO_SIGN.get()) && checkFacing(facing, pos.relative(Direction.WEST));
				break;

			case EAST:
				northState = level.getBlockState(pos.relative(Direction.NORTH));
				southState = level.getBlockState(pos.relative(Direction.SOUTH));

				leftThere = northState.is(BlockRegistry.BLOCK_HOLO_SIGN.get()) && checkFacing(facing, pos.relative(Direction.NORTH));
				rightThere = southState.is(BlockRegistry.BLOCK_HOLO_SIGN.get()) && checkFacing(facing, pos.relative(Direction.SOUTH));
				break;

			case WEST:
				northState = level.getBlockState(pos.relative(Direction.NORTH));
				southState = level.getBlockState(pos.relative(Direction.SOUTH));

				leftThere = southState.is(BlockRegistry.BLOCK_HOLO_SIGN.get()) && checkFacing(facing, pos.relative(Direction.SOUTH));
				rightThere = northState.is(BlockRegistry.BLOCK_HOLO_SIGN.get()) && checkFacing(facing, pos.relative(Direction.NORTH));
				break;

			default:
				break;
		}

		if (leftThere && rightThere) {
			state = state.setValue(OverdriveBlockStates.HOLO_SIGN_SIDES, HoloSignSides.NO_LEFT_OR_RIGHT);
		} else if (leftThere) {
			state = state.setValue(OverdriveBlockStates.HOLO_SIGN_SIDES, HoloSignSides.NO_RIGHT);
		} else if (rightThere) {
			state = state.setValue(OverdriveBlockStates.HOLO_SIGN_SIDES, HoloSignSides.NO_LEFT);
		} else {
			state = state.setValue(OverdriveBlockStates.HOLO_SIGN_SIDES, HoloSignSides.NORMAL);
		}

		level.setBlock(pos, state, 2);

		setChanged();

		List<BlockPos> neighbours = new ArrayList<>();

		if (facing == Direction.NORTH || facing == Direction.SOUTH) {
			neighbours.add(pos.east());
			neighbours.add(pos.west());
		}

		if (facing == Direction.EAST || facing == Direction.WEST) {
			neighbours.add(pos.north());
			neighbours.add(pos.south());
		}

		for (BlockPos neighbourPos: neighbours) {
			updateState(level, neighbourPos, visited, facing);
		}
	}

	private static final Set<BlockPos> beenThere = new HashSet<>();

	@Override
	public void onNeighborChange(BlockState state, BlockPos neighbor) {
		super.onNeighborChange(state, neighbor);

		if (getLevel() != null && getLevel().isClientSide()) {
			return;
		}

		System.out.println("In onNeighbourChange.");

		System.out.println("Location: " + this.worldPosition);
		System.out.println("State:    " + getLevel().getBlockEntity(this.worldPosition));

		// This keeps from infinite recursion happening.
		if (!beenThere.contains(this.worldPosition)) {
			updateState();

			beenThere.add(this.worldPosition);
		}
	}

	@Override
	public void onTilePlaced(BlockState state, BlockState oldState, boolean isMoving) {
		super.onTilePlaced(state, oldState, isMoving);

		if (getLevel() != null && getLevel().isClientSide()) {
			return;
		}

		System.out.println("Facing is: " + getFacing());

		previousFacing = ((TileHoloSign) getLevel().getBlockEntity(this.worldPosition)).getFacing();

		System.out.println("In Tile Placed.");

		System.out.println("Location: " + this.worldPosition);

		System.out.println("Facing: " + ((TileHoloSign) getLevel().getBlockEntity(this.worldPosition)).getFacing());

		// This keeps from infinite recursion happening.
		if (!beenThere.contains(this.worldPosition)) {
			updateState();

			beenThere.add(this.worldPosition);
		}
	}

	@Override
	public void setRemoved() {
		if (getLevel() != null && !getLevel().isClientSide()) {
			return;
		}

		System.out.println("Location: " + this.worldPosition);

		System.out.println("Previous facing: " + previousFacing);

		updateSides(getLevel(), previousFacing, this.worldPosition);
	}
}
