package matteroverdrive.datagen.server;

import java.util.function.Consumer;

import matteroverdrive.References;
import matteroverdrive.common.block.type.TypeMatterConduit;
import matteroverdrive.common.item.tools.electric.ItemBattery;
import matteroverdrive.common.item.type.TypeIsolinearCircuit;
import matteroverdrive.common.item.utils.OverdriveItem;
import matteroverdrive.common.recipe.RecipeInit;
import matteroverdrive.common.tags.OverdriveTags;
import matteroverdrive.datagen.utils.recipe.FinishedRecipeItemOutput;
import matteroverdrive.datagen.utils.recipe.OverdriveShapedCraftingRecipe;
import matteroverdrive.datagen.utils.recipe.AbstractOverdriveFinishedRecipe.RecipeCategory;
import matteroverdrive.datagen.utils.recipe.OverdriveShapelessCraftingRecipe;
import matteroverdrive.registry.BlockRegistry;
import matteroverdrive.registry.ItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import org.apache.commons.compress.harmony.pack200.NewAttributeBands;

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
			.addPattern("I").addPattern("R").addPattern("G").addKey('I', "forge", "ingots/iron").addKey('R', "forge", "dusts/redstone")
			.addKey('G', "forge", "glass").complete(References.ID, "isolinear_circuit_tier1", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_ANDROID_STATION.get().asItem(), 1)
			.addPattern("THA").addPattern("2F3").addPattern("GMR")
			.addKey('A', Ingredient.of(ItemRegistry.ITEM_ROGUE_ANDROID_ARMS.get()))
			.addKey('2', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER1).get()))
			.addKey('R', Ingredient.of(Items.REDSTONE))
			.addKey('3', Ingredient.of(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER2).get()))
			.addKey('T', Ingredient.of(ItemRegistry.ITEM_ROGUE_ANDROID_CHEST.get()))
			.addKey('F', Ingredient.of(ItemRegistry.ITEM_FORCEFIELD_EMITTER.get()))
			.addKey('G', Ingredient.of(Items.GLOWSTONE))
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

		OverdriveShapedCraftingRecipe.start(ItemRegistry.ITEM_BATTERIES.get(ItemBattery.BatteryType.HIGHCAPACITY).get(), 1)
			.addPattern(" P ")
			.addPattern("CBC")
			.addPattern(" P ")
			.addKey('B', Ingredient.of(ItemRegistry.ITEM_BATTERIES.get(ItemBattery.BatteryType.REGULAR).get()))
			.addKey('P', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
			.addKey('C', Ingredient.of(ItemRegistry.ITEM_DILITHIUM_CRYSTAL.get()))
			.complete(References.ID, "battery_highcapacity", consumer);

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
			.addKey('#', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
			.addKey('T', Ingredient.of(OverdriveTags.Items.NUGGET_TRITANIUM))
			.complete(References.ID, "decorative.beams", consumer);

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_DECORATIVE_CARBON_FIBER_PLATE.get().asItem(), 8)
			.addPattern("###")
			.addPattern("#C#")
			.addPattern("###")
			.addKey('#', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
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
			.addKey('#', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
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

		OverdriveShapedCraftingRecipe.start(BlockRegistry.BLOCK_MATTER_CONDUITS.get(TypeMatterConduit.HEAVY).get().asItem(),  1)
			.addPattern("RMR")
			.addPattern("TNT")
			.addPattern("RMR")
			.addKey('R', Ingredient.of(Tags.Items.DUSTS_REDSTONE))
			.addKey('T', Ingredient.of(ItemRegistry.ITEM_TRITANIUM_PLATE.get()))
			.addKey('M', Ingredient.of(ItemRegistry.ITEM_SUPERCONDUCTOR_MAGNET.get()))
			.complete(References.ID, "heavy_matter_pipe", consumer);


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

}
