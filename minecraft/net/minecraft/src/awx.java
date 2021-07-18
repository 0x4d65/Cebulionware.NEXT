package net.minecraft.src;

import com.google.common.base.MoreObjects;
import com.google.common.base.Predicate;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import javax.annotation.Nullable;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IWorldEventListener;

public class awx
{
    private final Predicate<FactoryBlockPattern>[][][] a;
    private final int b;
    private final int c;
    private final int d;

    public awx(Predicate<FactoryBlockPattern>[][][] p_i1831_1_)
    {
        this.a = p_i1831_1_;
        this.b = p_i1831_1_.length;

        if (this.b > 0)
        {
            this.c = p_i1831_1_[0].length;

            if (this.c > 0)
            {
                this.d = p_i1831_1_[0][0].length;
            }
            else
            {
                this.d = 0;
            }
        }
        else
        {
            this.c = 0;
            this.d = 0;
        }
    }

    public int a()
    {
        return this.b;
    }

    public int b()
    {
        return this.c;
    }

    public int c()
    {
        return this.d;
    }

    @Nullable
    private awx.b a(BlockPos p_a_1_, EnumFacing p_a_2_, EnumFacing p_a_3_, LoadingCache<BlockPos, FactoryBlockPattern> p_a_4_)
    {
        for (int i = 0; i < this.d; ++i)
        {
            for (int j = 0; j < this.c; ++j)
            {
                for (int k = 0; k < this.b; ++k)
                {
                    if (!this.a[k][j][i].apply(p_a_4_.getUnchecked(a(p_a_1_, p_a_2_, p_a_3_, i, j, k))))
                    {
                        return null;
                    }
                }
            }
        }

        return new awx.b(p_a_1_, p_a_2_, p_a_3_, p_a_4_, this.d, this.c, this.b);
    }

    @Nullable
    public awx.b a(IWorldEventListener p_a_1_, BlockPos p_a_2_)
    {
        LoadingCache<BlockPos, FactoryBlockPattern> loadingcache = a(p_a_1_, false);
        int i = Math.max(Math.max(this.d, this.c), this.b);

        for (BlockPos blockpos : BlockPos.getAllInBox(p_a_2_, p_a_2_.add(i - 1, i - 1, i - 1)))
        {
            for (EnumFacing enumfacing : EnumFacing.values())
            {
                for (EnumFacing enumfacing1 : EnumFacing.values())
                {
                    if (enumfacing1 != enumfacing && enumfacing1 != enumfacing.getOpposite())
                    {
                        awx.b awx$b = this.a(blockpos, enumfacing, enumfacing1, loadingcache);

                        if (awx$b != null)
                        {
                            return awx$b;
                        }
                    }
                }
            }
        }

        return null;
    }

    public static LoadingCache<BlockPos, FactoryBlockPattern> a(IWorldEventListener p_a_0_, boolean p_a_1_)
    {
        return CacheBuilder.newBuilder().<BlockPos, FactoryBlockPattern>build(new awx.a(p_a_0_, p_a_1_));
    }

    protected static BlockPos a(BlockPos p_a_0_, EnumFacing p_a_1_, EnumFacing p_a_2_, int p_a_3_, int p_a_4_, int p_a_5_)
    {
        if (p_a_1_ != p_a_2_ && p_a_1_ != p_a_2_.getOpposite())
        {
            Vec3i vec3i = new Vec3i(p_a_1_.getFrontOffsetX(), p_a_1_.getFrontOffsetY(), p_a_1_.getFrontOffsetZ());
            Vec3i vec3i1 = new Vec3i(p_a_2_.getFrontOffsetX(), p_a_2_.getFrontOffsetY(), p_a_2_.getFrontOffsetZ());
            Vec3i vec3i2 = vec3i.crossProduct(vec3i1);
            return p_a_0_.add(vec3i1.getX() * -p_a_4_ + vec3i2.getX() * p_a_3_ + vec3i.getX() * p_a_5_, vec3i1.getY() * -p_a_4_ + vec3i2.getY() * p_a_3_ + vec3i.getY() * p_a_5_, vec3i1.getZ() * -p_a_4_ + vec3i2.getZ() * p_a_3_ + vec3i.getZ() * p_a_5_);
        }
        else
        {
            throw new IllegalArgumentException("Invalid forwards & up combination");
        }
    }

    static class a extends CacheLoader<BlockPos, FactoryBlockPattern>
    {
        private final IWorldEventListener a;
        private final boolean b;

        public a(IWorldEventListener p_i1829_1_, boolean p_i1829_2_)
        {
            this.a = p_i1829_1_;
            this.b = p_i1829_2_;
        }

        public FactoryBlockPattern a(BlockPos p_a_1_) throws Exception
        {
            return new FactoryBlockPattern(this.a, p_a_1_, this.b);
        }
    }

    public static class b
    {
        private final BlockPos a;
        private final EnumFacing b;
        private final EnumFacing c;
        private final LoadingCache<BlockPos, FactoryBlockPattern> d;
        private final int e;
        private final int f;
        private final int g;

        public b(BlockPos p_i1830_1_, EnumFacing p_i1830_2_, EnumFacing p_i1830_3_, LoadingCache<BlockPos, FactoryBlockPattern> p_i1830_4_, int p_i1830_5_, int p_i1830_6_, int p_i1830_7_)
        {
            this.a = p_i1830_1_;
            this.b = p_i1830_2_;
            this.c = p_i1830_3_;
            this.d = p_i1830_4_;
            this.e = p_i1830_5_;
            this.f = p_i1830_6_;
            this.g = p_i1830_7_;
        }

        public BlockPos a()
        {
            return this.a;
        }

        public EnumFacing b()
        {
            return this.b;
        }

        public EnumFacing c()
        {
            return this.c;
        }

        public int d()
        {
            return this.e;
        }

        public int e()
        {
            return this.f;
        }

        public FactoryBlockPattern a(int p_a_1_, int p_a_2_, int p_a_3_)
        {
            return this.d.getUnchecked(awx.a(this.a, this.b(), this.c(), p_a_1_, p_a_2_, p_a_3_));
        }

        public String toString()
        {
            return MoreObjects.toStringHelper(this).add("up", this.c).add("forwards", this.b).add("frontTopLeft", this.a).toString();
        }
    }
}
