package matteroverdrive.compatibility.jei.categories.pseudo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import matteroverdrive.References;
import matteroverdrive.common.tile.TileMatterRecycler;
import matteroverdrive.compatibility.jei.categories.base.AbstractOverdriveRecipeCategory;
import matteroverdrive.compatibility.jei.utils.IPseudoRecipe;
import matteroverdrive.compatibility.jei.utils.gui.ScreenObjectWrapper;
import matteroverdrive.compatibility.jei.utils.gui.ScreenObjectWrapper.JeiTexture;
import matteroverdrive.compatibility.jei.utils.gui.arrows.animated.ArrowRightAnimatedWrapper;
import matteroverdrive.compatibility.jei.utils.gui.backgroud.OverdriveBackgroundManager;
import matteroverdrive.compatibility.jei.utils.gui.item.DefaultItemSlotWrapper;
import matteroverdrive.compatibility.jei.utils.label.types.LabelWrapperPowerConsumed;
import matteroverdrive.compatibility.jei.utils.label.types.LabelWrapperPowerUsage;
import matteroverdrive.compatibility.jei.utils.label.types.LabelWrapperProcessTime;
import matteroverdrive.core.screen.component.ScreenComponentSlot.SlotType;
import matteroverdrive.core.utils.UtilsRendering;
import matteroverdrive.registry.BlockRegistry;
import matteroverdrive.registry.ItemRegistry;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class RecipeCategoryMatterRecycler extends AbstractOverdriveRecipeCategory<matteroverdrive.compatibility.jei.categories.pseudo.RecipeCategoryMatterRecycler.PseudoRecipeMatterRecycler> {

	// JEI Window Parameters
	private static final ScreenObjectWrapper BACK_WRAP = new ScreenObjectWrapper(JeiTexture.VANILLA_BACKGROUND, 0, 0, 0, 0, 132, 72);
	private static final OverdriveBackgroundManager MANAGER = new OverdriveBackgroundManager(JeiTexture.OVERDRIVE_BACKGROUND_BAR, 72, 0, 0);
	
	private static final DefaultItemSlotWrapper INPUT_SLOT = new DefaultItemSlotWrapper(SlotType.MAIN, 7, 12, false);
	private static final DefaultItemSlotWrapper OUTPUT_SLOT = new DefaultItemSlotWrapper(SlotType.BIG, 65, 12, false);

	private static final ArrowRightAnimatedWrapper ANIM_ARROW = new ArrowRightAnimatedWrapper(35, 15);
	
	private static final LabelWrapperPowerUsage POWER_USE = new LabelWrapperPowerUsage(UtilsRendering.HOLO_RED, 50, 40);
	private static final LabelWrapperPowerConsumed POWER_CONSUMED = new LabelWrapperPowerConsumed(UtilsRendering.HOLO_RED, 50, 52);
	private static final LabelWrapperProcessTime PROCESS_TIME = new LabelWrapperProcessTime(UtilsRendering.TEXT_BLUE, 89, 19);

	private static final int ANIM_TIME = 50;

	public static final ItemStack INPUT_MACHINE = new ItemStack(BlockRegistry.BLOCK_MATTER_RECYCLER.get());
	
	public static final RecipeType<PseudoRecipeMatterRecycler> RECIPE_TYPE = new RecipeType<>(new ResourceLocation(References.ID, "matter_recycler"), PseudoRecipeMatterRecycler.class);
	
	public RecipeCategoryMatterRecycler(IGuiHelper guiHelper) {
		super(guiHelper, INPUT_MACHINE, BACK_WRAP, ANIM_TIME);
		
		setBackgroundExtra(guiHelper, MANAGER.getBackgroundExtras());
		setInputSlots(guiHelper, INPUT_SLOT);
		setOutputSlots(guiHelper, OUTPUT_SLOT);
		setAnimatedArrows(guiHelper, ANIM_ARROW);
		setLabels(POWER_USE, POWER_CONSUMED, PROCESS_TIME);
		
	}
	
	@Override
	public RecipeType<PseudoRecipeMatterRecycler> getRecipeType() {
		return RECIPE_TYPE;
	}
	
	public List<List<ItemStack>> getItemInputs(PseudoRecipeMatterRecycler recipe) {
		return Arrays.asList(Arrays.asList(recipe.input().getItems()));
	}
	
	public List<ItemStack> getItemOutputs(PseudoRecipeMatterRecycler recipe) {
		return Arrays.asList(recipe.output().getItems());
	}

	public static List<PseudoRecipeMatterRecycler> getPseudoReipes() {

		List<PseudoRecipeMatterRecycler> recipes = new ArrayList<>();

		recipes.add(new PseudoRecipeMatterRecycler(Ingredient.of(ItemRegistry.ITEM_RAW_MATTER_DUST.get()),
				Ingredient.of(ItemRegistry.ITEM_MATTER_DUST.get())));

		return recipes;

	}

	public static record PseudoRecipeMatterRecycler(Ingredient input, Ingredient output) implements IPseudoRecipe {

		@Override
		public double getUsagePerTick() {
			return TileMatterRecycler.USAGE_PER_TICK;
		}

		@Override
		public double getProcessTime() {
			return TileMatterRecycler.OPERATING_TIME;
		}

	}

}
