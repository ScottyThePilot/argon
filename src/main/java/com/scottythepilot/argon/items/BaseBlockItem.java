package com.scottythepilot.argon.items;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class BaseBlockItem extends BlockItem {
	public BaseBlockItem(Block block) {
		super(block, new Item.Properties());
	}
	
	public BaseBlockItem(Block block, ItemGroup group) {
		super(block, new Item.Properties().group(group));
	}
}
