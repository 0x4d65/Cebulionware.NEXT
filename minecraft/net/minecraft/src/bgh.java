package net.minecraft.src;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Random;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.init.Items;
import net.minecraft.item.crafting.RecipesMapCloning;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraft.world.storage.loot.conditions.EntityHasProperty;
import net.minecraft.world.storage.loot.functions.LootingEnchantBonus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bgh extends LootingEnchantBonus
{
    private static final Logger a = LogManager.getLogger();

    public bgh(EntityHasProperty[] p_i2124_1_)
    {
        super(p_i2124_1_);
    }

    public Items a(Items p_a_1_, Random p_a_2_, LootTableManager p_a_3_)
    {
        if (p_a_1_.b())
        {
            return p_a_1_;
        }
        else
        {
            Items items = RecipesMapCloning.a().a(p_a_1_);

            if (items.b())
            {
                a.warn("Couldn't smelt {} because there is no smelting recipe", (Object)p_a_1_);
                return p_a_1_;
            }
            else
            {
                Items items1 = items.l();
                items1.e(p_a_1_.E());
                return items1;
            }
        }
    }

    public static class a extends LootingEnchantBonus.Serializer<bgh>
    {
        protected a()
        {
            super(new BehaviorProjectileDispense("furnace_smelt"), bgh.class);
        }

        public void a(JsonObject p_a_1_, bgh p_a_2_, JsonSerializationContext p_a_3_)
        {
        }

        public bgh a(JsonObject p_a_1_, JsonDeserializationContext p_a_2_, EntityHasProperty[] p_a_3_)
        {
            return new bgh(p_a_3_);
        }
    }
}
