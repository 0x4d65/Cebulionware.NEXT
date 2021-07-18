package net.minecraft.src;

import java.io.IOException;
import javax.annotation.Nullable;
import net.minecraft.world.IWorldEventListener;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.BlockStatePaletteLinear;

public interface ayf
{
    @Nullable
    BlockStatePaletteLinear a(IWorldEventListener var1, int var2, int var3) throws IOException;

    void a(IWorldEventListener var1, BlockStatePaletteLinear var2) throws IOException, WorldSettings;

    void b(IWorldEventListener var1, BlockStatePaletteLinear var2) throws IOException;

    void b();

    void c();

    boolean a(int var1, int var2);
}
