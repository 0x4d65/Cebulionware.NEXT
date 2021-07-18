package net.minecraft.src;

import java.util.Collection;
import net.minecraft.client.resources.Locale;
import net.minecraft.client.resources.data.MetadataSerializer;

public class cfo implements MetadataSerializer
{
    private final Collection<Locale> a;

    public cfo(Collection<Locale> p_i303_1_)
    {
        this.a = p_i303_1_;
    }

    public Collection<Locale> a()
    {
        return this.a;
    }
}
