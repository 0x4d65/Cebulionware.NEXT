package net.minecraft.src;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.block.model.BakedQuadRetextured;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.client.renderer.block.model.BuiltInModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public class cgf implements BuiltInModel
{
    protected final List<BlockPartFace> cameraTransforms;
    protected final Map<EnumFacing, List<BlockPartFace>> overrideList;
    protected final boolean c;
    protected final boolean d;
    protected final ITextureObject e;
    protected final Variant f;
    protected final ItemCameraTransforms g;

    public cgf(List<BlockPartFace> p_i313_1_, Map<EnumFacing, List<BlockPartFace>> p_i313_2_, boolean p_i313_3_, boolean p_i313_4_, ITextureObject p_i313_5_, Variant p_i313_6_, ItemCameraTransforms p_i313_7_)
    {
        this.cameraTransforms = p_i313_1_;
        this.overrideList = p_i313_2_;
        this.c = p_i313_3_;
        this.d = p_i313_4_;
        this.e = p_i313_5_;
        this.f = p_i313_6_;
        this.g = p_i313_7_;
    }

    public List<BlockPartFace> a(@Nullable awt p_a_1_, @Nullable EnumFacing p_a_2_, long p_a_3_)
    {
        return p_a_2_ == null ? this.cameraTransforms : (List)this.overrideList.get(p_a_2_);
    }

    public boolean isAmbientOcclusion()
    {
        return this.c;
    }

    public boolean isGui3d()
    {
        return this.d;
    }

    public boolean isBuiltInRenderer()
    {
        return false;
    }

    public ITextureObject d()
    {
        return this.e;
    }

    public Variant e()
    {
        return this.f;
    }

    public ItemCameraTransforms f()
    {
        return this.g;
    }

    public static class a
    {
        private final List<BlockPartFace> a;
        private final Map<EnumFacing, List<BlockPartFace>> b;
        private final ItemCameraTransforms c;
        private final boolean d;
        private ITextureObject e;
        private final boolean f;
        private final Variant g;

        public a(BakedQuadRetextured p_i325_1_, ItemCameraTransforms p_i325_2_)
        {
            this(p_i325_1_.b(), p_i325_1_.c(), p_i325_1_.j(), p_i325_2_);
        }

        public a(awt p_i326_1_, BuiltInModel p_i326_2_, ITextureObject p_i326_3_, BlockPos p_i326_4_)
        {
            this(p_i326_2_.isAmbientOcclusion(), p_i326_2_.isGui3d(), p_i326_2_.e(), p_i326_2_.f());
            this.e = p_i326_2_.d();
            long i = IProgressUpdate.a((Vec3i)p_i326_4_);

            for (EnumFacing enumfacing : EnumFacing.values())
            {
                this.a(p_i326_1_, p_i326_2_, p_i326_3_, enumfacing, i);
            }

            this.a(p_i326_1_, p_i326_2_, p_i326_3_, i);
        }

        private a(boolean p_i327_1_, boolean p_i327_2_, Variant p_i327_3_, ItemCameraTransforms p_i327_4_)
        {
            this.a = Lists.<BlockPartFace>newArrayList();
            this.b = Maps.newEnumMap(EnumFacing.class);

            for (EnumFacing enumfacing : EnumFacing.values())
            {
                this.b.put(enumfacing, Lists.newArrayList());
            }

            this.c = p_i327_4_;
            this.d = p_i327_1_;
            this.f = p_i327_2_;
            this.g = p_i327_3_;
        }

        private void a(awt p_a_1_, BuiltInModel p_a_2_, ITextureObject p_a_3_, EnumFacing p_a_4_, long p_a_5_)
        {
            for (BlockPartFace blockpartface : p_a_2_.a(p_a_1_, p_a_4_, p_a_5_))
            {
                this.a(p_a_4_, new ItemModelGenerator(blockpartface, p_a_3_));
            }
        }

        private void a(awt p_a_1_, BuiltInModel p_a_2_, ITextureObject p_a_3_, long p_a_4_)
        {
            for (BlockPartFace blockpartface : p_a_2_.a(p_a_1_, (EnumFacing)null, p_a_4_))
            {
                this.a(new ItemModelGenerator(blockpartface, p_a_3_));
            }
        }

        public cgf.a a(EnumFacing p_a_1_, BlockPartFace p_a_2_)
        {
            (this.b.get(p_a_1_)).add(p_a_2_);
            return this;
        }

        public cgf.a a(BlockPartFace p_a_1_)
        {
            this.a.add(p_a_1_);
            return this;
        }

        public cgf.a a(ITextureObject p_a_1_)
        {
            this.e = p_a_1_;
            return this;
        }

        public BuiltInModel b()
        {
            if (this.e == null)
            {
                throw new RuntimeException("Missing particle!");
            }
            else
            {
                return new cgf(this.a, this.b, this.d, this.f, this.e, this.g, this.c);
            }
        }
    }
}
