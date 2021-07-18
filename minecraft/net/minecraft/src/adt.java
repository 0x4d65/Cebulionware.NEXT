package net.minecraft.src;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.BlockBone;
import net.minecraft.block.IGrowable;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.entity.EntityBodyHelper;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.NpcMerchant;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIDefendVillage;
import net.minecraft.entity.ai.EntityAIDoorInteract;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAIFollowOwnerFlying;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOcelotAttack;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIVillagerMate;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttribute;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Items;
import net.minecraft.item.ItemLead;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathWorldListener;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.datafix.IDataWalker;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.IWorldEventListener;
import net.minecraft.world.storage.loot.LootEntryItem;

public class adt extends IRangedAttackMob
{
    protected static final AttributeModifier a = (new EntityBodyHelper((AttributeModifier)null, "zombie.spawnReinforcements", 0.0D, 0.0D, 1.0D)).a("Spawn Reinforcements Chance");
    private static final UUID b = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
    private static final AbstractAttributeMap c = new AbstractAttributeMap(b, "Baby speed boost", 0.5D, 1);
    private static final DataSerializer<Boolean> bx = nb.a(adt.class, EntityDataManager.h);
    private static final DataSerializer<Integer> by = nb.a(adt.class, EntityDataManager.NEXT_ID_MAP);
    private static final DataSerializer<Boolean> bz = nb.a(adt.class, EntityDataManager.h);
    private final EntityAIDoorInteract bA = new EntityAIDoorInteract(this);
    private boolean bB;
    private float bC = -1.0F;
    private float bD;

    public adt(IWorldEventListener p_i1343_1_)
    {
        super(p_i1343_1_);
        this.a(0.6F, 1.95F);
    }

    protected void initEntityAI()
    {
        this.br.a(0, new EntityAIFollowOwnerFlying(this));
        this.br.a(2, new EntityAIDefendVillage(this, 1.0D, false));
        this.br.a(5, new EntityAIOcelotAttack(this, 1.0D));
        this.br.a(7, new yp(this, 1.0D));
        this.br.a(8, new EntityAIVillagerMate(this, RecipeItemHelper.class, 8.0F));
        this.br.a(8, new EntityAIAttackRanged(this));
        this.do();
    }

    protected void do()
    {
        this.br.a(6, new EntityAIMoveTowardsRestriction(this, 1.0D, false));
        this.bs.a(1, new EntityAIFindEntityNearestPlayer(this, true, new Class[] {SharedMonsterAttributes.class}));
        this.bs.a(2, new EntityAIOwnerHurtByTarget(this, RecipeItemHelper.class, true));
        this.bs.a(3, new EntityAIOwnerHurtByTarget(this, ady.class, false));
        this.bs.a(3, new EntityAIOwnerHurtByTarget(this, EntityWolf.class, true));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.a(EntitySilverfish.b).a(35.0D);
        this.a(EntitySilverfish.d).a(0.23000000417232513D);
        this.a(EntitySilverfish.f).a(3.0D);
        this.a(EntitySilverfish.h).a(2.0D);
        this.cm().b(a).a(this.S.nextDouble() * 0.10000000149011612D);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.V().a(bx, Boolean.valueOf(false));
        this.V().a(by, Integer.valueOf(0));
        this.V().a(bz, Boolean.valueOf(false));
    }

    public void setAttacking(boolean attacking)
    {
        this.V().b(bz, Boolean.valueOf(attacking));
    }

    public boolean dq()
    {
        return ((Boolean)this.V().a(bz)).booleanValue();
    }

    public boolean dr()
    {
        return this.bB;
    }

    public void setSwingingArms(boolean swingingArms)
    {
        if (this.bB != swingingArms)
        {
            this.bB = swingingArms;
            ((PathWorldListener)this.x()).a(swingingArms);

            if (swingingArms)
            {
                this.br.a(1, this.bA);
            }
            else
            {
                this.br.a(this.bA);
            }
        }
    }

