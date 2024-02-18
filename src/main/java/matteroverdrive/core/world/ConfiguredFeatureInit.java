package matteroverdrive.core.world;

import java.util.List;
import java.util.function.Supplier;

import com.google.common.base.Suppliers;

import matteroverdrive.References;
import matteroverdrive.registry.BlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ConfiguredFeatureInit {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, References.ID);

    private static final Supplier<List<OreConfiguration.TargetBlockState>> DILITHIUM_REPLACEMENT = Suppliers.memoize(() ->
    		List.of(
            		OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockRegistry.DILITHIUM_ORE.get().defaultBlockState()),
            		OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockRegistry.DEEPSLATE_DILITHIUM_ORE.get().defaultBlockState())
    			)
    );

    private static final Supplier<List<OreConfiguration.TargetBlockState>> TRITANIUM_REPLACEMENT = Suppliers.memoize(() ->
		List.of(
    			OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockRegistry.TRITANIUM_ORE.get().defaultBlockState()),
    			OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockRegistry.DEEPSLATE_TRITANIUM_ORE.get().defaultBlockState())
    			)
);

    public static final RegistryObject<ConfiguredFeature<?, ?>> DILITHIUM_ORE = CONFIGURED_FEATURES.register("dilithium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(DILITHIUM_REPLACEMENT.get(), 11)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> TRITANIUM_ORE = CONFIGURED_FEATURES.register("tritanium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(TRITANIUM_REPLACEMENT.get(), 11)));
}