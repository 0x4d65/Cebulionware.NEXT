package net.minecraft.src;

import net.minecraft.block.BlockBone;
import net.minecraft.block.BlockSkull;
import net.minecraft.init.Items;
import net.minecraft.inventory.ContainerDispenser;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemLead;
import net.minecraft.item.crafting.RecipeRepairItem;
import net.minecraft.util.NonNullList;
import net.minecraft.world.IWorldEventListener;

public class akz
{
    public static class a implements RecipeRepairItem
    {
        public boolean a(ContainerDispenser p_a_1_, IWorldEventListener p_a_2_)
        {
            int i = 0;
            int j = 0;

            for (int k = 0; k < p_a_1_.getSizeInventory(); ++k)
            {
                Items items = p_a_1_.a(k);

                if (!items.b())
                {
                    if (BlockBone.a(items.c()) instanceof BlockSkull)
                    {
                        ++i;
                    }
                    else
                    {
                        if (items.c() != ItemLead.be)
                        {
                            return false;
                        }

                        ++j;
                    }

                    if (j > 1 || i > 1)
                    {
                        return false;
                    }
                }
            }

            return i == 1 && j == 1;
        }

        public Items a(ContainerDispenser p_a_1_)
        {
            Items items = Items.field_190931_a;
            Items items1 = Items.field_190931_a;

            for (int i = 0; i < p_a_1_.getSizeInventory(); ++i)
            {
                Items items2 = p_a_1_.a(i);

                if (!items2.b())
                {
                    if (BlockBone.a(items2.c()) instanceof BlockSkull)
                    {
                        items = items2;
                    }
                    else if (items2.c() == ItemLead.be)
                    {
                        items1 = items2;
                    }
                }
            }

            Items items3 = BlockSkull.b(ItemCloth.a(items1.j()));

            if (items.o())
            {
                items3.b(items.p().copy());
            }

            return items3;
        }

        public Items b()
        {
            return Items.field_190931_a;
        }

        public NonNullList<Items> b(ContainerDispenser p_b_1_)
        {
            NonNullList<Items> nonnulllist = NonNullList.<Items>func_191197_a(p_b_1_.getSizeInventory(), Items.field_190931_a);

            for (int i = 0; i < nonnulllist.size(); ++i)
            {
                Items items = p_b_1_.a(i);

                if (items.c().r())
                {
                    nonnulllist.set(i, new Items(items.c().q()));
                }
            }

            return nonnulllist;
        }

        public boolean func_192399_d()
        {
            return true;
        }

        public boolean func_194133_a(int p_194133_1_, int p_194133_2_)
        {
            return p_194133_1_ * p_194133_2_ >= 2;
        }
    }
}
