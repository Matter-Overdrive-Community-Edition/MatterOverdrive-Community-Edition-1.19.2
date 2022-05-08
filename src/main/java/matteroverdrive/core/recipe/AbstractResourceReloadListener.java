package matteroverdrive.core.recipe;

import java.util.Set;

import matteroverdrive.common.recipe.AbstractOverdriveRecipe;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;

public abstract class AbstractResourceReloadListener extends SimplePreparableReloadListener<Void> {

	protected int currGeneration = 0;
	protected int lastKnownGeneration = -1;

	@Override
	public Void prepare(ResourceManager pResourceManager, ProfilerFiller pProfiler) {
		return null;
	}

	@Override
	public void apply(Void pObject, ResourceManager pResourceManager, ProfilerFiller pProfiler) {
		currGeneration++;
	}

	public abstract Set<Recipe<? extends AbstractOverdriveRecipe>> getRecipesByType(RecipeManager manager);

}
