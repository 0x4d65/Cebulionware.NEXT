package net.minecraft.src;

import net.minecraft.block.BlockBed;
import net.minecraft.block.IGrowable;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.item.EntityMinecartCommandBlock;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.IDataWalker;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.IWorldEventListener;

public class afm extends EntityMinecartCommandBlock
{
    private int a = -1;

    public afm(IWorldEventListener p_i1443_1_)
    {
        super(p_i1443_1_);
    }

    public afm(IWorldEventListener p_i1444_1_, double p_i1444_2_, double p_i1444_4_, double p_i1444_6_)
    {
        super(p_i1444_1_, p_i1444_2_, p_i1444_4_, p_i1444_6_);
    }

    public static void a(IDataWalker p_a_0_)
    {
        EntityMinecartCommandBlock.a(p_a_0_, afm.class);
    }

    public EntityMinecartCommandBlock.a v()
    {
        return EntityMinecartCommandBlock.a.d;
    }

    public awt x()
    {
        return IGrowable.W.t();
    }

    public void B_()
    {
        super.B_();

        if (this.a > 0)
        {
            --this.a;
            this.l.a(EnumParticleTypes.SMOKE_NORMAL, this.p, this.q + 0.5D, this.r, 0.0D, 0.0D, 0.0D);
        }
        else if (this.a == 0)
        {
            this.c(this.s * this.s + this.u * this.u);
        }

        if (this.A)
        {
            double d0 = this.s * this.s + this.u * this.u;

            if (d0 >= 0.009999999776482582D)
            {
                this.c(d0);
            }
        }
    }

    public boolean a(EntityDamageSourceIndirect p_a_1_, float p_a_2_)
    {
        EntityList entitylist = p_a_1_.i();

        if (entitylist instanceof EntityEvokerFangs)
        {
            EntityEvokerFangs entityevokerfangs = (EntityEvokerFangs)entitylist;

            if (entityevokerfangs.isBurning())
            {
                this.c(entityevokerfangs.s * entityevokerfangs.s + entityevokerfangs.t * entityevokerfangs.t + entityevokerfangs.u * entityevokerfangs.u);
            }
        }

        return super.a(p_a_1_, p_a_2_);
    }

    public void a(EntityDamageSourceIndirect p_a_1_)
    {
        double d0 = this.s * this.s + this.u * this.u;

        if (!p_a_1_.o() && !p_a_1_.c() && d0 < 0.009999999776482582D)
        {
            super.a(p_a_1_);

            if (!p_a_1_.c() && this.l.W().b("doEntityDrops"))
            {
                this.a(new Items(IGrowable.W, 1), 0.0F);
            }
        }
        else
        {
            if (this.a < 0)
            {
                this.j();
                this.a = this.S.nextInt(20) + this.S.nextInt(20);
            }
        }
    }

    protected void c(double p_c_1_)
    {
        if (!this.l.G)
        {
            double d0 = Math.sqrt(p_c_1_);

            if (d0 > 5.0D)
            {
                d0 = 5.0D;
            }

            this.l.a(this, this.p, this.q, this.r, (float)(4.0D + this.S.nextDouble() * 1.5D * d0), true);
            this.X();
        }
    }

    public void e(float p_e_1_, float p_e_2_)
    {
        if (p_e_1_ >= 3.0F)
        {
            float f = p_e_1_ / 10.0F;
            this.c((double)(f * f));
        }

        super.e(p_e_1_, p_e_2_);
    }

    /**
     * Called every tick the minecart is on an activator rail.
     */
    public void onActivatorRailPass(int x, int y, int z, boolean receivingPower)
    {
        if (receivingPower && this.a < 0)
        {
            this.j();
        }
    }

    public void a(byte p_a_1_)
    {
        if (p_a_1_ == 10)
        {
            this.j();
        }
        else
        {
            super.a(p_a_1_);
        }
    }

    public void j()
    {
        this.a = 80;

        if (!this.l.G)
        {
            this.l.a(this, (byte)10);

            if (!this.ai())
            {
                this.l.a((RecipeItemHelper)null, this.p, this.q, this.r, qf.hW, qg.e, 1.0F, 1.0F);
            }
        }
    }

    public int k()
    {
        return this.a;
    }

    public boolean l()
    {
        return this.a > -1;
    }

    public float a(GameRules p_a_1_, IWorldEventListener p_a_2_, BlockPos p_a_3_, awt p_a_4_)
    {
        return !this.l() || !BlockBed.i(p_a_4_) && !BlockBed.b(p_a_2_, p_a_3_.up()) ? super.a(p_a_1_, p_a_2_, p_a_3_, p_a_4_) : 0.0F;
    }

    public boolean a(GameRules p_a_1_, IWorldEventListener p_a_2_, BlockPos p_a_3_, awt p_a_4_, float p_a_5_)
    {
        return !this.l() || !BlockBed.i(p_a_4_) && !BlockBed.b(p_a_2_, p_a_3_.up()) ? super.a(p_a_1_, p_a_2_, p_a_3_, p_a_4_, p_a_5_) : false;
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        if (compound.hasKey("TNTFuse", 99))
        {
            this.a = compound.getInteger("TNTFuse");
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("TNTFuse", this.a);
    }
}
