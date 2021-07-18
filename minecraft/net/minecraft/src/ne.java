package net.minecraft.src;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntListIterator;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.ContainerDispenser;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.inventory.ContainerShulkerBox;
import net.minecraft.item.ItemAir;
import net.minecraft.item.crafting.RecipeRepairItem;
import net.minecraft.item.crafting.ShieldRecipes;
import net.minecraft.network.play.server.SPacketPlayerAbilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ne
{
    private final Logger a = LogManager.getLogger();
    private final EntityArrow b = new EntityArrow();
    private EntityTrackerEntry c;
    private RecipeRepairItem d;
    private boolean e;
    private ContainerShulkerBox f;
    private ContainerDispenser g;
    private List<ItemAir> h;

    public void a(EntityTrackerEntry p_a_1_, @Nullable RecipeRepairItem p_a_2_, boolean p_a_3_)
    {
        if (p_a_2_ != null && p_a_1_.F().b(p_a_2_))
        {
            this.c = p_a_1_;
            this.d = p_a_2_;
            this.e = p_a_3_;
            this.h = p_a_1_.by.c;
            ContainerBeacon containerbeacon = p_a_1_.by;
            this.f = null;
            this.g = null;

            if (containerbeacon instanceof ContainerEnchantment)
            {
                this.f = ((ContainerEnchantment)containerbeacon).xpSeed;
                this.g = ((ContainerEnchantment)containerbeacon).tableInventory;
            }
            else if (containerbeacon instanceof ContainerMerchant)
            {
                this.f = ((ContainerMerchant)containerbeacon).merchantInventory;
                this.g = ((ContainerMerchant)containerbeacon).theMerchant;
            }

            if (this.f != null && this.g != null)
            {
                if (this.c() || p_a_1_.z())
                {
                    this.b.a();
                    p_a_1_.bv.a(this.b, false);
                    this.g.a(this.b);

                    if (this.b.a(p_a_2_, (IntList)null))
                    {
                        this.b();
                    }
                    else
                    {
                        this.a();
                        p_a_1_.updateCounter.a(new SPacketPlayerAbilities(p_a_1_.by.d, p_a_2_));
                    }

                    p_a_1_.bv.markDirty();
                }
            }
        }
    }

    private void a()
    {
        EnumPlayerModelParts enumplayermodelparts = this.c.bv;

        for (int i = 0; i < this.g.getSizeInventory(); ++i)
        {
            Items items = this.g.a(i);

            if (!items.b())
            {
                while (items.E() > 0)
                {
                    int j = enumplayermodelparts.d(items);

                    if (j == -1)
                    {
                        j = enumplayermodelparts.k();
                    }

                    Items items1 = items.l();
                    items1.e(1);
                    enumplayermodelparts.c(j, items1);
                    this.g.a(i, 1);
                }
            }
        }

        this.g.clear();
        this.f.clear();
    }

    private void b()
    {
        boolean flag = this.d.a(this.g, this.c.encodedRotationPitch);
        int i = this.b.b(this.d, (IntList)null);

        if (flag)
        {
            boolean flag1 = true;

            for (int j = 0; j < this.g.getSizeInventory(); ++j)
            {
                Items items = this.g.a(j);

                if (!items.b() && Math.min(i, items.d()) > items.E())
                {
                    flag1 = false;
                }
            }

            if (flag1)
            {
                return;
            }
        }

        int i1 = this.a(i, flag);
        IntList intlist = new IntArrayList();

        if (this.b.a(this.d, intlist, i1))
        {
            int j1 = i1;
            IntListIterator intlistiterator = intlist.iterator();

            while (intlistiterator.hasNext())
            {
                int k = ((Integer)intlistiterator.next()).intValue();
                int l = EntityArrow.b(k).d();

                if (l < j1)
                {
                    j1 = l;
                }
            }

            if (this.b.a(this.d, intlist, j1))
            {
                this.a();
                this.a(j1, intlist);
            }
        }
    }

    private int a(int p_a_1_, boolean p_a_2_)
    {
        int i = 1;

        if (this.e)
        {
            i = p_a_1_;
        }
        else if (p_a_2_)
        {
            i = 64;

            for (int j = 0; j < this.g.getSizeInventory(); ++j)
            {
                Items items = this.g.a(j);

                if (!items.b() && i > items.E())
                {
                    i = items.E();
                }
            }

            if (i < 64)
            {
                ++i;
            }
        }

        return i;
    }

    private void a(int p_a_1_, IntList p_a_2_)
    {
        int i = this.g.j();
        int j = this.g.i();

        if (this.d instanceof ShieldRecipes)
        {
            ShieldRecipes shieldrecipes = (ShieldRecipes)this.d;
            i = shieldrecipes.f();
            j = shieldrecipes.g();
        }

        int j1 = 1;
        Iterator<Integer> iterator = p_a_2_.iterator();

        for (int k = 0; k < this.g.j() && j != k; ++k)
        {
            for (int l = 0; l < this.g.i(); ++l)
            {
                if (i == l || !iterator.hasNext())
                {
                    j1 += this.g.j() - l;
                    break;
                }

                ItemAir itemair = this.h.get(j1);
                Items items = EntityArrow.b(((Integer)iterator.next()).intValue());

                if (items.b())
                {
                    ++j1;
                }
                else
                {
                    for (int i1 = 0; i1 < p_a_1_; ++i1)
                    {
                        this.a(itemair, items);
                    }

                    ++j1;
                }
            }

            if (!iterator.hasNext())
            {
                break;
            }
        }
    }

    private void a(ItemAir p_a_1_, Items p_a_2_)
    {
        EnumPlayerModelParts enumplayermodelparts = this.c.bv;
        int i = enumplayermodelparts.c(p_a_2_);

        if (i != -1)
        {
            Items items = enumplayermodelparts.a(i).l();

            if (!items.b())
            {
                if (items.E() > 1)
                {
                    enumplayermodelparts.a(i, 1);
                }
                else
                {
                    enumplayermodelparts.c_(i);
                }

                items.e(1);

                if (p_a_1_.d().b())
                {
                    p_a_1_.d(items);
                }
                else
                {
                    p_a_1_.d().f(1);
                }
            }
        }
    }

    private boolean c()
    {
        EnumPlayerModelParts enumplayermodelparts = this.c.bv;

        for (int i = 0; i < this.g.getSizeInventory(); ++i)
        {
            Items items = this.g.a(i);

            if (!items.b())
            {
                int j = enumplayermodelparts.d(items);

                if (j == -1)
                {
                    j = enumplayermodelparts.k();
                }

                if (j == -1)
                {
                    return false;
                }
            }
        }

        return true;
    }
}
