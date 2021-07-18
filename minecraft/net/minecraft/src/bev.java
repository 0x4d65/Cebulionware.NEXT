package net.minecraft.src;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketMaps;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldEventListener;
import net.minecraft.world.storage.MapData;

public class bev extends MapData
{
    public int xCenter;
    public int zCenter;
    public byte dimension;
    public boolean trackingPosition;
    public boolean field_191096_f;
    public byte scale;

    /** colours */
    public byte[] colors = new byte[16384];
    public List<bev.a> playersArrayList = Lists.<bev.a>newArrayList();
    private final Map<RecipeItemHelper, bev.a> k = Maps.<RecipeItemHelper, bev.a>newHashMap();
    public Map<String, beu> mapDecorations = Maps.<String, beu>newLinkedHashMap();

    public bev(String p_i2077_1_)
    {
        super(p_i2077_1_);
    }

    public void calculateMapCenter(double x, double z, int mapScale)
    {
        int i = 128 * (1 << mapScale);
        int j = IProgressUpdate.c((x + 64.0D) / (double)i);
        int k = IProgressUpdate.c((z + 64.0D) / (double)i);
        this.xCenter = j * i + i / 2 - 64;
        this.zCenter = k * i + i / 2 - 64;
    }

    /**
     * reads in data from the NBTTagCompound into this MapDataBase
     */
    public void readFromNBT(NBTTagCompound nbt)
    {
        this.dimension = nbt.getByte("dimension");
        this.xCenter = nbt.getInteger("xCenter");
        this.zCenter = nbt.getInteger("zCenter");
        this.scale = nbt.getByte("scale");
        this.scale = (byte)IProgressUpdate.a(this.scale, 0, 4);

        if (nbt.hasKey("trackingPosition", 1))
        {
            this.trackingPosition = nbt.getBoolean("trackingPosition");
        }
        else
        {
            this.trackingPosition = true;
        }

        this.field_191096_f = nbt.getBoolean("unlimitedTracking");
        int i = nbt.getShort("width");
        int j = nbt.getShort("height");

        if (i == 128 && j == 128)
        {
            this.colors = nbt.getByteArray("colors");
        }
        else
        {
            byte[] abyte = nbt.getByteArray("colors");
            this.colors = new byte[16384];
            int k = (128 - i) / 2;
            int l = (128 - j) / 2;

            for (int i1 = 0; i1 < j; ++i1)
            {
                int j1 = i1 + l;

                if (j1 >= 0 || j1 < 128)
                {
                    for (int k1 = 0; k1 < i; ++k1)
                    {
                        int l1 = k1 + k;

                        if (l1 >= 0 || l1 < 128)
                        {
                            this.colors[l1 + j1 * 128] = abyte[k1 + i1 * i];
                        }
                    }
                }
            }
        }
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound.setByte("dimension", this.dimension);
        compound.setInteger("xCenter", this.xCenter);
        compound.setInteger("zCenter", this.zCenter);
        compound.setByte("scale", this.scale);
        compound.setShort("width", (short)128);
        compound.setShort("height", (short)128);
        compound.setByteArray("colors", this.colors);
        compound.setBoolean("trackingPosition", this.trackingPosition);
        compound.setBoolean("unlimitedTracking", this.field_191096_f);
        return compound;
    }

    public void a(RecipeItemHelper p_a_1_, Items p_a_2_)
    {
        if (!this.k.containsKey(p_a_1_))
        {
            bev.a bev$a = new bev.a(p_a_1_);
            this.k.put(p_a_1_, bev$a);
            this.playersArrayList.add(bev$a);
        }

        if (!p_a_1_.bv.h(p_a_2_))
        {
            this.mapDecorations.remove(p_a_1_.getName());
        }

        for (int i = 0; i < this.playersArrayList.size(); ++i)
        {
            bev.a bev$a = this.playersArrayList.get(i);

            if (!bev$a.a.F && (bev$a.a.bv.h(p_a_2_) || p_a_2_.z()))
            {
                if (!p_a_2_.z() && bev$a.a.am == this.dimension && this.trackingPosition)
                {
                    this.a(beu.a.a, bev$a.a.l, bev$a.a.getName(), bev$a.a.p, bev$a.a.r, (double)bev$a.a.v);
                }
            }
            else
            {
                this.k.remove(bev$a.a);
                this.playersArrayList.remove(bev$a);
            }
        }

        if (p_a_2_.z() && this.trackingPosition)
        {
            EntityPainting entitypainting = p_a_2_.A();
            BlockPos blockpos = entitypainting.q();
            this.a(beu.a.b, p_a_1_.l, "frame-" + entitypainting.S(), (double)blockpos.getX(), (double)blockpos.getZ(), (double)(entitypainting.field_191308_b.getHorizontalIndex() * 90));
        }

        if (p_a_2_.o() && p_a_2_.p().hasKey("Decorations", 9))
        {
            NBTTagList nbttaglist = p_a_2_.p().getTagList("Decorations", 10);

            for (int j = 0; j < nbttaglist.tagCount(); ++j)
            {
                NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(j);

                if (!this.mapDecorations.containsKey(nbttagcompound.getString("id")))
                {
                    this.a(beu.a.a(nbttagcompound.getByte("type")), p_a_1_.l, nbttagcompound.getString("id"), nbttagcompound.getDouble("x"), nbttagcompound.getDouble("z"), nbttagcompound.getDouble("rot"));
                }
            }
        }
    }

