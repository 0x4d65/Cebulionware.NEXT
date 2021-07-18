package net.minecraft.src;

import net.minecraft.client.resources.ResourcePackListEntryDefault;
import net.minecraft.client.resources.ResourcePackListEntryFound;
import net.minecraft.client.resources.SimpleResource;

public class bnz extends ResourcePackListEntryFound
{
    private final SimpleResource.a c;

    public bnz(ResourcePackListEntryDefault p_i168_1_, SimpleResource.a p_i168_2_)
    {
        super(p_i168_1_);
        this.c = p_i168_2_;
    }

    protected void bindResourcePackIcon()
    {
        this.c.a(this.client.N());
    }

    protected int getResourcePackFormat()
    {
        return this.c.f();
    }

    protected String getResourcePackDescription()
    {
        return this.c.e();
    }

    protected String getResourcePackName()
    {
        return this.c.d();
    }

    public SimpleResource.a k()
    {
        return this.c;
    }
}
