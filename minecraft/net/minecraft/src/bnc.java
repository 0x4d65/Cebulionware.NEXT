package net.minecraft.src;

import com.google.common.collect.Lists;
import io.netty.buffer.Unpooled;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.client.gui.GuiButtonImage;
import net.minecraft.client.gui.GuiCustomizeSkin;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.tileentity.TileEntityEndPortal;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.math.BlockPos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

public class bnc extends GuiCustomizeSkin
{
    private static final Logger f = LogManager.getLogger();
    public static final int[] a = new int[] {203, 205, 14, 211, 199, 207};
    private final TileEntityEndPortal g;
    private BlockSilverfish h = BlockSilverfish.VARIANT;
    private BlockSandStone i = BlockSandStone.TYPE;
    private TileEntityEndPortal.a s = TileEntityEndPortal.a.d;
    private boolean t;
    private boolean u;
    private boolean v;
    private GuiButtonImage w;
    private GuiButtonImage x;
    private GuiButtonImage y;
    private GuiButtonImage z;
    private GuiButtonImage A;
    private GuiButtonImage B;
    private GuiButtonImage C;
    private GuiButtonImage D;
    private GuiButtonImage E;
    private GuiButtonImage F;
    private GuiUtilRenderComponents G;
    private GuiUtilRenderComponents H;
    private GuiUtilRenderComponents I;
    private GuiUtilRenderComponents J;
    private GuiUtilRenderComponents K;
    private GuiUtilRenderComponents L;
    private GuiUtilRenderComponents M;
    private GuiUtilRenderComponents N;
    private GuiUtilRenderComponents O;
    private GuiUtilRenderComponents P;
    private GuiUtilRenderComponents Q;
    private GuiUtilRenderComponents R;
    private GuiUtilRenderComponents S;
    private GuiUtilRenderComponents T;
    private final List<GuiButtonImage> U = Lists.<GuiButtonImage>newArrayList();
    private final DecimalFormat V = new DecimalFormat("0.0###");

