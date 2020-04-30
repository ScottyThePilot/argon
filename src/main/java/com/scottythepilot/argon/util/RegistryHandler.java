package com.scottythepilot.argon.util;

import com.scottythepilot.argon.ArgonMod;
import com.scottythepilot.argon.blocks.BlockBase;
import com.scottythepilot.argon.items.BlockItemBase;
import com.scottythepilot.argon.items.ItemBase;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, ArgonMod.MOD_ID);
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, ArgonMod.MOD_ID);

	public static void init() {
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	public static final RegistryObject<Item> SULFUR = ITEMS.register("sulfur", () -> new ItemBase(ItemGroup.MISC));
	public static final RegistryObject<Item> SALTPETER = ITEMS.register("saltpeter", () -> new ItemBase(ItemGroup.MISC));
	
	public static final RegistryObject<Block> SULFUR_ORE_BLOCK = BLOCKS.register("nether_sulfur_ore", () -> new BlockBase(Material.ROCK, 3.0f, 3.0f, ToolType.PICKAXE, 1));
	public static final RegistryObject<BlockItem> SULFUR_ORE_ITEM = ITEMS.register("nether_sulfur_ore", () -> new BlockItemBase(SULFUR_ORE_BLOCK.get(), ItemGroup.BUILDING_BLOCKS));
	
	public static final RegistryObject<Block> SALTPETER_ORE_BLOCK = BLOCKS.register("saltpeter_ore", () -> new BlockBase(Material.ROCK, 3.0f, 3.0f, ToolType.PICKAXE, 1));
	public static final RegistryObject<BlockItem> SALTPETER_ORE_ITEM = ITEMS.register("saltpeter_ore", () -> new BlockItemBase(SALTPETER_ORE_BLOCK.get(), ItemGroup.BUILDING_BLOCKS));
}
