package net.minecraft.src;

import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.util.ActionResult;
import net.minecraft.util.text.ITextComponent;

public class bug implements ActionResult
{
    private final String a;
    private final ITextComponent b;

    public bug(String p_i287_1_, ITextComponent p_i287_2_)
    {
        this.a = p_i287_1_;
        this.b = p_i287_2_;
    }

    public ContainerBeacon a(EnumPlayerModelParts p_a_1_, RecipeItemHelper p_a_2_)
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName()
    {
        return this.b.getUnformattedText();
    }

    /**
     * Returns true if this thing is named
     */
    public boolean hasCustomName()
    {
        return true;
    }

    public String l()
    {
        return this.a;
    }

    /**
     * Get the formatted ChatComponent that will be used for the sender's username in chat
     */
    public ITextComponent getDisplayName()
    {
        return this.b;
    }
}
