package net.minecraft.src;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.pathfinding.PathNavigateFlying;

public class yz extends PathNavigateFlying
{
    IAttributeInstance a;
    EnumCreatureType b;
    private int c;

    public yz(IAttributeInstance p_i1157_1_)
    {
        super(p_i1157_1_, false);
        this.a = p_i1157_1_;
        this.a(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!this.a.dl())
        {
            return false;
        }
        else
        {
            EnumCreatureType enumcreaturetype = this.a.do();

            if (enumcreaturetype == null)
            {
                return false;
            }
            else
            {
                this.b = enumcreaturetype.bU();
                int i = enumcreaturetype.bV();
                return i != this.c && this.a(this.b, false) && this.a.a(this.b, enumcreaturetype);
            }
        }
    }

    public void c()
    {
        this.wantsToReapStuff.d(this.b);
        EnumCreatureType enumcreaturetype = this.a.do();

        if (enumcreaturetype != null)
        {
            this.c = enumcreaturetype.bV();
        }

        super.c();
    }
}
