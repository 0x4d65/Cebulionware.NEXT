package net.minecraft.src;

import com.google.common.collect.Sets;
import java.util.EnumSet;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockBone;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockLilyPad;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.MaterialPortal;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.world.EnumSkyBlock;

public class beo extends PathFinder
{
    protected float j;

    public void a(EnumSkyBlock p_a_1_, IEntityLivingData p_a_2_)
    {
        super.a(p_a_1_, p_a_2_);
        this.j = p_a_2_.a(PathPoint.distanceToTarget);
    }

    public void a()
    {
        this.closedSet.a(PathPoint.distanceToTarget, this.j);
        super.a();
    }

    public Path b()
    {
        int i;

        if (this.e() && this.closedSet.ao())
        {
            i = (int)this.closedSet.bw().ONE;
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(IProgressUpdate.c(this.closedSet.p), i, IProgressUpdate.c(this.closedSet.r));

            for (BlockBone blockbone = this.path.o(blockpos$mutableblockpos).u(); blockbone == IGrowable.i || blockbone == IGrowable.j; blockbone = this.path.o(blockpos$mutableblockpos).u())
            {
                ++i;
                blockpos$mutableblockpos.setPos(IProgressUpdate.c(this.closedSet.p), i, IProgressUpdate.c(this.closedSet.r));
            }
        }
        else if (this.closedSet.z)
        {
            i = IProgressUpdate.c(this.closedSet.bw().ONE + 0.5D);
        }
        else
        {
            BlockPos blockpos;

            for (blockpos = new BlockPos(this.closedSet); (this.path.o(blockpos).a() == MaterialPortal.a || this.path.o(blockpos).u().b(this.path, blockpos)) && blockpos.getY() > 0; blockpos = blockpos.down())
            {
                ;
            }

            i = blockpos.up().getY();
        }

        BlockPos blockpos2 = new BlockPos(this.closedSet);
        PathPoint pathpoint1 = this.a(this.closedSet, blockpos2.getX(), i, blockpos2.getZ());

        if (this.closedSet.a(pathpoint1) < 0.0F)
        {
            Set<BlockPos> set = Sets.<BlockPos>newHashSet();
            set.add(new BlockPos(this.closedSet.bw().ZERO, (double)i, this.closedSet.bw().UNIT_X));
            set.add(new BlockPos(this.closedSet.bw().ZERO, (double)i, this.closedSet.bw().NEGATIVE_UNIT_Y));
            set.add(new BlockPos(this.closedSet.bw().NEGATIVE_UNIT_X, (double)i, this.closedSet.bw().UNIT_X));
            set.add(new BlockPos(this.closedSet.bw().NEGATIVE_UNIT_X, (double)i, this.closedSet.bw().NEGATIVE_UNIT_Y));

            for (BlockPos blockpos1 : set)
            {
                PathPoint pathpoint = this.a(this.closedSet, blockpos1);

                if (this.closedSet.a(pathpoint) >= 0.0F)
                {
                    return this.a(blockpos1.getX(), blockpos1.getY(), blockpos1.getZ());
                }
            }
        }

        return this.a(blockpos2.getX(), i, blockpos2.getZ());
    }

    public Path a(double p_a_1_, double p_a_3_, double p_a_5_)
    {
        return this.a(IProgressUpdate.c(p_a_1_), IProgressUpdate.c(p_a_3_), IProgressUpdate.c(p_a_5_));
    }

