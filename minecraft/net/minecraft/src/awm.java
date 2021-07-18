package net.minecraft.src;

import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.BlockBone;
import net.minecraft.block.BlockPistonMoving;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.datafix.IDataWalker;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IWorldEventListener;
import net.minecraft.world.gen.layer.GenLayerDeepOcean;

public class awm extends TileEntityChest implements DedicatedServer
{
    private awt a;
    private EnumFacing f;
    private boolean g;
    private boolean h;
    private static final ThreadLocal<EnumFacing> i = new ThreadLocal<EnumFacing>()
    {
        protected EnumFacing a()
        {
            return null;
        }
    };
    private float j;
    private float k;

    public awm()
    {
    }

    public awm(awt playerInventory, EnumFacing merchant, boolean worldIn, boolean p_i1821_4_)
    {
        this.a = playerInventory;
        this.f = merchant;
        this.g = worldIn;
        this.h = p_i1821_4_;
    }

    public awt a()
    {
        return this.a;
    }

    public NBTTagCompound d()
    {
        return this.writeToNBT(new NBTTagCompound());
    }

    public int v()
    {
        return 0;
    }

    public boolean f()
    {
        return this.g;
    }

    public EnumFacing h()
    {
        return this.f;
    }

    public boolean i()
    {
        return this.h;
    }

    public float a(float p_a_1_)
    {
        if (p_a_1_ > 1.0F)
        {
            p_a_1_ = 1.0F;
        }

        return this.k + (this.j - this.k) * p_a_1_;
    }

    public float b(float p_b_1_)
    {
        return (float)this.f.getFrontOffsetX() * this.e(this.a(p_b_1_));
    }

    public float c(float p_c_1_)
    {
        return (float)this.f.getFrontOffsetY() * this.e(this.a(p_c_1_));
    }

    public float d(float p_d_1_)
    {
        return (float)this.f.getFrontOffsetZ() * this.e(this.a(p_d_1_));
    }

    private float e(float p_e_1_)
    {
        return this.g ? p_e_1_ - 1.0F : 1.0F - p_e_1_;
    }

    public Vec2f a(EnumSkyBlock p_a_1_, BlockPos p_a_2_)
    {
        return this.a(p_a_1_, p_a_2_, this.j).b(this.a(p_a_1_, p_a_2_, this.k));
    }

    public Vec2f a(EnumSkyBlock p_a_1_, BlockPos p_a_2_, float p_a_3_)
    {
        p_a_3_ = this.e(p_a_3_);
        awt awt = this.k();
        return awt.e(p_a_1_, p_a_2_).d((double)(p_a_3_ * (float)this.f.getFrontOffsetX()), (double)(p_a_3_ * (float)this.f.getFrontOffsetY()), (double)(p_a_3_ * (float)this.f.getFrontOffsetZ()));
    }

    private awt k()
    {
        return !this.f() && this.i() ? IGrowable.K.t().a(TileEntityPiston.pistonState, this.a.u() == IGrowable.F ? TileEntityPiston.a.b : TileEntityPiston.a.a).a(TileEntityPiston.H, this.a.c(BlockPistonMoving.H)) : this.a;
    }

    private void f(float p_f_1_)
    {
        EnumFacing enumfacing = this.g ? this.f : this.f.getOpposite();
        double d0 = (double)(p_f_1_ - this.j);
        List<Vec2f> list = Lists.<Vec2f>newArrayList();
        this.k().a(this.b, BlockPos.ORIGIN, new Vec2f(BlockPos.ORIGIN), list, (EntityList)null, true);

        if (!list.isEmpty())
        {
            Vec2f vec2f = this.a(this.a(list));
            List<EntityList> list1 = this.b.b((EntityList)null, this.a(vec2f, enumfacing, d0).b(vec2f));

            if (!list1.isEmpty())
            {
                boolean flag = this.a.u() == IGrowable.cE;

                for (int i = 0; i < list1.size(); ++i)
                {
                    EntityList entitylist = list1.get(i);

                    if (entitylist.o_() != GenLayerDeepOcean.d)
                    {
                        if (flag)
                        {
                            switch (enumfacing.getAxis())
                            {
                                case X:
                                    entitylist.s = (double)enumfacing.getFrontOffsetX();
                                    break;

                                case Y:
                                    entitylist.t = (double)enumfacing.getFrontOffsetY();
                                    break;

                                case Z:
                                    entitylist.u = (double)enumfacing.getFrontOffsetZ();
                            }
                        }

                        double d1 = 0.0D;

                        for (int j = 0; j < list.size(); ++j)
                        {
                            Vec2f vec2f1 = this.a(this.a(list.get(j)), enumfacing, d0);
                            Vec2f vec2f2 = entitylist.bw();

                            if (vec2f1.c(vec2f2))
                            {
                                d1 = Math.max(d1, this.a(vec2f1, enumfacing, vec2f2));

                                if (d1 >= d0)
                                {
                                    break;
                                }
                            }
                        }

                        if (d1 > 0.0D)
                        {
                            d1 = Math.min(d1, d0) + 0.01D;
                            i.set(enumfacing);
                            entitylist.a(EntityCreature.restoreWaterCost, d1 * (double)enumfacing.getFrontOffsetX(), d1 * (double)enumfacing.getFrontOffsetY(), d1 * (double)enumfacing.getFrontOffsetZ());
                            i.set((Object)null);

                            if (!this.g && this.h)
                            {
                                this.a(entitylist, enumfacing, d0);
                            }
                        }
                    }
                }
            }
        }
    }

