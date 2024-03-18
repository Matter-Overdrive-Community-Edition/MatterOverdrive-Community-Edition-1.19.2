package matteroverdrive.common.inventory;

import matteroverdrive.common.tile.TileHoloSign;
import matteroverdrive.core.capability.types.item.CapabilityInventory;
import matteroverdrive.core.capability.types.item.PlayerSlotDataWrapper;
import matteroverdrive.core.inventory.GenericInventoryTile;
import matteroverdrive.core.screen.component.ScreenComponentSlot.SlotType;
import matteroverdrive.registry.MenuRegistry;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;

public class InventoryHoloSign extends GenericInventoryTile<TileHoloSign> {

//	public InventoryHoloSign(int id, Inventory playerinv) {
//		this(id, playerinv, new CapabilityInventory(TileHoloSign.SIZE, false, false), new SimpleContainerData(3));
//	}
//
//	public InventoryHoloSign(int id, Inventory playerinv, CapabilityInventory invcap, ContainerData coords) {
//		super(MenuRegistry.MENU_HOLO_SIGN.get(), id, playerinv, invcap, coords);
//	}

	public InventoryHoloSign(int id, Inventory playerinv) {
		this(id, playerinv, new CapabilityInventory(0, false, false), new SimpleContainerData(3));
	}

	public InventoryHoloSign(int id, Inventory playerinv, CapabilityInventory invcap, ContainerData tilecoords) {
		super(MenuRegistry.MENU_HOLO_SIGN.get(), id, playerinv, invcap, tilecoords);
	}

	@Override
	public void init() {
		hasInventorySlots = false;
		hasHotbarSlots = false;
		super.init();
	}

	@Override
	public PlayerSlotDataWrapper getDataWrapper(Player player) {
		return null;
	}

	@Override
	public void addInvSlots(CapabilityInventory invcap, Inventory playerinv) {
		// unused
	}
}