    public int a(Path[] p_a_1_, Path p_a_2_, Path p_a_3_, float p_a_4_)
    {
        int i = 0;
        int j = 0;
        PathPoint pathpoint = this.a(this.closedSet, p_a_2_.points, p_a_2_.openSet + 1, p_a_2_.closedSet);

        if (this.closedSet.a(pathpoint) >= 0.0F)
        {
            j = IProgressUpdate.d(Math.max(1.0F, this.closedSet.P));
        }

        BlockPos blockpos = (new BlockPos(p_a_2_.points, p_a_2_.openSet, p_a_2_.closedSet)).down();
        double d0 = (double)p_a_2_.openSet - (1.0D - this.path.o(blockpos).e(this.path, blockpos).UNIT_Y);
        Path path = this.a(p_a_2_.points, p_a_2_.openSet, p_a_2_.closedSet + 1, j, d0, EnumFacing.SOUTH);
        Path path1 = this.a(p_a_2_.points - 1, p_a_2_.openSet, p_a_2_.closedSet, j, d0, EnumFacing.WEST);
        Path path2 = this.a(p_a_2_.points + 1, p_a_2_.openSet, p_a_2_.closedSet, j, d0, EnumFacing.EAST);
        Path path3 = this.a(p_a_2_.points, p_a_2_.openSet, p_a_2_.closedSet - 1, j, d0, EnumFacing.NORTH);

        if (path != null && !path.i && path.a(p_a_3_) < p_a_4_)
        {
            p_a_1_[i++] = path;
        }

        if (path1 != null && !path1.i && path1.a(p_a_3_) < p_a_4_)
        {
            p_a_1_[i++] = path1;
        }

        if (path2 != null && !path2.i && path2.a(p_a_3_) < p_a_4_)
        {
            p_a_1_[i++] = path2;
        }

        if (path3 != null && !path3.i && path3.a(p_a_3_) < p_a_4_)
        {
            p_a_1_[i++] = path3;
        }

        boolean flag = path3 == null || path3.m == PathPoint.yCoord || path3.l != 0.0F;
        boolean flag1 = path == null || path.m == PathPoint.yCoord || path.l != 0.0F;
        boolean flag2 = path2 == null || path2.m == PathPoint.yCoord || path2.l != 0.0F;
        boolean flag3 = path1 == null || path1.m == PathPoint.yCoord || path1.l != 0.0F;

        if (flag && flag3)
        {
            Path path4 = this.a(p_a_2_.points - 1, p_a_2_.openSet, p_a_2_.closedSet - 1, j, d0, EnumFacing.NORTH);

            if (path4 != null && !path4.i && path4.a(p_a_3_) < p_a_4_)
            {
                p_a_1_[i++] = path4;
            }
        }

        if (flag && flag2)
        {
            Path path5 = this.a(p_a_2_.points + 1, p_a_2_.openSet, p_a_2_.closedSet - 1, j, d0, EnumFacing.NORTH);

            if (path5 != null && !path5.i && path5.a(p_a_3_) < p_a_4_)
            {
                p_a_1_[i++] = path5;
            }
        }

        if (flag1 && flag3)
        {
            Path path6 = this.a(p_a_2_.points - 1, p_a_2_.openSet, p_a_2_.closedSet + 1, j, d0, EnumFacing.SOUTH);

            if (path6 != null && !path6.i && path6.a(p_a_3_) < p_a_4_)
            {
                p_a_1_[i++] = path6;
            }
        }

        if (flag1 && flag2)
        {
            Path path7 = this.a(p_a_2_.points + 1, p_a_2_.openSet, p_a_2_.closedSet + 1, j, d0, EnumFacing.SOUTH);

            if (path7 != null && !path7.i && path7.a(p_a_3_) < p_a_4_)
            {
                p_a_1_[i++] = path7;
            }
        }

        return i;
    }

