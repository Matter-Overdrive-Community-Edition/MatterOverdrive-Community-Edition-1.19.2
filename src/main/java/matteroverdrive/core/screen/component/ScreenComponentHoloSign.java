package matteroverdrive.core.screen.component;

import com.mojang.blaze3d.vertex.PoseStack;
import matteroverdrive.client.ClientReferences.Colors;
import matteroverdrive.common.tile.TileHoloSign;
import matteroverdrive.core.inventory.GenericInventoryTile;
import matteroverdrive.core.screen.GenericScreen;
import matteroverdrive.core.screen.component.utils.OverdriveScreenComponent;
import matteroverdrive.core.utils.UtilsRendering;
import net.minecraft.client.renderer.GameRenderer;

public class ScreenComponentHoloSign extends OverdriveScreenComponent {
	public ScreenComponentHoloSign(GenericScreen<?> gui, int x, int y, int[] screenNumbers) {
		super(OverdriveTextures.TABLET_SCREEN, gui, x, y, 118, 48, screenNumbers);
	}

	@Override
	public void renderBackground(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
		UtilsRendering.bindTexture(resource.getTexture());
		blit(stack, x, y, width, height, 0, 0, width, height, width, height);
	}

	@Override
	public void renderForeground(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
		TileHoloSign holoSign = (TileHoloSign) ((GenericInventoryTile<?>)gui.getMenu()).getTile();

		int color = Colors.HOLO.getColor();

		UtilsRendering.setShader(GameRenderer::getPositionTexShader);
		UtilsRendering.bindTexture(OverdriveTextures.WHITE.getTexture());
		UtilsRendering.setShaderColor(color);

		int marginsTop = 8;
		int marginsLeft = 7;
		int maxHeight = 32;

		UtilsRendering.resetShaderColor();
	}
}
