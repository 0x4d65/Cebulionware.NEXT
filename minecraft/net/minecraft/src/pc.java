package net.minecraft.src;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import javax.crypto.SecretKey;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.login.INetHandlerLoginServer;
import net.minecraft.network.login.client.CPacketEncryptionResponse;
import net.minecraft.network.login.client.CPacketLoginStart;
import net.minecraft.network.login.server.SPacketDisconnect;
import net.minecraft.network.login.server.SPacketEnableCompression;
import net.minecraft.network.login.server.SPacketEncryptionRequest;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class pc implements CPacketLoginStart, DedicatedServer
{
    private static final AtomicInteger b = new AtomicInteger(0);
    private static final Logger c = LogManager.getLogger();
    private static final Random d = new Random();
    private final byte[] e = new byte[4];
    private final MinecraftServer f;
    public final NetworkManager profile;
    private pc.a g = pc.a.a;
    private int h;
    private GameProfile i;
    private final String j = "";
    private SecretKey k;
    private EntityTrackerEntry l;

    public pc(MinecraftServer p_i967_1_, NetworkManager p_i967_2_)
    {
        this.f = p_i967_1_;
        this.profile = p_i967_2_;
        d.nextBytes(this.e);
    }

    public void e()
    {
        if (this.g == pc.a.d)
        {
            this.b();
        }
        else if (this.g == pc.a.e)
        {
            EntityTrackerEntry entitytrackerentry = this.f.am().a(this.i.getId());

            if (entitytrackerentry == null)
            {
                this.g = pc.a.d;
                this.f.am().a(this.profile, this.l);
                this.l = null;
            }
        }

        if (this.h++ == 600)
        {
            this.b(new TextComponentTranslation("multiplayer.disconnect.slow_login", new Object[0]));
        }
    }

    public void b(ITextComponent p_b_1_)
    {
        try
        {
            c.info("Disconnecting {}: {}", this.c(), p_b_1_.getUnformattedText());
            this.profile.sendPacket(new INetHandlerLoginServer(p_b_1_));
            this.profile.closeChannel(p_b_1_);
        }
        catch (Exception exception)
        {
            c.error("Error whilst disconnecting player", (Throwable)exception);
        }
    }

    public void b()
    {
        if (!this.i.isComplete())
        {
            this.i = this.a(this.i);
        }

        String s = this.f.am().a(this.profile.getRemoteAddress(), this.i);

        if (s != null)
        {
            this.b(new TextComponentTranslation(s, new Object[0]));
        }
        else
        {
            this.g = pc.a.f;

            if (this.f.getNetworkCompressionThreshold() >= 0 && !this.profile.isLocalChannel())
            {
                this.profile.sendPacket(new SPacketDisconnect(this.f.getNetworkCompressionThreshold()), new ChannelFutureListener()
                {
                    public void a(ChannelFuture p_a_1_) throws Exception
                    {
                        pc.this.profile.setCompressionThreshold(pc.this.f.getNetworkCompressionThreshold());
                    }
                });
            }

            this.profile.sendPacket(new SPacketEncryptionRequest(this.i));
            EntityTrackerEntry entitytrackerentry = this.f.am().a(this.i.getId());

            if (entitytrackerentry != null)
            {
                this.g = pc.a.e;
                this.l = this.f.am().g(this.i);
            }
            else
            {
                this.f.am().a(this.profile, this.f.am().g(this.i));
            }
        }
    }

    /**
     * Invoked when disconnecting, the parameter is a ChatComponent describing the reason for termination
     */
    public void onDisconnect(ITextComponent reason)
    {
        c.info("{} lost connection: {}", this.c(), reason.getUnformattedText());
    }

    public String c()
    {
        return this.i != null ? this.i + " (" + this.profile.getRemoteAddress() + ")" : String.valueOf((Object)this.profile.getRemoteAddress());
    }

    public void a(CPacketEncryptionResponse p_a_1_)
    {
        Validate.validState(this.g == pc.a.a, "Unexpected hello packet");
        this.i = p_a_1_.a();

        if (this.f.isServerInOnlineMode() && !this.profile.isLocalChannel())
        {
            this.g = pc.a.b;
            this.profile.sendPacket(new SPacketEnableCompression("", this.f.getKeyPair().getPublic(), this.e));
        }
        else
        {
            this.g = pc.a.d;
        }
    }

    public void a(mn p_a_1_)
    {
        Validate.validState(this.g == pc.a.b, "Unexpected key packet");
        PrivateKey privatekey = this.f.getKeyPair().getPrivate();

        if (!Arrays.equals(this.e, p_a_1_.b(privatekey)))
        {
            throw new IllegalStateException("Invalid nonce!");
        }
        else
        {
            this.k = p_a_1_.a(privatekey);
            this.g = pc.a.c;
            this.profile.enableEncryption(this.k);
            (new Thread("User Authenticator #" + b.incrementAndGet())
            {
                public void run()
                {
                    GameProfile gameprofile = pc.this.i;

                    try
                    {
                        String s = (new BigInteger(JsonUtils.a("", pc.this.f.getKeyPair().getPublic(), pc.this.k))).toString(16);
                        pc.this.i = pc.this.f.getMinecraftSessionService().hasJoinedServer(new GameProfile((UUID)null, gameprofile.getName()), s, this.a());

                        if (pc.this.i != null)
                        {
                            pc.c.info("UUID of player {} is {}", pc.this.i.getName(), pc.this.i.getId());
                            pc.this.g = pc.a.d;
                        }
                        else if (pc.this.f.isSinglePlayer())
                        {
                            pc.c.warn("Failed to verify username but will let them in anyway!");
                            pc.this.i = pc.this.a(gameprofile);
                            pc.this.g = pc.a.d;
                        }
                        else
                        {
                            pc.this.b(new TextComponentTranslation("multiplayer.disconnect.unverified_username", new Object[0]));
                            pc.c.error("Username '{}' tried to join with an invalid session", (Object)gameprofile.getName());
                        }
                    }
                    catch (AuthenticationUnavailableException var3)
                    {
                        if (pc.this.f.isSinglePlayer())
                        {
                            pc.c.warn("Authentication servers are down but will let them in anyway!");
                            pc.this.i = pc.this.a(gameprofile);
                            pc.this.g = pc.a.d;
                        }
                        else
                        {
                            pc.this.b(new TextComponentTranslation("multiplayer.disconnect.authservers_down", new Object[0]));
                            pc.c.error("Couldn't verify username because servers are unavailable");
                        }
                    }
                }
                @Nullable
                private InetAddress a()
                {
                    SocketAddress socketaddress = pc.this.profile.getRemoteAddress();
                    return pc.this.f.func_190518_ac() && socketaddress instanceof InetSocketAddress ? ((InetSocketAddress)socketaddress).getAddress() : null;
                }
            }).start();
        }
    }

    protected GameProfile a(GameProfile p_a_1_)
    {
        UUID uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + p_a_1_.getName()).getBytes(StandardCharsets.UTF_8));
        return new GameProfile(uuid, p_a_1_.getName());
    }

    static enum a
    {
        a,
        b,
        c,
        d,
        e,
        f;
    }
}
