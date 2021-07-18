package net.minecraft.src;

import com.google.common.base.Optional;
import javax.annotation.Nullable;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.boss.dragon.phase.PhaseBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldEventListener;

public class abc extends EntityList
{
    private static final DataSerializer<Optional<BlockPos>> b = nb.a(abc.class, EntityDataManager.k);
    private static final DataSerializer<Boolean> c = nb.a(abc.class, EntityDataManager.h);
    public int field_191307_a;

    public abc(IWorldEventListener p_i1225_1_)
    {
        super(p_i1225_1_);
        this.i = true;
        this.a(2.0F, 2.0F);
        this.field_191307_a = this.S.nextInt(100000);
    }

    public abc(IWorldEventListener p_i1226_1_, double p_i1226_2_, double p_i1226_4_, double p_i1226_6_)
    {
        this(p_i1226_1_);
        this.b(p_i1226_2_, p_i1226_4_, p_i1226_6_);
    }

    protected boolean ak()
    {
        return false;
    }

    protected void i()
    {
        this.V().a(b, Optional.absent());
        this.V().a(c, Boolean.valueOf(true));
    }

    public void B_()
    {
        this.m = this.p;
        this.n = this.q;
        this.o = this.r;
        ++this.field_191307_a;

        if (!this.l.G)
        {
            BlockPos blockpos = new BlockPos(this);

            if (this.l.s instanceof ays && this.l.o(blockpos).u() != IGrowable.ab)
            {
                this.l.a(blockpos, IGrowable.ab.t());
            }
        }
    }

    protected void b(NBTTagCompound p_b_1_)
    {
        if (this.j() != null)
        {
            p_b_1_.setTag("BeamTarget", NBTUtil.createPosTag(this.j()));
        }

        p_b_1_.setBoolean("ShowBottom", this.k());
    }

    protected void a(NBTTagCompound p_a_1_)
    {
        if (p_a_1_.hasKey("BeamTarget", 10))
        {
            this.a(NBTUtil.getPosFromTag(p_a_1_.getCompoundTag("BeamTarget")));
        }

        if (p_a_1_.hasKey("ShowBottom", 1))
        {
            this.a(p_a_1_.getBoolean("ShowBottom"));
        }
    }

    public boolean ay()
    {
        return true;
    }

    public boolean a(EntityDamageSourceIndirect p_a_1_, float p_a_2_)
    {
        if (this.b(p_a_1_))
        {
            return false;
        }
        else if (p_a_1_.j() instanceof PhaseBase)
        {
            return false;
        }
        else
        {
            if (!this.F && !this.l.G)
            {
                this.X();

                if (!this.l.G)
                {
                    if (!p_a_1_.c())
                    {
                        this.l.a((EntityList)null, this.p, this.q, this.r, 6.0F, true);
                    }

                    this.a(p_a_1_);
                }
            }

            return true;
        }
    }

    public void U()
    {
        this.a(EntityDamageSourceIndirect.n);
        super.U();
    }

    private void a(EntityDamageSourceIndirect p_a_1_)
    {
        if (this.l.s instanceof ays)
        {
            ays ays = (ays)this.l.s;
            ayr ayr = ays.t();

            if (ayr != null)
            {
                ayr.a(this, p_a_1_);
            }
        }
    }

    public void a(@Nullable BlockPos p_a_1_)
    {
        this.V().b(b, Optional.fromNullable(p_a_1_));
    }

    @Nullable
    public BlockPos j()
    {
        return (BlockPos)((Optional)this.V().a(b)).orNull();
    }

    public void a(boolean p_a_1_)
    {
        this.V().b(c, Boolean.valueOf(p_a_1_));
    }

    public boolean k()
    {
        return ((Boolean)this.V().a(c)).booleanValue();
    }

    public boolean a(double p_a_1_)
    {
        return super.a(p_a_1_) || this.j() != null;
    }
}
