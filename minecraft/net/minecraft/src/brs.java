package net.minecraft.src;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.model.ModelParrot;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.client.renderer.VertexBufferUploader;
import net.minecraft.util.MouseHelper;

public class brs
{
    public float a;
    public float b;
    private int r;
    private int s;
    public float c;
    public float d;
    public float e;
    public float f;
    public float g;
    public float h;
    private boolean t;
    private int u;
    public boolean i;
    public boolean j;
    public boolean k;
    public List<ModelRenderer> l;
    public List<brs> m;
    public final String n;
    private final ModelParrot v;
    public float o;
    public float p;
    public float q;

    public brs(ModelParrot p_i248_1_, String p_i248_2_)
    {
        this.a = 64.0F;
        this.b = 32.0F;
        this.j = true;
        this.l = Lists.<ModelRenderer>newArrayList();
        this.v = p_i248_1_;
        p_i248_1_.r.add(this);
        this.n = p_i248_2_;
        this.b(p_i248_1_.s, p_i248_1_.t);
    }

    public brs(ModelParrot p_i249_1_)
    {
        this(p_i249_1_, (String)null);
    }

    public brs(ModelParrot p_i250_1_, int p_i250_2_, int p_i250_3_)
    {
        this(p_i250_1_);
        this.a(p_i250_2_, p_i250_3_);
    }

    public void a(brs p_a_1_)
    {
        if (this.m == null)
        {
            this.m = Lists.<brs>newArrayList();
        }

        this.m.add(p_a_1_);
    }

    public brs a(int p_a_1_, int p_a_2_)
    {
        this.r = p_a_1_;
        this.s = p_a_2_;
        return this;
    }

    public brs a(String p_a_1_, float p_a_2_, float p_a_3_, float p_a_4_, int p_a_5_, int p_a_6_, int p_a_7_)
    {
        p_a_1_ = this.n + "." + p_a_1_;
        brt brt = this.v.a(p_a_1_);
        this.a(brt.a, brt.b);
        this.l.add((new ModelRenderer(this, this.r, this.s, p_a_2_, p_a_3_, p_a_4_, p_a_5_, p_a_6_, p_a_7_, 0.0F)).a(p_a_1_));
        return this;
    }

    public brs a(float p_a_1_, float p_a_2_, float p_a_3_, int p_a_4_, int p_a_5_, int p_a_6_)
    {
        this.l.add(new ModelRenderer(this, this.r, this.s, p_a_1_, p_a_2_, p_a_3_, p_a_4_, p_a_5_, p_a_6_, 0.0F));
        return this;
    }

    public brs a(float p_a_1_, float p_a_2_, float p_a_3_, int p_a_4_, int p_a_5_, int p_a_6_, boolean p_a_7_)
    {
        this.l.add(new ModelRenderer(this, this.r, this.s, p_a_1_, p_a_2_, p_a_3_, p_a_4_, p_a_5_, p_a_6_, 0.0F, p_a_7_));
        return this;
    }

    public void a(float p_a_1_, float p_a_2_, float p_a_3_, int p_a_4_, int p_a_5_, int p_a_6_, float p_a_7_)
    {
        this.l.add(new ModelRenderer(this, this.r, this.s, p_a_1_, p_a_2_, p_a_3_, p_a_4_, p_a_5_, p_a_6_, p_a_7_));
    }

    public void a(float p_a_1_, float p_a_2_, float p_a_3_)
    {
        this.c = p_a_1_;
        this.d = p_a_2_;
        this.e = p_a_3_;
    }

