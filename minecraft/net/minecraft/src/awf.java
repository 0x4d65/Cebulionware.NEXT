package net.minecraft.src;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.world.IWorldEventListener;
import net.minecraft.world.chunk.BlockStatePaletteLinear;
import net.minecraft.world.gen.feature.WorldGenEndPodium;
import net.minecraft.world.gen.feature.WorldGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class awf extends awg implements DedicatedServer
{
    private static final Logger a = LogManager.getLogger();
    private long f;
    private int g;
    private BlockPos h;
    private boolean i;

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setLong("Age", this.f);

        if (this.h != null)
        {
            compound.setTag("ExitPortal", NBTUtil.createPosTag(this.h));
        }

        if (this.i)
        {
            compound.setBoolean("ExactTeleport", this.i);
        }

        return compound;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.f = compound.getLong("Age");

        if (compound.hasKey("ExitPortal", 10))
        {
            this.h = NBTUtil.getPosFromTag(compound.getCompoundTag("ExitPortal"));
        }

        this.i = compound.getBoolean("ExactTeleport");
    }

    public double t()
    {
        return 65536.0D;
    }

    /**
     * Like the old updateEntity(), except more generic.
     */
    public void update()
    {
        boolean flag = this.a();
        boolean flag1 = this.f();
        ++this.f;

        if (flag1)
        {
            --this.g;
        }
        else if (!this.b.G)
        {
            List<EntityList> list = this.b.a(EntityList.class, new Vec2f(this.w()));

            if (!list.isEmpty())
            {
                this.a(list.get(0));
            }

            if (this.f % 2400L == 0L)
            {
                this.h();
            }
        }

        if (flag != this.a() || flag1 != this.f())
        {
            this.y_();
        }
    }

    public boolean a()
    {
        return this.f < 200L;
    }

    public boolean f()
    {
        return this.g > 0;
    }

    public float a(float p_a_1_)
    {
        return IProgressUpdate.a(((float)this.f + p_a_1_) / 200.0F, 0.0F, 1.0F);
    }

    public float b(float p_b_1_)
    {
        return 1.0F - IProgressUpdate.a(((float)this.g - p_b_1_) / 40.0F, 0.0F, 1.0F);
    }

    @Nullable
    public SPacketUpdateTileEntity c()
    {
        return new SPacketUpdateTileEntity(this.c, 8, this.d());
    }

    public NBTTagCompound d()
    {
        return this.writeToNBT(new NBTTagCompound());
    }

    public void h()
    {
        if (!this.b.G)
        {
            this.g = 40;
            this.b.c(this.w(), this.x(), 1, 0);
            this.y_();
        }
    }

    public boolean receiveClientEvent(int id, int type)
    {
        if (id == 1)
        {
            this.g = 40;
            return true;
        }
        else
        {
            return super.receiveClientEvent(id, type);
        }
    }

    public void a(EntityList p_a_1_)
    {
        if (!this.b.G && !this.f())
        {
            this.g = 100;

            if (this.h == null && this.b.s instanceof ays)
            {
                this.k();
            }

            if (this.h != null)
            {
                BlockPos blockpos = this.i ? this.h : this.j();
                p_a_1_.a((double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.5D, (double)blockpos.getZ() + 0.5D);
            }

            this.h();
        }
    }

    private BlockPos j()
    {
        BlockPos blockpos = a(this.b, this.h, 5, false);
        a.debug("Best exit position for portal at {} is {}", this.h, blockpos);
        return blockpos.up();
    }

    private void k()
    {
        ScoreObjective scoreobjective = (new ScoreObjective((double)this.w().getX(), 0.0D, (double)this.w().getZ())).a();
        ScoreObjective scoreobjective1 = scoreobjective.a(1024.0D);

        for (int i = 16; a(this.b, scoreobjective1).g() > 0 && i-- > 0; scoreobjective1 = scoreobjective1.e(scoreobjective.a(-16.0D)))
        {
            a.debug("Skipping backwards past nonempty chunk at {}", (Object)scoreobjective1);
        }

        for (int j = 16; a(this.b, scoreobjective1).g() == 0 && j-- > 0; scoreobjective1 = scoreobjective1.e(scoreobjective.a(16.0D)))
        {
            a.debug("Skipping forward past empty chunk at {}", (Object)scoreobjective1);
        }

        a.debug("Found chunk at {}", (Object)scoreobjective1);
        BlockStatePaletteLinear blockstatepalettelinear = a(this.b, scoreobjective1);
        this.h = a(blockstatepalettelinear);

        if (this.h == null)
        {
            this.h = new BlockPos(scoreobjective1.name + 0.5D, 75.0D, scoreobjective1.renderType + 0.5D);
            a.debug("Failed to find suitable block, settling on {}", (Object)this.h);
            (new WorldGenerator()).b(this.b, new Random(this.h.toLong()), this.h);
        }
        else
        {
            a.debug("Found block at {}", (Object)this.h);
        }

        this.h = a(this.b, this.h, 16, true);
        a.debug("Creating portal at {}", (Object)this.h);
        this.h = this.h.up(10);
        this.c(this.h);
        this.y_();
    }

    private static BlockPos a(IWorldEventListener p_a_0_, BlockPos p_a_1_, int p_a_2_, boolean p_a_3_)
    {
        BlockPos blockpos = null;

        for (int i = -p_a_2_; i <= p_a_2_; ++i)
        {
            for (int j = -p_a_2_; j <= p_a_2_; ++j)
            {
                if (i != 0 || j != 0 || p_a_3_)
                {
                    for (int k = 255; k > (blockpos == null ? 0 : blockpos.getY()); --k)
                    {
                        BlockPos blockpos1 = new BlockPos(p_a_1_.getX() + i, k, p_a_1_.getZ() + j);
                        awt awt = p_a_0_.o(blockpos1);

                        if (awt.isBlockNormalCube() && (p_a_3_ || awt.u() != IGrowable.h))
                        {
                            blockpos = blockpos1;
                            break;
                        }
                    }
                }
            }
        }

        return blockpos == null ? p_a_1_ : blockpos;
    }

    private static BlockStatePaletteLinear a(IWorldEventListener p_a_0_, ScoreObjective p_a_1_)
    {
        return p_a_0_.a(IProgressUpdate.c(p_a_1_.name / 16.0D), IProgressUpdate.c(p_a_1_.renderType / 16.0D));
    }

    @Nullable
    private static BlockPos a(BlockStatePaletteLinear p_a_0_)
    {
        BlockPos blockpos = new BlockPos(p_a_0_.resizeHandler * 16, 30, p_a_0_.bits * 16);
        int i = p_a_0_.g() + 16 - 1;
        BlockPos blockpos1 = new BlockPos(p_a_0_.resizeHandler * 16 + 16 - 1, i, p_a_0_.bits * 16 + 16 - 1);
        BlockPos blockpos2 = null;
        double d0 = 0.0D;

        for (BlockPos blockpos3 : BlockPos.getAllInBox(blockpos, blockpos1))
        {
            awt awt = p_a_0_.a(blockpos3);

            if (awt.u() == IGrowable.bH && !p_a_0_.a(blockpos3.up(1)).isBlockNormalCube() && !p_a_0_.a(blockpos3.up(2)).isBlockNormalCube())
            {
                double d1 = blockpos3.distanceSqToCenter(0.0D, 0.0D, 0.0D);

                if (blockpos2 == null || d1 < d0)
                {
                    blockpos2 = blockpos3;
                    d0 = d1;
                }
            }
        }

        return blockpos2;
    }

    private void c(BlockPos p_c_1_)
    {
        (new WorldGenEndPodium()).b(this.b, new Random(), p_c_1_);
        TileEntityChest tileentitychest = this.b.r(p_c_1_);

        if (tileentitychest instanceof awf)
        {
            awf awf = (awf)tileentitychest;
            awf.h = new BlockPos(this.w());
            awf.y_();
        }
        else
        {
            a.warn("Couldn't save exit portal at {}", (Object)p_c_1_);
        }
    }

    public boolean a(EnumFacing p_a_1_)
    {
        return this.x().t().c(this.b, this.w(), p_a_1_);
    }

    public int i()
    {
        int i = 0;

        for (EnumFacing enumfacing : EnumFacing.values())
        {
            i += this.a(enumfacing) ? 1 : 0;
        }

        return i;
    }

    public void b(BlockPos p_b_1_)
    {
        this.i = true;
        this.h = p_b_1_;
    }
}
