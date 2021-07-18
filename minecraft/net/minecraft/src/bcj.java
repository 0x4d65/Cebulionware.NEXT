package net.minecraft.src;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.BlockBone;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.IGrowable;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEndPortal;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.datafix.IDataWalker;
import net.minecraft.util.datafix.fixes.BedItemColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.world.IWorldEventListener;
import net.minecraft.world.gen.structure.StructureEndCityPieces;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

public class bcj
{
    private final List<bcj.b> a = Lists.<bcj.b>newArrayList();
    private final List<bcj.c> b = Lists.<bcj.c>newArrayList();
    private BlockPos c = BlockPos.ORIGIN;
    private String d = "?";

    public BlockPos a()
    {
        return this.c;
    }

    public void a(String p_a_1_)
    {
        this.d = p_a_1_;
    }

    public String b()
    {
        return this.d;
    }

    public void a(IWorldEventListener p_a_1_, BlockPos p_a_2_, BlockPos p_a_3_, boolean p_a_4_, @Nullable BlockBone p_a_5_)
    {
        if (p_a_3_.getX() >= 1 && p_a_3_.getY() >= 1 && p_a_3_.getZ() >= 1)
        {
            BlockPos blockpos = p_a_2_.add(p_a_3_).add(-1, -1, -1);
            List<bcj.b> list = Lists.<bcj.b>newArrayList();
            List<bcj.b> list1 = Lists.<bcj.b>newArrayList();
            List<bcj.b> list2 = Lists.<bcj.b>newArrayList();
            BlockPos blockpos1 = new BlockPos(Math.min(p_a_2_.getX(), blockpos.getX()), Math.min(p_a_2_.getY(), blockpos.getY()), Math.min(p_a_2_.getZ(), blockpos.getZ()));
            BlockPos blockpos2 = new BlockPos(Math.max(p_a_2_.getX(), blockpos.getX()), Math.max(p_a_2_.getY(), blockpos.getY()), Math.max(p_a_2_.getZ(), blockpos.getZ()));
            this.c = p_a_3_;

            for (BlockPos.MutableBlockPos blockpos$mutableblockpos : BlockPos.getAllInBoxMutable(blockpos1, blockpos2))
            {
                BlockPos blockpos3 = blockpos$mutableblockpos.subtract(blockpos1);
                awt awt = p_a_1_.o(blockpos$mutableblockpos);

                if (p_a_5_ == null || p_a_5_ != awt.u())
                {
                    TileEntityChest tileentitychest = p_a_1_.r(blockpos$mutableblockpos);

                    if (tileentitychest != null)
                    {
                        NBTTagCompound nbttagcompound = tileentitychest.writeToNBT(new NBTTagCompound());
                        nbttagcompound.removeTag("x");
                        nbttagcompound.removeTag("y");
                        nbttagcompound.removeTag("z");
                        list1.add(new bcj.b(blockpos3, awt, nbttagcompound));
                    }
                    else if (!awt.isFullBlock() && !awt.isFullCube())
                    {
                        list2.add(new bcj.b(blockpos3, awt, (NBTTagCompound)null));
                    }
                    else
                    {
                        list.add(new bcj.b(blockpos3, awt, (NBTTagCompound)null));
                    }
                }
            }

            this.a.clear();
            this.a.addAll(list);
            this.a.addAll(list1);
            this.a.addAll(list2);

            if (p_a_4_)
            {
                this.a(p_a_1_, blockpos1, blockpos2.add(1, 1, 1));
            }
            else
            {
                this.b.clear();
            }
        }
    }

