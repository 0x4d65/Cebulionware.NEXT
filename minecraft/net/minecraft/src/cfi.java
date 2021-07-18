package net.minecraft.src;

import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import net.minecraft.client.resources.data.AnimationMetadataSectionSerializer;
import net.minecraft.client.resources.data.MetadataSerializer;

public class cfi implements MetadataSerializer
{
    private final List<AnimationMetadataSectionSerializer> a;
    private final int b;
    private final int c;
    private final int d;
    private final boolean e;

    public cfi(List<AnimationMetadataSectionSerializer> p_i306_1_, int p_i306_2_, int p_i306_3_, int p_i306_4_, boolean p_i306_5_)
    {
        this.a = p_i306_1_;
        this.b = p_i306_2_;
        this.c = p_i306_3_;
        this.d = p_i306_4_;
        this.e = p_i306_5_;
    }

    public int a()
    {
        return this.c;
    }

    public int b()
    {
        return this.b;
    }

    public int c()
    {
        return this.a.size();
    }

    public int d()
    {
        return this.d;
    }

    public boolean e()
    {
        return this.e;
    }

    private AnimationMetadataSectionSerializer d(int p_d_1_)
    {
        return this.a.get(p_d_1_);
    }

    public int a(int p_a_1_)
    {
        AnimationMetadataSectionSerializer animationmetadatasectionserializer = this.d(p_a_1_);
        return animationmetadatasectionserializer.a() ? this.d : animationmetadatasectionserializer.b();
    }

    public boolean b(int p_b_1_)
    {
        return !((AnimationMetadataSectionSerializer)this.a.get(p_b_1_)).a();
    }

    public int c(int p_c_1_)
    {
        return ((AnimationMetadataSectionSerializer)this.a.get(p_c_1_)).c();
    }

    public Set<Integer> f()
    {
        Set<Integer> set = Sets.<Integer>newHashSet();

        for (AnimationMetadataSectionSerializer animationmetadatasectionserializer : this.a)
        {
            set.add(Integer.valueOf(animationmetadatasectionserializer.c()));
        }

        return set;
    }
}
