package matteroverdrive.client.screen;

import matteroverdrive.common.inventory.InventoryHoloSign;
import matteroverdrive.core.screen.component.ScreenComponentHoloSign;
import matteroverdrive.core.screen.component.ScreenComponentHotbarBar;
import matteroverdrive.core.screen.component.button.ButtonGeneric;
import matteroverdrive.core.screen.component.button.ButtonMenuBar;
import matteroverdrive.core.screen.types.GenericMachineScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ScreenHoloSign extends GenericMachineScreen<InventoryHoloSign> {

	private static boolean EXTENDED = false;

	public ButtonGeneric close;

	public ButtonMenuBar menu;

	public ScreenHoloSign(InventoryHoloSign menu, Inventory playerinventory, Component title) {
		super(menu, playerinventory, title, 224, 176);
	}

	@Override
	protected void init() {
		super.init();

		close = getCloseButton(207, 6);
		menu = new ButtonMenuBar(this, 212, 33, 143);

		addButton(close);
		addButton(menu);

		addScreenComponent(new ScreenComponentHoloSign(this, 45, 30, new int[]{ 0 }));

//		addScreenComponent(new ScreenComponentHotbarBar(this, 40, 143, 169, new int[] { 0, 1, 2, 3 }));
//		addScreenComponent(new ScreenComponentMatterAnalyzer(this, 52, 33, new int[] { 0 }));
//		addScreenComponent(defaultEnergyBar(180, 35, new int[] { 0 }));
//		addScreenComponent(getRunningIndicator(6, 159, new int[] { 0, 1, 2, 3 }));
//		addScreenComponent(new ScreenComponentHotbarBar(this, 40, 143, 169, new int[] { 0 }));
//		addScreenComponent(new ScreenComponentLabel(this, 110, 37, new int[] { 1 }, UtilsText.gui("redstone"),
//				Colors.HOLO.getColor()));
//		addScreenComponent(new ScreenComponentUpgradeInfo(this, 79, 76, new int[] { 2 }));
	}
}