    private void a(IWorldEventListener p_a_1_, BlockPos p_a_2_, BlockPos p_a_3_)
    {
        List<EntityList> list = p_a_1_.a(EntityList.class, new Vec2f(p_a_2_, p_a_3_), new Predicate<EntityList>()
        {
            public boolean a(@Nullable EntityList p_a_1_)
            {
                return !(p_a_1_ instanceof RecipeItemHelper);
            }
        });
        this.b.clear();

        for (EntityList entitylist : list)
        {
            ScoreObjective scoreobjective = new ScoreObjective(entitylist.p - (double)p_a_2_.getX(), entitylist.q - (double)p_a_2_.getY(), entitylist.r - (double)p_a_2_.getZ());
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            entitylist.d(nbttagcompound);
            BlockPos blockpos;

            if (entitylist instanceof EntityFishHook)
            {
                blockpos = ((EntityFishHook)entitylist).q().subtract(p_a_2_);
            }
            else
            {
                blockpos = new BlockPos(scoreobjective);
            }

            this.b.add(new bcj.c(scoreobjective, blockpos, nbttagcompound));
        }
    }

    public Map<BlockPos, String> a(BlockPos p_a_1_, Template p_a_2_)
    {
        Map<BlockPos, String> map = Maps.<BlockPos, String>newHashMap();
        StructureEndCityPieces structureendcitypieces = p_a_2_.i();

        for (bcj.b bcj$b : this.a)
        {
            BlockPos blockpos = a(p_a_2_, bcj$b.a).add(p_a_1_);

            if (structureendcitypieces == null || structureendcitypieces.b(blockpos))
            {
                awt awt = bcj$b.b;

                if (awt.u() == IGrowable.dT && bcj$b.c != null)
                {
                    TileEntityEndPortal.a tileentityendportal$a = TileEntityEndPortal.a.valueOf(bcj$b.c.getString("mode"));

                    if (tileentityendportal$a == TileEntityEndPortal.a.d)
                    {
                        map.put(blockpos, bcj$b.c.getString("metadata"));
                    }
                }
            }
        }

        return map;
    }

    public BlockPos a(Template p_a_1_, BlockPos p_a_2_, Template p_a_3_, BlockPos p_a_4_)
    {
        BlockPos blockpos = a(p_a_1_, p_a_2_);
        BlockPos blockpos1 = a(p_a_3_, p_a_4_);
        return blockpos.subtract(blockpos1);
    }

    public static BlockPos a(Template p_a_0_, BlockPos p_a_1_)
    {
        return b(p_a_1_, p_a_0_.b(), p_a_0_.c());
    }

    public void a(IWorldEventListener p_a_1_, BlockPos p_a_2_, Template p_a_3_)
    {
        p_a_3_.k();
        this.b(p_a_1_, p_a_2_, p_a_3_);
    }

    public void b(IWorldEventListener p_b_1_, BlockPos p_b_2_, Template p_b_3_)
    {
        this.a(p_b_1_, p_b_2_, new PlacementSettings(p_b_2_, p_b_3_), p_b_3_, 2);
    }

    public void a(IWorldEventListener p_a_1_, BlockPos p_a_2_, Template p_a_3_, int p_a_4_)
    {
        this.a(p_a_1_, p_a_2_, new PlacementSettings(p_a_2_, p_a_3_), p_a_3_, p_a_4_);
    }

