package net.minecraft.src;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.datafix.fixes.AddBedTileEntity;

public class tn extends to
{
    private final String[] a;

    public tn(Class<?> tps, String... p_i1018_2_)
    {
        super(tps);
        this.a = p_i1018_2_;
    }

    NBTTagCompound b(DataFixesManager p_b_1_, NBTTagCompound p_b_2_, int p_b_3_)
    {
        for (String s : this.a)
        {
            p_b_2_ = AddBedTileEntity.b(p_b_1_, p_b_2_, p_b_3_, s);
        }

        return p_b_2_;
    }
}
