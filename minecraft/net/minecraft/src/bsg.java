package net.minecraft.src;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.network.ServerPinger;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.handshake.INetHandlerHandshakeServer;
import net.minecraft.network.status.INetHandlerStatusServer;
import net.minecraft.network.status.client.CPacketServerQuery;
import net.minecraft.network.status.server.SPacketPong;
import net.minecraft.network.status.server.SPacketServerInfo;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bsg
{
    private static final Splitter a = Splitter.on('\u0000').limit(6);
    private static final Logger b = LogManager.getLogger();
    private final List<NetworkManager> c = Collections.<NetworkManager>synchronizedList(Lists.newArrayList());

    public void a(final ServerPinger p_a_1_) throws UnknownHostException
    {
        ServerList serverlist = ServerList.a(p_a_1_.LOGGER);
        final NetworkManager networkmanager = NetworkManager.createNetworkManagerAndConnect(InetAddress.getByName(serverlist.a()), serverlist.b(), false);
        this.c.add(networkmanager);
        p_a_1_.d = LanguageManager.a("multiplayer.status.pinging");
        p_a_1_.e = -1L;
        p_a_1_.i = null;
        networkmanager.setNetHandler(new SPacketPong()
        {
            private boolean d;
            private boolean e;
            private long f;
            public void a(ServerStatusResponse p_a_1_x)
            {
                if (this.e)
                {
                    networkmanager.closeChannel(new TextComponentTranslation("multiplayer.status.unrequested", new Object[0]));
                }
                else
                {
                    this.e = true;
                    INetHandlerStatusServer inethandlerstatusserver = p_a_1_.a();

                    if (inethandlerstatusserver.a() != null)
                    {
                        p_a_1_.d = inethandlerstatusserver.a().getFormattedText();
                    }
                    else
                    {
                        p_a_1_.d = "";
                    }

                    if (inethandlerstatusserver.c() != null)
                    {
                        p_a_1_.g = inethandlerstatusserver.c().a();
                        p_a_1_.f = inethandlerstatusserver.c().b();
                    }
                    else
                    {
                        p_a_1_.g = LanguageManager.a("multiplayer.status.old");
                        p_a_1_.f = 0;
                    }

                    if (inethandlerstatusserver.b() != null)
                    {
                        p_a_1_.pingDestinations = TextFormatting.GRAY + "" + inethandlerstatusserver.b().b() + "" + TextFormatting.DARK_GRAY + "/" + TextFormatting.GRAY + inethandlerstatusserver.b().a();

                        if (ArrayUtils.isNotEmpty(inethandlerstatusserver.b().c()))
                        {
                            StringBuilder stringbuilder = new StringBuilder();

                            for (GameProfile gameprofile : inethandlerstatusserver.b().c())
                            {
                                if (stringbuilder.length() > 0)
                                {
                                    stringbuilder.append("\n");
                                }

                                stringbuilder.append(gameprofile.getName());
                            }

                            if (inethandlerstatusserver.b().c().length < inethandlerstatusserver.b().b())
                            {
                                if (stringbuilder.length() > 0)
                                {
                                    stringbuilder.append("\n");
                                }

                                stringbuilder.append(LanguageManager.a("multiplayer.status.and_more", inethandlerstatusserver.b().b() - inethandlerstatusserver.b().c().length));
                            }

                            p_a_1_.i = stringbuilder.toString();
                        }
                    }
                    else
                    {
                        p_a_1_.pingDestinations = TextFormatting.DARK_GRAY + LanguageManager.a("multiplayer.status.unknown");
                    }

                    if (inethandlerstatusserver.d() != null)
                    {
                        String s = inethandlerstatusserver.d();

                        if (s.startsWith("data:image/png;base64,"))
                        {
                            p_a_1_.a(s.substring("data:image/png;base64,".length()));
                        }
                        else
                        {
                            bsg.b.error("Invalid server icon (unknown format)");
                        }
                    }
                    else
                    {
                        p_a_1_.a((String)null);
                    }

                    this.f = GameSettings.I();
                    networkmanager.sendPacket(new CPacketServerQuery(this.f));
                    this.d = true;
                }
            }
            public void a(SPacketServerInfo p_a_1_x)
            {
                long i = this.f;
                long j = GameSettings.I();
                p_a_1_.e = j - i;
                networkmanager.closeChannel(new TextComponentString("Finished"));
            }
            public void onDisconnect(ITextComponent reason)
            {
                if (!this.d)
                {
                    bsg.b.error("Can't ping {}: {}", p_a_1_.LOGGER, reason.getUnformattedText());
                    p_a_1_.d = TextFormatting.DARK_RED + LanguageManager.a("multiplayer.status.cannot_connect");
                    p_a_1_.pingDestinations = "";
                    bsg.this.b(p_a_1_);
                }
            }
        });

        try
        {
            networkmanager.sendPacket(new INetHandlerHandshakeServer(serverlist.a(), serverlist.b(), EnumConnectionState.STATUS));
            networkmanager.sendPacket(new mw());
        }
        catch (Throwable throwable)
        {
            b.error(throwable);
        }
    }

    private void b(final ServerPinger p_b_1_)
    {
        final ServerList serverlist = ServerList.a(p_b_1_.LOGGER);
        ((Bootstrap)((Bootstrap)((Bootstrap)(new Bootstrap()).group(NetworkManager.CLIENT_NIO_EVENTLOOP.c())).handler(new ChannelInitializer<Channel>()
        {
            protected void initChannel(Channel p_initChannel_1_) throws Exception
            {
                try
                {
                    p_initChannel_1_.config().setOption(ChannelOption.TCP_NODELAY, Boolean.valueOf(true));
                }
                catch (ChannelException var3)
                {
                    ;
                }

                p_initChannel_1_.pipeline().addLast(new SimpleChannelInboundHandler<ByteBuf>()
                {
                    public void channelActive(ChannelHandlerContext p_channelActive_1_) throws Exception
                    {
                        super.channelActive(p_channelActive_1_);
                        ByteBuf bytebuf = Unpooled.buffer();

                        try
                        {
                            bytebuf.writeByte(254);
                            bytebuf.writeByte(1);
                            bytebuf.writeByte(250);
                            char[] achar = "MC|PingHost".toCharArray();
                            bytebuf.writeShort(achar.length);

                            for (char c0 : achar)
                            {
                                bytebuf.writeChar(c0);
                            }

                            bytebuf.writeShort(7 + 2 * serverlist.a().length());
                            bytebuf.writeByte(127);
                            achar = serverlist.a().toCharArray();
                            bytebuf.writeShort(achar.length);

                            for (char c1 : achar)
                            {
                                bytebuf.writeChar(c1);
                            }

                            bytebuf.writeInt(serverlist.b());
                            p_channelActive_1_.channel().writeAndFlush(bytebuf).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                        }
                        finally
                        {
                            bytebuf.release();
                        }
                    }
                    protected void a(ChannelHandlerContext p_a_1_, ByteBuf p_a_2_) throws Exception
                    {
                        short short1 = p_a_2_.readUnsignedByte();

                        if (short1 == 255)
                        {
                            String s = new String(p_a_2_.readBytes(p_a_2_.readShort() * 2).array(), StandardCharsets.UTF_16BE);
                            String[] astring = (String[])Iterables.toArray(bsg.a.split(s), String.class);

                            if ("\u00a71".equals(astring[0]))
                            {
                                int i = IProgressUpdate.a(astring[1], 0);
                                String s1 = astring[2];
                                String s2 = astring[3];
                                int j = IProgressUpdate.a(astring[4], -1);
                                int k = IProgressUpdate.a(astring[5], -1);
                                p_b_1_.f = -1;
                                p_b_1_.g = s1;
                                p_b_1_.d = s2;
                                p_b_1_.pingDestinations = TextFormatting.GRAY + "" + j + "" + TextFormatting.DARK_GRAY + "/" + TextFormatting.GRAY + k;
                            }
                        }

                        p_a_1_.close();
                    }
                    public void exceptionCaught(ChannelHandlerContext p_exceptionCaught_1_, Throwable p_exceptionCaught_2_) throws Exception
                    {
                        p_exceptionCaught_1_.close();
                    }
                });
            }
        })).channel(NioSocketChannel.class)).connect(serverlist.a(), serverlist.b());
    }

    public void a()
    {
        synchronized (this.c)
        {
            Iterator<NetworkManager> iterator = this.c.iterator();

            while (iterator.hasNext())
            {
                NetworkManager networkmanager = iterator.next();

                if (networkmanager.isChannelOpen())
                {
                    networkmanager.processReceivedPackets();
                }
                else
                {
                    iterator.remove();
                    networkmanager.checkDisconnected();
                }
            }
        }
    }

    public void b()
    {
        synchronized (this.c)
        {
            Iterator<NetworkManager> iterator = this.c.iterator();

            while (iterator.hasNext())
            {
                NetworkManager networkmanager = iterator.next();

                if (networkmanager.isChannelOpen())
                {
                    iterator.remove();
                    networkmanager.closeChannel(new TextComponentTranslation("multiplayer.status.cancelled", new Object[0]));
                }
            }
        }
    }
}
