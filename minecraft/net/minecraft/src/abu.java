package net.minecraft.src;

import net.minecraft.entity.boss.dragon.phase.PhaseBase;
import net.minecraft.entity.boss.dragon.phase.PhaseSittingFlaming;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class abu
{
    private static final Logger a = LogManager.getLogger();
    private final PhaseBase b;
    private final PhaseSittingFlaming[] c = new PhaseSittingFlaming[abt.c()];
    private PhaseSittingFlaming d;

    public abu(PhaseBase p_i1242_1_)
    {
        this.b = p_i1242_1_;
        this.a(abt.k);
    }

    public void a(abt<?> p_a_1_)
    {
        if (this.d == null || p_a_1_ != this.d.i())
        {
            if (this.d != null)
            {
                this.d.removeAreaEffect();
            }

            this.d = this.b(p_a_1_);

            if (!this.b.l.G)
            {
                this.b.V().b(PhaseBase.dragon, Integer.valueOf(p_a_1_.b()));
            }

            a.debug("Dragon is now in phase {} on the {}", p_a_1_, this.b.l.G ? "client" : "server");
            this.d.initPhase();
        }
    }

    public PhaseSittingFlaming a()
    {
        return this.d;
    }

    public <T extends PhaseSittingFlaming> T b(abt<T> p_b_1_)
    {
        int i = p_b_1_.b();

        if (this.c[i] == null)
        {
            this.c[i] = p_b_1_.a(this.b);
        }

        return (T)this.c[i];
    }
}
