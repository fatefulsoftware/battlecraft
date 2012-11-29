package net.minecraft.src;

public class GrenadeItem extends Item {
	public GrenadeItem (int par1) {
		super(par1);
		
		setIconIndex(30);
		setItemName("grenade");

        iconIndex = ModLoader.addOverride("/gui/items.png", "/battlecraft/grenade.png");
	}
	
	@Override
	public ItemStack onItemRightClick (ItemStack par1ItemStack, World world,
			EntityPlayer par3EntityPlayer) {
		world.spawnEntityInWorld(new GrenadeEntity(world, par3EntityPlayer));

		//par1ItemStack.stackSize--;
        par3EntityPlayer.inventory.consumeInventoryItem(mod_Battlecraft.grenade.shiftedIndex);
		
		return par1ItemStack;
	}
}
