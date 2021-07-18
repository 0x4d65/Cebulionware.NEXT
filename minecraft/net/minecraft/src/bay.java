package net.minecraft.src;

import java.util.Random;
import net.minecraft.block.BlockWallSign;
import net.minecraft.block.IGrowable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldEventListener;
import net.minecraft.world.gen.feature.WorldGenFossils;

public class bay extends WorldGenFossils
{
    public boolean b(IWorldEventListener p_b_1_, Random p_b_2_, BlockPos p_b_3_)
    {
        for (; p_b_3_.getY() < 128; p_b_3_ = p_b_3_.up())
        {
            if (p_b_1_.d(p_b_3_))
            {
                for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL.facings())
                {
                    if (IGrowable.bn.b(p_b_1_, p_b_3_, enumfacing))
                    {
                        awt awt = IGrowable.bn.t().a(BlockWallSign.FACING, Boolean.valueOf(enumfacing == EnumFacing.NORTH)).a(BlockWallSign.SIGN_EAST_AABB, Boolean.valueOf(enumfacing == EnumFacing.EAST)).a(BlockWallSign.SIGN_WEST_AABB, Boolean.valueOf(enumfacing == EnumFacing.SOUTH)).a(BlockWallSign.SIGN_SOUTH_AABB, Boolean.valueOf(enumfacing == EnumFacing.WEST));
                        p_b_1_.a(p_b_3_, awt, 2);
                        break;
                    }
                }
            }
            else
            {
                p_b_3_ = p_b_3_.add(p_b_2_.nextInt(4) - p_b_2_.nextInt(4), 0, p_b_2_.nextInt(4) - p_b_2_.nextInt(4));
            }
        }

        return true;
    }
}
