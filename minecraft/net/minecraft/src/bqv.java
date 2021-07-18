package net.minecraft.src;

import net.minecraft.client.model.ModelParrot;
import net.minecraft.entity.EntityList;

public class bqv extends ModelParrot
{
    public brs field_192764_a;

    public bqv()
    {
        this(0, 35, 64, 64);
    }

    public bqv(int p_i258_1_, int p_i258_2_, int p_i258_3_, int p_i258_4_)
    {
        this.s = p_i258_3_;
        this.t = p_i258_4_;
        this.field_192764_a = new brs(this, p_i258_1_, p_i258_2_);
        this.field_192764_a.a(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.field_192764_a.a(0.0F, 0.0F, 0.0F);
    }

    public void a(EntityList p_a_1_, float p_a_2_, float p_a_3_, float p_a_4_, float p_a_5_, float p_a_6_, float p_a_7_)
    {
        this.a(p_a_2_, p_a_3_, p_a_4_, p_a_5_, p_a_6_, p_a_7_, p_a_1_);
        this.field_192764_a.a(p_a_7_);
    }

    public void a(float p_a_1_, float p_a_2_, float p_a_3_, float p_a_4_, float p_a_5_, float p_a_6_, EntityList p_a_7_)
    {
        super.a(p_a_1_, p_a_2_, p_a_3_, p_a_4_, p_a_5_, p_a_6_, p_a_7_);
        this.field_192764_a.g = p_a_4_ * 0.017453292F;
        this.field_192764_a.f = p_a_5_ * 0.017453292F;
    }
}
