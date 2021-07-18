package net.minecraft.src;

import net.minecraft.util.IProgressUpdate;

public class beu
{
    private final beu.a a;
    private byte b;
    private byte c;
    private byte d;

    public beu(beu.a p_i2075_1_, byte p_i2075_2_, byte p_i2075_3_, byte p_i2075_4_)
    {
        this.a = p_i2075_1_;
        this.b = p_i2075_2_;
        this.c = p_i2075_3_;
        this.d = p_i2075_4_;
    }

    public byte a()
    {
        return this.a.a();
    }

    public beu.a b()
    {
        return this.a;
    }

    public byte c()
    {
        return this.b;
    }

    public byte d()
    {
        return this.c;
    }

    public byte e()
    {
        return this.d;
    }

    public boolean f()
    {
        return this.a.b();
    }

    public boolean equals(Object p_equals_1_)
    {
        if (this == p_equals_1_)
        {
            return true;
        }
        else if (!(p_equals_1_ instanceof beu))
        {
            return false;
        }
        else
        {
            beu beu = (beu)p_equals_1_;

            if (this.a != beu.a)
            {
                return false;
            }
            else if (this.d != beu.d)
            {
                return false;
            }
            else if (this.b != beu.b)
            {
                return false;
            }
            else
            {
                return this.c == beu.c;
            }
        }
    }

    public int hashCode()
    {
        int i = this.a.a();
        i = 31 * i + this.b;
        i = 31 * i + this.c;
        i = 31 * i + this.d;
        return i;
    }

    public static enum a
    {
        a(false),
        b(true),
        c(false),
        d(false),
        e(true),
        f(true),
        g(false),
        h(false),
        i(true, 5393476),
        j(true, 3830373);

        private final byte k;
        private final boolean l;
        private final int m;

        private a(boolean p_i2073_3_)
        {
            this(p_i2073_3_, -1);
        }

        private a(boolean p_i2074_3_, int p_i2074_4_)
        {
            this.k = (byte)this.ordinal();
            this.l = p_i2074_3_;
            this.m = p_i2074_4_;
        }

        public byte a()
        {
            return this.k;
        }

        public boolean b()
        {
            return this.l;
        }

        public boolean c()
        {
            return this.m >= 0;
        }

        public int d()
        {
            return this.m;
        }

        public static beu.a a(byte p_a_0_)
        {
            return values()[IProgressUpdate.a(p_a_0_, 0, values().length - 1)];
        }
    }
}
