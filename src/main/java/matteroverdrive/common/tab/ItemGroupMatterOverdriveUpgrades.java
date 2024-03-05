package matteroverdrive.common.tab;

import matteroverdrive.registry.ItemRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ItemGroupMatterOverdriveUpgrades extends CreativeModeTab {

	public ItemGroupMatterOverdriveUpgrades(String langKey) {
		super(langKey);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(ItemRegistry.ITEM_UPGRADE_BASE.get());
	}

}
