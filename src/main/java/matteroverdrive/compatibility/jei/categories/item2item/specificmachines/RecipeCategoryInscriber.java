package matteroverdrive.compatibility.jei.categories.item2item.specificmachines;

import matteroverdrive.common.recipe.item2item.specific_machines.InscriberRecipe;
import matteroverdrive.compatibility.jei.categories.item2item.Item2ItemRecipeCategory;
import matteroverdrive.compatibility.jei.utils.gui.ScreenObjectWrapper;
import matteroverdrive.compatibility.jei.utils.gui.ScreenObjectWrapper.JeiTexture;
import matteroverdrive.compatibility.jei.utils.gui.arrows.animated.ArrowRightAnimatedWrapper;
import matteroverdrive.compatibility.jei.utils.gui.backgroud.OverdriveBackgroundManager;
import matteroverdrive.compatibility.jei.utils.gui.item.DefaultItemSlotWrapper;
import matteroverdrive.compatibility.jei.utils.label.types.PowerConsumedLabelWrapper;
import matteroverdrive.compatibility.jei.utils.label.types.PowerUsageLabelWrapper;
import matteroverdrive.compatibility.jei.utils.label.types.ProcessTimeLabelWrapper;
import matteroverdrive.core.screen.component.ScreenComponentSlot.SlotType;
import matteroverdrive.core.utils.UtilsRendering;
import matteroverdrive.registry.BlockRegistry;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.ItemStack;

public class RecipeCategoryInscriber extends Item2ItemRecipeCategory<InscriberRecipe> {

	// JEI Window Parameters
	private static final ScreenObjectWrapper BACK_WRAP = new ScreenObjectWrapper(JeiTexture.VANILLA_BACKGROUND, 0, 0, 0, 0, 132, 72);
	private static final OverdriveBackgroundManager MANAGER = new OverdriveBackgroundManager(JeiTexture.OVERDRIVE_BACKGROUND_BAR, 72, 0, 0);
	
	private static final DefaultItemSlotWrapper INPUT_SLOT_1 = new DefaultItemSlotWrapper(SlotType.MAIN, 7, 12, false);
	private static final DefaultItemSlotWrapper INPUT_SLOT_2 = new DefaultItemSlotWrapper(SlotType.BIG, 7, 39, false);
	private static final DefaultItemSlotWrapper OUTPUT_SLOT = new DefaultItemSlotWrapper(SlotType.BIG, 65, 12, false);

	private static final ArrowRightAnimatedWrapper ANIM_ARROW = new ArrowRightAnimatedWrapper(35, 15);
	
	private static final PowerUsageLabelWrapper POWER_USE = new PowerUsageLabelWrapper(UtilsRendering.HOLO_RED, 50, 40);
	private static final PowerConsumedLabelWrapper POWER_CONSUMED = new PowerConsumedLabelWrapper(UtilsRendering.HOLO_RED, 50, 52);
	private static final ProcessTimeLabelWrapper PROCESS_TIME = new ProcessTimeLabelWrapper(UtilsRendering.TEXT_BLUE, 89, 19);

	private static final int ANIM_TIME = 50;

	public static final ItemStack INPUT_MACHINE = new ItemStack(BlockRegistry.BLOCK_INSCRIBER.get());
	
	public static final RecipeType<InscriberRecipe> RECIPE_TYPE = new RecipeType<>(InscriberRecipe.RECIPE_ID, InscriberRecipe.class);

	public RecipeCategoryInscriber(IGuiHelper guiHelper) {
		super(guiHelper, INPUT_MACHINE, BACK_WRAP, ANIM_TIME);
		
		setBackgroundExtra(guiHelper, MANAGER.getBackgroundExtras());
		setInputSlots(guiHelper, INPUT_SLOT_1, INPUT_SLOT_2);
		setOutputSlots(guiHelper, OUTPUT_SLOT);
		setAnimatedArrows(guiHelper, ANIM_ARROW);
		setLabels(POWER_USE, POWER_CONSUMED, PROCESS_TIME);

	}

	@Override
	public RecipeType<InscriberRecipe> getRecipeType() {
		return RECIPE_TYPE;
	}

}