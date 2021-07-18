package net.minecraft.src;

import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.util.math.Vec2f;

public class bya implements Frustum
{
    private final byb a;
    private double b;
    private double c;
    private double d;

    public bya()
    {
        this(ClippingHelper.a());
    }

    public bya(byb p_i559_1_)
    {
        this.a = p_i559_1_;
    }

    public void setPosition(double p_78547_1_, double p_78547_3_, double p_78547_5_)
    {
        this.b = p_78547_1_;
        this.c = p_78547_3_;
        this.d = p_78547_5_;
    }

    /**
     * Calls the clipping helper. Returns true if the box is inside all 6 clipping planes, otherwise returns false.
     */
    public boolean isBoxInFrustum(double p_78548_1_, double p_78548_3_, double p_78548_5_, double p_78548_7_, double p_78548_9_, double p_78548_11_)
    {
        return this.a.b(p_78548_1_ - this.b, p_78548_3_ - this.c, p_78548_5_ - this.d, p_78548_7_ - this.b, p_78548_9_ - this.c, p_78548_11_ - this.d);
    }

    public boolean a(Vec2f p_a_1_)
    {
        return this.isBoxInFrustum(p_a_1_.ZERO, p_a_1_.ONE, p_a_1_.UNIT_X, p_a_1_.NEGATIVE_UNIT_X, p_a_1_.UNIT_Y, p_a_1_.NEGATIVE_UNIT_Y);
    }
}
