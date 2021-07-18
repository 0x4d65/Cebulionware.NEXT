package net.minecraft.src;

import net.minecraft.network.INetHandler;
import net.minecraft.network.handshake.INetHandlerHandshakeServer;

public interface me extends INetHandler
{
    void a(INetHandlerHandshakeServer var1);
}
