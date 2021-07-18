package net.minecraft.src;

import javax.annotation.Nullable;

public class rg<V>
{
    private transient rg.a<V>[] a = new rg.a[16];
    private transient int b;
    private int c = 12;
    private final float d = 0.75F;

    private static int g(int p_g_0_)
    {
        p_g_0_ = p_g_0_ ^ p_g_0_ >>> 20 ^ p_g_0_ >>> 12;
        return p_g_0_ ^ p_g_0_ >>> 7 ^ p_g_0_ >>> 4;
    }

    private static int a(int p_a_0_, int p_a_1_)
    {
        return p_a_0_ & p_a_1_ - 1;
    }

    @Nullable
    public V a(int p_a_1_)
    {
        int i = g(p_a_1_);

        for (rg.a<V> a = this.a[a(i, this.a.length)]; a != null; a = a.c)
        {
            if (a.a == p_a_1_)
            {
                return a.b;
            }
        }

        return (V)null;
    }

    public boolean b(int p_b_1_)
    {
        return this.c(p_b_1_) != null;
    }

    @Nullable
    final rg.a<V> c(int p_c_1_)
    {
        int i = g(p_c_1_);

        for (rg.a<V> a = this.a[a(i, this.a.length)]; a != null; a = a.c)
        {
            if (a.a == p_c_1_)
            {
                return a;
            }
        }

        return null;
    }

    public void a(int p_a_1_, V p_a_2_)
    {
        int i = g(p_a_1_);
        int j = a(i, this.a.length);

        for (rg.a<V> a = this.a[j]; a != null; a = a.c)
        {
            if (a.a == p_a_1_)
            {
                a.b = p_a_2_;
                return;
            }
        }

        this.a(i, p_a_1_, p_a_2_, j);
    }

    private void h(int p_h_1_)
    {
        rg.a<V>[] a = this.a;
        int i = a.length;

        if (i == 1073741824)
        {
            this.c = Integer.MAX_VALUE;
        }
        else
        {
            rg.a<V>[] a = new rg.a[p_h_1_];
            this.a(a);
            this.a = a;
            this.c = (int)((float)p_h_1_ * this.d);
        }
    }

    private void a(rg.a<V>[] p_a_1_)
    {
        rg.a<V>[] a = this.a;
        int i = p_a_1_.length;

        for (int j = 0; j < a.length; ++j)
        {
            rg.a<V> a = a[j];

            if (a != null)
            {
                a[j] = null;

                while (true)
                {
                    rg.a<V> a = a.c;
                    int k = a(a.d, i);
                    a.c = p_a_1_[k];
                    p_a_1_[k] = a;
                    a = a;

                    if (a == null)
                    {
                        break;
                    }
                }
            }
        }
    }

    @Nullable
    public V d(int p_d_1_)
    {
        rg.a<V> a = this.e(p_d_1_);
        return (V)(a == null ? null : a.b);
    }

    @Nullable
    final rg.a<V> e(int p_e_1_)
    {
        int i = g(p_e_1_);
        int j = a(i, this.a.length);
        rg.a<V> a = this.a[j];
        rg.a<V> a;
        rg.a<V> a;

        for (a = a; a != null; a = a)
        {
            a = a.c;

            if (a.a == p_e_1_)
            {
                --this.b;

                if (a == a)
                {
                    this.a[j] = a;
                }
                else
                {
                    a.c = a;
                }

                return a;
            }

            a = a;
        }

        return a;
    }

    public void c()
    {
        rg.a<V>[] a = this.a;

        for (int i = 0; i < a.length; ++i)
        {
            a[i] = null;
        }

        this.b = 0;
    }

    private void a(int p_a_1_, int p_a_2_, V p_a_3_, int p_a_4_)
    {
        rg.a<V> a = this.a[p_a_4_];
        this.a[p_a_4_] = new rg.a(p_a_1_, p_a_2_, p_a_3_, a);

        if (this.b++ >= this.c)
        {
            this.h(2 * this.a.length);
        }
    }

    static class a<V>
    {
        final int a;
        V b;
        rg.a<V> c;
        final int d;

        a(int mcIn, int p_i1010_2_, V p_i1010_3_, rg.a<V> p_i1010_4_)
        {
            this.b = p_i1010_3_;
            this.c = p_i1010_4_;
            this.a = p_i1010_2_;
            this.d = mcIn;
        }

        public final int a()
        {
            return this.a;
        }

        public final V b()
        {
            return this.b;
        }

        public final boolean equals(Object p_equals_1_)
        {
            if (!(p_equals_1_ instanceof rg.a))
            {
                return false;
            }
            else
            {
                rg.a<V> a = (rg.a)p_equals_1_;

                if (this.a == a.a)
                {
                    Object object = this.b();
                    Object object1 = a.b();

                    if (object == object1 || object != null && object.equals(object1))
                    {
                        return true;
                    }
                }

                return false;
            }
        }

        public final int hashCode()
        {
            return rg.g(this.a);
        }

        public final String toString()
        {
            return this.a() + "=" + this.b();
        }
    }
}
