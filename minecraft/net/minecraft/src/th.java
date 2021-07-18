package net.minecraft.src;

import java.util.Locale;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.datafix.FixTypes;

public class th implements FixTypes
{
    public int a()
    {
        return 816;
    }

    public NBTTagCompound a(NBTTagCompound p_a_1_)
    {
        if (p_a_1_.hasKey("lang", 8))
        {
            p_a_1_.setString("lang", p_a_1_.getString("lang").toLowerCase(Locale.ROOT));
        }

        return p_a_1_;
    }
}
