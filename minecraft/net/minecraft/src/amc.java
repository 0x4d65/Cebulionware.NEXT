package net.minecraft.src;

import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityFlying;

public class amc extends EnchantmentHelper
{
    public amc(EnchantmentHelper.DamageIterator p_i1610_1_, EntityFlying... p_i1610_2_)
    {
        super(p_i1610_1_, EnchantmentData.f, p_i1610_2_);
        this.c("waterWorker");
    }

    public int a(int p_a_1_)
    {
        return 1;
    }

    public int b(int p_b_1_)
    {
        return this.a(p_b_1_) + 40;
    }

    public int b()
    {
        return 1;
    }
}