    public boolean l_()
    {
        return ((Boolean)this.V().a(bx)).booleanValue();
    }

    protected int b(RecipeItemHelper p_b_1_)
    {
        if (this.l_())
        {
            this.b_ = (int)((float)this.b_ * 2.5F);
        }

        return super.b(p_b_1_);
    }

    public void q(boolean p_q_1_)
    {
        this.V().b(bx, Boolean.valueOf(p_q_1_));

        if (this.l != null && !this.l.G)
        {
            BaseAttribute baseattribute = this.a(EntitySilverfish.d);
            baseattribute.c(c);

            if (p_q_1_)
            {
                baseattribute.b(c);
            }
        }

        this.r(p_q_1_);
    }

    public void a(DataSerializer<?> p_a_1_)
    {
        if (bx.equals(p_a_1_))
        {
            this.r(this.l_());
        }

        super.a(p_a_1_);
    }

    public void n()
    {
        if (this.l.D() && !this.l.G && !this.l_() && this.isAttacking())
        {
            float f = this.aw();

            if (f > 0.5F && this.S.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.l.h(new BlockPos(this.p, this.q + (double)this.getEyeHeight(), this.r)))
            {
                boolean flag = true;
                Items items = this.b(net.minecraft.entity.EntityFlying.f);

                if (!items.b())
                {
                    if (items.f())
                    {
                        items.b(items.i() + this.S.nextInt(2));

                        if (items.i() >= items.k())
                        {
                            this.b(items);
                            this.a(net.minecraft.entity.EntityFlying.f, Items.field_190931_a);
                        }
                    }

                    flag = false;
                }

                if (flag)
                {
                    this.i(8);
                }
            }
        }

        super.n();
    }

    protected boolean isAttacking()
    {
        return true;
    }

