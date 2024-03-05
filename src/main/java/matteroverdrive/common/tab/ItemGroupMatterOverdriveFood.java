package matteroverdrive.common.tab;

import matteroverdrive.registry.ItemRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ItemGroupMatterOverdriveFood extends CreativeModeTab {

	public ItemGroupMatterOverdriveFood(String langKey) {
		super(langKey);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(ItemRegistry.ITEM_EARL_GRAY_TEA.get());
	}

}
