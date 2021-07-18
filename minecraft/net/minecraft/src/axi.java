package net.minecraft.src;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Set;
import net.minecraft.block.properties.PropertyDirection;

public class axi extends PropertyDirection<Integer>
{
    private final ImmutableSet<Integer> a;

    protected axi(String p_i1839_1_, int p_i1839_2_, int p_i1839_3_)
    {
        super(p_i1839_1_, Integer.class);

        if (p_i1839_2_ < 0)
        {
            throw new IllegalArgumentException("Min value of " + p_i1839_1_ + " must be 0 or greater");
        }
        else if (p_i1839_3_ <= p_i1839_2_)
        {
            throw new IllegalArgumentException("Max value of " + p_i1839_1_ + " must be greater than min (" + p_i1839_2_ + ")");
        }
        else
        {
            Set<Integer> set = Sets.<Integer>newHashSet();

            for (int i = p_i1839_2_; i <= p_i1839_3_; ++i)
            {
                set.add(Integer.valueOf(i));
            }

            this.a = ImmutableSet.copyOf(set);
        }
    }

    public Collection<Integer> c()
    {
        return this.a;
    }

    public boolean equals(Object p_equals_1_)
    {
        if (this == p_equals_1_)
        {
            return true;
        }
        else if (p_equals_1_ instanceof axi && super.equals(p_equals_1_))
        {
            axi axi = (axi)p_equals_1_;
            return this.a.equals(axi.a);
        }
        else
        {
            return false;
        }
    }

    public int hashCode()
    {
        return 31 * super.hashCode() + this.a.hashCode();
    }

    public static axi a(String p_a_0_, int p_a_1_, int p_a_2_)
    {
        return new axi(p_a_0_, p_a_1_, p_a_2_);
    }

    public Optional<Integer> b(String p_b_1_)
    {
        try
        {
            Integer integer = Integer.valueOf(p_b_1_);
            return this.a.contains(integer) ? Optional.of(integer) : Optional.absent();
        }
        catch (NumberFormatException var3)
        {
            return Optional.<Integer>absent();
        }
    }

    public String a(Integer p_a_1_)
    {
        return p_a_1_.toString();
    }
}