    public boolean a(EntityDamageSourceIndirect p_a_1_, float p_a_2_)
    {
        if (super.a(p_a_1_, p_a_2_))
        {
            EnumCreatureType enumcreaturetype = this.z();

            if (enumcreaturetype == null && p_a_1_.j() instanceof EnumCreatureType)
            {
                enumcreaturetype = (EnumCreatureType)p_a_1_.j();
            }

            if (enumcreaturetype != null && this.l.ag() == EnumHand.d && (double)this.S.nextFloat() < this.a(a).e() && this.l.W().b("doMobSpawning"))
            {
                int i = IProgressUpdate.c(this.p);
                int j = IProgressUpdate.c(this.q);
                int k = IProgressUpdate.c(this.r);
                adt adt = new adt(this.l);

                for (int l = 0; l < 50; ++l)
                {
                    int i1 = i + IProgressUpdate.a(this.S, 7, 40) * IProgressUpdate.a(this.S, -1, 1);
                    int j1 = j + IProgressUpdate.a(this.S, 7, 40) * IProgressUpdate.a(this.S, -1, 1);
                    int k1 = k + IProgressUpdate.a(this.S, 7, 40) * IProgressUpdate.a(this.S, -1, 1);

                    if (this.l.o(new BlockPos(i1, j1 - 1, k1)).isFullyOpaque() && this.l.k(new BlockPos(i1, j1, k1)) < 10)
                    {
                        adt.b((double)i1, (double)j1, (double)k1);

                        if (!this.l.a((double)i1, (double)j1, (double)k1, 7.0D) && this.l.a(adt.bw(), adt) && this.l.a(adt, adt.bw()).isEmpty() && !this.l.d(adt.bw()))
                        {
                            this.l.a(adt);
                            adt.d(enumcreaturetype);
                            adt.a(this.l.D(new BlockPos(adt)), (EnumCreatureAttribute)null);
                            this.a(a).b(new AbstractAttributeMap("Zombie reinforcement caller charge", -0.05000000074505806D, 0));
                            adt.a(a).b(new AbstractAttributeMap("Zombie reinforcement callee charge", -0.05000000074505806D, 0));
                            break;
                        }
                    }
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean B(EntityList p_B_1_)
    {
        boolean flag = super.B(p_B_1_);

        if (flag)
        {
            float f = this.l.D(new BlockPos(this)).b();

            if (this.co().b() && this.aR() && this.S.nextFloat() < f * 0.3F)
            {
                p_B_1_.i(2 * (int)f);
            }
        }

        return flag;
    }

    protected SoundCategory F()
    {
        return qf.ji;
    }

    protected SoundCategory d(EntityDamageSourceIndirect p_d_1_)
    {
        return qf.jq;
    }

    protected SoundCategory cf()
    {
        return qf.jm;
    }

    protected SoundCategory dm()
    {
        return qf.jw;
    }

    protected void a(BlockPos p_a_1_, BlockBone p_a_2_)
    {
        this.a(this.dm(), 0.15F, 1.0F);
    }

    public IEntityOwnable cn()
    {
        return IEntityOwnable.b;
    }

    @Nullable
    protected BehaviorProjectileDispense J()
    {
        return LootEntryItem.am;
    }

    protected void a(IInteractionObject p_a_1_)
    {
        super.a(p_a_1_);

        if (this.S.nextFloat() < (this.l.ag() == EnumHand.d ? 0.05F : 0.01F))
        {
            int i = this.S.nextInt(3);

            if (i == 0)
            {
                this.a(net.minecraft.entity.EntityFlying.a, new Items(ItemLead.o));
            }
            else
            {
                this.a(net.minecraft.entity.EntityFlying.a, new Items(ItemLead.b));
            }
        }
    }

    public static void c(IDataWalker p_c_0_)
    {
        IEntityLivingData.a(p_c_0_, adt.class);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);

        if (this.l_())
        {
            compound.setBoolean("IsBaby", true);
        }

        compound.setBoolean("CanBreakDoors", this.dr());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        if (compound.getBoolean("IsBaby"))
        {
            this.q(true);
        }

        this.setSwingingArms(compound.getBoolean("CanBreakDoors"));
    }

    public void b(EnumCreatureType p_b_1_)
    {
        super.b(p_b_1_);

        if ((this.l.ag() == EnumHand.$VALUES || this.l.ag() == EnumHand.d) && p_b_1_ instanceof ady)
        {
            if (this.l.ag() != EnumHand.d && this.S.nextBoolean())
            {
                return;
            }

            ady ady = (ady)p_b_1_;
            NpcMerchant npcmerchant = new NpcMerchant(this.l);
            npcmerchant.u(ady);
            this.l.e(ady);
            npcmerchant.a(this.l.D(new BlockPos(npcmerchant)), new adt.a(false));
            npcmerchant.a(ady.dl());
            npcmerchant.q(ady.l_());
            npcmerchant.n(ady.dc());

            if (ady.n_())
            {
                npcmerchant.c(ady.bq());
                npcmerchant.j(ady.br());
            }

            this.l.a(npcmerchant);
            this.l.a((RecipeItemHelper)null, 1026, new BlockPos(this), 0);
        }
    }

    public float getEyeHeight()
    {
        float f = 1.74F;

        if (this.l_())
        {
            f = (float)((double)f - 0.81D);
        }

        return f;
    }

    protected boolean c(Items p_c_1_)
    {
        return p_c_1_.c() == ItemLead.aX && this.l_() && this.aS() ? false : super.c(p_c_1_);
    }

    @Nullable
    public EnumCreatureAttribute a(IInteractionObject p_a_1_, @Nullable EnumCreatureAttribute p_a_2_)
    {
        p_a_2_ = super.a(p_a_1_, p_a_2_);
        float f = p_a_1_.d();
        this.m(this.S.nextFloat() < 0.55F * f);

        if (p_a_2_ == null)
        {
            p_a_2_ = new adt.a(this.l.r.nextFloat() < 0.05F);
        }

        if (p_a_2_ instanceof adt.a)
        {
            adt.a adt$a = (adt.a)p_a_2_;

            if (adt$a.UNDEFINED)
            {
                this.q(true);

                if ((double)this.l.r.nextFloat() < 0.05D)
                {
                    List<EntityFlying> list = this.l.a(EntityFlying.class, this.bw().c(5.0D, 3.0D, 5.0D), EntityXPOrb.xpOrbAge);

                    if (!list.isEmpty())
                    {
                        EntityFlying entityflying = list.get(0);
                        entityflying.p(true);
                        this.m(entityflying);
                    }
                }
                else if ((double)this.l.r.nextFloat() < 0.05D)
                {
                    EntityFlying entityflying1 = new EntityFlying(this.l);
                    entityflying1.b(this.p, this.q, this.r, this.v, 0.0F);
                    entityflying1.a(p_a_1_, (EnumCreatureAttribute)null);
                    entityflying1.p(true);
                    this.l.a(entityflying1);
                    this.m(entityflying1);
                }
            }
        }

        this.setSwingingArms(this.S.nextFloat() < f * 0.1F);
        this.a(p_a_1_);
        this.b(p_a_1_);

        if (this.b(net.minecraft.entity.EntityFlying.f).b())
        {
            Calendar calendar = this.l.ae();

            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.S.nextFloat() < 0.25F)
            {
                this.a(net.minecraft.entity.EntityFlying.f, new Items(this.S.nextFloat() < 0.1F ? IGrowable.aZ : IGrowable.aU));
                this.bu[net.minecraft.entity.EntityFlying.f.b()] = 0.0F;
            }
        }

        this.a(EntitySilverfish.c).b(new AbstractAttributeMap("Random spawn bonus", this.S.nextDouble() * 0.05000000074505806D, 0));
        double d0 = this.S.nextDouble() * 1.5D * (double)f;

        if (d0 > 1.0D)
        {
            this.a(EntitySilverfish.b).b(new AbstractAttributeMap("Random zombie-spawn bonus", d0, 2));
        }

        if (this.S.nextFloat() < f * 0.05F)
        {
            this.a(a).b(new AbstractAttributeMap("Leader zombie bonus", this.S.nextDouble() * 0.25D + 0.5D, 0));
            this.a(EntitySilverfish.summonSilverfish).b(new AbstractAttributeMap("Leader zombie bonus", this.S.nextDouble() * 3.0D + 1.0D, 2));
            this.setSwingingArms(true);
        }

        return p_a_2_;
    }

    public void r(boolean p_r_1_)
    {
        this.a(p_r_1_ ? 0.5F : 1.0F);
    }

    protected final void a(float p_a_1_, float p_a_2_)
    {
        boolean flag = this.bC > 0.0F && this.bD > 0.0F;
        this.bC = p_a_1_;
        this.bD = p_a_2_;

        if (!flag)
        {
            this.a(1.0F);
        }
    }

    protected final void a(float p_a_1_)
    {
        super.a(this.bC * p_a_1_, this.bD * p_a_1_);
    }

    public double aF()
    {
        return this.l_() ? 0.0D : -0.45D;
    }

    public void a(EntityDamageSourceIndirect p_a_1_)
    {
        super.a(p_a_1_);

        if (p_a_1_.j() instanceof EntityEnderman)
        {
            EntityEnderman entityenderman = (EntityEnderman)p_a_1_.j();

            if (entityenderman.isAttacking() && entityenderman.dp())
            {
                entityenderman.dq();
                Items items = this.dn();

                if (!items.b())
                {
                    this.a(items, 0.0F);
                }
            }
        }
    }

    protected Items dn()
    {
        return new Items(ItemLead.ci, 1, 2);
    }

    class a implements EnumCreatureAttribute
    {
        public boolean UNDEFINED;

        private a(boolean p_i1341_2_)
        {
            this.UNDEFINED = p_i1341_2_;
        }
    }
}
