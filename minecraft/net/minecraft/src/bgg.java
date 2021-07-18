package net.minecraft.src;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import java.util.Random;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.init.Items;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.LowerStringMap;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraft.world.storage.loot.conditions.EntityHasProperty;
import net.minecraft.world.storage.loot.functions.LootingEnchantBonus;

public class bgg extends LootingEnchantBonus
{
    private final NBTTagCompound a;

    public bgg(EntityHasProperty[] p_i2123_1_, NBTTagCompound p_i2123_2_)
    {
        super(p_i2123_1_);
        this.a = p_i2123_2_;
    }

    public Items a(Items p_a_1_, Random p_a_2_, LootTableManager p_a_3_)
    {
        NBTTagCompound nbttagcompound = p_a_1_.p();

        if (nbttagcompound == null)
        {
            nbttagcompound = this.a.copy();
        }
        else
        {
            nbttagcompound.merge(this.a);
        }

        p_a_1_.b(nbttagcompound);
        return p_a_1_;
    }

    public static class a extends LootingEnchantBonus.Serializer<bgg>
    {
        public a()
        {
            super(new BehaviorProjectileDispense("set_nbt"), bgg.class);
        }

        public void a(JsonObject p_a_1_, bgg p_a_2_, JsonSerializationContext p_a_3_)
        {
            p_a_1_.addProperty("tag", p_a_2_.a.toString());
        }

        public bgg a(JsonObject p_a_1_, JsonDeserializationContext p_a_2_, EntityHasProperty[] p_a_3_)
        {
            try
            {
                NBTTagCompound nbttagcompound = JsonToNBT.getTagFromJson(LowerStringMap.h(p_a_1_, "tag"));
                return new bgg(p_a_3_, nbttagcompound);
            }
            catch (NBTException nbtexception)
            {
                throw new JsonSyntaxException(nbtexception);
            }
        }
    }
}