    public void a(IWorldEventListener p_a_1_, BlockPos p_a_2_, @Nullable bci p_a_3_, Template p_a_4_, int p_a_5_)
    {
        if ((!this.a.isEmpty() || !p_a_4_.g() && !this.b.isEmpty()) && this.c.getX() >= 1 && this.c.getY() >= 1 && this.c.getZ() >= 1)
        {
            BlockBone blockbone = p_a_4_.h();
            StructureEndCityPieces structureendcitypieces = p_a_4_.i();

            for (bcj.b bcj$b : this.a)
            {
                BlockPos blockpos = a(p_a_4_, bcj$b.a).add(p_a_2_);
                bcj.b bcj$b = p_a_3_ != null ? p_a_3_.a(p_a_1_, blockpos, bcj$b) : bcj$b;

                if (bcj$b != null)
                {
                    BlockBone blockbone1 = bcj$b.b.u();

                    if ((blockbone == null || blockbone != blockbone1) && (!p_a_4_.j() || blockbone1 != IGrowable.dT) && (structureendcitypieces == null || structureendcitypieces.b(blockpos)))
                    {
                        awt awt = bcj$b.b.a(p_a_4_.b());
                        awt awt = awt.a(p_a_4_.c());

                        if (bcj$b.c != null)
                        {
                            TileEntityChest tileentitychest = p_a_1_.r(blockpos);

                            if (tileentitychest != null)
                            {
                                if (tileentitychest instanceof IInventoryChangedListener)
                                {
                                    ((IInventoryChangedListener)tileentitychest).clear();
                                }

                                p_a_1_.a(blockpos, IGrowable.cv.t(), 4);
                            }
                        }

                        if (p_a_1_.a(blockpos, awt, p_a_5_) && bcj$b.c != null)
                        {
                            TileEntityChest tileentitychest2 = p_a_1_.r(blockpos);

                            if (tileentitychest2 != null)
                            {
                                bcj$b.c.setInteger("x", blockpos.getX());
                                bcj$b.c.setInteger("y", blockpos.getY());
                                bcj$b.c.setInteger("z", blockpos.getZ());
                                tileentitychest2.readFromNBT(bcj$b.c);
                                tileentitychest2.a(p_a_4_.b());
                                tileentitychest2.a(p_a_4_.c());
                            }
                        }
                    }
                }
            }

            for (bcj.b bcj$b : this.a)
            {
                if (blockbone == null || blockbone != bcj$b.b.u())
                {
                    BlockPos blockpos1 = a(p_a_4_, bcj$b.a).add(p_a_2_);

                    if (structureendcitypieces == null || structureendcitypieces.b(blockpos1))
                    {
                        p_a_1_.a(blockpos1, bcj$b.b.u(), false);

                        if (bcj$b.c != null)
                        {
                            TileEntityChest tileentitychest1 = p_a_1_.r(blockpos1);

                            if (tileentitychest1 != null)
                            {
                                tileentitychest1.y_();
                            }
                        }
                    }
                }
            }

            if (!p_a_4_.g())
            {
                this.a(p_a_1_, p_a_2_, p_a_4_.b(), p_a_4_.c(), structureendcitypieces);
            }
        }
    }

    private void a(IWorldEventListener p_a_1_, BlockPos p_a_2_, BlockSilverfish p_a_3_, BlockSandStone p_a_4_, @Nullable StructureEndCityPieces p_a_5_)
    {
        for (bcj.c bcj$c : this.b)
        {
            BlockPos blockpos = b(bcj$c.b, p_a_3_, p_a_4_).add(p_a_2_);

            if (p_a_5_ == null || p_a_5_.b(blockpos))
            {
                NBTTagCompound nbttagcompound = bcj$c.c;
                ScoreObjective scoreobjective = a(bcj$c.a, p_a_3_, p_a_4_);
                ScoreObjective scoreobjective1 = scoreobjective.b((double)p_a_2_.getX(), (double)p_a_2_.getY(), (double)p_a_2_.getZ());
                NBTTagList nbttaglist = new NBTTagList();
                nbttaglist.appendTag(new NBTTagDouble(scoreobjective1.name));
                nbttaglist.appendTag(new NBTTagDouble(scoreobjective1.objectiveCriteria));
                nbttaglist.appendTag(new NBTTagDouble(scoreobjective1.renderType));
                nbttagcompound.setTag("Pos", nbttaglist);
                nbttagcompound.setUniqueId("UUID", UUID.randomUUID());
                EntityList entitylist;

                try
                {
                    entitylist = EntitySelectors.a(nbttagcompound, p_a_1_);
                }
                catch (Exception var15)
                {
                    entitylist = null;
                }

                if (entitylist != null)
                {
                    float f = entitylist.a(p_a_3_);
                    f = f + (entitylist.v - entitylist.a(p_a_4_));
                    entitylist.b(scoreobjective1.name, scoreobjective1.objectiveCriteria, scoreobjective1.renderType, f, entitylist.w);
                    p_a_1_.a(entitylist);
                }
            }
        }
    }