    public static void a(Items p_a_0_, BlockPos p_a_1_, String p_a_2_, beu.a p_a_3_)
    {
        NBTTagList nbttaglist;

        if (p_a_0_.o() && p_a_0_.p().hasKey("Decorations", 9))
        {
            nbttaglist = p_a_0_.p().getTagList("Decorations", 10);
        }
        else
        {
            nbttaglist = new NBTTagList();
            p_a_0_.a("Decorations", nbttaglist);
        }

        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setByte("type", p_a_3_.a());
        nbttagcompound.setString("id", p_a_2_);
        nbttagcompound.setDouble("x", (double)p_a_1_.getX());
        nbttagcompound.setDouble("z", (double)p_a_1_.getZ());
        nbttagcompound.setDouble("rot", 180.0D);
        nbttaglist.appendTag(nbttagcompound);

        if (p_a_3_.c())
        {
            NBTTagCompound nbttagcompound1 = p_a_0_.c("display");
            nbttagcompound1.setInteger("MapColor", p_a_3_.d());
        }
    }

    private void a(beu.a p_a_1_, IWorldEventListener p_a_2_, String p_a_3_, double p_a_4_, double p_a_6_, double p_a_8_)
    {
        int i = 1 << this.scale;
        float f = (float)(p_a_4_ - (double)this.xCenter) / (float)i;
        float f1 = (float)(p_a_6_ - (double)this.zCenter) / (float)i;
        byte b0 = (byte)((int)((double)(f * 2.0F) + 0.5D));
        byte b1 = (byte)((int)((double)(f1 * 2.0F) + 0.5D));
        int j = 63;
        byte b2;

        if (f >= -63.0F && f1 >= -63.0F && f <= 63.0F && f1 <= 63.0F)
        {
            p_a_8_ = p_a_8_ + (p_a_8_ < 0.0D ? -8.0D : 8.0D);
            b2 = (byte)((int)(p_a_8_ * 16.0D / 360.0D));

            if (this.dimension < 0)
            {
                int l = (int)(p_a_2_.V().f() / 10L);
                b2 = (byte)(l * l * 34187121 + l * 121 >> 15 & 15);
            }
        }
        else
        {
            if (p_a_1_ != beu.a.a)
            {
                this.mapDecorations.remove(p_a_3_);
                return;
            }

            int k = 320;

            if (Math.abs(f) < 320.0F && Math.abs(f1) < 320.0F)
            {
                p_a_1_ = beu.a.g;
            }
            else
            {
                if (!this.field_191096_f)
                {
                    this.mapDecorations.remove(p_a_3_);
                    return;
                }

                p_a_1_ = beu.a.h;
            }

            b2 = 0;

            if (f <= -63.0F)
            {
                b0 = -128;
            }

            if (f1 <= -63.0F)
            {
                b1 = -128;
            }

            if (f >= 63.0F)
            {
                b0 = 127;
            }

            if (f1 >= 63.0F)
            {
                b1 = 127;
            }
        }

        this.mapDecorations.put(p_a_3_, new beu(p_a_1_, b0, b1, b2));
    }

    @Nullable
    public Packet<?> a(Items p_a_1_, IWorldEventListener p_a_2_, RecipeItemHelper p_a_3_)
    {
        bev.a bev$a = this.k.get(p_a_3_);
        return bev$a == null ? null : bev$a.a(p_a_1_);
    }

    public void updateMapData(int x, int y)
    {
        super.c();

        for (bev.a bev$a : this.playersArrayList)
        {
            bev$a.a(x, y);
        }
    }

    public bev.a a(RecipeItemHelper p_a_1_)
    {
        bev.a bev$a = this.k.get(p_a_1_);

        if (bev$a == null)
        {
            bev$a = new bev.a(p_a_1_);
            this.k.put(p_a_1_, bev$a);
            this.playersArrayList.add(bev$a);
        }

        return bev$a;
    }

    public class a
    {
        public final RecipeItemHelper a;
        private boolean d = true;
        private int e;
        private int f;
        private int g = 127;
        private int h = 127;
        private int i;
        public int b;

        public a(RecipeItemHelper p_i2076_2_)
        {
            this.a = p_i2076_2_;
        }

        @Nullable
        public Packet<?> a(Items p_a_1_)
        {
            if (this.d)
            {
                this.d = false;
                return new SPacketMaps(p_a_1_.j(), bev.this.scale, bev.this.trackingPosition, bev.this.mapDecorations.values(), bev.this.colors, this.e, this.f, this.g + 1 - this.e, this.h + 1 - this.f);
            }
            else
            {
                return this.i++ % 5 == 0 ? new SPacketMaps(p_a_1_.j(), bev.this.scale, bev.this.trackingPosition, bev.this.mapDecorations.values(), bev.this.colors, 0, 0, 0, 0) : null;
            }
        }

        public void a(int p_a_1_, int p_a_2_)
        {
            if (this.d)
            {
                this.e = Math.min(this.e, p_a_1_);
                this.f = Math.min(this.f, p_a_2_);
                this.g = Math.max(this.g, p_a_1_);
                this.h = Math.max(this.h, p_a_2_);
            }
            else
            {
                this.d = true;
                this.e = p_a_1_;
                this.f = p_a_2_;
                this.g = p_a_1_;
                this.h = p_a_2_;
            }
        }
    }
}
