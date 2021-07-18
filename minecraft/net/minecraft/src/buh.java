package net.minecraft.src;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.profiler.ISnooperInfo;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IWorldNameable;
import net.minecraft.world.storage.loot.ILootContainer;

public class buh extends ISnooperInfo implements ILootContainer
{
    private final String a;
    private final Map<Integer, Integer> b = Maps.<Integer, Integer>newHashMap();

    public buh(String p_i288_1_, ITextComponent p_i288_2_, int p_i288_3_)
    {
        super(p_i288_2_, p_i288_3_);
        this.a = p_i288_1_;
    }

    public int getField(int id)
    {
        return this.b.containsKey(Integer.valueOf(id)) ? ((Integer)this.b.get(Integer.valueOf(id))).intValue() : 0;
    }

    public void setField(int id, int value)
    {
        this.b.put(Integer.valueOf(id), Integer.valueOf(value));
    }

    public int getFieldCount()
    {
        return this.b.size();
    }

    public boolean A_()
    {
        return false;
    }

    public void a(IWorldNameable p_a_1_)
    {
    }

    public IWorldNameable j()
    {
        return IWorldNameable.a;
    }

    public String l()
    {
        return this.a;
    }

    public ContainerBeacon a(EnumPlayerModelParts p_a_1_, RecipeItemHelper p_a_2_)
    {
        throw new UnsupportedOperationException();
    }
}