    public bnc(TileEntityEndPortal p_i102_1_)
    {
        this.g = p_i102_1_;
        this.V.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        this.w.a();
        this.x.a();
        this.y.a();
        this.z.a();
        this.A.a();
        this.B.a();
        this.C.a();
        this.D.a();
        this.E.a();
        this.F.a();
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
        this.n.clear();
        this.G = this.b(new GuiUtilRenderComponents(0, this.l / 2 - 4 - 150, 210, 150, 20, LanguageManager.a("gui.done")));
        this.H = this.b(new GuiUtilRenderComponents(1, this.l / 2 + 4, 210, 150, 20, LanguageManager.a("gui.cancel")));
        this.I = this.b(new GuiUtilRenderComponents(9, this.l / 2 + 4 + 100, 185, 50, 20, LanguageManager.a("structure_block.button.save")));
        this.J = this.b(new GuiUtilRenderComponents(10, this.l / 2 + 4 + 100, 185, 50, 20, LanguageManager.a("structure_block.button.load")));
        this.O = this.b(new GuiUtilRenderComponents(18, this.l / 2 - 4 - 150, 185, 50, 20, "MODE"));
        this.P = this.b(new GuiUtilRenderComponents(19, this.l / 2 + 4 + 100, 120, 50, 20, LanguageManager.a("structure_block.button.detect_size")));
        this.Q = this.b(new GuiUtilRenderComponents(20, this.l / 2 + 4 + 100, 160, 50, 20, "ENTITIES"));
        this.R = this.b(new GuiUtilRenderComponents(21, this.l / 2 - 20, 185, 40, 20, "MIRROR"));
        this.S = this.b(new GuiUtilRenderComponents(22, this.l / 2 + 4 + 100, 80, 50, 20, "SHOWAIR"));
        this.T = this.b(new GuiUtilRenderComponents(23, this.l / 2 + 4 + 100, 80, 50, 20, "SHOWBB"));
        this.K = this.b(new GuiUtilRenderComponents(11, this.l / 2 - 1 - 40 - 1 - 40 - 20, 185, 40, 20, "0"));
        this.L = this.b(new GuiUtilRenderComponents(12, this.l / 2 - 1 - 40 - 20, 185, 40, 20, "90"));
        this.M = this.b(new GuiUtilRenderComponents(13, this.l / 2 + 1 + 20, 185, 40, 20, "180"));
        this.N = this.b(new GuiUtilRenderComponents(14, this.l / 2 + 1 + 40 + 1 + 20, 185, 40, 20, "270"));
        this.w = new GuiButtonImage(2, this.q, this.l / 2 - 152, 40, 300, 20);
        this.w.f(64);
        this.w.a(this.g.a());
        this.U.add(this.w);
        BlockPos blockpos = this.g.e();
        this.x = new GuiButtonImage(3, this.q, this.l / 2 - 152, 80, 80, 20);
        this.x.f(15);
        this.x.a(Integer.toString(blockpos.getX()));
        this.U.add(this.x);
        this.y = new GuiButtonImage(4, this.q, this.l / 2 - 72, 80, 80, 20);
        this.y.f(15);
        this.y.a(Integer.toString(blockpos.getY()));
        this.U.add(this.y);
        this.z = new GuiButtonImage(5, this.q, this.l / 2 + 8, 80, 80, 20);
        this.z.f(15);
        this.z.a(Integer.toString(blockpos.getZ()));
        this.U.add(this.z);
        BlockPos blockpos1 = this.g.f();
        this.A = new GuiButtonImage(6, this.q, this.l / 2 - 152, 120, 80, 20);
        this.A.f(15);
        this.A.a(Integer.toString(blockpos1.getX()));
        this.U.add(this.A);
        this.B = new GuiButtonImage(7, this.q, this.l / 2 - 72, 120, 80, 20);
        this.B.f(15);
        this.B.a(Integer.toString(blockpos1.getY()));
        this.U.add(this.B);
        this.C = new GuiButtonImage(8, this.q, this.l / 2 + 8, 120, 80, 20);
        this.C.f(15);
        this.C.a(Integer.toString(blockpos1.getZ()));
        this.U.add(this.C);
        this.D = new GuiButtonImage(15, this.q, this.l / 2 - 152, 120, 80, 20);
        this.D.f(15);
        this.D.a(this.V.format((double)this.g.n()));
        this.U.add(this.D);
        this.E = new GuiButtonImage(16, this.q, this.l / 2 - 72, 120, 80, 20);
        this.E.f(31);
        this.E.a(Long.toString(this.g.o()));
        this.U.add(this.E);
        this.F = new GuiButtonImage(17, this.q, this.l / 2 - 152, 120, 240, 20);
        this.F.f(128);
        this.F.a(this.g.j());
        this.U.add(this.F);
        this.h = this.g.h();
        this.h();
        this.i = this.g.i();
        this.i();
        this.s = this.g.k();
        this.j();
        this.t = this.g.m();
        this.a();
        this.u = this.g.G();
        this.f();
        this.v = this.g.I();
        this.g();
    }

    public void m()
    {
        Keyboard.enableRepeatEvents(false);
    }

    protected void a(GuiUtilRenderComponents p_a_1_)
    {
        if (p_a_1_.l)
        {
            if (p_a_1_.k == 1)
            {
                this.g.b(this.h);
                this.g.b(this.i);
                this.g.a(this.s);
                this.g.a(this.t);
                this.g.e(this.u);
                this.g.f(this.v);
                this.j.a((GuiCustomizeSkin)null);
            }
            else if (p_a_1_.k == 0)
            {
                if (this.b(1))
                {
                    this.j.a((GuiCustomizeSkin)null);
                }
            }
            else if (p_a_1_.k == 9)
            {
                if (this.g.k() == TileEntityEndPortal.a.a)
                {
                    this.b(2);
                    this.j.a((GuiCustomizeSkin)null);
                }
            }
            else if (p_a_1_.k == 10)
            {
                if (this.g.k() == TileEntityEndPortal.a.b)
                {
                    this.b(3);
                    this.j.a((GuiCustomizeSkin)null);
                }
            }
            else if (p_a_1_.k == 11)
            {
                this.g.b(BlockSandStone.TYPE);
                this.i();
            }
            else if (p_a_1_.k == 12)
            {
                this.g.b(BlockSandStone.b);
                this.i();
            }
            else if (p_a_1_.k == 13)
            {
                this.g.b(BlockSandStone.c);
                this.i();
            }
            else if (p_a_1_.k == 14)
            {
                this.g.b(BlockSandStone.d);
                this.i();
            }
            else if (p_a_1_.k == 18)
            {
                this.g.l();
                this.j();
            }
            else if (p_a_1_.k == 19)
            {
                if (this.g.k() == TileEntityEndPortal.a.a)
                {
                    this.b(4);
                    this.j.a((GuiCustomizeSkin)null);
                }
            }
            else if (p_a_1_.k == 20)
            {
                this.g.a(!this.g.m());
                this.a();
            }
            else if (p_a_1_.k == 22)
            {
                this.g.e(!this.g.G());
                this.f();
            }
            else if (p_a_1_.k == 23)
            {
                this.g.f(!this.g.I());
                this.g();
            }
            else if (p_a_1_.k == 21)
            {
                switch (this.g.h())
                {
                    case VARIANT:
                        this.g.b(BlockSilverfish.b);
                        break;

                    case b:
                        this.g.b(BlockSilverfish.c);
                        break;

                    case c:
                        this.g.b(BlockSilverfish.VARIANT);
                }

                this.h();
            }
        }
    }

