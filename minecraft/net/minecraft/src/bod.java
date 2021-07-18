package net.minecraft.src;

import java.util.List;
import net.minecraft.client.gui.GuiOptionsRowList;
import net.minecraft.client.renderer.VertexBufferUploader;
import net.minecraft.client.resources.ResourcePackListEntryFound;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.text.TextFormatting;

public abstract class bod extends GuiOptionsRowList
{
    protected final GameSettings options;
    protected final List<ResourcePackListEntryFound> v;

    public bod(GameSettings p_i163_1_, int p_i163_2_, int p_i163_3_, List<ResourcePackListEntryFound> p_i163_4_)
    {
        super(p_i163_1_, p_i163_2_, p_i163_3_, 32, p_i163_3_ - 55 + 4, 36);
        this.options = p_i163_1_;
        this.v = p_i163_4_;
        this.k = false;
        this.a(true, (int)((float)p_i163_1_.fancyGraphics.field_191383_a * 1.5F));
    }

    protected void a(int p_a_1_, int p_a_2_, VertexBufferUploader p_a_3_)
    {
        String s = TextFormatting.UNDERLINE + "" + TextFormatting.BOLD + this.e();
        this.options.fancyGraphics.a(s, p_a_1_ + this.b / 2 - this.options.fancyGraphics.a(s) / 2, Math.min(this.d + 3, p_a_2_), 16777215);
    }

    protected abstract String e();

    public List<ResourcePackListEntryFound> f()
    {
        return this.v;
    }

    protected int getSize()
    {
        return this.f().size();
    }

    public ResourcePackListEntryFound c(int p_c_1_)
    {
        return (ResourcePackListEntryFound)this.f().get(p_c_1_);
    }

    /**
     * Gets the width of the list
     */
    public int getListWidth()
    {
        return this.b;
    }

    protected int getScrollBarX()
    {
        return this.f - 6;
    }
}
