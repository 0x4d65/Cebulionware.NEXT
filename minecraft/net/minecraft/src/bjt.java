package net.minecraft.src;

import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.dispenser.BehaviorProjectileDispense;

public class bjt extends GuiUtilRenderComponents
{
    protected BehaviorProjectileDispense o;
    protected boolean p;
    protected int q;
    protected int r;
    protected int s;
    protected int t;

    public bjt(int p_i192_1_, int p_i192_2_, int p_i192_3_, int p_i192_4_, int p_i192_5_, boolean p_i192_6_)
    {
        super(p_i192_1_, p_i192_2_, p_i192_3_, p_i192_4_, p_i192_5_, "");
        this.p = p_i192_6_;
    }

    public void a(int p_a_1_, int p_a_2_, int p_a_3_, int p_a_4_, BehaviorProjectileDispense p_a_5_)
    {
        this.q = p_a_1_;
        this.r = p_a_2_;
        this.s = p_a_3_;
        this.t = p_a_4_;
        this.o = p_a_5_;
    }

    public void b(boolean p_b_1_)
    {
        this.p = p_b_1_;
    }

    public boolean c()
    {
        return this.p;
    }

    public void c(int p_c_1_, int p_c_2_)
    {
        this.h = p_c_1_;
        this.i = p_c_2_;
    }

    public void a(GameSettings p_a_1_, int p_a_2_, int p_a_3_, float p_a_4_)
    {
        if (this.m)
        {
            this.n = p_a_2_ >= this.h && p_a_3_ >= this.i && p_a_2_ < this.h + this.f && p_a_3_ < this.i + this.g;
            p_a_1_.N().a(this.o);
            ItemRenderer.j();
            int i = this.q;
            int j = this.r;

            if (this.p)
            {
                i += this.s;
            }

            if (this.n)
            {
                j += this.t;
            }

            this.b(this.h, this.i, i, j, this.f, this.g);
            ItemRenderer.k();
        }
    }
}
