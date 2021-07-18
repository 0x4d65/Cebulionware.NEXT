package net.minecraft.src;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.status.client.CPacketPing;

public class mw implements Packet<CPacketPing>
{
    /**
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(PacketBuffer buf) throws IOException
    {
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer buf) throws IOException
    {
    }

    public void a(CPacketPing p_a_1_)
    {
        p_a_1_.a(this);
    }
}
