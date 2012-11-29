package net.minecraft.src;

public class mod_Battlecraft extends BaseMod {
	//public static final Item sandbag = new SandbagItem();
    public static final Item grenadeLauncher = (new GrenadeLauncherItem(201));

    //public static final Block sandbagBlock = (new SandbagBlock(200));
	
	public mod_Battlecraft () {
        //ModLoader.addName(sandbag, "Sandbag");
        
        //ModLoader.addName(sandbagBlock, "Sandbag");
        //ModLoader.registerBlock(sandbagBlock);
        
        ModLoader.addName(grenadeLauncher, "Grenade Launcher");
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