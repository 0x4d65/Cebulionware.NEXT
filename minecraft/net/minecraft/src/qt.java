package net.minecraft.src;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.stats.StatList;
import net.minecraft.util.TupleIntJsonSerializable;

public class qt
{
    protected final Map<TupleIntJsonSerializable, StatList> a = Maps.<TupleIntJsonSerializable, StatList>newConcurrentMap();

    public void b(RecipeItemHelper p_b_1_, TupleIntJsonSerializable p_b_2_, int p_b_3_)
    {
        this.a(p_b_1_, p_b_2_, this.a(p_b_2_) + p_b_3_);
    }

    public void a(RecipeItemHelper p_a_1_, TupleIntJsonSerializable p_a_2_, int p_a_3_)
    {
        StatList statlist = this.a.get(p_a_2_);

        if (statlist == null)
        {
            statlist = new StatList();
            this.a.put(p_a_2_, statlist);
        }

        statlist.a(p_a_3_);
    }

    public int a(TupleIntJsonSerializable p_a_1_)
    {
        StatList statlist = this.a.get(p_a_1_);
        return statlist == null ? 0 : statlist.a();
    }
}
