package matteroverdrive.client.screen;

import com.mojang.blaze3d.vertex.PoseStack;

import matteroverdrive.common.inventory.InventoryTritaniumCrate;
import matteroverdrive.core.screen.types.GenericVanillaScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ScreenTritaniumCrate extends GenericVanillaScreen<InventoryTritaniumCrate> {
	public ScreenTritaniumCrate(InventoryTritaniumCrate pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle, 176 + InventoryTritaniumCrate.OFFSET, 166 + InventoryTritaniumCrate.OFFSET);
	}

	@Override
	protected void renderLabels(PoseStack matrixStack, int mouseX, int mouseY) {
	    this.font.draw(matrixStack, this.title, 8.0F, 6.0F, 4210752);
	    this.font.draw(matrixStack, this.playerInventoryTitle, 8.0F, (float) (this.imageHeight - 96 + 2), 4210752);
	}
}
