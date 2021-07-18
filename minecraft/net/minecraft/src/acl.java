package net.minecraft.src;

import javax.annotation.Nullable;
import net.minecraft.block.material.MaterialPortal;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Items;
import net.minecraft.item.ItemLead;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.IDataWalker;
import net.minecraft.util.datafix.walkers.EntityTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IWorldEventListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class acl extends EntityList
{
    private static final Logger b = LogManager.getLogger();
    private static final DataSerializer<Items> c = nb.a(acl.class, EntityDataManager.empty);
    private int d;
    private int e;
    private int f;
    private String g;
    private String h;
    public float field_191307_a;

    public acl(IWorldEventListener p_i1264_1_, double p_i1264_2_, double p_i1264_4_, double p_i1264_6_)
    {
        super(p_i1264_1_);
        this.f = 5;
        this.field_191307_a = (float)(Math.random() * Math.PI * 2.0D);
        this.a(0.25F, 0.25F);
        this.b(p_i1264_2_, p_i1264_4_, p_i1264_6_);
        this.v = (float)(Math.random() * 360.0D);
        this.s = (double)((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
        this.t = 0.20000000298023224D;
        this.u = (double)((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
    }

    public acl(IWorldEventListener p_i1265_1_, double p_i1265_2_, double p_i1265_4_, double p_i1265_6_, Items p_i1265_8_)
    {
        this(p_i1265_1_, p_i1265_2_, p_i1265_4_, p_i1265_6_);
        this.a(p_i1265_8_);
    }

    protected boolean ak()
    {
        return false;
    }

    public acl(IWorldEventListener p_i1266_1_)
    {
        super(p_i1266_1_);
        this.f = 5;
        this.field_191307_a = (float)(Math.random() * Math.PI * 2.0D);
        this.a(0.25F, 0.25F);
        this.a(Items.field_190931_a);
    }

    protected void i()
    {
        this.V().a(c, Items.field_190931_a);
    }

    public void B_()
    {
        if (this.k().b())
        {
            this.X();
        }
        else
        {
            super.B_();

            if (this.e > 0 && this.e != 32767)
            {
                --this.e;
            }

            this.m = this.p;
            this.n = this.q;
            this.o = this.r;
            double d0 = this.s;
            double d1 = this.t;
            double d2 = this.u;

            if (!this.aj())
            {
                this.t -= 0.03999999910593033D;
            }

            if (this.l.G)
            {
                this.Q = false;
            }
            else
            {
                this.Q = this.i(this.p, (this.bw().ONE + this.bw().UNIT_Y) / 2.0D, this.r);
            }

            this.a(EntityCreature.homePosition, this.s, this.t, this.u);
            boolean flag = (int)this.m != (int)this.p || (int)this.n != (int)this.q || (int)this.o != (int)this.r;

            if (flag || this.T % 25 == 0)
            {
                if (this.l.o(new BlockPos(this)).a() == MaterialPortal.i)
                {
                    this.t = 0.20000000298023224D;
                    this.s = (double)((this.S.nextFloat() - this.S.nextFloat()) * 0.2F);
                    this.u = (double)((this.S.nextFloat() - this.S.nextFloat()) * 0.2F);
                    this.a(qf.bR, 0.4F, 2.0F + this.S.nextFloat() * 0.4F);
                }

                if (!this.l.G)
                {
                    this.x();
                }
            }

            float f = 0.98F;

            if (this.z)
            {
                f = this.l.o(new BlockPos(IProgressUpdate.c(this.p), IProgressUpdate.c(this.bw().ONE) - 1, IProgressUpdate.c(this.r))).u().z * 0.98F;
            }

            this.s *= (double)f;
            this.t *= 0.9800000190734863D;
            this.u *= (double)f;

            if (this.z)
            {
                this.t *= -0.5D;
            }

            if (this.d != -32768)
            {
                ++this.d;
            }

            this.aq();

            if (!this.l.G)
            {
                double d3 = this.s - d0;
                double d4 = this.t - d1;
                double d5 = this.u - d2;
                double d6 = d3 * d3 + d4 * d4 + d5 * d5;

                if (d6 > 0.01D)
                {
                    this.ai = true;
                }
            }

            if (!this.l.G && this.d >= 6000)
            {
                this.X();
            }
        }
    }

    private void x()
    {
        for (acl acl : this.l.a(acl.class, this.bw().c(0.5D, 0.0D, 0.5D)))
        {
            this.a(acl);
        }
    }

    private boolean a(acl p_a_1_)
    {
        if (p_a_1_ == this)
        {
            return false;
        }
        else if (p_a_1_.aC() && this.aC())
        {
            Items items = this.k();
            Items items1 = p_a_1_.k();

            if (this.e != 32767 && p_a_1_.e != 32767)
            {
                if (this.d != -32768 && p_a_1_.d != -32768)
                {
                    if (items1.c() != items.c())
                    {
                        return false;
                    }
                    else if (items1.o() ^ items.o())
                    {
                        return false;
                    }
                    else if (items1.o() && !items1.p().equals(items.p()))
                    {
                        return false;
                    }
                    else if (items1.c() == null)
                    {
                        return false;
                    }
                    else if (items1.c().k() && items1.j() != items.j())
                    {
                        return false;
                    }
                    else if (items1.E() < items.E())
                    {
                        return p_a_1_.a(this);
                    }
                    else if (items1.E() + items.E() > items1.d())
                    {
                        return false;
                    }
                    else
                    {
                        items1.f(items.E());
                        p_a_1_.e = Math.max(p_a_1_.e, this.e);
                        p_a_1_.d = Math.min(p_a_1_.d, this.d);
                        p_a_1_.a(items1);
                        this.X();
                        return true;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public void j()
    {
        this.d = 4800;
    }

    public boolean aq()
    {
        if (this.l.a(this.bw(), MaterialPortal.h, this))
        {
            if (!this.U && !this.W)
            {
                this.ar();
            }

            this.U = true;
        }
        else
        {
            this.U = false;
        }

        return this.U;
    }

    protected void j(int p_j_1_)
    {
        this.a(EntityDamageSourceIndirect.a, (float)p_j_1_);
    }

    public boolean a(EntityDamageSourceIndirect p_a_1_, float p_a_2_)
    {
        if (this.b(p_a_1_))
        {
            return false;
        }
        else if (!this.k().b() && this.k().c() == ItemLead.ck && p_a_1_.c())
        {
            return false;
        }
        else
        {
            this.ax();
            this.f = (int)((float)this.f - p_a_2_);

            if (this.f <= 0)
            {
                this.X();
            }

            return false;
        }
    }

    public static void a(IDataWalker p_a_0_)
    {
        p_a_0_.a(DataFixer.e, new EntityTag(acl.class, new String[] {"Item"}));
    }

    public void b(NBTTagCompound p_b_1_)
    {
        p_b_1_.setShort("Health", (short)this.f);
        p_b_1_.setShort("Age", (short)this.d);
        p_b_1_.setShort("PickupDelay", (short)this.e);

        if (this.n() != null)
        {
            p_b_1_.setString("Thrower", this.g);
        }

        if (this.l() != null)
        {
            p_b_1_.setString("Owner", this.h);
        }

        if (!this.k().b())
        {
            p_b_1_.setTag("Item", this.k().a(new NBTTagCompound()));
        }
    }

    public void a(NBTTagCompound p_a_1_)
    {
        this.f = p_a_1_.getShort("Health");
        this.d = p_a_1_.getShort("Age");

        if (p_a_1_.hasKey("PickupDelay"))
        {
            this.e = p_a_1_.getShort("PickupDelay");
        }

        if (p_a_1_.hasKey("Owner"))
        {
            this.h = p_a_1_.getString("Owner");
        }

        if (p_a_1_.hasKey("Thrower"))
        {
            this.g = p_a_1_.getString("Thrower");
        }

        NBTTagCompound nbttagcompound = p_a_1_.getCompoundTag("Item");
        this.a(new Items(nbttagcompound));

        if (this.k().b())
        {
            this.X();
        }
    }

    public void d(RecipeItemHelper p_d_1_)
    {
        if (!this.l.G)
        {
            Items items = this.k();
            ItemStack itemstack = items.c();
            int i = items.E();

            if (this.e == 0 && (this.h == null || 6000 - this.d <= 200 || this.h.equals(p_d_1_.getName())) && p_d_1_.bv.e(items))
            {
                p_d_1_.a(this, i);

                if (items.b())
                {
                    this.X();
                    items.e(i);
                }

                p_d_1_.a(qs.d(itemstack), i);
            }
        }
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName()
    {
        return this.n_() ? this.bq() : I18n.translateToLocal("item." + this.k().a());
    }

    public boolean bd()
    {
        return false;
    }

    @Nullable
    public EntityList b(int p_b_1_)
    {
        EntityList entitylist = super.b(p_b_1_);

        if (!this.l.G && entitylist instanceof acl)
        {
            ((acl)entitylist).x();
        }

        return entitylist;
    }

    public Items k()
    {
        return (Items)this.V().a(c);
    }

    public void a(Items p_a_1_)
    {
        this.V().b(c, p_a_1_);
        this.V().b(c);
    }

    public String l()
    {
        return this.h;
    }

    public void d(String p_d_1_)
    {
        this.h = p_d_1_;
    }

    public String n()
    {
        return this.g;
    }

    public void e(String p_e_1_)
    {
        this.g = p_e_1_;
    }

    public int p()
    {
        return this.d;
    }

    public void q()
    {
        this.e = 10;
    }

    public void r()
    {
        this.e = 0;
    }

    public void s()
    {
        this.e = 32767;
    }

    public void a(int p_a_1_)
    {
        this.e = p_a_1_;
    }

    public boolean t()
    {
        return this.e > 0;
    }

    public void v()
    {
        this.d = -6000;
    }

    public void w()
    {
        this.s();
        this.d = 5999;
    }
}
