package net.minecraft.src;

import javax.annotation.Nullable;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.toasts.TutorialToast;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.text.ITextComponent;

public class bkd implements TutorialToast
{
    private final bkd.a c;
    private final String d;
    private final String e;
    private TutorialToast.Icons f = TutorialToast.Icons.MOVEMENT_KEYS;
    private long g;
    private float h;
    private float i;
    private final boolean j;

    public bkd(bkd.a p_i213_1_, ITextComponent p_i213_2_, @Nullable ITextComponent p_i213_3_, boolean p_i213_4_)
    {
        this.c = p_i213_1_;
        this.d = p_i213_2_.getFormattedText();
        this.e = p_i213_3_ == null ? null : p_i213_3_.getFormattedText();
        this.j = p_i213_4_;
    }

    public TutorialToast.Icons a(bkc p_a_1_, long p_a_2_)
    {
        p_a_1_.b().N().a(a);
        ItemRenderer.d(1.0F, 1.0F, 1.0F);
        p_a_1_.b(0, 0, 0, 96, 160, 32);
        this.c.a(p_a_1_, 6, 6);

        if (this.e == null)
        {
            p_a_1_.b().fancyGraphics.a(this.d, 30, 12, -11534256);
        }
        else
        {
            p_a_1_.b().fancyGraphics.a(this.d, 30, 7, -11534256);
            p_a_1_.b().fancyGraphics.a(this.e, 30, 18, -16777216);
        }

        if (this.j)
        {
            ScaledResolution.a(3, 28, 157, 29, -1);
            float f = (float)IProgressUpdate.b((double)this.h, (double)this.i, (double)((float)(p_a_2_ - this.g) / 100.0F));
            int i;

            if (this.i >= this.h)
            {
                i = -16755456;
            }
            else
            {
                i = -11206656;
            }

            ScaledResolution.a(3, 28, (int)(3.0F + 154.0F * f), 29, i);
            this.h = f;
            this.g = p_a_2_;
        }

        return this.f;
    }

    public void func_193670_a()
    {
        this.f = TutorialToast.Icons.MOUSE;
    }

    public void func_193669_a(float p_193669_1_)
    {
        this.i = p_193669_1_;
    }

    public static enum a
    {
        a(0, 0),
        b(1, 0),
        c(2, 0),
        d(0, 1),
        e(1, 1);

        private final int f;
        private final int g;

        private a(int p_i212_3_, int p_i212_4_)
        {
            this.f = p_i212_3_;
            this.g = p_i212_4_;
        }

        public void a(ScaledResolution p_a_1_, int p_a_2_, int p_a_3_)
        {
            ItemRenderer.m();
            p_a_1_.b(p_a_2_, p_a_3_, 176 + this.f * 20, this.g * 20, 20, 20);
            ItemRenderer.m();
        }
    }
}
