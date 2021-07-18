package net.minecraft.src;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.crafting.RecipesArmorDyes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

public class aki
{
    public static List<va> a(Items p_a_0_)
    {
        return a(p_a_0_.p());
    }

    public static List<va> a(PotionUtils p_a_0_, Collection<va> p_a_1_)
    {
        List<va> list = Lists.<va>newArrayList();
        list.addAll(p_a_0_.a());
        list.addAll(p_a_1_);
        return list;
    }

    public static List<va> a(@Nullable NBTTagCompound p_a_0_)
    {
        List<va> list = Lists.<va>newArrayList();
        list.addAll(c(p_a_0_).a());
        a(p_a_0_, list);
        return list;
    }

    public static List<va> b(Items p_b_0_)
    {
        return b(p_b_0_.p());
    }

    public static List<va> b(@Nullable NBTTagCompound p_b_0_)
    {
        List<va> list = Lists.<va>newArrayList();
        a(p_b_0_, list);
        return list;
    }

    public static void a(@Nullable NBTTagCompound p_a_0_, List<va> p_a_1_)
    {
        if (p_a_0_ != null && p_a_0_.hasKey("CustomPotionEffects", 9))
        {
            NBTTagList nbttaglist = p_a_0_.getTagList("CustomPotionEffects", 10);

            for (int i = 0; i < nbttaglist.tagCount(); ++i)
            {
                NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
                va va = va.b(nbttagcompound);

                if (va != null)
                {
                    p_a_1_.add(va);
                }
            }
        }
    }

    public static int c(Items p_c_0_)
    {
        NBTTagCompound nbttagcompound = p_c_0_.p();

        if (nbttagcompound != null && nbttagcompound.hasKey("CustomPotionColor", 99))
        {
            return nbttagcompound.getInteger("CustomPotionColor");
        }
        else
        {
            return d(p_c_0_) == RecipesArmorDyes.a ? 16253176 : a(a(p_c_0_));
        }
    }

    public static int a(PotionUtils p_a_0_)
    {
        return p_a_0_ == RecipesArmorDyes.a ? 16253176 : a(p_a_0_.a());
    }

    public static int a(Collection<va> p_a_0_)
    {
        int i = 3694022;

        if (p_a_0_.isEmpty())
        {
            return 3694022;
        }
        else
        {
            float f = 0.0F;
            float f1 = 0.0F;
            float f2 = 0.0F;
            int j = 0;

            for (va va : p_a_0_)
            {
                if (va.e())
                {
                    int k = va.a().g();
                    int l = va.c() + 1;
                    f += (float)(l * (k >> 16 & 255)) / 255.0F;
                    f1 += (float)(l * (k >> 8 & 255)) / 255.0F;
                    f2 += (float)(l * (k >> 0 & 255)) / 255.0F;
                    j += l;
                }
            }

            if (j == 0)
            {
                return 0;
            }
            else
            {
                f = f / (float)j * 255.0F;
                f1 = f1 / (float)j * 255.0F;
                f2 = f2 / (float)j * 255.0F;
                return (int)f << 16 | (int)f1 << 8 | (int)f2;
            }
        }
    }

    public static PotionUtils d(Items p_d_0_)
    {
        return c(p_d_0_.p());
    }

    public static PotionUtils c(@Nullable NBTTagCompound p_c_0_)
    {
        return p_c_0_ == null ? RecipesArmorDyes.a : PotionUtils.a(p_c_0_.getString("Potion"));
    }

    public static Items a(Items p_a_0_, PotionUtils p_a_1_)
    {
        BehaviorProjectileDispense behaviorprojectiledispense = PotionUtils.a.getNameForObject(p_a_1_);

        if (p_a_1_ == RecipesArmorDyes.a)
        {
            if (p_a_0_.o())
            {
                NBTTagCompound nbttagcompound = p_a_0_.p();
                nbttagcompound.removeTag("Potion");

                if (nbttagcompound.hasNoTags())
                {
                    p_a_0_.b((NBTTagCompound)null);
                }
            }
        }
        else
        {
            NBTTagCompound nbttagcompound1 = p_a_0_.o() ? p_a_0_.p() : new NBTTagCompound();
            nbttagcompound1.setString("Potion", behaviorprojectiledispense.toString());
            p_a_0_.b(nbttagcompound1);
        }

        return p_a_0_;
    }

