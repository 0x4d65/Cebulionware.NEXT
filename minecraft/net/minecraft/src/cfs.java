package net.minecraft.src;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import net.minecraft.client.resources.data.IMetadataSectionSerializer;
import net.minecraft.util.LowerStringMap;
import net.minecraft.util.text.ITextComponent;

public class cfs extends IMetadataSectionSerializer<cfr> implements JsonSerializer<cfr>
{
    public cfr a(JsonElement p_a_1_, Type p_a_2_, JsonDeserializationContext p_a_3_) throws JsonParseException
    {
        JsonObject jsonobject = p_a_1_.getAsJsonObject();
        ITextComponent itextcomponent = (ITextComponent)p_a_3_.deserialize(jsonobject.get("description"), ITextComponent.class);

        if (itextcomponent == null)
        {
            throw new JsonParseException("Invalid/missing description!");
        }
        else
        {
            int i = LowerStringMap.n(jsonobject, "pack_format");
            return new cfr(itextcomponent, i);
        }
    }

    public JsonElement a(cfr p_a_1_, Type p_a_2_, JsonSerializationContext p_a_3_)
    {
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("pack_format", Integer.valueOf(p_a_1_.b()));
        jsonobject.add("description", p_a_3_.serialize(p_a_1_.a()));
        return jsonobject;
    }

    /**
     * The name of this section type as it appears in JSON.
     */
    public String getSectionName()
    {
        return "pack";
    }
}
