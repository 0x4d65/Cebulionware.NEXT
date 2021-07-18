package net.minecraft.src;

import java.util.Random;
import net.minecraft.block.material.MaterialLogic;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

public class bcp extends MaterialLogic
{
    private final NoiseGeneratorPerlin[] a;
    private final int b;

    public bcp(Random p_i2036_1_, int p_i2036_2_)
    {
        this.b = p_i2036_2_;
        this.a = new NoiseGeneratorPerlin[p_i2036_2_];

        for (int i = 0; i < p_i2036_2_; ++i)
        {
            this.a[i] = new NoiseGeneratorPerlin(p_i2036_1_);
        }
    }

    public double[] a(double[] p_a_1_, int p_a_2_, int p_a_3_, int p_a_4_, int p_a_5_, int p_a_6_, int p_a_7_, double p_a_8_, double p_a_10_, double p_a_12_)
    {
        if (p_a_1_ == null)
        {
            p_a_1_ = new double[p_a_5_ * p_a_6_ * p_a_7_];
        }
        else
        {
            for (int i = 0; i < p_a_1_.length; ++i)
            {
                p_a_1_[i] = 0.0D;
            }
        }

        double d3 = 1.0D;

        for (int j = 0; j < this.b; ++j)
        {
            double d0 = (double)p_a_2_ * d3 * p_a_8_;
            double d1 = (double)p_a_3_ * d3 * p_a_10_;
            double d2 = (double)p_a_4_ * d3 * p_a_12_;
            long k = IProgressUpdate.d(d0);
            long l = IProgressUpdate.d(d2);
            d0 = d0 - (double)k;
            d2 = d2 - (double)l;
            k = k % 16777216L;
            l = l % 16777216L;
            d0 = d0 + (double)k;
            d2 = d2 + (double)l;
            this.a[j].a(p_a_1_, d0, d1, d2, p_a_5_, p_a_6_, p_a_7_, p_a_8_ * d3, p_a_10_ * d3, p_a_12_ * d3, d3);
            d3 /= 2.0D;
        }

        return p_a_1_;
    }

    public double[] a(double[] p_a_1_, int p_a_2_, int p_a_3_, int p_a_4_, int p_a_5_, double p_a_6_, double p_a_8_, double p_a_10_)
    {
        return this.a(p_a_1_, p_a_2_, 10, p_a_3_, p_a_4_, 1, p_a_5_, p_a_6_, 1.0D, p_a_8_);
    }
}
