package net.minecraft.src;

import com.google.common.collect.Lists;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class nj extends IOException
{
    private final List<nj.a> a = Lists.<nj.a>newArrayList();
    private final String b;

    public nj(String p_i922_1_)
    {
        this.a.add(new nj.a());
        this.b = p_i922_1_;
    }

    public nj(String p_i923_1_, Throwable p_i923_2_)
    {
        super(p_i923_2_);
        this.a.add(new nj.a());
        this.b = p_i923_1_;
    }

    public void a(String p_a_1_)
    {
        ((nj.a)this.a.get(0)).a(p_a_1_);
    }

    public void b(String p_b_1_)
    {
        (this.a.get(0)).a = p_b_1_;
        this.a.add(0, new nj.a());
    }

    public String getMessage()
    {
        return "Invalid " + this.a.get(this.a.size() - 1) + ": " + this.b;
    }

    public static nj a(Exception p_a_0_)
    {
        if (p_a_0_ instanceof nj)
        {
            return (nj)p_a_0_;
        }
        else
        {
            String s = p_a_0_.getMessage();

            if (p_a_0_ instanceof FileNotFoundException)
            {
                s = "File not found";
            }

            return new nj(s, p_a_0_);
        }
    }

    public static class a
    {
        private String a;
        private final List<String> b;

        private a()
        {
            this.b = Lists.<String>newArrayList();
        }

        private void a(String p_a_1_)
        {
            this.b.add(0, p_a_1_);
        }

        public String b()
        {
            return StringUtils.join((Iterable)this.b, "->");
        }

        public String toString()
        {
            if (this.a != null)
            {
                return this.b.isEmpty() ? this.a : this.a + " " + this.b();
            }
            else
            {
                return this.b.isEmpty() ? "(Unknown file)" : "(Unknown file) " + this.b();
            }
        }
    }
}
