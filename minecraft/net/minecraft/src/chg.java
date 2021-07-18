package net.minecraft.src;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.multiplayer.ThreadLanServerPing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class chg
{
    private static final AtomicInteger a = new AtomicInteger(0);
    private static final Logger b = LogManager.getLogger();

    public static class a extends Thread
    {
        private final chg.b a;
        private final InetAddress b;
        private final MulticastSocket c;

        public a(chg.b p_i658_1_) throws IOException
        {
            super("LanServerDetector #" + chg.a.incrementAndGet());
            this.a = p_i658_1_;
            this.setDaemon(true);
            this.c = new MulticastSocket(4445);
            this.b = InetAddress.getByName("224.0.2.60");
            this.c.setSoTimeout(5000);
            this.c.joinGroup(this.b);
        }

        public void run()
        {
            byte[] abyte = new byte[1024];

            while (!this.isInterrupted())
            {
                DatagramPacket datagrampacket = new DatagramPacket(abyte, abyte.length);

                try
                {
                    this.c.receive(datagrampacket);
                }
                catch (SocketTimeoutException var5)
                {
                    continue;
                }
                catch (IOException ioexception)
                {
                    chg.b.error("Couldn't ping server", (Throwable)ioexception);
                    break;
                }

                String s = new String(datagrampacket.getData(), datagrampacket.getOffset(), datagrampacket.getLength(), StandardCharsets.UTF_8);
                chg.b.debug("{}: {}", datagrampacket.getAddress(), s);
                this.a.a(s, datagrampacket.getAddress());
            }

            try
            {
                this.c.leaveGroup(this.b);
            }
            catch (IOException var4)
            {
                ;
            }

            this.c.close();
        }
    }

    public static class b
    {
        private final List<ThreadLanServerPing> b = Lists.<ThreadLanServerPing>newArrayList();
        boolean a;

        public synchronized boolean a()
        {
            return this.a;
        }

        public synchronized void b()
        {
            this.a = false;
        }

        public synchronized List<ThreadLanServerPing> c()
        {
            return Collections.<ThreadLanServerPing>unmodifiableList(this.b);
        }

        public synchronized void a(String p_a_1_, InetAddress p_a_2_)
        {
            String s = chh.a(p_a_1_);
            String s1 = chh.b(p_a_1_);

            if (s1 != null)
            {
                s1 = p_a_2_.getHostAddress() + ":" + s1;
                boolean flag = false;

                for (ThreadLanServerPing threadlanserverping : this.b)
                {
                    if (threadlanserverping.b().equals(s1))
                    {
                        threadlanserverping.c();
                        flag = true;
                        break;
                    }
                }

                if (!flag)
                {
                    this.b.add(new ThreadLanServerPing(s, s1));
                    this.a = true;
                }
            }
        }
    }
}
