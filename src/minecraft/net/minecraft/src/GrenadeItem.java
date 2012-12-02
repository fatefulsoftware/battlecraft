package net.minecraft.src;

import java.util.Date;

public class GrenadeItem extends Item {
	long lastThrown;
	
	public GrenadeItem (int par1) {
		super(par1);
		
		setIconIndex(30);
		setItemName("grenade");

        iconIndex = ModLoader.addOverride("/gui/items.png", "/battlecraft/grenade.png");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World world,
			EntityPlayer player) {
		long now;
		
		now = new Date().getTime();
		
		if (lastThrown != 0 && (now - lastThrown) < 1000)
			return par1ItemStack;
		
		lastThrown = now;
		
		world.spawnEntityInWorld(new GrenadeEntity(world, player));

		player.inventory.consumeInventoryItem(mod_Battlecraft.grenade.shiftedIndex);
		
		return par1ItemStack;
	}
    
    @Override
    public boolean hitEntity (ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving) {
    	par1ItemStack.damageItem(1, par2EntityLiving);
    	
    	return true;
    }
}