package net.minecraft.src;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.pathfinding.PathWorldListener;
import net.minecraft.pathfinding.SwimNodeProcessor;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldEventListener;

public class zg extends PathWorldListener
{
    private BlockPos i;

    public zg(IEntityLivingData scale, IWorldEventListener p_i1163_2_)
    {
        super(scale, p_i1163_2_);
    }

    public SwimNodeProcessor b(BlockPos p_b_1_)
    {
        this.i = p_b_1_;
        return super.b(p_b_1_);
    }

    public SwimNodeProcessor a(EntityList p_a_1_)
    {
        this.i = new BlockPos(p_a_1_);
        return super.a(p_a_1_);
    }

    public boolean a(EntityList p_a_1_, double p_a_2_)
    {
        SwimNodeProcessor swimnodeprocessor = this.a(p_a_1_);

        if (swimnodeprocessor != null)
        {
            return this.a(swimnodeprocessor, p_a_2_);
        }
        else
        {
            this.i = new BlockPos(p_a_1_);
            this.d = p_a_2_;
            return true;
        }
    }

    public void onUpdateNavigation()
    {
        if (!this.o())
        {
            super.onUpdateNavigation();
        }
        else
        {
            if (this.i != null)
            {
                double d0 = (double)(this.navigations.G * this.navigations.G);

                if (this.navigations.d(this.i) >= d0 && (this.navigations.q <= (double)this.i.getY() || this.navigations.d(new BlockPos(this.i.getX(), IProgressUpdate.c(this.navigations.q), this.i.getZ())) >= d0))
                {
                    this.navigations.u().a((double)this.i.getX(), (double)this.i.getY(), (double)this.i.getZ(), this.d);
                }
                else
                {
                    this.i = null;
                }
            }
        }
    }
}
