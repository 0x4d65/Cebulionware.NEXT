package net.minecraft.src;

import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.datafix.fixes.BedItemColor;

public abstract class to implements BedItemColor
{
    private final BehaviorProjectileDispense a;

    public to(Class<?> p_i1019_1_)
    {
        if (EntityList.class.isAssignableFrom(p_i1019_1_))
        {
            this.a = EntitySelectors.a(p_i1019_1_);
        }
        else if (TileEntityChest.class.isAssignableFrom(p_i1019_1_))
        {
            this.a = TileEntityChest.a(p_i1019_1_);
        }
        else
        {
            this.a = null;
        }
    }

    public NBTTagCompound a(DataFixesManager p_a_1_, NBTTagCompound p_a_2_, int p_a_3_)
    {
        if ((new BehaviorProjectileDispense(p_a_2_.getString("id"))).equals(this.a))
        {
            p_a_2_ = this.b(p_a_1_, p_a_2_, p_a_3_);
        }

        return p_a_2_;
    }

    abstract NBTTagCompound b(DataFixesManager var1, NBTTagCompound var2, int var3);
}
