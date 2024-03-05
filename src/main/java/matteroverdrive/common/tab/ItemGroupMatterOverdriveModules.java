package matteroverdrive.common.tab;

import matteroverdrive.registry.ItemRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ItemGroupMatterOverdriveModules extends CreativeModeTab {

	public ItemGroupMatterOverdriveModules(String langKey) {
		super(langKey);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(ItemRegistry.ITEM_MATTER_SCANNER.get());
	}

}
