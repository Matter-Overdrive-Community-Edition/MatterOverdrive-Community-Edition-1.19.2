package matteroverdrive.common.tile;

import matteroverdrive.common.inventory.InventoryHoloSign;
import matteroverdrive.core.capability.types.item.CapabilityInventory;
import matteroverdrive.core.property.Property;
import matteroverdrive.core.property.PropertyTypes;
import matteroverdrive.core.tile.types.GenericMachineTile;
import matteroverdrive.registry.TileRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class TileHoloSign extends GenericMachineTile {
	public final static int SIZE = 54;

	public String holoText;

	public final Property<String> holoSignTextProp;

	public TileHoloSign(BlockPos pos, BlockState state) {
		super(TileRegistry.TILE_HOLO_SIGN.get(), pos, state);

		addCapability(ForgeCapabilities.ITEM_HANDLER,
				new CapabilityInventory(SIZE, false, false).setOwner(this).setInputs(SIZE));

		setMenuProvider(new SimpleMenuProvider(
			(id, inv, play) -> new InventoryHoloSign(id, play.getInventory(),
				exposeCapability(ForgeCapabilities.ITEM_HANDLER), getCoordsData()),
			getContainerName("holo_sign")));

		holoSignTextProp = this.getPropertyManager().addTrackedProperty(
			PropertyTypes.STRING.create(
				() -> holoText,
				item -> holoText = item
			)
		);

		setTickable();
	}

	@Override
	public void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);

		tag.putString("text", this.holoSignTextProp.getOrElse(""));
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);

		this.holoText = tag.getString("text");
	}

	@Override
	public void onInventoryChange(int slot, CapabilityInventory inv) {
		setChanged();
	}

	public boolean shouldRender() {
		return true;
	}

	public String getText() {
		return holoSignTextProp.getOrElse("");
	}

	@Override
	public CompoundTag getUpdateTag() {
		return this.saveWithFullMetadata();
	}

	// Server side ONLY.
	public void setText(String tag) {
		holoSignTextProp.set(tag);

		setChanged();
	}

	// Client side ONLY.
	public void setClientText(String msg) {
		holoSignTextProp.set(msg);
	}
}
