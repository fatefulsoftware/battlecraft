package net.minecraft.src;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class BattlecraftTeam {
	protected String name;
	protected Color color;
	protected List<EntityPlayer> players;
	protected Point spawn;

	public BattlecraftTeam(String name, Color color, Point spawn) {
		this.name = name;
		this.color = color;
		this.spawn = spawn;
		
		players = new ArrayList<EntityPlayer>();
	}
	
	public String getName () {
		return name;
	}
	
	public Color getColor () {
		return color;
	}
	
	public Point getSpawn () {
		return spawn;
	}
	
	public void addPlayer (EntityPlayer player) {
		System.out.println(String.format("Player %s joined %s", player.toString(), name));
		
		players.add(player);
	}
	
	public List<EntityPlayer> getPlayers () {
		return (List<EntityPlayer>)((ArrayList<EntityPlayer>)players).clone();
	}

	public int getPlayerSize() {
		return players.size();
	}

	public void removePlayer(EntityPlayer player) {
		System.out.println(String.format("Player %s left %s", player.toString(), name));
		
		players.remove(player);
	}
}