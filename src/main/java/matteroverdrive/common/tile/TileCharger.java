package matteroverdrive.common.tile;

import matteroverdrive.common.block.type.TypeMachine;
import matteroverdrive.common.inventory.InventoryCharger;
import matteroverdrive.core.capability.types.energy.CapabilityEnergyStorage;
import matteroverdrive.core.capability.types.item.CapabilityInventory;
import matteroverdrive.core.tile.types.GenericUpgradableTile;
import matteroverdrive.registry.TileRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class TileCharger extends GenericUpgradableTile {

	public static final int SLOT_COUNT = 2;

	public static final int CHARGE_RATE = 512;
	private static final int ENERGY_STORAGE = 512000;
	private static final int DEFAULT_RADIUS = 8;

	private boolean running = false;
	
	public boolean clientRunning;

	public TileCharger(BlockPos pos, BlockState state) {
		super(TileRegistry.TILE_CHARGER.get(), pos, state);
		currentPowerUsage = CHARGE_RATE;
		currentRange = DEFAULT_RADIUS;
		addInventoryCap(new CapabilityInventory(SLOT_COUNT, false, false).setUpgrades(2).setOwner(this)
				.setValidator(machineValidator()).setValidUpgrades(InventoryCharger.UPGRADES));
		addEnergyStorageCap(new CapabilityEnergyStorage(ENERGY_STORAGE, true, false).setOwner(this)
				.setDefaultDirections(state, new Direction[] { Direction.DOWN, Direction.NORTH }, null));
		setMenuProvider(new SimpleMenuProvider(
				(id, inv, play) -> new InventoryCharger(id, play.getInventory(), getInventoryCap(), getCoordsData()),
				getContainerName(TypeMachine.CHARGER.id())));
		setHasMenuData();
		setTickable();
	}

	@Override
	public void tickServer() {
		// TODO implement
	}

	@Override
	public void getMenuData(CompoundTag tag) {
		tag.putBoolean("running", running);
	}

	@Override
	public void readMenuData(CompoundTag tag) {
		clientRunning = tag.getBoolean("running");
	}

	@Override
	protected void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);

		CompoundTag additional = new CompoundTag();
		additional.putDouble("usage", currentPowerUsage);
		additional.putDouble("radius", currentRange);
		additional.putBoolean("running", running);

		tag.put("additional", additional);
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);

		CompoundTag additional = tag.getCompound("additional");
		currentPowerUsage = additional.getInt("usage");
		currentRange = additional.getInt("radius");
		running = additional.getBoolean("runnning");
	}

	@Override
	public AABB getRenderBoundingBox() {
		return super.getRenderBoundingBox().inflate(2);
	}

	@Override
	public double getDefaultPowerStorage() {
		return ENERGY_STORAGE;
	}

	@Override
	public double getDefaultPowerUsage() {
		return CHARGE_RATE;
	}

	@Override
	public double getDefaultRange() {
		return DEFAULT_RADIUS;
	}

	@Override
	public double getCurrentPowerStorage() {
		return getEnergyStorageCap().getMaxEnergyStored();
	}

	@Override
	public void setPowerStorage(double storage) {
		getEnergyStorageCap().updateMaxEnergyStorage((int) storage);
	}

}
