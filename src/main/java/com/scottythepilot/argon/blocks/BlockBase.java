package com.scottythepilot.argon.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockBase extends Block {
	public BlockBase(Material material, float hardness, float resistance, ToolType tool, int harvestLevel) {
		super(Block.Properties.create(material).hardnessAndResistance(hardness, resistance).harvestTool(tool).harvestLevel(harvestLevel));
	}
}
