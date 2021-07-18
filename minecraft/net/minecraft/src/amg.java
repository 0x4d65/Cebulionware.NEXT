package net.minecraft.src;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class amg
{
    private Items a;
    private Items b;
    private Items c;
    private int d;
    private int e;
    private boolean f;

    public amg(NBTTagCompound livingIn)
    {
        this.a = Items.field_190931_a;
        this.b = Items.field_190931_a;
        this.c = Items.field_190931_a;
        this.a(livingIn);
    }

    public amg(Items entityIn, Items p_i1612_2_, Items p_i1612_3_)
    {
        this(entityIn, p_i1612_2_, p_i1612_3_, 0, 7);
    }

    public amg(Items entitylivingIn, Items p_i1613_2_, Items p_i1613_3_, int p_i1613_4_, int p_i1613_5_)
    {
        this.a = Items.field_190931_a;
        this.b = Items.field_190931_a;
        this.c = Items.field_190931_a;
        this.a = entitylivingIn;
        this.b = p_i1613_2_;
        this.c = p_i1613_3_;
        this.d = p_i1613_4_;
        this.e = p_i1613_5_;
        this.f = true;
    }

    public amg(Items entitylivingIn, Items p_i1614_2_)
    {
        this(entitylivingIn, Items.field_190931_a, p_i1614_2_);
    }

    public amg(Items p_i1615_1_, ItemStack p_i1615_2_)
    {
        this(p_i1615_1_, new Items(p_i1615_2_));
    }

    public Items a()
    {
        return this.a;
    }

    public Items b()
    {
        return this.b;
    }

    public boolean c()
    {
        return !this.b.b();
    }

    public Items d()
    {
        return this.c;
    }

    public int e()
    {
        return this.d;
    }

    public int f()
    {
        return this.e;
    }

    public void g()
    {
        ++this.d;
    }

    public void a(int p_a_1_)
    {
        this.e += p_a_1_;
    }

    public boolean h()
    {
        return this.d >= this.e;
    }

    public void i()
    {
        this.d = this.e;
    }

    public boolean j()
    {
        return this.f;
    }

    public void a(NBTTagCompound p_a_1_)
    {
        NBTTagCompound nbttagcompound = p_a_1_.getCompoundTag("buy");
        this.a = new Items(nbttagcompound);
        NBTTagCompound nbttagcompound1 = p_a_1_.getCompoundTag("sell");
        this.c = new Items(nbttagcompound1);

        if (p_a_1_.hasKey("buyB", 10))
        {
            this.b = new Items(p_a_1_.getCompoundTag("buyB"));
        }

        if (p_a_1_.hasKey("uses", 99))
        {
            this.d = p_a_1_.getInteger("uses");
        }

        if (p_a_1_.hasKey("maxUses", 99))
        {
            this.e = p_a_1_.getInteger("maxUses");
        }
        else
        {
            this.e = 7;
        }

        if (p_a_1_.hasKey("rewardExp", 1))
        {
            this.f = p_a_1_.getBoolean("rewardExp");
        }
        else
        {
            this.f = true;
        }
    }

    public NBTTagCompound k()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setTag("buy", this.a.a(new NBTTagCompound()));
        nbttagcompound.setTag("sell", this.c.a(new NBTTagCompound()));

        if (!this.b.b())
        {
            nbttagcompound.setTag("buyB", this.b.a(new NBTTagCompound()));
        }

        nbttagcompound.setInteger("uses", this.d);
        nbttagcompound.setInteger("maxUses", this.e);
        nbttagcompound.setBoolean("rewardExp", this.f);
        return nbttagcompound;
    }
}
