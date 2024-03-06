package matteroverdrive.datagen.server;

import matteroverdrive.References;
import matteroverdrive.common.block.OverdriveBlockColors;
import matteroverdrive.common.block.type.TypeMatterConduit;
import matteroverdrive.common.block.type.TypeMatterNetworkCable;
import matteroverdrive.common.item.ItemUpgrade;
import matteroverdrive.common.item.tools.ItemMatterContainer;
import matteroverdrive.common.item.tools.electric.ItemBattery;
import matteroverdrive.common.item.type.TypeIsolinearCircuit;
import matteroverdrive.common.recipe.RecipeInit;
import matteroverdrive.common.tags.OverdriveTags;
import matteroverdrive.common.tile.TileTritaniumCrate;
import matteroverdrive.datagen.utils.recipe.AbstractOverdriveFinishedRecipe.RecipeCategory;
import matteroverdrive.datagen.utils.recipe.FinishedRecipeItemOutput;
import matteroverdrive.datagen.utils.recipe.OverdriveShapedCraftingRecipe;
import matteroverdrive.datagen.utils.recipe.OverdriveShapelessCraftingRecipe;
import matteroverdrive.registry.BlockRegistry;
import matteroverdrive.registry.ItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class OverdriveRecipeProvider extends RecipeProvider {

	private static final String INSCRIBER_LOC = "inscriber";

	public OverdriveRecipeProvider(DataGenerator gen) {
		super(gen);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		addShapedCraftingRecipes(consumer);
		addInscriberRecipes(consumer);
	}

	private void addShapedCraftingRecipes(Consumer<FinishedRecipe> consumer) {

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER1).get(), 1)
			.addPattern("I")
			.addPattern("R")
			.addPattern("G")
			.addKey('I', Ingredient.of(Tags.Items.INGOTS_IRON))
			.addKey('R', Ingredient.of(Tags.Items.DUSTS_REDSTONE))
			.addKey('G', Ingredient.of(Tags.Items.GLASS))
			.complete(References.ID, "isolinear_circuit_tier1", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_ANDROID_STATION.get().asItem(), 1)
			.addPattern("THA")
			.addPattern("2F3")
			.addPattern("GMR")
			.addKey('A', Ingredient.of(ItemRegistry.ITEM_ROGUE_ANDROID_ARMS.get()))
			.addKey('2', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER1).get()))
			.addKey('R', Ingredient.of(Tags.Items.DUSTS_REDSTONE))
			.addKey('3', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER2).get()))
			.addKey('T', Ingredient.of(ItemRegistry.ITEM_ROGUE_ANDROID_CHEST.get()))
			.addKey('F', Ingredient.of(ItemRegistry.ITEM_FORCEFIELD_EMITTER.get()))
			.addKey('G', Ingredient.of(Tags.Items.DUSTS_GLOWSTONE))
			.addKey('H', Ingredient.of(ItemRegistry.ITEM_ROGUE_ANDROID_HEAD.get()))
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_MACHINE_CASING.get()))
			.complete(References.ID, "android_station", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_BATTERIES.get(ItemBattery.BatteryType.REGULAR).get(), 1)
			.addPattern(" R ")
			.addPattern("TGT")
			.addPattern("TDT")
			.addKey('R', Ingredient.of(Tags.Items.DUSTS_REDSTONE))
			.addKey('T', Ingredient.of(OverdriveTags.Items.INGOT_TRITANIUM))
			.addKey('D', Ingredient.of(OverdriveTags.Items.GEM_DILITHIUM))
			.addKey('G', Ingredient.of(Tags.Items.INGOTS_GOLD))
			.complete(References.ID, "battery_regular", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_CHARGER.get().asItem(), 1)
			.addPattern(" F ")
			.addPattern("EDR")
			.addPattern("BMB")
			.addKey('B', Ingredient.of(ItemRegistry.ITEM_BATTERIES.get(ItemBattery.BatteryType.HIGHCAPACITY).get()))
			.addKey('R', Ingredient.of(Items.REPEATER))
			.addKey('D', Ingredient.of(OverdriveTags.Items.GEM_DILITHIUM))
			.addKey('E', Ingredient.of(Items.ENDER_EYE))
			.addKey('F', Ingredient.of(ItemRegistry.ITEM_FORCEFIELD_EMITTER.get()))
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_MACHINE_CASING.get()))
			.complete(References.ID, "block_charger", consumer);

		// Contract Market

		OverdriveShapelessCraftingRecipe.start(ItemRegistry.ITEM_DATAPAD.get().asItem(), 1)
			.addIngredient(Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER1).get()))
			.addIngredient(Ingredient.of(Items.BOOK))
			.complete(References.ID, "data_pad", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_MATTER_DECOMPOSER.get().asItem(), 1)
			.addPattern("TCT")
			.addPattern("S S")
			.addPattern("NTM")
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER3).get()))
			.addKey('S', Ingredient.of(Items.STICKY_PISTON))
			.addKey('T', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_ME_CONVERSION_MATRIX.get()))
			.addKey('N', Ingredient.of(ItemRegistry.ITEM_INTEGRATION_MATRIX.get()))
			.complete(References.ID, "block_decomposer", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_DECORATIVE_BEAMS.get().asItem(), 6)
			.addPattern("#T#")
			.addPattern("#T#")
			.addPattern("#T#")
			.addKey('#', Ingredient.of(BlockRegistry.BLOCK_REGULAR_TRITANIUM_PLATING.get().asItem()))
			.addKey('T', Ingredient.of(OverdriveTags.Items.NUGGET_TRITANIUM))
			.complete(References.ID, "decorative.beams", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_DECORATIVE_CARBON_FIBER_PLATE.get().asItem(), 8)
			.addPattern("###")
			.addPattern("#C#")
			.addPattern("###")
			.addKey('#', Ingredient.of(BlockRegistry.BLOCK_REGULAR_TRITANIUM_PLATING.get().asItem()))
			.addKey('C', Ingredient.of(Items.COAL))
			.complete(References.ID, "decorative.carbon_fiber_plate", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_DECORATIVE_CLEAN.get().asItem(), 8)
			.addPattern("SS ")
			.addPattern("SS ")
			.addPattern("   ")
			.addKey('S', Ingredient.of(OverdriveTags.Items.INGOT_TRITANIUM))
			.complete(References.ID, "decorative.clean", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_DECORATIVE_COILS.get().asItem(), 12)
			.addPattern("###")
			.addPattern("#C#")
			.addPattern("###")
			.addKey('#', Ingredient.of(BlockRegistry.BLOCK_REGULAR_TRITANIUM_PLATING.get().asItem()))
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_SUPERCONDUCTOR_MAGNET.get()))
			.complete(References.ID, "decorative.coils", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_FORCEFIELD_EMITTER.get(), 1)
			.addPattern("CGC")
			.addPattern("CDC")
			.addPattern("P1P")
			.addKey('P', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
			.addKey('1', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER1).get()))
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_SUPERCONDUCTOR_MAGNET.get()))
			.addKey('D', Ingredient.of(OverdriveTags.Items.GEM_DILITHIUM))
			.addKey('G', Ingredient.of(Tags.Items.GLASS))
			.complete(References.ID, "forcefield_emitter", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_H_COMPENSATOR.get(), 1)
			.addPattern(" M ")
			.addPattern("CPC")
			.addPattern("DED")
			.addKey('P', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER2).get()))
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER1).get()))
			.addKey('D', Ingredient.of(OverdriveTags.Items.GEM_DILITHIUM))
			.addKey('E', Ingredient.of(Items.ENDER_EYE))
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_MACHINE_CASING.get()))
			.complete(References.ID, "h_compensator", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_BATTERIES.get(ItemBattery.BatteryType.HIGHCAPACITY).get(), 1)
			.addPattern(" P ")
			.addPattern("DBD")
			.addPattern(" P ")
			.addKey('P', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
			.addKey('B', Ingredient.of(ItemRegistry.ITEM_BATTERIES.get(ItemBattery.BatteryType.REGULAR).get()))
			.addKey('D', Ingredient.of(OverdriveTags.Items.GEM_DILITHIUM))
			.complete(References.ID, "hc_battery", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_MATTER_CONDUITS.get(TypeMatterConduit.HEAVY).get().asItem(), 8)
			.addPattern("RMR")
			.addPattern("TMT")
			.addPattern("RMR")
			.addKey('R', Ingredient.of(Tags.Items.DUSTS_REDSTONE))
			.addKey('T', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_SUPERCONDUCTOR_MAGNET.get()))
			.complete(References.ID, "heavy_matter_pipe", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_MICROWAVE.get().asItem(), 1)
			.addPattern("PMP")
			.addPattern("SGS")
			.addPattern("PCP")
			.addKey('P', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER1).get()))
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_MACHINE_CASING.get()))
			.addKey('G', Ingredient.of(Tags.Items.GLASS))
			.addKey('S', Ingredient.of(ItemRegistry.ITEM_SUPERCONDUCTOR_MAGNET.get()))
			.complete(References.ID, "microwave", consumer);

		// Not yet implemented.