    private void a()
    {
        boolean flag = !this.g.m();

        if (flag)
        {
            this.Q.j = LanguageManager.a("options.on");
        }
        else
        {
            this.Q.j = LanguageManager.a("options.off");
        }
    }

    private void f()
    {
        boolean flag = this.g.G();

        if (flag)
        {
            this.S.j = LanguageManager.a("options.on");
        }
        else
        {
            this.S.j = LanguageManager.a("options.off");
        }
    }

    private void g()
    {
        boolean flag = this.g.I();

        if (flag)
        {
            this.T.j = LanguageManager.a("options.on");
        }
        else
        {
            this.T.j = LanguageManager.a("options.off");
        }
    }

    private void h()
    {
        BlockSilverfish blocksilverfish = this.g.h();

        switch (blocksilverfish)
        {
            case VARIANT:
                this.R.j = "|";
                break;

            case b:
                this.R.j = "< >";
                break;

            case c:
                this.R.j = "^ v";
        }
    }

    private void i()
    {
        this.K.l = true;
        this.L.l = true;
        this.M.l = true;
        this.N.l = true;

        switch (this.g.i())
        {
            case TYPE:
                this.K.l = false;
                break;

            case c:
                this.M.l = false;
                break;

            case d:
                this.N.l = false;
                break;

            case b:
                this.L.l = false;
        }
    }

    private void j()
    {
        this.w.b(false);
        this.x.b(false);
        this.y.b(false);
        this.z.b(false);
        this.A.b(false);
        this.B.b(false);
        this.C.b(false);
        this.D.b(false);
        this.E.b(false);
        this.F.b(false);
        this.w.e(false);
        this.w.b(false);
        this.x.e(false);
        this.y.e(false);
        this.z.e(false);
        this.A.e(false);
        this.B.e(false);
        this.C.e(false);
        this.D.e(false);
        this.E.e(false);
        this.F.e(false);
        this.I.m = false;
        this.J.m = false;
        this.P.m = false;
        this.Q.m = false;
        this.R.m = false;
        this.K.m = false;
        this.L.m = false;
        this.M.m = false;
        this.N.m = false;
        this.S.m = false;
        this.T.m = false;

        switch (this.g.k())
        {
            case a:
                this.w.e(true);
                this.w.b(true);
                this.x.e(true);
                this.y.e(true);
                this.z.e(true);
                this.A.e(true);
                this.B.e(true);
                this.C.e(true);
                this.I.m = true;
                this.P.m = true;
                this.Q.m = true;
                this.S.m = true;
                break;

            case b:
                this.w.e(true);
                this.w.b(true);
                this.x.e(true);
                this.y.e(true);
                this.z.e(true);
                this.D.e(true);
                this.E.e(true);
                this.J.m = true;
                this.Q.m = true;
                this.R.m = true;
                this.K.m = true;
                this.L.m = true;
                this.M.m = true;
                this.N.m = true;
                this.T.m = true;
                this.i();
                break;

            case c:
                this.w.e(true);
                this.w.b(true);
                break;

            case d:
                this.F.e(true);
                this.F.b(true);
        }

        this.O.j = LanguageManager.a("structure_block.mode." + this.g.k().m());
    }

