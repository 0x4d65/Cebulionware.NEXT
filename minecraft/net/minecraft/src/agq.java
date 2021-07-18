package net.minecraft.src;

import net.minecraft.block.BlockBone;
import net.minecraft.block.BlockSkull;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.item.ItemAir;

public class agq extends ItemAir
{
    public agq(IInventoryChangedListener p_i1482_1_, int p_i1482_2_, int p_i1482_3_, int p_i1482_4_)
    {
        super(p_i1482_1_, p_i1482_2_, p_i1482_3_, p_i1482_4_);
    }

    public boolean a(Items p_a_1_)
    {
        return !(BlockBone.a(p_a_1_.c()) instanceof BlockSkull);
    }
}
