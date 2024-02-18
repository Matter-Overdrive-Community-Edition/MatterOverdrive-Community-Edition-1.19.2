package matteroverdrive.common.inventory;

import matteroverdrive.common.tile.TileWeaponStation;
import matteroverdrive.core.capability.types.item.CapabilityInventory;
import matteroverdrive.core.capability.types.item.PlayerSlotDataWrapper;
import matteroverdrive.core.inventory.GenericInventoryTile;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;

public class InventoryWeaponStation extends GenericInventoryTile<TileWeaponStation> {
	protected InventoryWeaponStation(MenuType<?> menu, int id, Inventory playerinv, CapabilityInventory invcap, ContainerData tilecoords) {
		super(menu, id, playerinv, invcap, tilecoords);
	}

	@Override
	public void addInvSlots(CapabilityInventory invcap, Inventory playerinv) {

	}

	@Override
	public PlayerSlotDataWrapper getDataWrapper(Player player) {
		return null;
	}
}
