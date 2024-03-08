package matteroverdrive.client.screen;

import com.mojang.blaze3d.platform.InputConstants;
import matteroverdrive.client.ClientReferences;
import matteroverdrive.common.inventory.InventoryHoloSign;
import matteroverdrive.core.screen.component.button.ButtonGeneric;
import matteroverdrive.core.screen.component.edit_box.EditBoxOverdrive;
import matteroverdrive.core.screen.types.GenericOverdriveScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ScreenHoloSign extends GenericOverdriveScreen<InventoryHoloSign> {
	private EditBoxOverdrive textArea;

	public ScreenHoloSign(InventoryHoloSign menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title, 176 + InventoryHoloSign.OFFSET, 166 + InventoryHoloSign.OFFSET, true);
	}

	@Override
	protected void init() {
		super.init();

        ButtonGeneric close = getCloseButton(214, 6);

		textArea = new EditBoxOverdrive(EditBoxOverdrive.EditBoxTextures.OVERDRIVE_EDIT_BOX, this, 145, 40, 150, 153);
		textArea.setTextColor(ClientReferences.Colors.WHITE.getColor());
		textArea.setFocus(true);
		textArea.setCursorPosition(1);
		textArea.setTextColorUneditable(ClientReferences.Colors.WHITE.getColor());
		textArea.setMaxLength(100);
//		textArea.setResponder(this::handleQuantityBar);
//		textArea.setFilter(EditBoxOverdrive.POSITIVE_INTEGER_BOX);
		textArea.setValue("");

		addButton(close);

//		addScreenComponent(new ScreenComponentHoloSign(this, 45, 30, new int[]{ 0 }));
		addEditBox(textArea);
	}

	@Override
	public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
		InputConstants.Key mouseKey = InputConstants.getKey(pKeyCode, pScanCode);
		if (this.minecraft.options.keyInventory.isActiveAndMatches(mouseKey) && screenNumber == 0) {
			return false;
		}
		return super.keyPressed(pKeyCode, pScanCode, pModifiers);
	}

}
