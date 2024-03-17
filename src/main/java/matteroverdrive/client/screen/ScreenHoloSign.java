package matteroverdrive.client.screen;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import matteroverdrive.client.ClientReferences;
import matteroverdrive.client.ClientReferences.Colors;
import matteroverdrive.common.inventory.InventoryHoloSign;
import matteroverdrive.common.tile.TileHoloSign;
import matteroverdrive.core.packet.NetworkHandler;
import matteroverdrive.core.packet.type.serverbound.property.PacketUpdateHoloSignServerSide;
import matteroverdrive.core.screen.component.ScreenComponentLabel;
import matteroverdrive.core.screen.component.button.ButtonGeneric;
import matteroverdrive.core.screen.component.button.ButtonMenuBar;
import matteroverdrive.core.screen.component.button.ButtonMenuOption;
import matteroverdrive.core.screen.component.button.ButtonMenuOption.MenuButtonType;
import matteroverdrive.core.screen.component.button.ButtonOverdrive;
import matteroverdrive.core.screen.component.edit_box.EditBoxOverdrive;
import matteroverdrive.core.screen.types.GenericMachineScreen;
import matteroverdrive.core.utils.UtilsText;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import java.util.Objects;
import java.util.function.Supplier;

public class ScreenHoloSign extends GenericMachineScreen<InventoryHoloSign> {

	private static boolean EXTENDED = false;

	private EditBoxOverdrive textArea;

	private ButtonGeneric close;

	public ButtonMenuBar menu;

	private ButtonMenuOption home;
	private ButtonMenuOption settings;

	private ButtonOverdrive redstone;
	private Supplier<TileHoloSign> holoSupplier;

	private static final int BETWEEN_MENUS = 26;
	private static final int FIRST_HEIGHT = 40;

	private boolean alreadyOpened;

	public ScreenHoloSign(InventoryHoloSign menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title, 224, 176);
	}

	@Override
	protected void init() {
		alreadyOpened = false;

		minecraft.keyboardHandler.setSendRepeatsToGui(true);

		super.init();

		close = getCloseButton(207, 6);

		menu = new ButtonMenuBar(this, 212, 33, 143, EXTENDED, button -> {
			toggleBarOpen();
			home.visible = !home.visible;
			settings.visible = !settings.visible;
		});

		home = new ButtonMenuOption(this, 217, FIRST_HEIGHT, button -> {
			updateScreen(0);
			settings.isActivated = false;
			textArea.visible = true;
			redstone.visible = false;
			}, MenuButtonType.HOME, menu, true);

		settings = new ButtonMenuOption(this, 217, FIRST_HEIGHT + BETWEEN_MENUS, button -> {
			updateScreen(1);
			home.isActivated = false;
			textArea.visible = false;
			redstone.visible = true;
		}, MenuButtonType.SETTINGS, menu, false);

		redstone = redstoneButton(48, 32);
		addButton(close);
		addButton(menu);
		addButton(home);
		addButton(settings);
		addButton(redstone);
		
		redstone.visible = false;

		// Initialize once on init.
		String incoming = "";

		if (getMenu().getTile() != null) {
			incoming = getMenu().getTile().getText();
		}

		textArea = new EditBoxOverdrive(EditBoxOverdrive.EditBoxTextures.OVERDRIVE_EDIT_BOX, this, this.getXPos() + 45 , this.getYPos() + 30, 150, 135);
		textArea.setTextColor(ClientReferences.Colors.WHITE.getColor());
		textArea.setFocus(true);
		textArea.setCanLoseFocus(false);
		textArea.setCursorPosition(1);
		textArea.setTextColorUneditable(ClientReferences.Colors.WHITE.getColor());
		textArea.setMaxLength(60);
		textArea.setResponder(string -> textArea.setFocus(true));
		textArea.setValue(Objects.requireNonNull(incoming));

		addEditBox(textArea);

		addScreenComponent(new ScreenComponentLabel(this, 110, 37, new int[] { 1 }, UtilsText.gui("redstone"),
				Colors.HOLO.getColor()));
	}

	@Override
	protected void containerTick() {
		super.containerTick();
//		textArea.tick();
	}

	private void toggleBarOpen() {
		EXTENDED = !EXTENDED;
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

//		alreadyOpened = false;

		return super.keyPressed(pKeyCode, pScanCode, pModifiers);
	}
}