    private boolean b(int p_b_1_)
    {
        try
        {
            PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
            this.g.a(packetbuffer);
            packetbuffer.writeByte(p_b_1_);
            packetbuffer.writeString(this.g.k().toString());
            packetbuffer.writeString(this.w.b());
            packetbuffer.writeInt(this.c(this.x.b()));
            packetbuffer.writeInt(this.c(this.y.b()));
            packetbuffer.writeInt(this.c(this.z.b()));
            packetbuffer.writeInt(this.c(this.A.b()));
            packetbuffer.writeInt(this.c(this.B.b()));
            packetbuffer.writeInt(this.c(this.C.b()));
            packetbuffer.writeString(this.g.h().toString());
            packetbuffer.writeString(this.g.i().toString());
            packetbuffer.writeString(this.F.b());
            packetbuffer.writeBoolean(this.g.m());
            packetbuffer.writeBoolean(this.g.G());
            packetbuffer.writeBoolean(this.g.I());
            packetbuffer.writeFloat(this.b(this.D.b()));
            packetbuffer.writeVarLong(this.a(this.E.b()));
            this.j.v().sendPacketToServer(new CPacketCustomPayload("MC|Struct", packetbuffer));
            return true;
        }
        catch (Exception exception)
        {
            f.warn("Could not send structure block info", (Throwable)exception);
            return false;
        }
    }

    private long a(String p_a_1_)
    {
        try
        {
            return Long.valueOf(p_a_1_).longValue();
        }
        catch (NumberFormatException var3)
        {
            return 0L;
        }
    }

    private float b(String p_b_1_)
    {
        try
        {
            return Float.valueOf(p_b_1_).floatValue();
        }
        catch (NumberFormatException var3)
        {
            return 1.0F;
        }
    }

    private int c(String p_c_1_)
    {
        try
        {
            return Integer.parseInt(p_c_1_);
        }
        catch (NumberFormatException var3)
        {
            return 0;
        }
    }

    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    protected void keyTyped(char typedChar, int keyCode)
    {
        if (this.w.r() && b(typedChar, keyCode))
        {
            this.w.a(typedChar, keyCode);
        }

        if (this.x.r())
        {
            this.x.a(typedChar, keyCode);
        }

        if (this.y.r())
        {
            this.y.a(typedChar, keyCode);
        }

        if (this.z.r())
        {
            this.z.a(typedChar, keyCode);
        }

        if (this.A.r())
        {
            this.A.a(typedChar, keyCode);
        }

        if (this.B.r())
        {
            this.B.a(typedChar, keyCode);
        }

        if (this.C.r())
        {
            this.C.a(typedChar, keyCode);
        }

        if (this.D.r())
        {
            this.D.a(typedChar, keyCode);
        }

        if (this.E.r())
        {
            this.E.a(typedChar, keyCode);
        }

        if (this.F.r())
        {
            this.F.a(typedChar, keyCode);
        }

        if (keyCode == 15)
        {
            GuiButtonImage guibuttonimage = null;
            GuiButtonImage guibuttonimage1 = null;

            for (GuiButtonImage guibuttonimage2 : this.U)
            {
                if (guibuttonimage != null && guibuttonimage2.r())
                {
                    guibuttonimage1 = guibuttonimage2;
                    break;
                }

                if (guibuttonimage2.m() && guibuttonimage2.r())
                {
                    guibuttonimage = guibuttonimage2;
                }
            }

            if (guibuttonimage != null && guibuttonimage1 == null)
            {
                for (GuiButtonImage guibuttonimage3 : this.U)
                {
                    if (guibuttonimage3.r() && guibuttonimage3 != guibuttonimage)
                    {
                        guibuttonimage1 = guibuttonimage3;
                        break;
                    }
                }
            }

            if (guibuttonimage1 != null && guibuttonimage1 != guibuttonimage)
            {
                guibuttonimage.b(false);
                guibuttonimage1.b(true);
            }
        }

        if (keyCode != 28 && keyCode != 156)
        {
            if (keyCode == 1)
            {
                this.a(this.H);
            }
        }
        else
        {
            this.a(this.G);
        }
    }

