package net.minecraft.src;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;

public class mod_Battlecraft extends BaseMod implements OnPlayerJoinedListener, OnPlayerLeftListener {
	public static final Item grenade = new GrenadeItem(201);
    public static final Item grenadeLauncher = (new GrenadeLauncherItem(202));

    public static final Block sandbagBlock = (new SandbagBlock(200));
    
    protected List<BattlecraftTeam> teams;
    protected Map<EntityPlayer, BattlecraftTeam> players;
	
	public mod_Battlecraft () {
		teams = new ArrayList<BattlecraftTeam>();
		teams.add(new BattlecraftTeam("Red", Color.RED, new Point(-50, 0)));
		teams.add(new BattlecraftTeam("Blue", Color.BLUE, new Point(50, 0)));
		
		players = new HashMap<EntityPlayer, BattlecraftTeam>();
		
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
        
        BattlecraftPlayerTracker.getSharedInstance().addJoinListener(this);
        BattlecraftPlayerTracker.getSharedInstance().addLeaveListener(this);
	}
	
	protected void addPlayer (EntityPlayer player) {
		BattlecraftTeam smallestTeam, team;
		int i, y;
		World world;
		
		smallestTeam = teams.get(0);
		
		for (i = 1; i < teams.size(); i++) {
			team = teams.get(i);
			
			if (team.getPlayerSize() < smallestTeam.getPlayerSize())
				smallestTeam = team;
		}
		
		smallestTeam.addPlayer(player);
		players.put(player, smallestTeam);

		//world = ModLoader.getMinecraftInstance().thePlayer.worldObj;
		//y = world.getFirstUncoveredBlock(smallestTeam.getSpawn().x, smallestTeam.getSpawn().y);
		
		//world.setSpawnLocation(smallestTeam.getSpawn().x, 100, smallestTeam.getSpawn().y));
	    //ModLoader.getMinecraftInstance().thePlayer.setPosition(smallestTeam.getSpawn().x, y, smallestTeam.getSpawn().y);
	}
	
	protected void removePlayer (EntityPlayer player) {
		players.get(player).removePlayer(player);
		players.remove(player);
	}
	
	//@Override
	public String getVersion() {
		return "1";
	}

	//@Override
	public void load() {
	}

	@Override
	public void onPlayerLeft (EntityPlayer player) {
		removePlayer(player);
	}

	@Override
	public void onPlayerJoined (EntityPlayer player) {
		addPlayer(player);
	}
}