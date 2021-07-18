package net.minecraft.src;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;
import net.minecraft.network.rcon.RConOutputStream;
import net.minecraft.server.management.UserListBans;
import net.minecraft.server.management.UserListBansEntry;

public class ps extends UserListBansEntry<GameProfile, RConOutputStream>
{
    public ps(File p_i993_1_)
    {
        super(p_i993_1_);
    }

    protected UserListBans<GameProfile> a(JsonObject p_a_1_)
    {
        return new RConOutputStream(p_a_1_);
    }

    public String[] a()
    {
        String[] astring = new String[this.e().size()];
        int i = 0;

        for (RConOutputStream rconoutputstream : this.e().values())
        {
            astring[i++] = ((GameProfile)rconoutputstream.f()).getName();
        }

        return astring;
    }

    protected String b(GameProfile p_b_1_)
    {
        return p_b_1_.getId().toString();
    }

    public GameProfile a(String p_a_1_)
    {
        for (RConOutputStream rconoutputstream : this.e().values())
        {
            if (p_a_1_.equalsIgnoreCase(((GameProfile)rconoutputstream.f()).getName()))
            {
                return (GameProfile)rconoutputstream.f();
            }
        }

        return null;
    }
}