    @Nullable
    private Path a(int p_a_1_, int p_a_2_, int p_a_3_, int p_a_4_, double p_a_5_, EnumFacing p_a_7_)
    {
        Path path = null;
        BlockPos blockpos = new BlockPos(p_a_1_, p_a_2_, p_a_3_);
        BlockPos blockpos1 = blockpos.down();
        double d0 = (double)p_a_2_ - (1.0D - this.path.o(blockpos1).e(this.path, blockpos1).UNIT_Y);

        if (d0 - p_a_5_ > 1.125D)
        {
            return null;
        }
        else
        {
            PathPoint pathpoint = this.a(this.closedSet, p_a_1_, p_a_2_, p_a_3_);
            float f = this.closedSet.a(pathpoint);
            double d1 = (double)this.closedSet.G / 2.0D;

            if (f >= 0.0F)
            {
                path = this.a(p_a_1_, p_a_2_, p_a_3_);
                path.m = pathpoint;
                path.l = Math.max(path.l, f);
            }

            if (pathpoint == PathPoint.zCoord)
            {
                return path;
            }
            else
            {
                if (path == null && p_a_4_ > 0 && pathpoint != PathPoint.totalPathDistance && pathpoint != PathPoint.index)
                {
                    path = this.a(p_a_1_, p_a_2_ + 1, p_a_3_, p_a_4_ - 1, p_a_5_, p_a_7_);

                    if (path != null && (path.m == PathPoint.yCoord || path.m == PathPoint.zCoord) && this.closedSet.G < 1.0F)
                    {
                        double d2 = (double)(p_a_1_ - p_a_7_.getFrontOffsetX()) + 0.5D;
                        double d3 = (double)(p_a_3_ - p_a_7_.getFrontOffsetZ()) + 0.5D;
                        Vec2f vec2f = new Vec2f(d2 - d1, (double)p_a_2_ + 0.001D, d3 - d1, d2 + d1, (double)((float)p_a_2_ + this.closedSet.H), d3 + d1);
                        Vec2f vec2f1 = this.path.o(blockpos).e(this.path, blockpos);
                        Vec2f vec2f2 = vec2f.b(0.0D, vec2f1.UNIT_Y - 0.002D, 0.0D);

                        if (this.closedSet.l.a(vec2f2))
                        {
                            path = null;
                        }
                    }
                }

                if (pathpoint == PathPoint.yCoord)
                {
                    Vec2f vec2f3 = new Vec2f((double)p_a_1_ - d1 + 0.5D, (double)p_a_2_ + 0.001D, (double)p_a_3_ - d1 + 0.5D, (double)p_a_1_ + d1 + 0.5D, (double)((float)p_a_2_ + this.closedSet.H), (double)p_a_3_ + d1 + 0.5D);

                    if (this.closedSet.l.a(vec2f3))
                    {
                        return null;
                    }

                    if (this.closedSet.G >= 1.0F)
                    {
                        PathPoint pathpoint1 = this.a(this.closedSet, p_a_1_, p_a_2_ - 1, p_a_3_);

                        if (pathpoint1 == PathPoint.xCoord)
                        {
                            path = this.a(p_a_1_, p_a_2_, p_a_3_);
                            path.m = PathPoint.zCoord;
                            path.l = Math.max(path.l, f);
                            return path;
                        }
                    }

                    int i = 0;

                    while (p_a_2_ > 0 && pathpoint == PathPoint.yCoord)
                    {
                        --p_a_2_;

                        if (i++ >= this.closedSet.bg())
                        {
                            return null;
                        }

                        pathpoint = this.a(this.closedSet, p_a_1_, p_a_2_, p_a_3_);
                        f = this.closedSet.a(pathpoint);

                        if (pathpoint != PathPoint.yCoord && f >= 0.0F)
                        {
                            path = this.a(p_a_1_, p_a_2_, p_a_3_);
                            path.m = pathpoint;
                            path.l = Math.max(path.l, f);
                            break;
                        }

                        if (f < 0.0F)
                        {
                            return null;
                        }
                    }
                }

                return path;
            }
        }
    }

