package matteroverdrive.common.tile;

import matteroverdrive.common.block.type.TypeMachine;
import matteroverdrive.common.inventory.InventorySolarPanel;
import matteroverdrive.core.capability.types.energy.CapabilityEnergyStorage;
import matteroverdrive.core.capability.types.item.CapabilityInventory;
import matteroverdrive.core.tile.types.GenericUpgradableTile;
import matteroverdrive.core.utils.UtilsTile;
import matteroverdrive.registry.TileRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class TileSolarPanel extends GenericUpgradableTile {

	public static final int SLOT_COUNT = 2;
	public static final int GENERATION = 5;

	private static final int ENERGY_STORAGE = 64000;

	private boolean generating = false;

	public boolean clientGenerating;

	public TileSolarPanel(BlockPos pos, BlockState state) {
		super(TileRegistry.TILE_SOLAR_PANEL.get(), pos, state);
		addInventoryCap(new CapabilityInventory(SLOT_COUNT, false, false).setUpgrades(SLOT_COUNT).setOwner(this)
				.setValidator(machineValidator()).setValidUpgrades(InventorySolarPanel.UPGRADES));
		addEnergyStorageCap(new CapabilityEnergyStorage(ENERGY_STORAGE, false, true).setOwner(this)
				.setDefaultDirections(state, null, new Direction[] { Direction.DOWN }));
		setMenuProvider(new SimpleMenuProvider(
				(id, inv, play) -> new InventorySolarPanel(id, play.getInventory(), getInventoryCap(), getCoordsData()),
				getContainerName(TypeMachine.SOLAR_PANEL.id())));
		setTickable();
		setHasMenuData();
	}

	@Override
	public void tickServer() {
		if (canRun()) {
			if (ticks % 5 == 0) {
				Level world = getLevel();
				generating = world.isDay() && world.canSeeSky(getBlockPos());
			}
			if (generating) {
				CapabilityEnergyStorage energy = getEnergyStorageCap();
				energy.giveEnergy((int) (GENERATION * saMultiplier));
			}
			UtilsTile.outputEnergy(this);
			setChanged();
		} else {
			generating = false;
		}
	}

	@Override
	public void getMenuData(CompoundTag tag) {
		tag.putBoolean("generating", generating);
	}

	public void readMenuData(CompoundTag tag) {
		clientGenerating = tag.getBoolean("generating");
	}

	@Override
	public double getDefaultPowerStorage() {
		return ENERGY_STORAGE;
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