    public BlockPos a(BlockSandStone p_a_1_)
    {
        switch (p_a_1_)
        {
            case d:
            case b:
                return new BlockPos(this.c.getZ(), this.c.getY(), this.c.getX());

            default:
                return this.c;
        }
    }

    private static BlockPos b(BlockPos p_b_0_, BlockSilverfish p_b_1_, BlockSandStone p_b_2_)
    {
        int i = p_b_0_.getX();
        int j = p_b_0_.getY();
        int k = p_b_0_.getZ();
        boolean flag = true;

        switch (p_b_1_)
        {
            case b:
                k = -k;
                break;

            case c:
                i = -i;
                break;

            default:
                flag = false;
        }

        switch (p_b_2_)
        {
            case d:
                return new BlockPos(k, j, -i);

            case b:
                return new BlockPos(-k, j, i);

            case c:
                return new BlockPos(-i, j, -k);

            default:
                return flag ? new BlockPos(i, j, k) : p_b_0_;
        }
    }

    private static ScoreObjective a(ScoreObjective p_a_0_, BlockSilverfish p_a_1_, BlockSandStone p_a_2_)
    {
        double d0 = p_a_0_.name;
        double d1 = p_a_0_.objectiveCriteria;
        double d2 = p_a_0_.renderType;
        boolean flag = true;

        switch (p_a_1_)
        {
            case b:
                d2 = 1.0D - d2;
                break;

            case c:
                d0 = 1.0D - d0;
                break;

            default:
                flag = false;
        }

        switch (p_a_2_)
        {
            case d:
                return new ScoreObjective(d2, d1, 1.0D - d0);

            case b:
                return new ScoreObjective(1.0D - d2, d1, d0);

            case c:
                return new ScoreObjective(1.0D - d0, d1, 1.0D - d2);

            default:
                return flag ? new ScoreObjective(d0, d1, d2) : p_a_0_;
        }
    }

    public BlockPos a(BlockPos p_a_1_, BlockSilverfish p_a_2_, BlockSandStone p_a_3_)
    {
        return a(p_a_1_, p_a_2_, p_a_3_, this.a().getX(), this.a().getZ());
    }

    public static BlockPos a(BlockPos p_a_0_, BlockSilverfish p_a_1_, BlockSandStone p_a_2_, int p_a_3_, int p_a_4_)
    {
        --p_a_3_;
        --p_a_4_;
        int i = p_a_1_ == BlockSilverfish.c ? p_a_3_ : 0;
        int j = p_a_1_ == BlockSilverfish.b ? p_a_4_ : 0;
        BlockPos blockpos = p_a_0_;

        switch (p_a_2_)
        {
            case d:
                blockpos = p_a_0_.add(j, 0, p_a_3_ - i);
                break;

            case b:
                blockpos = p_a_0_.add(p_a_4_ - j, 0, i);
                break;

            case c:
                blockpos = p_a_0_.add(p_a_3_ - i, 0, p_a_4_ - j);
                break;

            case TYPE:
                blockpos = p_a_0_.add(i, 0, j);
        }

        return blockpos;
    }

