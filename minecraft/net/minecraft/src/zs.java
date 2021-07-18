package net.minecraft.src;

import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.world.IWorldEventListener;

public abstract class zs extends IEntityLivingData implements vf
{
    public zs(IWorldEventListener model)
    {
        super(model);
    }

    public boolean a(RecipeItemHelper p_a_1_)
    {
        return false;
    }
}
