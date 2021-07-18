package net.minecraft.src;

import net.minecraft.util.EnumFacing;

public enum bup
{
    a(new bup.b[]{new bup.b(bup.a.f, bup.a.e, bup.a.a), new bup.b(bup.a.f, bup.a.e, bup.a.d), new bup.b(bup.a.c, bup.a.e, bup.a.d), new bup.b(bup.a.c, bup.a.e, bup.a.a)}),
    b(new bup.b[]{new bup.b(bup.a.f, bup.a.b, bup.a.d), new bup.b(bup.a.f, bup.a.b, bup.a.a), new bup.b(bup.a.c, bup.a.b, bup.a.a), new bup.b(bup.a.c, bup.a.b, bup.a.d)}),
    c(new bup.b[]{new bup.b(bup.a.c, bup.a.b, bup.a.d), new bup.b(bup.a.c, bup.a.e, bup.a.d), new bup.b(bup.a.f, bup.a.e, bup.a.d), new bup.b(bup.a.f, bup.a.b, bup.a.d)}),
    d(new bup.b[]{new bup.b(bup.a.f, bup.a.b, bup.a.a), new bup.b(bup.a.f, bup.a.e, bup.a.a), new bup.b(bup.a.c, bup.a.e, bup.a.a), new bup.b(bup.a.c, bup.a.b, bup.a.a)}),
    e(new bup.b[]{new bup.b(bup.a.f, bup.a.b, bup.a.d), new bup.b(bup.a.f, bup.a.e, bup.a.d), new bup.b(bup.a.f, bup.a.e, bup.a.a), new bup.b(bup.a.f, bup.a.b, bup.a.a)}),
    f(new bup.b[]{new bup.b(bup.a.c, bup.a.b, bup.a.a), new bup.b(bup.a.c, bup.a.e, bup.a.a), new bup.b(bup.a.c, bup.a.e, bup.a.d), new bup.b(bup.a.c, bup.a.b, bup.a.d)});

    private static final bup[] g = new bup[6];
    private final bup.b[] h;

    public static bup a(EnumFacing p_a_0_)
    {
        return g[p_a_0_.getIndex()];
    }

    private bup(bup.b[] p_i398_3_)
    {
        this.h = p_i398_3_;
    }

    public bup.b a(int p_a_1_)
    {
        return this.h[p_a_1_];
    }

    static {
        g[bup.a.e] = a;
        g[bup.a.b] = b;
        g[bup.a.d] = c;
        g[bup.a.a] = d;
        g[bup.a.f] = e;
        g[bup.a.c] = f;
    }

    public static final class a {
        public static final int a = EnumFacing.SOUTH.getIndex();
        public static final int b = EnumFacing.UP.getIndex();
        public static final int c = EnumFacing.EAST.getIndex();
        public static final int d = EnumFacing.NORTH.getIndex();
        public static final int e = EnumFacing.DOWN.getIndex();
        public static final int f = EnumFacing.WEST.getIndex();
    }

    public static class b {
        public final int a;
        public final int b;
        public final int c;

        private b(int p_i641_1_, int p_i641_2_, int p_i641_3_)
        {
            this.a = p_i641_1_;
            this.b = p_i641_2_;
            this.c = p_i641_3_;
        }
    }
}
