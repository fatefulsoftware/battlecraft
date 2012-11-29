package net.minecraft.src;

public class SandbagBlock extends Block {
	protected static final Material material = Material.sand;
	
	protected SandbagBlock (int par1, Material par3Material) {
		super(par1, material);
		
		this.blockIndexInTexture = Block.stoneBrick.blockIndexInTexture;
		
		setBlockName("sandbag");
	}
	
	protected SandbagBlock (int par1) {
		this(par1, null);
	}
}