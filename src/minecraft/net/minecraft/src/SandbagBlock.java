package net.minecraft.src;

public class SandbagBlock extends Block {
	protected static final Material material = Material.sand;
	
	protected SandbagBlock (int par1, Material par3Material) {
		super(par1, material);
		
		setBlockName("sandbag");
		
		blockIndexInTexture = 0;
	}
	
	protected SandbagBlock (int par1) {
		this(par1, null);
	}
	
	@Override
	public String getTextureFile () {
		return mod_Battlecraft.TEXTURE;
	}
}