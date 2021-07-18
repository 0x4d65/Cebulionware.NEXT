package net.minecraft.src;

import java.util.Random;

public class bct
{
    private static final int[][] e = new int[][] {{1, 1, 0}, { -1, 1, 0}, {1, -1, 0}, { -1, -1, 0}, {1, 0, 1}, { -1, 0, 1}, {1, 0, -1}, { -1, 0, -1}, {0, 1, 1}, {0, -1, 1}, {0, 1, -1}, {0, -1, -1}};
    public static final double a = Math.sqrt(3.0D);
    private final int[] f;
    public double b;
    public double c;
    public double d;
    private static final double g = 0.5D * (a - 1.0D);
    private static final double h = (3.0D - a) / 6.0D;

    public bct()
    {
        this(new Random());
    }

    public bct(Random p_i2038_1_)
    {
        this.f = new int[512];
        this.b = p_i2038_1_.nextDouble() * 256.0D;
        this.c = p_i2038_1_.nextDouble() * 256.0D;
        this.d = p_i2038_1_.nextDouble() * 256.0D;

        for (int i = 0; i < 256; this.f[i] = i++)
        {
            ;
        }

        for (int l = 0; l < 256; ++l)
        {
            int j = p_i2038_1_.nextInt(256 - l) + l;
            int k = this.f[l];
            this.f[l] = this.f[j];
            this.f[j] = k;
            this.f[l + 256] = this.f[l];
        }
    }

    private static int a(double p_a_0_)
    {
        return p_a_0_ > 0.0D ? (int)p_a_0_ : (int)p_a_0_ - 1;
    }

    private static double a(int[] p_a_0_, double p_a_1_, double p_a_3_)
    {
        return (double)p_a_0_[0] * p_a_1_ + (double)p_a_0_[1] * p_a_3_;
    }

    public double a(double p_a_1_, double p_a_3_)
    {
        double d3 = 0.5D * (a - 1.0D);
        double d4 = (p_a_1_ + p_a_3_) * d3;
        int i = a(p_a_1_ + d4);
        int j = a(p_a_3_ + d4);
        double d5 = (3.0D - a) / 6.0D;
        double d6 = (double)(i + j) * d5;
        double d7 = (double)i - d6;
        double d8 = (double)j - d6;
        double d9 = p_a_1_ - d7;
        double d10 = p_a_3_ - d8;
        int k;
        int l;

        if (d9 > d10)
        {
            k = 1;
            l = 0;
        }
        else
        {
            k = 0;
            l = 1;
        }

        double d11 = d9 - (double)k + d5;
        double d12 = d10 - (double)l + d5;
        double d13 = d9 - 1.0D + 2.0D * d5;
        double d14 = d10 - 1.0D + 2.0D * d5;
        int i1 = i & 255;
        int j1 = j & 255;
        int k1 = this.f[i1 + this.f[j1]] % 12;
        int l1 = this.f[i1 + k + this.f[j1 + l]] % 12;
        int i2 = this.f[i1 + 1 + this.f[j1 + 1]] % 12;
        double d15 = 0.5D - d9 * d9 - d10 * d10;
        double d0;

        if (d15 < 0.0D)
        {
            d0 = 0.0D;
        }
        else
        {
            d15 = d15 * d15;
            d0 = d15 * d15 * a(e[k1], d9, d10);
        }

        double d16 = 0.5D - d11 * d11 - d12 * d12;
        double d1;

        if (d16 < 0.0D)
        {
            d1 = 0.0D;
        }
        else
        {
            d16 = d16 * d16;
            d1 = d16 * d16 * a(e[l1], d11, d12);
        }

        double d17 = 0.5D - d13 * d13 - d14 * d14;
        double d2;

        if (d17 < 0.0D)
        {
            d2 = 0.0D;
        }
        else
        {
            d17 = d17 * d17;
            d2 = d17 * d17 * a(e[i2], d13, d14);
        }

        return 70.0D * (d0 + d1 + d2);
    }

    public void a(double[] p_a_1_, double p_a_2_, double p_a_4_, int p_a_6_, int p_a_7_, double p_a_8_, double p_a_10_, double p_a_12_)
    {
        int i = 0;

        for (int j = 0; j < p_a_7_; ++j)
        {
            double d0 = (p_a_4_ + (double)j) * p_a_10_ + this.c;

            for (int k = 0; k < p_a_6_; ++k)
            {
                double d1 = (p_a_2_ + (double)k) * p_a_8_ + this.b;
                double d5 = (d1 + d0) * g;
                int l = a(d1 + d5);
                int i1 = a(d0 + d5);
                double d6 = (double)(l + i1) * h;
                double d7 = (double)l - d6;
                double d8 = (double)i1 - d6;
                double d9 = d1 - d7;
                double d10 = d0 - d8;
                int j1;
                int k1;

                if (d9 > d10)
                {
                    j1 = 1;
                    k1 = 0;
                }
                else
                {
                    j1 = 0;
                    k1 = 1;
                }

                double d11 = d9 - (double)j1 + h;
                double d12 = d10 - (double)k1 + h;
                double d13 = d9 - 1.0D + 2.0D * h;
                double d14 = d10 - 1.0D + 2.0D * h;
                int l1 = l & 255;
                int i2 = i1 & 255;
                int j2 = this.f[l1 + this.f[i2]] % 12;
                int k2 = this.f[l1 + j1 + this.f[i2 + k1]] % 12;
                int l2 = this.f[l1 + 1 + this.f[i2 + 1]] % 12;
                double d15 = 0.5D - d9 * d9 - d10 * d10;
                double d2;

                if (d15 < 0.0D)
                {
                    d2 = 0.0D;
                }
                else
                {
                    d15 = d15 * d15;
                    d2 = d15 * d15 * a(e[j2], d9, d10);
                }

                double d16 = 0.5D - d11 * d11 - d12 * d12;
                double d3;

                if (d16 < 0.0D)
                {
                    d3 = 0.0D;
                }
                else
                {
                    d16 = d16 * d16;
                    d3 = d16 * d16 * a(e[k2], d11, d12);
                }

                double d17 = 0.5D - d13 * d13 - d14 * d14;
                double d4;

                if (d17 < 0.0D)
                {
                    d4 = 0.0D;
                }
                else
                {
                    d17 = d17 * d17;
                    d4 = d17 * d17 * a(e[l2], d13, d14);
                }

                int i3 = i++;
                p_a_1_[i3] += 70.0D * (d2 + d3 + d4) * p_a_12_;
            }
        }
    }
}
