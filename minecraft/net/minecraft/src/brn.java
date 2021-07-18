package net.minecraft.src;

import net.minecraft.client.model.ModelParrot;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.boss.dragon.phase.PhaseBase;

public class brn extends ModelParrot
{
    private final brs a;
    private final brs b;
    private final brs c;
    private final brs d;
    private final brs e;
    private final brs f;
    private final brs g;
    private final brs h;
    private final brs i;
    private final brs j;
    private final brs k;
    private final brs l;
    private float m;

    public brn(float p_i229_1_)
    {
        this.s = 256;
        this.t = 256;
        this.a("body.body", 0, 0);
        this.a("wing.skin", -56, 88);
        this.a("wingtip.skin", -56, 144);
        this.a("rearleg.main", 0, 0);
        this.a("rearfoot.main", 112, 0);
        this.a("rearlegtip.main", 196, 0);
        this.a("head.upperhead", 112, 30);
        this.a("wing.bone", 112, 88);
        this.a("head.upperlip", 176, 44);
        this.a("jaw.jaw", 176, 65);
        this.a("frontleg.main", 112, 104);
        this.a("wingtip.bone", 112, 136);
        this.a("frontfoot.main", 144, 104);
        this.a("neck.box", 192, 104);
        this.a("frontlegtip.main", 226, 138);
        this.a("body.scale", 220, 53);
        this.a("head.scale", 0, 0);
        this.a("neck.scale", 48, 0);
        this.a("head.nostril", 112, 0);
        float f = -16.0F;
        this.a = new brs(this, "head");
        this.a.a("upperlip", -6.0F, -1.0F, -24.0F, 12, 5, 16);
        this.a.a("upperhead", -8.0F, -8.0F, -10.0F, 16, 16, 16);
        this.a.i = true;
        this.a.a("scale", -5.0F, -12.0F, -4.0F, 2, 4, 6);
        this.a.a("nostril", -5.0F, -3.0F, -22.0F, 2, 2, 4);
        this.a.i = false;
        this.a.a("scale", 3.0F, -12.0F, -4.0F, 2, 4, 6);
        this.a.a("nostril", 3.0F, -3.0F, -22.0F, 2, 2, 4);
        this.c = new brs(this, "jaw");
        this.c.a(0.0F, 4.0F, -8.0F);
        this.c.a("jaw", -6.0F, 0.0F, -16.0F, 12, 4, 16);
        this.a.a(this.c);
        this.b = new brs(this, "neck");
        this.b.a("box", -5.0F, -5.0F, -5.0F, 10, 10, 10);
        this.b.a("scale", -1.0F, -9.0F, -3.0F, 2, 4, 6);
        this.d = new brs(this, "body");
        this.d.a(0.0F, 4.0F, 8.0F);
        this.d.a("body", -12.0F, 0.0F, -16.0F, 24, 24, 64);
        this.d.a("scale", -1.0F, -6.0F, -10.0F, 2, 6, 12);
        this.d.a("scale", -1.0F, -6.0F, 10.0F, 2, 6, 12);
        this.d.a("scale", -1.0F, -6.0F, 30.0F, 2, 6, 12);
        this.k = new brs(this, "wing");
        this.k.a(-12.0F, 5.0F, 2.0F);
        this.k.a("bone", -56.0F, -4.0F, -4.0F, 56, 8, 8);
        this.k.a("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
        this.l = new brs(this, "wingtip");
        this.l.a(-56.0F, 0.0F, 0.0F);
        this.l.a("bone", -56.0F, -2.0F, -2.0F, 56, 4, 4);
        this.l.a("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
        this.k.a(this.l);
        this.f = new brs(this, "frontleg");
        this.f.a(-12.0F, 20.0F, 2.0F);
        this.f.a("main", -4.0F, -4.0F, -4.0F, 8, 24, 8);
        this.h = new brs(this, "frontlegtip");
        this.h.a(0.0F, 20.0F, -1.0F);
        this.h.a("main", -3.0F, -1.0F, -3.0F, 6, 24, 6);
        this.f.a(this.h);
        this.j = new brs(this, "frontfoot");
        this.j.a(0.0F, 23.0F, 0.0F);
        this.j.a("main", -4.0F, 0.0F, -12.0F, 8, 4, 16);
        this.h.a(this.j);
        this.e = new brs(this, "rearleg");
        this.e.a(-16.0F, 16.0F, 42.0F);
        this.e.a("main", -8.0F, -4.0F, -8.0F, 16, 32, 16);
        this.g = new brs(this, "rearlegtip");
        this.g.a(0.0F, 32.0F, -4.0F);
        this.g.a("main", -6.0F, -2.0F, 0.0F, 12, 32, 12);
        this.e.a(this.g);
        this.i = new brs(this, "rearfoot");
        this.i.a(0.0F, 31.0F, 4.0F);
        this.i.a("main", -9.0F, 0.0F, -20.0F, 18, 6, 24);
        this.g.a(this.i);
    }

    public void a(EnumCreatureType p_a_1_, float p_a_2_, float p_a_3_, float p_a_4_)
    {
        this.m = p_a_4_;
    }

    public void a(EntityList p_a_1_, float p_a_2_, float p_a_3_, float p_a_4_, float p_a_5_, float p_a_6_, float p_a_7_)
    {
        ItemRenderer.G();
        PhaseBase phasebase = (PhaseBase)p_a_1_;
        float f = phasebase.bE + (phasebase.bF - phasebase.bE) * this.m;
        this.c.f = (float)(Math.sin((double)(f * ((float)Math.PI * 2F))) + 1.0D) * 0.2F;
        float f1 = (float)(Math.sin((double)(f * ((float)Math.PI * 2F) - 1.0F)) + 1.0D);
        f1 = (f1 * f1 + f1 * 2.0F) * 0.05F;
        ItemRenderer.c(0.0F, f1 - 2.0F, -3.0F);
        ItemRenderer.b(f1 * 2.0F, 1.0F, 0.0F, 0.0F);
        float f2 = -30.0F;
        float f4 = 0.0F;
        float f5 = 1.5F;
        double[] adouble = phasebase.a(6, this.m);
        float f6 = this.a(phasebase.a(5, this.m)[0] - phasebase.a(10, this.m)[0]);
        float f7 = this.a(phasebase.a(5, this.m)[0] + (double)(f6 / 2.0F));
        float f8 = f * ((float)Math.PI * 2F);
        f2 = 20.0F;
        float f3 = -12.0F;

        for (int i = 0; i < 5; ++i)
        {
            double[] adouble1 = phasebase.a(5 - i, this.m);
            float f9 = (float)Math.cos((double)((float)i * 0.45F + f8)) * 0.15F;
            this.b.g = this.a(adouble1[0] - adouble[0]) * 0.017453292F * 1.5F;
            this.b.f = f9 + phasebase.a(i, adouble, adouble1) * 0.017453292F * 1.5F * 5.0F;
            this.b.h = -this.a(adouble1[0] - (double)f7) * 0.017453292F * 1.5F;
            this.b.d = f2;
            this.b.e = f3;
            this.b.c = f4;
            f2 = (float)((double)f2 + Math.sin((double)this.b.f) * 10.0D);
            f3 = (float)((double)f3 - Math.cos((double)this.b.g) * Math.cos((double)this.b.f) * 10.0D);
            f4 = (float)((double)f4 - Math.sin((double)this.b.g) * Math.cos((double)this.b.f) * 10.0D);
            this.b.a(p_a_7_);
        }

        this.a.d = f2;
        this.a.e = f3;
        this.a.c = f4;
        double[] adouble2 = phasebase.a(0, this.m);
        this.a.g = this.a(adouble2[0] - adouble[0]) * 0.017453292F;
        this.a.f = this.a((double)phasebase.a(6, adouble, adouble2)) * 0.017453292F * 1.5F * 5.0F;
        this.a.h = -this.a(adouble2[0] - (double)f7) * 0.017453292F;
        this.a.a(p_a_7_);
        ItemRenderer.G();
        ItemRenderer.c(0.0F, 1.0F, 0.0F);
        ItemRenderer.b(-f6 * 1.5F, 0.0F, 0.0F, 1.0F);
        ItemRenderer.c(0.0F, -1.0F, 0.0F);
        this.d.h = 0.0F;
        this.d.a(p_a_7_);

        for (int j = 0; j < 2; ++j)
        {
            ItemRenderer.q();
            float f11 = f * ((float)Math.PI * 2F);
            this.k.f = 0.125F - (float)Math.cos((double)f11) * 0.2F;
            this.k.g = 0.25F;
            this.k.h = (float)(Math.sin((double)f11) + 0.125D) * 0.8F;
            this.l.h = -((float)(Math.sin((double)(f11 + 2.0F)) + 0.5D)) * 0.75F;
            this.e.f = 1.0F + f1 * 0.1F;
            this.g.f = 0.5F + f1 * 0.1F;
            this.i.f = 0.75F + f1 * 0.1F;
            this.f.f = 1.3F + f1 * 0.1F;
            this.h.f = -0.5F - f1 * 0.1F;
            this.j.f = 0.75F + f1 * 0.1F;
            this.k.a(p_a_7_);
            this.f.a(p_a_7_);
            this.e.a(p_a_7_);
            ItemRenderer.b(-1.0F, 1.0F, 1.0F);

            if (j == 0)
            {
                ItemRenderer.a(ItemRenderer.i.a);
            }
        }

        ItemRenderer.H();
        ItemRenderer.a(ItemRenderer.i.b);
        ItemRenderer.r();
        float f10 = -((float)Math.sin((double)(f * ((float)Math.PI * 2F)))) * 0.0F;
        f8 = f * ((float)Math.PI * 2F);
        f2 = 10.0F;
        f3 = 60.0F;
        f4 = 0.0F;
        adouble = phasebase.a(11, this.m);

        for (int k = 0; k < 12; ++k)
        {
            adouble2 = phasebase.a(12 + k, this.m);
            f10 = (float)((double)f10 + Math.sin((double)((float)k * 0.45F + f8)) * 0.05000000074505806D);
            this.b.g = (this.a(adouble2[0] - adouble[0]) * 1.5F + 180.0F) * 0.017453292F;
            this.b.f = f10 + (float)(adouble2[1] - adouble[1]) * 0.017453292F * 1.5F * 5.0F;
            this.b.h = this.a(adouble2[0] - (double)f7) * 0.017453292F * 1.5F;
            this.b.d = f2;
            this.b.e = f3;
            this.b.c = f4;
            f2 = (float)((double)f2 + Math.sin((double)this.b.f) * 10.0D);
            f3 = (float)((double)f3 - Math.cos((double)this.b.g) * Math.cos((double)this.b.f) * 10.0D);
            f4 = (float)((double)f4 - Math.sin((double)this.b.g) * Math.cos((double)this.b.f) * 10.0D);
            this.b.a(p_a_7_);
        }

        ItemRenderer.H();
    }

    private float a(double p_a_1_)
    {
        while (p_a_1_ >= 180.0D)
        {
            p_a_1_ -= 360.0D;
        }

        while (p_a_1_ < -180.0D)
        {
            p_a_1_ += 360.0D;
        }

        return (float)p_a_1_;
    }
}