    public static void a(IDataWalker p_a_0_)
    {
        p_a_0_.a(DataFixer.h, new BedItemColor()
        {
            public NBTTagCompound a(DataFixesManager p_a_1_, NBTTagCompound p_a_2_, int p_a_3_)
            {
                if (p_a_2_.hasKey("entities", 9))
                {
                    NBTTagList nbttaglist = p_a_2_.getTagList("entities", 10);

                    for (int i = 0; i < nbttaglist.tagCount(); ++i)
                    {
                        NBTTagCompound nbttagcompound = (NBTTagCompound)nbttaglist.get(i);

                        if (nbttagcompound.hasKey("nbt", 10))
                        {
                            nbttagcompound.setTag("nbt", p_a_1_.a(DataFixer.e, nbttagcompound.getCompoundTag("nbt"), p_a_3_));
                        }
                    }
                }

                if (p_a_2_.hasKey("blocks", 9))
                {
                    NBTTagList nbttaglist1 = p_a_2_.getTagList("blocks", 10);

                    for (int j = 0; j < nbttaglist1.tagCount(); ++j)
                    {
                        NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist1.get(j);

                        if (nbttagcompound1.hasKey("nbt", 10))
                        {
                            nbttagcompound1.setTag("nbt", p_a_1_.a(DataFixer.version, nbttagcompound1.getCompoundTag("nbt"), p_a_3_));
                        }
                    }
                }

                return p_a_2_;
            }
        });
    }

    public NBTTagCompound a(NBTTagCompound p_a_1_)
    {
        bcj.a bcj$a = new bcj.a();
        NBTTagList nbttaglist = new NBTTagList();

        for (bcj.b bcj$b : this.a)
        {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.setTag("pos", this.a(bcj$b.a.getX(), bcj$b.a.getY(), bcj$b.a.getZ()));
            nbttagcompound.setInteger("state", bcj$a.a(bcj$b.b));

            if (bcj$b.c != null)
            {
                nbttagcompound.setTag("nbt", bcj$b.c);
            }

            nbttaglist.appendTag(nbttagcompound);
        }

        NBTTagList nbttaglist1 = new NBTTagList();

        for (bcj.c bcj$c : this.b)
        {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setTag("pos", this.a(bcj$c.a.name, bcj$c.a.objectiveCriteria, bcj$c.a.renderType));
            nbttagcompound1.setTag("blockPos", this.a(bcj$c.b.getX(), bcj$c.b.getY(), bcj$c.b.getZ()));

            if (bcj$c.c != null)
            {
                nbttagcompound1.setTag("nbt", bcj$c.c);
            }

            nbttaglist1.appendTag(nbttagcompound1);
        }

        NBTTagList nbttaglist2 = new NBTTagList();

        for (awt awt : bcj$a)
        {
            nbttaglist2.appendTag(NBTUtil.a(new NBTTagCompound(), awt));
        }

        p_a_1_.setTag("palette", nbttaglist2);
        p_a_1_.setTag("blocks", nbttaglist);
        p_a_1_.setTag("entities", nbttaglist1);
        p_a_1_.setTag("size", this.a(this.c.getX(), this.c.getY(), this.c.getZ()));
        p_a_1_.setString("author", this.d);
        p_a_1_.setInteger("DataVersion", 1343);
        return p_a_1_;
    }

