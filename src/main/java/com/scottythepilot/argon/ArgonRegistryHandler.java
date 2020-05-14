package com.scottythepilot.argon;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.scottythepilot.argon.blocks.CloneBlock;
import com.scottythepilot.argon.blocks.FertilizedFarmlandBlock;
import com.scottythepilot.argon.effects.BaseEffect;
import com.scottythepilot.argon.items.BaseBlockItem;
import com.scottythepilot.argon.items.BaseItem;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.potion.Potions;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ArgonRegistryHandler {
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, ArgonMod.MOD_ID);
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, ArgonMod.MOD_ID);
	public static final DeferredRegister<Effect> EFFECTS = new DeferredRegister<>(ForgeRegistries.POTIONS, ArgonMod.MOD_ID);
	public static final DeferredRegister<Potion> POTIONS = new DeferredRegister<>(ForgeRegistries.POTION_TYPES, ArgonMod.MOD_ID);

	private static Method brewingMixes = null;
	
	public static void init() {
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
		POTIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	public static void addBrewingRecipes() {
		addMix(Potions.AWKWARD, SALTPETER.get(), ANTI_POISON.get());
		addMix(ANTI_POISON.get(), Items.REDSTONE, LONG_ANTI_POISON.get());
	}
	
	private static void addMix(Potion input, Item reagent, Potion result) {
		if (brewingMixes == null) {
			brewingMixes = ObfuscationReflectionHelper.findMethod(PotionBrewing.class, "addMix", Potion.class, Item.class, Potion.class);
			brewingMixes.setAccessible(true);
		}
		
		try {
			brewingMixes.invoke(null, input, reagent, result);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public static final RegistryObject<Block> SULFUR_ORE_BLOCK = BLOCKS.register("nether_sulfur_ore", () -> new CloneBlock(Blocks.IRON_ORE));
	public static final RegistryObject<Block> SALTPETER_ORE_BLOCK = BLOCKS.register("saltpeter_ore", () -> new CloneBlock(Blocks.IRON_ORE));
	public static final RegistryObject<Block> FERTILIZED_DIRT_BLOCK = BLOCKS.register("fertilized_dirt", () -> new CloneBlock(Blocks.DIRT));
	public static final RegistryObject<Block> FERTILIZED_FARMLAND_BLOCK = BLOCKS.register("fertilized_farmland", FertilizedFarmlandBlock::new);
	
	public static final RegistryObject<BlockItem> SULFUR_ORE_ITEM = ITEMS.register("nether_sulfur_ore", () -> new BaseBlockItem(SULFUR_ORE_BLOCK.get(), ItemGroup.BUILDING_BLOCKS));
	public static final RegistryObject<BlockItem> SALTPETER_ORE_ITEM = ITEMS.register("saltpeter_ore", () -> new BaseBlockItem(SALTPETER_ORE_BLOCK.get(), ItemGroup.BUILDING_BLOCKS));
	public static final RegistryObject<BlockItem> FERTILIZED_DIRT_ITEM = ITEMS.register("fertilized_dirt", () -> new BaseBlockItem(FERTILIZED_DIRT_BLOCK.get()));
	public static final RegistryObject<BlockItem> FERTILIZED_FARMLAND_ITEM = ITEMS.register("fertilized_farmland", () -> new BaseBlockItem(FERTILIZED_FARMLAND_BLOCK.get()));
	public static final RegistryObject<Item> SULFUR = ITEMS.register("sulfur", () -> new BaseItem(ItemGroup.MISC));
	public static final RegistryObject<Item> SALTPETER = ITEMS.register("saltpeter", () -> new BaseItem(ItemGroup.MISC));
	public static final RegistryObject<Item> TANNED_FLESH = ITEMS.register("tanned_flesh", () -> new BaseItem(ItemGroup.MISC));
	
	public static final RegistryObject<Effect> ANTI_POISON_EFFECT = EFFECTS.register("anti_poison", () -> new BaseEffect(EffectType.BENEFICIAL, 782785));
	public static final RegistryObject<Potion> ANTI_POISON = POTIONS.register("anti_poison", () -> new Potion(new EffectInstance(ANTI_POISON_EFFECT.get(), 900)));
	public static final RegistryObject<Potion> LONG_ANTI_POISON = POTIONS.register("long_anti_poison", () -> new Potion(new EffectInstance(ANTI_POISON_EFFECT.get(), 1800)));
}
