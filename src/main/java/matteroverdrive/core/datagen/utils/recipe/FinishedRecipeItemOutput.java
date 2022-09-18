package matteroverdrive.core.datagen.utils.recipe;

import com.google.gson.JsonObject;

import matteroverdrive.common.recipe.AbstractOverdriveRecipeSerializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;

public class FinishedRecipeItemOutput extends AbstractOverdriveFinishedRecipe {

	private ItemStack output;
	
	private FinishedRecipeItemOutput(RecipeSerializer<?> serializer, ItemStack stack, double experience, double processTime, double usage) {
		super(serializer, experience, processTime, usage);
		this.output = stack;
	}

	@Override
	public void writeOutput(JsonObject recipeJson) {
		JsonObject output = new JsonObject();
		output.addProperty("item", ForgeRegistries.ITEMS.getKey(this.output.getItem()).toString());
		output.addProperty(AbstractOverdriveRecipeSerializer.COUNT, this.output.getCount());
		recipeJson.add(AbstractOverdriveRecipeSerializer.OUTPUT, output);
	}
	
	@Override
	public FinishedRecipeItemOutput name(RecipeCategory category, String parent, String name) {
		return (FinishedRecipeItemOutput) super.name(category, parent, name);
	}
	
	public static FinishedRecipeItemOutput of(RecipeSerializer<?> serializer, ItemStack output, double experience, double processTime, double usage) {
		return new FinishedRecipeItemOutput(serializer, output, experience, processTime, usage);
	}
	

}
