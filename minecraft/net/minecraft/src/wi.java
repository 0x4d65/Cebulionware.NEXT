package net.minecraft.src;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import net.minecraft.entity.EntityBodyHelper;
import net.minecraft.entity.ai.attributes.AttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.util.IntHashMap;

public class wi extends AttributeMap
{
    private final Set<BaseAttribute> e = Sets.<BaseAttribute>newHashSet();
    protected final Map<String, BaseAttribute> descriptionToAttributeInstanceMap = new IntHashMap();

    public RangedAttribute e(AttributeModifier p_e_1_)
    {
        return (RangedAttribute)super.a(p_e_1_);
    }

    public RangedAttribute b(String p_b_1_)
    {
        BaseAttribute baseattribute = super.a(p_b_1_);

        if (baseattribute == null)
        {
            baseattribute = this.descriptionToAttributeInstanceMap.get(p_b_1_);
        }

        return (RangedAttribute)baseattribute;
    }

    public BaseAttribute b(AttributeModifier p_b_1_)
    {
        BaseAttribute baseattribute = super.b(p_b_1_);

        if (p_b_1_ instanceof EntityBodyHelper && ((EntityBodyHelper)p_b_1_).g() != null)
        {
            this.descriptionToAttributeInstanceMap.put(((EntityBodyHelper)p_b_1_).g(), baseattribute);
        }

        return baseattribute;
    }

    protected BaseAttribute c(AttributeModifier p_c_1_)
    {
        return new RangedAttribute(this, p_c_1_);
    }

    public void a(BaseAttribute p_a_1_)
    {
        if (p_a_1_.a().c())
        {
            this.e.add(p_a_1_);
        }

        for (AttributeModifier attributemodifier : this.c.get(p_a_1_.a()))
        {
            RangedAttribute rangedattribute = this.e(attributemodifier);

            if (rangedattribute != null)
            {
                rangedattribute.f();
            }
        }
    }

    public Set<BaseAttribute> getAttributeInstanceSet()
    {
        return this.e;
    }

    public Collection<BaseAttribute> getWatchedAttributes()
    {
        Set<BaseAttribute> set = Sets.<BaseAttribute>newHashSet();

        for (BaseAttribute baseattribute : this.a())
        {
            if (baseattribute.a().c())
            {
                set.add(baseattribute);
            }
        }

        return set;
    }
}
