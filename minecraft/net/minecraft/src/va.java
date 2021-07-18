package net.minecraft.src;

import com.google.common.collect.ComparisonChain;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class va implements Comparable<va>
{
    private static final Logger a = LogManager.getLogger();
    private final MobEffects b;
    private int c;
    private int d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;

    public va(MobEffects buttonID)
    {
        this(buttonID, 0, 0);
    }

    public va(MobEffects p_i1042_1_, int p_i1042_2_)
    {
        this(p_i1042_1_, p_i1042_2_, 0);
    }

    public va(MobEffects screen, int gameSettingsObj, int manager)
    {
        this(screen, gameSettingsObj, manager, false, true);
    }

    public va(MobEffects textureManagerInstance, int skinCacheDirectory, int sessionService, boolean p_i1044_4_, boolean p_i1044_5_)
    {
        this.b = textureManagerInstance;
        this.c = skinCacheDirectory;
        this.d = sessionService;
        this.f = p_i1044_4_;
        this.h = p_i1044_5_;
    }

    public va(va p_i1045_1_)
    {
        this.b = p_i1045_1_.b;
        this.c = p_i1045_1_.c;
        this.d = p_i1045_1_.d;
        this.f = p_i1045_1_.f;
        this.h = p_i1045_1_.h;
    }

    public void a(va p_a_1_)
    {
        if (this.b != p_a_1_.b)
        {
            a.warn("This method should only be called for matching effects!");
        }

        if (p_a_1_.d > this.d)
        {
            this.d = p_a_1_.d;
            this.c = p_a_1_.c;
        }
        else if (p_a_1_.d == this.d && this.c < p_a_1_.c)
        {
            this.c = p_a_1_.c;
        }
        else if (!p_a_1_.f && this.f)
        {
            this.f = p_a_1_.f;
        }

        this.h = p_a_1_.h;
    }

    public MobEffects a()
    {
        return this.b;
    }

    public int b()
    {
        return this.c;
    }

    public int c()
    {
        return this.d;
    }

    public boolean d()
    {
        return this.f;
    }

    public boolean e()
    {
        return this.h;
    }

    public boolean a(EnumCreatureType p_a_1_)
    {
        if (this.c > 0)
        {
            if (this.b.a(this.c, this.d))
            {
                this.b(p_a_1_);
            }

            this.h();
        }

        return this.c > 0;
    }

    private int h()
    {
        return --this.c;
    }

    public void b(EnumCreatureType p_b_1_)
    {
        if (this.c > 0)
        {
            this.b.a(p_b_1_, this.d);
        }
    }

    public String f()
    {
        return this.b.a();
    }

    public String toString()
    {
        String s;

        if (this.d > 0)
        {
            s = this.f() + " x " + (this.d + 1) + ", Duration: " + this.c;
        }
        else
        {
            s = this.f() + ", Duration: " + this.c;
        }

        if (this.e)
        {
            s = s + ", Splash: true";
        }

        if (!this.h)
        {
            s = s + ", Particles: false";
        }

        return s;
    }

    public boolean equals(Object p_equals_1_)
    {
        if (this == p_equals_1_)
        {
            return true;
        }
        else if (!(p_equals_1_ instanceof va))
        {
            return false;
        }
        else
        {
            va va = (va)p_equals_1_;
            return this.c == va.c && this.d == va.d && this.e == va.e && this.f == va.f && this.b.equals(va.b);
        }
    }

    public int hashCode()
    {
        int i = this.b.hashCode();
        i = 31 * i + this.c;
        i = 31 * i + this.d;
        i = 31 * i + (this.e ? 1 : 0);
        i = 31 * i + (this.f ? 1 : 0);
        return i;
    }

    public NBTTagCompound a(NBTTagCompound p_a_1_)
    {
        p_a_1_.setByte("Id", (byte)MobEffects.a(this.a()));
        p_a_1_.setByte("Amplifier", (byte)this.c());
        p_a_1_.setInteger("Duration", this.b());
        p_a_1_.setBoolean("Ambient", this.d());
        p_a_1_.setBoolean("ShowParticles", this.e());
        return p_a_1_;
    }

    public static va b(NBTTagCompound p_b_0_)
    {
        int i = p_b_0_.getByte("Id");
        MobEffects mobeffects = MobEffects.a(i);

        if (mobeffects == null)
        {
            return null;
        }
        else
        {
            int j = p_b_0_.getByte("Amplifier");
            int k = p_b_0_.getInteger("Duration");
            boolean flag = p_b_0_.getBoolean("Ambient");
            boolean flag1 = true;

            if (p_b_0_.hasKey("ShowParticles", 1))
            {
                flag1 = p_b_0_.getBoolean("ShowParticles");
            }

            return new va(mobeffects, k, j < 0 ? 0 : j, flag, flag1);
        }
    }

    public void b(boolean p_b_1_)
    {
        this.g = p_b_1_;
    }

    public boolean g()
    {
        return this.g;
    }

    public int b(va p_b_1_)
    {
        int i = 32147;
        return (this.b() <= 32147 || p_b_1_.b() <= 32147) && (!this.d() || !p_b_1_.d()) ? ComparisonChain.start().compare(Boolean.valueOf(this.d()), Boolean.valueOf(p_b_1_.d())).compare(this.b(), p_b_1_.b()).compare(this.a().g(), p_b_1_.a().g()).result() : ComparisonChain.start().compare(Boolean.valueOf(this.d()), Boolean.valueOf(p_b_1_.d())).compare(this.a().g(), p_b_1_.a().g()).result();
    }
}