    public void b(NBTTagCompound p_b_1_)
    {
        this.a.clear();
        this.b.clear();
        NBTTagList nbttaglist = p_b_1_.getTagList("size", 3);
        this.c = new BlockPos(nbttaglist.getIntAt(0), nbttaglist.getIntAt(1), nbttaglist.getIntAt(2));
        this.d = p_b_1_.getString("author");
        bcj.a bcj$a = new bcj.a();
        NBTTagList nbttaglist1 = p_b_1_.getTagList("palette", 10);

        for (int i = 0; i < nbttaglist1.tagCount(); ++i)
        {
            bcj$a.a(NBTUtil.d(nbttaglist1.getCompoundTagAt(i)), i);
        }

        NBTTagList nbttaglist3 = p_b_1_.getTagList("blocks", 10);

        for (int j = 0; j < nbttaglist3.tagCount(); ++j)
        {
            NBTTagCompound nbttagcompound = nbttaglist3.getCompoundTagAt(j);
            NBTTagList nbttaglist2 = nbttagcompound.getTagList("pos", 3);
            BlockPos blockpos = new BlockPos(nbttaglist2.getIntAt(0), nbttaglist2.getIntAt(1), nbttaglist2.getIntAt(2));
            awt awt = bcj$a.a(nbttagcompound.getInteger("state"));
            NBTTagCompound nbttagcompound1;

            if (nbttagcompound.hasKey("nbt"))
            {
                nbttagcompound1 = nbttagcompound.getCompoundTag("nbt");
            }
            else
            {
                nbttagcompound1 = null;
            }

            this.a.add(new bcj.b(blockpos, awt, nbttagcompound1));
        }

        NBTTagList nbttaglist4 = p_b_1_.getTagList("entities", 10);

        for (int k = 0; k < nbttaglist4.tagCount(); ++k)
        {
            NBTTagCompound nbttagcompound3 = nbttaglist4.getCompoundTagAt(k);
            NBTTagList nbttaglist5 = nbttagcompound3.getTagList("pos", 6);
            ScoreObjective scoreobjective = new ScoreObjective(nbttaglist5.getDoubleAt(0), nbttaglist5.getDoubleAt(1), nbttaglist5.getDoubleAt(2));
            NBTTagList nbttaglist6 = nbttagcompound3.getTagList("blockPos", 3);
            BlockPos blockpos1 = new BlockPos(nbttaglist6.getIntAt(0), nbttaglist6.getIntAt(1), nbttaglist6.getIntAt(2));

            if (nbttagcompound3.hasKey("nbt"))
            {
                NBTTagCompound nbttagcompound2 = nbttagcompound3.getCompoundTag("nbt");
                this.b.add(new bcj.c(scoreobjective, blockpos1, nbttagcompound2));
            }
        }
    }

    private NBTTagList a(int... p_a_1_)
    {
        NBTTagList nbttaglist = new NBTTagList();

        for (int i : p_a_1_)
        {
            nbttaglist.appendTag(new NBTTagInt(i));
        }

        return nbttaglist;
    }

    private NBTTagList a(double... p_a_1_)
    {
        NBTTagList nbttaglist = new NBTTagList();

        for (double d0 : p_a_1_)
        {
            nbttaglist.appendTag(new NBTTagDouble(d0));
        }

        return nbttaglist;
    }

    static class a implements Iterable<awt>
    {
        public static final awt a = IGrowable.a.t();
        final ObjectIntIdentityMap<awt> b;
        private int c;

        private a()
        {
            this.b = new ObjectIntIdentityMap<awt>(16);
        }

        public int a(awt p_a_1_)
        {
            int i = this.b.get(p_a_1_);

            if (i == -1)
            {
                i = this.c++;
                this.b.put(p_a_1_, i);
            }

            return i;
        }

        @Nullable
        public awt a(int p_a_1_)
        {
            awt awt = this.b.getByValue(p_a_1_);
            return awt == null ? a : awt;
        }

        public Iterator<awt> iterator()
        {
            return this.b.iterator();
        }

        public void a(awt p_a_1_, int p_a_2_)
        {
            this.b.put(p_a_1_, p_a_2_);
        }
    }

    public static class b
    {
        public final BlockPos a;
        public final awt b;
        public final NBTTagCompound c;

        public b(BlockPos xMin, awt zMin, @Nullable NBTTagCompound xMax)
        {
            this.a = xMin;
            this.b = zMin;
            this.c = xMax;
        }
    }

    public static class c
    {
        public final ScoreObjective a;
        public final BlockPos b;
        public final NBTTagCompound c;

        public c(ScoreObjective p_i2034_1_, BlockPos p_i2034_2_, NBTTagCompound p_i2034_3_)
        {
            this.a = p_i2034_1_;
            this.b = p_i2034_2_;
            this.c = p_i2034_3_;
        }
    }
}
