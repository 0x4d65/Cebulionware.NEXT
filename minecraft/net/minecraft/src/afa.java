package net.minecraft.src;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Set;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemLead;
import net.minecraft.item.crafting.RecipesArmorDyes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.IDataWalker;
import net.minecraft.world.IWorldEventListener;

public class afa extends EntityEvokerFangs
{
    private static final DataSerializer<Integer> f = nb.a(afa.class, EntityDataManager.NEXT_ID_MAP);
    private PotionUtils g = RecipesArmorDyes.a;
    private final Set<va> h = Sets.<va>newHashSet();
    private boolean at;

    public afa(IWorldEventListener p_i1414_1_)
    {
        super(p_i1414_1_);
    }

    public afa(IWorldEventListener p_i1415_1_, double p_i1415_2_, double p_i1415_4_, double p_i1415_6_)
    {
        super(p_i1415_1_, p_i1415_2_, p_i1415_4_, p_i1415_6_);
    }

    public afa(IWorldEventListener p_i1416_1_, EnumCreatureType p_i1416_2_)
    {
        super(p_i1416_1_, p_i1416_2_);
    }

    public void a(Items p_a_1_)
    {
        if (p_a_1_.c() == ItemLead.j)
        {
            this.g = aki.d(p_a_1_);
            Collection<va> collection = aki.b(p_a_1_);

            if (!collection.isEmpty())
            {
                for (va va : collection)
                {
                    this.h.add(new va(va));
                }
            }

            int i = b(p_a_1_);

            if (i == -1)
            {
                this.q();
            }
            else
            {
                this.d(i);
            }
        }
        else if (p_a_1_.c() == ItemLead.h)
        {
            this.g = RecipesArmorDyes.a;
            this.h.clear();
            this.Y.b(f, Integer.valueOf(-1));
        }
    }

    public static int b(Items p_b_0_)
    {
        NBTTagCompound nbttagcompound = p_b_0_.p();
        return nbttagcompound != null && nbttagcompound.hasKey("CustomPotionColor", 99) ? nbttagcompound.getInteger("CustomPotionColor") : -1;
    }

    private void q()
    {
        this.at = false;
        this.Y.b(f, Integer.valueOf(aki.a(aki.a(this.g, this.h))));
    }

    public void a(va p_a_1_)
    {
        this.h.add(p_a_1_);
        this.V().b(f, Integer.valueOf(aki.a(aki.a(this.g, this.h))));
    }

    protected void entityInit()
    {
        super.entityInit();
        this.Y.a(f, Integer.valueOf(-1));
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (this.l.G)
        {
            if (this.field_190553_a)
            {
                if (this.field_190554_b % 5 == 0)
                {
                    this.c(1);
                }
            }
            else
            {
                this.c(2);
            }
        }
        else if (this.field_190553_a && this.field_190554_b != 0 && !this.h.isEmpty() && this.field_190554_b >= 600)
        {
            this.l.a(this, (byte)0);
            this.g = RecipesArmorDyes.a;
            this.h.clear();
            this.Y.b(f, Integer.valueOf(-1));
        }
    }

    private void c(int p_c_1_)
    {
        int i = this.p();

        if (i != -1 && p_c_1_ > 0)
        {
            double d0 = (double)(i >> 16 & 255) / 255.0D;
            double d1 = (double)(i >> 8 & 255) / 255.0D;
            double d2 = (double)(i >> 0 & 255) / 255.0D;

            for (int j = 0; j < p_c_1_; ++j)
            {
                this.l.a(EnumParticleTypes.SPELL_MOB, this.p + (this.S.nextDouble() - 0.5D) * (double)this.G, this.q + this.S.nextDouble() * (double)this.H, this.r + (this.S.nextDouble() - 0.5D) * (double)this.G, d0, d1, d2);
            }
        }
    }

    public int p()
    {
        return ((Integer)this.Y.a(f)).intValue();
    }

    private void d(int p_d_1_)
    {
        this.at = true;
        this.Y.b(f, Integer.valueOf(p_d_1_));
    }

    public static void c(IDataWalker p_c_0_)
    {
        EntityEvokerFangs.a(p_c_0_, "TippedArrow");
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);

        if (this.g != RecipesArmorDyes.a && this.g != null)
        {
            compound.setString("Potion", ((BehaviorProjectileDispense)PotionUtils.a.getNameForObject(this.g)).toString());
        }

        if (this.at)
        {
            compound.setInteger("Color", this.p());
        }

        if (!this.h.isEmpty())
        {
            NBTTagList nbttaglist = new NBTTagList();

            for (va va : this.h)
            {
                nbttaglist.appendTag(va.a(new NBTTagCompound()));
            }

            compound.setTag("CustomPotionEffects", nbttaglist);
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        if (compound.hasKey("Potion", 8))
        {
            this.g = aki.c(compound);
        }

        for (va va : aki.b(compound))
        {
            this.a(va);
        }

        if (compound.hasKey("Color", 99))
        {
            this.d(compound.getInteger("Color"));
        }
        else
        {
            this.q();
        }
    }

    protected void a(EnumCreatureType p_a_1_)
    {
        super.a(p_a_1_);

        for (va va : this.g.a())
        {
            p_a_1_.c(new va(va.a(), Math.max(va.b() / 8, 1), va.c(), va.d(), va.e()));
        }

        if (!this.h.isEmpty())
        {
            for (va va : this.h)
            {
                p_a_1_.c(va);
            }
        }
    }

    protected Items j()
    {
        if (this.h.isEmpty() && this.g == RecipesArmorDyes.a)
        {
            return new Items(ItemLead.h);
        }
        else
        {
            Items items = new Items(ItemLead.j);
            aki.a(items, this.g);
            aki.a(items, this.h);

            if (this.at)
            {
                NBTTagCompound nbttagcompound = items.p();

                if (nbttagcompound == null)
                {
                    nbttagcompound = new NBTTagCompound();
                    items.b(nbttagcompound);
                }

                nbttagcompound.setInteger("CustomPotionColor", this.p());
            }

            return items;
        }
    }

    public void handleStatusUpdate(byte id)
    {
        if (id == 0)
        {
            int i = this.p();

            if (i != -1)
            {
                double d0 = (double)(i >> 16 & 255) / 255.0D;
                double d1 = (double)(i >> 8 & 255) / 255.0D;
                double d2 = (double)(i >> 0 & 255) / 255.0D;

                for (int j = 0; j < 20; ++j)
                {
                    this.l.a(EnumParticleTypes.SPELL_MOB, this.p + (this.S.nextDouble() - 0.5D) * (double)this.G, this.q + this.S.nextDouble() * (double)this.H, this.r + (this.S.nextDouble() - 0.5D) * (double)this.G, d0, d1, d2);
                }
            }
        }
        else
        {
            super.handleStatusUpdate(id);
        }
    }
}
