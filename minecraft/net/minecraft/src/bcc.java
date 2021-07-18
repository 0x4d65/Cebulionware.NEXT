package net.minecraft.src;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.IGrowable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldEventListener;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.gen.ChunkGeneratorDebug;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.structure.MapGenStructureData;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureComponentTemplate;
import net.minecraft.world.gen.structure.StructureEndCityPieces;
import net.minecraft.world.gen.structure.template.BlockRotationProcessor;

public class bcc extends MapGenStructureData
{
    private final int b = 80;
    private final int d = 20;
    public static final List<BiomeColorHelper> a = Arrays.<BiomeColorHelper>asList(BiomeDesert.E, BiomeDesert.ab);
    private final WorldGenAbstractTree h;

    public bcc(WorldGenAbstractTree p_i2018_1_)
    {
        this.h = p_i2018_1_;
    }

    public String a()
    {
        return "Mansion";
    }

    protected boolean a(int p_a_1_, int p_a_2_)
    {
        int i = p_a_1_;
        int j = p_a_2_;

        if (p_a_1_ < 0)
        {
            i = p_a_1_ - 79;
        }

        if (p_a_2_ < 0)
        {
            j = p_a_2_ - 79;
        }

        int k = i / 80;
        int l = j / 80;
        Random random = this.scaleNoise.a(k, l, 10387319);
        k = k * 80;
        l = l * 80;
        k = k + (random.nextInt(60) + random.nextInt(60)) / 2;
        l = l + (random.nextInt(60) + random.nextInt(60)) / 2;

        if (p_a_1_ == k && p_a_2_ == l)
        {
            boolean flag = this.scaleNoise.C().a(p_a_1_ * 16 + 8, p_a_2_ * 16 + 8, 32, a);

            if (flag)
            {
                return true;
            }
        }

        return false;
    }

    public BlockPos a(IWorldEventListener p_a_1_, BlockPos p_a_2_, boolean p_a_3_)
    {
        this.scaleNoise = p_a_1_;
        anl anl = p_a_1_.C();
        return anl.c() && anl.d() != BiomeDesert.E ? null : a(p_a_1_, this, p_a_2_, 80, 20, 10387319, true, 100, p_a_3_);
    }

    protected MapGenVillage b(int p_b_1_, int p_b_2_)
    {
        return new bcc.a(this.scaleNoise, this.h, this.SOUL_SAND, p_b_1_, p_b_2_);
    }

    public static class a extends MapGenVillage
    {
        private boolean c;

        public a()
        {
        }

        public a(IWorldEventListener p_i2017_1_, WorldGenAbstractTree p_i2017_2_, Random p_i2017_3_, int p_i2017_4_, int p_i2017_5_)
        {
            super(p_i2017_4_, p_i2017_5_);
            this.a(p_i2017_1_, p_i2017_2_, p_i2017_3_, p_i2017_4_, p_i2017_5_);
        }

        private void a(IWorldEventListener p_a_1_, WorldGenAbstractTree p_a_2_, Random p_a_3_, int p_a_4_, int p_a_5_)
        {
            BlockSandStone blocksandstone = BlockSandStone.values()[p_a_3_.nextInt(BlockSandStone.values().length)];
            ChunkGeneratorDebug chunkgeneratordebug = new ChunkGeneratorDebug();
            p_a_2_.a(p_a_4_, p_a_5_, chunkgeneratordebug);
            int i = 5;
            int j = 5;

            if (blocksandstone == BlockSandStone.b)
            {
                i = -5;
            }
            else if (blocksandstone == BlockSandStone.c)
            {
                i = -5;
                j = -5;
            }
            else if (blocksandstone == BlockSandStone.d)
            {
                j = -5;
            }

            int k = chunkgeneratordebug.a(7, 7);
            int l = chunkgeneratordebug.a(7, 7 + j);
            int i1 = chunkgeneratordebug.a(7 + i, 7);
            int j1 = chunkgeneratordebug.a(7 + i, 7 + j);
            int k1 = Math.min(Math.min(k, l), Math.min(i1, j1));

            if (k1 < 60)
            {
                this.c = false;
            }
            else
            {
                BlockPos blockpos = new BlockPos(p_a_4_ * 16 + 8, k1 + 1, p_a_5_ * 16 + 8);
                List<BlockRotationProcessor.i> list = Lists.<BlockRotationProcessor.i>newLinkedList();
                BlockRotationProcessor.a(p_a_1_.U().h(), blockpos, blocksandstone, list, p_a_3_);
                this.VILLAGE_SPAWN_BIOMES.addAll(list);
                this.d();
                this.c = true;
            }
        }

        public void a(IWorldEventListener p_a_1_, Random p_a_2_, StructureEndCityPieces p_a_3_)
        {
            super.a(p_a_1_, p_a_2_, p_a_3_);
            int i = this.size.INSERT;

            for (int j = p_a_3_.OVERWRITE; j <= p_a_3_.TOWER_BRIDGES; ++j)
            {
                for (int k = p_a_3_.HOUSE_TOWER_GENERATOR; k <= p_a_3_.TOWER_BRIDGE_GENERATOR; ++k)
                {
                    BlockPos blockpos = new BlockPos(j, i, k);

                    if (!p_a_1_.d(blockpos) && this.size.b(blockpos))
                    {
                        boolean flag = false;

                        for (StructureComponentTemplate structurecomponenttemplate : this.VILLAGE_SPAWN_BIOMES)
                        {
                            if (structurecomponenttemplate.l.b(blockpos))
                            {
                                flag = true;
                                break;
                            }
                        }

                        if (flag)
                        {
                            for (int l = i - 1; l > 1; --l)
                            {
                                BlockPos blockpos1 = new BlockPos(j, l, k);

                                if (!p_a_1_.d(blockpos1) && !p_a_1_.o(blockpos1).a().d())
                                {
                                    break;
                                }

                                p_a_1_.a(blockpos1, IGrowable.e.t(), 2);
                            }
                        }
                    }
                }
            }
        }

        public boolean a()
        {
            return this.c;
        }
    }
}
