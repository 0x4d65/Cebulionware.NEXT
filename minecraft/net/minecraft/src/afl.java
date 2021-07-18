package net.minecraft.src;

import net.minecraft.block.IGrowable;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.entity.item.EntityMinecartCommandBlock;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.datafix.IDataWalker;
import net.minecraft.util.datafix.fixes.BedItemColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldEventListener;

public class afl extends EntityMinecartCommandBlock
{
    private final BlockRenderLayer a = new BlockRenderLayer()
    {
        public void a(int p_a_1_)
        {
            afl.this.l.a(afl.this, (byte)p_a_1_);
        }
        public IWorldEventListener a()
        {
            return afl.this.l;
        }
        public BlockPos b()
        {
            return new BlockPos(afl.this);
        }
    };

    public afl(IWorldEventListener p_i1441_1_)
    {
        super(p_i1441_1_);
    }

    public afl(IWorldEventListener p_i1442_1_, double p_i1442_2_, double p_i1442_4_, double p_i1442_6_)
    {
        super(p_i1442_1_, p_i1442_2_, p_i1442_4_, p_i1442_6_);
    }

    public static void a(IDataWalker p_a_0_)
    {
        a(p_a_0_, afl.class);
        p_a_0_.a(DataFixer.e, new BedItemColor()
        {
            public NBTTagCompound a(DataFixesManager p_a_1_, NBTTagCompound p_a_2_, int p_a_3_)
            {
                String s = p_a_2_.getString("id");

                if (EntitySelectors.a(afl.class).equals(new BehaviorProjectileDispense(s)))
                {
                    p_a_2_.setString("id", TileEntityChest.a(TileEntityLockableLoot.class).toString());
                    p_a_1_.a(DataFixer.version, p_a_2_, p_a_3_);
                    p_a_2_.setString("id", s);
                }

                return p_a_2_;
            }
        });
    }

    public EntityMinecartCommandBlock.a v()
    {
        return EntityMinecartCommandBlock.a.e;
    }

    public awt x()
    {
        return IGrowable.ac.t();
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.a.a(compound);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        this.a.b(compound);
    }

    public void a(byte p_a_1_)
    {
        this.a.b(p_a_1_);
    }

    public void B_()
    {
        super.B_();
        this.a.c();
    }
}
