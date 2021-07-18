package net.minecraft.src;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import net.minecraft.client.gui.GuiCustomizeSkin;
import net.minecraft.client.gui.GuiOptionsRowList;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.ServerListEntryLanDetected;
import net.minecraft.client.network.ServerPinger;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.texture.ITextureMapPopulator;
import net.minecraft.client.renderer.texture.ITickableTextureObject;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.util.text.TextFormatting;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bni implements GuiOptionsRowList.Row
{
    private static final Logger a = LogManager.getLogger();
    private static final ThreadPoolExecutor b = new ScheduledThreadPoolExecutor(5, (new ThreadFactoryBuilder()).setNameFormat("Server Pinger #%d").setDaemon(true).build());
    private static final BehaviorProjectileDispense c = new BehaviorProjectileDispense("textures/misc/unknown_server.png");
    private static final BehaviorProjectileDispense d = new BehaviorProjectileDispense("textures/gui/server_selection.png");
    private final ServerListEntryLanDetected e;
    private final GameSettings f;
    private final ServerPinger g;
    private final BehaviorProjectileDispense h;
    private String i;
    private ITextureMapPopulator j;
    private long k;

    protected bni(ServerListEntryLanDetected p_i68_1_, ServerPinger p_i68_2_)
    {
        this.e = p_i68_1_;
        this.g = p_i68_2_;
        this.f = GameSettings.z();
        this.h = new BehaviorProjectileDispense("servers/" + p_i68_2_.LOGGER + "/icon");
        this.j = (ITextureMapPopulator)this.f.N().b(this.h);
    }

    public void func_192634_a(int p_192634_1_, int p_192634_2_, int p_192634_3_, int p_192634_4_, int p_192634_5_, int p_192634_6_, int p_192634_7_, boolean p_192634_8_, float p_192634_9_)
    {
        if (!this.g.h)
        {
            this.g.h = true;
            this.g.e = -2L;
            this.g.d = "";
            this.g.pingDestinations = "";
            b.submit(new Runnable()
            {
                public void run()
                {
                    try
                    {
                        bni.this.e.g().a(bni.this.g);
                    }
                    catch (UnknownHostException var2)
                    {
                        bni.this.g.e = -1L;
                        bni.this.g.d = TextFormatting.DARK_RED + LanguageManager.a("multiplayer.status.cannot_resolve");
                    }
                    catch (Exception var3)
                    {
                        bni.this.g.e = -1L;
                        bni.this.g.d = TextFormatting.DARK_RED + LanguageManager.a("multiplayer.status.cannot_connect");
                    }
                }
            });
        }

        boolean flag = this.g.f > 340;
        boolean flag1 = this.g.f < 340;
        boolean flag2 = flag || flag1;
        this.f.fancyGraphics.a(this.g.PING_RESPONSE_SPLITTER, p_192634_2_ + 32 + 3, p_192634_3_ + 1, 16777215);
        List<String> list = this.f.fancyGraphics.c(this.g.d, p_192634_4_ - 32 - 2);

        for (int i = 0; i < Math.min(list.size(), 2); ++i)
        {
            this.f.fancyGraphics.a(list.get(i), p_192634_2_ + 32 + 3, p_192634_3_ + 12 + this.f.fancyGraphics.field_191383_a * i, 8421504);
        }

        String s2 = flag2 ? TextFormatting.DARK_RED + this.g.g : this.g.pingDestinations;
        int j = this.f.fancyGraphics.a(s2);
        this.f.fancyGraphics.a(s2, p_192634_2_ + p_192634_4_ - j - 15 - 2, p_192634_3_ + 1, 8421504);
        int k = 0;
        String s = null;
        int l;
        String s1;

        if (flag2)
        {
            l = 5;
            s1 = LanguageManager.a(flag ? "multiplayer.status.client_out_of_date" : "multiplayer.status.server_out_of_date");
            s = this.g.i;
        }
        else if (this.g.h && this.g.e != -2L)
        {
            if (this.g.e < 0L)
            {
                l = 5;
            }
            else if (this.g.e < 150L)
            {
                l = 0;
            }
            else if (this.g.e < 300L)
            {
                l = 1;
            }
            else if (this.g.e < 600L)
            {
                l = 2;
            }
            else if (this.g.e < 1000L)
            {
                l = 3;
            }
            else
            {
                l = 4;
            }

            if (this.g.e < 0L)
            {
                s1 = LanguageManager.a("multiplayer.status.no_connection");
            }
            else
            {
                s1 = this.g.e + "ms";
                s = this.g.i;
            }
        }
        else
        {
            k = 1;
            l = (int)(GameSettings.I() / 100L + (long)(p_192634_1_ * 2) & 7L);

            if (l > 4)
            {
                l = 8 - l;
            }

            s1 = LanguageManager.a("multiplayer.status.pinging");
        }

        ItemRenderer.c(1.0F, 1.0F, 1.0F, 1.0F);
        this.f.N().a(ScaledResolution.scaledHeight);
        ScaledResolution.a(p_192634_2_ + p_192634_4_ - 15, p_192634_3_, (float)(k * 10), (float)(176 + l * 8), 10, 8, 256.0F, 256.0F);

        if (this.g.c() != null && !this.g.c().equals(this.i))
        {
            this.i = this.g.c();
            this.c();
            this.e.h().b();
        }

        if (this.j != null)
        {
            this.a(p_192634_2_, p_192634_3_, this.h);
        }
        else
        {
            this.a(p_192634_2_, p_192634_3_, c);
        }

        int i1 = p_192634_6_ - p_192634_2_;
        int j1 = p_192634_7_ - p_192634_3_;

        if (i1 >= p_192634_4_ - 15 && i1 <= p_192634_4_ - 5 && j1 >= 0 && j1 <= 8)
        {
            this.e.a(s1);
        }
        else if (i1 >= p_192634_4_ - j - 15 - 2 && i1 <= p_192634_4_ - 15 - 2 && j1 >= 0 && j1 <= 8)
        {
            this.e.a(s);
        }

        if (this.f.snooperEnabled.B || p_192634_8_)
        {
            this.f.N().a(d);
            ScaledResolution.a(p_192634_2_, p_192634_3_, p_192634_2_ + 32, p_192634_3_ + 32, -1601138544);
            ItemRenderer.c(1.0F, 1.0F, 1.0F, 1.0F);
            int k1 = p_192634_6_ - p_192634_2_;
            int l1 = p_192634_7_ - p_192634_3_;

            if (this.b())
            {
                if (k1 < 32 && k1 > 16)
                {
                    ScaledResolution.a(p_192634_2_, p_192634_3_, 0.0F, 32.0F, 32, 32, 256.0F, 256.0F);
                }
                else
                {
                    ScaledResolution.a(p_192634_2_, p_192634_3_, 0.0F, 0.0F, 32, 32, 256.0F, 256.0F);
                }
            }

            if (this.e.a(this, p_192634_1_))
            {
                if (k1 < 16 && l1 < 16)
                {
                    ScaledResolution.a(p_192634_2_, p_192634_3_, 96.0F, 32.0F, 32, 32, 256.0F, 256.0F);
                }
                else
                {
                    ScaledResolution.a(p_192634_2_, p_192634_3_, 96.0F, 0.0F, 32, 32, 256.0F, 256.0F);
                }
            }

            if (this.e.b(this, p_192634_1_))
            {
                if (k1 < 16 && l1 > 16)
                {
                    ScaledResolution.a(p_192634_2_, p_192634_3_, 64.0F, 32.0F, 32, 32, 256.0F, 256.0F);
                }
                else
                {
                    ScaledResolution.a(p_192634_2_, p_192634_3_, 64.0F, 0.0F, 32, 32, 256.0F, 256.0F);
                }
            }
        }
    }

    protected void a(int p_a_1_, int p_a_2_, BehaviorProjectileDispense p_a_3_)
    {
        this.f.N().a(p_a_3_);
        ItemRenderer.m();
        ScaledResolution.a(p_a_1_, p_a_2_, 0.0F, 0.0F, 32, 32, 32.0F, 32.0F);
        ItemRenderer.l();
    }

    private boolean b()
    {
        return true;
    }

    private void c()
    {
        if (this.g.c() == null)
        {
            this.f.N().c(this.h);
            this.j = null;
        }
        else
        {
            ByteBuf bytebuf = Unpooled.copiedBuffer((CharSequence)this.g.c(), StandardCharsets.UTF_8);
            ByteBuf bytebuf1 = null;
            BufferedImage bufferedimage;
            label99:
            {
                try
                {
                    bytebuf1 = Base64.decode(bytebuf);
                    bufferedimage = ITickableTextureObject.a((InputStream)(new ByteBufInputStream(bytebuf1)));
                    Validate.validState(bufferedimage.getWidth() == 64, "Must be 64 pixels wide");
                    Validate.validState(bufferedimage.getHeight() == 64, "Must be 64 pixels high");
                    break label99;
                }
                catch (Throwable throwable)
                {
                    a.error("Invalid icon for server {} ({})", this.g.PING_RESPONSE_SPLITTER, this.g.LOGGER, throwable);
                    this.g.a((String)null);
                }
                finally
                {
                    bytebuf.release();

                    if (bytebuf1 != null)
                    {
                        bytebuf1.release();
                    }
                }

                return;
            }

            if (this.j == null)
            {
                this.j = new ITextureMapPopulator(bufferedimage.getWidth(), bufferedimage.getHeight());
                this.f.N().a(this.h, this.j);
            }

            bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), this.j.e(), 0, bufferedimage.getWidth());
            this.j.loadTextureFromServer();
        }
    }

    /**
     * Called when the mouse is clicked within this entry. Returning true means that something within this entry was
     * clicked and the list should not be dragged.
     */
    public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY)
    {
        if (relativeX <= 32)
        {
            if (relativeX < 32 && relativeX > 16 && this.b())
            {
                this.e.b(slotIndex);
                this.e.f();
                return true;
            }

            if (relativeX < 16 && relativeY < 16 && this.e.a(this, slotIndex))
            {
                this.e.a(this, slotIndex, GuiCustomizeSkin.s());
                return true;
            }

            if (relativeX < 16 && relativeY > 16 && this.e.b(this, slotIndex))
            {
                this.e.b(this, slotIndex, GuiCustomizeSkin.s());
                return true;
            }
        }

        this.e.b(slotIndex);

        if (GameSettings.I() - this.k < 250L)
        {
            this.e.f();
        }

        this.k = GameSettings.I();
        return false;
    }

    public void func_192633_a(int p_192633_1_, int p_192633_2_, int p_192633_3_, float p_192633_4_)
    {
    }

    /**
     * Fired when the mouse button is released. Arguments: index, x, y, mouseEvent, relativeX, relativeY
     */
    public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY)
    {
    }

    public ServerPinger a()
    {
        return this.g;
    }
}
