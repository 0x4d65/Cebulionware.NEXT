package net.minecraft.src;

import net.minecraft.block.BlockBone;
import net.minecraft.dispenser.BehaviorProjectileDispense;

public class bbc
{
    private final int a;
    private awt b;
    private int c;
    private int d;

    public bbc(int p_i1901_1_, BlockBone p_i1901_2_)
    {
        this(3, p_i1901_1_, p_i1901_2_);
    }

    public bbc(int p_i1902_1_, int p_i1902_2_, BlockBone p_i1902_3_)
    {
        this.c = 1;
        this.a = p_i1902_1_;
        this.c = p_i1902_2_;
        this.b = p_i1902_3_.t();
    }

    public bbc(int p_i1903_1_, int p_i1903_2_, BlockBone p_i1903_3_, int p_i1903_4_)
    {
        this(p_i1903_1_, p_i1903_2_, p_i1903_3_);
        this.b = p_i1903_3_.a(p_i1903_4_);
    }

    public int b()
    {
        return this.c;
    }

    public awt c()
    {
        return this.b;
    }

    private BlockBone e()
    {
        return this.b.u();
    }

    private int f()
    {
        return this.b.u().e(this.b);
    }

    public int d()
    {
        return this.d;
    }

    public void b(int p_b_1_)
    {
        this.d = p_b_1_;
    }

    public String toString()
    {
        String s;

        if (this.a >= 3)
        {
            BehaviorProjectileDispense behaviorprojectiledispense = BlockBone.h.getNameForObject(this.e());
            s = behaviorprojectiledispense == null ? "null" : behaviorprojectiledispense.toString();

            if (this.c > 1)
            {
                s = this.c + "*" + s;
            }
        }
        else
        {
            s = Integer.toString(BlockBone.a(this.e()));

            if (this.c > 1)
            {
                s = this.c + "x" + s;
            }
        }

        int i = this.f();

        if (i > 0)
        {
            s = s + ":" + i;
        }

        return s;
    }
}
