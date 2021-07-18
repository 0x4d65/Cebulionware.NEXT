package net.minecraft.src;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.block.model.BuiltInModel;
import net.minecraft.client.renderer.block.model.MultipartBakedModel;
import net.minecraft.client.renderer.block.model.SimpleBakedModel;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class buv
{
    private final Map<Integer, SimpleBakedModel> a = Maps.<Integer, SimpleBakedModel>newHashMap();
    private final Map<Integer, BuiltInModel> b = Maps.<Integer, BuiltInModel>newHashMap();
    private final Map<ItemStack, RenderGlobal> c = Maps.<ItemStack, RenderGlobal>newHashMap();
    private final MultipartBakedModel d;

    public buv(MultipartBakedModel p_i401_1_)
    {
        this.d = p_i401_1_;
    }

    public ITextureObject a(ItemStack p_a_1_)
    {
        return this.a(p_a_1_, 0);
    }

    public ITextureObject a(ItemStack p_a_1_, int p_a_2_)
    {
        return this.a(new Items(p_a_1_, 1, p_a_2_)).d();
    }

    public BuiltInModel a(Items p_a_1_)
    {
        ItemStack itemstack = p_a_1_.c();
        BuiltInModel builtinmodel = this.b(itemstack, this.b(p_a_1_));

        if (builtinmodel == null)
        {
            RenderGlobal renderglobal = this.c.get(itemstack);

            if (renderglobal != null)
            {
                builtinmodel = this.d.a(renderglobal.a(p_a_1_));
            }
        }

        if (builtinmodel == null)
        {
            builtinmodel = this.d.a();
        }

        return builtinmodel;
    }

    protected int b(Items p_b_1_)
    {
        return p_b_1_.k() > 0 ? 0 : p_b_1_.j();
    }

    @Nullable
    protected BuiltInModel b(ItemStack p_b_1_, int p_b_2_)
    {
        return this.b.get(Integer.valueOf(this.c(p_b_1_, p_b_2_)));
    }

    private int c(ItemStack p_c_1_, int p_c_2_)
    {
        return ItemStack.a(p_c_1_) << 16 | p_c_2_;
    }

    public void a(ItemStack p_a_1_, int p_a_2_, SimpleBakedModel p_a_3_)
    {
        this.a.put(Integer.valueOf(this.c(p_a_1_, p_a_2_)), p_a_3_);
        this.b.put(Integer.valueOf(this.c(p_a_1_, p_a_2_)), this.d.a(p_a_3_));
    }

    public void a(ItemStack p_a_1_, RenderGlobal p_a_2_)
    {
        this.c.put(p_a_1_, p_a_2_);
    }

    public MultipartBakedModel a()
    {
        return this.d;
    }

    public void b()
    {
        this.b.clear();

        for (Entry<Integer, SimpleBakedModel> entry : this.a.entrySet())
        {
            this.b.put(entry.getKey(), this.d.a(entry.getValue()));
        }
    }
}
