package net.minecraft.src;

import com.google.common.base.Predicate;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.chunk.BlockStatePaletteLinear;

public class ou
{
    private static final Predicate<EntityTrackerEntry> a = new Predicate<EntityTrackerEntry>()
    {
        public boolean a(@Nullable EntityTrackerEntry p_a_1_)
        {
            return p_a_1_ != null && !p_a_1_.y();
        }
    };
    private static final Predicate<EntityTrackerEntry> b = new Predicate<EntityTrackerEntry>()
    {
        public boolean a(@Nullable EntityTrackerEntry p_a_1_)
        {
            return p_a_1_ != null && (!p_a_1_.y() || p_a_1_.x().W().b("spectatorsGenerateChunks"));
        }
    };
    private final EntityPlayerMP c;
    private final List<EntityTrackerEntry> d = Lists.<EntityTrackerEntry>newArrayList();
    private final Long2ObjectMap<ot> e = new Long2ObjectOpenHashMap<ot>(4096);
    private final Set<ot> f = Sets.<ot>newHashSet();
    private final List<ot> g = Lists.<ot>newLinkedList();
    private final List<ot> h = Lists.<ot>newLinkedList();
    private final List<ot> i = Lists.<ot>newArrayList();
    private int j;
    private long k;
    private boolean l = true;
    private boolean m = true;

    public ou(EntityPlayerMP p_i952_1_)
    {
        this.c = p_i952_1_;
        this.a(p_i952_1_.u().am().s());
    }

    public EntityPlayerMP a()
    {
        return this.c;
    }

    public Iterator<BlockStatePaletteLinear> b()
    {
        final Iterator<ot> iterator = this.i.iterator();
        return new AbstractIterator<BlockStatePaletteLinear>()
        {
            protected BlockStatePaletteLinear a()
            {
                while (true)
                {
                    if (iterator.hasNext())
                    {
                        ot ot = iterator.next();
                        BlockStatePaletteLinear blockstatepalettelinear = ot.f();

                        if (blockstatepalettelinear == null)
                        {
                            continue;
                        }

                        if (!blockstatepalettelinear.v() && blockstatepalettelinear.u())
                        {
                            return blockstatepalettelinear;
                        }

                        if (!blockstatepalettelinear.j())
                        {
                            return blockstatepalettelinear;
                        }

                        if (!ot.a(128.0D, ou.a))
                        {
                            continue;
                        }

                        return blockstatepalettelinear;
                    }

                    return (BlockStatePaletteLinear)this.endOfData();
                }
            }
        };
    }

    public void c()
    {
        long i = this.c.R();

        if (i - this.k > 8000L)
        {
            this.k = i;

            for (int j = 0; j < this.i.size(); ++j)
            {
                ot ot = this.i.get(j);
                ot.d();
                ot.c();
            }
        }

        if (!this.f.isEmpty())
        {
            for (ot ot : this.f)
            {
                ot.d();
            }

            this.f.clear();
        }

        if (this.l && i % 4L == 0L)
        {
            this.l = false;
            Collections.sort(this.h, new Comparator<ot>()
            {
                public int a(ot p_a_1_, ot p_a_2_)
                {
                    return ComparisonChain.start().compare(p_a_1_.g(), p_a_2_.g()).result();
                }
            });
        }

        if (this.m && i % 4L == 2L)
        {
            this.m = false;
            Collections.sort(this.g, new Comparator<ot>()
            {
                public int a(ot p_a_1_, ot p_a_2_)
                {
                    return ComparisonChain.start().compare(p_a_1_.g(), p_a_2_.g()).result();
                }
            });
        }

        if (!this.h.isEmpty())
        {
            long l = System.nanoTime() + 50000000L;
            int k = 49;
            Iterator<ot> iterator = this.h.iterator();

            while (iterator.hasNext())
            {
                ot ot = iterator.next();

                if (ot.f() == null)
                {
                    boolean flag = ot.a(b);

                    if (ot.a(flag))
                    {
                        iterator.remove();

                        if (ot.b())
                        {
                            this.g.remove(ot);
                        }

                        --k;

                        if (k < 0 || System.nanoTime() > l)
                        {
                            break;
                        }
                    }
                }
            }
        }

        if (!this.g.isEmpty())
        {
            int i1 = 81;
            Iterator<ot> iterator1 = this.g.iterator();

            while (iterator1.hasNext())
            {
                ot ot = iterator1.next();

                if (ot.b())
                {
                    iterator1.remove();
                    --i1;

                    if (i1 < 0)
                    {
                        break;
                    }
                }
            }
        }

        if (this.d.isEmpty())
        {
            WorldProviderHell worldproviderhell = this.c.s;

            if (!worldproviderhell.canRespawnHere())
            {
                this.c.r().generateSkylightMap();
            }
        }
    }

