package matteroverdrive.common.recipe.item2item;

import java.lang.reflect.Constructor;

import com.google.gson.JsonObject;

import matteroverdrive.MatterOverdrive;
import matteroverdrive.common.recipe.AbstractOverdriveRecipeSerializer;
import matteroverdrive.core.recipe.CountableIngredient;
import matteroverdrive.core.recipe.ProbableFluid;
import matteroverdrive.core.recipe.ProbableItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class Item2ItemRecipeSerializer<T extends Item2ItemRecipe> extends AbstractOverdriveRecipeSerializer<T> {

	public Item2ItemRecipeSerializer(Class<T> recipeClass) {
		super(recipeClass);
	}

	@Override
	public T fromJson(ResourceLocation recipeId, JsonObject recipeJson) {
		CountableIngredient[] inputs = getItemIngredients(recipeJson);
		ItemStack output = getItemOutput(recipeJson);
		double experience = getExperience(recipeJson);
		if (recipeJson.has(ITEM_BIPRODUCTS)) {
			ProbableItem[] itemBi = getItemBiproducts(recipeJson);
			if (recipeJson.has(FLUID_BIPRODUCTS)) {
				ProbableFluid[] fluidBi = getFluidBiproducts(recipeJson);
				try {
					Constructor<T> recipeConstructor = getRecipeClass().getDeclaredConstructor(ResourceLocation.class, CountableIngredient[].class, ItemStack.class, ProbableItem[].class, ProbableFluid[].class, double.class);
					return recipeConstructor.newInstance(recipeId, inputs, output, itemBi, fluidBi, experience);
				} catch (Exception e) {
					MatterOverdrive.LOGGER.info(e.getMessage());
				}
			} else {
				try {
					Constructor<T> recipeConstructor = getRecipeClass().getDeclaredConstructor(ResourceLocation.class, CountableIngredient[].class, ItemStack.class, ProbableItem[].class, double.class);
					return recipeConstructor.newInstance(recipeId, inputs, output, itemBi, experience);
				} catch (Exception e) {
					MatterOverdrive.LOGGER.info(e.getMessage());
				}
			}
		} else if (recipeJson.has(FLUID_BIPRODUCTS)) {
			ProbableFluid[] fluidBi = getFluidBiproducts(recipeJson);
			try {
				Constructor<T> recipeConstructor = getRecipeClass().getDeclaredConstructor(CountableIngredient[].class, ItemStack.class, ProbableFluid[].class, ResourceLocation.class, double.class);
				return recipeConstructor.newInstance(inputs, output, fluidBi, recipeId, experience);
			} catch (Exception e) {
				MatterOverdrive.LOGGER.info(e.getMessage());
			}
		} else {
			try {
				Constructor<T> recipeConstructor = getRecipeClass().getDeclaredConstructor(ResourceLocation.class, CountableIngredient[].class, ItemStack.class, double.class);
				return recipeConstructor.newInstance(recipeId, inputs, output, experience);
			} catch (Exception e) {
				MatterOverdrive.LOGGER.info(e.getMessage());
			}
		}
		MatterOverdrive.LOGGER.info("returning null at " + recipeId);
		return null;
	}

	@Override
	public T fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
		boolean hasItemBi = buffer.readBoolean();
		boolean hasFluidBi = buffer.readBoolean();
		CountableIngredient[] inputs = CountableIngredient.readList(buffer);
		ItemStack output = buffer.readItem();
		double experience = buffer.readDouble();
		if (hasItemBi) {
			ProbableItem[] itemBi = ProbableItem.readList(buffer);
			if (hasFluidBi) {
				ProbableFluid[] fluidBi = ProbableFluid.readList(buffer);
				try {
					Constructor<T> recipeConstructor = getRecipeClass().getDeclaredConstructor(ResourceLocation.class, CountableIngredient[].class, ItemStack.class, ProbableItem[].class, ProbableFluid[].class, double.class);
					return recipeConstructor.newInstance(recipeId, inputs, output, itemBi, fluidBi, experience);
				} catch (Exception e) {
					MatterOverdrive.LOGGER.info(e.getMessage());
				}
			} else {
				try {
					Constructor<T> recipeConstructor = getRecipeClass().getDeclaredConstructor(ResourceLocation.class, CountableIngredient[].class, ItemStack.class, ProbableItem[].class, double.class);
					return recipeConstructor.newInstance(recipeId, inputs, output, itemBi, experience);
				} catch (Exception e) {
					MatterOverdrive.LOGGER.info(e.getMessage());
				}
			}
		} else if (hasFluidBi) {
			ProbableFluid[] fluidBi = ProbableFluid.readList(buffer);
			try {
				Constructor<T> recipeConstructor = getRecipeClass().getDeclaredConstructor(CountableIngredient[].class, ItemStack.class, ProbableFluid[].class, ResourceLocation.class, double.class);
				return recipeConstructor.newInstance(inputs, output, fluidBi, recipeId, experience);
			} catch (Exception e) {
				MatterOverdrive.LOGGER.info(e.getMessage());
			}
		} else {
			try {
				Constructor<T> recipeConstructor = getRecipeClass().getDeclaredConstructor(ResourceLocation.class, CountableIngredient[].class, ItemStack.class, double.class);
				return recipeConstructor.newInstance(recipeId, inputs, output, experience);
			} catch (Exception e) {
				MatterOverdrive.LOGGER.info(e.getMessage());
			}
		}
		MatterOverdrive.LOGGER.info("returning null at " + recipeId);
		return null;
	}

	@Override
	public void toNetwork(FriendlyByteBuf buffer, Item2ItemRecipe recipe) {
		buffer.writeBoolean(recipe.hasItemBiproducts());
		buffer.writeBoolean(recipe.hasFluidBiproducts());
		CountableIngredient.writeList(buffer, recipe.getCountedIngredients());
		buffer.writeItem(recipe.getResultItem());
		buffer.writeDouble(recipe.getXp());
		if (recipe.hasItemBiproducts()) {
			ProbableItem.writeList(buffer, recipe.getItemBiproducts());
		}
		if (recipe.hasFluidBiproducts()) {
			ProbableFluid.writeList(buffer, recipe.getFluidBiproducts());
		}
	}

}
