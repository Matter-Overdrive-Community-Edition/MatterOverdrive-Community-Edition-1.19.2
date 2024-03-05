package matteroverdrive.common.tab;

import matteroverdrive.common.block.OverdriveBlockColors;
import matteroverdrive.common.tile.TileTritaniumCrate;
import matteroverdrive.registry.BlockRegistry;
import matteroverdrive.registry.ItemRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ItemGroupMatterOverdriveDecorative extends CreativeModeTab {

	public ItemGroupMatterOverdriveDecorative(String langKey) {
		super(langKey);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(BlockRegistry.BLOCK_COLORED_TRITANIUM_PLATING.get(OverdriveBlockColors.BLACK).get().asItem());
	}

}