    public boolean a(int p_a_1_, int p_a_2_)
    {
        long i = d(p_a_1_, p_a_2_);
        return this.e.get(i) != null;
    }

    @Nullable
    public ot b(int p_b_1_, int p_b_2_)
    {
        return (ot)this.e.get(d(p_b_1_, p_b_2_));
    }

    private ot c(int p_c_1_, int p_c_2_)
    {
        long i = d(p_c_1_, p_c_2_);
        ot ot = (ot)this.e.get(i);

        if (ot == null)
        {
            ot = new ot(this, p_c_1_, p_c_2_);
            this.e.put(i, ot);
            this.i.add(ot);

            if (ot.f() == null)
            {
                this.h.add(ot);
            }

            if (!ot.b())
            {
                this.g.add(ot);
            }
        }

        return ot;
    }

    public void a(BlockPos p_a_1_)
    {
        int i = p_a_1_.getX() >> 4;
        int j = p_a_1_.getZ() >> 4;
        ot ot = this.b(i, j);

        if (ot != null)
        {
            ot.a(p_a_1_.getX() & 15, p_a_1_.getY(), p_a_1_.getZ() & 15);
        }
    }

    public void a(EntityTrackerEntry p_a_1_)
    {
        int i = (int)p_a_1_.motionZ >> 4;
        int j = (int)p_a_1_.lastTrackedEntityPosY >> 4;
        p_a_1_.trackedEntity = p_a_1_.motionZ;
        p_a_1_.range = p_a_1_.lastTrackedEntityPosY;

        for (int k = i - this.j; k <= i + this.j; ++k)
        {
            for (int l = j - this.j; l <= j + this.j; ++l)
            {
                this.c(k, l).a(p_a_1_);
            }
        }

        this.d.add(p_a_1_);
        this.e();
    }

    public void b(EntityTrackerEntry p_b_1_)
    {
        int i = (int)p_b_1_.trackedEntity >> 4;
        int j = (int)p_b_1_.range >> 4;

        for (int k = i - this.j; k <= i + this.j; ++k)
        {
            for (int l = j - this.j; l <= j + this.j; ++l)
            {
                ot ot = this.b(k, l);

                if (ot != null)
                {
                    ot.b(p_b_1_);
                }
            }
        }

        this.d.remove(p_b_1_);
        this.e();
    }

    private boolean a(int p_a_1_, int p_a_2_, int p_a_3_, int p_a_4_, int p_a_5_)
    {
        int i = p_a_1_ - p_a_3_;
        int j = p_a_2_ - p_a_4_;

        if (i >= -p_a_5_ && i <= p_a_5_)
        {
            return j >= -p_a_5_ && j <= p_a_5_;
        }
        else
        {
            return false;
        }
    }

