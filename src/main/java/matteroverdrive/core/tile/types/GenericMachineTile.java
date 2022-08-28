package matteroverdrive.core.tile.types;

import matteroverdrive.common.item.ItemUpgrade;
import matteroverdrive.core.capability.MatterOverdriveCapabilities;
import matteroverdrive.core.capability.types.energy.CapabilityEnergyStorage;
import matteroverdrive.core.capability.types.item.CapabilityInventory;
import matteroverdrive.core.capability.types.matter.CapabilityMatterStorage;
import matteroverdrive.core.property.Property;
import matteroverdrive.core.property.PropertyTypes;
import matteroverdrive.core.utils.UtilsCapability;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.TriPredicate;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;

public abstract class GenericMachineTile extends GenericSoundTile {

	private boolean running = false;
	private boolean powered = false;
	private double progress = 0.0;
	private double recipeValue = 0.0;

	private final Property<Boolean> runningProp;
	private final Property<Boolean> poweredProp;
	private final Property<Double> progressProp;
	private final Property<Double> recipeValueProp;

	protected GenericMachineTile(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
		runningProp = this.getPropertyManager()
				.addTrackedProperty(PropertyTypes.BOOLEAN.create(() -> running, run -> running = run));
		poweredProp = this.getPropertyManager()
				.addTrackedProperty(PropertyTypes.BOOLEAN.create(() -> powered, pow -> powered = pow));
		progressProp = this.getPropertyManager()
				.addTrackedProperty(PropertyTypes.DOUBLE.create(() -> progress, prog -> progress = prog));
		recipeValueProp = this.getPropertyManager()
				.addTrackedProperty(PropertyTypes.DOUBLE.create(() -> recipeValue, value -> recipeValue = value));
	}

	public boolean setRunning(boolean running) {
		runningProp.set(running);
		return runningProp.isDirtyNoUpdate();
	}

	@Override
	public boolean isRunning() {
		return runningProp.get();
	}

	public boolean setPowered(boolean powered) {
		poweredProp.set(powered);
		return poweredProp.isDirtyNoUpdate();
	}

	public boolean isPowered() {
		return poweredProp.get();
	}

	public boolean setRecipeValue(double value) {
		recipeValueProp.set(value);
		return recipeValueProp.isDirtyNoUpdate();
	}

	public double getRecipeValue() {
		return recipeValueProp.get();
	}

	public boolean setProgress(double progress) {
		progressProp.set(progress);
		return progressProp.isDirtyNoUpdate();
	}

	public boolean incrementProgress(double increment) {
		progressProp.set(getProgress() + increment);
		return progressProp.isDirtyNoUpdate();
	}

	public double getProgress() {
		return progressProp.get();
	}

	@Override
	public double getCurrentMatterStorage() {
		return hasMatterStorageCap() ? getMatterStorageCap().getMaxMatterStored() : 0;
	}

	@Override
	public double getCurrentPowerStorage() {
		return hasEnergyStorageCap() ? getEnergyStorageCap().getMaxEnergyStored() : 0;
	}

	@Override
	public void setMatterStorage(double storage) {
		if (hasMatterStorageCap()) {
			getMatterStorageCap().updateMaxMatterStorage(storage);
		}
	}

	@Override
	public void setPowerStorage(double storage) {
		if (hasEnergyStorageCap()) {
			getEnergyStorageCap().updateMaxEnergyStorage((int) storage);
		}
	}

	public void addEnergyStorageCap(CapabilityEnergyStorage cap) {
		addCapability(CapabilityEnergy.ENERGY, cap);
	}

	public void addMatterStorageCap(CapabilityMatterStorage cap) {
		addCapability(MatterOverdriveCapabilities.MATTER_STORAGE, cap);
	}

	public void addInventoryCap(CapabilityInventory cap) {
		addCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, cap);
	}

	// Serverside only!
	public CapabilityEnergyStorage getEnergyStorageCap() {
		return exposeCapability(CapabilityEnergy.ENERGY);
	}

	public CapabilityMatterStorage getMatterStorageCap() {
		return exposeCapability(MatterOverdriveCapabilities.MATTER_STORAGE);
	}

	public CapabilityInventory getInventoryCap() {
		return exposeCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
	}

	public boolean hasInventoryCap() {
		return hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
	}

	public boolean hasEnergyStorageCap() {
		return hasCapability(CapabilityEnergy.ENERGY);
	}

	public boolean hasMatterStorageCap() {
		return hasCapability(MatterOverdriveCapabilities.MATTER_STORAGE);
	}

	protected static TriPredicate<Integer, ItemStack, CapabilityInventory> machineValidator() {
		return (x, y, i) -> x < i.outputIndex()
				|| x >= i.energySlotsIndex() && x < i.matterSlotsIndex() && UtilsCapability.hasEnergyCap(y)
				|| x >= i.matterSlotsIndex() && x < i.upgradeIndex() && UtilsCapability.hasMatterCap(y)
				|| x >= i.upgradeIndex() && y.getItem() instanceof ItemUpgrade upgrade
						&& i.isUpgradeValid(upgrade.type);
	}

}