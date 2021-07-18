package net.minecraft.src;

import javax.annotation.Nullable;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Items;
import net.minecraft.item.ItemLead;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.datafix.IDataWalker;
import net.minecraft.world.IWorldEventListener;
import net.minecraft.world.storage.loot.LootEntryItem;

public class aaw extends EntityHorse
{
    public aaw(IWorldEventListener p_i1223_1_)
    {
        super(p_i1223_1_);
    }

    public static void a(IDataWalker p_a_0_)
    {
        EntityHorse.c(p_a_0_, aaw.class);
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.a(EntitySilverfish.summonSilverfish).a(15.0D);
        this.a(EntitySilverfish.d).a(0.20000000298023224D);
        this.a(TAMED).a(this.dN());
    }

    public IEntityOwnable cn()
    {
        return IEntityOwnable.b;
    }

    protected SoundCategory F()
    {
        super.F();
        return qf.jn;
    }

    protected SoundCategory cf()
    {
        super.cf();
        return qf.jo;
    }

    protected SoundCategory d(EntityDamageSourceIndirect p_d_1_)
    {
        super.d(p_d_1_);
        return qf.jp;
    }

    @Nullable
    protected BehaviorProjectileDispense J()
    {
        return LootEntryItem.J;
    }

    public boolean a(RecipeItemHelper p_a_1_, EnumActionResult p_a_2_)
    {
        Items items = p_a_1_.b(p_a_2_);
        boolean flag = !items.b();

        if (flag && items.c() == ItemLead.bU)
        {
            return super.a(p_a_1_, p_a_2_);
        }
        else if (!this.du())
        {
            return false;
        }
        else if (this.l_())
        {
            return super.a(p_a_1_, p_a_2_);
        }
        else if (p_a_1_.aU())
        {
            this.c(p_a_1_);
            return true;
        }
        else if (this.aT())
        {
            return super.a(p_a_1_, p_a_2_);
        }
        else
        {
            if (flag)
            {
                if (!this.dG() && items.c() == ItemLead.aD)
                {
                    this.c(p_a_1_);
                    return true;
                }

                if (items.a(p_a_1_, this, p_a_2_))
                {
                    return true;
                }
            }

            this.g(p_a_1_);
            return true;
        }
    }
}
