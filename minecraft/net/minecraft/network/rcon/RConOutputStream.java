package net.minecraft.network.rcon;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.server.management.UserListBans;

public class RConOutputStream extends UserListBans<GameProfile>
{
    public RConOutputStream(GameProfile p_i994_1_)
    {
        super(p_i994_1_);
    }

    public RConOutputStream(JsonObject p_i995_1_)
    {
        super(b(p_i995_1_), p_i995_1_);
    }

    protected void a(JsonObject p_a_1_)
    {
        if (this.f() != null)
        {
            p_a_1_.addProperty("uuid", ((GameProfile)this.f()).getId() == null ? "" : ((GameProfile)this.f()).getId().toString());
            p_a_1_.addProperty("name", ((GameProfile)this.f()).getName());
            super.a(p_a_1_);
        }
    }

    private static GameProfile b(JsonObject p_b_0_)
    {
        if (p_b_0_.has("uuid") && p_b_0_.has("name"))
        {
            String s = p_b_0_.get("uuid").getAsString();
            UUID uuid;

            try
            {
                uuid = UUID.fromString(s);
            }
            catch (Throwable var4)
            {
                return null;
            }

            return new GameProfile(uuid, p_b_0_.get("name").getAsString());
        }
        else
        {
            return null;
        }
    }
}
