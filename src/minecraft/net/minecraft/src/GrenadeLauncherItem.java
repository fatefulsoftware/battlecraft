package net.minecraft.src;

public class GrenadeLauncherItem extends Item {
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
        // has ammo
        if (player.capabilities.isCreativeMode || player.inventory.hasItem(mod_Battlecraft.grenade.shiftedIndex)) {
            GrenadeEntity var8 = new GrenadeEntity(world, player);

            par1ItemStack.damageItem(1, player);
            
            world.playSoundAtEntity(player, "random.click", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.5F);

            player.inventory.consumeInventoryItem(mod_Battlecraft.grenade.shiftedIndex);

            if (!world.isRemote)
            	world.spawnEntityInWorld(var8);
        }
        
        return par1ItemStack;
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