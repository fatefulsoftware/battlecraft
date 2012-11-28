package net.minecraft.src;

public class SandbagItem extends ItemBlock {
	public SandbagItem () {
		super(Block.sandbag.blockID - 256);
		
		setItemName(Block.sandbag.getBlockName());
	}
}
