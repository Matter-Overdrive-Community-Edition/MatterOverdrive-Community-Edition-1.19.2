package matteroverdrive.client.screen;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import matteroverdrive.client.ClientReferences;
import matteroverdrive.common.inventory.InventoryHoloSign;
import matteroverdrive.common.tile.TileHoloSign;
import matteroverdrive.core.screen.component.button.ButtonGeneric;
import matteroverdrive.core.screen.component.edit_box.EditBoxOverdrive;
import matteroverdrive.core.screen.types.GenericOverdriveScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.Nullable;

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
		textArea.setCanLoseFocus(false);
		textArea.setCursorPosition(1);
		textArea.setTextColorUneditable(ClientReferences.Colors.WHITE.getColor());
		textArea.setMaxLength(100);

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

	@Override
	public void onClose() {
		super.onClose();

		TileHoloSign holoSign = getMenu().getTile();

		if (holoSign != null) {
			CompoundTag tag = new CompoundTag();

			tag.putString("text", textArea.getValue());

			holoSign.setText(tag);
		}
	}

	public void setText(String text) {
		textArea.value = text;
	}
}
