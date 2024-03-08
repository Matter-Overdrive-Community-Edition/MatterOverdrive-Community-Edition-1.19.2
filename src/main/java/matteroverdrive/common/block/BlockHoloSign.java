package matteroverdrive.common.block;

import matteroverdrive.common.tile.TileHoloSign;
import matteroverdrive.core.block.GenericEntityBlock;
import matteroverdrive.core.block.OverdriveBlockProperties;
import matteroverdrive.core.tile.GenericTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BlockHoloSign extends GenericEntityBlock {
    private final static VoxelShape N = Block.box(0.0D, 0.0D, 11.0D, 16.0D, 16.0D, 16.0D);
    private final static VoxelShape S = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 5.0D);
    private final static VoxelShape E = Block.box(11.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private final static VoxelShape W = Block.box(0.0D, 0.0D, 0.0D, 5.0D, 16.0D, 16.0D);

    protected BlockHoloSign(OverdriveBlockProperties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)) {
            case NORTH: return N;
            case SOUTH: return S;
            case EAST:  return E;
            case WEST:  return W;
            default:    return super.getShape(state, level, pos, context);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TileHoloSign(blockPos, blockState);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
                                 BlockHitResult hit) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        BlockEntity tile = level.getBlockEntity(pos);

        if (tile instanceof GenericTile generic) {
            if (generic.hasMenu) {
                player.openMenu(generic.getMenuProvider());
            }

            return InteractionResult.CONSUME;
        }

        return InteractionResult.FAIL;
    }

}
