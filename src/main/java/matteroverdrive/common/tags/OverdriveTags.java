package matteroverdrive.common.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class OverdriveTags {
	
	public static void init() {
		Items.init();

		Blocks.init();
	}
	
	public static class Items {
		
		public static final TagKey<Item> CIRCUITS_BASIC = forgeTag("circuits/basic");
		public static final TagKey<Item> CIRCUITS_ADVANCED = forgeTag("circuits/advanced");
		public static final TagKey<Item> CIRCUITS_ELITE = forgeTag("circuits/elite");
		public static final TagKey<Item> CIRCUITS_ULTIMATE = forgeTag("circuits/ultimate");
		public static final TagKey<Item> RAW_FOOD = forgeTag("raw_food");
		public static final TagKey<Item> AMETHYST_BUDS = forgeTag("amethyst_buds");
		public static final TagKey<Item> WEATHER_COPPER_BLOCKS = forgeTag("weather_copper_blocks");
		public static final TagKey<Item> CORAL = forgeTag("coral");
		public static final TagKey<Item> GOLD_INGOT = forgeTag("ingots/gold");
		public static final TagKey<Item> DIAMOND_GEM = forgeTag("gems/diamond");
		public static final TagKey<Item> EMERALD_GEM = forgeTag("gems/emerald");
		public static final TagKey<Item> INGOT_TRITANIUM = forgeTag("ingots/tritanium");
		public static final TagKey<Item> GEM_DILITHIUM = forgeTag("gems/dilithium");
		public static final TagKey<Item> NUGGET_TRITANIUM = forgeTag("nuggets/tritanium");
		public static final TagKey<Item> BLOCK_TRITANIUM = forgeTag("blocks/tritanium");
		public static final TagKey<Item> COLORED_PLATING = forgeTag("blocks/colored_plating");

		private static void init() {}
		
		private static TagKey<Item> forgeTag(String name) {
			return ItemTags.create(new ResourceLocation("forge", name));
		}
	}

	public static class Blocks {
//		public static final TagKey<Block> BLOCK_TRITANIUM = forgeTag("blocks/tritanium");

		private static void init() {}

		private static TagKey<Block> forgeTag(String name) {
			return BlockTags.create(new ResourceLocation("forge", name));
		}
	}
}
