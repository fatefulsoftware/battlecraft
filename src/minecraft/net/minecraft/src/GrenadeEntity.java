package net.minecraft.src;

public class GrenadeEntity extends EntityItem {
	protected static final int damage = 20;
	
	protected double bounceFactor;
	protected boolean exploded;
	protected int fuse;
	
	public GrenadeEntity (World par1World, double par2, double par4,
			double par6, ItemStack par8ItemStack) {
		super(par1World, par2, par4, par6, par8ItemStack);
		
		setUp();
	}

	public GrenadeEntity (World par1World) {
		super(par1World);
		
		setUp();
	}
	
	public GrenadeEntity (World world, Entity entity) {
		this(world);
		
		setRotation(entity.rotationYaw, 0);

        double xHeading = -MathHelper.sin((entity.rotationYaw * 3.141593F) / 180F);
        double zHeading = MathHelper.cos((entity.rotationYaw * 3.141593F) / 180F);
        
        motionX = 0.5*xHeading*MathHelper.cos((entity.rotationPitch / 180F) * 3.141593F);
        motionY = -0.5*MathHelper.sin((entity.rotationPitch / 180F) * 3.141593F);
        motionZ = 0.5*zHeading*MathHelper.cos((entity.rotationPitch / 180F) * 3.141593F);
        
        // Set the position
        setPosition(entity.posX+xHeading*0.8, entity.posY, entity.posZ+zHeading*0.8);
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
	}
	
	protected void setUp () {
		setSize(.5F, .5F);
		
		yOffset = height / 2F;
		bounceFactor = 0.75;
		fuse = 50;
		
		item = new ItemStack(mod_Battlecraft.grenade);
		
		setDead();
	}

	@Override
	public void onUpdate() {
        double prevVelX = motionX;
        double prevVelY = motionY;
        double prevVelZ = motionZ;
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        moveEntity(motionX, motionY, motionZ);
        
        // Take into account bouncing (normal displacement just sets them to 0)
        if(motionX!=prevVelX)
        {
                motionX = -bounceFactor*prevVelX;
        }
        if(motionY!=prevVelY)
        {
                motionY = -bounceFactor*prevVelY;
        }
        
        if(motionZ!=prevVelZ)
        {
                motionZ = -bounceFactor*prevVelZ;
        }
        else
        {
                motionY -= 0.04;
        }
        
        // Air friction
        motionX *= 0.99;
        motionY *= 0.99;
        motionZ *= 0.99;
        
        // Are we going to explode?
        if (--fuse <= 0)
            explode();
    }

    protected void explode() {
        if (!exploded) {
            exploded = true;
            
            worldObj.createExplosion(null, posX, posY, posZ, 2F, false);
            
            //worldObj.removeEntity(this);
        }
    }
    
    @Override
    public boolean attackEntityFrom (DamageSource par1DamageSource, int par2) {
    	super.attackEntityFrom(par1DamageSource, par2);
    	
    	explode();
    	
    	return false;
    }
    
    @Override
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
    }
}