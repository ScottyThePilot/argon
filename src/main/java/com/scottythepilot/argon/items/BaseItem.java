package com.scottythepilot.argon.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class BaseItem extends Item {
	public BaseItem(ItemGroup group) {
		super(new Item.Properties().group(group));
	}
}
