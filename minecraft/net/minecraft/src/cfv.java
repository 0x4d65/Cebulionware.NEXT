package net.minecraft.src;

import net.minecraft.client.resources.data.MetadataSerializer;

public class cfv implements MetadataSerializer
{
    private final boolean a;
    private final boolean b;

    public cfv(boolean p_i308_1_, boolean p_i308_2_)
    {
        this.a = p_i308_1_;
        this.b = p_i308_2_;
    }

    public boolean a()
    {
        return this.a;
    }

    public boolean b()
    {
        return this.b;
    }
}
