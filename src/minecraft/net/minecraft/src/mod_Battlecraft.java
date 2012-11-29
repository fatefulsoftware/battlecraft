package net.minecraft.src;

public class mod_Battlecraft extends BaseMod {
	public static final Item grenade = new GrenadeItem(201);
    public static final Item grenadeLauncher = (new GrenadeLauncherItem(202));

    public static final Block sandbagBlock = (new SandbagBlock(200));
	
	public mod_Battlecraft () {
        ModLoader.addName(sandbagBlock, "Sandbag");
        ModLoader.addRecipe(new ItemStack(sandbagBlock), new Object[] {
			"XXX", "XSX", "XXX", 'X', Block.cloth, 'S', Block.sand
		});
        ModLoader.registerBlock(sandbagBlock);
        
        ModLoader.addName(grenade, "Grenade");
        ModLoader.addRecipe(new ItemStack(grenade), new Object[] {
            "###", "#X#", "###", Character.valueOf('#'), Item.ingotIron, Character.valueOf('X'), Item.gunpowder
        });
        
        ModLoader.registerEntityID(GrenadeEntity.class, "Grenade", ModLoader.getUniqueEntityId());
        
        ModLoader.addName(grenadeLauncher, "Grenade Launcher");
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