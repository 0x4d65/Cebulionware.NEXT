package net.minecraft.src;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.datafix.FixTypes;

public class tg implements FixTypes
{
    public int a()
    {
        return 505;
    }

    public NBTTagCompound a(NBTTagCompound p_a_1_)
    {
        p_a_1_.setString("useVbo", "true");
        return p_a_1_;
    }
}
