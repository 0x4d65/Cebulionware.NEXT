package net.minecraft.src;

import net.minecraft.scoreboard.IScoreCriteria;
import net.minecraft.util.TupleIntJsonSerializable;

public class bhs extends IScoreCriteria
{
    private final TupleIntJsonSerializable o;

    public bhs(TupleIntJsonSerializable p_i2156_1_)
    {
        super(p_i2156_1_.integerValue);
        this.o = p_i2156_1_;
    }
}
