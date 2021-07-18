package net.minecraft.src;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.client.renderer.block.model.BuiltInModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.datafix.IFixableData;

public class cgg implements BuiltInModel
{
    private final int a;
    private final List<cgg.b> b;
    private final BuiltInModel c;

    public cgg(List<cgg.b> p_i328_1_)
    {
        this.b = p_i328_1_;
        this.a = IFixableData.a(p_i328_1_);
        this.c = (p_i328_1_.get(0)).b;
    }

    private BuiltInModel a(long p_a_1_)
    {
        return ((cgg.b)IFixableData.a(this.b, Math.abs((int)p_a_1_ >> 16) % this.a)).b;
    }

    public List<BlockPartFace> a(@Nullable awt p_a_1_, @Nullable EnumFacing p_a_2_, long p_a_3_)
    {
        return this.a(p_a_3_).a(p_a_1_, p_a_2_, p_a_3_);
    }

    public boolean isAmbientOcclusion()
    {
        return this.c.isAmbientOcclusion();
    }

    public boolean isGui3d()
    {
        return this.c.isGui3d();
    }

    public boolean isBuiltInRenderer()
    {
        return this.c.isBuiltInRenderer();
    }

    public ITextureObject d()
    {
        return this.c.d();
    }

    public Variant e()
    {
        return this.c.e();
    }

    public ItemCameraTransforms f()
    {
        return this.c.f();
    }

    public static class a
    {
        private final List<cgg.b> a = Lists.<cgg.b>newArrayList();

        public cgg.a a(BuiltInModel p_a_1_, int p_a_2_)
        {
            this.a.add(new cgg.b(p_a_1_, p_a_2_));
            return this;
        }

        public cgg a()
        {
            Collections.sort(this.a);
            return new cgg(this.a);
        }

        public BuiltInModel b()
        {
            return (this.a.get(0)).b;
        }
    }

    static class b extends IFixableData.a implements Comparable<cgg.b>
    {
        protected final BuiltInModel b;

        public b(BuiltInModel p_i314_1_, int p_i314_2_)
        {
            super(p_i314_2_);
            this.b = p_i314_1_;
        }

        public int a(cgg.b p_a_1_)
        {
            return ComparisonChain.start().compare(p_a_1_.a, this.a).result();
        }

        public String toString()
        {
            return "MyWeighedRandomItem{weight=" + this.a + ", model=" + this.b + '}';
        }
    }
}