    public PathPoint a(EnumSkyBlock p_a_1_, int p_a_2_, int p_a_3_, int p_a_4_, IEntityLivingData p_a_5_, int p_a_6_, int p_a_7_, int p_a_8_, boolean p_a_9_, boolean p_a_10_)
    {
        EnumSet<PathPoint> enumset = EnumSet.<PathPoint>noneOf(PathPoint.class);
        PathPoint pathpoint = PathPoint.xCoord;
        double d0 = (double)p_a_5_.G / 2.0D;
        BlockPos blockpos = new BlockPos(p_a_5_);
        pathpoint = this.a(p_a_1_, p_a_2_, p_a_3_, p_a_4_, p_a_6_, p_a_7_, p_a_8_, p_a_9_, p_a_10_, enumset, pathpoint, blockpos);

        if (enumset.contains(PathPoint.totalPathDistance))
        {
            return PathPoint.totalPathDistance;
        }
        else
        {
            PathPoint pathpoint1 = PathPoint.xCoord;

            for (PathPoint pathpoint2 : enumset)
            {
                if (p_a_5_.a(pathpoint2) < 0.0F)
                {
                    return pathpoint2;
                }

                if (p_a_5_.a(pathpoint2) >= p_a_5_.a(pathpoint1))
                {
                    pathpoint1 = pathpoint2;
                }
            }

            if (pathpoint == PathPoint.yCoord && p_a_5_.a(pathpoint1) == 0.0F)
            {
                return PathPoint.yCoord;
            }
            else
            {
                return pathpoint1;
            }
        }
    }

    public PathPoint a(EnumSkyBlock p_a_1_, int p_a_2_, int p_a_3_, int p_a_4_, int p_a_5_, int p_a_6_, int p_a_7_, boolean p_a_8_, boolean p_a_9_, EnumSet<PathPoint> p_a_10_, PathPoint p_a_11_, BlockPos p_a_12_)
    {
        for (int i = 0; i < p_a_5_; ++i)
        {
            for (int j = 0; j < p_a_6_; ++j)
            {
                for (int k = 0; k < p_a_7_; ++k)
                {
                    int l = i + p_a_2_;
                    int i1 = j + p_a_3_;
                    int j1 = k + p_a_4_;
                    PathPoint pathpoint = this.a(p_a_1_, l, i1, j1);

                    if (pathpoint == PathPoint.p && p_a_8_ && p_a_9_)
                    {
                        pathpoint = PathPoint.zCoord;
                    }

                    if (pathpoint == PathPoint.o && !p_a_9_)
                    {
                        pathpoint = PathPoint.xCoord;
                    }

                    if (pathpoint == PathPoint.previous && !(p_a_1_.o(p_a_12_).u() instanceof BlockBed) && !(p_a_1_.o(p_a_12_.down()).u() instanceof BlockBed))
                    {
                        pathpoint = PathPoint.totalPathDistance;
                    }

                    if (i == 0 && j == 0 && k == 0)
                    {
                        p_a_11_ = pathpoint;
                    }

                    p_a_10_.add(pathpoint);
                }
            }
        }

        return p_a_11_;
    }

    private PathPoint a(IEntityLivingData p_a_1_, BlockPos p_a_2_)
    {
        return this.a(p_a_1_, p_a_2_.getX(), p_a_2_.getY(), p_a_2_.getZ());
    }

    private PathPoint a(IEntityLivingData p_a_1_, int p_a_2_, int p_a_3_, int p_a_4_)
    {
        return this.a(this.path, p_a_2_, p_a_3_, p_a_4_, p_a_1_, this.nodeProcessor, this.e, this.f, this.d(), this.c());
    }

