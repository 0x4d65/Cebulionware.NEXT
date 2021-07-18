package net.minecraft.src;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.ParticlePortal;
import net.minecraft.world.IWorldEventListener;

public class btx extends IParticleFactory
{
    protected btx(IWorldEventListener p_i706_1_, double p_i706_2_, double p_i706_4_, double p_i706_6_, double p_i706_8_, double p_i706_10_, double p_i706_12_)
    {
        super(p_i706_1_, p_i706_2_, p_i706_4_, p_i706_6_, 0.0D, 0.0D, 0.0D);
        this.j *= 0.30000001192092896D;
        this.k = Math.random() * 0.20000000298023224D + 0.10000000149011612D;
        this.l *= 0.30000001192092896D;
        this.A = 1.0F;
        this.B = 1.0F;
        this.C = 1.0F;
        this.b(19);
        this.a(0.01F, 0.01F);
        this.x = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
        this.z = 0.0F;
        this.j = p_i706_8_;
        this.k = p_i706_10_;
        this.l = p_i706_12_;
    }

    public void a()
    {
        this.d = this.g;
        this.e = this.h;
        this.f = this.i;
        this.k -= (double)this.z;
        this.a(this.j, this.k, this.l);
        this.j *= 0.9800000190734863D;
        this.k *= 0.9800000190734863D;
        this.l *= 0.9800000190734863D;
        int i = 60 - this.x;
        float f = (float)i * 0.001F;
        this.a(f, f);
        this.b(19 + i % 4);

        if (this.x-- <= 0)
        {
            this.i();
        }
    }

    public static class a implements ParticlePortal
    {
        public IParticleFactory a(int p_a_1_, IWorldEventListener p_a_2_, double p_a_3_, double p_a_5_, double p_a_7_, double p_a_9_, double p_a_11_, double p_a_13_, int... p_a_15_)
        {
            return new btx(p_a_2_, p_a_3_, p_a_5_, p_a_7_, p_a_9_, p_a_11_, p_a_13_);
        }
    }
}
