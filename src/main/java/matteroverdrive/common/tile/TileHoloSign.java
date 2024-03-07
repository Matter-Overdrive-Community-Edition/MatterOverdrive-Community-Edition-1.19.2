package matteroverdrive.common.tile;

import matteroverdrive.common.block.type.TypeMachine;
import matteroverdrive.common.inventory.InventoryHoloSign;
import matteroverdrive.common.inventory.InventoryMatterAnalyzer;
import matteroverdrive.common.item.ItemUpgrade;
import matteroverdrive.core.capability.types.item.CapabilityInventory;
import matteroverdrive.core.matter.MatterRegister;
import matteroverdrive.core.property.Property;
import matteroverdrive.core.property.PropertyTypes;
import matteroverdrive.core.tile.types.GenericMachineTile;
import matteroverdrive.core.utils.UtilsCapability;
import matteroverdrive.registry.TileRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.TriPredicate;

public class TileHoloSign extends GenericMachineTile {
	public static final int SLOT_COUNT = 1;

	public final Property<CompoundTag> capInventoryProp;

	public TileHoloSign(BlockPos pos, BlockState state) {
		super(TileRegistry.TILE_HOLO_SIGN.get(), pos, state);

		capInventoryProp = this.getPropertyManager().addTrackedProperty(PropertyTypes.NBT
				.create(() -> getInventoryCap().serializeNBT(), tag -> getInventoryCap().deserializeNBT(tag)));

		addInventoryCap(new CapabilityInventory(SLOT_COUNT, false, false)
				.setOwner(this).setValidator(getValidator()).setPropertyManager(capInventoryProp));

		setMenuProvider(new SimpleMenuProvider((id, inv, play) -> new InventoryHoloSign(id, play.getInventory(),
				getInventoryCap(), getCoordsData()), getContainerName(TypeMachine.HOLO_SIGN.id())));

		setTickable();
	}

	public boolean shouldRender() {
		return true;
	}

	public String getText() {
		return "Some text.";
	}

	private static TriPredicate<Integer, ItemStack, CapabilityInventory> getValidator() {
		return (index, stack, cap) -> index == 0 && MatterRegister.INSTANCE.getServerMatterValue(stack) > 0.0
				|| index == 1 && UtilsCapability.hasEnergyCap(stack)
				|| index > 1 && stack.getItem() instanceof ItemUpgrade;
	}

}
