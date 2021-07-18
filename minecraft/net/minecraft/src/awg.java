package net.minecraft.src;

import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;

public class awg extends TileEntityChest
{
    public boolean a(EnumFacing p_a_1_)
    {
        return p_a_1_ == EnumFacing.UP;
    }
}
