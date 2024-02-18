package matteroverdrive.datagen.server;

import matteroverdrive.References;
import matteroverdrive.registry.BlockRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

public class OverdriveBlockTagsProvider extends BlockTagsProvider {

	public OverdriveBlockTagsProvider(DataGenerator pGenerator, ExistingFileHelper existingFileHelper) {
		super(pGenerator, References.ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BlockRegistry.BLOCK_REGULAR_TRITANIUM_PLATING.get())
				.add(BlockRegistry.BLOCK_COLORED_TRITANIUM_PLATING.<Block>getObjectsAsArray(new Block[0]))
				.add(BlockRegistry.BLOCK_FLOOR_TILE.<Block>getObjectsAsArray(new Block[0]))
				.add(BlockRegistry.BLOCK_FLOOR_TILES.<Block>getObjectsAsArray(new Block[0]))
				.add(BlockRegistry.BLOCK_TRITANIUM_CRATES.<Block>getObjectsAsArray(new Block[0]))
				.add(BlockRegistry.BLOCK_SOLAR_PANEL.get()).add(BlockRegistry.BLOCK_MATTER_DECOMPOSER.get())
				.add(BlockRegistry.BLOCK_DECORATIVE_BEAMS.get()).add(BlockRegistry.BLOCK_DECORATIVE_CARBON_FIBER_PLATE.get())
				.add(BlockRegistry.BLOCK_DECORATIVE_CLEAN.get()).add(BlockRegistry.BLOCK_DECORATIVE_COILS.get())
				.add(BlockRegistry.BLOCK_MACHINE_HULL.get()).add(BlockRegistry.BLOCK_TRITANIUM.get())
				.add(BlockRegistry.BLOCK_MATTER_RECYCLER.get()).add(BlockRegistry.BLOCK_CHARGER.get())
				.add(BlockRegistry.BLOCK_MICROWAVE.get()).add(BlockRegistry.BLOCK_TRANSPORTER.get())
				.add(BlockRegistry.BLOCK_VENT_OPEN.get()).add(BlockRegistry.BLOCK_VENT_CLOSED.get())
				.add(BlockRegistry.BLOCK_ANDROID_STATION.get()).add(BlockRegistry.BLOCK_WEAPON_STATION.get())
				.add(BlockRegistry.BLOCK_SPACETIME_ACCELERATOR.get()).add(BlockRegistry.BLOCK_STAR_MAP.get())
				.add(BlockRegistry.BLOCK_MATTER_NETWORK_CABLES.getObjectsAsArray(new Block[0]))
				.add(BlockRegistry.BLOCK_CHUNKLOADER.get()).add(BlockRegistry.BLOCK_MATTER_ANALYZER.get())
				.add(BlockRegistry.BLOCK_PATTERN_STORAGE.get()).add(BlockRegistry.BLOCK_PATTERN_MONITOR.get())
				.add(BlockRegistry.BLOCK_MATTER_REPLICATOR.get()).add(BlockRegistry.DILITHIUM_ORE.get())
				.add(BlockRegistry.TRITANIUM_ORE.get()).add(BlockRegistry.DEEPSLATE_TRITANIUM_ORE.get())
				.add(BlockRegistry.DILITHIUM_ORE.get()).add(BlockRegistry.DEEPSLATE_DILITHIUM_ORE.get())
				.add(BlockRegistry.BLOCK_NETWORK_SWITCH.get()).add(BlockRegistry.BLOCK_INSCRIBER.get());

		tag(BlockTags.NEEDS_STONE_TOOL).add(BlockRegistry.BLOCK_REGULAR_TRITANIUM_PLATING.get())
				.add(BlockRegistry.BLOCK_COLORED_TRITANIUM_PLATING.<Block>getObjectsAsArray(new Block[0]))
				.add(BlockRegistry.BLOCK_FLOOR_TILE.<Block>getObjectsAsArray(new Block[0]))
				.add(BlockRegistry.BLOCK_FLOOR_TILES.<Block>getObjectsAsArray(new Block[0]))
				.add(BlockRegistry.BLOCK_TRITANIUM_CRATES.<Block>getObjectsAsArray(new Block[0]))
				.add(BlockRegistry.BLOCK_SOLAR_PANEL.get()).add(BlockRegistry.BLOCK_MATTER_DECOMPOSER.get())
				.add(BlockRegistry.BLOCK_MATTER_RECYCLER.get()).add(BlockRegistry.BLOCK_CHARGER.get())
				.add(BlockRegistry.BLOCK_MICROWAVE.get()).add(BlockRegistry.BLOCK_TRANSPORTER.get())
				.add(BlockRegistry.BLOCK_VENT_OPEN.get()).add(BlockRegistry.BLOCK_VENT_CLOSED.get())
				.add(BlockRegistry.BLOCK_SPACETIME_ACCELERATOR.get())
				.add(BlockRegistry.BLOCK_MATTER_NETWORK_CABLES.getObjectsAsArray(new Block[0]))
				.add(BlockRegistry.BLOCK_CHUNKLOADER.get()).add(BlockRegistry.BLOCK_MATTER_ANALYZER.get())
				.add(BlockRegistry.BLOCK_PATTERN_STORAGE.get()).add(BlockRegistry.BLOCK_PATTERN_MONITOR.get())
				.add(BlockRegistry.BLOCK_MATTER_REPLICATOR.get());

		tag(BlockTags.NEEDS_IRON_TOOL).add(BlockRegistry.BLOCK_REGULAR_TRITANIUM_PLATING.get())
				.add(BlockRegistry.TRITANIUM_ORE.get()).add(BlockRegistry.DEEPSLATE_TRITANIUM_ORE.get());
		
		tag(BlockTags.NEEDS_DIAMOND_TOOL).add(BlockRegistry.BLOCK_REGULAR_TRITANIUM_PLATING.get())
				.add(BlockRegistry.DILITHIUM_ORE.get()).add(BlockRegistry.DEEPSLATE_DILITHIUM_ORE.get());

	}
	
	
	private static TagKey<Block> forgeTag(String name){
		return BlockTags.create(new ResourceLocation("forge", name));
	}

}
