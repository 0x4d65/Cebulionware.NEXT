package net.minecraft.src;

import java.util.Locale;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.IGrowable;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.enchantment.EnchantmentFireAspect;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIFollowOwnerFlying;
import net.minecraft.entity.ai.EntityAILandOnOwnersShoulder;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.entity.ai.EntityAIOcelotAttack;
import net.minecraft.entity.ai.EntityAIPlay;
import net.minecraft.entity.ai.EntityAIRunAroundLikeCrazy;
import net.minecraft.entity.ai.EntityAITradePlayer;
import net.minecraft.entity.ai.EntityAIVillagerMate;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWanderAvoidWaterFlying;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemEnderEye;
import net.minecraft.item.ItemLead;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathWorldListener;
import net.minecraft.profiler.ISnooperInfo;
import net.minecraft.scoreboard.ScoreCriteria;
import net.minecraft.tileentity.CommandBlockBaseLogic;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.datafix.IDataWalker;
import net.minecraft.util.datafix.fixes.AddBedTileEntity;
import net.minecraft.util.datafix.fixes.BedItemColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.village.VillageCollection;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.IWorldEventListener;
import net.minecraft.world.storage.loot.LootEntryItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ady extends IAnimals implements adx, MerchantRecipeList
{
    private static final Logger by = LogManager.getLogger();
    private static final DataSerializer<Integer> bz = nb.a(ady.class, EntityDataManager.NEXT_ID_MAP);
    private int bA;
    private boolean bB;
    private boolean bC;
    VillageCollection bx;
    @Nullable
    private RecipeItemHelper bD;
    @Nullable
    private CommandBlockBaseLogic bE;
    private int bF;
    private boolean bG;
    private boolean bH;
    private int bI;
    private String bJ;
    private int bK;
    private int bL;
    private boolean bM;
    private boolean bN;
    private final ISnooperInfo bO;
    private static final ady.f[][][][] bP = new ady.f[][][][] {{{{new ady.a(ItemLead.R, new ady.g(18, 22)), new ady.a(ItemLead.cd, new ady.g(15, 19)), new ady.a(ItemLead.cc, new ady.g(15, 19)), new ady.e(ItemLead.S, new ady.g(-4, -2))}, {new ady.a(ItemStack.a(IGrowable.aU), new ady.g(8, 13)), new ady.e(ItemLead.cl, new ady.g(-3, -2))}, {new ady.a(ItemStack.a(IGrowable.bk), new ady.g(7, 12)), new ady.e(ItemLead.f, new ady.g(-7, -5))}, {new ady.e(ItemLead.bk, new ady.g(-10, -6)), new ady.e(ItemLead.bh, new ady.g(1, 1))}}, {{new ady.a(ItemLead.I, new ady.g(15, 20)), new ady.a(ItemLead.k, new ady.g(16, 24)), new ady.d(ItemLead.bc, new ady.g(6, 6), ItemLead.bd, new ady.g(6, 6))}, {new ady.c(ItemLead.aZ, new ady.g(7, 8))}}, {{new ady.a(ItemStack.a(IGrowable.L), new ady.g(16, 22)), new ady.e(ItemLead.bm, new ady.g(3, 4))}, {new ady.e(new Items(ItemStack.a(IGrowable.L)), new ady.g(1, 2)), new ady.e(new Items(ItemStack.a(IGrowable.L), 1, 1), new ady.g(1, 2)), new ady.e(new Items(ItemStack.a(IGrowable.L), 1, 2), new ady.g(1, 2)), new ady.e(new Items(ItemStack.a(IGrowable.L), 1, 3), new ady.g(1, 2)), new ady.e(new Items(ItemStack.a(IGrowable.L), 1, 4), new ady.g(1, 2)), new ady.e(new Items(ItemStack.a(IGrowable.L), 1, 5), new ady.g(1, 2)), new ady.e(new Items(ItemStack.a(IGrowable.L), 1, 6), new ady.g(1, 2)), new ady.e(new Items(ItemStack.a(IGrowable.L), 1, 7), new ady.g(1, 2)), new ady.e(new Items(ItemStack.a(IGrowable.L), 1, 8), new ady.g(1, 2)), new ady.e(new Items(ItemStack.a(IGrowable.L), 1, 9), new ady.g(1, 2)), new ady.e(new Items(ItemStack.a(IGrowable.L), 1, 10), new ady.g(1, 2)), new ady.e(new Items(ItemStack.a(IGrowable.L), 1, 11), new ady.g(1, 2)), new ady.e(new Items(ItemStack.a(IGrowable.L), 1, 12), new ady.g(1, 2)), new ady.e(new Items(ItemStack.a(IGrowable.L), 1, 13), new ady.g(1, 2)), new ady.e(new Items(ItemStack.a(IGrowable.L), 1, 14), new ady.g(1, 2)), new ady.e(new Items(ItemStack.a(IGrowable.L), 1, 15), new ady.g(1, 2))}}, {{new ady.a(ItemLead.I, new ady.g(15, 20)), new ady.e(ItemLead.h, new ady.g(-12, -8))}, {new ady.e(ItemLead.g, new ady.g(2, 3)), new ady.d(ItemStack.a(IGrowable.n), new ady.g(10, 10), ItemLead.an, new ady.g(6, 10))}}}, {{{new ady.a(ItemLead.aS, new ady.g(24, 36)), new ady.b()}, {new ady.a(ItemLead.aT, new ady.g(8, 10)), new ady.e(ItemLead.aY, new ady.g(10, 12)), new ady.e(ItemStack.a(IGrowable.X), new ady.g(3, 4))}, {new ady.a(ItemLead.bY, new ady.g(2, 2)), new ady.e(ItemLead.ba, new ady.g(10, 12)), new ady.e(ItemStack.a(IGrowable.w), new ady.g(-5, -3))}, {new ady.b()}, {new ady.b()}, {new ady.e(ItemLead.cz, new ady.g(20, 22))}}, {{new ady.a(ItemLead.aS, new ady.g(24, 36))}, {new ady.a(ItemLead.aY, new ady.g(1, 1))}, {new ady.e(ItemLead.cg, new ady.g(7, 11))}, {new ady.h(new ady.g(12, 20), "Monument", beu.a.j), new ady.h(new ady.g(16, 28), "Mansion", beu.a.i)}}}, {{{new ady.a(ItemLead.bB, new ady.g(36, 40)), new ady.a(ItemLead.n, new ady.g(8, 10))}, {new ady.e(ItemLead.aF, new ady.g(-4, -1)), new ady.e(new Items(ItemLead.be, 1, ItemCloth.l.b()), new ady.g(-2, -1))}, {new ady.e(ItemLead.bC, new ady.g(4, 7)), new ady.e(ItemStack.a(IGrowable.aX), new ady.g(-3, -1))}, {new ady.e(ItemLead.bV, new ady.g(3, 11))}}}, {{{new ady.a(ItemLead.k, new ady.g(16, 24)), new ady.e(ItemLead.ab, new ady.g(4, 6))}, {new ady.a(ItemLead.m, new ady.g(7, 9)), new ady.e(ItemLead.ac, new ady.g(10, 14))}, {new ady.a(ItemLead.l, new ady.g(3, 4)), new ady.c(ItemLead.ag, new ady.g(16, 19))}, {new ady.e(ItemLead.aa, new ady.g(5, 7)), new ady.e(ItemLead.Z, new ady.g(9, 11)), new ady.e(ItemLead.X, new ady.g(5, 7)), new ady.e(ItemLead.Y, new ady.g(11, 15))}}, {{new ady.a(ItemLead.k, new ady.g(16, 24)), new ady.e(ItemLead.d, new ady.g(6, 8))}, {new ady.a(ItemLead.m, new ady.g(7, 9)), new ady.c(ItemLead.o, new ady.g(9, 10))}, {new ady.a(ItemLead.l, new ady.g(3, 4)), new ady.c(ItemLead.x, new ady.g(12, 15)), new ady.c(ItemLead.A, new ady.g(9, 12))}}, {{new ady.a(ItemLead.k, new ady.g(16, 24)), new ady.c(ItemLead.b, new ady.g(5, 7))}, {new ady.a(ItemLead.m, new ady.g(7, 9)), new ady.c(ItemLead.c, new ady.g(9, 11))}, {new ady.a(ItemLead.l, new ady.g(3, 4)), new ady.c(ItemLead.z, new ady.g(12, 15))}}}, {{{new ady.a(ItemLead.ao, new ady.g(14, 18)), new ady.a(ItemLead.bs, new ady.g(14, 18))}, {new ady.a(ItemLead.k, new ady.g(16, 24)), new ady.e(ItemLead.ap, new ady.g(-7, -5)), new ady.e(ItemLead.bt, new ady.g(-8, -6))}}, {{new ady.a(ItemLead.aN, new ady.g(9, 12)), new ady.e(ItemLead.V, new ady.g(2, 4))}, {new ady.c(ItemLead.U, new ady.g(7, 12))}, {new ady.e(ItemLead.aD, new ady.g(8, 10))}}}, {new ady.f[0][]}};

    public ady(IWorldEventListener report)
    {
        this(report, 0);
    }

    public ady(IWorldEventListener p_i1354_1_, int p_i1354_2_)
    {
        super(p_i1354_1_);
        this.bO = new ISnooperInfo("Items", false, 8);
        this.g(p_i1354_2_);
        this.a(0.6F, 1.95F);
        ((PathWorldListener)this.x()).a(true);
        this.m(true);
    }

    protected void r()
    {
        this.br.a(0, new EntityAIFollowOwnerFlying(this));
        this.br.a(1, new EntityAIBreakDoor(this, adt.class, 8.0F, 0.6D, 0.6D));
        this.br.a(1, new EntityAIBreakDoor(this, EntityGiantZombie.class, 12.0F, 0.8D, 0.8D));
        this.br.a(1, new EntityAIBreakDoor(this, EntityWitherSkeleton.class, 8.0F, 0.8D, 0.8D));
        this.br.a(1, new EntityAIBreakDoor(this, EntityWitch.class, 8.0F, 0.6D, 0.6D));
        this.br.a(1, new EntityAIWanderAvoidWaterFlying(this));
        this.br.a(1, new EntityAIAttackMelee(this));
        this.br.a(2, new EntityAIMoveToBlock(this));
        this.br.a(3, new EntityAIRunAroundLikeCrazy(this));
        this.br.a(4, new EntityAIPlay(this, true));
        this.br.a(5, new EntityAIOcelotAttack(this, 0.6D));
        this.br.a(6, new EntityAIMoveIndoors(this));
        this.br.a(7, new EntityAITradePlayer(this));
        this.br.a(9, new EntityAILeapAtTarget(this, RecipeItemHelper.class, 3.0F, 1.0F));
        this.br.a(9, new EntityAIWanderAvoidWater(this));
        this.br.a(9, new yp(this, 0.6D));
        this.br.a(10, new EntityAIVillagerMate(this, IEntityLivingData.class, 8.0F));
    }

    private void dw()
    {
        if (!this.bN)
        {
            this.bN = true;

            if (this.l_())
            {
                this.br.a(8, new EntityAILookIdle(this, 0.32D));
            }
            else if (this.dl() == 0)
            {
                this.br.a(6, new EntityAILandOnOwnersShoulder(this, 0.6D));
            }
        }
    }

    protected void p()
    {
        if (this.dl() == 0)
        {
            this.br.a(8, new EntityAILandOnOwnersShoulder(this, 0.6D));
        }

        super.p();
    }

    protected void bM()
    {
        super.bM();
        this.a(EntitySilverfish.d).a(0.5D);
    }

    protected void M()
    {
        if (--this.bA <= 0)
        {
            BlockPos blockpos = new BlockPos(this);
            this.l.ak().a(blockpos);
            this.bA = 70 + this.S.nextInt(50);
            this.bx = this.l.ak().a(blockpos, 32);

            if (this.bx == null)
            {
                this.di();
            }
            else
            {
                BlockPos blockpos1 = this.bx.a();
                this.a(blockpos1, this.bx.b());

                if (this.bM)
                {
                    this.bM = false;
                    this.bx.b(5);
                }
            }
        }

        if (!this.do() && this.bF > 0)
        {
            --this.bF;

            if (this.bF <= 0)
            {
                if (this.bG)
                {
                    for (amg amg : this.bE)
                    {
                        if (amg.h())
                        {
                            amg.a(this.S.nextInt(6) + this.S.nextInt(6) + 2);
                        }
                    }

                    this.dx();
                    this.bG = false;

                    if (this.bx != null && this.bJ != null)
                    {
                        this.l.a(this, (byte)14);
                        this.bx.a(this.bJ, 1);
                    }
                }

                this.c(new va(EntityAgeable.j, 200, 0));
            }
        }

        super.M();
    }

    public boolean a(RecipeItemHelper p_a_1_, EnumActionResult p_a_2_)
    {
        Items items = p_a_1_.b(p_a_2_);
        boolean flag = items.c() == ItemLead.cz;

        if (flag)
        {
            items.a(p_a_1_, this, p_a_2_);
            return true;
        }
        else if (!this.a(items, this.getClass()) && this.aC() && !this.do() && !this.l_())
        {
            if (this.bE == null)
            {
                this.dx();
            }

            if (p_a_2_ == EnumActionResult.SUCCESS)
            {
                p_a_1_.b(qs.F);
            }

            if (!this.l.G && !this.bE.isEmpty())
            {
                this.a_(p_a_1_);
                p_a_1_.a((MerchantRecipeList)this);
            }
            else if (this.bE.isEmpty())
            {
                return super.a(p_a_1_, p_a_2_);
            }

            return true;
        }
        else
        {
            return super.a(p_a_1_, p_a_2_);
        }
    }

    protected void i()
    {
        super.i();
        this.Y.a(bz, Integer.valueOf(0));
    }

    public static void a(IDataWalker p_a_0_)
    {
        IEntityLivingData.a(p_a_0_, ady.class);
        p_a_0_.a(DataFixer.e, new tn(ady.class, new String[] {"Inventory"}));
        p_a_0_.a(DataFixer.e, new BedItemColor()
        {
            public NBTTagCompound a(DataFixesManager p_a_1_, NBTTagCompound p_a_2_, int p_a_3_)
            {
                if (EntitySelectors.a(ady.class).equals(new BehaviorProjectileDispense(p_a_2_.getString("id"))) && p_a_2_.hasKey("Offers", 10))
                {
                    NBTTagCompound nbttagcompound = p_a_2_.getCompoundTag("Offers");

                    if (nbttagcompound.hasKey("Recipes", 9))
                    {
                        NBTTagList nbttaglist = nbttagcompound.getTagList("Recipes", 10);

                        for (int i = 0; i < nbttaglist.tagCount(); ++i)
                        {
                            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
                            AddBedTileEntity.a(p_a_1_, nbttagcompound1, p_a_3_, "buy");
                            AddBedTileEntity.a(p_a_1_, nbttagcompound1, p_a_3_, "buyB");
                            AddBedTileEntity.a(p_a_1_, nbttagcompound1, p_a_3_, "sell");
                            nbttaglist.set(i, nbttagcompound1);
                        }
                    }
                }

                return p_a_2_;
            }
        });
    }

    public void b(NBTTagCompound p_b_1_)
    {
        super.b(p_b_1_);
        p_b_1_.setInteger("Profession", this.dl());
        p_b_1_.setInteger("Riches", this.bI);
        p_b_1_.setInteger("Career", this.bK);
        p_b_1_.setInteger("CareerLevel", this.bL);
        p_b_1_.setBoolean("Willing", this.bH);

        if (this.bE != null)
        {
            p_b_1_.setTag("Offers", this.bE.a());
        }

        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.bO.getSizeInventory(); ++i)
        {
            Items items = this.bO.a(i);

            if (!items.b())
            {
                nbttaglist.appendTag(items.a(new NBTTagCompound()));
            }
        }

        p_b_1_.setTag("Inventory", nbttaglist);
    }

    public void readRecipiesFromTags(NBTTagCompound compound)
    {
        super.a(compound);
        this.g(compound.getInteger("Profession"));
        this.bI = compound.getInteger("Riches");
        this.bK = compound.getInteger("Career");
        this.bL = compound.getInteger("CareerLevel");
        this.bH = compound.getBoolean("Willing");

        if (compound.hasKey("Offers", 10))
        {
            NBTTagCompound nbttagcompound = compound.getCompoundTag("Offers");
            this.bE = new CommandBlockBaseLogic(nbttagcompound);
        }

        NBTTagList nbttaglist = compound.getTagList("Inventory", 10);

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            Items items = new Items(nbttaglist.getCompoundTagAt(i));

            if (!items.b())
            {
                this.bO.a(items);
            }
        }

        this.m(true);
        this.dw();
    }

    protected boolean K()
    {
        return false;
    }

    protected SoundCategory F()
    {
        return this.do() ? qf.io : qf.ik;
    }

    protected SoundCategory d(EntityDamageSourceIndirect p_d_1_)
    {
        return qf.im;
    }

    protected SoundCategory cf()
    {
        return qf.il;
    }

    @Nullable
    protected BehaviorProjectileDispense J()
    {
        return LootEntryItem.at;
    }

    public void g(int p_g_1_)
    {
        this.Y.b(bz, Integer.valueOf(p_g_1_));
    }

    public int dl()
    {
        return Math.max(((Integer)this.Y.a(bz)).intValue() % 6, 0);
    }

    public boolean dm()
    {
        return this.bB;
    }

    public void p(boolean p_p_1_)
    {
        this.bB = p_p_1_;
    }

    public void q(boolean p_q_1_)
    {
        this.bC = p_q_1_;
    }

    public boolean dn()
    {
        return this.bC;
    }

    public void a(@Nullable EnumCreatureType p_a_1_)
    {
        super.a(p_a_1_);

        if (this.bx != null && p_a_1_ != null)
        {
            this.bx.a(p_a_1_);

            if (p_a_1_ instanceof RecipeItemHelper)
            {
                int i = -1;

                if (this.l_())
                {
                    i = -3;
                }

                this.bx.a(p_a_1_.getName(), i);

                if (this.aC())
                {
                    this.l.a(this, (byte)13);
                }
            }
        }
    }

    public void a(EntityDamageSourceIndirect p_a_1_)
    {
        if (this.bx != null)
        {
            EntityList entitylist = p_a_1_.j();

            if (entitylist != null)
            {
                if (entitylist instanceof RecipeItemHelper)
                {
                    this.bx.a(entitylist.getName(), -2);
                }
                else if (entitylist instanceof EntityGhast)
                {
                    this.bx.h();
                }
            }
            else
            {
                RecipeItemHelper recipeitemhelper = this.l.a(this, 16.0D);

                if (recipeitemhelper != null)
                {
                    this.bx.h();
                }
            }
        }

        super.a(p_a_1_);
    }

    public void a_(@Nullable RecipeItemHelper p_a__1_)
    {
        this.bD = p_a__1_;
    }

    @Nullable
    public RecipeItemHelper t_()
    {
        return this.bD;
    }

    public boolean do()
    {
        return this.bD != null;
    }

    public boolean r(boolean p_r_1_)
    {
        if (!this.bH && p_r_1_ && this.dr())
        {
            boolean flag = false;

            for (int i = 0; i < this.bO.getSizeInventory(); ++i)
            {
                Items items = this.bO.a(i);

                if (!items.b())
                {
                    if (items.c() == ItemLead.S && items.E() >= 3)
                    {
                        flag = true;
                        this.bO.a(i, 3);
                    }
                    else if ((items.c() == ItemLead.cd || items.c() == ItemLead.cc) && items.E() >= 12)
                    {
                        flag = true;
                        this.bO.a(i, 12);
                    }
                }

                if (flag)
                {
                    this.l.a(this, (byte)18);
                    this.bH = true;
                    break;
                }
            }
        }

        return this.bH;
    }

    public void s(boolean p_s_1_)
    {
        this.bH = p_s_1_;
    }

    public void a(amg p_a_1_)
    {
        p_a_1_.g();
        this.a_ = -this.C();
        this.a(qf.ip, this.cq(), this.cr());
        int i = 3 + this.S.nextInt(4);

        if (p_a_1_.e() == 1 || this.S.nextInt(5) == 0)
        {
            this.bF = 40;
            this.bG = true;
            this.bH = true;

            if (this.bD != null)
            {
                this.bJ = this.bD.getName();
            }
            else
            {
                this.bJ = null;
            }

            i += 5;
        }

        if (p_a_1_.a().c() == ItemLead.bZ)
        {
            this.bI += p_a_1_.a().E();
        }

        if (p_a_1_.j())
        {
            this.l.a(new EnumHandSide(this.l, this.p, this.q + 0.5D, this.r, i));
        }

        if (this.bD instanceof EntityTrackerEntry)
        {
            CriteriaTriggers.field_192138_r.a((EntityTrackerEntry)this.bD, this, p_a_1_.d());
        }
    }

    public void a(Items p_a_1_)
    {
        if (!this.l.G && this.a_ > -this.C() + 20)
        {
            this.a_ = -this.C();
            this.a(p_a_1_.b() ? qf.in : qf.ip, this.cq(), this.cr());
        }
    }

    @Nullable
    public CommandBlockBaseLogic b_(RecipeItemHelper p_b__1_)
    {
        if (this.bE == null)
        {
            this.dx();
        }

        return this.bE;
    }

    private void dx()
    {
        ady.f[][][] aady$f = bP[this.dl()];

        if (this.bK != 0 && this.bL != 0)
        {
            ++this.bL;
        }
        else
        {
            this.bK = this.S.nextInt(aady$f.length) + 1;
            this.bL = 1;
        }

        if (this.bE == null)
        {
            this.bE = new CommandBlockBaseLogic();
        }

        int i = this.bK - 1;
        int j = this.bL - 1;

        if (i >= 0 && i < aady$f.length)
        {
            ady.f[][] aady$f1 = aady$f[i];

            if (j >= 0 && j < aady$f1.length)
            {
                ady.f[] aady$f2 = aady$f1[j];

                for (ady.f ady$f : aady$f2)
                {
                    ady$f.a(this, this.bE, this.S);
                }
            }
        }
    }

    public void a(@Nullable CommandBlockBaseLogic p_a_1_)
    {
    }

    public IWorldEventListener u_()
    {
        return this.l;
    }

    public BlockPos v_()
    {
        return new BlockPos(this);
    }

    /**
     * Get the formatted ChatComponent that will be used for the sender's username in chat
     */
    public ITextComponent getDisplayName()
    {
        ScoreCriteria scorecriteria = this.aY();
        String s = this.bq();

        if (s != null && !s.isEmpty())
        {
            TextComponentString textcomponentstring = new TextComponentString(bhh.a(scorecriteria, s));
            textcomponentstring.getStyle().setHoverEvent(this.bv());
            textcomponentstring.getStyle().setInsertion(this.bn());
            return textcomponentstring;
        }
        else
        {
            if (this.bE == null)
            {
                this.dx();
            }

            String s1 = null;

            switch (this.dl())
            {
                case 0:
                    if (this.bK == 1)
                    {
                        s1 = "farmer";
                    }
                    else if (this.bK == 2)
                    {
                        s1 = "fisherman";
                    }
                    else if (this.bK == 3)
                    {
                        s1 = "shepherd";
                    }
                    else if (this.bK == 4)
                    {
                        s1 = "fletcher";
                    }

                    break;

                case 1:
                    if (this.bK == 1)
                    {
                        s1 = "librarian";
                    }
                    else if (this.bK == 2)
                    {
                        s1 = "cartographer";
                    }

                    break;

                case 2:
                    s1 = "cleric";
                    break;

                case 3:
                    if (this.bK == 1)
                    {
                        s1 = "armor";
                    }
                    else if (this.bK == 2)
                    {
                        s1 = "weapon";
                    }
                    else if (this.bK == 3)
                    {
                        s1 = "tool";
                    }

                    break;

                case 4:
                    if (this.bK == 1)
                    {
                        s1 = "butcher";
                    }
                    else if (this.bK == 2)
                    {
                        s1 = "leather";
                    }

                    break;

                case 5:
                    s1 = "nitwit";
            }

            if (s1 != null)
            {
                ITextComponent itextcomponent = new TextComponentTranslation("entity.Villager." + s1, new Object[0]);
                itextcomponent.getStyle().setHoverEvent(this.bv());
                itextcomponent.getStyle().setInsertion(this.bn());

                if (scorecriteria != null)
                {
                    itextcomponent.getStyle().setColor(scorecriteria.m());
                }

                return itextcomponent;
            }
            else
            {
                return super.getDisplayName();
            }
        }
    }

    public float by()
    {
        return this.l_() ? 0.81F : 1.62F;
    }

    public void a(byte p_a_1_)
    {
        if (p_a_1_ == 12)
        {
            this.a(EnumParticleTypes.HEART);
        }
        else if (p_a_1_ == 13)
        {
            this.a(EnumParticleTypes.VILLAGER_ANGRY);
        }
        else if (p_a_1_ == 14)
        {
            this.a(EnumParticleTypes.VILLAGER_HAPPY);
        }
        else
        {
            super.a(p_a_1_);
        }
    }

    private void a(EnumParticleTypes p_a_1_)
    {
        for (int i = 0; i < 5; ++i)
        {
            double d0 = this.S.nextGaussian() * 0.02D;
            double d1 = this.S.nextGaussian() * 0.02D;
            double d2 = this.S.nextGaussian() * 0.02D;
            this.l.a(p_a_1_, this.p + (double)(this.S.nextFloat() * this.G * 2.0F) - (double)this.G, this.q + 1.0D + (double)(this.S.nextFloat() * this.H), this.r + (double)(this.S.nextFloat() * this.G * 2.0F) - (double)this.G, d0, d1, d2);
        }
    }

    @Nullable
    public EnumCreatureAttribute a(IInteractionObject p_a_1_, @Nullable EnumCreatureAttribute p_a_2_)
    {
        return this.a(p_a_1_, p_a_2_, true);
    }

    public EnumCreatureAttribute a(IInteractionObject p_a_1_, @Nullable EnumCreatureAttribute p_a_2_, boolean p_a_3_)
    {
        p_a_2_ = super.a(p_a_1_, p_a_2_);

        if (p_a_3_)
        {
            this.g(this.l.r.nextInt(6));
        }

        this.dw();
        this.dx();
        return p_a_2_;
    }

    public void dp()
    {
        this.bM = true;
    }

    public ady b(IAnimals p_b_1_)
    {
        ady ady = new ady(this.l);
        ady.a(this.l.D(new BlockPos(ady)), (EnumCreatureAttribute)null);
        return ady;
    }

    public boolean a(RecipeItemHelper p_a_1_)
    {
        return false;
    }

    public void a(EntityFallingBlock p_a_1_)
    {
        if (!this.l.G && !this.F)
        {
            EntityZombie entityzombie = new EntityZombie(this.l);
            entityzombie.b(this.p, this.q, this.r, this.v, this.w);
            entityzombie.a(this.l.D(new BlockPos(entityzombie)), (EnumCreatureAttribute)null);
            entityzombie.n(this.dc());

            if (this.n_())
            {
                entityzombie.c(this.bq());
                entityzombie.j(this.br());
            }

            this.l.a(entityzombie);
            this.X();
        }
    }

    public ISnooperInfo dq()
    {
        return this.bO;
    }

    protected void a(acl p_a_1_)
    {
        Items items = p_a_1_.k();
        ItemStack itemstack = items.c();

        if (this.a(itemstack))
        {
            Items items1 = this.bO.a(items);

            if (items1.b())
            {
                p_a_1_.X();
            }
            else
            {
                items.e(items1.E());
            }
        }
    }

    private boolean a(ItemStack p_a_1_)
    {
        return p_a_1_ == ItemLead.S || p_a_1_ == ItemLead.cd || p_a_1_ == ItemLead.cc || p_a_1_ == ItemLead.R || p_a_1_ == ItemLead.Q || p_a_1_ == ItemLead.cW || p_a_1_ == ItemLead.cV;
    }

    public boolean dr()
    {
        return this.m(1);
    }

    public boolean ds()
    {
        return this.m(2);
    }

    public boolean dt()
    {
        boolean flag = this.dl() == 0;

        if (flag)
        {
            return !this.m(5);
        }
        else
        {
            return !this.m(1);
        }
    }

    private boolean m(int p_m_1_)
    {
        boolean flag = this.dl() == 0;

        for (int i = 0; i < this.bO.getSizeInventory(); ++i)
        {
            Items items = this.bO.a(i);

            if (!items.b())
            {
                if (items.c() == ItemLead.S && items.E() >= 3 * p_m_1_ || items.c() == ItemLead.cd && items.E() >= 12 * p_m_1_ || items.c() == ItemLead.cc && items.E() >= 12 * p_m_1_ || items.c() == ItemLead.cW && items.E() >= 12 * p_m_1_)
                {
                    return true;
                }

                if (flag && items.c() == ItemLead.R && items.E() >= 9 * p_m_1_)
                {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean du()
    {
        for (int i = 0; i < this.bO.getSizeInventory(); ++i)
        {
            Items items = this.bO.a(i);

            if (!items.b() && (items.c() == ItemLead.Q || items.c() == ItemLead.cd || items.c() == ItemLead.cc || items.c() == ItemLead.cV))
            {
                return true;
            }
        }

        return false;
    }

    public boolean c(int p_c_1_, Items p_c_2_)
    {
        if (super.c(p_c_1_, p_c_2_))
        {
            return true;
        }
        else
        {
            int i = p_c_1_ - 300;

            if (i >= 0 && i < this.bO.getSizeInventory())
            {
                this.bO.a(i, p_c_2_);
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    static class a implements ady.f
    {
        public ItemStack a;
        public ady.g b;

        public a(ItemStack p_i1346_1_, ady.g p_i1346_2_)
        {
            this.a = p_i1346_1_;
            this.b = p_i1346_2_;
        }

        public void a(MerchantRecipeList p_a_1_, CommandBlockBaseLogic p_a_2_, Random p_a_3_)
        {
            int i = 1;

            if (this.b != null)
            {
                i = this.b.a(p_a_3_);
            }

            p_a_2_.add(new amg(new Items(this.a, i, 0), ItemLead.bZ));
        }
    }

    static class b implements ady.f
    {
        public void a(MerchantRecipeList p_a_1_, CommandBlockBaseLogic p_a_2_, Random p_a_3_)
        {
            EnchantmentHelper enchantmenthelper = (EnchantmentHelper)EnchantmentHelper.ENCHANTMENT_MODIFIER_LIVING.getRandomObject(p_a_3_);
            int i = IProgressUpdate.a(p_a_3_, enchantmenthelper.f(), enchantmenthelper.b());
            Items items = ItemEnderEye.a(new EnchantmentFireAspect(enchantmenthelper, i));
            int j = 2 + p_a_3_.nextInt(5 + i * 10) + 3 * i;

            if (enchantmenthelper.c())
            {
                j *= 2;
            }

            if (j > 64)
            {
                j = 64;
            }

            p_a_2_.add(new amg(new Items(ItemLead.aT), new Items(ItemLead.bZ, j), items));
        }
    }

    static class c implements ady.f
    {
        public Items a;
        public ady.g b;

        public c(ItemStack p_i1347_1_, ady.g p_i1347_2_)
        {
            this.a = new Items(p_i1347_1_);
            this.b = p_i1347_2_;
        }

        public void a(MerchantRecipeList p_a_1_, CommandBlockBaseLogic p_a_2_, Random p_a_3_)
        {
            int i = 1;

            if (this.b != null)
            {
                i = this.b.a(p_a_3_);
            }

            Items items = new Items(ItemLead.bZ, i, 0);
            Items items1 = Enchantments.a(p_a_3_, new Items(this.a.c(), 1, this.a.j()), 5 + p_a_3_.nextInt(15), false);
            p_a_2_.add(new amg(items, items1));
        }
    }

    static class d implements ady.f
    {
        public Items a;
        public ady.g b;
        public Items c;
        public ady.g d;

        public d(ItemStack descriptionIn, ady.g causeThrowable, ItemStack p_i1348_3_, ady.g p_i1348_4_)
        {
            this.a = new Items(descriptionIn);
            this.b = causeThrowable;
            this.c = new Items(p_i1348_3_);
            this.d = p_i1348_4_;
        }

        public void a(MerchantRecipeList p_a_1_, CommandBlockBaseLogic p_a_2_, Random p_a_3_)
        {
            int i = this.b.a(p_a_3_);
            int j = this.d.a(p_a_3_);
            p_a_2_.add(new amg(new Items(this.a.c(), i, this.a.j()), new Items(ItemLead.bZ), new Items(this.c.c(), j, this.c.j())));
        }
    }

    static class e implements ady.f
    {
        public Items a;
        public ady.g b;

        public e(ItemStack p_i1349_1_, ady.g p_i1349_2_)
        {
            this.a = new Items(p_i1349_1_);
            this.b = p_i1349_2_;
        }

        public e(Items p_i1350_1_, ady.g p_i1350_2_)
        {
            this.a = p_i1350_1_;
            this.b = p_i1350_2_;
        }

        public void a(MerchantRecipeList p_a_1_, CommandBlockBaseLogic p_a_2_, Random p_a_3_)
        {
            int i = 1;

            if (this.b != null)
            {
                i = this.b.a(p_a_3_);
            }

            Items items;
            Items items1;

            if (i < 0)
            {
                items = new Items(ItemLead.bZ);
                items1 = new Items(this.a.c(), -i, this.a.j());
            }
            else
            {
                items = new Items(ItemLead.bZ, i, 0);
                items1 = new Items(this.a.c(), 1, this.a.j());
            }

            p_a_2_.add(new amg(items, items1));
        }
    }

    interface f
    {
        void a(MerchantRecipeList var1, CommandBlockBaseLogic var2, Random var3);
    }

    static class g extends rr<Integer, Integer>
    {
        public g(int p_i1351_1_, int p_i1351_2_)
        {
            super(Integer.valueOf(p_i1351_1_), Integer.valueOf(p_i1351_2_));

            if (p_i1351_2_ < p_i1351_1_)
            {
                ady.by.warn("PriceRange({}, {}) invalid, {} smaller than {}", Integer.valueOf(p_i1351_1_), Integer.valueOf(p_i1351_2_), Integer.valueOf(p_i1351_2_), Integer.valueOf(p_i1351_1_));
            }
        }

        public int a(Random p_a_1_)
        {
            return ((Integer)this.a()).intValue() >= ((Integer)this.b()).intValue() ? ((Integer)this.a()).intValue() : ((Integer)this.a()).intValue() + p_a_1_.nextInt(((Integer)this.b()).intValue() - ((Integer)this.a()).intValue() + 1);
        }
    }

    static class h implements ady.f
    {
        public ady.g a;
        public String b;
        public beu.a c;

        public h(ady.g key, String value, beu.a p_i1352_3_)
        {
            this.a = key;
            this.b = value;
            this.c = p_i1352_3_;
        }

        public void a(MerchantRecipeList p_a_1_, CommandBlockBaseLogic p_a_2_, Random p_a_3_)
        {
            int i = this.a.a(p_a_3_);
            IWorldEventListener iworldeventlistener = p_a_1_.u_();
            BlockPos blockpos = iworldeventlistener.a(this.b, p_a_1_.v_(), true);

            if (blockpos != null)
            {
                Items items = ItemMinecart.a(iworldeventlistener, (double)blockpos.getX(), (double)blockpos.getZ(), (byte)2, true, true);
                ItemMinecart.a(iworldeventlistener, items);
                bev.a(items, blockpos, "+", this.c);
                items.f("filled_map." + this.b.toLowerCase(Locale.ROOT));
                p_a_2_.add(new amg(new Items(ItemLead.bZ, i), new Items(ItemLead.aY), items));
            }
        }
    }
}
