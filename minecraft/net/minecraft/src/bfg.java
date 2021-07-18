package net.minecraft.src;

import java.io.File;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.SaveHandlerMP;
import net.minecraft.world.storage.WorldSummary;

public interface bfg
{
    String a();

    ISaveFormat a(String var1, boolean var2);

    List<SaveHandlerMP> b() throws WorldSummary;

    void d();

    @Nullable
    AnvilConverterException c(String var1);

    boolean d(String var1);

    boolean e(String var1);

    void a(String var1, String var2);

    boolean a(String var1);

    boolean b(String var1);

    boolean a(String var1, IStringSerializable var2);

    boolean f(String var1);

    File b(String var1, String var2);
}
