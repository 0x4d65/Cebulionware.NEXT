package net.minecraft.src;

import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentFishingSpeed;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityFlying;

public class amb extends EnchantmentHelper
{
    public amb(EnchantmentHelper.DamageIterator p_i1609_1_, EntityFlying... p_i1609_2_)
    {
        super(p_i1609_1_, EnchantmentData.enchantmentLevel, p_i1609_2_);
        this.c("waterWalker");
    }

    public int a(int p_a_1_)
    {
        return p_a_1_ * 10;
    }

    public int b(int p_b_1_)
    {
        return this.a(p_b_1_) + 15;
    }

    public int b()
    {
        return 3;
    }

    public boolean a(EnchantmentHelper p_a_1_)
    {
        return super.a(p_a_1_) && p_a_1_ != EnchantmentFishingSpeed.j;
    }
}
