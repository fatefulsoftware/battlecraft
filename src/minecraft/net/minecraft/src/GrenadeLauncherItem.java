package net.minecraft.src;

public class GrenadeLauncherItem extends Item {
	protected static final int damage = 20;
	
    public GrenadeLauncherItem (int par1) {
        super(par1);
        
        maxStackSize = 1;
        setMaxDamage(1000);
        setCreativeTab(CreativeTabs.tabCombat);
        setItemName("Grenade Launcher");
        setFull3D();
        setIconCoord(5, 1);
    }

    /**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
    public void onPlayerStoppedUsing (ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4) {
        //boolean var5 = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

        // has ammo
        if (true) {
            int var6 = this.getMaxItemUseDuration(par1ItemStack) - par4;
            float var7 = (float)var6 / 20.0F;
            var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;

            if ((double)var7 < 0.1D)
                return;

            if (var7 > 1.0F)
                var7 = 1.0F;

            EntityArrow var8 = new EntityArrow(par2World, par3EntityPlayer, var7 * 2.0F);

            var8.setIsCritical(true);
            var8.setDamage(damage/*var8.getDamage() + (double)var9 * 0.5D + 0.5D*/);
            var8.setKnockbackStrength(10);
            var8.setFire(100);

            par1ItemStack.damageItem(1, par3EntityPlayer);
            
            par2World.playSoundAtEntity(par3EntityPlayer, "random.explode1", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);

            var8.canBePickedUp = 0;

            par3EntityPlayer.inventory.consumeInventoryItem(Item.arrow.shiftedIndex);

            if (!par2World.isRemote)
                par2World.spawnEntityInWorld(var8);
        }
    }

    public ItemStack onFoodEaten (ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        return par1ItemStack;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 72000;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick (ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(Item.arrow.shiftedIndex))
            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));

        return par1ItemStack;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability() {
        return 0;
    }
    
    @Override
    public boolean hitEntity (ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving) {
    	par1ItemStack.damageItem(damage, par2EntityLiving);
    	
    	return true;
    }

    /**
     * Returns the damage against a given entity.
     */
    @Override
    public int getDamageVsEntity (Entity par1Entity) {
        return damage;
    }
}