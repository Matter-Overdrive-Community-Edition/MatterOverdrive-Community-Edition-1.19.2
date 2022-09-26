package matteroverdrive.client.screen;

import matteroverdrive.common.inventory.InventoryTritaniumCrate;
import matteroverdrive.core.screen.types.GenericVanillaScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ScreenTritaniumCrate extends GenericVanillaScreen<InventoryTritaniumCrate> {

	public ScreenTritaniumCrate(InventoryTritaniumCrate pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle, 176 + InventoryTritaniumCrate.OFFSET, 166 + InventoryTritaniumCrate.OFFSET);
	}

	
}
