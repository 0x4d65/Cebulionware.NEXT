package net.minecraft.src;

import java.util.BitSet;
import java.util.Set;
import net.minecraft.util.EnumFacing;

public class bxv
{
    private static final int a = EnumFacing.values().length;
    private final BitSet b;

    public bxv()
    {
        this.b = new BitSet(a * a);
    }

    public void a(Set<EnumFacing> p_a_1_)
    {
        for (EnumFacing enumfacing : p_a_1_)
        {
            for (EnumFacing enumfacing1 : p_a_1_)
            {
                this.a(enumfacing, enumfacing1, true);
            }
        }
    }

    public void a(EnumFacing p_a_1_, EnumFacing p_a_2_, boolean p_a_3_)
    {
        this.b.set(p_a_1_.ordinal() + p_a_2_.ordinal() * a, p_a_3_);
        this.b.set(p_a_2_.ordinal() + p_a_1_.ordinal() * a, p_a_3_);
    }

    public void a(boolean p_a_1_)
    {
        this.b.set(0, this.b.size(), p_a_1_);
    }

    public boolean a(EnumFacing p_a_1_, EnumFacing p_a_2_)
    {
        return this.b.get(p_a_1_.ordinal() + p_a_2_.ordinal() * a);
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(' ');

        for (EnumFacing enumfacing : EnumFacing.values())
        {
            stringbuilder.append(' ').append(enumfacing.toString().toUpperCase().charAt(0));
        }

        stringbuilder.append('\n');

        for (EnumFacing enumfacing2 : EnumFacing.values())
        {
            stringbuilder.append(enumfacing2.toString().toUpperCase().charAt(0));

            for (EnumFacing enumfacing1 : EnumFacing.values())
            {
                if (enumfacing2 == enumfacing1)
                {
                    stringbuilder.append("  ");
                }
                else
                {
                    boolean flag = this.a(enumfacing2, enumfacing1);
                    stringbuilder.append(' ').append((char)(flag ? 'Y' : 'n'));
                }
            }

            stringbuilder.append('\n');
        }

        return stringbuilder.toString();
    }
}
