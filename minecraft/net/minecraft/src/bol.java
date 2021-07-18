package net.minecraft.src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.client.audio.SoundList;
import net.minecraft.client.gui.GuiCreateFlatWorld;
import net.minecraft.client.gui.GuiListWorldSelection;
import net.minecraft.client.gui.GuiListWorldSelectionEntry;
import net.minecraft.client.gui.GuiOptionsRowList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.spectator.PlayerMenuObject;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.texture.ITextureMapPopulator;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.SaveHandlerMP;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bol implements GuiOptionsRowList.Row
{
    private static final Logger a = LogManager.getLogger();
    private static final DateFormat b = new SimpleDateFormat();
    private static final BehaviorProjectileDispense c = new BehaviorProjectileDispense("textures/misc/unknown_server.png");
    private static final BehaviorProjectileDispense d = new BehaviorProjectileDispense("textures/gui/world_selection.png");
    private final GameSettings e;
    private final GuiListWorldSelection f;
    private final SaveHandlerMP g;
    private final BehaviorProjectileDispense h;
    private final PlayerMenuObject i;
    private File j;
    private ITextureMapPopulator k;
    private long l;

    public bol(PlayerMenuObject p_i61_1_, SaveHandlerMP p_i61_2_, bfg p_i61_3_)
    {
        this.i = p_i61_1_;
        this.f = p_i61_1_.g();
        this.g = p_i61_2_;
        this.e = GameSettings.z();
        this.h = new BehaviorProjectileDispense("worlds/" + p_i61_2_.a() + "/icon");
        this.j = p_i61_3_.b(p_i61_2_.a(), "icon.png");

        if (!this.j.isFile())
        {
            this.j = null;
        }

        this.f();
    }

    public void func_192634_a(int p_192634_1_, int p_192634_2_, int p_192634_3_, int p_192634_4_, int p_192634_5_, int p_192634_6_, int p_192634_7_, boolean p_192634_8_, float p_192634_9_)
    {
        String s = this.g.b();
        String s1 = this.g.a() + " (" + b.format(new Date(this.g.e())) + ")";
        String s2 = "";

        if (StringUtils.isEmpty(s))
        {
            s = LanguageManager.a("selectWorld.world") + " " + (p_192634_1_ + 1);
        }

        if (this.g.d())
        {
            s2 = LanguageManager.a("selectWorld.conversion") + " " + s2;
        }
        else
        {
            s2 = LanguageManager.a("gameMode." + this.g.f().b());

            if (this.g.g())
            {
                s2 = TextFormatting.DARK_RED + LanguageManager.a("gameMode.hardcore") + TextFormatting.RESET;
            }

            if (this.g.h())
            {
                s2 = s2 + ", " + LanguageManager.a("selectWorld.cheats");
            }

            String s3 = this.g.i();

            if (this.g.l())
            {
                if (this.g.m())
                {
                    s2 = s2 + ", " + LanguageManager.a("selectWorld.version") + " " + TextFormatting.RED + s3 + TextFormatting.RESET;
                }
                else
                {
                    s2 = s2 + ", " + LanguageManager.a("selectWorld.version") + " " + TextFormatting.ITALIC + s3 + TextFormatting.RESET;
                }
            }
            else
            {
                s2 = s2 + ", " + LanguageManager.a("selectWorld.version") + " " + s3;
            }
        }

        this.e.fancyGraphics.a(s, p_192634_2_ + 32 + 3, p_192634_3_ + 1, 16777215);
        this.e.fancyGraphics.a(s1, p_192634_2_ + 32 + 3, p_192634_3_ + this.e.fancyGraphics.field_191383_a + 3, 8421504);
        this.e.fancyGraphics.a(s2, p_192634_2_ + 32 + 3, p_192634_3_ + this.e.fancyGraphics.field_191383_a + this.e.fancyGraphics.field_191383_a + 3, 8421504);
        ItemRenderer.c(1.0F, 1.0F, 1.0F, 1.0F);
        this.e.N().a(this.k != null ? this.h : c);
        ItemRenderer.m();
        ScaledResolution.a(p_192634_2_, p_192634_3_, 0.0F, 0.0F, 32, 32, 32.0F, 32.0F);
        ItemRenderer.l();

        if (this.e.snooperEnabled.B || p_192634_8_)
        {
            this.e.N().a(d);
            ScaledResolution.a(p_192634_2_, p_192634_3_, p_192634_2_ + 32, p_192634_3_ + 32, -1601138544);
            ItemRenderer.c(1.0F, 1.0F, 1.0F, 1.0F);
            int j = p_192634_6_ - p_192634_2_;
            int i = j < 32 ? 32 : 0;

            if (this.g.l())
            {
                ScaledResolution.a(p_192634_2_, p_192634_3_, 32.0F, (float)i, 32, 32, 256.0F, 256.0F);

                if (this.g.m())
                {
                    ScaledResolution.a(p_192634_2_, p_192634_3_, 96.0F, (float)i, 32, 32, 256.0F, 256.0F);

                    if (j < 32)
                    {
                        this.f.a(TextFormatting.RED + LanguageManager.a("selectWorld.tooltip.fromNewerVersion1") + "\n" + TextFormatting.RED + LanguageManager.a("selectWorld.tooltip.fromNewerVersion2"));
                    }
                }
                else
                {
                    ScaledResolution.a(p_192634_2_, p_192634_3_, 64.0F, (float)i, 32, 32, 256.0F, 256.0F);

                    if (j < 32)
                    {
                        this.f.a(TextFormatting.GOLD + LanguageManager.a("selectWorld.tooltip.snapshot1") + "\n" + TextFormatting.GOLD + LanguageManager.a("selectWorld.tooltip.snapshot2"));
                    }
                }
            }
            else
            {
                ScaledResolution.a(p_192634_2_, p_192634_3_, 0.0F, (float)i, 32, 32, 256.0F, 256.0F);
            }
        }
    }

    /**
     * Called when the mouse is clicked within this entry. Returning true means that something within this entry was
     * clicked and the list should not be dragged.
     */
    public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY)
    {
        this.i.d(slotIndex);

        if (relativeX <= 32 && relativeX < 32)
        {
            this.a();
            return true;
        }
        else if (GameSettings.I() - this.l < 250L)
        {
            this.a();
            return true;
        }
        else
        {
            this.l = GameSettings.I();
            return false;
        }
    }

    public void a()
    {
        if (this.g.m())
        {
            this.e.a(new GuiCreateFlatWorld(new GuiConnecting()
            {
                public void a(boolean p_a_1_, int p_a_2_)
                {
                    if (p_a_1_)
                    {
                        bol.this.e();
                    }
                    else
                    {
                        bol.this.e.a(bol.this.f);
                    }
                }
            }, LanguageManager.a("selectWorld.versionQuestion"), LanguageManager.a("selectWorld.versionWarning", this.g.i()), LanguageManager.a("selectWorld.versionJoinButton"), LanguageManager.a("gui.cancel"), 0));
        }
        else
        {
            this.e();
        }
    }

    public void b()
    {
        this.e.a(new GuiCreateFlatWorld(new GuiConnecting()
        {
            public void a(boolean p_a_1_, int p_a_2_)
            {
                if (p_a_1_)
                {
                    bol.this.e.a(new GuiScreen());
                    bfg bfg = bol.this.e.g();
                    bfg.d();
                    bfg.e(bol.this.g.a());
                    bol.this.i.e();
                }

                bol.this.e.a(bol.this.f);
            }
        }, LanguageManager.a("selectWorld.deleteQuestion"), "'" + this.g.b() + "' " + LanguageManager.a("selectWorld.deleteWarning"), LanguageManager.a("selectWorld.deleteButton"), LanguageManager.a("gui.cancel"), 0));
    }

    public void c()
    {
        this.e.a(new GuiListWorldSelectionEntry(this.f, this.g.a()));
    }

    public void d()
    {
        this.e.a(new GuiScreen());
        GuiWorldSelection guiworldselection = new GuiWorldSelection(this.f);
        ISaveFormat isaveformat = this.e.g().a(this.g.a(), false);
        AnvilConverterException anvilconverterexception = isaveformat.d();
        isaveformat.a();

        if (anvilconverterexception != null)
        {
            guiworldselection.a(anvilconverterexception);
            this.e.a(guiworldselection);
        }
    }

    private void e()
    {
        this.e.U().a(SoundList.a(qf.ic, 1.0F));

        if (this.e.g().f(this.g.a()))
        {
            this.e.a(this.g.a(), this.g.b(), (WorldType)null);
        }
    }

    private void f()
    {
        boolean flag = this.j != null && this.j.isFile();

        if (flag)
        {
            BufferedImage bufferedimage;

            try
            {
                bufferedimage = ImageIO.read(this.j);
                Validate.validState(bufferedimage.getWidth() == 64, "Must be 64 pixels wide");
                Validate.validState(bufferedimage.getHeight() == 64, "Must be 64 pixels high");
            }
            catch (Throwable throwable)
            {
                a.error("Invalid icon for world {}", this.g.a(), throwable);
                this.j = null;
                return;
            }

            if (this.k == null)
            {
                this.k = new ITextureMapPopulator(bufferedimage.getWidth(), bufferedimage.getHeight());
                this.e.N().a(this.h, this.k);
            }

            bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), this.k.e(), 0, bufferedimage.getWidth());
            this.k.loadTextureFromServer();
        }
        else if (!flag)
        {
            this.e.N().c(this.h);
            this.k = null;
        }
    }

    /**
     * Fired when the mouse button is released. Arguments: index, x, y, mouseEvent, relativeX, relativeY
     */
    public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY)
    {
    }

    public void func_192633_a(int p_192633_1_, int p_192633_2_, int p_192633_3_, float p_192633_4_)
    {
    }
}
