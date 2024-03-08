package matteroverdrive.common.inventory;

import matteroverdrive.common.tile.TileHoloSign;
import matteroverdrive.core.capability.types.item.CapabilityInventory;
import matteroverdrive.core.capability.types.item.PlayerSlotDataWrapper;
import matteroverdrive.core.inventory.GenericInventoryTile;
import matteroverdrive.core.screen.component.ScreenComponentSlot.SlotType;
import matteroverdrive.core.tile.GenericTile;
import matteroverdrive.registry.MenuRegistry;
import matteroverdrive.registry.SoundRegistry;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;

public class InventoryHoloSign extends GenericInventoryTile<TileHoloSign> {

	public static final int OFFSET = 56;

	public InventoryHoloSign(int id, Inventory playerinv) {
		this(id, playerinv, new CapabilityInventory(TileHoloSign.SIZE, true, true), new SimpleContainerData(3));
	}

	public InventoryHoloSign(int id, Inventory playerinv, CapabilityInventory invcap, ContainerData coords) {
		super(MenuRegistry.MENU_HOLO_SIGN.get(), id, playerinv, invcap, coords);
	}

	@Override
	public void init() {
		hasInventorySlots = false;

		super.init();
	}

	@Override
	public void addInvSlots(CapabilityInventory invcap, Inventory playerinv) {
//		for (int j = 0; j < 6; ++j) {
//			for (int k = 0; k < 9; ++k) {
//				this.addSlot(new SlotGeneric(invcap, nextIndex(), 8 + k * 18, 18 + j * 18, new int[] { 0, 1, 2 },
//						SlotType.VANILLA, IconType.NONE));
//			}
//		}
	}

	// Defines location and size of player hotbar and inventory in GUI.
	@Override
	public PlayerSlotDataWrapper getDataWrapper(Player player) {
		return new PlayerSlotDataWrapper(
			8, 84 + OFFSET,
			18, 18,
			45, 137 + OFFSET,
			18, 18,
			SlotType.SMALL, SlotType.SMALL,
			new int[] { 0, 1, 2 }, new int[] { 0, 1, 2 }
		);
	}

}
