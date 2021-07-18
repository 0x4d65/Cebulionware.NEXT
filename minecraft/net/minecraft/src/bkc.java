package net.minecraft.src;

import com.google.common.collect.Queues;
import java.util.Arrays;
import java.util.Deque;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.toasts.TutorialToast;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.IProgressUpdate;

public class bkc extends ScaledResolution
{
    private final GameSettings a;
    private final bkc.a<?>[] f = new bkc.a[5];
    private final Deque<TutorialToast> g = Queues.<TutorialToast>newArrayDeque();

    public bkc(GameSettings p_i215_1_)
    {
        this.a = p_i215_1_;
    }

    public void a(NarratorChatListener p_a_1_)
    {
        if (!this.a.snooperEnabled.av)
        {
            Minecraft.run();

            for (int i = 0; i < this.f.length; ++i)
            {
                bkc.a<?> a = this.f[i];

                if (a != null && a.a(p_a_1_.a(), i))
                {
                    this.f[i] = null;
                }

                if (this.f[i] == null && !this.g.isEmpty())
                {
                    this.f[i] = new bkc.a(this.g.removeFirst());
                }
            }
        }
    }

    @Nullable
    public <T extends TutorialToast> T a(Class <? extends T > p_a_1_, Object p_a_2_)
    {
        for (bkc.a<?> a : this.f)
        {
            if (a != null && p_a_1_.isAssignableFrom(a.a().getClass()) && a.a().b().equals(p_a_2_))
            {
                return (T)a.a();
            }
        }

        for (TutorialToast tutorialtoast : this.g)
        {
            if (p_a_1_.isAssignableFrom(tutorialtoast.getClass()) && tutorialtoast.b().equals(p_a_2_))
            {
                return (T)tutorialtoast;
            }
        }

        return (T)null;
    }

    public void a()
    {
        Arrays.fill(this.f, (Object)null);
        this.g.clear();
    }

    public void a(TutorialToast p_a_1_)
    {
        this.g.add(p_a_1_);
    }

    public GameSettings b()
    {
        return this.a;
    }

    class a<T extends TutorialToast>
    {
        private final T b;
        private long c;
        private long d;
        private TutorialToast.Icons e;

        private a(T p_i206_2_)
        {
            this.c = -1L;
            this.d = -1L;
            this.e = TutorialToast.Icons.MOVEMENT_KEYS;
            this.b = p_i206_2_;
        }

        public T a()
        {
            return this.b;
        }

        private float a(long p_a_1_)
        {
            float f = IProgressUpdate.a((float)(p_a_1_ - this.c) / 600.0F, 0.0F, 1.0F);
            f = f * f;
            return this.e == TutorialToast.Icons.MOUSE ? 1.0F - f : f;
        }

        public boolean a(int p_a_1_, int p_a_2_)
        {
            long i = GameSettings.I();

            if (this.c == -1L)
            {
                this.c = i;
                this.e.a(bkc.this.a.U());
            }

            if (this.e == TutorialToast.Icons.MOVEMENT_KEYS && i - this.c <= 600L)
            {
                this.d = i;
            }

            ItemRenderer.G();
            ItemRenderer.c((float)p_a_1_ - 160.0F * this.a(i), (float)(p_a_2_ * 32), (float)(500 + p_a_2_));
            TutorialToast.Icons tutorialtoast$icons = this.b.a(bkc.this, i - this.d);
            ItemRenderer.H();

            if (tutorialtoast$icons != this.e)
            {
                this.c = i - (long)((int)((1.0F - this.a(i)) * 600.0F));
                this.e = tutorialtoast$icons;
                this.e.a(bkc.this.a.U());
            }

            return this.e == TutorialToast.Icons.MOUSE && i - this.c > 600L;
        }
    }
}
