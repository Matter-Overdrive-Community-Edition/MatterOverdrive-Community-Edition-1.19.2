package matteroverdrive.datagen.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import com.google.common.hash.Hashing;
import com.google.common.hash.HashingOutputStream;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import matteroverdrive.common.tags.OverdriveTags;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Items;

public class OverdriveMatterValueGenerator implements DataProvider {

	private static final String DATA_LOC = "data/matteroverdrive/matter/values.json";
	private DataGenerator gen;

	public OverdriveMatterValueGenerator(DataGenerator gen) {
		this.gen = gen;
	}

	@Override
	public void run(CachedOutput cache) throws IOException {
		JsonObject json = new JsonObject();
		addValues(json);
		Path path = gen.getOutputFolder().resolve(DATA_LOC);
		try {
			ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
			HashingOutputStream hash = new HashingOutputStream(Hashing.sha1(), byteArray);
			Writer writer = new OutputStreamWriter(hash, StandardCharsets.UTF_8);
			JsonWriter jsonWriter = new JsonWriter(writer);
			jsonWriter.setSerializeNulls(false);
			jsonWriter.setIndent("  ");
			GsonHelper.writeValue(jsonWriter, json, KEY_COMPARATOR);
			jsonWriter.close();
			cache.writeIfNeeded(path, byteArray.toByteArray(), hash.hash());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void addValues(JsonObject json) {
		json.addProperty("minecraft:basalt", 4);
		json.addProperty("minecraft:bamboo", 1);
		json.addProperty("minecraft:bedrock", 1024);
		json.addProperty("minecraft:bell", 10);
		json.addProperty("minecraft:bee_nest", 10);
		json.addProperty("minecraft:big_dripleaf", 2);
		json.addProperty("minecraft:blackstone", 4);
		json.addProperty("minecraft:brown_mushroom_block", 1);
		json.addProperty("minecraft:budding_amethyst", 1);
		json.addProperty("minecraft:cactus", 4);
		json.addProperty("minecraft:calcite", 1);
		json.addProperty("minecraft:carved_pumpkin", 2);
		json.addProperty("minecraft:chorus_plant", 1);
		json.addProperty("minecraft:chorus_flower", 1);
		json.addProperty("minecraft:chorus_fruit", 1);
		json.addProperty("minecraft:clay_ball", 1);
		json.addProperty("#" + ItemTags.COAL_ORES.location().toString(), 16);
		json.addProperty("minecraft:cocoa_beans", 1);
		json.addProperty("minecraft:cobweb", 1);
		json.addProperty("#" + ItemTags.COPPER_ORES.location().toString(), 16);
		json.addProperty("minecraft:crimson_fungus", 1);
		json.addProperty("minecraft:crimson_nylium", 1);
		json.addProperty("minecraft:crimson_roots", 1);
		json.addProperty("minecraft:crying_obsidian", 16);
		json.addProperty("minecraft:dead_bush", 1);
		json.addProperty("#" + ItemTags.DIAMOND_ORES.location().toString(), 512);
		json.addProperty("minecraft:diamond_horse_armor", 1280);
		json.addProperty("#" + ItemTags.DIRT.location().toString(), 1);
		json.addProperty("minecraft:dirt_path", 1);
		json.addProperty("#" + ItemTags.EMERALD_ORES.location().toString(), 512);
		json.addProperty("minecraft:enchanted_golden_apple", 5000);
		json.addProperty("minecraft:end_portal_frame", 1024);
		json.addProperty("minecraft:elytra", 100);
		json.addProperty("minecraft:farmland", 1);
		json.addProperty("minecraft:fern", 2);
		json.addProperty("minecraft:flint", 1);
		json.addProperty("#" + ItemTags.FLOWERS.location().toString(), 1);
		json.addProperty("minecraft:glow_lichen", 1);
		json.addProperty("#" + ItemTags.GOLD_ORES.location().toString(), 84);
		json.addProperty("minecraft:grass", 1);
		json.addProperty("minecraft:ghast_tear", 8);
		json.addProperty("minecraft:gilded_blackstone", 4);
		json.addProperty("minecraft:glow_ink_sac", 1);
		json.addProperty("minecraft:gold_horse_armor", 210);
		json.addProperty("minecraft:hanging_roots", 1);
		json.addProperty("minecraft:heart_of_the_sea", 1000);
		json.addProperty("minecraft:honeycomb", 1);
		json.addProperty("minecraft:ice", 1);
		json.addProperty("minecraft:iron_horse_armor", 160);
		json.addProperty("minecraft:ink_sac", 1);
		json.addProperty("#" + ItemTags.IRON_ORES.location().toString(), 64);
		json.addProperty("minecraft:kelp", 1);
		json.addProperty("#" + ItemTags.LAPIS_ORES.location().toString(), 16);
		json.addProperty("minecraft:large_fern", 1);
		json.addProperty("minecraft:lava_bucket", 250);
		json.addProperty("#" + ItemTags.LEAVES.location().toString(), 1);
		json.addProperty("minecraft:lily_pad", 1);
		json.addProperty("#" + ItemTags.LOGS.location().toString(), 16);
		json.addProperty("minecraft:milk_bucket", 193);
		json.addProperty("#" + ItemTags.MUSIC_DISCS.location().toString(), 1);
		json.addProperty("minecraft:mushroom_stem", 1);
		json.addProperty("minecraft:nautilus_shell", 4);
		json.addProperty("minecraft:nether_sprouts", 1);
		json.addProperty("minecraft:name_tag", 10);
		json.addProperty("minecraft:pointed_dripstone", 1);
		json.addProperty("minecraft:powder_snow_bucket", 193);
		json.addProperty("minecraft:pumpkin", 2);
		json.addProperty("minecraft:rabbit_hide", 1);
		json.addProperty("minecraft:rabbit_foot", 10);
		json.addProperty("#" + ItemTags.REDSTONE_ORES.location().toString(), 16);
		json.addProperty("minecraft:red_mushroom_block", 1);
		json.addProperty("minecraft:phantom_membrane", 10);
		json.addProperty("#" + ItemTags.SAND.location().toString(), 2);
		json.addProperty("#" + ItemTags.SAPLINGS.location().toString(), 2);
		json.addProperty("minecraft:scute", 10);
		json.addProperty("minecraft:seagrass", 1);
		json.addProperty("minecraft:sea_pickle", 1);
		json.addProperty("minecraft:shulker_shell", 20);
		json.addProperty("minecraft:shroomlight", 4);
		json.addProperty("minecraft:small_dripleaf", 2);
		json.addProperty("minecraft:snowball", 1);
		json.addProperty("minecraft:soul_sand", 4);
		json.addProperty("minecraft:soul_soil", 4);
		json.addProperty("minecraft:sugar_cane", 1);
		json.addProperty("minecraft:spore_blossom", 2);
		json.addProperty("minecraft:tall_grass", 1);
		json.addProperty("minecraft:twisting_vines", 1);
		json.addProperty("minecraft:vine", 1);
		json.addProperty("minecraft:warped_fungus", 1);
		json.addProperty("minecraft:warped_nylium", 1);
		json.addProperty("minecraft:warped_roots", 1);
		json.addProperty("minecraft:water_bottle", 3);
		json.addProperty("minecraft:water_bucket", 193);
		json.addProperty("minecraft:weeping_vines", 1);
		json.addProperty("minecraft:wet_sponge", 8);

// Forge Blocks Tags - json.addProperty("", 1);
// 		Barrels
		json.addProperty("#forge:barrels/wooden", 2);
//      Chests
		json.addProperty("#forge:chests/ender", 1);
		json.addProperty("#forge:chests/trapped", 1);
		json.addProperty("#forge:chests/wooden", 1);
//		Cobblestones
		json.addProperty("#forge:cobblestone/normal", 1);
		json.addProperty("#forge:cobblestone/infested", 1);
		json.addProperty("#forge:cobblestone/mossy", 1);
		json.addProperty("#forge:cobblestone/deepslate", 1);
//		End stones
		json.addProperty("#forge:end_stones", 1);
//		Fence gates
		json.addProperty("#forge:fence_gates", 1);
//		Nether brick fences
		json.addProperty("#forge:fences/nether_brick", 1);
//		Wooden fences
		json.addProperty("#forge:fences/wooden", 1);
//		Glass
		json.addProperty("#forge:glass", 1);
//		Tinted Glass
		json.addProperty("#forge:glass/tinted", 1);
//		Normal glass panes
		json.addProperty("#forge:glass_panes", 1);
//		Gravel
		json.addProperty("#forge:gravel",1);
//		Netherrack
		json.addProperty("#forge:netherrack", 1);
//		Obsidian
		json.addProperty("#forge:obsidian", 16);
//		Ores
		json.addProperty("#forge:ores/coal", 1);
		json.addProperty("#forge:ores/copper", 1);
		json.addProperty("#forge:ores/diamond", 1);
		json.addProperty("#forge:ores/emerald", 1);
		json.addProperty("#forge:ores/gold", 1);
		json.addProperty("#forge:ores/iron", 1);
		json.addProperty("#forge:ores/lapis", 1);
		json.addProperty("#forge:ores/redstone", 1);
		json.addProperty("#forge:ores/quartz", 1);
		json.addProperty("#forge:ores/netherite_scrap", 1);
//		Normal Sand
		json.addProperty("#forge:sand/colorless", 1);
//		Red Sand
		json.addProperty("#forge:sand/red", 1);
//		Sandstone
		json.addProperty("#forge:sandstone", 1);
//		Stained glass
		json.addProperty("#forge:stained_glass", 1);
//		Stained glass panes
		json.addProperty("#forge:stained_glass_panes", 1);
//		Stone
		json.addProperty("#forge:stone", 1);
// 		Storage Blocks
		json.addProperty("#forge:storage_blocks/amethyst", 1);
		json.addProperty("#forge:storage_blocks/coal", 1);
		json.addProperty("#forge:storage_blocks/copper", 1);
		json.addProperty("#forge:storage_blocks/diamond", 1);
		json.addProperty("#forge:storage_blocks/emerald", 1);
		json.addProperty("#forge:storage_blocks/gold", 1);
		json.addProperty("#forge:storage_blocks/iron", 1);
		json.addProperty("#forge:storage_blocks/lapis", 1);
		json.addProperty("#forge:storage_blocks/quartz", 1);
		json.addProperty("#forge:storage_blocks/raw_copper", 1);
		json.addProperty("#forge:storage_blocks/raw_gold", 1);
		json.addProperty("#forge:storage_blocks/raw_iron", 1);
		json.addProperty("#forge:storage_blocks/redstone", 1);
		json.addProperty("#forge:storage_blocks/netherite", 1);

// Forge Items Tags - json.addProperty("", 1);
//		Barrels
		json.addProperty("#forge:barrels/wooden", 2);
//		Bones
		json.addProperty("#forge:bones", 2);
//		Bookshelves
		json.addProperty("#forge:bookshelves", 1);
//		Normal cobblestones
		json.addProperty("#forge:cobblestone/normal", 1);
//		Infested cobblestones
		json.addProperty("#forge:cobblestone/infested", 1);
//		Mossy cobblestones
		json.addProperty("#forge:cobblestone/mossy", 1);
//		Deepslate cobblestones
		json.addProperty("#forge:cobblestone/deepslate", 1);
//		Beetroot crops
		json.addProperty("#forge:crops/beetroot", 1);
//		Carrot crops
		json.addProperty("#forge:crops/carrot", 1);
//		Nether wart crops
		json.addProperty("#forge:crops/nether_wart", 1);
//		Potato crops
		json.addProperty("#forge:crops/potato", 1);
//		Wheat crops
		json.addProperty("#forge:crops/wheat", 1);
// 		Dusts Blocks
		json.addProperty("#forge:dusts/prismarine", 4);
		json.addProperty("#forge:dusts/glowstone", 2);
		json.addProperty("#forge:dusts/redstone", 4);
//		Dyes
		json.addProperty("#forge:dyes", 1);
//		Eggs
		json.addProperty("#forge:eggs", 1);
//		End Stones
		json.addProperty("#forge:end_stones", 1);
//		Ender pearls
		json.addProperty("#forge:ender_pearls", 8);
//		Feathers
		json.addProperty("#forge:feathers", 1);
//		Amethyst gems
		json.addProperty("#forge:gems/amethyst", 4);
//		Diamond gems
		json.addProperty("#forge:gems/diamond", 1);
//		Emerald gems
		json.addProperty("#forge:gems/emerald", 1);
//		Lapis gems
		json.addProperty("#forge:gems/lapis", 1);
//		Prismarine gems
		json.addProperty("#forge:gems/prismarine", 4);
//		Quartz gems
		json.addProperty("#forge:gems/quartz", 4);
//		Gravel
		json.addProperty("#forge:gravel", 2);
//		Gunpowder		
		json.addProperty("#forge:gunpowder", 2);
//		Heads
		json.addProperty("#forge:heads", 12);
//		Brick ingots
		json.addProperty("#forge:ingots/brick", 1);
//		Copper ingots
		json.addProperty("#forge:ingots/copper", 1);
//		Gold ingots
		json.addProperty("#forge:ingots/gold", 1);
//		Iron ingots
		json.addProperty("#forge:ingots/iron", 1);
//		Netherite ingots
		json.addProperty("#forge:ingots/netherite", 1);
//		Nether brick ingots
		json.addProperty("#forge:ingots/nether_brick", 1);		
//		Leather
		json.addProperty("#forge:leather", 3);
//		Mushrooms
		json.addProperty("#forge:mushrooms", 1);
//		Nether Stars
		json.addProperty("#forge:nether_stars", 1012);
//		Netherrack
		json.addProperty("#forge:netherrack", 1);
//		Gold nuggets
		json.addProperty("#forge:nuggets/gold", 1);
//		Iron nuggets
		json.addProperty("#forge:nuggets/iron", 1);
//		Rods
		json.addProperty("#forge:rods/blaze", 4);
		json.addProperty("#forge:rods/wooden", 1);
//		Misc
		json.addProperty("#forge:raw_food", 2);
		json.addProperty("#forge:raw_materials/copper", 16);
		json.addProperty("#forge:raw_materials/gold", 84);
		json.addProperty("#forge:raw_materials/iron", 64);
		json.addProperty("#forge:string", 1);
		json.addProperty("#forge:seeds", 1);
		json.addProperty("#forge:slimeballs", 2);
		json.addProperty("#forge:weather_copper_blocks", 144);
		json.addProperty("#forge:coral", 1);
		json.addProperty("#forge:amethyst_buds", 4);
		// OverdriveTags matter providers.
		json.addProperty("#" + OverdriveTags.Items.CIRCUITS_BASIC.location(), 10);
		json.addProperty("#" + OverdriveTags.Items.CIRCUITS_ADVANCED.location(), 20);
		json.addProperty("#" + OverdriveTags.Items.CIRCUITS_ELITE.location(), 40);
		json.addProperty("#" + OverdriveTags.Items.CIRCUITS_ULTIMATE.location(), 80);
		json.addProperty("#" + OverdriveTags.Items.RAW_FOOD.location(), 5);
		json.addProperty("#" + OverdriveTags.Items.AMETHYST_BUDS.location(), 10);
		json.addProperty("#" + OverdriveTags.Items.WEATHER_COPPER_BLOCKS.location(), 144);
		json.addProperty("#" + OverdriveTags.Items.CORAL.location(), 10);
		json.addProperty("#" + OverdriveTags.Items.DIAMOND_GEM.location(), 64);

		json.addProperty("#" + ItemTags.ANVIL.location(), 31);
	}

	@Override
	public String getName() {
		return "Matter Generator";
	}

}