//		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_HOLO_SIGHTS_BASE.get(TypeMatterConduit.HEAVY).get().asItem(), 1)
//			.addPattern("   ")
//			.addPattern("T T")
//			.addPattern("TTT")
//			.addKey('T', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
//			.complete(References.ID, "heavy_matter_pipe", consumer);

//		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_HOLO_SIGN.get().asItem(), 1)
//			.addPattern("GGG")
//			.addPattern("g0g")
//			.addPattern(" T ")
//			.addKey('0', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER1).get()))
//			.addKey('T', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
//			.addKey('G', Ingredient.of(Tags.Items.GLASS_COLORLESS)) // Assuming BLOCKGLASS refers to colorless glass
//			.addKey('g', Ingredient.of(Tags.Items.DUSTS_GLOWSTONE)) // Assuming DUSTGLOWSTONE refers to glowstone dust
//			.complete(References.ID, "holo_sign", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_INDUSTRIAL_GLASS.get().asItem(), 4)
			.addPattern(" T ")
			.addPattern("TMT")
			.addPattern(" T ")
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
			.addKey('T', Ingredient.of(Tags.Items.GLASS_COLORLESS)) // Assuming BLOCKGLASS refers to colorless glass
			.complete(References.ID, "industrial_glass", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_INSCRIBER.get().asItem(), 1)
			.addPattern("IDI")
			.addPattern("TPT")
			.addPattern("RMR")
			.addKey('P', Ingredient.of(Items.PISTON))
			.addKey('R', Ingredient.of(Tags.Items.DUSTS_REDSTONE))
			.addKey('D', Ingredient.of(OverdriveTags.Items.GEM_DILITHIUM))
			.addKey('T', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
			.addKey('I', Ingredient.of(Tags.Items.INGOTS_IRON))
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_MACHINE_CASING.get()))
			.complete(References.ID, "inscriber", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_INTEGRATION_MATRIX.get(), 1)
			.addPattern(" M ")
			.addPattern("GPG")
			.addPattern("DED")
			.addKey('P', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER2).get()))
			.addKey('D', Ingredient.of(OverdriveTags.Items.GEM_DILITHIUM))
			.addKey('E', Ingredient.of(Tags.Items.ENDER_PEARLS))
			.addKey('G', Ingredient.of(Tags.Items.GLASS_COLORLESS)) // Assuming BLOCKGLASS refers to colorless glass
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_MACHINE_CASING.get()))
			.complete(References.ID, "integration_matrix", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_ION_SNIPER.get(), 1)
			.addPattern("ICI")
			.addPattern("SPP")
			.addPattern(" HB")
			.addKey('P', Ingredient.of(ItemRegistry.ITEM_PLASMA_CORE.get()))
			.addKey('B', Ingredient.of(ItemRegistry.ITEM_BATTERIES.get(ItemBattery.BatteryType.REGULAR).get()))
			.addKey('S', Ingredient.of(ItemRegistry.ITEM_WEAPON_RECEIVER.get()))
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER4).get()))
			.addKey('H', Ingredient.of(ItemRegistry.ITEM_WEAPON_HANDLE.get()))
			.addKey('I', Ingredient.of(Tags.Items.INGOTS_IRON))
			.complete(References.ID, "ion_sniper", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_MACHINE_CASING.get(), 1)
			.addPattern(" T ")
			.addPattern("I I")
			.addPattern("GRG")
			.addKey('R', Ingredient.of(Tags.Items.DUSTS_REDSTONE))
			.addKey('T', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
			.addKey('G', Ingredient.of(Tags.Items.INGOTS_GOLD))
			.addKey('I', Ingredient.of(OverdriveTags.Items.INGOT_TRITANIUM))
			.complete(References.ID, "machine_casing", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_MACHINE_HULL.get().asItem(), 1)
			.addPattern(" T ")
			.addPattern("T T")
			.addPattern(" T ")
			.addKey('T', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
			.complete(References.ID, "machine_hull", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_MATTER_CONTAINERS.get(ItemMatterContainer.ContainerType.REGULAR).get(), 4)
			.addPattern("TMT")
			.addPattern(" T ")
			.addKey('T', Ingredient.of(OverdriveTags.Items.INGOT_TRITANIUM))
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_SUPERCONDUCTOR_MAGNET.get()))
			.complete(References.ID, "matter_container", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_MATTER_CONDUITS.get(TypeMatterConduit.REGULAR).get().asItem(), 8)
			.addPattern(" G ")
			.addPattern("IMI")
			.addPattern(" G ")
			.addKey('G', Ingredient.of(Tags.Items.GLASS_COLORLESS)) // Assuming BLOCKGLASS refers to colorless glass
			.addKey('I', Ingredient.of(Tags.Items.INGOTS_IRON))
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_SUPERCONDUCTOR_MAGNET.get()))
			.complete(References.ID, "matter_pipe", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_MATTER_RECYCLER.get().asItem(), 1)
			.addPattern("T T")
			.addPattern("1P2")
			.addPattern("NTM")
			.addKey('P', Ingredient.of(Items.PISTON))
			.addKey('1', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER1).get()))
			.addKey('2', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER2).get()))
			.addKey('T', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_ME_CONVERSION_MATRIX.get()))
			.addKey('N', Ingredient.of(ItemRegistry.ITEM_INTEGRATION_MATRIX.get()))
			.complete(References.ID, "matter_recycler", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_MATTER_ANALYZER.get().asItem(), 1)
			.addPattern(" C ")
			.addPattern("PMF")
			.addPattern("ONO")
			.addKey('P', Ingredient.of(ItemRegistry.ITEM_PATTERN_DRIVE.get()))
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER3).get()))
			.addKey('F', Ingredient.of(ItemRegistry.ITEM_NETWORK_FLASH_DRIVE.get()))
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_ME_CONVERSION_MATRIX.get()))
			.addKey('N', Ingredient.of(ItemRegistry.ITEM_INTEGRATION_MATRIX.get()))
			.addKey('O', Ingredient.of(Tags.Items.STORAGE_BLOCKS_IRON))
			.complete(References.ID, "matter_analyzer", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_MATTER_SCANNER.get(), 1)
			.addPattern("III")
			.addPattern("GDG")
			.addPattern("IRI")
			.addKey('R', Ingredient.of(Tags.Items.DUSTS_REDSTONE))
			.addKey('D', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER3).get()))
			.addKey('G', Ingredient.of(Tags.Items.INGOTS_GOLD))
			.addKey('I', Ingredient.of(Tags.Items.INGOTS_IRON))
			.complete(References.ID, "matter_scanner", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_ME_CONVERSION_MATRIX.get(), 1)
			.addPattern("EIE")
			.addPattern("CDC")
			.addPattern("EIE")
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER2).get()))
			.addKey('D', Ingredient.of(OverdriveTags.Items.GEM_DILITHIUM))
			.addKey('E', Ingredient.of(Tags.Items.ENDER_PEARLS))
			.addKey('I', Ingredient.of(Tags.Items.INGOTS_IRON))
			.complete(References.ID, "me_conversion_matrix", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_NETWORK_FLASH_DRIVE.get(), 1)
			.addPattern("RCR")
			.addKey('R', Ingredient.of(Tags.Items.DUSTS_REDSTONE))
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER1).get()))
			.complete(References.ID, "network_flash_drive", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_MATTER_NETWORK_CABLES.get(TypeMatterNetworkCable.REGULAR).get().asItem(), 16)
			.addPattern("IGI")
			.addPattern("BCB")
			.addPattern("IGI")
			.addKey('B', Ingredient.of(Tags.Items.INGOTS_GOLD))
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER1).get()))
			.addKey('G', Ingredient.of(Tags.Items.GLASS_COLORLESS))
			.addKey('I', Ingredient.of(Tags.Items.INGOTS_IRON))
			.complete(References.ID, "network_pipe", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_OMNI_TOOL.get(), 1)
			.addPattern("IFC")
			.addPattern("SPI")
			.addPattern(" BH")
			.addKey('P', Ingredient.of(ItemRegistry.ITEM_PLASMA_CORE.get()))
			.addKey('B', Ingredient.of(ItemRegistry.ITEM_BATTERIES.get(ItemBattery.BatteryType.REGULAR).get()))
			.addKey('S', Ingredient.of(ItemRegistry.ITEM_WEAPON_RECEIVER.get()))
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER3).get()))
			.addKey('F', Ingredient.of(ItemRegistry.ITEM_FORCEFIELD_EMITTER.get()))
			.addKey('H', Ingredient.of(ItemRegistry.ITEM_WEAPON_HANDLE.get()))
			.addKey('I', Ingredient.of(Tags.Items.INGOTS_IRON))
			.complete(References.ID, "omni_tool", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_PATTERN_DRIVE.get(), 1)
			.addPattern(" E ")
			.addPattern("RMR")
			.addPattern(" C ")
			.addKey('R', Ingredient.of(Tags.Items.DUSTS_REDSTONE))
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER1).get()))
			.addKey('E', Ingredient.of(Tags.Items.ENDER_PEARLS))
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_MACHINE_CASING.get()))
			.complete(References.ID, "pattern_drive", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_PATTERN_MONITOR.get().asItem(), 1)
			.addPattern(" H ")
			.addPattern("1N1")
			.addPattern(" F ")
			.addKey('1', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER1).get()))
			.addKey('F', Ingredient.of(ItemRegistry.ITEM_NETWORK_FLASH_DRIVE.get()))
			.addKey('H', Ingredient.of(BlockRegistry.BLOCK_HOLO_SIGN.get()))
			.addKey('N', Ingredient.of(BlockRegistry.BLOCK_NETWORK_SWITCH.get()))
			.complete(References.ID, "pattern_monitor", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_PATTERN_STORAGE.get().asItem(), 1)
			.addPattern("B3B")
			.addPattern("TCT")
			.addPattern("2M1")
			.addKey('1', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER1).get()))
			.addKey('B', Ingredient.of(ItemTags.WOOL))
			.addKey('2', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER2).get()))
			.addKey('3', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER3).get()))
			.addKey('C', Ingredient.of(Tags.Items.CHESTS_WOODEN))
			.addKey('T', Ingredient.of(OverdriveTags.Items.INGOT_TRITANIUM))
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_MACHINE_CASING.get()))
			.complete(References.ID, "pattern_storage", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_PHASER.get(), 1)
			.addPattern("IGI")
			.addPattern("IPH")
			.addPattern("WCW")
			.addKey('P', Ingredient.of(ItemRegistry.ITEM_PLASMA_CORE.get()))
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER3).get()))
			.addKey('G', Ingredient.of(Tags.Items.GLASS_COLORLESS))
			.addKey('W', Ingredient.of(ItemTags.WOOL))
			.addKey('H', Ingredient.of(ItemRegistry.ITEM_WEAPON_HANDLE.get()))
			.addKey('I', Ingredient.of(Tags.Items.INGOTS_IRON))
			.complete(References.ID, "phaser", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_PHASER_RIFLE.get(), 1)
			.addPattern("III")
			.addPattern("SPC")
			.addPattern("WHB")
			.addKey('P', Ingredient.of(ItemRegistry.ITEM_PLASMA_CORE.get()))
			.addKey('B', Ingredient.of(ItemRegistry.ITEM_BATTERIES.get(ItemBattery.BatteryType.REGULAR).get()))
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER3).get()))
			.addKey('S', Ingredient.of(ItemRegistry.ITEM_WEAPON_RECEIVER.get()))
			.addKey('W', Ingredient.of(ItemTags.WOOL))
			.addKey('H', Ingredient.of(ItemRegistry.ITEM_WEAPON_HANDLE.get()))
			.addKey('I', Ingredient.of(Items.IRON_INGOT))
			.complete(References.ID, "phaser_rifle", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_PLASMA_CORE.get(), 1)
			.addPattern("GI ")
			.addPattern("MCM")
			.addPattern(" IG")
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_MATTER_CONTAINERS.get(ItemMatterContainer.ContainerType.REGULAR).get()))
			.addKey('G', Ingredient.of(Tags.Items.GLASS_COLORLESS))
			.addKey('I', Ingredient.of(Tags.Items.INGOTS_IRON))
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_SUPERCONDUCTOR_MAGNET.get()))
			.complete(References.ID, "plasma_core", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_PLASMA_SHOTGUN.get(), 1)
			.addPattern("SP ")
			.addPattern("ICH")
			.addPattern("SPB")
			.addKey('P', Ingredient.of(ItemRegistry.ITEM_PLASMA_CORE.get()))
			.addKey('B', Ingredient.of(ItemRegistry.ITEM_BATTERIES.get(ItemBattery.BatteryType.REGULAR).get()))
			.addKey('S', Ingredient.of(ItemRegistry.ITEM_WEAPON_RECEIVER.get()))
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER3).get()))
			.addKey('H', Ingredient.of(ItemRegistry.ITEM_WEAPON_HANDLE.get()))
			.addKey('I', Ingredient.of(Tags.Items.INGOTS_IRON))
			.complete(References.ID, "plasma_shotgun", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_PORTABLE_DECOMPOSER.get(), 1)
			.addPattern(" T ")
			.addPattern("IPM")
			.addPattern(" T ")
			.addKey('P', Ingredient.of(Items.STICKY_PISTON))
			.addKey('T', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
			.addKey('I', Ingredient.of(ItemRegistry.ITEM_INTEGRATION_MATRIX.get()))
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_ME_CONVERSION_MATRIX.get()))
			.complete(References.ID, "portable_decomposer", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_MATTER_REPLICATOR.get().asItem(), 1)
			.addPattern("PCF")
			.addPattern("IHI")
			.addPattern("NTM")
			.addKey('P', Ingredient.of(ItemRegistry.ITEM_PATTERN_DRIVE.get()))
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER2).get()))
			.addKey('T', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
			.addKey('F', Ingredient.of(ItemRegistry.ITEM_NETWORK_FLASH_DRIVE.get()))
			.addKey('H', Ingredient.of(ItemRegistry.ITEM_H_COMPENSATOR.get()))
			.addKey('I', Ingredient.of(Tags.Items.INGOTS_IRON))
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_ME_CONVERSION_MATRIX.get()))
			.addKey('N', Ingredient.of(ItemRegistry.ITEM_INTEGRATION_MATRIX.get()))
			.complete(References.ID, "replicator", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_SUPERCONDUCTOR_MAGNET.get(), 4)
			.addPattern("RRR")
			.addPattern("TET")
			.addPattern("RRR")
			.addKey('R', Ingredient.of(Tags.Items.DUSTS_REDSTONE))
			.addKey('T', Ingredient.of(OverdriveTags.Items.INGOT_TRITANIUM))
			.addKey('E', Ingredient.of(Tags.Items.ENDER_PEARLS))
			.complete(References.ID, "s_magnet", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_SECURITY_PROTOCOL.get(), 1)
			.addPattern("PP")
			.addPattern("CP")
			.addKey('P', Ingredient.of(Items.PAPER))
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER1).get()))
			.complete(References.ID, "security_protocol", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_SNIPER_SCOPE.get(), 1)
			.addPattern("IIC")
			.addPattern("GFG")
			.addPattern("III")
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER2).get()))
			.addKey('F', Ingredient.of(ItemRegistry.ITEM_FORCEFIELD_EMITTER.get()))
			.addKey('G', Ingredient.of(Tags.Items.GLASS_PANES))
			.addKey('I', Ingredient.of(Tags.Items.INGOTS_IRON))
			.complete(References.ID, "sniper_scope", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_SOLAR_PANEL.get().asItem(), 1)
			.addPattern("CGC")
			.addPattern("GQG")
			.addPattern("KMK")
			.addKey('Q', Ingredient.of(Tags.Items.STORAGE_BLOCKS_QUARTZ))
			.addKey('C', Ingredient.of(Items.COAL))
			.addKey('G', Ingredient.of(Tags.Items.GLASS))
			.addKey('K', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER2).get()))
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_MACHINE_CASING.get()))
			.complete(References.ID, "solar_panel", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_SPACETIME_ACCELERATOR.get().asItem(), 1)
			.addPattern("THT")
			.addPattern("DDD")
			.addPattern("THT")
			.addKey('T', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
			.addKey('D', Ingredient.of(OverdriveTags.Items.GEM_DILITHIUM))
			.addKey('H', Ingredient.of(ItemRegistry.ITEM_H_COMPENSATOR.get()))
			.complete(References.ID, "spacetime_accelerator", consumer);

//		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_SPACETIME_EQUALIZER.get().asItem(), 1)
//			.addPattern(" M ")
//			.addPattern("EHE")
//			.addPattern(" M ")
//			.addKey('E', Ingredient.of(Tags.Items.ENDER_PEARLS))
//			.addKey('H', Ingredient.of(ItemRegistry.ITEM_H_COMPENSATOR.get()))
//			.addKey('M', Ingredient.of(ItemRegistry.ITEM_SUPERCONDUCTOR_MAGNET.get()))
//			.complete(References.ID, "spacetime_equalizer", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_STAR_MAP.get().asItem(), 1)
			.addPattern(" S ")
			.addPattern("CFC")
			.addPattern("GMR")
			.addKey('R', Ingredient.of(Tags.Items.DUSTS_REDSTONE))
			.addKey('S', Ingredient.of(ItemRegistry.ITEM_SECURITY_PROTOCOL.get()))
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER2).get()))
			.addKey('F', Ingredient.of(ItemRegistry.ITEM_FORCEFIELD_EMITTER.get()))
			.addKey('G', Ingredient.of(Tags.Items.DUSTS_GLOWSTONE))
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_MACHINE_CASING.get()))
			.complete(References.ID, "star_map", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_TRANSPORTER_FLASHDRIVE.get(), 1)
			.addPattern(" I ")
			.addPattern("RCR")
			.addPattern(" I ")
			.addKey('R', Ingredient.of(Tags.Items.DUSTS_REDSTONE))
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER1).get()))
			.addKey('I', Ingredient.of(Tags.Items.INGOTS_IRON))
			.complete(References.ID, "transport_flash_drive", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_TRANSPORTER.get().asItem(), 1)
			.addPattern("TGT")
			.addPattern("CMC")
			.addPattern("NBH")
			.addKey('B', Ingredient.of(ItemRegistry.ITEM_BATTERIES.get(ItemBattery.BatteryType.HIGHCAPACITY).get()))
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER2).get()))
			.addKey('T', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
			.addKey('G', Ingredient.of(Items.GLOWSTONE.asItem()))
			.addKey('H', Ingredient.of(ItemRegistry.ITEM_H_COMPENSATOR.get()))
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_ME_CONVERSION_MATRIX.get()))
			.addKey('N', Ingredient.of(ItemRegistry.ITEM_INTEGRATION_MATRIX.get()))
			.complete(References.ID, "transporter", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_TRITANIUM_AXE.get(), 1)
			.addPattern("XX ")
			.addPattern("X# ")
			.addPattern(" # ")
			.addKey('#', Ingredient.of(Tags.Items.RODS_WOODEN))
			.addKey('X', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_INGOT.get()))
			.complete(References.ID, "tritanium_axe", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_TRITANIUM.get().asItem(), 1)
			.addPattern("TTT")
			.addPattern("TTT")
			.addPattern("TTT")
			.addKey('T', Ingredient.of(OverdriveTags.Items.INGOT_TRITANIUM))
			.complete(References.ID, "tritanium_block", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_TRITANIUM_BOOTS.get(), 1)
			.addPattern("   ")
			.addPattern("X X")
			.addPattern("X X")
			.addKey('X', Ingredient.of(OverdriveTags.Items.INGOT_TRITANIUM))
			.complete(References.ID, "tritanium_boots", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_TRITANIUM_CHESTPLATE.get(), 1)
			.addPattern("X X")
			.addPattern("XCX")
			.addPattern("XXX")
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER2).get()))
			.addKey('X', Ingredient.of(OverdriveTags.Items.INGOT_TRITANIUM))
			.complete(References.ID, "tritanium_chestplate", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_VENT_CLOSED.get().asItem(), 6)
			.addPattern(" # ")
			.addPattern("T T")
			.addPattern(" # ")
			.addKey('#', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
			.addKey('T', Ingredient.of(OverdriveTags.Items.INGOT_TRITANIUM))
			.complete(References.ID, "decorative_vent_closed", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_VENT_OPEN.get().asItem(), 8)
			.addPattern("###")
			.addPattern("#D#")
			.addPattern("###")
			.addKey('#', Ingredient.of(BlockRegistry.BLOCK_VENT_CLOSED.get().asItem()))
			.addKey('D', Ingredient.of(Tags.Items.DYES_BLACK))
			.complete(References.ID, "decorative_vent_open", consumer);
		
		OverdriveShapedCraftingRecipe.start(BlockRegistry.SOFT_WALL_PLATES.get().asItem(), 8)
			.addPattern("PWP")
			.addPattern("PPP")
			.addPattern("PWP")
			.addKey('P', Ingredient.of(BlockRegistry.BLOCK_REGULAR_TRITANIUM_PLATING.get().asItem()))
			.addKey('W', Ingredient.of(ItemTags.WOOL))
			.complete(References.ID, "decorative_soft_wall_plates", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.TRITANIUM_RAIL.get().asItem(), 8)
			.addPattern("###")
			.addPattern("#N#")
			.addPattern("###")
			.addKey('#', Ingredient.of(BlockRegistry.BLOCK_REGULAR_TRITANIUM_PLATING.get().asItem()))
			.addKey('N', Ingredient.of(OverdriveTags.Items.NUGGET_TRITANIUM))
			.complete(References.ID, "decorative_tritanium_rail", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.TRITANIUM_LAMP.get().asItem(), 2)
			.addPattern("###")
			.addPattern("#D#")
			.addPattern("DDD")
			.addKey('D', Ingredient.of(Tags.Items.DUSTS_GLOWSTONE))
			.addKey('#', Ingredient.of(BlockRegistry.BLOCK_REGULAR_TRITANIUM_PLATING.get().asItem()))
			.complete(References.ID, "decorative_tritanium_lamp", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.TRITANIUM_PLATE_STRIPE.get().asItem(), 8)
			.addPattern("###")
			.addPattern("###")
			.addPattern("#D#")
			.addKey('D', Ingredient.of(Tags.Items.DYES_YELLOW))
			.addKey('#', Ingredient.of(BlockRegistry.BLOCK_REGULAR_TRITANIUM_PLATING.get().asItem()))
			.complete(References.ID, "decorative_tritanium_plate_stripe", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.HOLO_MATRIX.get().asItem(), 8)
			.addPattern("###")
			.addPattern("#C#")
			.addPattern("###")
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER1).get()))
			.addKey('#', Ingredient.of(BlockRegistry.BLOCK_REGULAR_TRITANIUM_PLATING.get().asItem()))
			.complete(References.ID, "decorative_holo_matrix", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.YELLOW_STRIPES.get().asItem(), 8)
			.addPattern("#I#")
			.addPattern("###")
			.addPattern("#D#")
			.addKey('D', Ingredient.of(Tags.Items.DYES_YELLOW))
			.addKey('I', Ingredient.of(Tags.Items.DYES_BLACK))
			.addKey('#', Ingredient.of(BlockRegistry.BLOCK_REGULAR_TRITANIUM_PLATING.get().asItem()))
			.complete(References.ID, "decorative_yellow_stripes", consumer);

		// Define a mapping of dye colors to their appropriate tags
		Map<DyeColor, TagKey<Item>> dyeColorTags = new HashMap<>();

		// Populate the map with the dye colors and their corresponding tags
		dyeColorTags.put(DyeColor.BLACK, Tags.Items.DYES_BLACK);
		dyeColorTags.put(DyeColor.RED, Tags.Items.DYES_RED);
		dyeColorTags.put(DyeColor.GREEN, Tags.Items.DYES_GREEN);
		dyeColorTags.put(DyeColor.BROWN, Tags.Items.DYES_BROWN);
		dyeColorTags.put(DyeColor.BLUE, Tags.Items.DYES_BLUE);
		dyeColorTags.put(DyeColor.PURPLE, Tags.Items.DYES_PURPLE);
		dyeColorTags.put(DyeColor.CYAN, Tags.Items.DYES_CYAN);
		dyeColorTags.put(DyeColor.LIGHT_GRAY, Tags.Items.DYES_LIGHT_GRAY);
		dyeColorTags.put(DyeColor.GRAY, Tags.Items.DYES_GRAY);
		dyeColorTags.put(DyeColor.PINK, Tags.Items.DYES_PINK);
		dyeColorTags.put(DyeColor.LIME, Tags.Items.DYES_LIME);
		dyeColorTags.put(DyeColor.YELLOW, Tags.Items.DYES_YELLOW);
		dyeColorTags.put(DyeColor.LIGHT_BLUE, Tags.Items.DYES_LIGHT_BLUE);
		dyeColorTags.put(DyeColor.MAGENTA, Tags.Items.DYES_MAGENTA);
		dyeColorTags.put(DyeColor.ORANGE, Tags.Items.DYES_ORANGE);
		dyeColorTags.put(DyeColor.WHITE, Tags.Items.DYES_WHITE);

		// Iterate over the array to create crafting recipes for each color
		for (Map.Entry<DyeColor, TagKey<Item>> entry: dyeColorTags.entrySet()) {
			String color = entry.getKey().getName().toUpperCase();

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_TRITANIUM_CRATES.get(TileTritaniumCrate.CrateColors.valueOf(color)).get().asItem(), 1)
			 .addPattern(" D ")
			 .addPattern("TCT")
			 .addPattern(" T ")
			 .addKey('C', Ingredient.of(Tags.Items.CHESTS_WOODEN))
			 .addKey('T', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
			 .addKey('D', Ingredient.of(entry.getKey().getTag()))
			 .complete(References.ID, "tritanium_crate_" + entry.getKey().getName(), consumer);
		}

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_TRITANIUM_CRATES.get(TileTritaniumCrate.CrateColors.REG).get().asItem(), 1)
			 .addPattern("   ")
	  	  	 .addPattern("TCT")
		  	 .addPattern(" T ")
		  	 .addKey('C', Ingredient.of(Tags.Items.CHESTS_WOODEN))
		  	 .addKey('T', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
		  	 .complete(References.ID, "tritanium_crate_reg", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_TRITANIUM_HELMET.get(), 1)
			.addPattern("XCX")
			.addPattern("X X")
			.addPattern("   ")
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER2).get()))
			.addKey('X', Ingredient.of(OverdriveTags.Items.INGOT_TRITANIUM))
			.complete(References.ID, "tritanium_helmet", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_TRITANIUM_HOE.get(), 1)
			.addPattern("XX ")
			.addPattern(" # ")
			.addPattern(" # ")
			.addKey('#', Ingredient.of(Tags.Items.RODS_WOODEN))
			.addKey('X', Ingredient.of(OverdriveTags.Items.INGOT_TRITANIUM))
			.complete(References.ID, "tritanium_hoe", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_TRITANIUM_INGOT.get(), 1)
			.addPattern("###")
			.addPattern("###")
			.addPattern("###")
			.addKey('#', Ingredient.of(OverdriveTags.Items.NUGGET_TRITANIUM))
			.complete(References.ID, "tritanium_ingot", consumer);

		OverdriveShapelessCraftingRecipe.start(ItemRegistry.ITEM_TRITANIUM_NUGGET.get(), 9)
			.addIngredient(Ingredient.of(OverdriveTags.Items.INGOT_TRITANIUM))
			.complete(References.ID, "tritanium_nugget_from_ingot", consumer);
		
		OverdriveShapelessCraftingRecipe.start(ItemRegistry.ITEM_TRITANIUM_INGOT.get(),9)
			.addIngredient(Ingredient.of(BlockRegistry.BLOCK_TRITANIUM.get().asItem()))
			.complete(References.ID, "tritanium_ingot_from_block", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_TRITANIUM_LEGGINGS.get(), 1)
			.addPattern("XCX")
			.addPattern("X X")
			.addPattern("X X")
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER2).get()))
			.addKey('X', Ingredient.of(OverdriveTags.Items.INGOT_TRITANIUM))
			.complete(References.ID, "tritanium_leggings", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_TRITANIUM_PICKAXE.get(), 1)
			.addPattern("XXX")
			.addPattern(" # ")
			.addPattern(" # ")
			.addKey('#', Ingredient.of(Tags.Items.RODS_WOODEN))
			.addKey('X', Ingredient.of(OverdriveTags.Items.INGOT_TRITANIUM))
			.complete(References.ID, "tritanium_pickaxe", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_TRITANIUM_SHOVEL.get(), 1)
			.addPattern(" X ")
			.addPattern(" # ")
			.addPattern(" # ")
			.addKey('#', Ingredient.of(Tags.Items.RODS_WOODEN))
			.addKey('X', Ingredient.of(OverdriveTags.Items.INGOT_TRITANIUM))
			.complete(References.ID, "tritanium_shovel", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_TRITANIUM_SWORD.get(), 1)
			.addPattern(" X ")
			.addPattern(" X ")
			.addPattern(" # ")
			.addKey('#', Ingredient.of(Tags.Items.RODS_WOODEN))
			.addKey('X', Ingredient.of(OverdriveTags.Items.INGOT_TRITANIUM))
			.complete(References.ID, "tritanium_sword", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_TRITANIUM_WRENCH.get(), 1)
			.addPattern("T T")
			.addPattern(" Y ")
			.addPattern(" T ")
			.addKey('T', Ingredient.of(OverdriveTags.Items.INGOT_TRITANIUM))
			.addKey('Y', Ingredient.of(ItemTags.WOOL))
			.complete(References.ID, "tritanium_wrench", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_UPGRADE_BASE.get(), 1)
			.addPattern(" R ")
			.addPattern(" C ")
			.addPattern(" T ")
			.addKey('R', Ingredient.of(Tags.Items.DUSTS_REDSTONE))
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER1).get()))
			.addKey('T', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
			.complete(References.ID, "upgrade_base", consumer);

		// On hold until the Weapon Modules are worked out.
//		OverdriveShapedCraftingRecipe.start(ItemRegistry.WEAPON_MODULE_BARREL, 1)
//			.addPattern(" G ")
//			.addPattern("RDR")
//			.addPattern(" T ")
//			.addKey('R', Ingredient.of(Tags.Items.DUSTS_REDSTONE))
//			.addKey('T', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
//			.addKey('D', Ingredient.of(OverdriveTags.Items.GEM_DILITHIUM))
//			.addKey('G', Ingredient.of(Tags.Items.GLASS))
//			.complete(References.ID, "weapon_module_barrel", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_WEAPON_HANDLE.get(), 1)
			.addPattern("TWT")
			.addPattern("I I")
			.addPattern("I I")
			.addKey('W', Ingredient.of(Items.BLACK_WOOL))
			.addKey('I', Ingredient.of(Tags.Items.INGOTS_IRON))
			.addKey('T', Ingredient.of(OverdriveTags.Items.INGOT_TRITANIUM))
			.complete(References.ID, "weapon_handle", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_WEAPON_RECEIVER.get(), 1)
			.addPattern("IRT")
			.addPattern("   ")
			.addPattern("IIT")
			.addKey('R', Ingredient.of(Tags.Items.DUSTS_REDSTONE))
			.addKey('I', Ingredient.of(Tags.Items.INGOTS_IRON))
			.addKey('T', Ingredient.of(OverdriveTags.Items.INGOT_TRITANIUM))
			.complete(References.ID, "weapon_receiver", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_TRITANIUM_PLATE.get(), 1)
			.addPattern("TT")
			.addKey('T', Ingredient.of(OverdriveTags.Items.INGOT_TRITANIUM))
			.complete(References.ID, "tritanium_plate", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_WEAPON_STATION.get().asItem(), 1)
			.addPattern("   ")
			.addPattern("GFR")
			.addPattern("CMB")
			.addKey('B', Ingredient.of(ItemRegistry.ITEM_BATTERIES.get(ItemBattery.BatteryType.REGULAR).get()))
			.addKey('R', Ingredient.of(Tags.Items.DUSTS_REDSTONE))
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER3).get()))
			.addKey('F', Ingredient.of(ItemRegistry.ITEM_FORCEFIELD_EMITTER.get()))
			.addKey('G', Ingredient.of(Tags.Items.DUSTS_GLOWSTONE))
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_MACHINE_CASING.get()))
			.complete(References.ID, "weapon_station", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_NETWORK_SWITCH.get().asItem(), 1)
			.addPattern(" G ")
			.addPattern("CDC")
			.addPattern(" M ")
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER1).get()))
			.addKey('D', Ingredient.of(ItemRegistry.ITEM_NETWORK_FLASH_DRIVE.get()))
			.addKey('G', Ingredient.of(Tags.Items.GLASS))
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_MACHINE_CASING.get()))
			.complete(References.ID, "network_switch", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_HOLO_SIGN.get().asItem(), 1)
			.addPattern("GGG")
			.addPattern("DCD")
			.addPattern(" P ")
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER1).get()))
			.addKey('P', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
			.addKey('D', Ingredient.of(Tags.Items.DUSTS_GLOWSTONE))
			.addKey('G', Ingredient.of(Tags.Items.GLASS))
			.complete(References.ID, "holo_sign", consumer);

		addDecorativeFloorTile(consumer);
		addDecorativeFloorTiles(consumer);
		addDecorativeRecipes(consumer);
		addUpgradeRecipes(consumer);
	}

	private void addDecorativeRecipes(Consumer<FinishedRecipe> consumer) {
		for (DyeColor color: DyeColor.values()) {
			String useColor = color.getName().toUpperCase();
			Block block = BlockRegistry.BLOCK_COLORED_TRITANIUM_PLATING
				.get(OverdriveBlockColors.valueOf(useColor)).get();

			OverdriveShapedCraftingRecipe.start(block.asItem(), 8)
				.addPattern("###")
				.addPattern("#D#")
				.addPattern("###")
				.addKey('#', Ingredient.of(OverdriveTags.Items.COLORED_PLATING))
				.addKey('D', Ingredient.of(color.getTag()))
				.complete(References.ID, "tritanium_plating_" + color.getName(), consumer);
		}
	}

	private void addDecorativeFloorTile(Consumer<FinishedRecipe> consumer) {
		for (DyeColor color: DyeColor.values()) {
			String useColor = color.getName().toUpperCase();
			Block block = BlockRegistry.BLOCK_FLOOR_TILE
				.get(OverdriveBlockColors.valueOf(useColor)).get();

			OverdriveShapedCraftingRecipe.start(block.asItem(), 32)
				.addPattern("#P#")
				.addPattern("#D#")
				.addPattern("#P#")
				.addKey('#', Ingredient.of(Items.CLAY))
				.addKey('D', Ingredient.of(color.getTag()))
				.addKey('P', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
				.complete(References.ID, "decorative_floor_tile_" + color.getName(), consumer);
		}
	}

	private void addDecorativeFloorTiles(Consumer<FinishedRecipe> consumer) {
		for (DyeColor color: DyeColor.values()) {
			String useColor = color.getName().toUpperCase();
			Block block = BlockRegistry.BLOCK_FLOOR_TILES
				.get(OverdriveBlockColors.valueOf(useColor)).get();

			OverdriveShapedCraftingRecipe.start(block.asItem(), 32)
				.addPattern("#P#")
				.addPattern("#D#")
				.addPattern("###")
				.addKey('#', Ingredient.of(Items.CLAY))
				.addKey('D', Ingredient.of(color.getTag()))
				.addKey('P', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
				.complete(References.ID, "decorative_floor_tiles_" + color.getName(), consumer);
		}
	}

	private void addInscriberRecipes(Consumer<FinishedRecipe> consumer) {

		inscriberRecipe(new ItemStack(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER2).get()), "isolinear_circuit_tier2", 250, 250)
			.addItemTagInput(OverdriveTags.Items.CIRCUITS_BASIC, 1).addItemTagInput(OverdriveTags.Items.GOLD_INGOT, 1).complete(consumer);
		inscriberRecipe(new ItemStack(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER3).get()), "isolinear_circuit_tier3", 300, 300)
			.addItemTagInput(OverdriveTags.Items.CIRCUITS_ADVANCED, 1).addItemTagInput(OverdriveTags.Items.DIAMOND_GEM, 1).complete(consumer);
		inscriberRecipe(new ItemStack(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER4).get()), "isolinear_circuit_tier4", 350, 350)
			.addItemTagInput(OverdriveTags.Items.CIRCUITS_ELITE, 1).addItemTagInput(OverdriveTags.Items.EMERALD_GEM, 1).complete(consumer);

	}

	private FinishedRecipeItemOutput inscriberRecipe(ItemStack output, String name, double processTime, double usage) {
		return FinishedRecipeItemOutput.of(RecipeInit.INSCRIBER_SERIALIZER.get(), output, 0.0, processTime, usage)
			.name(RecipeCategory.ITEM_2_ITEM, References.ID, INSCRIBER_LOC + "/" + name);
	}

	private void addUpgradeRecipes(Consumer<FinishedRecipe> consumer) {
		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_UPGRADES.get(ItemUpgrade.UpgradeType.SPEED).get(), 1)
			.addPattern(" R ")
			.addPattern("GUG")
			.addPattern(" E ")
			.addKey('R', Ingredient.of(Tags.Items.DUSTS_REDSTONE))
			.addKey('U', Ingredient.of(ItemRegistry.ITEM_UPGRADE_BASE.get()))
			.addKey('E', Ingredient.of(Tags.Items.GEMS_EMERALD))
			.addKey('G', Ingredient.of(Tags.Items.DUSTS_GLOWSTONE))
			.complete(References.ID, "upgrade_1", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_UPGRADES.get(ItemUpgrade.UpgradeType.POWER).get(), 1)
			.addPattern(" B ")
			.addPattern("RUR")
			.addPattern(" C ")
			.addKey('B', Ingredient.of(ItemRegistry.ITEM_BATTERIES.get(ItemBattery.BatteryType.REGULAR).get()))
			.addKey('R', Ingredient.of(Tags.Items.DUSTS_REDSTONE))
			.addKey('C', Ingredient.of(Tags.Items.GEMS_QUARTZ))
			.addKey('U', Ingredient.of(ItemRegistry.ITEM_UPGRADE_BASE.get()))
			.complete(References.ID, "upgrade_2", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_UPGRADES.get(ItemUpgrade.UpgradeType.FAIL_SAFE).get(), 1)
			.addPattern(" D ")
			.addPattern("RUR")
			.addPattern(" G ")
			.addKey('D', Ingredient.of(Tags.Items.GEMS_DIAMOND))
			.addKey('R', Ingredient.of(Tags.Items.DUSTS_REDSTONE))
			.addKey('U', Ingredient.of(ItemRegistry.ITEM_UPGRADE_BASE.get()))
			.addKey('G', Ingredient.of(Tags.Items.INGOTS_GOLD))
			.complete(References.ID, "upgrade_3", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_UPGRADES.get(ItemUpgrade.UpgradeType.RANGE).get(), 1)
			.addPattern(" E ")
			.addPattern("RUR")
			.addPattern(" G ")
			.addKey('E', Ingredient.of(Tags.Items.ENDER_PEARLS))
			.addKey('R', Ingredient.of(Tags.Items.DUSTS_REDSTONE))
			.addKey('U', Ingredient.of(ItemRegistry.ITEM_UPGRADE_BASE.get()))
			.addKey('G', Ingredient.of(Tags.Items.INGOTS_GOLD))
			.complete(References.ID, "upgrade_4", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_UPGRADES.get(ItemUpgrade.UpgradeType.POWER_STORAGE).get(), 1)
			.addPattern(" G ")
			.addPattern("RUR")
			.addPattern(" B ")
			.addKey('G', Ingredient.of(Tags.Items.INGOTS_GOLD))
			.addKey('R', Ingredient.of(Tags.Items.DUSTS_REDSTONE))
			.addKey('U', Ingredient.of(ItemRegistry.ITEM_UPGRADE_BASE.get()))
			.addKey('B', Ingredient.of(ItemRegistry.ITEM_BATTERIES.get(ItemBattery.BatteryType.HIGHCAPACITY).get()))
			.complete(References.ID, "upgrade_5", consumer);

		OverdriveShapelessCraftingRecipe.start(ItemRegistry.ITEM_UPGRADES.get(ItemUpgrade.UpgradeType.HYPER_SPEED).get(), 1)
			.addIngredient(Ingredient.of(OverdriveTags.Items.GEM_DILITHIUM))
			.addIngredient(Ingredient.of(Items.NETHER_STAR))
			.addIngredient(Ingredient.of(ItemRegistry.ITEM_UPGRADES.get(ItemUpgrade.UpgradeType.SPEED).get()))
			.complete(References.ID, "upgrade_6", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_UPGRADES.get(ItemUpgrade.UpgradeType.MATTER_STORAGE).get(), 1)
			.addPattern(" R ")
			.addPattern("MUM")
			.addPattern(" R ")
			.addKey('R', Ingredient.of(Tags.Items.DUSTS_REDSTONE))
			.addKey('U', Ingredient.of(ItemRegistry.ITEM_UPGRADE_BASE.get()))
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_SUPERCONDUCTOR_MAGNET.get()))
			.complete(References.ID, "upgrade_7", consumer);

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_UPGRADES.get(ItemUpgrade.UpgradeType.MUFFLER).get(), 1)
			.addPattern(" R ")
			.addPattern("WUW")
			.addPattern(" R ")
			.addKey('R', Ingredient.of(Tags.Items.DUSTS_REDSTONE))
			.addKey('U', Ingredient.of(ItemRegistry.ITEM_UPGRADE_BASE.get()))
			.addKey('W', Ingredient.of(ItemTags.WOOL))
			.complete(References.ID, "upgrade_8", consumer);
	}
}
