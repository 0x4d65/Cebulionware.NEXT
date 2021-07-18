package net.minecraft.src;

import net.minecraft.block.BlockAir;
import net.minecraft.world.biome.BiomeColorHelper;

public class aoj extends BiomeColorHelper
{
    public aoj(BiomeColorHelper.ColorResolver worldIn)
    {
        super(worldIn);
        this.t.clear();
        this.u.clear();
        this.v.clear();
        this.w.clear();
        this.s = new BlockAir();
    }

    public boolean i()
    {
        return true;
    }
}
