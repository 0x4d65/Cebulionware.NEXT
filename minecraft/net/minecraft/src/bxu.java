package net.minecraft.src;

import com.google.common.collect.Queues;
import java.util.BitSet;
import java.util.EnumSet;
import java.util.Queue;
import java.util.Set;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.math.BlockPos;

public class bxu
{
    private static final int a = (int)Math.pow(16.0D, 0.0D);
    private static final int b = (int)Math.pow(16.0D, 1.0D);
    private static final int c = (int)Math.pow(16.0D, 2.0D);
    private final BitSet d = new BitSet(4096);
    private static final int[] e = new int[1352];
    private int f = 4096;

    public void a(BlockPos p_a_1_)
    {
        this.d.set(c(p_a_1_), true);
        --this.f;
    }

    private static int c(BlockPos p_c_0_)
    {
        return a(p_c_0_.getX() & 15, p_c_0_.getY() & 15, p_c_0_.getZ() & 15);
    }

    private static int a(int p_a_0_, int p_a_1_, int p_a_2_)
    {
        return p_a_0_ << 0 | p_a_1_ << 8 | p_a_2_ << 4;
    }

    public bxv a()
    {
        bxv bxv = new bxv();

        if (4096 - this.f < 256)
        {
            bxv.a(true);
        }
        else if (this.f == 0)
        {
            bxv.a(false);
        }
        else
        {
            for (int i : e)
            {
                if (!this.d.get(i))
                {
                    bxv.a(this.a(i));
                }
            }
        }

        return bxv;
    }

    public Set<EnumFacing> b(BlockPos p_b_1_)
    {
        return this.a(c(p_b_1_));
    }

    private Set<EnumFacing> a(int p_a_1_)
    {
        Set<EnumFacing> set = EnumSet.<EnumFacing>noneOf(EnumFacing.class);
        Queue<Integer> queue = Queues.<Integer>newArrayDeque();
        queue.add(LazyLoadBase.a(p_a_1_));
        this.d.set(p_a_1_, true);

        while (!queue.isEmpty())
        {
            int i = ((Integer)queue.poll()).intValue();
            this.a(i, set);

            for (EnumFacing enumfacing : EnumFacing.values())
            {
                int j = this.a(i, enumfacing);

                if (j >= 0 && !this.d.get(j))
                {
                    this.d.set(j, true);
                    queue.add(LazyLoadBase.a(j));
                }
            }
        }

        return set;
    }

    private void a(int p_a_1_, Set<EnumFacing> p_a_2_)
    {
        int i = p_a_1_ >> 0 & 15;

        if (i == 0)
        {
            p_a_2_.add(EnumFacing.WEST);
        }
        else if (i == 15)
        {
            p_a_2_.add(EnumFacing.EAST);
        }

        int j = p_a_1_ >> 8 & 15;

        if (j == 0)
        {
            p_a_2_.add(EnumFacing.DOWN);
        }
        else if (j == 15)
        {
            p_a_2_.add(EnumFacing.UP);
        }

        int k = p_a_1_ >> 4 & 15;

        if (k == 0)
        {
            p_a_2_.add(EnumFacing.NORTH);
        }
        else if (k == 15)
        {
            p_a_2_.add(EnumFacing.SOUTH);
        }
    }

    private int a(int p_a_1_, EnumFacing p_a_2_)
    {
        switch (p_a_2_)
        {
            case DOWN:
                if ((p_a_1_ >> 8 & 15) == 0)
                {
                    return -1;
                }

                return p_a_1_ - c;

            case UP:
                if ((p_a_1_ >> 8 & 15) == 15)
                {
                    return -1;
                }

                return p_a_1_ + c;

            case NORTH:
                if ((p_a_1_ >> 4 & 15) == 0)
                {
                    return -1;
                }

                return p_a_1_ - b;

            case SOUTH:
                if ((p_a_1_ >> 4 & 15) == 15)
                {
                    return -1;
                }

                return p_a_1_ + b;

            case WEST:
                if ((p_a_1_ >> 0 & 15) == 0)
                {
                    return -1;
                }

                return p_a_1_ - a;

            case EAST:
                if ((p_a_1_ >> 0 & 15) == 15)
                {
                    return -1;
                }

                return p_a_1_ + a;

            default:
                return -1;
        }
    }

    static
    {
        int i = 0;
        int j = 15;
        int k = 0;

        for (int l = 0; l < 16; ++l)
        {
            for (int i1 = 0; i1 < 16; ++i1)
            {
                for (int j1 = 0; j1 < 16; ++j1)
                {
                    if (l == 0 || l == 15 || i1 == 0 || i1 == 15 || j1 == 0 || j1 == 15)
                    {
                        e[k++] = a(l, i1, j1);
                    }
                }
            }
        }
    }
}
