package net.minecraft.src;

import javax.annotation.Nullable;
import net.minecraft.entity.IJumpingMount;
import net.minecraft.entity.ai.EntityAIAttackRangedBow;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.village.VillageDoorInfo;

public class yp extends EntityAIAttackRangedBow
{
    protected final float strafingBackwards;

    public yp(IJumpingMount p_i1140_1_, double p_i1140_2_)
    {
        this(p_i1140_1_, p_i1140_2_, 0.001F);
    }

    public yp(IJumpingMount p_i1141_1_, double p_i1141_2_, float p_i1141_4_)
    {
        super(p_i1141_1_, p_i1141_2_);
        this.strafingBackwards = p_i1141_4_;
    }

    @Nullable
    protected ScoreObjective f()
    {
        if (this.entity.ao())
        {
            ScoreObjective scoreobjective = VillageDoorInfo.b(this.entity, 15, 7);
            return scoreobjective == null ? super.f() : scoreobjective;
        }
        else
        {
            return this.entity.bR().nextFloat() >= this.strafingBackwards ? VillageDoorInfo.b(this.entity, 10, 7) : super.f();
        }
    }
}
