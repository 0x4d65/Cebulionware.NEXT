package net.minecraft.src;

import java.io.OutputStream;
import java.io.PrintStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class no extends PrintStream
{
    protected static final Logger a = LogManager.getLogger();
    protected final String b;

    public no(String p_i924_1_, OutputStream p_i924_2_)
    {
        super(p_i924_2_);
        this.b = p_i924_1_;
    }

    public void println(String p_println_1_)
    {
        this.a(p_println_1_);
    }

    public void println(Object p_println_1_)
    {
        this.a(String.valueOf(p_println_1_));
    }

    protected void a(String p_a_1_)
    {
        a.info("[{}]: {}", this.b, p_a_1_);
    }
}
