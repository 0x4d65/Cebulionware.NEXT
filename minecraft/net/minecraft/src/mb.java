package net.minecraft.src;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketConfirmTeleport;
import net.minecraft.util.EnumActionResult;

public class mb implements Packet<CPacketConfirmTeleport>
{
    private EnumActionResult a;

    public mb()
    {
    }

    public mb(EnumActionResult p_i898_1_)
    {
        this.a = p_i898_1_;
    }

    /**
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(PacketBuffer buf) throws IOException
    {
        this.a = (EnumActionResult)buf.readEnumValue(EnumActionResult.class);
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer buf) throws IOException
    {
        buf.writeEnumValue(this.a);
    }

    public void a(CPacketConfirmTeleport p_a_1_)
    {
        p_a_1_.a(this);
    }

    public EnumActionResult a()
    {
        return this.a;
    }
}