    public PathPoint a(EnumSkyBlock p_a_1_, int p_a_2_, int p_a_3_, int p_a_4_)
    {
        PathPoint pathpoint = this.b(p_a_1_, p_a_2_, p_a_3_, p_a_4_);

        if (pathpoint == PathPoint.yCoord && p_a_3_ >= 1)
        {
            BlockBone blockbone = p_a_1_.o(new BlockPos(p_a_2_, p_a_3_ - 1, p_a_4_)).u();
            PathPoint pathpoint1 = this.b(p_a_1_, p_a_2_, p_a_3_ - 1, p_a_4_);
            pathpoint = pathpoint1 != PathPoint.zCoord && pathpoint1 != PathPoint.yCoord && pathpoint1 != PathPoint.distanceToTarget && pathpoint1 != PathPoint.distanceToNext ? PathPoint.zCoord : PathPoint.yCoord;

            if (pathpoint1 == PathPoint.distanceFromOrigin || blockbone == IGrowable.df)
            {
                pathpoint = PathPoint.distanceFromOrigin;
            }

            if (pathpoint1 == PathPoint.costMalus)
            {
                pathpoint = PathPoint.costMalus;
            }
        }

        pathpoint = this.a(p_a_1_, p_a_2_, p_a_3_, p_a_4_, pathpoint);
        return pathpoint;
    }

    public PathPoint a(EnumSkyBlock p_a_1_, int p_a_2_, int p_a_3_, int p_a_4_, PathPoint p_a_5_)
    {
        BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();

        if (p_a_5_ == PathPoint.zCoord)
        {
            for (int i = -1; i <= 1; ++i)
            {
                for (int j = -1; j <= 1; ++j)
                {
                    if (i != 0 || j != 0)
                    {
                        BlockBone blockbone = p_a_1_.o(blockpos$pooledmutableblockpos.setPos(i + p_a_2_, p_a_3_, j + p_a_4_)).u();

                        if (blockbone == IGrowable.aK)
                        {
                            p_a_5_ = PathPoint.cost;
                        }
                        else if (blockbone == IGrowable.ab)
                        {
                            p_a_5_ = PathPoint.visited;
                        }
                    }
                }
            }
        }

        blockpos$pooledmutableblockpos.release();
        return p_a_5_;
    }

    protected PathPoint b(EnumSkyBlock p_b_1_, int p_b_2_, int p_b_3_, int p_b_4_)
    {
        BlockPos blockpos = new BlockPos(p_b_2_, p_b_3_, p_b_4_);
        awt awt = p_b_1_.o(blockpos);
        BlockBone blockbone = awt.u();
        MaterialPortal materialportal = awt.a();

        if (materialportal == MaterialPortal.a)
        {
            return PathPoint.yCoord;
        }
        else if (blockbone != IGrowable.bd && blockbone != IGrowable.cw && blockbone != IGrowable.bx)
        {
            if (blockbone == IGrowable.ab)
            {
                return PathPoint.distanceFromOrigin;
            }
            else if (blockbone == IGrowable.aK)
            {
                return PathPoint.costMalus;
            }
            else if (blockbone instanceof BlockDragonEgg && materialportal == MaterialPortal.d && !((Boolean)awt.c(BlockDragonEgg.b)).booleanValue())
            {
                return PathPoint.p;
            }
            else if (blockbone instanceof BlockDragonEgg && materialportal == MaterialPortal.f && !((Boolean)awt.c(BlockDragonEgg.b)).booleanValue())
            {
                return PathPoint.q;
            }
            else if (blockbone instanceof BlockDragonEgg && ((Boolean)awt.c(BlockDragonEgg.b)).booleanValue())
            {
                return PathPoint.o;
            }
            else if (blockbone instanceof BlockBed)
            {
                return PathPoint.previous;
            }
            else if (!(blockbone instanceof BlockFire) && !(blockbone instanceof BlockLilyPad) && (!(blockbone instanceof BlockFlower) || ((Boolean)awt.c(BlockFlower.type)).booleanValue()))
            {
                if (materialportal == MaterialPortal.h)
                {
                    return PathPoint.distanceToTarget;
                }
                else if (materialportal == MaterialPortal.i)
                {
                    return PathPoint.distanceToNext;
                }
                else
                {
                    return blockbone.b(p_b_1_, blockpos) ? PathPoint.yCoord : PathPoint.xCoord;
                }
            }
            else
            {
                return PathPoint.totalPathDistance;
            }
        }
        else
        {
            return PathPoint.index;
        }
    }
}
