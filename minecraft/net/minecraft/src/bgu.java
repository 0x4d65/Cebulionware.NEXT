package net.minecraft.src;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import java.util.Random;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.entity.EntityList;
import net.minecraft.util.LowerStringMap;

public class bgu implements bgt
{
    private final boolean a;

    public bgu(boolean p_i2132_1_)
    {
        this.a = p_i2132_1_;
    }

    public boolean a(Random p_a_1_, EntityList p_a_2_)
    {
        return p_a_2_.aR() == this.a;
    }

    public static class a extends bgt.a<bgu>
    {
        protected a()
        {
            super(new BehaviorProjectileDispense("on_fire"), bgu.class);
        }

        public JsonElement a(bgu p_a_1_, JsonSerializationContext p_a_2_)
        {
            return new JsonPrimitive(p_a_1_.a);
        }

        public bgu b(JsonElement p_b_1_, JsonDeserializationContext p_b_2_)
        {
            return new bgu(LowerStringMap.c(p_b_1_, "on_fire"));
        }
    }
}
