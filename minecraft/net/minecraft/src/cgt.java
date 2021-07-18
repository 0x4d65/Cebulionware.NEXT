package net.minecraft.src;

import javax.annotation.Nullable;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.client.audio.SoundListSerializer;
import net.minecraft.dispenser.BehaviorProjectileDispense;

public interface cgt
{
    BehaviorProjectileDispense a();

    @Nullable
    chq a(SoundEventAccessor var1);

    SoundListSerializer b();

    qg c();

    boolean d();

    int f();

    float g();

    float h();

    float i();

    float j();

    float k();

    cgt.a l();

    public static enum a
    {
        a(0),
        b(2);

        private final int c;

        private a(int p_i350_3_)
        {
            this.c = p_i350_3_;
        }

        public int a()
        {
            return this.c;
        }
    }
}
