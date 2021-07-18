package net.minecraft.src;

import com.mojang.authlib.properties.PropertyMap;
import java.io.File;
import java.net.Proxy;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.FileResourcePack;

public class boz
{
    public final boz.e a;
    public final boz.a b;
    public final boz.b c;
    public final boz.c d;
    public final boz.d e;

    public boz(boz.e p_i670_1_, boz.a p_i670_2_, boz.b p_i670_3_, boz.c p_i670_4_, boz.d p_i670_5_)
    {
        this.a = p_i670_1_;
        this.b = p_i670_2_;
        this.c = p_i670_3_;
        this.d = p_i670_4_;
        this.e = p_i670_5_;
    }

    public static class a
    {
        public final int a;
        public final int b;
        public final boolean c;
        public final boolean d;

        public a(int p_i669_1_, int p_i669_2_, boolean p_i669_3_, boolean p_i669_4_)
        {
            this.a = p_i669_1_;
            this.b = p_i669_2_;
            this.c = p_i669_3_;
            this.d = p_i669_4_;
        }
    }

    public static class b
    {
        public final File a;
        public final File b;
        public final File c;
        public final String d;

        public b(File p_i671_1_, File p_i671_2_, File p_i671_3_, @Nullable String p_i671_4_)
        {
            this.a = p_i671_1_;
            this.b = p_i671_2_;
            this.c = p_i671_3_;
            this.d = p_i671_4_;
        }

        public DefaultResourcePack a()
        {
            return (DefaultResourcePack)(this.d == null ? new FileResourcePack(this.c) : new DefaultResourcePack(this.c, this.d));
        }
    }

    public static class c
    {
        public final boolean a;
        public final String b;
        public final String c;

        public c(boolean p_i673_1_, String p_i673_2_, String p_i673_3_)
        {
            this.a = p_i673_1_;
            this.b = p_i673_2_;
            this.c = p_i673_3_;
        }
    }

    public static class d
    {
        public final String a;
        public final int b;

        public d(String p_i668_1_, int p_i668_2_)
        {
            this.a = p_i668_1_;
            this.b = p_i668_2_;
        }
    }

    public static class e
    {
        public final BlockColors a;
        public final PropertyMap b;
        public final PropertyMap c;
        public final Proxy d;

        public e(BlockColors p_i672_1_, PropertyMap p_i672_2_, PropertyMap p_i672_3_, Proxy p_i672_4_)
        {
            this.a = p_i672_1_;
            this.b = p_i672_2_;
            this.c = p_i672_3_;
            this.d = p_i672_4_;
        }
    }
}
