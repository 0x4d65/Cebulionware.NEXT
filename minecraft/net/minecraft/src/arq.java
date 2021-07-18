package net.minecraft.src;

import net.minecraft.block.BlockBone;
import net.minecraft.block.BlockIce;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.material.MaterialPortal;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemDoor;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IWorldEventListener;
import net.minecraft.world.border.IBorderListener;

public class arq extends BlockBone
{
    public static final PropertyInteger a = BlockIce.D;
    protected static final Vec2f b = new Vec2f(0.0D, 0.0D, 0.0D, 0.1875D, 1.0D, 1.0D);
    protected static final Vec2f c = new Vec2f(0.8125D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    protected static final Vec2f d = new Vec2f(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1875D);
    protected static final Vec2f e = new Vec2f(0.0D, 0.0D, 0.8125D, 1.0D, 1.0D, 1.0D);

    protected arq()
    {
        super(MaterialPortal.q);
        this.w(this.A.b().a(a, EnumFacing.NORTH));
        this.a(ItemDoor.c);
    }

    public Vec2f b(awt p_b_1_, EnumSkyBlock p_b_2_, BlockPos p_b_3_)
    {
        switch ((EnumFacing)p_b_1_.c(a))
        {
            case NORTH:
                return e;

            case SOUTH:
                return d;

            case WEST:
                return c;

            case EAST:
            default:
                return b;
        }
    }

    public boolean b(awt p_b_1_)
    {
        return false;
    }

    public boolean c(awt p_c_1_)
    {
        return false;
    }

    public boolean b(IWorldEventListener p_b_1_, BlockPos p_b_2_, EnumFacing p_b_3_)
    {
        if (this.a(p_b_1_, p_b_2_.west(), p_b_3_))
        {
            return true;
        }
        else if (this.a(p_b_1_, p_b_2_.east(), p_b_3_))
        {
            return true;
        }
        else if (this.a(p_b_1_, p_b_2_.north(), p_b_3_))
        {
            return true;
        }
        else
        {
            return this.a(p_b_1_, p_b_2_.south(), p_b_3_);
        }
    }

    private boolean a(IWorldEventListener p_a_1_, BlockPos p_a_2_, EnumFacing p_a_3_)
    {
        awt awt = p_a_1_.o(p_a_2_);
        boolean flag = c(awt.u());
        return !flag && awt.d(p_a_1_, p_a_2_, p_a_3_) == IBlockState.a && !awt.canProvidePower();
    }

    public awt a(IWorldEventListener p_a_1_, BlockPos p_a_2_, EnumFacing p_a_3_, float p_a_4_, float p_a_5_, float p_a_6_, int p_a_7_, EnumCreatureType p_a_8_)
    {
        if (p_a_3_.getAxis().isHorizontal() && this.a(p_a_1_, p_a_2_.offset(p_a_3_.getOpposite()), p_a_3_))
        {
            return this.t().a(a, p_a_3_);
        }
        else
        {
            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
            {
                if (this.a(p_a_1_, p_a_2_.offset(enumfacing.getOpposite()), enumfacing))
                {
                    return this.t().a(a, enumfacing);
                }
            }

            return this.t();
        }
    }

    public void a(awt p_a_1_, IWorldEventListener p_a_2_, BlockPos p_a_3_, BlockBone p_a_4_, BlockPos p_a_5_)
    {
        EnumFacing enumfacing = (EnumFacing)p_a_1_.c(a);

        if (!this.a(p_a_2_, p_a_3_.offset(enumfacing.getOpposite()), enumfacing))
        {
            this.b(p_a_2_, p_a_3_, p_a_1_, 0);
            p_a_2_.g(p_a_3_);
        }

        super.a(p_a_1_, p_a_2_, p_a_3_, p_a_4_, p_a_5_);
    }

    public amm f()
    {
        return amm.c;
    }

    public awt a(int p_a_1_)
    {
        EnumFacing enumfacing = EnumFacing.getFront(p_a_1_);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.t().a(a, enumfacing);
    }

    public int e(awt p_e_1_)
    {
        return ((EnumFacing)p_e_1_.c(a)).getIndex();
    }

    public awt a(awt p_a_1_, BlockSandStone p_a_2_)
    {
        return p_a_1_.a(a, p_a_2_.a((EnumFacing)p_a_1_.c(a)));
    }

    public awt a(awt p_a_1_, BlockSilverfish p_a_2_)
    {
        return p_a_1_.a(p_a_2_.a((EnumFacing)p_a_1_.c(a)));
    }

    protected BlockWorldState b()
    {
        return new BlockWorldState(this, new IBorderListener[] {a});
    }

    public IBlockState a(EnumSkyBlock p_a_1_, awt p_a_2_, BlockPos p_a_3_, EnumFacing p_a_4_)
    {
        return IBlockState.i;
    }
}
