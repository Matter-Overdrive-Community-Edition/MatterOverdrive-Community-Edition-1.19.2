package matteroverdrive.client.render.tile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
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

import java.util.ArrayList;
import java.util.List;

public class RendererHoloSign extends AbstractTileRenderer<TileHoloSign> {
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
	public void render(TileHoloSign tile, float ticks, PoseStack matrix,
					   MultiBufferSource buffer, int light, int overlay) {
		// These should be final offsets and coordinates.

		// First line.
		// North, South, East, West.
		final Vec3[] TEXT_COORDS = new Vec3[] {
			new Vec3(-60.0f, -50.0f, 25.0f),  // North
			new Vec3(10.0f, -50.0f, 25.0f),   // South
			new Vec3(-23.0f, -50.0f, 61.0f),  // East
			new Vec3(-23.0f, -50.0f, -10.0f), // West
		};

		if (!tile.shouldRender()) {
			return;
		}

//		System.out.println("Client Side? " + tile.getLevel().isClientSide());

		if (tile.getLevel() != null && !tile.getLevel().isClientSide()) {
			return;
		}

		String text = tile.holoSignTextProp.getOrElse("");

//		System.out.println("Text is: " + text);

		// Not sure why this happens, but every second time, the text comes through as blank.
		if (text.isEmpty()) {
			return;
		}

		matrix.pushPose();

		Font font = Minecraft.getInstance().font;

		Direction facing = tile.getBlockState().getValue(GenericEntityBlock.FACING);

		final float startScale = 0.02f;

		matrix.scale(startScale, startScale, startScale);

		matrix.mulPose(Vector3f.ZP.rotationDegrees(180));

		switch (facing) {
			case NORTH:
				matrix.translate(TEXT_COORDS[0].x, TEXT_COORDS[0].y, TEXT_COORDS[0].z);
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

		// Limit to 5 lines, or 60 characters (12 chars per line).
		text = text.substring(0, Math.min(60, text.length()));

		List<String> lines = new ArrayList<>();

		int index = 0;

		while (!text.isEmpty()) {
			while (font.width(text.substring(0, index)) < 72 && index < text.length()) {
				index++;
			}

			lines.add(text.substring(0, index));

			text = text.substring(index);

			index = 0;
		}

		int offsetIndex = lines.size() - 1;

		List<Float> yOffsets = List.of(20.0f, 15.0f, 10.0f, 5.0f, 0.0f);

		matrix.translate(0.0f, yOffsets.get(offsetIndex), 0.0f);

		for (String line: lines) {
			font.draw(matrix, Component.literal(line), 0f, 0f, ClientReferences.Colors.HOLO.getColor());

			matrix.translate(0.0f, 10.0f, 0.0f);
		}

		matrix.popPose();
	}
}