    public static Items a(Items p_a_0_, Collection<va> p_a_1_)
    {
        if (p_a_1_.isEmpty())
        {
            return p_a_0_;
        }
        else
        {
            NBTTagCompound nbttagcompound = (NBTTagCompound)MoreObjects.firstNonNull(p_a_0_.p(), new NBTTagCompound());
            NBTTagList nbttaglist = nbttagcompound.getTagList("CustomPotionEffects", 9);

            for (va va : p_a_1_)
            {
                nbttaglist.appendTag(va.a(new NBTTagCompound()));
            }

            nbttagcompound.setTag("CustomPotionEffects", nbttaglist);
            p_a_0_.b(nbttagcompound);
            return p_a_0_;
        }
    }

    public static void a(Items p_a_0_, List<String> p_a_1_, float p_a_2_)
    {
        List<va> list = a(p_a_0_);
        List<rr<String, AbstractAttributeMap>> list1 = Lists.<rr<String, AbstractAttributeMap>>newArrayList();

        if (list.isEmpty())
        {
            String s = I18n.translateToLocal("effect.none").trim();
            p_a_1_.add(TextFormatting.GRAY + s);
        }
        else
        {
            for (va va : list)
            {
                String s1 = I18n.translateToLocal(va.f()).trim();
                MobEffects mobeffects = va.a();
                Map<AttributeModifier, AbstractAttributeMap> map = mobeffects.h();

                if (!map.isEmpty())
                {
                    for (Entry<AttributeModifier, AbstractAttributeMap> entry : map.entrySet())
                    {
                        AbstractAttributeMap abstractattributemap = entry.getValue();
                        AbstractAttributeMap abstractattributemap1 = new AbstractAttributeMap(abstractattributemap.b(), mobeffects.a(va.c(), abstractattributemap), abstractattributemap.c());
                        list1.add(new rr(((AttributeModifier)entry.getKey()).a(), abstractattributemap1));
                    }
                }

                if (va.c() > 0)
                {
                    s1 = s1 + " " + I18n.translateToLocal("potion.potency." + va.c()).trim();
                }

                if (va.b() > 20)
                {
                    s1 = s1 + " (" + MobEffects.a(va, p_a_2_) + ")";
                }

                if (mobeffects.e())
                {
                    p_a_1_.add(TextFormatting.RED + s1);
                }
                else
                {
                    p_a_1_.add(TextFormatting.BLUE + s1);
                }
            }
        }

        if (!list1.isEmpty())
        {
            p_a_1_.add("");
            p_a_1_.add(TextFormatting.DARK_PURPLE + I18n.translateToLocal("potion.whenDrank"));

            for (rr<String, AbstractAttributeMap> rr : list1)
            {
                AbstractAttributeMap abstractattributemap2 = rr.b();
                double d0 = abstractattributemap2.d();
                double d1;

                if (abstractattributemap2.c() != 1 && abstractattributemap2.c() != 2)
                {
                    d1 = abstractattributemap2.d();
                }
                else
                {
                    d1 = abstractattributemap2.d() * 100.0D;
                }

                if (d0 > 0.0D)
                {
                    p_a_1_.add(TextFormatting.BLUE + I18n.translateToLocalFormatted("attribute.modifier.plus." + abstractattributemap2.c(), Items.IRON_SHOVEL.format(d1), I18n.translateToLocal("attribute.name." + (String)rr.a())));
                }
                else if (d0 < 0.0D)
                {
                    d1 = d1 * -1.0D;
                    p_a_1_.add(TextFormatting.RED + I18n.translateToLocalFormatted("attribute.modifier.take." + abstractattributemap2.c(), Items.IRON_SHOVEL.format(d1), I18n.translateToLocal("attribute.name." + (String)rr.a())));
                }
            }
        }
    }
}
