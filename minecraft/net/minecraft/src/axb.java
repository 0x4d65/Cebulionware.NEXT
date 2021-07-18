package net.minecraft.src;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.block.BlockBone;

public class axb implements Predicate<awt>
{
    private final BlockBone a;

    private axb(BlockBone p_i1833_1_)
    {
        this.a = p_i1833_1_;
    }

    public static axb a(BlockBone p_a_0_)
    {
        return new axb(p_a_0_);
    }

    public boolean a(@Nullable awt p_a_1_)
    {
        return p_a_1_ != null && p_a_1_.u() == this.a;
    }
}
