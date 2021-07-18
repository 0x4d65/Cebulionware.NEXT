package net.minecraft.src;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Random;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.util.LowerStringMap;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraft.world.storage.loot.conditions.EntityHasProperty;

public class bgp implements EntityHasProperty
{
    private final float a;

    public bgp(float p_i2129_1_)
    {
        this.a = p_i2129_1_;
    }

    public boolean a(Random p_a_1_, LootTableManager p_a_2_)
    {
        return p_a_1_.nextFloat() < this.a;
    }

    public static class a extends EntityHasProperty.Serializer<bgp>
    {
        protected a()
        {
            super(new BehaviorProjectileDispense("random_chance"), bgp.class);
        }

        public void a(JsonObject p_a_1_, bgp p_a_2_, JsonSerializationContext p_a_3_)
        {
            p_a_1_.addProperty("chance", Float.valueOf(p_a_2_.a));
        }

        public bgp a(JsonObject p_a_1_, JsonDeserializationContext p_a_2_)
        {
            return new bgp(LowerStringMap.l(p_a_1_, "chance"));
        }
    }
}
