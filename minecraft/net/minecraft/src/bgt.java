package net.minecraft.src;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import java.util.Random;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.entity.EntityList;

public interface bgt
{
    boolean a(Random var1, EntityList var2);

    public abstract static class a<T extends bgt>
    {
        private final BehaviorProjectileDispense a;
        private final Class<T> b;

        protected a(BehaviorProjectileDispense p_i2131_1_, Class<T> p_i2131_2_)
        {
            this.a = p_i2131_1_;
            this.b = p_i2131_2_;
        }

        public BehaviorProjectileDispense a()
        {
            return this.a;
        }

        public Class<T> b()
        {
            return this.b;
        }

        public abstract JsonElement a(T var1, JsonSerializationContext var2);

        public abstract T a(JsonElement var1, JsonDeserializationContext var2);
    }
}
