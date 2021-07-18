package net.minecraft.src;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public enum aee
{
    a(0, "cape"),
    b(1, "jacket"),
    c(2, "left_sleeve"),
    d(3, "right_sleeve"),
    e(4, "left_pants_leg"),
    f(5, "right_pants_leg"),
    g(6, "hat");

    private final int h;
    private final int i;
    private final String j;
    private final ITextComponent k;

    private aee(int p_i1362_3_, String p_i1362_4_)
    {
        this.h = p_i1362_3_;
        this.i = 1 << p_i1362_3_;
        this.j = p_i1362_4_;
        this.k = new TextComponentTranslation("options.modelPart." + p_i1362_4_, new Object[0]);
    }

    public int a()
    {
        return this.i;
    }

    public int b()
    {
        return this.h;
    }

    public String c()
    {
        return this.j;
    }

    public ITextComponent d()
    {
        return this.k;
    }
}
