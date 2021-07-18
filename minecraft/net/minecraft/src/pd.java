package net.minecraft.src;

import net.minecraft.network.NetworkManager;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.status.client.CPacketPing;
import net.minecraft.network.status.client.CPacketServerQuery;
import net.minecraft.network.status.server.SPacketServerInfo;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class pd implements CPacketPing
{
    private static final ITextComponent a = new TextComponentString("Status request has been handled.");
    private final MinecraftServer b;
    private final NetworkManager c;
    private boolean d;

    public pd(MinecraftServer p_i968_1_, NetworkManager p_i968_2_)
    {
        this.b = p_i968_1_;
        this.c = p_i968_2_;
    }

    /**
     * Invoked when disconnecting, the parameter is a ChatComponent describing the reason for termination
     */
    public void onDisconnect(ITextComponent reason)
    {
    }

    public void a(mw p_a_1_)
    {
        if (this.d)
        {
            this.c.closeChannel(a);
        }
        else
        {
            this.d = true;
            this.c.sendPacket(new ServerStatusResponse(this.b.aC()));
        }
    }

    public void a(CPacketServerQuery p_a_1_)
    {
        this.c.sendPacket(new SPacketServerInfo(p_a_1_.a()));
        this.c.closeChannel(a);
    }
}
