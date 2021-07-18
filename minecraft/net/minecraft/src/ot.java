package net.minecraft.src;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.network.play.server.SPacketChunkData;
import net.minecraft.network.play.server.SPacketMultiBlockChange;
import net.minecraft.network.play.server.SPacketUnloadChunk;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.chunk.BlockStatePaletteLinear;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ot
{
    private static final Logger a = LogManager.getLogger();
    private final ou b;
    private final List<EntityTrackerEntry> c = Lists.<EntityTrackerEntry>newArrayList();
    private final Explosion d;
    private final short[] e = new short[64];
    @Nullable
    private BlockStatePaletteLinear f;
    private int g;
    private int h;
    private long i;
    private boolean j;

    public ot(ou p_i948_1_, int p_i948_2_, int p_i948_3_)
    {
        this.b = p_i948_1_;
        this.d = new Explosion(p_i948_2_, p_i948_3_);
        this.f = p_i948_1_.a().r().b(p_i948_2_, p_i948_3_);
    }

    public Explosion a()
    {
        return this.d;
    }

    public void a(EntityTrackerEntry p_a_1_)
    {
        if (this.c.contains(p_a_1_))
        {
            a.debug("Failed to add player. {} already is in chunk {}, {}", p_a_1_, Integer.valueOf(this.d.isFlaming), Integer.valueOf(this.d.isSmoking));
        }
        else
        {
            if (this.c.isEmpty())
            {
                this.i = this.b.a().R();
            }

            this.c.add(p_a_1_);

            if (this.j)
            {
                this.c(p_a_1_);
            }
        }
    }

    public void b(EntityTrackerEntry p_b_1_)
    {
        if (this.c.contains(p_b_1_))
        {
            if (this.j)
            {
                p_b_1_.updateCounter.a(new SPacketUnloadChunk(this.d.isFlaming, this.d.isSmoking));
            }

            this.c.remove(p_b_1_);

            if (this.c.isEmpty())
            {
                this.b.b(this);
            }
        }
    }

    public boolean a(boolean p_a_1_)
    {
        if (this.f != null)
        {
            return true;
        }
        else
        {
            if (p_a_1_)
            {
                this.f = this.b.a().r().c(this.d.isFlaming, this.d.isSmoking);
            }
            else
            {
                this.f = this.b.a().r().b(this.d.isFlaming, this.d.isSmoking);
            }

            return this.f != null;
        }
    }

    public boolean b()
    {
        if (this.j)
        {
            return true;
        }
        else if (this.f == null)
        {
            return false;
        }
        else if (!this.f.i())
        {
            return false;
        }
        else
        {
            this.g = 0;
            this.h = 0;
            this.j = true;
            Packet<?> packet = new SPacketChunkData(this.f, 65535);

            for (EntityTrackerEntry entitytrackerentry : this.c)
            {
                entitytrackerentry.updateCounter.a(packet);
                this.b.a().v().a(entitytrackerentry, this.f);
            }

            return true;
        }
    }

    public void c(EntityTrackerEntry p_c_1_)
    {
        if (this.j)
        {
            p_c_1_.updateCounter.a(new SPacketChunkData(this.f, 65535));
            this.b.a().v().a(p_c_1_, this.f);
        }
    }

    public void c()
    {
        long i = this.b.a().R();

        if (this.f != null)
        {
            this.f.c(this.f.x() + i - this.i);
        }

        this.i = i;
    }

    public void a(int p_a_1_, int p_a_2_, int p_a_3_)
    {
        if (this.j)
        {
            if (this.g == 0)
            {
                this.b.a(this);
            }

            this.h |= 1 << (p_a_2_ >> 4);

            if (this.g < 64)
            {
                short short1 = (short)(p_a_1_ << 12 | p_a_3_ << 8 | p_a_2_);

                for (int i = 0; i < this.g; ++i)
                {
                    if (this.e[i] == short1)
                    {
                        return;
                    }
                }

                this.e[this.g++] = short1;
            }
        }
    }

    public void a(Packet<?> p_a_1_)
    {
        if (this.j)
        {
            for (int i = 0; i < this.c.size(); ++i)
            {
                (this.c.get(i)).updateCounter.a(p_a_1_);
            }
        }
    }

    public void d()
    {
        if (this.j && this.f != null)
        {
            if (this.g != 0)
            {
                if (this.g == 1)
                {
                    int i = (this.e[0] >> 12 & 15) + this.d.isFlaming * 16;
                    int j = this.e[0] & 255;
                    int k = (this.e[0] >> 8 & 15) + this.d.isSmoking * 16;
                    BlockPos blockpos = new BlockPos(i, j, k);
                    this.a(new SPacketBlockChange(this.b.a(), blockpos));

                    if (this.b.a().o(blockpos).u().l())
                    {
                        this.a(this.b.a().r(blockpos));
                    }
                }
                else if (this.g == 64)
                {
                    this.a(new SPacketChunkData(this.f, this.h));
                }
                else
                {
                    this.a(new SPacketMultiBlockChange(this.g, this.e, this.f));

                    for (int l = 0; l < this.g; ++l)
                    {
                        int i1 = (this.e[l] >> 12 & 15) + this.d.isFlaming * 16;
                        int j1 = this.e[l] & 255;
                        int k1 = (this.e[l] >> 8 & 15) + this.d.isSmoking * 16;
                        BlockPos blockpos1 = new BlockPos(i1, j1, k1);

                        if (this.b.a().o(blockpos1).u().l())
                        {
                            this.a(this.b.a().r(blockpos1));
                        }
                    }
                }

                this.g = 0;
                this.h = 0;
            }
        }
    }

    private void a(@Nullable TileEntityChest p_a_1_)
    {
        if (p_a_1_ != null)
        {
            SPacketUpdateTileEntity spacketupdatetileentity = p_a_1_.c();

            if (spacketupdatetileentity != null)
            {
                this.a(spacketupdatetileentity);
            }
        }
    }

    public boolean d(EntityTrackerEntry p_d_1_)
    {
        return this.c.contains(p_d_1_);
    }

    public boolean a(Predicate<EntityTrackerEntry> p_a_1_)
    {
        return Iterables.tryFind(this.c, p_a_1_).isPresent();
    }

    public boolean a(double p_a_1_, Predicate<EntityTrackerEntry> p_a_3_)
    {
        int i = 0;

        for (int j = this.c.size(); i < j; ++i)
        {
            EntityTrackerEntry entitytrackerentry = this.c.get(i);

            if (p_a_3_.apply(entitytrackerentry) && this.d.a(entitytrackerentry) < p_a_1_ * p_a_1_)
            {
                return true;
            }
        }

        return false;
    }

    public boolean e()
    {
        return this.j;
    }

    @Nullable
    public BlockStatePaletteLinear f()
    {
        return this.f;
    }

    public double g()
    {
        double d0 = Double.MAX_VALUE;

        for (EntityTrackerEntry entitytrackerentry : this.c)
        {
            double d1 = this.d.a(entitytrackerentry);

            if (d1 < d0)
            {
                d0 = d1;
            }
        }

        return d0;
    }
}
