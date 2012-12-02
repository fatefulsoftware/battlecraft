package net.minecraft.src;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class GrenadeLauncherItem extends Item {
	static Map<EntityPlayer, Long> lastTimestamps = new HashMap<EntityPlayer, Long>();
	
    public GrenadeLauncherItem (int par1) {
        super(par1);
        
        maxStackSize = 1;
        setMaxDamage(1000);
        setCreativeTab(CreativeTabs.tabCombat);
        setItemName("grenadeLauncher");
        setFull3D();
        iconIndex = ModLoader.addOverride("/gui/items.png", "/battlecraft/grenadelauncher.png");
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World world,
    		EntityPlayer player) {
		long now, last;
		Long obj;

        // has ammo
		if (player.inventory.hasItem(mod_Battlecraft.grenade.shiftedIndex)) {
			obj = lastTimestamps.get(player);
			
			if (obj == null)
				last = 0;
			else
				last = obj.longValue();
			
			now = Calendar.getInstance().getTime().getTime();
			
			if (last == 0 || (now - last) >= 1000) {
				lastTimestamps.put(player, Long.valueOf(now));
	
	            par1ItemStack.damageItem(1, player);
	
	            player.inventory.consumeInventoryItem(mod_Battlecraft.grenade.shiftedIndex);
	            
	            world.playSoundAtEntity(player, "random.click", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.5F);
				
		        world.spawnEntityInWorld(new GrenadeEntity(world, player));
			}
		}
		
		return super.onItemRightClick(par1ItemStack, world, player);
    }
    
    @Override
    public boolean hitEntity (ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving) {
    	par1ItemStack.damageItem(1, par2EntityLiving);
    	
    	return true;
    }

    @Override
    public boolean shouldRotateAroundWhenRendering () {
        return true;
    }
}