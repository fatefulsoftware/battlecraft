package net.minecraft.src;

public class mod_Battlecraft extends BaseMod {
    public static final Item grenadeLauncher = (new GrenadeLauncherItem(201));

    public static final Block sandbagBlock = (new SandbagBlock(200));
	
	public mod_Battlecraft () {
        ModLoader.addName(sandbagBlock, sandbagBlock.getBlockName());
        ModLoader.addRecipe(new ItemStack(sandbagBlock), new Object[] {
			"XXX", "XSX", "XXX", 'X', Block.cloth, 'S', Block.sand
		});
        ModLoader.registerBlock(sandbagBlock);
        
        ModLoader.addName(grenadeLauncher, grenadeLauncher.getItemName());
        ModLoader.addRecipe(new ItemStack(grenadeLauncher), new Object[] {
			"XXX", " X ", 'X', Item.ingotIron
		});
	}
	
	@Override
	public String getVersion() {
		return "1";
	}

	@Override
	public void load() {
	}
}