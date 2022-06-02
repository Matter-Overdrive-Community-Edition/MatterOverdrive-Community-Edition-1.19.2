package matteroverdrive.common.inventory;

import matteroverdrive.DeferredRegisters;
import matteroverdrive.common.item.ItemUpgrade.UpgradeType;
import matteroverdrive.common.tile.matter_network.TileNetworkPowerSupply;
import matteroverdrive.core.capability.types.item.CapabilityInventory;
import matteroverdrive.core.inventory.GenericInventoryTile;
import matteroverdrive.core.inventory.slot.SlotEnergyCharging;
import matteroverdrive.core.inventory.slot.SlotUpgrade;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;

public class InventoryNetworkPowerSupply extends GenericInventoryTile<TileNetworkPowerSupply> {

	public static final UpgradeType[] UPGRADES = new UpgradeType[] { UpgradeType.POWER_STORAGE };

	public InventoryNetworkPowerSupply(int id, Inventory playerinv, CapabilityInventory invcap, ContainerData tilecoords) {
		super(DeferredRegisters.MENU_NETWORK_POWER_SUPPLY.get(), id, playerinv, invcap, tilecoords);
	}
	
	public InventoryNetworkPowerSupply(int id, Inventory playerinv) {
		this(id, playerinv, new CapabilityInventory(TileNetworkPowerSupply.SLOT_COUNT, true, true), new SimpleContainerData(3));
	}

	@Override
	public void addInvSlots(CapabilityInventory invcap, Inventory playerinv) {
		addSlot(new SlotEnergyCharging(invcap, nextIndex(), 8, 48, new int[] { 0 }));
		addSlot(new SlotUpgrade(invcap, nextIndex(), 81, 55, new int[] { 2 }, UPGRADES));
		addSlot(new SlotUpgrade(invcap, nextIndex(), 105, 55, new int[] { 2 }, UPGRADES));
		addSlot(new SlotUpgrade(invcap, nextIndex(), 129, 55, new int[] { 2 }, UPGRADES));
		addSlot(new SlotUpgrade(invcap, nextIndex(), 153, 55, new int[] { 2 }, UPGRADES));
	}

	@Override
	public int[] getHotbarNumbers() {
		return new int[] { 0, 1, 2 };
	}

	@Override
	public int[] getPlayerInvNumbers() {
		return new int[] { 0 };
	}

}
