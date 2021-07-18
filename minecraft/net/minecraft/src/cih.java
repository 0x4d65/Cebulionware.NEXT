package net.minecraft.src;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import net.minecraft.client.gui.recipebook.IRecipeShownListener;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.crafting.RecipeRepairItem;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.stats.StatisticsManagerServer;

public class cih extends StatisticsManagerServer
{
    public static final Map<ItemDoor, List<IRecipeShownListener>> e = Maps.<ItemDoor, List<IRecipeShownListener>>newHashMap();
    public static final List<IRecipeShownListener> f = Lists.<IRecipeShownListener>newArrayList();

    private static IRecipeShownListener a(ItemDoor p_a_0_)
    {
        IRecipeShownListener irecipeshownlistener = new IRecipeShownListener();
        f.add(irecipeshownlistener);
        (e.computeIfAbsent(p_a_0_, (p_c_0_) ->
        {
            return new ArrayList();
        })).add(irecipeshownlistener);
        (e.computeIfAbsent(ItemDoor.g, (p_b_0_) ->
        {
            return new ArrayList();
        })).add(irecipeshownlistener);
        return irecipeshownlistener;
    }

    private static ItemDoor a(Items p_a_0_)
    {
        ItemDoor itemdoor = p_a_0_.c().b();

        if (itemdoor != ItemDoor.b && itemdoor != ItemDoor.i && itemdoor != ItemDoor.d)
        {
            return itemdoor == ItemDoor.j ? ItemDoor.i : ItemDoor.f;
        }
        else
        {
            return itemdoor;
        }
    }

    static
    {
        Table<ItemDoor, String, IRecipeShownListener> table = HashBasedTable.<ItemDoor, String, IRecipeShownListener>create();

        for (RecipeRepairItem reciperepairitem : ShapedRecipes.recipeWidth)
        {
            if (!reciperepairitem.func_192399_d())
            {
                ItemDoor itemdoor = a(reciperepairitem.b());
                String s = reciperepairitem.e();
                IRecipeShownListener irecipeshownlistener1;

                if (s.isEmpty())
                {
                    irecipeshownlistener1 = a(itemdoor);
                }
                else
                {
                    irecipeshownlistener1 = table.get(itemdoor, s);

                    if (irecipeshownlistener1 == null)
                    {
                        irecipeshownlistener1 = a(itemdoor);
                        table.put(itemdoor, s, irecipeshownlistener1);
                    }
                }

                irecipeshownlistener1.b(reciperepairitem);
            }
        }
    }
}