    private static boolean b(char p_b_0_, int p_b_1_)
    {
        boolean flag = true;

        for (int i : a)
        {
            if (i == p_b_1_)
            {
                return true;
            }
        }

        for (char c0 : ChatAllowedCharacters.ILLEGAL_STRUCTURE_CHARACTERS)
        {
            if (c0 == p_b_0_)
            {
                flag = false;
                break;
            }
        }

        return flag;
    }

    protected void a(int p_a_1_, int p_a_2_, int p_a_3_)
    {
        super.a(p_a_1_, p_a_2_, p_a_3_);

        if (this.w.r())
        {
            this.w.a(p_a_1_, p_a_2_, p_a_3_);
        }

        if (this.x.r())
        {
            this.x.a(p_a_1_, p_a_2_, p_a_3_);
        }

        if (this.y.r())
        {
            this.y.a(p_a_1_, p_a_2_, p_a_3_);
        }

        if (this.z.r())
        {
            this.z.a(p_a_1_, p_a_2_, p_a_3_);
        }

        if (this.A.r())
        {
            this.A.a(p_a_1_, p_a_2_, p_a_3_);
        }

        if (this.B.r())
        {
            this.B.a(p_a_1_, p_a_2_, p_a_3_);
        }

        if (this.C.r())
        {
            this.C.a(p_a_1_, p_a_2_, p_a_3_);
        }

        if (this.D.r())
        {
            this.D.a(p_a_1_, p_a_2_, p_a_3_);
        }

        if (this.E.r())
        {
            this.E.a(p_a_1_, p_a_2_, p_a_3_);
        }

        if (this.F.r())
        {
            this.F.a(p_a_1_, p_a_2_, p_a_3_);
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.c();
        TileEntityEndPortal.a tileentityendportal$a = this.g.k();
        this.a(this.q, LanguageManager.a("tile.structureBlock.name"), this.l / 2, 10, 16777215);

        if (tileentityendportal$a != TileEntityEndPortal.a.d)
        {
            this.c(this.q, LanguageManager.a("structure_block.structure_name"), this.l / 2 - 153, 30, 10526880);
            this.w.g();
        }

        if (tileentityendportal$a == TileEntityEndPortal.a.b || tileentityendportal$a == TileEntityEndPortal.a.a)
        {
            this.c(this.q, LanguageManager.a("structure_block.position"), this.l / 2 - 153, 70, 10526880);
            this.x.g();
            this.y.g();
            this.z.g();
            String s = LanguageManager.a("structure_block.include_entities");
            int i = this.q.a(s);
            this.c(this.q, s, this.l / 2 + 154 - i, 150, 10526880);
        }

        if (tileentityendportal$a == TileEntityEndPortal.a.a)
        {
            this.c(this.q, LanguageManager.a("structure_block.size"), this.l / 2 - 153, 110, 10526880);
            this.A.g();
            this.B.g();
            this.C.g();
            String s2 = LanguageManager.a("structure_block.detect_size");
            int k = this.q.a(s2);
            this.c(this.q, s2, this.l / 2 + 154 - k, 110, 10526880);
            String s1 = LanguageManager.a("structure_block.show_air");
            int j = this.q.a(s1);
            this.c(this.q, s1, this.l / 2 + 154 - j, 70, 10526880);
        }

        if (tileentityendportal$a == TileEntityEndPortal.a.b)
        {
            this.c(this.q, LanguageManager.a("structure_block.integrity"), this.l / 2 - 153, 110, 10526880);
            this.D.g();
            this.E.g();
            String s3 = LanguageManager.a("structure_block.show_boundingbox");
            int l = this.q.a(s3);
            this.c(this.q, s3, this.l / 2 + 154 - l, 70, 10526880);
        }

        if (tileentityendportal$a == TileEntityEndPortal.a.d)
        {
            this.c(this.q, LanguageManager.a("structure_block.custom_data"), this.l / 2 - 153, 110, 10526880);
            this.F.g();
        }

        String s4 = "structure_block.mode_info." + tileentityendportal$a.m();
        this.c(this.q, LanguageManager.a(s4), this.l / 2 - 153, 174, 10526880);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public boolean d()
    {
        return false;
    }
}
