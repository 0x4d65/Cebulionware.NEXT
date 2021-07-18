package net.minecraft.src;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.gen.ChunkGeneratorFlat;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerHills;

public class anl
{
    private ChunkGeneratorFlat a;
    private GenLayerHills b;
    private GenLayerHills c;
    private final BiomeDecorator d;
    private final List<BiomeColorHelper> e;

    protected anl()
    {
        this.d = new BiomeDecorator(this);
        this.e = Lists.newArrayList(BiomeDesert.f, BiomeDesert.c, BiomeDesert.g, BiomeDesert.u, BiomeDesert.t, BiomeDesert.w, BiomeDesert.x);
    }

    private anl(long entityIn, WorldEntitySpawner p_i1654_3_, String p_i1654_4_)
    {
        this();

        if (p_i1654_3_ == WorldEntitySpawner.f && !p_i1654_4_.isEmpty())
        {
            this.a = ChunkGeneratorFlat.a.a(p_i1654_4_).b();
        }

        GenLayerHills[] agenlayerhills = GenLayerHills.a(entityIn, p_i1654_3_, this.a);
        this.b = agenlayerhills[0];
        this.c = agenlayerhills[1];
    }

    public anl(AnvilConverterException entitycreeperIn)
    {
        this(entitycreeperIn.a(), entitycreeperIn.t(), entitycreeperIn.A());
    }

    public List<BiomeColorHelper> a()
    {
        return this.e;
    }

    public BiomeColorHelper a(BlockPos p_a_1_)
    {
        return this.a(p_a_1_, (BiomeColorHelper)null);
    }

    public BiomeColorHelper a(BlockPos p_a_1_, BiomeColorHelper p_a_2_)
    {
        return this.d.a(p_a_1_.getX(), p_a_1_.getZ(), p_a_2_);
    }

    public float a(float p_a_1_, int p_a_2_)
    {
        return p_a_1_;
    }

    public BiomeColorHelper[] a(BiomeColorHelper[] p_a_1_, int p_a_2_, int p_a_3_, int p_a_4_, int p_a_5_)
    {
        GenLayer.a();

        if (p_a_1_ == null || p_a_1_.length < p_a_4_ * p_a_5_)
        {
            p_a_1_ = new BiomeColorHelper[p_a_4_ * p_a_5_];
        }

        int[] aint = this.b.getInts(p_a_2_, p_a_3_, p_a_4_, p_a_5_);

        try
        {
            for (int i = 0; i < p_a_4_ * p_a_5_; ++i)
            {
                p_a_1_[i] = BiomeColorHelper.a(aint[i], BiomeDesert.b);
            }

            return p_a_1_;
        }
        catch (Throwable throwable)
        {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("RawBiomeBlock");
            crashreportcategory.addCrashSection("biomes[] size", Integer.valueOf(p_a_1_.length));
            crashreportcategory.addCrashSection("x", Integer.valueOf(p_a_2_));
            crashreportcategory.addCrashSection("z", Integer.valueOf(p_a_3_));
            crashreportcategory.addCrashSection("w", Integer.valueOf(p_a_4_));
            crashreportcategory.addCrashSection("h", Integer.valueOf(p_a_5_));
            throw new ReportedException(crashreport);
        }
    }

    public BiomeColorHelper[] b(@Nullable BiomeColorHelper[] p_b_1_, int p_b_2_, int p_b_3_, int p_b_4_, int p_b_5_)
    {
        return this.a(p_b_1_, p_b_2_, p_b_3_, p_b_4_, p_b_5_, true);
    }

    public BiomeColorHelper[] a(@Nullable BiomeColorHelper[] p_a_1_, int p_a_2_, int p_a_3_, int p_a_4_, int p_a_5_, boolean p_a_6_)
    {
        GenLayer.a();

        if (p_a_1_ == null || p_a_1_.length < p_a_4_ * p_a_5_)
        {
            p_a_1_ = new BiomeColorHelper[p_a_4_ * p_a_5_];
        }

        if (p_a_6_ && p_a_4_ == 16 && p_a_5_ == 16 && (p_a_2_ & 15) == 0 && (p_a_3_ & 15) == 0)
        {
            BiomeColorHelper[] abiomecolorhelper = this.d.b(p_a_2_, p_a_3_);
            System.arraycopy(abiomecolorhelper, 0, p_a_1_, 0, p_a_4_ * p_a_5_);
            return p_a_1_;
        }
        else
        {
            int[] aint = this.c.getInts(p_a_2_, p_a_3_, p_a_4_, p_a_5_);

            for (int i = 0; i < p_a_4_ * p_a_5_; ++i)
            {
                p_a_1_[i] = BiomeColorHelper.a(aint[i], BiomeDesert.b);
            }

            return p_a_1_;
        }
    }

    public boolean a(int p_a_1_, int p_a_2_, int p_a_3_, List<BiomeColorHelper> p_a_4_)
    {
        GenLayer.a();
        int i = p_a_1_ - p_a_3_ >> 2;
        int j = p_a_2_ - p_a_3_ >> 2;
        int k = p_a_1_ + p_a_3_ >> 2;
        int l = p_a_2_ + p_a_3_ >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        int[] aint = this.b.getInts(i, j, i1, j1);

        try
        {
            for (int k1 = 0; k1 < i1 * j1; ++k1)
            {
                BiomeColorHelper biomecolorhelper = BiomeColorHelper.b(aint[k1]);

                if (!p_a_4_.contains(biomecolorhelper))
                {
                    return false;
                }
            }

            return true;
        }
        catch (Throwable throwable)
        {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("Layer");
            crashreportcategory.addCrashSection("Layer", this.b.toString());
            crashreportcategory.addCrashSection("x", Integer.valueOf(p_a_1_));
            crashreportcategory.addCrashSection("z", Integer.valueOf(p_a_2_));
            crashreportcategory.addCrashSection("radius", Integer.valueOf(p_a_3_));
            crashreportcategory.addCrashSection("allowed", p_a_4_);
            throw new ReportedException(crashreport);
        }
    }

    @Nullable
    public BlockPos a(int p_a_1_, int p_a_2_, int p_a_3_, List<BiomeColorHelper> p_a_4_, Random p_a_5_)
    {
        GenLayer.a();
        int i = p_a_1_ - p_a_3_ >> 2;
        int j = p_a_2_ - p_a_3_ >> 2;
        int k = p_a_1_ + p_a_3_ >> 2;
        int l = p_a_2_ + p_a_3_ >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        int[] aint = this.b.getInts(i, j, i1, j1);
        BlockPos blockpos = null;
        int k1 = 0;

        for (int l1 = 0; l1 < i1 * j1; ++l1)
        {
            int i2 = i + l1 % i1 << 2;
            int j2 = j + l1 / i1 << 2;
            BiomeColorHelper biomecolorhelper = BiomeColorHelper.b(aint[l1]);

            if (p_a_4_.contains(biomecolorhelper) && (blockpos == null || p_a_5_.nextInt(k1 + 1) == 0))
            {
                blockpos = new BlockPos(i2, 0, j2);
                ++k1;
            }
        }

        return blockpos;
    }

    public void b()
    {
        this.d.a();
    }

    public boolean c()
    {
        return this.a != null && this.a.G >= 0;
    }

    public BiomeColorHelper d()
    {
        return this.a != null && this.a.G >= 0 ? BiomeColorHelper.a(this.a.G) : null;
    }
}
