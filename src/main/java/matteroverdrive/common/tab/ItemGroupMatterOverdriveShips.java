package matteroverdrive.common.tab;

import matteroverdrive.registry.ItemRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ItemGroupMatterOverdriveShips extends CreativeModeTab {

	public ItemGroupMatterOverdriveShips(String langKey) {
		super(langKey);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(ItemRegistry.SHIP_COLONIZER.get());
	}

}
