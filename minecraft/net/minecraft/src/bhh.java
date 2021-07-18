package net.minecraft.src;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.scoreboard.ScoreCriteria;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.text.TextFormatting;

public class bhh extends ScoreCriteria
{
    private final Team a;
    private final String b;
    private final Set<String> c = Sets.<String>newHashSet();
    private String d;
    private String e = "";
    private String f = "";
    private boolean g = true;
    private boolean h = true;
    private ScoreCriteria.b i = ScoreCriteria.b.a;
    private ScoreCriteria.b j = ScoreCriteria.b.a;
    private TextFormatting k = TextFormatting.RESET;
    private ScoreCriteria.a l = ScoreCriteria.a.a;

    public bhh(Team p_i2147_1_, String p_i2147_2_)
    {
        this.a = p_i2147_1_;
        this.b = p_i2147_2_;
        this.d = p_i2147_2_;
    }

    public String b()
    {
        return this.b;
    }

    public String c()
    {
        return this.d;
    }

    public void a(String p_a_1_)
    {
        if (p_a_1_ == null)
        {
            throw new IllegalArgumentException("Name cannot be null");
        }
        else
        {
            this.d = p_a_1_;
            this.a.b(this);
        }
    }

    public Collection<String> d()
    {
        return this.c;
    }

    public String e()
    {
        return this.e;
    }

    public void b(String p_b_1_)
    {
        if (p_b_1_ == null)
        {
            throw new IllegalArgumentException("Prefix cannot be null");
        }
        else
        {
            this.e = p_b_1_;
            this.a.b(this);
        }
    }

    public String f()
    {
        return this.f;
    }

    public void c(String p_c_1_)
    {
        this.f = p_c_1_;
        this.a.b(this);
    }

    public String d(String p_d_1_)
    {
        return this.e() + p_d_1_ + this.f();
    }

    public static String a(@Nullable ScoreCriteria p_a_0_, String p_a_1_)
    {
        return p_a_0_ == null ? p_a_1_ : p_a_0_.d(p_a_1_);
    }

    public boolean g()
    {
        return this.g;
    }

    public void a(boolean p_a_1_)
    {
        this.g = p_a_1_;
        this.a.b(this);
    }

    public boolean h()
    {
        return this.h;
    }

    public void b(boolean p_b_1_)
    {
        this.h = p_b_1_;
        this.a.b(this);
    }

    public ScoreCriteria.b i()
    {
        return this.i;
    }

    public ScoreCriteria.b j()
    {
        return this.j;
    }

    public void a(ScoreCriteria.b p_a_1_)
    {
        this.i = p_a_1_;
        this.a.b(this);
    }

    public void b(ScoreCriteria.b p_b_1_)
    {
        this.j = p_b_1_;
        this.a.b(this);
    }

    public ScoreCriteria.a k()
    {
        return this.l;
    }

    public void a(ScoreCriteria.a p_a_1_)
    {
        this.l = p_a_1_;
        this.a.b(this);
    }

    public int l()
    {
        int i = 0;

        if (this.g())
        {
            i |= 1;
        }

        if (this.h())
        {
            i |= 2;
        }

        return i;
    }

    public void a(int p_a_1_)
    {
        this.a((p_a_1_ & 1) > 0);
        this.b((p_a_1_ & 2) > 0);
    }

    public void a(TextFormatting p_a_1_)
    {
        this.k = p_a_1_;
    }

    public TextFormatting m()
    {
        return this.k;
    }
}
