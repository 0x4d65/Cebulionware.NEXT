package net.minecraft.src;

import net.minecraft.client.gui.chat.OverlayChatListener;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;

public class biw implements OverlayChatListener
{
    private final GameSettings a;

    public biw(GameSettings p_i44_1_)
    {
        this.a = p_i44_1_;
    }

    public void func_192576_a(ChatType p_192576_1_, ITextComponent p_192576_2_)
    {
        this.a.chatLinks.a(p_192576_2_, false);
    }
}