    private Vec2f a(List<Vec2f> p_a_1_)
    {
        double d0 = 0.0D;
        double d1 = 0.0D;
        double d2 = 0.0D;
        double d3 = 1.0D;
        double d4 = 1.0D;
        double d5 = 1.0D;

        for (Vec2f vec2f : p_a_1_)
        {
            d0 = Math.min(vec2f.ZERO, d0);
            d1 = Math.min(vec2f.ONE, d1);
            d2 = Math.min(vec2f.UNIT_X, d2);
            d3 = Math.max(vec2f.NEGATIVE_UNIT_X, d3);
            d4 = Math.max(vec2f.UNIT_Y, d4);
            d5 = Math.max(vec2f.NEGATIVE_UNIT_Y, d5);
        }

        return new Vec2f(d0, d1, d2, d3, d4, d5);
    }

    private double a(Vec2f p_a_1_, EnumFacing p_a_2_, Vec2f p_a_3_)
    {
        switch (p_a_2_.getAxis())
        {
            case X:
                return b(p_a_1_, p_a_2_, p_a_3_);

            case Y:
            default:
                return c(p_a_1_, p_a_2_, p_a_3_);

            case Z:
                return d(p_a_1_, p_a_2_, p_a_3_);
        }
    }

    private Vec2f a(Vec2f p_a_1_)
    {
        double d0 = (double)this.e(this.j);
        return p_a_1_.d((double)this.c.getX() + d0 * (double)this.f.getFrontOffsetX(), (double)this.c.getY() + d0 * (double)this.f.getFrontOffsetY(), (double)this.c.getZ() + d0 * (double)this.f.getFrontOffsetZ());
    }

    private Vec2f a(Vec2f p_a_1_, EnumFacing p_a_2_, double p_a_3_)
    {
        double d0 = p_a_3_ * (double)p_a_2_.getAxisDirection().getOffset();
        double d1 = Math.min(d0, 0.0D);
        double d2 = Math.max(d0, 0.0D);

        switch (p_a_2_)
        {
            case WEST:
                return new Vec2f(p_a_1_.ZERO + d1, p_a_1_.ONE, p_a_1_.UNIT_X, p_a_1_.ZERO + d2, p_a_1_.UNIT_Y, p_a_1_.NEGATIVE_UNIT_Y);

            case EAST:
                return new Vec2f(p_a_1_.NEGATIVE_UNIT_X + d1, p_a_1_.ONE, p_a_1_.UNIT_X, p_a_1_.NEGATIVE_UNIT_X + d2, p_a_1_.UNIT_Y, p_a_1_.NEGATIVE_UNIT_Y);

            case DOWN:
                return new Vec2f(p_a_1_.ZERO, p_a_1_.ONE + d1, p_a_1_.UNIT_X, p_a_1_.NEGATIVE_UNIT_X, p_a_1_.ONE + d2, p_a_1_.NEGATIVE_UNIT_Y);

            case UP:
            default:
                return new Vec2f(p_a_1_.ZERO, p_a_1_.UNIT_Y + d1, p_a_1_.UNIT_X, p_a_1_.NEGATIVE_UNIT_X, p_a_1_.UNIT_Y + d2, p_a_1_.NEGATIVE_UNIT_Y);

            case NORTH:
                return new Vec2f(p_a_1_.ZERO, p_a_1_.ONE, p_a_1_.UNIT_X + d1, p_a_1_.NEGATIVE_UNIT_X, p_a_1_.UNIT_Y, p_a_1_.UNIT_X + d2);

            case SOUTH:
                return new Vec2f(p_a_1_.ZERO, p_a_1_.ONE, p_a_1_.NEGATIVE_UNIT_Y + d1, p_a_1_.NEGATIVE_UNIT_X, p_a_1_.UNIT_Y, p_a_1_.NEGATIVE_UNIT_Y + d2);
        }
    }

