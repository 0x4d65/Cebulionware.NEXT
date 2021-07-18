package net.minecraft.src;

import net.minecraft.client.gui.GuiButtonToggle;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.realms.RealmsScrolledSelectionList;

public class bkj extends GuiButtonToggle
{
    private final RealmsScrolledSelectionList u;

    public bkj(RealmsScrolledSelectionList p_i47_1_, int p_i47_2_, int p_i47_3_, int p_i47_4_, int p_i47_5_, int p_i47_6_)
    {
        super(GameSettings.z(), p_i47_2_, p_i47_3_, p_i47_4_, p_i47_5_, p_i47_6_);
        this.u = p_i47_1_;
    }

    protected int b()
    {
        return this.u.getItemCount();
    }

    protected void a(int p_a_1_, boolean p_a_2_, int p_a_3_, int p_a_4_)
    {
        this.u.selectItem(p_a_1_, p_a_2_, p_a_3_, p_a_4_);
    }

    protected boolean a(int p_a_1_)
    {
        return this.u.isSelectedItem(p_a_1_);
    }

    protected void a()
    {
        this.u.renderBackground();
    }

    protected void a(int p_a_1_, int p_a_2_, int p_a_3_, int p_a_4_, int p_a_5_, int p_a_6_, float p_a_7_)
    {
        this.u.renderItem(p_a_1_, p_a_2_, p_a_3_, p_a_4_, p_a_5_, p_a_6_);
    }

    public int e()
    {
        return this.b;
    }

    public int f()
    {
        return this.j;
    }

    public int g()
    {
        return this.i;
    }

    protected int k()
    {
        return this.u.getMaxPosition();
    }

    protected int d()
    {
        return this.u.getScrollbarPosition();
    }

    public void p()
    {
        super.p();
    }
}
