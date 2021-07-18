package net.minecraft.src;

import net.minecraft.client.resources.data.MetadataSerializer;
import net.minecraft.util.text.ITextComponent;

public class cfr implements MetadataSerializer
{
    private final ITextComponent a;
    private final int b;

    public cfr(ITextComponent p_i309_1_, int p_i309_2_)
    {
        this.a = p_i309_1_;
        this.b = p_i309_2_;
    }

    public ITextComponent a()
    {
        return this.a;
    }

    public int b()
    {
        return this.b;
    }
}
