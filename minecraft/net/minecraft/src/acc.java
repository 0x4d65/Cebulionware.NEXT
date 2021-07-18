package net.minecraft.src;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.BlockFire;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.world.IWorldEventListener;

public class acc extends EntityLeashKnot
{
    public acc(IWorldEventListener p_i1251_1_)
    {
        super(p_i1251_1_);
    }

    public acc(IWorldEventListener p_i1252_1_, BlockPos p_i1252_2_)
    {
        super(p_i1252_1_, p_i1252_2_);
        this.setPosition((double)p_i1252_2_.getX() + 0.5D, (double)p_i1252_2_.getY() + 0.5D, (double)p_i1252_2_.getZ() + 0.5D);
        float f = 0.125F;
        float f1 = 0.1875F;
        float f2 = 0.25F;
        this.a(new Vec2f(this.p - 0.1875D, this.q - 0.25D + 0.125D, this.r - 0.1875D, this.p + 0.1875D, this.q + 0.25D + 0.125D, this.r + 0.1875D));
        this.k = true;
    }

    /**
     * Sets the x,y,z of the entity from the given parameters. Also seems to set up a bounding box.
     */
    public void setPosition(double x, double y, double z)
    {
        super.setPosition((double)IProgressUpdate.c(x) + 0.5D, (double)IProgressUpdate.c(y) + 0.5D, (double)IProgressUpdate.c(z) + 0.5D);
    }

    /**
     * Updates the entity bounding box based on current facing
     */
    protected void updateBoundingBox()
    {
        this.p = (double)this.field_191307_a.getX() + 0.5D;
        this.q = (double)this.field_191307_a.getY() + 0.5D;
        this.r = (double)this.field_191307_a.getZ() + 0.5D;
    }

    /**
     * Updates facing and bounding box based on it
     */
    public void updateFacingWithBoundingBox(EnumFacing facingDirectionIn)
    {
    }

    public int getWidthPixels()
    {
        return 9;
    }

    public int getHeightPixels()
    {
        return 9;
    }

    public float getEyeHeight()
    {
        return -0.0625F;
    }

    /**
     * Checks if the entity is in range to render.
     */
    public boolean isInRangeToRenderDist(double distance)
    {
        return distance < 1024.0D;
    }

    public void a(@Nullable EntityList p_a_1_)
    {
        this.a(qf.dG, 1.0F, 1.0F);
    }

    /**
     * Either write this entity to the NBT tag given and return true, or return false without doing anything. If this
     * returns false the entity is not saved on disk. Ridden entities return false here as they are saved with their
     * rider.
     */
    public boolean writeToNBTOptional(NBTTagCompound compound)
    {
        return false;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
    }

    public boolean b(RecipeItemHelper p_b_1_, EnumActionResult p_b_2_)
    {
        if (this.l.G)
        {
            return true;
        }
        else
        {
            boolean flag = false;
            double d0 = 7.0D;
            List<IEntityLivingData> list = this.l.a(IEntityLivingData.class, new Vec2f(this.p - 7.0D, this.q - 7.0D, this.r - 7.0D, this.p + 7.0D, this.q + 7.0D, this.r + 7.0D));

            for (IEntityLivingData ientitylivingdata : list)
            {
                if (ientitylivingdata.da() && ientitylivingdata.db() == p_b_1_)
                {
                    ientitylivingdata.b(this, true);
                    flag = true;
                }
            }

            if (!flag)
            {
                this.X();

                if (p_b_1_.bO.spawnChunk)
                {
                    for (IEntityLivingData ientitylivingdata1 : list)
                    {
                        if (ientitylivingdata1.da() && ientitylivingdata1.db() == this)
                        {
                            ientitylivingdata1.a(true, false);
                        }
                    }
                }
            }

            return true;
        }
    }

    /**
     * checks to make sure painting can be placed there
     */
    public boolean onValidSurface()
    {
        return this.l.o(this.field_191307_a).u() instanceof BlockFire;
    }

    public static acc a(IWorldEventListener p_a_0_, BlockPos p_a_1_)
    {
        acc acc = new acc(p_a_0_, p_a_1_);
        p_a_0_.a(acc);
        acc.playPlaceSound();
        return acc;
    }

    @Nullable
    public static acc b(IWorldEventListener p_b_0_, BlockPos p_b_1_)
    {
        int i = p_b_1_.getX();
        int j = p_b_1_.getY();
        int k = p_b_1_.getZ();

        for (acc acc : p_b_0_.a(acc.class, new Vec2f((double)i - 1.0D, (double)j - 1.0D, (double)k - 1.0D, (double)i + 1.0D, (double)j + 1.0D, (double)k + 1.0D)))
        {
            if (acc.q().equals(p_b_1_))
            {
                return acc;
            }
        }

        return null;
    }

    public void playPlaceSound()
    {
        this.a(qf.dH, 1.0F, 1.0F);
    }
}
