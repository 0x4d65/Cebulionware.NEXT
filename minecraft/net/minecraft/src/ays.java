package net.minecraft.src;

import javax.annotation.Nullable;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.biome.BiomeHell;
import net.minecraft.world.chunk.NibbleArray;
import net.minecraft.world.gen.feature.WorldGenBigTree;

public class ays extends WorldProviderHell
{
    private ayr h;

    /**
     * creates a new world chunk manager for WorldProvider
     */
    public void createBiomeProvider()
    {
        this.c = new BiomeHell(BiomeDesert.k);
        NBTTagCompound nbttagcompound = this.b.V().a(WorldProviderSurface.c);
        this.h = this.b instanceof EntityPlayerMP ? new ayr((EntityPlayerMP)this.b, nbttagcompound.getCompoundTag("DragonFight")) : null;
    }

    public NibbleArray c()
    {
        return new WorldGenBigTree(this.b, this.b.V().r(), this.b.Q(), this.h());
    }

    /**
     * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
     */
    public float calculateCelestialAngle(long worldTime, float partialTicks)
    {
        return 0.0F;
    }

    @Nullable
    public float[] a(float p_a_1_, float p_a_2_)
    {
        return null;
    }

    public ScoreObjective b(float p_b_1_, float p_b_2_)
    {
        int i = 10518688;
        float f = IProgressUpdate.b(p_b_1_ * ((float)Math.PI * 2F)) * 2.0F + 0.5F;
        f = IProgressUpdate.a(f, 0.0F, 1.0F);
        float f1 = 0.627451F;
        float f2 = 0.5019608F;
        float f3 = 0.627451F;
        f1 = f1 * (f * 0.0F + 0.15F);
        f2 = f2 * (f * 0.0F + 0.15F);
        f3 = f3 * (f * 0.0F + 0.15F);
        return new ScoreObjective((double)f1, (double)f2, (double)f3);
    }

    public boolean g()
    {
        return false;
    }

    /**
     * True if the player can respawn in this dimension (true = overworld, false = nether).
     */
    public boolean canRespawnHere()
    {
        return false;
    }

    /**
     * Returns 'true' if in the "main surface world", but 'false' if in the Nether or End dimensions.
     */
    public boolean isSurfaceWorld()
    {
        return false;
    }

    public float f()
    {
        return 8.0F;
    }

    /**
     * Will check if the x, z position specified is alright to be set as the map spawn point
     */
    public boolean canCoordinateBeSpawn(int x, int z)
    {
        return this.b.c(new BlockPos(x, 0, z)).a().blocksMovement();
    }

    public BlockPos h()
    {
        return new BlockPos(100, 50, 0);
    }

    public int i()
    {
        return 50;
    }

    /**
     * Returns true if the given X,Z coordinate should show environmental fog.
     */
    public boolean doesXZShowFog(int x, int z)
    {
        return false;
    }

    public WorldProviderSurface q()
    {
        return WorldProviderSurface.c;
    }

    public void r()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        if (this.h != null)
        {
            nbttagcompound.setTag("DragonFight", this.h.a());
        }

        this.b.V().a(WorldProviderSurface.c, nbttagcompound);
    }

    public void s()
    {
        if (this.h != null)
        {
            this.h.b();
        }
    }

    @Nullable
    public ayr t()
    {
        return this.h;
    }
}
