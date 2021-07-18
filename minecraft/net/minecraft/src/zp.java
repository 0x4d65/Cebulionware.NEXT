package net.minecraft.src;

import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.VillageCollection;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.IWorldEventListener;

public class zp
{
    private final IWorldEventListener a;
    private boolean b;
    private int c = -1;
    private int d;
    private int e;
    private VillageCollection f;
    private int g;
    private int h;
    private int i;

    public zp(IWorldEventListener p_i1170_1_)
    {
        this.a = p_i1170_1_;
    }

    public void a()
    {
        if (this.a.D())
        {
            this.c = 0;
        }
        else if (this.c != 2)
        {
            if (this.c == 0)
            {
                float f = this.a.c(0.0F);

                if ((double)f < 0.5D || (double)f > 0.501D)
                {
                    return;
                }

                this.c = this.a.r.nextInt(10) == 0 ? 1 : 2;
                this.b = false;

                if (this.c == 2)
                {
                    return;
                }
            }

            if (this.c != -1)
            {
                if (!this.b)
                {
                    if (!this.b())
                    {
                        return;
                    }

                    this.b = true;
                }

                if (this.e > 0)
                {
                    --this.e;
                }
                else
                {
                    this.e = 2;

                    if (this.d > 0)
                    {
                        this.c();
                        --this.d;
                    }
                    else
                    {
                        this.c = 2;
                    }
                }
            }
        }
    }

    private boolean b()
    {
        List<RecipeItemHelper> list = this.a.i;
        Iterator iterator = list.iterator();

        while (true)
        {
            if (!iterator.hasNext())
            {
                return false;
            }

            RecipeItemHelper recipeitemhelper = (RecipeItemHelper)iterator.next();

            if (!recipeitemhelper.y())
            {
                this.f = this.a.ak().a(new BlockPos(recipeitemhelper), 1);

                if (this.f != null && this.f.c() >= 10 && this.f.d() >= 20 && this.f.e() >= 20)
                {
                    BlockPos blockpos = this.f.a();
                    float f = (float)this.f.b();
                    boolean flag = false;

                    for (int i = 0; i < 10; ++i)
                    {
                        float f1 = this.a.r.nextFloat() * ((float)Math.PI * 2F);
                        this.g = blockpos.getX() + (int)((double)(IProgressUpdate.b(f1) * f) * 0.9D);
                        this.h = blockpos.getY();
                        this.i = blockpos.getZ() + (int)((double)(IProgressUpdate.a(f1) * f) * 0.9D);
                        flag = false;

                        for (VillageCollection villagecollection : this.a.ak().b())
                        {
                            if (villagecollection != this.f && villagecollection.a(new BlockPos(this.g, this.h, this.i)))
                            {
                                flag = true;
                                break;
                            }
                        }

                        if (!flag)
                        {
                            break;
                        }
                    }

                    if (flag)
                    {
                        return false;
                    }

                    ScoreObjective scoreobjective = this.a(new BlockPos(this.g, this.h, this.i));

                    if (scoreobjective != null)
                    {
                        break;
                    }
                }
            }
        }

        this.e = 0;
        this.d = 20;
        return true;
    }

    private boolean c()
    {
        ScoreObjective scoreobjective = this.a(new BlockPos(this.g, this.h, this.i));

        if (scoreobjective == null)
        {
            return false;
        }
        else
        {
            adt adt;

            try
            {
                adt = new adt(this.a);
                adt.a(this.a.D(new BlockPos(adt)), (EnumCreatureAttribute)null);
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
                return false;
            }

            adt.b(scoreobjective.name, scoreobjective.objectiveCriteria, scoreobjective.renderType, this.a.r.nextFloat() * 360.0F, 0.0F);
            this.a.a(adt);
            BlockPos blockpos = this.f.a();
            adt.a(blockpos, this.f.b());
            return true;
        }
    }

    @Nullable
    private ScoreObjective a(BlockPos p_a_1_)
    {
        for (int i = 0; i < 10; ++i)
        {
            BlockPos blockpos = p_a_1_.add(this.a.r.nextInt(16) - 8, this.a.r.nextInt(6) - 3, this.a.r.nextInt(16) - 8);

            if (this.f.a(blockpos) && ChunkCache.a(IEntityLivingData.a.a, this.a, blockpos))
            {
                return new ScoreObjective((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ());
            }
        }

        return null;
    }
}
