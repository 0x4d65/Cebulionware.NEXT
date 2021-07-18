package net.minecraft.src;

import java.io.IOException;
import net.minecraft.client.shader.Shader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class cdb
{
    private static final Logger a = LogManager.getLogger();
    private static cdb b;

    public static void a()
    {
        b = new cdb();
    }

    public static cdb b()
    {
        return b;
    }

    public void a(Shader p_a_1_)
    {
        p_a_1_.f().b(p_a_1_);
        p_a_1_.e().b(p_a_1_);
        cii.e(p_a_1_.h());
    }

    public int c() throws nj
    {
        int i = cii.d();

        if (i <= 0)
        {
            throw new nj("Could not create shader program (returned program ID " + i + ")");
        }
        else
        {
            return i;
        }
    }

    public void b(Shader p_b_1_) throws IOException
    {
        p_b_1_.f().a(p_b_1_);
        p_b_1_.e().a(p_b_1_);
        cii.f(p_b_1_.h());
        int i = cii.a(p_b_1_.h(), cii.m);

        if (i == 0)
        {
            a.warn("Error encountered when linking program containing VS {} and FS {}. Log output:", p_b_1_.e().getShaderName(), p_b_1_.f().getShaderName());
            a.warn(cii.e(p_b_1_.h(), 32768));
        }
    }
}
