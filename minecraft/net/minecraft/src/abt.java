package net.minecraft.src;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import net.minecraft.entity.boss.dragon.phase.IPhase;
import net.minecraft.entity.boss.dragon.phase.PhaseBase;
import net.minecraft.entity.boss.dragon.phase.PhaseHoldingPattern;
import net.minecraft.entity.boss.dragon.phase.PhaseHover;
import net.minecraft.entity.boss.dragon.phase.PhaseLanding;
import net.minecraft.entity.boss.dragon.phase.PhaseLandingApproach;
import net.minecraft.entity.boss.dragon.phase.PhaseList;
import net.minecraft.entity.boss.dragon.phase.PhaseManager;
import net.minecraft.entity.boss.dragon.phase.PhaseSittingAttacking;
import net.minecraft.entity.boss.dragon.phase.PhaseSittingFlaming;
import net.minecraft.entity.boss.dragon.phase.PhaseSittingScanning;
import net.minecraft.entity.boss.dragon.phase.PhaseStrafePlayer;
import net.minecraft.entity.boss.dragon.phase.PhaseTakeoff;

public class abt<T extends PhaseSittingFlaming>
{
    private static abt<?>[] l = new abt[0];
    public static final abt<PhaseLandingApproach> a = a(PhaseLandingApproach.class, "HoldingPattern");
    public static final abt<PhaseList> b = a(PhaseList.class, "StrafePlayer");
    public static final abt<IPhase> c = a(IPhase.class, "LandingApproach");
    public static final abt<PhaseSittingAttacking> d = a(PhaseSittingAttacking.class, "Landing");
    public static final abt<PhaseManager> e = a(PhaseManager.class, "Takeoff");
    public static final abt<PhaseStrafePlayer> f = a(PhaseStrafePlayer.class, "SittingFlaming");
    public static final abt<PhaseTakeoff> g = a(PhaseTakeoff.class, "SittingScanning");
    public static final abt<PhaseSittingScanning> h = a(PhaseSittingScanning.class, "SittingAttacking");
    public static final abt<PhaseHoldingPattern> i = a(PhaseHoldingPattern.class, "ChargingPlayer");
    public static final abt<PhaseHover> j = a(PhaseHover.class, "Dying");
    public static final abt<PhaseLanding> k = a(PhaseLanding.class, "Hover");
    private final Class <? extends PhaseSittingFlaming > m;
    private final int n;
    private final String o;

    private abt(int p_i1241_1_, Class <? extends PhaseSittingFlaming > p_i1241_2_, String p_i1241_3_)
    {
        this.n = p_i1241_1_;
        this.m = p_i1241_2_;
        this.o = p_i1241_3_;
    }

    public PhaseSittingFlaming a(PhaseBase p_a_1_)
    {
        try
        {
            Constructor <? extends PhaseSittingFlaming > constructor = this.a();
            return constructor.newInstance(p_a_1_);
        }
        catch (Exception exception)
        {
            throw new Error(exception);
        }
    }

    protected Constructor <? extends PhaseSittingFlaming > a() throws NoSuchMethodException
    {
        return this.m.getConstructor(PhaseBase.class);
    }

    public int b()
    {
        return this.n;
    }

    public String toString()
    {
        return this.o + " (#" + this.n + ")";
    }

    public static abt<?> a(int p_a_0_)
    {
        return p_a_0_ >= 0 && p_a_0_ < l.length ? l[p_a_0_] : a;
    }

    public static int c()
    {
        return l.length;
    }

    private static <T extends PhaseSittingFlaming> abt<T> a(Class<T> p_a_0_, String p_a_1_)
    {
        abt<T> abt = new abt<T>(l.length, p_a_0_, p_a_1_);
        l = (abt[])Arrays.copyOf(l, l.length + 1);
        l[abt.b()] = abt;
        return abt;
    }
}
