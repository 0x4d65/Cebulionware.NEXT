package net.minecraft.src;

import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAIHarvestFarmland;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.monster.EntitySpellcasterIllager;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityZombieHorse;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemLead;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IInteractionObject;

public class aav extends EntityAIHarvestFarmland
{
    private final EntityZombieHorse a;

    public aav(EntityZombieHorse p_i1222_1_)
    {
        this.a = p_i1222_1_;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        return this.a.l.a(this.a.p, this.a.q, this.a.r, 10.0D);
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        IInteractionObject iinteractionobject = this.a.l.D(new BlockPos(this.a));
        this.a.playTameEffect(false);
        this.a.setSitting(true);
        this.a.c(0);
        this.a.l.d(new EntityFallingBlock(this.a.l, this.a.p, this.a.q, this.a.r, true));
        EntitySpellcasterIllager entityspellcasterillager = this.a(iinteractionobject, this.a);
        entityspellcasterillager.m(this.a);

        for (int i = 0; i < 3; ++i)
        {
            EntityHorse entityhorse = this.a(iinteractionobject);
            EntitySpellcasterIllager entityspellcasterillager1 = this.a(iinteractionobject, entityhorse);
            entityspellcasterillager1.m(entityhorse);
            entityhorse.f(this.a.bR().nextGaussian() * 0.5D, 0.0D, this.a.bR().nextGaussian() * 0.5D);
        }
    }

    private EntityHorse a(IInteractionObject p_a_1_)
    {
        EntityZombieHorse entityzombiehorse = new EntityZombieHorse(this.a.l);
        entityzombiehorse.a(p_a_1_, (EnumCreatureAttribute)null);
        entityzombiehorse.b(this.a.p, this.a.q, this.a.r);
        entityzombiehorse.V = 60;
        entityzombiehorse.cW();
        entityzombiehorse.setSitting(true);
        entityzombiehorse.c(0);
        entityzombiehorse.l.a(entityzombiehorse);
        return entityzombiehorse;
    }

    private EntitySpellcasterIllager a(IInteractionObject p_a_1_, EntityHorse p_a_2_)
    {
        EntitySpellcasterIllager entityspellcasterillager = new EntitySpellcasterIllager(p_a_2_.l);
        entityspellcasterillager.a(p_a_1_, (EnumCreatureAttribute)null);
        entityspellcasterillager.setPosition(p_a_2_.p, p_a_2_.q, p_a_2_.r);
        entityspellcasterillager.V = 60;
        entityspellcasterillager.cW();

        if (entityspellcasterillager.b(EntityFlying.f).b())
        {
            entityspellcasterillager.a(EntityFlying.f, new Items(ItemLead.ab));
        }

        entityspellcasterillager.a(EntityFlying.a, Enchantments.a(entityspellcasterillager.bR(), entityspellcasterillager.co(), (int)(5.0F + p_a_1_.d() * (float)entityspellcasterillager.bR().nextInt(18)), false));
        entityspellcasterillager.a(EntityFlying.f, Enchantments.a(entityspellcasterillager.bR(), entityspellcasterillager.b(EntityFlying.f), (int)(5.0F + p_a_1_.d() * (float)entityspellcasterillager.bR().nextInt(18)), false));
        entityspellcasterillager.l.a(entityspellcasterillager);
        return entityspellcasterillager;
    }
}
