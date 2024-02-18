package matteroverdrive.core.world;

import matteroverdrive.References;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class PlacedFeatureInit {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, References.ID);

    public static final RegistryObject<PlacedFeature> DILITHIUM_ORE = PLACED_FEATURES.register("dilithium_ore",
            () -> new PlacedFeature(ConfiguredFeatureInit.DILITHIUM_ORE.getHolder().get(),
                    commonOrePlacement(7, HeightRangePlacement.triangle(
                            VerticalAnchor.bottom(),
                            VerticalAnchor.absolute(40)
                    ))));

    public static final RegistryObject<PlacedFeature> TRITANIUM_ORE = PLACED_FEATURES.register("tritanium_ore",
            () -> new PlacedFeature(ConfiguredFeatureInit.TRITANIUM_ORE.getHolder().get(),
                    commonOrePlacement(7, HeightRangePlacement.triangle(
                            VerticalAnchor.bottom(),
                            VerticalAnchor.absolute(40)
                    ))));
    private static List<PlacementModifier> commonOrePlacement(int countPerChunk, PlacementModifier height) {
        return orePlacement(CountPlacement.of(countPerChunk), height);
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier count, PlacementModifier height) {
        return List.of(count, InSquarePlacement.spread(), height, BiomeFilter.biome());
    }
}