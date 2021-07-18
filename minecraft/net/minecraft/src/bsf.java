package net.minecraft.src;

import com.google.common.collect.Lists;
import java.io.File;
import java.util.List;
import net.minecraft.client.network.ServerPinger;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bsf
{
    private static final Logger a = LogManager.getLogger();
    private final GameSettings b;
    private final List<ServerPinger> c = Lists.<ServerPinger>newArrayList();

    public bsf(GameSettings p_i20_1_)
    {
        this.b = p_i20_1_;
        this.a();
    }

    public void a()
    {
        try
        {
            this.c.clear();
            NBTTagCompound nbttagcompound = CompressedStreamTools.read(new File(this.b.useVbo, "servers.dat"));

            if (nbttagcompound == null)
            {
                return;
            }

            NBTTagList nbttaglist = nbttagcompound.getTagList("servers", 10);

            for (int i = 0; i < nbttaglist.tagCount(); ++i)
            {
                this.c.add(ServerPinger.a(nbttaglist.getCompoundTagAt(i)));
            }
        }
        catch (Exception exception)
        {
            a.error("Couldn't load server list", (Throwable)exception);
        }
    }

    public void b()
    {
        try
        {
            NBTTagList nbttaglist = new NBTTagList();

            for (ServerPinger serverpinger : this.c)
            {
                nbttaglist.appendTag(serverpinger.a());
            }

            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.setTag("servers", nbttaglist);
            CompressedStreamTools.safeWrite(nbttagcompound, new File(this.b.useVbo, "servers.dat"));
        }
        catch (Exception exception)
        {
            a.error("Couldn't save server list", (Throwable)exception);
        }
    }

    public ServerPinger a(int p_a_1_)
    {
        return this.c.get(p_a_1_);
    }

    public void b(int p_b_1_)
    {
        this.c.remove(p_b_1_);
    }

    public void a(ServerPinger p_a_1_)
    {
        this.c.add(p_a_1_);
    }

    public int c()
    {
        return this.c.size();
    }

    public void a(int p_a_1_, int p_a_2_)
    {
        ServerPinger serverpinger = this.a(p_a_1_);
        this.c.set(p_a_1_, this.a(p_a_2_));
        this.c.set(p_a_2_, serverpinger);
        this.b();
    }

    public void a(int p_a_1_, ServerPinger p_a_2_)
    {
        this.c.set(p_a_1_, p_a_2_);
    }

    public static void b(ServerPinger p_b_0_)
    {
        bsf bsf = new bsf(GameSettings.z());
        bsf.a();

        for (int i = 0; i < bsf.c(); ++i)
        {
            ServerPinger serverpinger = bsf.a(i);

            if (serverpinger.PING_RESPONSE_SPLITTER.equals(p_b_0_.PING_RESPONSE_SPLITTER) && serverpinger.LOGGER.equals(p_b_0_.LOGGER))
            {
                bsf.a(i, p_b_0_);
                break;
            }
        }

        bsf.b();
    }
}
