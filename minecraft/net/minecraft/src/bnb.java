package net.minecraft.src;

import net.minecraft.block.BlockBone;
import net.minecraft.block.IGrowable;
import net.minecraft.client.gui.GuiCustomizeSkin;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.tileentity.TileEntityStructure;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.text.TextComponentString;
import org.lwjgl.input.Keyboard;

public class bnb extends GuiCustomizeSkin
{
    private final TileEntityStructure a;
    private int f;
    private int g;
    private GuiUtilRenderComponents h;

    public bnb(TileEntityStructure p_i74_1_)
    {
        this.a = p_i74_1_;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        this.n.clear();
        Keyboard.enableRepeatEvents(true);
        this.h = this.b(new GuiUtilRenderComponents(0, this.l / 2 - 100, this.m / 4 + 120, LanguageManager.a("gui.done")));
        this.a.setIgnoresEntities(false);
    }

    public void m()
    {
        Keyboard.enableRepeatEvents(false);
        WorldClient worldclient = this.j.v();

        if (worldclient != null)
        {
            worldclient.sendPacketToServer(new CPacketAnimation(this.a.w(), this.a.name));
        }

        this.a.setIgnoresEntities(true);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        ++this.f;
    }

    protected void a(GuiUtilRenderComponents p_a_1_)
    {
        if (p_a_1_.l)
        {
            if (p_a_1_.k == 0)
            {
                this.a.y_();
                this.j.a((GuiCustomizeSkin)null);
            }
        }
    }

    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    protected void keyTyped(char typedChar, int keyCode)
    {
        if (keyCode == 200)
        {
            this.g = this.g - 1 & 3;
        }

        if (keyCode == 208 || keyCode == 28 || keyCode == 156)
        {
            this.g = this.g + 1 & 3;
        }

        String s = this.a.name[this.g].getUnformattedText();

        if (keyCode == 14 && !s.isEmpty())
        {
            s = s.substring(0, s.length() - 1);
        }

        if (ChatAllowedCharacters.isAllowedCharacter(typedChar) && this.q.a(s + typedChar) <= 90)
        {
            s = s + typedChar;
        }

        this.a.name[this.g] = new TextComponentString(s);

        if (keyCode == 1)
        {
            this.a(this.h);
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.c();
        this.a(this.q, LanguageManager.a("sign.edit"), this.l / 2, 40, 16777215);
        ItemRenderer.c(1.0F, 1.0F, 1.0F, 1.0F);
        ItemRenderer.G();
        ItemRenderer.c((float)(this.l / 2), 0.0F, 50.0F);
        float f = 93.75F;
        ItemRenderer.b(-93.75F, -93.75F, -93.75F);
        ItemRenderer.b(180.0F, 0.0F, 1.0F, 0.0F);
        BlockBone blockbone = this.a.x();

        if (blockbone == IGrowable.an)
        {
            float f1 = (float)(this.a.v() * 360) / 16.0F;
            ItemRenderer.b(f1, 0.0F, 1.0F, 0.0F);
            ItemRenderer.c(0.0F, -1.0625F, 0.0F);
        }
        else
        {
            int i = this.a.v();
            float f2 = 0.0F;

            if (i == 2)
            {
                f2 = 180.0F;
            }

            if (i == 4)
            {
                f2 = 90.0F;
            }

            if (i == 5)
            {
                f2 = -90.0F;
            }

            ItemRenderer.b(f2, 0.0F, 1.0F, 0.0F);
            ItemRenderer.c(0.0F, -1.0625F, 0.0F);
        }

        if (this.f / 6 % 2 == 0)
        {
            this.a.author = this.g;
        }

        TileEntityChestRenderer.TEXTURE_TRAPPED_DOUBLE.a(this.a, -0.5D, -0.75D, -0.5D, 0.0F);
        this.a.author = -1;
        ItemRenderer.H();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
