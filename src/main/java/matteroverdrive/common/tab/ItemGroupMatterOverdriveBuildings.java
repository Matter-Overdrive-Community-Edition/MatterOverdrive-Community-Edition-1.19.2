package matteroverdrive.common.tab;

import matteroverdrive.registry.ItemRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ItemGroupMatterOverdriveBuildings extends CreativeModeTab {

	public ItemGroupMatterOverdriveBuildings(String langKey) {
		super(langKey);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(ItemRegistry.BUILDING_BASE.get());
	}

}
