package net.minecraft.src;

import javax.annotation.Nullable;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Items;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;

public class us extends EntityDamageSourceIndirect
{
    @Nullable
    protected EntityList v;
    private boolean w;

    public us(String p_i1036_1_, @Nullable EntityList p_i1036_2_)
    {
        super(p_i1036_1_);
        this.v = p_i1036_2_;
    }

    public us w()
    {
        this.w = true;
        return this;
    }

    public boolean x()
    {
        return this.w;
    }

    @Nullable
    public EntityList j()
    {
        return this.v;
    }

    public ITextComponent c(EnumCreatureType p_c_1_)
    {
        Items items = this.v instanceof EnumCreatureType ? ((EnumCreatureType)this.v).co() : Items.field_190931_a;
        String s = "death.attack." + this.u;
        String s1 = s + ".item";
        return !items.b() && items.t() && I18n.canTranslate(s1) ? new TextComponentTranslation(s1, new Object[] {p_c_1_.getDisplayName(), this.v.getDisplayName(), items.C()}) : new TextComponentTranslation(s, new Object[] {p_c_1_.getDisplayName(), this.v.getDisplayName()});
    }

    public boolean r()
    {
        return this.v != null && this.v instanceof EnumCreatureType && !(this.v instanceof RecipeItemHelper);
    }

    @Nullable
    public ScoreObjective v()
    {
        return new ScoreObjective(this.v.p, this.v.q, this.v.r);
    }
}
