package net.minecraft.src;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.client.renderer.block.model.multipart.ConditionAnd;
import net.minecraft.client.renderer.block.model.multipart.ConditionOr;
import net.minecraft.client.renderer.block.model.multipart.ConditionPropertyValue;
import net.minecraft.client.renderer.block.model.multipart.Multipart;
import net.minecraft.util.LowerStringMap;

public class bwk
{
    private final Multipart a;
    private final ConditionAnd b;

    public bwk(Multipart p_i599_1_, ConditionAnd p_i599_2_)
    {
        if (p_i599_1_ == null)
        {
            throw new IllegalArgumentException("Missing condition for selector");
        }
        else if (p_i599_2_ == null)
        {
            throw new IllegalArgumentException("Missing variant for selector");
        }
        else
        {
            this.a = p_i599_1_;
            this.b = p_i599_2_;
        }
    }

    public ConditionAnd a()
    {
        return this.b;
    }

    public Predicate<awt> a(BlockWorldState p_a_1_)
    {
        return this.a.a(p_a_1_);
    }

    public boolean equals(Object p_equals_1_)
    {
        if (this == p_equals_1_)
        {
            return true;
        }
        else
        {
            if (p_equals_1_ instanceof bwk)
            {
                bwk bwk = (bwk)p_equals_1_;

                if (this.a.equals(bwk.a))
                {
                    return this.b.equals(bwk.b);
                }
            }

            return false;
        }
    }

    public int hashCode()
    {
        return 31 * this.a.hashCode() + this.b.hashCode();
    }

    public static class a implements JsonDeserializer<bwk>
    {
        private static final Function<JsonElement, Multipart> a = new Function<JsonElement, Multipart>()
        {
            @Nullable
            public Multipart a(@Nullable JsonElement p_a_1_)
            {
                return p_a_1_ == null ? null : bwk.a.a(p_a_1_.getAsJsonObject());
            }
        };
        private static final Function<Entry<String, JsonElement>, Multipart> b = new Function<Entry<String, JsonElement>, Multipart>()
        {
            @Nullable
            public Multipart a(@Nullable Entry<String, JsonElement> p_a_1_)
            {
                return p_a_1_ == null ? null : bwk.a.b(p_a_1_);
            }
        };

        public bwk a(JsonElement p_a_1_, Type p_a_2_, JsonDeserializationContext p_a_3_) throws JsonParseException
        {
            JsonObject jsonobject = p_a_1_.getAsJsonObject();
            return new bwk(this.b(jsonobject), (ConditionAnd)p_a_3_.deserialize(jsonobject.get("apply"), ConditionAnd.class));
        }

        private Multipart b(JsonObject p_b_1_)
        {
            return p_b_1_.has("when") ? a(LowerStringMap.t(p_b_1_, "when")) : Multipart.selectors;
        }

        @VisibleForTesting
        static Multipart a(JsonObject p_a_0_)
        {
            Set<Entry<String, JsonElement>> set = p_a_0_.entrySet();

            if (set.isEmpty())
            {
                throw new JsonParseException("No elements found in selector");
            }
            else if (set.size() == 1)
            {
                if (p_a_0_.has("OR"))
                {
                    return new bwj(Iterables.transform(LowerStringMap.u(p_a_0_, "OR"), a));
                }
                else
                {
                    return (Multipart)(p_a_0_.has("AND") ? new ConditionPropertyValue(Iterables.transform(LowerStringMap.u(p_a_0_, "AND"), a)) : b(set.iterator().next()));
                }
            }
            else
            {
                return new ConditionPropertyValue(Iterables.transform(set, b));
            }
        }

        private static ConditionOr b(Entry<String, JsonElement> p_b_0_)
        {
            return new ConditionOr(p_b_0_.getKey(), ((JsonElement)p_b_0_.getValue()).getAsString());
        }
    }
}
