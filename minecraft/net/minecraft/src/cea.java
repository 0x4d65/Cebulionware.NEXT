package net.minecraft.src;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.resources.AbstractResourcePack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class cea
{
    private static final Logger a = LogManager.getLogger();
    private final List<AbstractResourcePack> b;
    private final List<Integer> c;
    private int d;
    private int e;
    private final List<Integer> f;
    private int g;

    public cea(cea p_i637_1_)
    {
        this();

        for (int i = 0; i < p_i637_1_.i(); ++i)
        {
            this.a(p_i637_1_.c(i));
        }

        this.d = p_i637_1_.g();
    }

    public cea()
    {
        this.b = Lists.<AbstractResourcePack>newArrayList();
        this.c = Lists.<Integer>newArrayList();
        this.e = -1;
        this.f = Lists.<Integer>newArrayList();
        this.g = -1;
    }

    public void a()
    {
        this.b.clear();
        this.c.clear();
        this.e = -1;
        this.f.clear();
        this.g = -1;
        this.d = 0;
    }

    public cea a(AbstractResourcePack p_a_1_)
    {
        if (p_a_1_.f() && this.j())
        {
            a.warn("VertexFormat error: Trying to add a position VertexFormatElement when one already exists, ignoring.");
            return this;
        }
        else
        {
            this.b.add(p_a_1_);
            this.c.add(Integer.valueOf(this.d));

            switch (p_a_1_.b())
            {
                case b:
                    this.g = this.d;
                    break;

                case c:
                    this.e = this.d;
                    break;

                case d:
                    this.f.add(p_a_1_.d(), Integer.valueOf(this.d));
            }

            this.d += p_a_1_.e();
            return this;
        }
    }

    public boolean b()
    {
        return this.g >= 0;
    }

    public int c()
    {
        return this.g;
    }

    public boolean d()
    {
        return this.e >= 0;
    }

    public int e()
    {
        return this.e;
    }

    public boolean a(int p_a_1_)
    {
        return this.f.size() - 1 >= p_a_1_;
    }

    public int b(int p_b_1_)
    {
        return ((Integer)this.f.get(p_b_1_)).intValue();
    }

    public String toString()
    {
        String s = "format: " + this.b.size() + " elements: ";

        for (int i = 0; i < this.b.size(); ++i)
        {
            s = s + ((AbstractResourcePack)this.b.get(i)).toString();

            if (i != this.b.size() - 1)
            {
                s = s + " ";
            }
        }

        return s;
    }

    private boolean j()
    {
        int i = 0;

        for (int j = this.b.size(); i < j; ++i)
        {
            AbstractResourcePack abstractresourcepack = this.b.get(i);

            if (abstractresourcepack.f())
            {
                return true;
            }
        }

        return false;
    }

    public int f()
    {
        return this.g() / 4;
    }

    public int g()
    {
        return this.d;
    }

    public List<AbstractResourcePack> h()
    {
        return this.b;
    }

    public int i()
    {
        return this.b.size();
    }

    public AbstractResourcePack c(int p_c_1_)
    {
        return this.b.get(p_c_1_);
    }

    public int d(int p_d_1_)
    {
        return ((Integer)this.c.get(p_d_1_)).intValue();
    }

    public boolean equals(Object p_equals_1_)
    {
        if (this == p_equals_1_)
        {
            return true;
        }
        else if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass())
        {
            cea cea = (cea)p_equals_1_;

            if (this.d != cea.d)
            {
                return false;
            }
            else if (!this.b.equals(cea.b))
            {
                return false;
            }
            else
            {
                return this.c.equals(cea.c);
            }
        }
        else
        {
            return false;
        }
    }

    public int hashCode()
    {
        int i = this.b.hashCode();
        i = 31 * i + this.c.hashCode();
        i = 31 * i + this.d;
        return i;
    }
}
