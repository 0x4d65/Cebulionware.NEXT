package net.minecraft.src;

import com.google.common.collect.Maps;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.block.BlockBone;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.datafix.IDataWalker;
import net.minecraft.util.datafix.fixes.BedItemColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IWorldEventListener;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.BlockStatePaletteLinear;
import net.minecraft.world.chunk.BlockStatePaletteRegistry;
import net.minecraft.world.chunk.storage.NibbleArrayReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class aye implements ayf, bgy
{
    private static final Logger a = LogManager.getLogger();
    private final Map<Explosion, NBTTagCompound> b = Maps.<Explosion, NBTTagCompound>newConcurrentMap();
    private final Set<Explosion> c = Collections.<Explosion>newSetFromMap(Maps.newConcurrentMap());
    private final File d;
    private final IDataWalker e;
    private boolean f;

    public aye(File p_i1851_1_, IDataWalker p_i1851_2_)
    {
        this.d = p_i1851_1_;
        this.e = p_i1851_2_;
    }

    @Nullable
    public BlockStatePaletteLinear a(IWorldEventListener p_a_1_, int p_a_2_, int p_a_3_) throws IOException
    {
        Explosion explosion = new Explosion(p_a_2_, p_a_3_);
        NBTTagCompound nbttagcompound = this.b.get(explosion);

        if (nbttagcompound == null)
        {
            DataInputStream datainputstream = WorldProvider.d(this.d, p_a_2_, p_a_3_);

            if (datainputstream == null)
            {
                return null;
            }

            nbttagcompound = this.e.a(DataFixer.fixMap, CompressedStreamTools.read(datainputstream));
        }

        return this.a(p_a_1_, p_a_2_, p_a_3_, nbttagcompound);
    }

    public boolean a(int p_a_1_, int p_a_2_)
    {
        Explosion explosion = new Explosion(p_a_1_, p_a_2_);
        NBTTagCompound nbttagcompound = this.b.get(explosion);
        return nbttagcompound != null ? true : WorldProvider.f(this.d, p_a_1_, p_a_2_);
    }

    @Nullable
    protected BlockStatePaletteLinear a(IWorldEventListener p_a_1_, int p_a_2_, int p_a_3_, NBTTagCompound p_a_4_)
    {
        if (!p_a_4_.hasKey("Level", 10))
        {
            a.error("Chunk file at {},{} is missing level data, skipping", Integer.valueOf(p_a_2_), Integer.valueOf(p_a_3_));
            return null;
        }
        else
        {
            NBTTagCompound nbttagcompound = p_a_4_.getCompoundTag("Level");

            if (!nbttagcompound.hasKey("Sections", 9))
            {
                a.error("Chunk file at {},{} is missing block data, skipping", Integer.valueOf(p_a_2_), Integer.valueOf(p_a_3_));
                return null;
            }
            else
            {
                BlockStatePaletteLinear blockstatepalettelinear = this.a(p_a_1_, nbttagcompound);

                if (!blockstatepalettelinear.a(p_a_2_, p_a_3_))
                {
                    a.error("Chunk file at {},{} is in the wrong location; relocating. (Expected {}, {}, got {}, {})", Integer.valueOf(p_a_2_), Integer.valueOf(p_a_3_), Integer.valueOf(p_a_2_), Integer.valueOf(p_a_3_), Integer.valueOf(blockstatepalettelinear.resizeHandler), Integer.valueOf(blockstatepalettelinear.bits));
                    nbttagcompound.setInteger("xPos", p_a_2_);
                    nbttagcompound.setInteger("zPos", p_a_3_);
                    blockstatepalettelinear = this.a(p_a_1_, nbttagcompound);
                }

                return blockstatepalettelinear;
            }
        }
    }

    public void a(IWorldEventListener p_a_1_, BlockStatePaletteLinear p_a_2_) throws IOException, WorldSettings
    {
        p_a_1_.P();

        try
        {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound.setTag("Level", nbttagcompound1);
            nbttagcompound.setInteger("DataVersion", 1343);
            this.a(p_a_2_, p_a_1_, nbttagcompound1);
            this.a(p_a_2_.k(), nbttagcompound);
        }
        catch (Exception exception)
        {
            a.error("Failed to save chunk", (Throwable)exception);
        }
    }

    protected void a(Explosion p_a_1_, NBTTagCompound p_a_2_)
    {
        if (!this.c.contains(p_a_1_))
        {
            this.b.put(p_a_1_, p_a_2_);
        }

        bgx.a().a(this);
    }

    public boolean a()
    {
        if (this.b.isEmpty())
        {
            if (this.f)
            {
                a.info("ThreadedAnvilChunkStorage ({}): All chunks are saved", (Object)this.d.getName());
            }

            return false;
        }
        else
        {
            Explosion explosion = this.b.keySet().iterator().next();
            boolean lvt_3_1_;

            try
            {
                this.c.add(explosion);
                NBTTagCompound nbttagcompound = this.b.remove(explosion);

                if (nbttagcompound != null)
                {
                    try
                    {
                        this.b(explosion, nbttagcompound);
                    }
                    catch (Exception exception)
                    {
                        a.error("Failed to save chunk", (Throwable)exception);
                    }
                }

                lvt_3_1_ = true;
            }
            finally
            {
                this.c.remove(explosion);
            }

            return lvt_3_1_;
        }
    }

    private void b(Explosion p_b_1_, NBTTagCompound p_b_2_) throws IOException
    {
        DataOutputStream dataoutputstream = WorldProvider.e(this.d, p_b_1_.isFlaming, p_b_1_.isSmoking);
        CompressedStreamTools.write(p_b_2_, dataoutputstream);
        dataoutputstream.close();
    }

    public void b(IWorldEventListener p_b_1_, BlockStatePaletteLinear p_b_2_) throws IOException
    {
    }

    public void b()
    {
    }

    public void c()
    {
        try
        {
            this.f = true;

            while (true)
            {
                if (this.a())
                {
                    continue;
                }
            }
        }
        finally
        {
            this.f = false;
        }
    }

    public static void a(IDataWalker p_a_0_)
    {
        p_a_0_.a(DataFixer.fixMap, new BedItemColor()
        {
            public NBTTagCompound a(DataFixesManager p_a_1_, NBTTagCompound p_a_2_, int p_a_3_)
            {
                if (p_a_2_.hasKey("Level", 10))
                {
                    NBTTagCompound nbttagcompound = p_a_2_.getCompoundTag("Level");

                    if (nbttagcompound.hasKey("Entities", 9))
                    {
                        NBTTagList nbttaglist = nbttagcompound.getTagList("Entities", 10);

                        for (int i = 0; i < nbttaglist.tagCount(); ++i)
                        {
                            nbttaglist.set(i, p_a_1_.a(DataFixer.e, (NBTTagCompound)nbttaglist.get(i), p_a_3_));
                        }
                    }

                    if (nbttagcompound.hasKey("TileEntities", 9))
                    {
                        NBTTagList nbttaglist1 = nbttagcompound.getTagList("TileEntities", 10);

                        for (int j = 0; j < nbttaglist1.tagCount(); ++j)
                        {
                            nbttaglist1.set(j, p_a_1_.a(DataFixer.version, (NBTTagCompound)nbttaglist1.get(j), p_a_3_));
                        }
                    }
                }

                return p_a_2_;
            }
        });
    }

    private void a(BlockStatePaletteLinear p_a_1_, IWorldEventListener p_a_2_, NBTTagCompound p_a_3_)
    {
        p_a_3_.setInteger("xPos", p_a_1_.resizeHandler);
        p_a_3_.setInteger("zPos", p_a_1_.bits);
        p_a_3_.setLong("LastUpdate", p_a_2_.R());
        p_a_3_.setIntArray("HeightMap", p_a_1_.r());
        p_a_3_.setBoolean("TerrainPopulated", p_a_1_.u());
        p_a_3_.setBoolean("LightPopulated", p_a_1_.v());
        p_a_3_.setLong("InhabitedTime", p_a_1_.x());
        NibbleArrayReader[] anibblearrayreader = p_a_1_.h();
        NBTTagList nbttaglist = new NBTTagList();
        boolean flag = p_a_2_.s.m();

        for (NibbleArrayReader nibblearrayreader : anibblearrayreader)
        {
            if (nibblearrayreader != BlockStatePaletteLinear.states)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Y", (byte)(nibblearrayreader.d() >> 4 & 255));
                byte[] abyte = new byte[4096];
                BlockStatePaletteRegistry blockstatepaletteregistry = new BlockStatePaletteRegistry();
                BlockStatePaletteRegistry blockstatepaletteregistry1 = nibblearrayreader.g().a(abyte, blockstatepaletteregistry);
                nbttagcompound.setByteArray("Blocks", abyte);
                nbttagcompound.setByteArray("Data", blockstatepaletteregistry.a());

                if (blockstatepaletteregistry1 != null)
                {
                    nbttagcompound.setByteArray("Add", blockstatepaletteregistry1.a());
                }

                nbttagcompound.setByteArray("BlockLight", nibblearrayreader.h().a());

                if (flag)
                {
                    nbttagcompound.setByteArray("SkyLight", nibblearrayreader.i().a());
                }
                else
                {
                    nbttagcompound.setByteArray("SkyLight", new byte[nibblearrayreader.h().a().length]);
                }

                nbttaglist.appendTag(nbttagcompound);
            }
        }

        p_a_3_.setTag("Sections", nbttaglist);
        p_a_3_.setByteArray("Biomes", p_a_1_.l());
        p_a_1_.g(false);
        NBTTagList nbttaglist1 = new NBTTagList();

        for (int i = 0; i < p_a_1_.t().length; ++i)
        {
            for (EntityList entitylist : p_a_1_.t()[i])
            {
                NBTTagCompound nbttagcompound2 = new NBTTagCompound();

                if (entitylist.d(nbttagcompound2))
                {
                    p_a_1_.g(true);
                    nbttaglist1.appendTag(nbttagcompound2);
                }
            }
        }

        p_a_3_.setTag("Entities", nbttaglist1);
        NBTTagList nbttaglist2 = new NBTTagList();

        for (TileEntityChest tileentitychest : p_a_1_.s().values())
        {
            NBTTagCompound nbttagcompound3 = tileentitychest.writeToNBT(new NBTTagCompound());
            nbttaglist2.appendTag(nbttagcompound3);
        }

        p_a_3_.setTag("TileEntities", nbttaglist2);
        List<Biome> list = p_a_2_.a(p_a_1_, false);

        if (list != null)
        {
            long j = p_a_2_.R();
            NBTTagList nbttaglist3 = new NBTTagList();

            for (Biome biome : list)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                BehaviorProjectileDispense behaviorprojectiledispense = BlockBone.h.getNameForObject(biome.a());
                nbttagcompound1.setString("i", behaviorprojectiledispense == null ? "" : behaviorprojectiledispense.toString());
                nbttagcompound1.setInteger("x", biome.STONE.getX());
                nbttagcompound1.setInteger("y", biome.STONE.getY());
                nbttagcompound1.setInteger("z", biome.STONE.getZ());
                nbttagcompound1.setInteger("t", (int)(biome.AIR - j));
                nbttagcompound1.setInteger("p", biome.BEDROCK);
                nbttaglist3.appendTag(nbttagcompound1);
            }

            p_a_3_.setTag("TileTicks", nbttaglist3);
        }
    }

    private BlockStatePaletteLinear a(IWorldEventListener p_a_1_, NBTTagCompound p_a_2_)
    {
        int i = p_a_2_.getInteger("xPos");
        int j = p_a_2_.getInteger("zPos");
        BlockStatePaletteLinear blockstatepalettelinear = new BlockStatePaletteLinear(p_a_1_, i, j);
        blockstatepalettelinear.a(p_a_2_.getIntArray("HeightMap"));
        blockstatepalettelinear.d(p_a_2_.getBoolean("TerrainPopulated"));
        blockstatepalettelinear.e(p_a_2_.getBoolean("LightPopulated"));
        blockstatepalettelinear.c(p_a_2_.getLong("InhabitedTime"));
        NBTTagList nbttaglist = p_a_2_.getTagList("Sections", 10);
        int k = 16;
        NibbleArrayReader[] anibblearrayreader = new NibbleArrayReader[16];
        boolean flag = p_a_1_.s.m();

        for (int l = 0; l < nbttaglist.tagCount(); ++l)
        {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(l);
            int i1 = nbttagcompound.getByte("Y");
            NibbleArrayReader nibblearrayreader = new NibbleArrayReader(i1 << 4, flag);
            byte[] abyte = nbttagcompound.getByteArray("Blocks");
            BlockStatePaletteRegistry blockstatepaletteregistry = new BlockStatePaletteRegistry(nbttagcompound.getByteArray("Data"));
            BlockStatePaletteRegistry blockstatepaletteregistry1 = nbttagcompound.hasKey("Add", 7) ? new BlockStatePaletteRegistry(nbttagcompound.getByteArray("Add")) : null;
            nibblearrayreader.g().a(abyte, blockstatepaletteregistry, blockstatepaletteregistry1);
            nibblearrayreader.a(new BlockStatePaletteRegistry(nbttagcompound.getByteArray("BlockLight")));

            if (flag)
            {
                nibblearrayreader.b(new BlockStatePaletteRegistry(nbttagcompound.getByteArray("SkyLight")));
            }

            nibblearrayreader.e();
            anibblearrayreader[i1] = nibblearrayreader;
        }

        blockstatepalettelinear.a(anibblearrayreader);

        if (p_a_2_.hasKey("Biomes", 7))
        {
            blockstatepalettelinear.a(p_a_2_.getByteArray("Biomes"));
        }

        NBTTagList nbttaglist1 = p_a_2_.getTagList("Entities", 10);

        for (int j1 = 0; j1 < nbttaglist1.tagCount(); ++j1)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist1.getCompoundTagAt(j1);
            a(nbttagcompound1, p_a_1_, blockstatepalettelinear);
            blockstatepalettelinear.g(true);
        }

        NBTTagList nbttaglist2 = p_a_2_.getTagList("TileEntities", 10);

        for (int k1 = 0; k1 < nbttaglist2.tagCount(); ++k1)
        {
            NBTTagCompound nbttagcompound2 = nbttaglist2.getCompoundTagAt(k1);
            TileEntityChest tileentitychest = TileEntityChest.a(p_a_1_, nbttagcompound2);

            if (tileentitychest != null)
            {
                blockstatepalettelinear.a(tileentitychest);
            }
        }

        if (p_a_2_.hasKey("TileTicks", 9))
        {
            NBTTagList nbttaglist3 = p_a_2_.getTagList("TileTicks", 10);

            for (int l1 = 0; l1 < nbttaglist3.tagCount(); ++l1)
            {
                NBTTagCompound nbttagcompound3 = nbttaglist3.getCompoundTagAt(l1);
                BlockBone blockbone;

                if (nbttagcompound3.hasKey("i", 8))
                {
                    blockbone = BlockBone.b(nbttagcompound3.getString("i"));
                }
                else
                {
                    blockbone = BlockBone.c(nbttagcompound3.getInteger("i"));
                }

                p_a_1_.b(new BlockPos(nbttagcompound3.getInteger("x"), nbttagcompound3.getInteger("y"), nbttagcompound3.getInteger("z")), blockbone, nbttagcompound3.getInteger("t"), nbttagcompound3.getInteger("p"));
            }
        }

        return blockstatepalettelinear;
    }

    @Nullable
    public static EntityList a(NBTTagCompound p_a_0_, IWorldEventListener p_a_1_, BlockStatePaletteLinear p_a_2_)
    {
        EntityList entitylist = a(p_a_0_, p_a_1_);

        if (entitylist == null)
        {
            return null;
        }
        else
        {
            p_a_2_.a(entitylist);

            if (p_a_0_.hasKey("Passengers", 9))
            {
                NBTTagList nbttaglist = p_a_0_.getTagList("Passengers", 10);

                for (int i = 0; i < nbttaglist.tagCount(); ++i)
                {
                    EntityList entitylist1 = a(nbttaglist.getCompoundTagAt(i), p_a_1_, p_a_2_);

                    if (entitylist1 != null)
                    {
                        entitylist1.a(entitylist, true);
                    }
                }
            }

            return entitylist;
        }
    }

    @Nullable
    public static EntityList a(NBTTagCompound p_a_0_, IWorldEventListener p_a_1_, double p_a_2_, double p_a_4_, double p_a_6_, boolean p_a_8_)
    {
        EntityList entitylist = a(p_a_0_, p_a_1_);

        if (entitylist == null)
        {
            return null;
        }
        else
        {
            entitylist.b(p_a_2_, p_a_4_, p_a_6_, entitylist.v, entitylist.w);

            if (p_a_8_ && !p_a_1_.a(entitylist))
            {
                return null;
            }
            else
            {
                if (p_a_0_.hasKey("Passengers", 9))
                {
                    NBTTagList nbttaglist = p_a_0_.getTagList("Passengers", 10);

                    for (int i = 0; i < nbttaglist.tagCount(); ++i)
                    {
                        EntityList entitylist1 = a(nbttaglist.getCompoundTagAt(i), p_a_1_, p_a_2_, p_a_4_, p_a_6_, p_a_8_);

                        if (entitylist1 != null)
                        {
                            entitylist1.a(entitylist, true);
                        }
                    }
                }

                return entitylist;
            }
        }
    }

    @Nullable
    protected static EntityList a(NBTTagCompound p_a_0_, IWorldEventListener p_a_1_)
    {
        try
        {
            return EntitySelectors.a(p_a_0_, p_a_1_);
        }
        catch (RuntimeException var3)
        {
            return null;
        }
    }

    public static void a(EntityList p_a_0_, IWorldEventListener p_a_1_)
    {
        if (p_a_1_.a(p_a_0_) && p_a_0_.aT())
        {
            for (EntityList entitylist : p_a_0_.bF())
            {
                a(entitylist, p_a_1_);
            }
        }
    }

    @Nullable
    public static EntityList a(NBTTagCompound p_a_0_, IWorldEventListener p_a_1_, boolean p_a_2_)
    {
        EntityList entitylist = a(p_a_0_, p_a_1_);

        if (entitylist == null)
        {
            return null;
        }
        else if (p_a_2_ && !p_a_1_.a(entitylist))
        {
            return null;
        }
        else
        {
            if (p_a_0_.hasKey("Passengers", 9))
            {
                NBTTagList nbttaglist = p_a_0_.getTagList("Passengers", 10);

                for (int i = 0; i < nbttaglist.tagCount(); ++i)
                {
                    EntityList entitylist1 = a(nbttaglist.getCompoundTagAt(i), p_a_1_, p_a_2_);

                    if (entitylist1 != null)
                    {
                        entitylist1.a(entitylist, true);
                    }
                }
            }

            return entitylist;
        }
    }
}
