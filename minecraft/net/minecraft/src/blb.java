package net.minecraft.src;

import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.network.play.client.CPacketInput;

public class blb extends GuiYesNoCallback
{
    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        super.initGui();
        this.n.add(new GuiUtilRenderComponents(1, this.l / 2 - 100, this.m - 40, LanguageManager.a("multiplayer.stopSleeping")));
    }

    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    protected void keyTyped(char typedChar, int keyCode)
    {
        if (keyCode == 1)
        {
            this.a();
        }
        else if (keyCode != 28 && keyCode != 156)
        {
            super.keyTyped(typedChar, keyCode);
        }
        else
        {
            String s = this.MORE_INFO_TEXT.b().trim();

            if (!s.isEmpty())
            {
                this.j.fboEnable.g(s);
            }

            this.MORE_INFO_TEXT.a("");
            this.j.chatLinks.d().c();
        }
    }

    protected void a(GuiUtilRenderComponents p_a_1_)
    {
        if (p_a_1_.k == 1)
        {
            this.a();
        }
        else
        {
            super.a(p_a_1_);
        }
    }

    private void a()
    {
        WorldClient worldclient = this.j.fboEnable.WATER_CREATURE;
        worldclient.sendPacketToServer(new CPacketInput(this.j.fboEnable, CPacketInput.a.c));
    }
}