    public void a(float p_a_1_)
    {
        if (!this.k)
        {
            if (this.j)
            {
                if (!this.t)
                {
                    this.d(p_a_1_);
                }

                ItemRenderer.c(this.o, this.p, this.q);

                if (this.f == 0.0F && this.g == 0.0F && this.h == 0.0F)
                {
                    if (this.c == 0.0F && this.d == 0.0F && this.e == 0.0F)
                    {
                        ItemRenderer.s(this.u);

                        if (this.m != null)
                        {
                            for (int k = 0; k < this.m.size(); ++k)
                            {
                                ((brs)this.m.get(k)).a(p_a_1_);
                            }
                        }
                    }
                    else
                    {
                        ItemRenderer.c(this.c * p_a_1_, this.d * p_a_1_, this.e * p_a_1_);
                        ItemRenderer.s(this.u);

                        if (this.m != null)
                        {
                            for (int j = 0; j < this.m.size(); ++j)
                            {
                                ((brs)this.m.get(j)).a(p_a_1_);
                            }
                        }

                        ItemRenderer.c(-this.c * p_a_1_, -this.d * p_a_1_, -this.e * p_a_1_);
                    }
                }
                else
                {
                    ItemRenderer.G();
                    ItemRenderer.c(this.c * p_a_1_, this.d * p_a_1_, this.e * p_a_1_);

                    if (this.h != 0.0F)
                    {
                        ItemRenderer.b(this.h * (180F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
                    }

                    if (this.g != 0.0F)
                    {
                        ItemRenderer.b(this.g * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
                    }

                    if (this.f != 0.0F)
                    {
                        ItemRenderer.b(this.f * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
                    }

                    ItemRenderer.s(this.u);

                    if (this.m != null)
                    {
                        for (int i = 0; i < this.m.size(); ++i)
                        {
                            ((brs)this.m.get(i)).a(p_a_1_);
                        }
                    }

                    ItemRenderer.H();
                }

                ItemRenderer.c(-this.o, -this.p, -this.q);
            }
        }
    }

    public void b(float p_b_1_)
    {
        if (!this.k)
        {
            if (this.j)
            {
                if (!this.t)
                {
                    this.d(p_b_1_);
                }

                ItemRenderer.G();
                ItemRenderer.c(this.c * p_b_1_, this.d * p_b_1_, this.e * p_b_1_);

                if (this.g != 0.0F)
                {
                    ItemRenderer.b(this.g * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
                }

                if (this.f != 0.0F)
                {
                    ItemRenderer.b(this.f * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
                }

                if (this.h != 0.0F)
                {
                    ItemRenderer.b(this.h * (180F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
                }

                ItemRenderer.s(this.u);
                ItemRenderer.H();
            }
        }
    }

    public void c(float p_c_1_)
    {
        if (!this.k)
        {
            if (this.j)
            {
                if (!this.t)
                {
                    this.d(p_c_1_);
                }

                if (this.f == 0.0F && this.g == 0.0F && this.h == 0.0F)
                {
                    if (this.c != 0.0F || this.d != 0.0F || this.e != 0.0F)
                    {
                        ItemRenderer.c(this.c * p_c_1_, this.d * p_c_1_, this.e * p_c_1_);
                    }
                }
                else
                {
                    ItemRenderer.c(this.c * p_c_1_, this.d * p_c_1_, this.e * p_c_1_);

                    if (this.h != 0.0F)
                    {
                        ItemRenderer.b(this.h * (180F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
                    }

                    if (this.g != 0.0F)
                    {
                        ItemRenderer.b(this.g * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
                    }

                    if (this.f != 0.0F)
                    {
                        ItemRenderer.b(this.f * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
                    }
                }
            }
        }
    }

    private void d(float p_d_1_)
    {
        this.u = MouseHelper.a(1);
        ItemRenderer.f(this.u, 4864);
        RegionRenderCacheBuilder regionrendercachebuilder = VertexBufferUploader.a().c();

        for (int i = 0; i < this.l.size(); ++i)
        {
            ((ModelRenderer)this.l.get(i)).a(regionrendercachebuilder, p_d_1_);
        }

        ItemRenderer.K();
        this.t = true;
    }

    public brs b(int p_b_1_, int p_b_2_)
    {
        this.a = (float)p_b_1_;
        this.b = (float)p_b_2_;
        return this;
    }
}
