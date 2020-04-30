package com.scottythepilot.argon;

import com.scottythepilot.argon.util.RegistryHandler;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class ArgonOreGen {
	public static void generateOre() {
		for (Biome biome: ForgeRegistries.BIOMES) {
			biome.addFeature(
				GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.ORE
					.withConfiguration(new OreFeatureConfig(
						OreFeatureConfig.FillerBlockType.NATURAL_STONE,
						RegistryHandler.SALTPETER_ORE_BLOCK.get().getDefaultState(),
						14
					))
					.withPlacement(
						Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 128))
					)
			);
			
			biome.addFeature(
				GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.ORE
					.withConfiguration(new OreFeatureConfig(
						OreFeatureConfig.FillerBlockType.NETHERRACK,
						RegistryHandler.SULFUR_ORE_BLOCK.get().getDefaultState(),
						10
					))
					.withPlacement(
						Placement.COUNT_RANGE.configure(new CountRangeConfig(12, 0, 0, 256))
					)
			);
		}
	}
}
