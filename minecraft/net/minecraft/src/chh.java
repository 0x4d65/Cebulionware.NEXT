package net.minecraft.src;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class chh extends Thread
{
    private static final AtomicInteger a = new AtomicInteger(0);
    private static final Logger b = LogManager.getLogger();
    private final String c;
    private final DatagramSocket d;
    private boolean e = true;
    private final String f;

    public chh(String p_i657_1_, String p_i657_2_) throws IOException
    {
        super("LanServerPinger #" + a.incrementAndGet());
        this.c = p_i657_1_;
        this.f = p_i657_2_;
        this.setDaemon(true);
        this.d = new DatagramSocket();
    }

    public void run()
    {
        String s = a(this.c, this.f);
        byte[] abyte = s.getBytes(StandardCharsets.UTF_8);

        while (!this.isInterrupted() && this.e)
        {
            try
            {
                InetAddress inetaddress = InetAddress.getByName("224.0.2.60");
                DatagramPacket datagrampacket = new DatagramPacket(abyte, abyte.length, inetaddress, 4445);
                this.d.send(datagrampacket);
            }
            catch (IOException ioexception)
            {
                b.warn("LanServerPinger: {}", (Object)ioexception.getMessage());
                break;
            }

            try
            {
                sleep(1500L);
            }
            catch (InterruptedException var5)
            {
                ;
            }
        }
    }

    public void interrupt()
    {
        super.interrupt();
        this.e = false;
    }

    public static String a(String p_a_0_, String p_a_1_)
    {
        return "[MOTD]" + p_a_0_ + "[/MOTD][AD]" + p_a_1_ + "[/AD]";
    }

    public static String a(String p_a_0_)
    {
        int i = p_a_0_.indexOf("[MOTD]");

        if (i < 0)
        {
            return "missing no";
        }
        else
        {
            int j = p_a_0_.indexOf("[/MOTD]", i + "[MOTD]".length());
            return j < i ? "missing no" : p_a_0_.substring(i + "[MOTD]".length(), j);
        }
    }

    public static String b(String p_b_0_)
    {
        int i = p_b_0_.indexOf("[/MOTD]");

        if (i < 0)
        {
            return null;
        }
        else
        {
            int j = p_b_0_.indexOf("[/MOTD]", i + "[/MOTD]".length());

            if (j >= 0)
            {
                return null;
            }
            else
            {
                int k = p_b_0_.indexOf("[AD]", i + "[/MOTD]".length());

                if (k < 0)
                {
                    return null;
                }
                else
                {
                    int l = p_b_0_.indexOf("[/AD]", k + "[AD]".length());
                    return l < k ? null : p_b_0_.substring(k + "[AD]".length(), l);
                }
            }
        }
    }
}
