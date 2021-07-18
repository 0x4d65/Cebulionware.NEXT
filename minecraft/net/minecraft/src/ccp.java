package net.minecraft.src;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderWitherSkull;
import net.minecraft.client.renderer.entity.layers.LayerSlimeGel;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.IProgressUpdate;

public class ccp implements LayerSlimeGel<EntityArmorStand>
{
    private static final BehaviorProjectileDispense a = new BehaviorProjectileDispense("textures/entity/wither/wither_armor.png");
    private final RenderWitherSkull b;
    private final ModelZombie c = new ModelZombie(0.5F);

    public ccp(RenderWitherSkull p_i409_1_)
    {
        this.b = p_i409_1_;
    }

    public void a(EntityArmorStand p_a_1_, float p_a_2_, float p_a_3_, float p_a_4_, float p_a_5_, float p_a_6_, float p_a_7_, float p_a_8_)
    {
        if (p_a_1_.dn())
        {
            ItemRenderer.a(!p_a_1_.aX());
            this.b.a(a);
            ItemRenderer.n(5890);
            ItemRenderer.F();
            float f = (float)p_a_1_.T + p_a_4_;
            float f1 = IProgressUpdate.b(f * 0.02F) * 3.0F;
            float f2 = f * 0.01F;
            ItemRenderer.c(f1, f2, 0.0F);
            ItemRenderer.n(5888);
            ItemRenderer.m();
            float f3 = 0.5F;
            ItemRenderer.c(0.5F, 0.5F, 0.5F, 1.0F);
            ItemRenderer.g();
            ItemRenderer.a(ItemRenderer.r.e, ItemRenderer.l.e);
            this.c.a(p_a_1_, p_a_2_, p_a_3_, p_a_4_);
            this.c.a(this.b.b());
            GameSettings.z().chatVisibility.d(true);
            this.c.a(p_a_1_, p_a_2_, p_a_3_, p_a_5_, p_a_6_, p_a_7_, p_a_8_);
            GameSettings.z().chatVisibility.d(false);
            ItemRenderer.n(5890);
            ItemRenderer.F();
            ItemRenderer.n(5888);
            ItemRenderer.f();
            ItemRenderer.l();
        }
    }

    public boolean shouldCombineTextures()
    {
        return false;
    }
}
