package net.minecraft.src;

import net.minecraft.client.renderer.EnumFaceDirection;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.chunk.IRenderChunkFactory;
import net.minecraft.client.renderer.chunk.VboChunkFactory;

public class bva extends EnumFaceDirection
{
    public void a(amm p_a_1_)
    {
        if (this.UP)
        {
            for (VboChunkFactory vbochunkfactory : this.DOWN)
            {
                IRenderChunkFactory irenderchunkfactory = (IRenderChunkFactory)vbochunkfactory;
                ItemRenderer.G();
                this.a(vbochunkfactory);
                ItemRenderer.s(irenderchunkfactory.a(p_a_1_, irenderchunkfactory.h()));
                ItemRenderer.H();
            }

            ItemRenderer.I();
            this.DOWN.clear();
        }
    }
}
