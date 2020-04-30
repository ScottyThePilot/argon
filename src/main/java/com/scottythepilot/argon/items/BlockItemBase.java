package com.scottythepilot.argon.items;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class BlockItemBase extends BlockItem {
	public BlockItemBase(Block block, ItemGroup group) {
		super(block, new Item.Properties().group(group));
	}
}
