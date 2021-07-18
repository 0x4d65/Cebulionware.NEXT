package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;

public class CPacketRecipePlacement implements Packet<CPacketConfirmTeleport>
{
    private int field_192616_a;

    public CPacketRecipePlacement()
    {
    }

    public CPacketRecipePlacement(int p_i872_1_)
    {
        this.field_192616_a = p_i872_1_;
    }

    /**
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(PacketBuffer buf) throws IOException
    {
        this.field_192616_a = buf.readVarIntFromBuffer();
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer buf) throws IOException
    {
        buf.writeVarIntToBuffer(this.field_192616_a);
    }

    public void a(CPacketConfirmTeleport p_a_1_)
    {
        p_a_1_.a(this);
    }

    public int func_192613_a()
    {
        return this.field_192616_a;
    }
}
