package net.minecraft.src;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.pattern.BlockMaterialMatcher;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.boss.dragon.phase.PhaseBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeVoidDecorator;
import net.minecraft.world.chunk.BlockStatePaletteLinear;
import net.minecraft.world.gen.feature.WorldGenEndPodium;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ayr
{
    private static final Logger a = LogManager.getLogger();
    private static final Predicate<EntityTrackerEntry> b = Predicates.<EntityTrackerEntry> and (EntityXPOrb.xpColor, EntityXPOrb.a(0.0D, 128.0D, 0.0D, 192.0D));
    private final WorldServer c = (WorldServer)(new WorldServer(new TextComponentTranslation("entity.EnderDragon.name", new Object[0]), IInventory.a.a, IInventory.b.a)).b(true).c(true);
    private final EntityPlayerMP d;
    private final List<Integer> e = Lists.<Integer>newArrayList();
    private final awx f;
    private int g;
    private int h;
    private int i;
    private int j;
    private boolean k;
    private boolean l;
    private UUID m;
    private boolean n = true;
    private BlockPos o;
    private WorldProviderEnd p;
    private int q;
    private List<abc> r;

    public ayr(EntityPlayerMP p_i1864_1_, NBTTagCompound p_i1864_2_)
    {
        this.d = p_i1864_1_;

        if (p_i1864_2_.hasKey("DragonKilled", 99))
        {
            if (p_i1864_2_.hasUniqueId("DragonUUID"))
            {
                this.m = p_i1864_2_.getUniqueId("DragonUUID");
            }

            this.k = p_i1864_2_.getBoolean("DragonKilled");
            this.l = p_i1864_2_.getBoolean("PreviouslyKilled");

            if (p_i1864_2_.getBoolean("IsRespawning"))
            {
                this.p = WorldProviderEnd.a;
            }

            if (p_i1864_2_.hasKey("ExitPortalLocation", 10))
            {
                this.o = NBTUtil.getPosFromTag(p_i1864_2_.getCompoundTag("ExitPortalLocation"));
            }
        }
        else
        {
            this.k = true;
            this.l = true;
        }

        if (p_i1864_2_.hasKey("Gateways", 9))
        {
            NBTTagList nbttaglist = p_i1864_2_.getTagList("Gateways", 3);

            for (int i = 0; i < nbttaglist.tagCount(); ++i)
            {
                this.e.add(Integer.valueOf(nbttaglist.getIntAt(i)));
            }
        }
        else
        {
            this.e.addAll(ContiguousSet.create(Range.closedOpen(Integer.valueOf(0), Integer.valueOf(20)), DiscreteDomain.integers()));
            Collections.shuffle(this.e, new Random(p_i1864_1_.Q()));
        }

        this.f = BlockMaterialMatcher.a().a("       ", "       ", "       ", "   #   ", "       ", "       ", "       ").a("       ", "       ", "       ", "   #   ", "       ", "       ", "       ").a("       ", "       ", "       ", "   #   ", "       ", "       ", "       ").a("  ###  ", " #   # ", "#     #", "#  #  #", "#     #", " #   # ", "  ###  ").a("       ", "  ###  ", " ##### ", " ##### ", " ##### ", "  ###  ", "       ").a('#', FactoryBlockPattern.a(axb.a(IGrowable.h))).b();
    }

    public NBTTagCompound a()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        if (this.m != null)
        {
            nbttagcompound.setUniqueId("DragonUUID", this.m);
        }

        nbttagcompound.setBoolean("DragonKilled", this.k);
        nbttagcompound.setBoolean("PreviouslyKilled", this.l);

        if (this.o != null)
        {
            nbttagcompound.setTag("ExitPortalLocation", NBTUtil.createPosTag(this.o));
        }

        NBTTagList nbttaglist = new NBTTagList();
        Iterator iterator = this.e.iterator();

        while (iterator.hasNext())
        {
            int i = ((Integer)iterator.next()).intValue();
            nbttaglist.appendTag(new NBTTagInt(i));
        }

        nbttagcompound.setTag("Gateways", nbttaglist);
        return nbttagcompound;
    }

    public void b()
    {
        this.c.d(!this.k);

        if (++this.j >= 20)
        {
            this.j();
            this.j = 0;
        }

        if (!this.c.c().isEmpty())
        {
            if (this.n)
            {
                a.info("Scanning for legacy world dragon fight...");
                this.i();
                this.n = false;
                boolean flag = this.g();

                if (flag)
                {
                    a.info("Found that the dragon has been killed in this world already.");
                    this.l = true;
                }
                else
                {
                    a.info("Found that the dragon has not yet been killed in this world.");
                    this.l = false;
                    this.a(false);
                }

                List<PhaseBase> list = this.d.a(PhaseBase.class, EntityXPOrb.xpColor);

                if (list.isEmpty())
                {
                    this.k = true;
                }
                else
                {
                    PhaseBase phasebase = list.get(0);
                    this.m = phasebase.bm();
                    a.info("Found that there's a dragon still alive ({})", (Object)phasebase);
                    this.k = false;

                    if (!flag)
                    {
                        a.info("But we didn't have a portal, let's remove it.");
                        phasebase.X();
                        this.m = null;
                    }
                }

                if (!this.l && this.k)
                {
                    this.k = false;
                }
            }

            if (this.p != null)
            {
                if (this.r == null)
                {
                    this.p = null;
                    this.e();
                }

                this.p.a(this.d, this, this.r, this.q++, this.o);
            }

            if (!this.k)
            {
                if (this.m == null || ++this.g >= 1200)
                {
                    this.i();
                    List<PhaseBase> list1 = this.d.a(PhaseBase.class, EntityXPOrb.xpColor);

                    if (list1.isEmpty())
                    {
                        a.debug("Haven't seen the dragon, respawning it");
                        this.m();
                    }
                    else
                    {
                        a.debug("Haven't seen our dragon, but found another one to use.");
                        this.m = ((PhaseBase)list1.get(0)).bm();
                    }

                    this.g = 0;
                }

                if (++this.i >= 100)
                {
                    this.k();
                    this.i = 0;
                }
            }
        }
    }

    protected void a(WorldProviderEnd p_a_1_)
    {
        if (this.p == null)
        {
            throw new IllegalStateException("Dragon respawn isn't in progress, can't skip ahead in the animation.");
        }
        else
        {
            this.q = 0;

            if (p_a_1_ == WorldProviderEnd.e)
            {
                this.p = null;
                this.k = false;
                PhaseBase phasebase = this.m();

                for (EntityTrackerEntry entitytrackerentry : this.c.c())
                {
                    CriteriaTriggers.field_192133_m.a(entitytrackerentry, phasebase);
                }
            }
            else
            {
                this.p = p_a_1_;
            }
        }
    }

    private boolean g()
    {
        for (int i = -8; i <= 8; ++i)
        {
            for (int j = -8; j <= 8; ++j)
            {
                BlockStatePaletteLinear blockstatepalettelinear = this.d.a(i, j);

                for (TileEntityChest tileentitychest : blockstatepalettelinear.s().values())
                {
                    if (tileentitychest instanceof awg)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Nullable
    private awx.b h()
    {
        for (int i = -8; i <= 8; ++i)
        {
            for (int j = -8; j <= 8; ++j)
            {
                BlockStatePaletteLinear blockstatepalettelinear = this.d.a(i, j);

                for (TileEntityChest tileentitychest : blockstatepalettelinear.s().values())
                {
                    if (tileentitychest instanceof awg)
                    {
                        awx.b awx$b = this.f.a(this.d, tileentitychest.w());

                        if (awx$b != null)
                        {
                            BlockPos blockpos = awx$b.a(3, 3, 3).d();

                            if (this.o == null && blockpos.getX() == 0 && blockpos.getZ() == 0)
                            {
                                this.o = blockpos;
                            }

                            return awx$b;
                        }
                    }
                }
            }
        }

        int k = this.d.l(WorldGenFlowers.flower).getY();

        for (int l = k; l >= 0; --l)
        {
            awx.b awx$b = this.f.a(this.d, new BlockPos(WorldGenFlowers.flower.getX(), l, WorldGenFlowers.flower.getZ()));

            if (awx$b != null)
            {
                if (this.o == null)
                {
                    this.o = awx$b.a(3, 3, 3).d();
                }

                return awx$b;
            }
        }

        return null;
    }

    private void i()
    {
        for (int i = -8; i <= 8; ++i)
        {
            for (int j = -8; j <= 8; ++j)
            {
                this.d.a(i, j);
            }
        }
    }

    private void j()
    {
        Set<EntityTrackerEntry> set = Sets.<EntityTrackerEntry>newHashSet();

        for (EntityTrackerEntry entitytrackerentry : this.d.b(EntityTrackerEntry.class, b))
        {
            this.c.a(entitytrackerentry);
            set.add(entitytrackerentry);
        }

        Set<EntityTrackerEntry> set1 = Sets.newHashSet(this.c.c());
        set1.removeAll(set);

        for (EntityTrackerEntry entitytrackerentry1 : set1)
        {
            this.c.b(entitytrackerentry1);
        }
    }

    private void k()
    {
        this.i = 0;
        this.h = 0;

        for (WorldGenTaiga2.a worldgentaiga2$a : BiomeVoidDecorator.a(this.d))
        {
            this.h += this.d.a(abc.class, worldgentaiga2$a.f()).size();
        }

        a.debug("Found {} end crystals still alive", (int)this.h);
    }

    public void a(PhaseBase p_a_1_)
    {
        if (p_a_1_.bm().equals(this.m))
        {
            this.c.a(0.0F);
            this.c.d(false);
            this.a(true);
            this.l();

            if (!this.l)
            {
                this.d.a(this.d.l(WorldGenFlowers.flower), IGrowable.bI.t());
            }

            this.l = true;
            this.k = true;
        }
    }

    private void l()
    {
        if (!this.e.isEmpty())
        {
            int i = ((Integer)this.e.remove(this.e.size() - 1)).intValue();
            int j = (int)(96.0D * Math.cos(2.0D * (-Math.PI + 0.15707963267948966D * (double)i)));
            int k = (int)(96.0D * Math.sin(2.0D * (-Math.PI + 0.15707963267948966D * (double)i)));
            this.a(new BlockPos(j, 75, k));
        }
    }

    private void a(BlockPos p_a_1_)
    {
        this.d.sendBlockBreakProgress(3000, p_a_1_, 0);
        (new WorldGenEndPodium()).b(this.d, new Random(), p_a_1_);
    }

    private void a(boolean p_a_1_)
    {
        WorldGenFlowers worldgenflowers = new WorldGenFlowers(p_a_1_);

        if (this.o == null)
        {
            for (this.o = this.d.q(WorldGenFlowers.flower).down(); this.d.o(this.o).u() == IGrowable.h && this.o.getY() > this.d.M(); this.o = this.o.down())
            {
                ;
            }
        }

        worldgenflowers.b(this.d, new Random(), this.o);
    }

    private PhaseBase m()
    {
        this.d.f(new BlockPos(0, 128, 0));
        PhaseBase phasebase = new PhaseBase(this.d);
        phasebase.de().a(abt.a);
        phasebase.b(0.0D, 128.0D, 0.0D, this.d.r.nextFloat() * 360.0F, 0.0F);
        this.d.a(phasebase);
        this.m = phasebase.bm();
        return phasebase;
    }

    public void b(PhaseBase p_b_1_)
    {
        if (p_b_1_.bm().equals(this.m))
        {
            this.c.a(p_b_1_.cd() / p_b_1_.cj());
            this.g = 0;

            if (p_b_1_.n_())
            {
                this.c.a(p_b_1_.getDisplayName());
            }
        }
    }

    public int c()
    {
        return this.h;
    }

    public void a(abc p_a_1_, EntityDamageSourceIndirect p_a_2_)
    {
        if (this.p != null && this.r.contains(p_a_1_))
        {
            a.debug("Aborting respawn sequence");
            this.p = null;
            this.q = 0;
            this.f();
            this.a(true);
        }
        else
        {
            this.k();
            EntityList entitylist = this.d.a(this.m);

            if (entitylist instanceof PhaseBase)
            {
                ((PhaseBase)entitylist).a(p_a_1_, new BlockPos(p_a_1_), p_a_2_);
            }
        }
    }

    public boolean d()
    {
        return this.l;
    }

    public void e()
    {
        if (this.k && this.p == null)
        {
            BlockPos blockpos = this.o;

            if (blockpos == null)
            {
                a.debug("Tried to respawn, but need to find the portal first.");
                awx.b awx$b = this.h();

                if (awx$b == null)
                {
                    a.debug("Couldn't find a portal, so we made one.");
                    this.a(true);
                }
                else
                {
                    a.debug("Found the exit portal & temporarily using it.");
                }

                blockpos = this.o;
            }

            List<abc> list1 = Lists.<abc>newArrayList();
            BlockPos blockpos1 = blockpos.up(1);

            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
            {
                List<abc> list = this.d.a(abc.class, new Vec2f(blockpos1.offset(enumfacing, 2)));

                if (list.isEmpty())
                {
                    return;
                }

                list1.addAll(list);
            }

            a.debug("Found all crystals, respawning dragon.");
            this.a(list1);
        }
    }

    private void a(List<abc> p_a_1_)
    {
        if (this.k && this.p == null)
        {
            for (awx.b awx$b = this.h(); awx$b != null; awx$b = this.h())
            {
                for (int i = 0; i < this.f.c(); ++i)
                {
                    for (int j = 0; j < this.f.b(); ++j)
                    {
                        for (int k = 0; k < this.f.a(); ++k)
                        {
                            FactoryBlockPattern factoryblockpattern = awx$b.a(i, j, k);

                            if (factoryblockpattern.a().u() == IGrowable.h || factoryblockpattern.a().u() == IGrowable.bF)
                            {
                                this.d.a(factoryblockpattern.d(), IGrowable.bH.t());
                            }
                        }
                    }
                }
            }

            this.p = WorldProviderEnd.a;
            this.q = 0;
            this.a(false);
            this.r = p_a_1_;
        }
    }

    public void f()
    {
        for (WorldGenTaiga2.a worldgentaiga2$a : BiomeVoidDecorator.a(this.d))
        {
            for (abc abc : this.d.a(abc.class, worldgentaiga2$a.f()))
            {
                abc.i(false);
                abc.a((BlockPos)null);
            }
        }
    }
}
