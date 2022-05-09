package matteroverdrive.common.block.cable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import matteroverdrive.common.block.type.TypeMatterConduit;
import matteroverdrive.common.tile.TileMatterConduit;
import matteroverdrive.core.block.WaterloggableEntityBlock;
import matteroverdrive.core.cable.api.EnumConnectType;
import matteroverdrive.core.cable.types.matter_pipe.IMatterConduit;
import matteroverdrive.core.utils.UtilsMatter;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockMatterConduit extends WaterloggableEntityBlock {

	public static final Map<Direction, EnumProperty<EnumConnectType>> FACING_TO_PROPERTY_MAP = Util.make(Maps.newEnumMap(Direction.class), p -> {
		p.put(Direction.NORTH, EnumConnectType.NORTH);
		p.put(Direction.EAST, EnumConnectType.EAST);
		p.put(Direction.SOUTH, EnumConnectType.SOUTH);
		p.put(Direction.WEST, EnumConnectType.WEST);
		p.put(Direction.UP, EnumConnectType.UP);
		p.put(Direction.DOWN, EnumConnectType.DOWN);
	});

	public static final HashSet<Block> PIPESET = new HashSet<>();

	protected final VoxelShape cube;
	protected final VoxelShape cubeup;
	protected final VoxelShape cubedown;
	protected final VoxelShape cubenorth;
	protected final VoxelShape cubesouth;
	protected final VoxelShape cubewest;
	protected final VoxelShape cubeeast;

	protected HashMap<HashSet<Direction>, VoxelShape> shapestates = new HashMap<>();
	protected boolean locked = false;

	public final TypeMatterConduit matterConduit;

	public BlockMatterConduit(TypeMatterConduit pipe) {
		super(Properties.of(Material.METAL).sound(SoundType.METAL).strength(0.15f).dynamicShape());
		this.matterConduit = pipe;
		double w = pipe == TypeMatterConduit.REGULAR ? 2.5 : 3;
		double sm = 8 - w;
		double lg = 8 + w;
		cube = Block.box(sm, sm, sm, lg, lg, lg);
		cubeup = Block.box(sm, sm, sm, lg, 16, lg);
		cubedown = Block.box(sm, 0, sm, lg, lg, lg);
		cubenorth = Block.box(sm, sm, 0, lg, lg, lg);
		cubesouth = Block.box(sm, sm, sm, lg, lg, 16);
		cubewest = Block.box(0, sm, sm, lg, lg, lg);
		cubeeast = Block.box(sm, sm, sm, 16, lg, lg);
		PIPESET.add(this);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(EnumConnectType.UP, EnumConnectType.DOWN, EnumConnectType.NORTH, EnumConnectType.EAST, EnumConnectType.SOUTH, EnumConnectType.WEST);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		VoxelShape shape = cube;
		HashSet<Direction> checked = new HashSet<>();
		if (!EnumConnectType.NONE.equals(state.getValue(EnumConnectType.UP))) {
			checked.add(Direction.UP);
		}
		if (!EnumConnectType.NONE.equals(state.getValue(EnumConnectType.DOWN))) {
			checked.add(Direction.DOWN);
		}
		if (!EnumConnectType.NONE.equals(state.getValue(EnumConnectType.WEST))) {
			checked.add(Direction.WEST);
		}
		if (!EnumConnectType.NONE.equals(state.getValue(EnumConnectType.EAST))) {
			checked.add(Direction.EAST);
		}
		if (!EnumConnectType.NONE.equals(state.getValue(EnumConnectType.NORTH))) {
			checked.add(Direction.NORTH);
		}
		if (!EnumConnectType.NONE.equals(state.getValue(EnumConnectType.SOUTH))) {
			checked.add(Direction.SOUTH);
		}
		locked = true;
		if (shapestates.containsKey(checked)) {
			locked = false;
			return shapestates.get(checked);
		}
		locked = false;
		for (Direction dir : checked) {
			switch (dir) {
			case DOWN:
				shape = Shapes.join(shape, cubedown, BooleanOp.OR);
				break;
			case EAST:
				shape = Shapes.join(shape, cubeeast, BooleanOp.OR);
				break;
			case NORTH:
				shape = Shapes.join(shape, cubenorth, BooleanOp.OR);
				break;
			case SOUTH:
				shape = Shapes.join(shape, cubesouth, BooleanOp.OR);
				break;
			case UP:
				shape = Shapes.join(shape, cubeup, BooleanOp.OR);
				break;
			case WEST:
				shape = Shapes.join(shape, cubewest, BooleanOp.OR);
				break;
			default:
				break;
			}
		}
		// I figured out why this happens
		/*
		while (locked) {
			System.out.println("Wire bounding boxes locked. This should never happen but still does for some reason!");
		}
		*/
		shapestates.put(checked, shape);
		if (shape == null) {
			return Shapes.empty();
		}
		return shape;
	}

	@Override
	public void setPlacedBy(Level worldIn, BlockPos pos, BlockState stateIn, @Nullable LivingEntity placer, ItemStack stack) {
		BlockState acc = stateIn;
		for (Direction d : Direction.values()) {
			BlockEntity facingTile = worldIn.getBlockEntity(pos.relative(d));
			if (facingTile instanceof IMatterConduit) {
				acc = acc.setValue(FACING_TO_PROPERTY_MAP.get(d), EnumConnectType.WIRE);
			} else if (UtilsMatter.isMatterReceiver(facingTile, d.getOpposite())) {
				acc = acc.setValue(FACING_TO_PROPERTY_MAP.get(d), EnumConnectType.INVENTORY);
			}
		}
		worldIn.setBlockAndUpdate(pos, acc);
	}

	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		super.onPlace(state, worldIn, pos, oldState, isMoving);
		if (!worldIn.isClientSide) {
			BlockEntity tile = worldIn.getBlockEntity(pos);
			if (tile instanceof IMatterConduit p) {
				p.refreshNetwork();
			}
		}
	}

	@Override
	public void onNeighborChange(BlockState state, LevelReader world, BlockPos pos, BlockPos neighbor) {
		super.onNeighborChange(state, world, pos, neighbor);
		if (!world.isClientSide()) {
			BlockEntity tile = world.getBlockEntity(pos);
			if (tile instanceof IMatterConduit p) {
				p.refreshNetworkIfChange();
			} 
		}
	}

	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.getValue(BlockStateProperties.WATERLOGGED) == Boolean.TRUE) {
			world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}
		EnumProperty<EnumConnectType> property = FACING_TO_PROPERTY_MAP.get(facing);
		BlockEntity tile = world.getBlockEntity(facingPos);
		if (tile instanceof IMatterConduit) {
			return stateIn.setValue(property, EnumConnectType.WIRE);
		} else if (UtilsMatter.isMatterReceiver(tile, facing.getOpposite())) {
			return stateIn.setValue(property, EnumConnectType.INVENTORY);
		} else {
			return stateIn.setValue(property, EnumConnectType.NONE);
		}
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new TileMatterConduit(pos, state);
	}

}