    public void c(EntityTrackerEntry p_c_1_)
    {
        int i = (int)p_c_1_.motionZ >> 4;
        int j = (int)p_c_1_.lastTrackedEntityPosY >> 4;
        double d0 = p_c_1_.trackedEntity - p_c_1_.motionZ;
        double d1 = p_c_1_.range - p_c_1_.lastTrackedEntityPosY;
        double d2 = d0 * d0 + d1 * d1;

        if (d2 >= 64.0D)
        {
            int k = (int)p_c_1_.trackedEntity >> 4;
            int l = (int)p_c_1_.range >> 4;
            int i1 = this.j;
            int j1 = i - k;
            int k1 = j - l;

            if (j1 != 0 || k1 != 0)
            {
                for (int l1 = i - i1; l1 <= i + i1; ++l1)
                {
                    for (int i2 = j - i1; i2 <= j + i1; ++i2)
                    {
                        if (!this.a(l1, i2, k, l, i1))
                        {
                            this.c(l1, i2).a(p_c_1_);
                        }

                        if (!this.a(l1 - j1, i2 - k1, i, j, i1))
                        {
                            ot ot = this.b(l1 - j1, i2 - k1);

                            if (ot != null)
                            {
                                ot.b(p_c_1_);
                            }
                        }
                    }
                }

                p_c_1_.trackedEntity = p_c_1_.motionZ;
                p_c_1_.range = p_c_1_.lastTrackedEntityPosY;
                this.e();
            }
        }
    }

    public boolean a(EntityTrackerEntry p_a_1_, int p_a_2_, int p_a_3_)
    {
        ot ot = this.b(p_a_2_, p_a_3_);
        return ot != null && ot.d(p_a_1_) && ot.e();
    }

    public void a(int p_a_1_)
    {
        p_a_1_ = IProgressUpdate.a(p_a_1_, 3, 32);

        if (p_a_1_ != this.j)
        {
            int i = p_a_1_ - this.j;

            for (EntityTrackerEntry entitytrackerentry : Lists.newArrayList(this.d))
            {
                int j = (int)entitytrackerentry.motionZ >> 4;
                int k = (int)entitytrackerentry.lastTrackedEntityPosY >> 4;

                if (i > 0)
                {
                    for (int j1 = j - p_a_1_; j1 <= j + p_a_1_; ++j1)
                    {
                        for (int k1 = k - p_a_1_; k1 <= k + p_a_1_; ++k1)
                        {
                            ot ot = this.c(j1, k1);

                            if (!ot.d(entitytrackerentry))
                            {
                                ot.a(entitytrackerentry);
                            }
                        }
                    }
                }
                else
                {
                    for (int l = j - this.j; l <= j + this.j; ++l)
                    {
                        for (int i1 = k - this.j; i1 <= k + this.j; ++i1)
                        {
                            if (!this.a(l, i1, j, k, p_a_1_))
                            {
                                this.c(l, i1).b(entitytrackerentry);
                            }
                        }
                    }
                }
            }

            this.j = p_a_1_;
            this.e();
        }
    }

    private void e()
    {
        this.l = true;
        this.m = true;
    }

    public static int b(int p_b_0_)
    {
        return p_b_0_ * 16 - 16;
    }

    private static long d(int p_d_0_, int p_d_1_)
    {
        return (long)p_d_0_ + 2147483647L | (long)p_d_1_ + 2147483647L << 32;
    }

    public void a(ot p_a_1_)
    {
        this.f.add(p_a_1_);
    }

    public void b(ot p_b_1_)
    {
        Explosion explosion = p_b_1_.a();
        long i = d(explosion.isFlaming, explosion.isSmoking);
        p_b_1_.c();
        this.e.remove(i);
        this.i.remove(p_b_1_);
        this.f.remove(p_b_1_);
        this.g.remove(p_b_1_);
        this.h.remove(p_b_1_);
        BlockStatePaletteLinear blockstatepalettelinear = p_b_1_.f();

        if (blockstatepalettelinear != null)
        {
            this.a().r().a(blockstatepalettelinear);
        }
    }
}
