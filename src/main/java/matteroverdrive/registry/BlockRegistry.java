package matteroverdrive.registry;

import java.util.function.Function;
import java.util.function.Supplier;

import matteroverdrive.References;
import matteroverdrive.common.block.BlockColored;
import matteroverdrive.common.block.BlockCustomGlass;
import matteroverdrive.common.block.BlockMachine;
import matteroverdrive.common.block.BlockOverdrive;
import matteroverdrive.common.block.BlockRedstoneToggle;
import matteroverdrive.common.block.BlockTritaniumCrate;
import matteroverdrive.common.block.OverdriveBlockColors;
import matteroverdrive.common.block.cable.types.BlockMatterConduit;
import matteroverdrive.common.block.cable.types.BlockMatterNetworkCable;
import matteroverdrive.common.block.charger.BlockAndroidChargerChild;
import matteroverdrive.common.block.charger.BlockAndroidChargerParent;
import matteroverdrive.common.block.type.TypeMachine;
import matteroverdrive.common.block.type.TypeMatterConduit;
import matteroverdrive.common.block.type.TypeMatterNetworkCable;
import matteroverdrive.common.blockitem.BlockItemColored;
import matteroverdrive.common.blockitem.OverdriveBlockItem;
import matteroverdrive.common.tile.*;
import matteroverdrive.common.tile.TileTritaniumCrate.CrateColors;
import matteroverdrive.common.tile.matter_network.TileDiscManipulator;
import matteroverdrive.common.tile.matter_network.TileMatterAnalyzer;
import matteroverdrive.common.tile.matter_network.TilePatternMonitor;
import matteroverdrive.common.tile.matter_network.TilePatternStorage;
import matteroverdrive.common.tile.matter_network.matter_replicator.TileMatterReplicator;
import matteroverdrive.common.tile.station.TileAndroidStation;
import matteroverdrive.common.tile.transporter.TileTransporter;
import matteroverdrive.core.block.OverdriveBlockProperties;
import matteroverdrive.core.registers.BulkRegister;
import matteroverdrive.core.registers.IBulkRegistryObject;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, References.ID);

	/**
	 * REGISTRY ORDER NOTES:
	 * 
	 * Register ore blocks, decoration blocks, then crates, then machines
	 */

	// Ore Blocks

	public static final RegistryObject<Block> DILITHIUM_ORE = registerBlock("dilithium_ore",
			() -> new BlockOverdrive(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F)),
			false);
	public static final RegistryObject<Block> TRITANIUM_ORE = registerBlock("tritanium_ore",
    		() -> new BlockOverdrive(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F)),
			false);
	public static final RegistryObject<Block> DEEPSLATE_DILITHIUM_ORE = registerBlock("deepslate_dilithium_ore",
			() -> new BlockOverdrive(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F)),
			false);
	public static final RegistryObject<Block> DEEPSLATE_TRITANIUM_ORE = registerBlock("deepslate_tritanium_ore",
    		() -> new BlockOverdrive(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F)),
			false);
	public static final RegistryObject<Block> BLOCK_TRITANIUM = registerBlock("tritanium_block",
			() -> new BlockOverdrive(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F)),
			false);

	// Decoration Blocks

	public static final RegistryObject<Block> BLOCK_REGULAR_TRITANIUM_PLATING = registerBlock("tritanium_plating",
			() -> new BlockOverdrive(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F)), new Item.Properties().tab(References.DECORATIVE), 
			false);
	public static final RegistryObject<Block> BLOCK_MACHINE_HULL = registerBlock("machine_hull",
			() -> new BlockOverdrive(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F)), new Item.Properties().tab(References.DECORATIVE), 
			false);
	public static final RegistryObject<Block> HOLO_MATRIX = registerBlock("holo_matrix",
			() -> new BlockOverdrive(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F)), new Item.Properties().tab(References.DECORATIVE), 
			false);
	public static final RegistryObject<Block> EXHAUST_PLASMA = registerBlock("engine_exhaust_plasma",
			() -> new BlockOverdrive(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F)), new Item.Properties().tab(References.DECORATIVE), 
			false);
	public static final RegistryObject<Block> YELLOW_STRIPES = registerBlock("stripes",
			() -> new BlockOverdrive(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F)), new Item.Properties().tab(References.DECORATIVE), 
			false);
	public static final RegistryObject<Block> TRITANIUM_PLATE_STRIPE = registerBlock("tritanium_plate_stripe",
			() -> new BlockOverdrive(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F)), new Item.Properties().tab(References.DECORATIVE), 
			false);
	public static final RegistryObject<Block> TRITANIUM_RAIL = registerBlock("tritanium_rail",
			() -> new BlockOverdrive(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F)), new Item.Properties().tab(References.DECORATIVE), 
			false);
	public static final RegistryObject<Block> MATTER_TUBE = registerBlock("matter_tube",
			() -> new BlockOverdrive(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F)), new Item.Properties().tab(References.DECORATIVE), 
			false);
	public static final RegistryObject<Block> SOFT_WALL_PLATES = registerBlock("white_plate",
			() -> new BlockOverdrive(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F)), new Item.Properties().tab(References.DECORATIVE), 
			false);
	public static final RegistryObject<Block> TRITANIUM_LAMP = registerBlock("tritanium_lamp",
			() -> new BlockOverdrive(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F).lightLevel((state) -> 15)), new Item.Properties().tab(References.DECORATIVE), 
			false);

	public static final BulkRegister<Block> BLOCK_COLORED_TRITANIUM_PLATING = bulkBlock(
			color -> registerColoredBlock(color.id("tritanium_plating_"),
					() -> new BlockColored(
							Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F),
							((OverdriveBlockColors) color).color),
					false, ((OverdriveBlockColors) color).color),
			OverdriveBlockColors.values());
	public static final BulkRegister<Block> BLOCK_FLOOR_TILE = bulkBlock(
			color -> registerColoredBlock(((OverdriveBlockColors) color).id("floor_tile_"),
					() -> new BlockColored(
							Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F),
							((OverdriveBlockColors) color).color),
					false, ((OverdriveBlockColors) color).color),
			OverdriveBlockColors.values());
	public static final BulkRegister<Block> BLOCK_FLOOR_TILES = bulkBlock(
		color -> registerColoredBlock(((OverdriveBlockColors) color).id("floor_tiles_"),
			() -> new BlockColored(
				Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F),
				((OverdriveBlockColors) color).color),
			false, ((OverdriveBlockColors) color).color),
		OverdriveBlockColors.values());

	public static final RegistryObject<Block> BLOCK_INDUSTRIAL_GLASS = registerBlock("industrial_glass",
			() -> new BlockCustomGlass(0.3F, 0.3F), false);

	public static final RegistryObject<Block> BLOCK_VENT_OPEN = registerBlock("vent_open",
			() -> new BlockRedstoneToggle(
					Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F)), new Item.Properties().tab(References.DECORATIVE), 
			false);

	public static final RegistryObject<Block> BLOCK_VENT_CLOSED = registerBlock("vent_closed",
			() -> new BlockRedstoneToggle(
					Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F)), new Item.Properties().tab(References.DECORATIVE), 
			false);

	// Crates

	public static final BulkRegister<Block> BLOCK_TRITANIUM_CRATES = bulkBlock(crate -> registerBlock(
			((CrateColors) crate).id(),
			() -> new BlockTritaniumCrate(OverdriveBlockProperties
					.from(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F).noOcclusion())
					.setCanBeWaterlogged().setHasFacing(false)),
			true), TileTritaniumCrate.CrateColors.values());

	// Machines

	public static final RegistryObject<Block> BLOCK_SOLAR_PANEL = registerBlock(TypeMachine.SOLAR_PANEL.id(),
			() -> new BlockMachine<>(TileSolarPanel::new, TypeMachine.SOLAR_PANEL,
					TileRegistry.TILE_SOLAR_PANEL),
			true);

	public static final RegistryObject<Block> BLOCK_MATTER_DECOMPOSER = registerBlock(TypeMachine.MATTER_DECOMPOSER.id(),
			() -> new BlockMachine<>(TileMatterDecomposer::new,	TypeMachine.MATTER_DECOMPOSER,
					TileRegistry.TILE_MATTER_DECOMPOSER),
			true);

	public static final RegistryObject<Block> BLOCK_MATTER_RECYCLER = registerBlock(TypeMachine.MATTER_RECYCLER.id(),
			() -> new BlockMachine<>(TileMatterRecycler::new, TypeMachine.MATTER_RECYCLER,
					TileRegistry.TILE_MATTER_RECYCLER),
			true);

	public static final RegistryObject<Block> BLOCK_CHARGER_CHILD = registerBlock("charger_child",
			() -> new BlockAndroidChargerChild(), true);

	public static final RegistryObject<Block> BLOCK_CHARGER = registerBlock(TypeMachine.CHARGER.id(),
			() -> new BlockAndroidChargerParent<>(TileCharger::new, TypeMachine.CHARGER,
					TileRegistry.TILE_CHARGER),
			true);

	public static final RegistryObject<Block> BLOCK_MICROWAVE = registerBlock(TypeMachine.MICROWAVE.id(),
			() -> new BlockMachine<>(TileMicrowave::new, TypeMachine.MICROWAVE,
					TileRegistry.TILE_MICROWAVE),
			true);

	public static final RegistryObject<Block> BLOCK_INSCRIBER = registerBlock(TypeMachine.INSCRIBER.id(),
			() -> new BlockMachine<>(TileInscriber::new, TypeMachine.INSCRIBER,
					TileRegistry.TILE_INSCRIBER),
			true);

	public static final BulkRegister<Block> BLOCK_MATTER_CONDUITS = bulkBlock(
			conduit -> registerBlock(((TypeMatterConduit) conduit).id(),
					() -> new BlockMatterConduit((TypeMatterConduit) conduit), true),
			TypeMatterConduit.values());

	public static final RegistryObject<Block> BLOCK_TRANSPORTER = registerBlock(TypeMachine.TRANSPORTER.id(),
			() -> new BlockMachine<>(TileTransporter::new, TypeMachine.TRANSPORTER,
					TileRegistry.TILE_TRANSPORTER),
			true);

	public static final RegistryObject<Block> BLOCK_SPACETIME_ACCELERATOR = registerBlock(
			TypeMachine.SPACETIME_ACCELERATOR.id(),
			() -> new BlockMachine<>(TileSpacetimeAccelerator::new,
					TypeMachine.SPACETIME_ACCELERATOR, TileRegistry.TILE_SPACETIME_ACCELERATOR),
			true);

	public static final BulkRegister<Block> BLOCK_MATTER_NETWORK_CABLES = bulkBlock(
			cable -> registerBlock(cable.id(), () -> new BlockMatterNetworkCable((TypeMatterNetworkCable) cable), true),
			TypeMatterNetworkCable.values());

	public static final RegistryObject<Block> BLOCK_CHUNKLOADER = registerBlock(TypeMachine.CHUNKLOADER.id(),
			() -> new BlockMachine<>(TileChunkloader::new, TypeMachine.CHUNKLOADER,
					TileRegistry.TILE_CHUNKLOADER),
			true);

	public static final RegistryObject<Block> BLOCK_MATTER_ANALYZER = registerBlock(TypeMachine.MATTER_ANALYZER.id(),
			() -> new BlockMachine<>(TileMatterAnalyzer::new, TypeMachine.MATTER_ANALYZER,
					TileRegistry.TILE_MATTER_ANALYZER),
			true);

	public static final RegistryObject<Block> BLOCK_PATTERN_STORAGE = registerBlock(TypeMachine.PATTERN_STORAGE.id(),
			() -> new BlockMachine<>(TilePatternStorage::new, TypeMachine.PATTERN_STORAGE,
					TileRegistry.TILE_PATTERN_STORAGE),
			true);

	public static final RegistryObject<Block> BLOCK_PATTERN_MONITOR = registerBlock(TypeMachine.PATTERN_MONITOR.id(),
			() -> new BlockMachine<>(TilePatternMonitor::new, TypeMachine.PATTERN_MONITOR,
					TileRegistry.TILE_PATTERN_MONITOR),
			true);

	public static final RegistryObject<Block> BLOCK_MATTER_REPLICATOR = registerBlock(
			TypeMachine.MATTER_REPLICATOR.id(), () -> new BlockMachine<>(TileMatterReplicator::new,
					TypeMachine.MATTER_REPLICATOR, TileRegistry.TILE_MATTER_REPLICATOR),
			true);

	public static final RegistryObject<Block> BLOCK_ANDROID_STATION = registerBlock("android_station",
			() -> new BlockMachine<>(TileAndroidStation::new, TypeMachine.ANDROID_STATION,
					TileRegistry.TILE_ANDROID_STATION),
			true);
	
	public static final RegistryObject<Block> BLOCK_DISC_MANIPULATOR = registerBlockNoItem(TypeMachine.DISC_MANIPULATOR.id(),
			() -> new BlockMachine<>(TileDiscManipulator::new, TypeMachine.DISC_MANIPULATOR, TileRegistry.TILE_DISC_MANIPULATOR),
			true);

	public static final RegistryObject<Block> BLOCK_DECORATIVE_BEAMS = registerBlock("decorative.beams",
		() -> new BlockOverdrive(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F)), new Item.Properties().tab(References.DECORATIVE), 
		false);

	public static final RegistryObject<Block> BLOCK_DECORATIVE_CARBON_FIBER_PLATE = registerBlock("decorative.carbon_fiber_plate",
		() -> new BlockOverdrive(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F)), new Item.Properties().tab(References.DECORATIVE), 
		false);

	public static final RegistryObject<Block> BLOCK_DECORATIVE_CLEAN = registerBlock("decorative.clean",
		() -> new BlockOverdrive(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F)), new Item.Properties().tab(References.DECORATIVE), 
		false);

	public static final RegistryObject<Block> BLOCK_DECORATIVE_COILS = registerBlock("decorative.coils",
		() -> new BlockOverdrive(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F)), new Item.Properties().tab(References.DECORATIVE), 
		false);

	public static final RegistryObject<Block> BLOCK_HOLO_SIGN = registerBlock(TypeMachine.HOLO_SIGN.id(),
		() -> new BlockMachine<>(TileHoloSign::new, TypeMachine.HOLO_SIGN,
			TileRegistry.TILE_HOLO_SIGN),
		true);

	// For crafting only now.
	public static final RegistryObject<Block> BLOCK_NETWORK_SWITCH = registerBlock("network_switch",
		() -> new BlockOverdrive(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1F, 100F)),
		false);

	public static final RegistryObject<Block> BLOCK_STAR_MAP = registerBlock(
		TypeMachine.STAR_MAP.id(),
		() -> new BlockMachine<>(TileStarMap::new,
			TypeMachine.STAR_MAP, TileRegistry.TILE_STAR_MAP),
		true);

	public static final RegistryObject<Block> BLOCK_WEAPON_STATION = registerBlock(
		TypeMachine.WEAPON_STATION.id(),
		() -> new BlockMachine<>(TileStarMap::new,
			TypeMachine.WEAPON_STATION, TileRegistry.TILE_WEAPON_STATION),
		true);

	// Functional Methods

	private static RegistryObject<Block> registerBlock(String name, Supplier<Block> supplier, boolean shiftTip) {
		return registerBlock(name, supplier, new Item.Properties().tab(References.MAIN), shiftTip);
	}

	private static RegistryObject<Block> registerBlockNoItem(String name, Supplier<Block> supplier, boolean shiftTip) {
		return registerBlock(name, supplier, new Item.Properties(), shiftTip);
	}

	private static RegistryObject<Block> registerBlock(String name, Supplier<Block> supplier, Item.Properties properties, boolean shiftTip) {
		RegistryObject<Block> block = BLOCKS.register(name, supplier);
		ItemRegistry.ITEMS.register(name, () -> new OverdriveBlockItem(block.get(), properties, shiftTip));
		return block;
	}

	private static RegistryObject<Block> registerColoredBlock(String name, Supplier<Block> supplier, boolean shiftTip,
			int color) {
		return registerColoredBlock(name, supplier, new Item.Properties().tab(References.DECORATIVE), shiftTip, color);
	}

	private static RegistryObject<Block> registerColoredBlock(String name, Supplier<Block> supplier,Item.Properties properties, boolean shiftTip, int color) {
		RegistryObject<Block> block = BLOCKS.register(name, supplier);
		ItemRegistry.ITEMS.register(name, () -> new BlockItemColored(block.get(), properties, shiftTip, color));
		return block;
	}

	private static BulkRegister<Block> bulkBlock(Function<IBulkRegistryObject, RegistryObject<Block>> factory,
			IBulkRegistryObject[] bulkValues) {
		return new BulkRegister<>(factory, bulkValues);
	}
}
