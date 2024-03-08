package matteroverdrive.common.tile;

import matteroverdrive.common.inventory.InventoryHoloSign;
import matteroverdrive.core.capability.types.item.CapabilityInventory;
import matteroverdrive.core.tile.types.GenericMachineTile;
import matteroverdrive.registry.TileRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class TileHoloSign extends GenericMachineTile {
	public final static int SIZE = 54;

	public TileHoloSign(BlockPos pos, BlockState state) {
		super(TileRegistry.TILE_HOLO_SIGN.get(), pos, state);

		addCapability(ForgeCapabilities.ITEM_HANDLER,
				new CapabilityInventory(SIZE, true, true).setOwner(this).setInputs(SIZE));

		setMenuProvider(new SimpleMenuProvider(
			(id, inv, play) -> new InventoryHoloSign(id, play.getInventory(),
				exposeCapability(ForgeCapabilities.ITEM_HANDLER), getCoordsData()),
			getContainerName("holo_sign")));
	}

	@Override
	public void onInventoryChange(int slot, CapabilityInventory inv) {
		setChanged();
	}

	public boolean shouldRender() {
		return true;
	}

	public String getText() {
		return "Some text.";
	}
}
