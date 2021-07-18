package net.minecraft.src;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.chunk.ChunkCompileTaskGenerator;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.item.ItemCloth;
import net.minecraft.util.IProgressUpdate;

public class bxi extends ChunkCompileTaskGenerator
{
    private static final BehaviorProjectileDispense a = new BehaviorProjectileDispense("textures/entity/end_gateway_beam.png");

    public void a(awg p_a_1_, double p_a_2_, double p_a_4_, double p_a_6_, float p_a_8_, int p_a_9_, float p_a_10_)
    {
        ItemRenderer.p();
        awf awf = (awf)p_a_1_;

        if (awf.a() || awf.f())
        {
            ItemRenderer.a(516, 0.1F);
            this.a(a);
            float f = awf.a() ? awf.a(p_a_8_) : awf.b(p_a_8_);
            double d0 = awf.a() ? 256.0D - p_a_4_ : 50.0D;
            f = IProgressUpdate.a(f * (float)Math.PI);
            int i = IProgressUpdate.c((double)f * d0);
            float[] afloat = awf.a() ? ItemCloth.c.f() : ItemCloth.k.f();
            TileEntityRendererDispatcher.a(p_a_2_, p_a_4_, p_a_6_, (double)p_a_8_, (double)f, (double)awf.D().R(), 0, i, afloat, 0.15D, 0.175D);
            TileEntityRendererDispatcher.a(p_a_2_, p_a_4_, p_a_6_, (double)p_a_8_, (double)f, (double)awf.D().R(), 0, -i, afloat, 0.15D, 0.175D);
        }

        super.a(p_a_1_, p_a_2_, p_a_4_, p_a_6_, p_a_8_, p_a_9_, p_a_10_);
        ItemRenderer.o();
    }

    protected int a(double p_a_1_)
    {
        return super.a(p_a_1_) + 1;
    }

    protected float c()
    {
        return 1.0F;
    }
}
