package net.minecraft.src;

public class mod_Battlecraft extends BaseMod {
    public static final Item grenadeLauncher = (new GrenadeLauncherItem(201));

    public static final Block sandbagBlock = (new SandbagBlock(200));
	
	public mod_Battlecraft () {
        ModLoader.addName(sandbagBlock, sandbagBlock.getBlockName());
        ModLoader.addRecipe(new ItemStack(sandbagBlock), new Object[] {
			"X", Character.valueOf('X'), Block.cobblestone
		});
        ModLoader.registerBlock(sandbagBlock);
        
        ModLoader.addName(grenadeLauncher, grenadeLauncher.getItemName());
        ModLoader.addRecipe(new ItemStack(grenadeLauncher), new Object[] {
			"X", Character.valueOf('X'), Block.dirt
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