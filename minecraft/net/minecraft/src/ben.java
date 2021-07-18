package net.minecraft.src;

import javax.annotation.Nullable;
import net.minecraft.block.material.MaterialPortal;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;

public class ben extends PathFinder
{
    public Path b()
    {
        return this.a(IProgressUpdate.c(this.closedSet.bw().ZERO), IProgressUpdate.c(this.closedSet.bw().ONE + 0.5D), IProgressUpdate.c(this.closedSet.bw().UNIT_X));
    }

    public Path a(double p_a_1_, double p_a_3_, double p_a_5_)
    {
        return this.a(IProgressUpdate.c(p_a_1_ - (double)(this.closedSet.G / 2.0F)), IProgressUpdate.c(p_a_3_ + 0.5D), IProgressUpdate.c(p_a_5_ - (double)(this.closedSet.G / 2.0F)));
    }

    public int a(Path[] p_a_1_, Path p_a_2_, Path p_a_3_, float p_a_4_)
    {
        int i = 0;

        for (EnumFacing enumfacing : EnumFacing.values())
        {
            Path path = this.b(p_a_2_.points + enumfacing.getFrontOffsetX(), p_a_2_.openSet + enumfacing.getFrontOffsetY(), p_a_2_.closedSet + enumfacing.getFrontOffsetZ());

            if (path != null && !path.i && path.a(p_a_3_) < p_a_4_)
            {
                p_a_1_[i++] = path;
            }
        }

        return i;
    }

    public PathPoint a(EnumSkyBlock p_a_1_, int p_a_2_, int p_a_3_, int p_a_4_, IEntityLivingData p_a_5_, int p_a_6_, int p_a_7_, int p_a_8_, boolean p_a_9_, boolean p_a_10_)
    {
        return PathPoint.distanceToTarget;
    }

    public PathPoint a(EnumSkyBlock p_a_1_, int p_a_2_, int p_a_3_, int p_a_4_)
    {
        return PathPoint.distanceToTarget;
    }

    @Nullable
    private Path b(int p_b_1_, int p_b_2_, int p_b_3_)
    {
        PathPoint pathpoint = this.c(p_b_1_, p_b_2_, p_b_3_);
        return pathpoint == PathPoint.distanceToTarget ? this.a(p_b_1_, p_b_2_, p_b_3_) : null;
    }

    private PathPoint c(int p_c_1_, int p_c_2_, int p_c_3_)
    {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int i = p_c_1_; i < p_c_1_ + this.nodeProcessor; ++i)
        {
            for (int j = p_c_2_; j < p_c_2_ + this.e; ++j)
            {
                for (int k = p_c_3_; k < p_c_3_ + this.f; ++k)
                {
                    awt awt = this.path.o(blockpos$mutableblockpos.setPos(i, j, k));

                    if (awt.a() != MaterialPortal.h)
                    {
                        return PathPoint.xCoord;
                    }
                }
            }
        }

        return PathPoint.distanceToTarget;
    }
}
