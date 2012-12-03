package net.minecraft.src;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GrenadeItem extends Item {
	static Map<EntityPlayer, Long> lastTimestamps = new HashMap<EntityPlayer, Long>();
	
	public GrenadeItem (int par1) {
		super(par1);
		
		setIconIndex(1);
		setItemName("grenade");
	}
	
	@Override
	public ItemStack onItemRightClick (ItemStack par1ItemStack, World world,
			EntityPlayer player) {
		long now, last;
		Long obj;
		
		obj = lastTimestamps.get(player);
		
		if (obj == null)
			last = 0;
		else
			last = obj.longValue();
		
		now = Calendar.getInstance().getTime().getTime();
		
		// TODO: for some reason skipping this custom logic *some* of the time causes explosion and click sounds not to play
		if (last == 0 || (now - last) >= 1000) {
			lastTimestamps.put(player, Long.valueOf(now));
			
	        par1ItemStack.stackSize--;
			
	        world.spawnEntityInWorld(new GrenadeEntity(world, player));
		}
		
		return super.onItemRightClick(par1ItemStack, world, player);
	}
    
    @Override
    public boolean hitEntity (ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving) {
    	par1ItemStack.damageItem(1, par2EntityLiving);
    	
    	return true;
    }
    
    @Override
    public String getTextureFile () {
    	return mod_Battlecraft.TEXTURE;
    }
}