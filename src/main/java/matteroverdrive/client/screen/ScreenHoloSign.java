package matteroverdrive.client.screen;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import matteroverdrive.client.ClientReferences;
import matteroverdrive.common.inventory.InventoryHoloSign;
import matteroverdrive.common.tile.TileHoloSign;
import matteroverdrive.core.packet.NetworkHandler;
import matteroverdrive.core.packet.type.serverbound.property.PacketUpdateHoloSignServerSide;
import matteroverdrive.core.screen.component.button.ButtonGeneric;
import matteroverdrive.core.screen.component.button.ButtonMenuBar;
import matteroverdrive.core.screen.component.edit_box.EditBoxOverdrive;
import matteroverdrive.core.screen.component.edit_box.EditBoxOverdriveMultiline;
import matteroverdrive.core.screen.types.GenericOverdriveScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import java.util.Objects;

public class ScreenHoloSign extends GenericOverdriveScreen<InventoryHoloSign> {
	private EditBoxOverdriveMultiline textArea;

	private ButtonGeneric close;

	public ButtonMenuBar menu;

	private boolean alreadyOpened;

	public ScreenHoloSign(InventoryHoloSign menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title, 224, 176, true);
	}

	@Override
	protected void init() {
		alreadyOpened = false;

		minecraft.keyboardHandler.setSendRepeatsToGui(true);

		super.init();

		close = getCloseButton(207, 6);

		addButton(close);

		// Initialize once on init.
		String incoming = "";

		if (getMenu().getTile() != null) {
			incoming = getMenu().getTile().getText();
		}

		textArea = new EditBoxOverdriveMultiline(
			EditBoxOverdrive.EditBoxTextures.OVERDRIVE_EDIT_BOX,
			this,
			this.getXPos() + 80,
			this.getYPos() + 65,
			80,
			55
		);

		textArea.setTextColor(ClientReferences.Colors.WHITE.getColor());
		textArea.setFocus(true);
		textArea.setCanLoseFocus(false);
		textArea.setCursorPosition(1);
		textArea.changeFocus(true);
		textArea.setTextColorUneditable(ClientReferences.Colors.WHITE.getColor());
		textArea.setMaxLength(60);
		textArea.setResponder(string -> textArea.setFocus(true));
		textArea.setValue(Objects.requireNonNull(incoming));

		addEditBox(textArea);
	}

	@Override
	public void onClose() {
		super.onClose();

		TileHoloSign holoSign = getMenu().getTile();

		BlockPos pos = holoSign.getBlockPos();

		NetworkHandler.sendToServer(new PacketUpdateHoloSignServerSide(
			pos, textArea.getValue()
		));
	}

	@Override
	public void removed() {
		super.removed();

		minecraft.keyboardHandler.setSendRepeatsToGui(false);
	}
	
	@Override
	public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		super.render(matrixStack, mouseX, mouseY, partialTicks);

		String incoming = Objects.requireNonNull(getMenu().getTile()).getText();

		if (!alreadyOpened && textArea != null) {
			textArea.setValue(incoming);

			alreadyOpened = true;
		}
	}

	public void setText(String text) {
		textArea.value = text;
	}

	@Override
	public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
		InputConstants.Key mouseKey = InputConstants.getKey(pKeyCode, pScanCode);

		if (this.minecraft.options.keyInventory.isActiveAndMatches(mouseKey)) {
			return false;
		}

		return super.keyPressed(pKeyCode, pScanCode, pModifiers);
	}
}
