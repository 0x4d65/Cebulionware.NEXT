package net.minecraft.network.rcon;

import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IWorldEventListener;

public class RConThreadBase implements ICommandSender
{
    private final StringBuffer field_72619_a = new StringBuffer();
    private final MinecraftServer field_72617_b;

    public RConThreadBase(MinecraftServer p_i996_1_)
    {
        this.field_72617_b = p_i996_1_;
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName()
    {
        return "Rcon";
    }

    /**
     * Send a chat message to the CommandSender
     */
    public void addChatMessage(ITextComponent component)
    {
        this.field_72619_a.append(component.getUnformattedText());
    }

    /**
     * Returns {@code true} if the CommandSender is allowed to execute the command, {@code false} if not
     */
    public boolean canCommandSenderUseCommand(int permLevel, String commandName)
    {
        return true;
    }

    public IWorldEventListener e()
    {
        return this.field_72617_b.e();
    }

    /**
     * Returns true if the command sender should be sent feedback about executed commands
     */
    public boolean sendCommandFeedback()
    {
        return true;
    }

    /**
     * Get the Minecraft server instance
     */
    public MinecraftServer getServer()
    {
        return this.field_72617_b;
    }
}
