package net.minecraft.src;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.util.IProgressUpdate;

public class wp
{
    private final IEntityLivingData a;
    private float b;
    private float c;
    private boolean d;
    private double e;
    private double f;
    private double g;

    public wp(IEntityLivingData p_i1081_1_)
    {
        this.a = p_i1081_1_;
    }

    public void a(EntityList p_a_1_, float p_a_2_, float p_a_3_)
    {
        this.e = p_a_1_.p;

        if (p_a_1_ instanceof EnumCreatureType)
        {
            this.f = p_a_1_.q + (double)p_a_1_.by();
        }
        else
        {
            this.f = (p_a_1_.bw().ONE + p_a_1_.bw().UNIT_Y) / 2.0D;
        }

        this.g = p_a_1_.r;
        this.b = p_a_2_;
        this.c = p_a_3_;
        this.d = true;
    }

    public void a(double p_a_1_, double p_a_3_, double p_a_5_, float p_a_7_, float p_a_8_)
    {
        this.e = p_a_1_;
        this.f = p_a_3_;
        this.g = p_a_5_;
        this.b = p_a_7_;
        this.c = p_a_8_;
        this.d = true;
    }

    public void a()
    {
        this.a.w = 0.0F;

        if (this.d)
        {
            this.d = false;
            double d0 = this.e - this.a.p;
            double d1 = this.f - (this.a.q + (double)this.a.by());
            double d2 = this.g - this.a.r;
            double d3 = (double)IProgressUpdate.a(d0 * d0 + d2 * d2);
            float f = (float)(IProgressUpdate.c(d2, d0) * (180D / Math.PI)) - 90.0F;
            float f1 = (float)(-(IProgressUpdate.c(d1, d3) * (180D / Math.PI)));
            this.a.w = this.a(this.a.w, f1, this.c);
            this.a.aP = this.a(this.a.aP, f, this.b);
        }
        else
        {
            this.a.aP = this.a(this.a.aP, this.a.aN, 10.0F);
        }

        float f2 = IProgressUpdate.g(this.a.aP - this.a.aN);

        if (!this.a.x().o())
        {
            if (f2 < -75.0F)
            {
                this.a.aP = this.a.aN - 75.0F;
            }

            if (f2 > 75.0F)
            {
                this.a.aP = this.a.aN + 75.0F;
            }
        }
    }

    private float a(float p_a_1_, float p_a_2_, float p_a_3_)
    {
        float f = IProgressUpdate.g(p_a_2_ - p_a_1_);

        if (f > p_a_3_)
        {
            f = p_a_3_;
        }

        if (f < -p_a_3_)
        {
            f = -p_a_3_;
        }

        return p_a_1_ + f;
    }

    public boolean b()
    {
        return this.d;
    }

    public double e()
    {
        return this.e;
    }

    public double f()
    {
        return this.f;
    }

    public double g()
    {
        return this.g;
    }
}
