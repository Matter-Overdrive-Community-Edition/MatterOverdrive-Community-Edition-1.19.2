package matteroverdrive.common.tab;

import matteroverdrive.registry.BlockRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ItemGroupMatterOverdriveDecorative extends CreativeModeTab {

	public ItemGroupMatterOverdriveDecorative(String langKey) {
		super(langKey);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(BlockRegistry.BLOCK_REGULAR_TRITANIUM_PLATING.get().asItem());
	}

}
