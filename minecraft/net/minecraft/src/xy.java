package net.minecraft.src;

import javax.annotation.Nullable;
import net.minecraft.block.material.MaterialPortal;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.IJumpingMount;
import net.minecraft.entity.ai.EntityAIHarvestFarmland;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.VillageDoorInfo;
import net.minecraft.world.IWorldEventListener;

public class xy extends EntityAIHarvestFarmland
{
    protected final IJumpingMount a;
    protected double b;

    /** Villager that is harvesting */
    protected double theVillager;
    protected double hasFarmItem;
    protected double wantsToReapStuff;

    public xy(IJumpingMount hostIn, double portIn)
    {
        this.a = hostIn;
        this.b = portIn;
        this.a(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.a.bS() == null && !this.a.aR())
        {
            return false;
        }
        else
        {
            if (this.a.aR())
            {
                BlockPos blockpos = this.a(this.a.l, this.a, 5, 4);

                if (blockpos != null)
                {
                    this.theVillager = (double)blockpos.getX();
                    this.hasFarmItem = (double)blockpos.getY();
                    this.wantsToReapStuff = (double)blockpos.getZ();
                    return true;
                }
            }

            return this.f();
        }
    }

    protected boolean f()
    {
        ScoreObjective scoreobjective = VillageDoorInfo.a(this.a, 5, 4);

        if (scoreobjective == null)
        {
            return false;
        }
        else
        {
            this.theVillager = scoreobjective.name;
            this.hasFarmItem = scoreobjective.objectiveCriteria;
            this.wantsToReapStuff = scoreobjective.renderType;
            return true;
        }
    }

    public void c()
    {
        this.a.x().a(this.theVillager, this.hasFarmItem, this.wantsToReapStuff, this.b);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.a.x().o();
    }

    @Nullable
    private BlockPos a(IWorldEventListener p_a_1_, EntityList p_a_2_, int p_a_3_, int p_a_4_)
    {
        BlockPos blockpos = new BlockPos(p_a_2_);
        int i = blockpos.getX();
        int j = blockpos.getY();
        int k = blockpos.getZ();
        float f = (float)(p_a_3_ * p_a_3_ * p_a_4_ * 2);
        BlockPos blockpos1 = null;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int l = i - p_a_3_; l <= i + p_a_3_; ++l)
        {
            for (int i1 = j - p_a_4_; i1 <= j + p_a_4_; ++i1)
            {
                for (int j1 = k - p_a_3_; j1 <= k + p_a_3_; ++j1)
                {
                    blockpos$mutableblockpos.setPos(l, i1, j1);
                    awt awt = p_a_1_.o(blockpos$mutableblockpos);

                    if (awt.a() == MaterialPortal.h)
                    {
                        float f1 = (float)((l - i) * (l - i) + (i1 - j) * (i1 - j) + (j1 - k) * (j1 - k));

                        if (f1 < f)
                        {
                            f = f1;
                            blockpos1 = new BlockPos(blockpos$mutableblockpos);
                        }
                    }
                }
            }
        }

        return blockpos1;
    }
}
