package net.minecraft.src;

import net.minecraft.util.IProgressUpdate;
import org.apache.commons.lang3.Validate;

public class qw
{
    private final long[] a;
    private final int b;
    private final long c;
    private final int d;

    public qw(int mcIn, int p_i1005_2_)
    {
        Validate.inclusiveBetween(1L, 32L, (long)mcIn);
        this.d = p_i1005_2_;
        this.b = mcIn;
        this.c = (1L << mcIn) - 1L;
        this.a = new long[IProgressUpdate.c(p_i1005_2_ * mcIn, 64) / 64];
    }

    public void a(int p_a_1_, int p_a_2_)
    {
        Validate.inclusiveBetween(0L, (long)(this.d - 1), (long)p_a_1_);
        Validate.inclusiveBetween(0L, this.c, (long)p_a_2_);
        int i = p_a_1_ * this.b;
        int j = i / 64;
        int k = ((p_a_1_ + 1) * this.b - 1) / 64;
        int l = i % 64;
        this.a[j] = this.a[j] & ~(this.c << l) | ((long)p_a_2_ & this.c) << l;

        if (j != k)
        {
            int i1 = 64 - l;
            int j1 = this.b - i1;
            this.a[k] = this.a[k] >>> j1 << j1 | ((long)p_a_2_ & this.c) >> i1;
        }
    }

    public int a(int p_a_1_)
    {
        Validate.inclusiveBetween(0L, (long)(this.d - 1), (long)p_a_1_);
        int i = p_a_1_ * this.b;
        int j = i / 64;
        int k = ((p_a_1_ + 1) * this.b - 1) / 64;
        int l = i % 64;

        if (j == k)
        {
            return (int)(this.a[j] >>> l & this.c);
        }
        else
        {
            int i1 = 64 - l;
            return (int)((this.a[j] >>> l | this.a[k] << i1) & this.c);
        }
    }

    public long[] a()
    {
        return this.a;
    }

    public int b()
    {
        return this.d;
    }
}
