package net.minecraft.src;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.client.audio.SoundListSerializer;
import net.minecraft.client.tutorial.CompletedTutorialStep;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class chq implements CompletedTutorialStep<SoundListSerializer>
{
    private final List<CompletedTutorialStep<SoundListSerializer>> a = Lists.<CompletedTutorialStep<SoundListSerializer>>newArrayList();
    private final Random b = new Random();
    private final BehaviorProjectileDispense c;
    private final ITextComponent d;

    public chq(BehaviorProjectileDispense p_i278_1_, @Nullable String p_i278_2_)
    {
        this.c = p_i278_1_;
        this.d = p_i278_2_ == null ? null : new TextComponentTranslation(p_i278_2_, new Object[0]);
    }

    public int e()
    {
        int i = 0;

        for (CompletedTutorialStep<SoundListSerializer> completedtutorialstep : this.a)
        {
            i += completedtutorialstep.e();
        }

        return i;
    }

    public SoundListSerializer a()
    {
        int i = this.e();

        if (!this.a.isEmpty() && i != 0)
        {
            int j = this.b.nextInt(i);

            for (CompletedTutorialStep<SoundListSerializer> completedtutorialstep : this.a)
            {
                j -= completedtutorialstep.e();

                if (j < 0)
                {
                    return completedtutorialstep.i();
                }
            }

            return SoundEventAccessor.accessorList;
        }
        else
        {
            return SoundEventAccessor.accessorList;
        }
    }

    public void a(CompletedTutorialStep<SoundListSerializer> p_a_1_)
    {
        this.a.add(p_a_1_);
    }

    public BehaviorProjectileDispense b()
    {
        return this.c;
    }

    @Nullable
    public ITextComponent c()
    {
        return this.d;
    }
}
