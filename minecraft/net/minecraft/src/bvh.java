package net.minecraft.src;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.RenderList;
import net.minecraft.client.renderer.chunk.VboChunkFactory;
import net.minecraft.client.renderer.chunk.VisGraph;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldEventListener;

public class bvh
{
    protected final RenderList a;
    protected final IWorldEventListener b;
    protected int c;
    protected int d;
    protected int e;
    public VboChunkFactory[] f;

    public bvh(IWorldEventListener p_i565_1_, int p_i565_2_, RenderList p_i565_3_, VisGraph p_i565_4_)
    {
        this.a = p_i565_3_;
        this.b = p_i565_1_;
        this.a(p_i565_2_);
        this.a(p_i565_4_);
    }

    protected void a(VisGraph p_a_1_)
    {
        int i = this.d * this.c * this.e;
        this.f = new VboChunkFactory[i];
        int j = 0;

        for (int k = 0; k < this.d; ++k)
        {
            for (int l = 0; l < this.c; ++l)
            {
                for (int i1 = 0; i1 < this.e; ++i1)
                {
                    int j1 = (i1 * this.c + l) * this.d + k;
                    this.f[j1] = p_a_1_.a(this.b, this.a, j++);
                    this.f[j1].a(k * 16, l * 16, i1 * 16);
                }
            }
        }
    }

    public void a()
    {
        for (VboChunkFactory vbochunkfactory : this.f)
        {
            vbochunkfactory.a();
        }
    }

    protected void a(int p_a_1_)
    {
        int i = p_a_1_ * 2 + 1;
        this.d = i;
        this.c = 16;
        this.e = i;
    }

    public void a(double p_a_1_, double p_a_3_)
    {
        int i = IProgressUpdate.c(p_a_1_) - 8;
        int j = IProgressUpdate.c(p_a_3_) - 8;
        int k = this.d * 16;

        for (int l = 0; l < this.d; ++l)
        {
            int i1 = this.a(i, k, l);

            for (int j1 = 0; j1 < this.e; ++j1)
            {
                int k1 = this.a(j, k, j1);

                for (int l1 = 0; l1 < this.c; ++l1)
                {
                    int i2 = l1 * 16;
                    VboChunkFactory vbochunkfactory = this.f[(j1 * this.c + l1) * this.d + l];
                    vbochunkfactory.a(i1, i2, k1);
                }
            }
        }
    }

    private int a(int p_a_1_, int p_a_2_, int p_a_3_)
    {
        int i = p_a_3_ * 16;
        int j = i - p_a_1_ + p_a_2_ / 2;

        if (j < 0)
        {
            j -= p_a_2_ - 1;
        }

        return i - j / p_a_2_ * p_a_2_;
    }

    public void a(int p_a_1_, int p_a_2_, int p_a_3_, int p_a_4_, int p_a_5_, int p_a_6_, boolean p_a_7_)
    {
        int i = IProgressUpdate.a(p_a_1_, 16);
        int j = IProgressUpdate.a(p_a_2_, 16);
        int k = IProgressUpdate.a(p_a_3_, 16);
        int l = IProgressUpdate.a(p_a_4_, 16);
        int i1 = IProgressUpdate.a(p_a_5_, 16);
        int j1 = IProgressUpdate.a(p_a_6_, 16);

        for (int k1 = i; k1 <= l; ++k1)
        {
            int l1 = k1 % this.d;

            if (l1 < 0)
            {
                l1 += this.d;
            }

            for (int i2 = j; i2 <= i1; ++i2)
            {
                int j2 = i2 % this.c;

                if (j2 < 0)
                {
                    j2 += this.c;
                }

                for (int k2 = k; k2 <= j1; ++k2)
                {
                    int l2 = k2 % this.e;

                    if (l2 < 0)
                    {
                        l2 += this.e;
                    }

                    int i3 = (l2 * this.c + j2) * this.d + l1;
                    VboChunkFactory vbochunkfactory = this.f[i3];
                    vbochunkfactory.a(p_a_7_);
                }
            }
        }
    }

    @Nullable
    protected VboChunkFactory a(BlockPos p_a_1_)
    {
        int i = IProgressUpdate.a(p_a_1_.getX(), 16);
        int j = IProgressUpdate.a(p_a_1_.getY(), 16);
        int k = IProgressUpdate.a(p_a_1_.getZ(), 16);

        if (j >= 0 && j < this.c)
        {
            i = i % this.d;

            if (i < 0)
            {
                i += this.d;
            }

            k = k % this.e;

            if (k < 0)
            {
                k += this.e;
            }

            int l = (k * this.c + j) * this.d + i;
            return this.f[l];
        }
        else
        {
            return null;
        }
    }
}
