package matteroverdrive.common.tile;

import matteroverdrive.common.block.type.TypeMachine;
import matteroverdrive.common.inventory.InventoryHoloSign;
import matteroverdrive.core.capability.types.item.CapabilityInventory;
import matteroverdrive.core.property.Property;
import matteroverdrive.core.property.PropertyTypes;
import matteroverdrive.core.tile.types.GenericMachineTile;
import matteroverdrive.registry.TileRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class TileHoloSign extends GenericMachineTile {
	public final static int SIZE = 54;

	public String holoText;

	public final Property<CompoundTag> holoSignTextProp;

	public TileHoloSign(BlockPos pos, BlockState state) {
		super(TileRegistry.TILE_HOLO_SIGN.get(), pos, state);

		addCapability(ForgeCapabilities.ITEM_HANDLER,
				new CapabilityInventory(SIZE, false, false).setOwner(this).setInputs(SIZE));

		setMenuProvider(new SimpleMenuProvider(
				(id, inv, play) -> new InventoryHoloSign(id, play.getInventory(),
					exposeCapability(ForgeCapabilities.ITEM_HANDLER), getCoordsData()),
				getContainerName("holo_sign")));
		holoSignTextProp = this.getPropertyManager().addTrackedProperty(
				PropertyTypes.NBT.create(
					() -> createStringTag(holoText),
					item -> holoText = item.getString("text")
				)
			);
	}

	
	private CompoundTag createStringTag(String text) {
		CompoundTag tag = new CompoundTag();

		tag.putString("text", text == null ? "" : text);

		return tag;
	}
	@Override
	public void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);

		CompoundTag additional = new CompoundTag();

		tag.putString("text", this.holoSignTextProp.toString());
		System.out.println("save: " + this.holoSignTextProp.get());

	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		this.holoText = tag.getString("text");
		System.out.println("Load: " + tag.getString("text"));
	}
	
	@Override
	public void onInventoryChange(int slot, CapabilityInventory inv) {
		setChanged();
	}

	public boolean shouldRender() {
		return true;
	}

	public CompoundTag getText() {
		return holoSignTextProp.get();
	}
	@Override
	public CompoundTag getUpdateTag() {
		return this.saveWithFullMetadata();
	}
	public void setText(CompoundTag tag) {
		holoSignTextProp.set(tag);
	}
}
