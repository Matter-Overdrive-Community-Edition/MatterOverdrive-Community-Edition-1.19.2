package matteroverdrive.common.inventory;

import matteroverdrive.common.tile.TileHoloSign;
import matteroverdrive.core.capability.types.item.CapabilityInventory;
import matteroverdrive.core.capability.types.item.PlayerSlotDataWrapper;
import matteroverdrive.core.inventory.GenericInventoryTile;
import matteroverdrive.core.inventory.slot.SlotGeneric;
import matteroverdrive.core.screen.component.ScreenComponentIcon;
import matteroverdrive.core.screen.component.ScreenComponentSlot;
import matteroverdrive.registry.MenuRegistry;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;

public class InventoryHoloSign extends GenericInventoryTile<TileHoloSign> {
	public InventoryHoloSign(int id, Inventory playerinv, CapabilityInventory invcap, ContainerData tilecoords) {
		super(MenuRegistry.MENU_HOLO_SIGN.get(), id, playerinv, invcap, tilecoords);
	}
	public InventoryHoloSign(int id, Inventory playerinv) {
		this(id, playerinv, new CapabilityInventory(TileHoloSign.SLOT_COUNT, true, true),
				new SimpleContainerData(0));
	}

	@Override
	public void addInvSlots(CapabilityInventory invcap, Inventory playerinv) {
//		addSlot(new SlotGeneric(invcap, nextIndex(), 0, 0, new int[] { 0 }, ScreenComponentSlot.SlotType.MAIN, ScreenComponentIcon.IconType.NONE));
	}

	@Override
	public PlayerSlotDataWrapper getDataWrapper(Player player) {
		return defaultOverdriveScreen(new int[] {}, new int[] {});
	}
}
