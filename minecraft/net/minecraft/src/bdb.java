package net.minecraft.src;

import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MaterialPortal;

public class bdb extends MaterialPortal
{
    public bdb(EnumPushReaction p_i2045_1_)
    {
        super(p_i2045_1_);
    }

    /**
     * Returns true if the block is a considered solid. This is true by default.
     */
    public boolean isSolid()
    {
        return false;
    }

    /**
     * Will prevent grass from growing on dirt underneath and kill any grass below it if it returns true
     */
    public boolean blocksLight()
    {
        return false;
    }

    /**
     * Returns if this material is considered solid or not
     */
    public boolean blocksMovement()
    {
        return false;
    }
}
