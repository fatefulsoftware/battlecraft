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
    
    public GrenadeEntity (World world, double x, double y, double z, float yaw, float pitch, double force, int fuseLength) {
        this(world);
        
    	setRotation(yaw, 0);
    	
        // Set the velocity
    	double xHeading = -MathHelper.sin((yaw * 3.141593F) / 180F);
    	double zHeading = MathHelper.cos((yaw * 3.141593F) / 180F);
        motionX = force*xHeading*MathHelper.cos((pitch / 180F) * 3.141593F);
        motionY = -force*MathHelper.sin((pitch / 180F) * 3.141593F);
        motionZ = force*zHeading*MathHelper.cos((pitch / 180F) * 3.141593F);
    	
        // Set the position
        setPosition(x+xHeading*0.8, y, z+zHeading*0.8);
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        
        fuse = fuseLength;
    }

    public GrenadeEntity (World world, Entity entity) {
        this(world, entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch, 1, 50);
    }

	public GrenadeEntity (World par1World) {
		super(par1World);
		
		setUp();
	}
	
	protected void setUp () {
		setSize(.5F, .5F);
		
		yOffset = height / 2F;
		bounceFactor = 0.75;
		fuse = 50;
		
		item = new ItemStack(mod_Battlecraft.grenade);
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
            
            setDead();
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