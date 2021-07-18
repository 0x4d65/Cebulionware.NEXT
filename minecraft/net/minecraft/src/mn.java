package net.minecraft.src;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.SecretKey;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.client.CPacketLoginStart;
import net.minecraft.util.JsonUtils;

public class mn implements Packet<CPacketLoginStart>
{
    private byte[] a = new byte[0];
    private byte[] b = new byte[0];

    public mn()
    {
    }

    public mn(SecretKey p_i905_1_, PublicKey p_i905_2_, byte[] p_i905_3_)
    {
        this.a = JsonUtils.a(p_i905_2_, p_i905_1_.getEncoded());
        this.b = JsonUtils.a(p_i905_2_, p_i905_3_);
    }

    /**
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(PacketBuffer buf) throws IOException
    {
        this.a = buf.readByteArray();
        this.b = buf.readByteArray();
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer buf) throws IOException
    {
        buf.writeByteArray(this.a);
        buf.writeByteArray(this.b);
    }

    public void a(CPacketLoginStart p_a_1_)
    {
        p_a_1_.a(this);
    }

    public SecretKey a(PrivateKey p_a_1_)
    {
        return JsonUtils.a(p_a_1_, this.a);
    }

    public byte[] b(PrivateKey p_b_1_)
    {
        return p_b_1_ == null ? this.b : JsonUtils.b(p_b_1_, this.b);
    }
}
