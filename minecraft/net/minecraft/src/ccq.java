package net.minecraft.src;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderZombieVillager;
import net.minecraft.client.renderer.entity.layers.LayerSlimeGel;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.entity.passive.AbstractHorse;

public class ccq implements LayerSlimeGel<AbstractHorse>
{
    private static final BehaviorProjectileDispense a = new BehaviorProjectileDispense("textures/entity/wolf/wolf_collar.png");
    private final RenderZombieVillager b;

    public ccq(RenderZombieVillager p_i407_1_)
    {
        this.b = p_i407_1_;
    }

    public void a(AbstractHorse p_a_1_, float p_a_2_, float p_a_3_, float p_a_4_, float p_a_5_, float p_a_6_, float p_a_7_, float p_a_8_)
    {
        if (p_a_1_.dl() && !p_a_1_.aX())
        {
            this.b.a(a);
            float[] afloat = p_a_1_.dw().f();
            ItemRenderer.d(afloat[0], afloat[1], afloat[2]);
            this.b.b().a(p_a_1_, p_a_2_, p_a_3_, p_a_5_, p_a_6_, p_a_7_, p_a_8_);
        }
    }

    public boolean shouldCombineTextures()
    {
        return true;
    }
}
