package matteroverdrive.client.render.tile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import matteroverdrive.client.ClientReferences;
import matteroverdrive.client.ClientReferences;
import matteroverdrive.client.render.tile.utils.AbstractTileRenderer;
import matteroverdrive.common.tile.TileHoloSign;
import matteroverdrive.core.block.GenericEntityBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.awt.font.FontRenderContext;
import java.util.*;

public class RendererHoloSign extends AbstractTileRenderer<TileHoloSign> {
	// Line 1 and line 2.
	Vec3[] TEXT_COORDS = new Vec3[] {
		// One block.
		new Vec3(-49.0f, -20.5f, 30.0f),
		new Vec3(-4.0f, -10.0f, 6.5f),
		new Vec3(-4.0f, -10.0f, 6.5f),
		new Vec3(-4.0f, -10.0f, 6.5f),
	};

	// Offset for line 2.
	// North, South, East, West.
	Vec3[] OFFSET = new Vec3[] {
		new Vec3(-11.5f, 10.0f, 0.0f),
		new Vec3(-11.5f, 10.0f, 0.0f),
		new Vec3(-11.5f, 10.0f, 0.0f),
		new Vec3(-11.5f, 10.0f, 0.0f)
	};

	public RendererHoloSign(BlockEntityRendererProvider.Context context) {
		super(context);
	}

	public static int getBlockWidthInPixels(Level level, BlockPos pos) {
		// Get the block state at the given position
		BlockState blockState = level.getBlockState(pos);

		// Get the voxel shape representing the collision box of the block
		VoxelShape shape = blockState.getCollisionShape(level, pos);

		// Calculate the width of the block in pixels based on the bounding box
		double minX = shape.min(Direction.Axis.X);
		double maxX = shape.max(Direction.Axis.X);

		// Convert from block units to pixels (assuming each block is 16 pixels wide)

		return (int) ((maxX - minX) * 81);
	}

	@Override
	public void render(TileHoloSign tile, float ticks, PoseStack matrix, MultiBufferSource buffer, int light,
										 int overlay) {
		if (tile.getLevel() != null && !tile.getLevel().isClientSide()) {
			return;
		}

		matrix.pushPose();

		Minecraft instance = Minecraft.getInstance();

		Direction facing = tile.getBlockState().getValue(GenericEntityBlock.FACING);

		String text = "A";

//		List<String> lines = new ArrayList<>();
//
//		StringBuilder temp = new StringBuilder();

//		for (int i=0; i<text.length(); i++) {
//			temp.append(text.charAt(i));
//
//			if (font.width(temp.toString()) > getBlockWidthInPixels(tile.getLevel(), tile.getBlockPos())) {
//				lines.add(temp.toString().trim());
//
//				temp = new StringBuilder();
//			}
//		}
//
//		if (!temp.isEmpty()) {
//			lines.add(temp.toString());
//		}

//		String[] results = lines.toArray(new String[]{});

		final Map<Float, float[]> fontToLocation = Map.of(
			0.01f, new float[]{ -95.0f, -95.0f, 55f },
			0.02f, new float[]{ -47.0f, -48.0f, 30f }
		);

		final float startScale = 0.02f;

		matrix.scale(startScale, startScale, startScale);

		matrix.mulPose(Vector3f.ZP.rotationDegrees(180));

		switch (facing) {
			case NORTH:
				matrix.translate(
					fontToLocation.get(startScale)[0],
					fontToLocation.get(startScale)[1],
					fontToLocation.get(startScale)[2]
				);
			break;
			case SOUTH:
				matrix.translate(TEXT_COORDS[1].x, TEXT_COORDS[1].y, TEXT_COORDS[1].z);

				matrix.mulPose(Vector3f.YP.rotationDegrees(180));
			break;
			case EAST:
				matrix.translate(TEXT_COORDS[2].x, TEXT_COORDS[2].y, TEXT_COORDS[2].z);

				matrix.mulPose(Vector3f.YP.rotationDegrees(90));
			break;
			case WEST:
				matrix.translate(TEXT_COORDS[3].x, TEXT_COORDS[3].y, TEXT_COORDS[3].z);

				matrix.mulPose(Vector3f.YP.rotationDegrees(270));
			break;
		}

//		for (String line: results) {
//			instance.font.draw(matrix, Component.literal(line), 0f, 0f,
//							ClientReferences.Colors.HOLO.getColor());

			instance.font.draw(matrix, Component.literal(text), 0f, 0f,
					ClientReferences.Colors.HOLO.getColor());

//			matrix.translate(0.0f, 10.0f, 0.0f);
//		}

		matrix.popPose();
	}
}