    private void a(EntityList p_a_1_, EnumFacing p_a_2_, double p_a_3_)
    {
        Vec2f vec2f = p_a_1_.bw();
        Vec2f vec2f1 = BlockBone.j.a(this.c);

        if (vec2f.c(vec2f1))
        {
            EnumFacing enumfacing = p_a_2_.getOpposite();
            double d0 = this.a(vec2f1, enumfacing, vec2f) + 0.01D;
            double d1 = this.a(vec2f1, enumfacing, vec2f.a(vec2f1)) + 0.01D;

            if (Math.abs(d0 - d1) < 0.01D)
            {
                d0 = Math.min(d0, p_a_3_) + 0.01D;
                i.set(p_a_2_);
                p_a_1_.a(EntityCreature.restoreWaterCost, d0 * (double)enumfacing.getFrontOffsetX(), d0 * (double)enumfacing.getFrontOffsetY(), d0 * (double)enumfacing.getFrontOffsetZ());
                i.set((Object)null);
            }
        }
    }

    private static double b(Vec2f p_b_0_, EnumFacing p_b_1_, Vec2f p_b_2_)
    {
        return p_b_1_.getAxisDirection() == EnumFacing.AxisDirection.POSITIVE ? p_b_0_.NEGATIVE_UNIT_X - p_b_2_.ZERO : p_b_2_.NEGATIVE_UNIT_X - p_b_0_.ZERO;
    }

    private static double c(Vec2f p_c_0_, EnumFacing p_c_1_, Vec2f p_c_2_)
    {
        return p_c_1_.getAxisDirection() == EnumFacing.AxisDirection.POSITIVE ? p_c_0_.UNIT_Y - p_c_2_.ONE : p_c_2_.UNIT_Y - p_c_0_.ONE;
    }

    private static double d(Vec2f p_d_0_, EnumFacing p_d_1_, Vec2f p_d_2_)
    {
        return p_d_1_.getAxisDirection() == EnumFacing.AxisDirection.POSITIVE ? p_d_0_.NEGATIVE_UNIT_Y - p_d_2_.UNIT_X : p_d_2_.NEGATIVE_UNIT_Y - p_d_0_.UNIT_X;
    }

    public void j()
    {
        if (this.k < 1.0F && this.b != null)
        {
            this.j = 1.0F;
            this.k = this.j;
            this.b.s(this.c);
            this.invalidate();

            if (this.b.o(this.c).u() == IGrowable.M)
            {
                this.b.a(this.c, this.a, 3);
                this.b.a(this.c, this.a.u(), this.c);
            }
        }
    }

    /**
     * Like the old updateEntity(), except more generic.
     */
    public void update()
    {
        this.k = this.j;

        if (this.k >= 1.0F)
        {
            this.b.s(this.c);
            this.invalidate();

            if (this.b.o(this.c).u() == IGrowable.M)
            {
                this.b.a(this.c, this.a, 3);
                this.b.a(this.c, this.a.u(), this.c);
            }
        }
        else
        {
            float f = this.j + 0.5F;
            this.f(f);
            this.j = f;

            if (this.j >= 1.0F)
            {
                this.j = 1.0F;
            }
        }
    }

    public static void a(IDataWalker p_a_0_)
    {
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.a = BlockBone.c(compound.getInteger("blockId")).a(compound.getInteger("blockData"));
        this.f = EnumFacing.getFront(compound.getInteger("facing"));
        this.j = compound.getFloat("progress");
        this.k = this.j;
        this.g = compound.getBoolean("extending");
        this.h = compound.getBoolean("source");
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("blockId", BlockBone.a(this.a.u()));
        compound.setInteger("blockData", this.a.u().e(this.a));
        compound.setInteger("facing", this.f.getIndex());
        compound.setFloat("progress", this.k);
        compound.setBoolean("extending", this.g);
        compound.setBoolean("source", this.h);
        return compound;
    }

    public void a(IWorldEventListener p_a_1_, BlockPos p_a_2_, Vec2f p_a_3_, List<Vec2f> p_a_4_, @Nullable EntityList p_a_5_)
    {
        if (!this.g && this.h)
        {
            this.a.a(BlockPistonMoving.FACING, Boolean.valueOf(true)).a(p_a_1_, p_a_2_, p_a_3_, p_a_4_, p_a_5_, false);
        }

        EnumFacing enumfacing = i.get();

        if ((double)this.j >= 1.0D || enumfacing != (this.g ? this.f : this.f.getOpposite()))
        {
            int i = p_a_4_.size();
            awt awt;

            if (this.i())
            {
                awt = IGrowable.K.t().a(TileEntityPiston.H, this.f).a(TileEntityPiston.b, Boolean.valueOf(this.g != 1.0F - this.j < 0.25F));
            }
            else
            {
                awt = this.a;
            }

            float f = this.e(this.j);
            double d0 = (double)((float)this.f.getFrontOffsetX() * f);
            double d1 = (double)((float)this.f.getFrontOffsetY() * f);
            double d2 = (double)((float)this.f.getFrontOffsetZ() * f);
            awt.a(p_a_1_, p_a_2_, p_a_3_.d(-d0, -d1, -d2), p_a_4_, p_a_5_, true);

            for (int j = i; j < p_a_4_.size(); ++j)
            {
                p_a_4_.set(j, ((Vec2f)p_a_4_.get(j)).d(d0, d1, d2));
            }
        }
    }
}
