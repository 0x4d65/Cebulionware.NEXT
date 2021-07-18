package net.minecraft.src;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import net.minecraft.client.shader.Shader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix4f;

public class cdc
{
    private static final Logger a = LogManager.getLogger();
    private int b;
    private final int c;
    private final int d;
    private final IntBuffer e;
    private final FloatBuffer f;
    private final String g;
    private boolean h;
    private final Shader i;

    public cdc(String p_i570_1_, int p_i570_2_, int p_i570_3_, Shader p_i570_4_)
    {
        this.g = p_i570_1_;
        this.c = p_i570_3_;
        this.d = p_i570_2_;
        this.i = p_i570_4_;

        if (p_i570_2_ <= 3)
        {
            this.e = BufferUtils.createIntBuffer(p_i570_3_);
            this.f = null;
        }
        else
        {
            this.e = null;
            this.f = BufferUtils.createFloatBuffer(p_i570_3_);
        }

        this.b = -1;
        this.h();
    }

    private void h()
    {
        this.h = true;

        if (this.i != null)
        {
            this.i.preLoadShader();
        }
    }

    public static int a(String p_a_0_)
    {
        int i = -1;

        if ("int".equals(p_a_0_))
        {
            i = 0;
        }
        else if ("float".equals(p_a_0_))
        {
            i = 4;
        }
        else if (p_a_0_.startsWith("matrix"))
        {
            if (p_a_0_.endsWith("2x2"))
            {
                i = 8;
            }
            else if (p_a_0_.endsWith("3x3"))
            {
                i = 9;
            }
            else if (p_a_0_.endsWith("4x4"))
            {
                i = 10;
            }
        }

        return i;
    }

    public void b(int p_b_1_)
    {
        this.b = p_b_1_;
    }

    public String a()
    {
        return this.g;
    }

    public void a(float p_a_1_)
    {
        this.f.position(0);
        this.f.put(0, p_a_1_);
        this.h();
    }

    public void a(float p_a_1_, float p_a_2_)
    {
        this.f.position(0);
        this.f.put(0, p_a_1_);
        this.f.put(1, p_a_2_);
        this.h();
    }

    public void a(float p_a_1_, float p_a_2_, float p_a_3_)
    {
        this.f.position(0);
        this.f.put(0, p_a_1_);
        this.f.put(1, p_a_2_);
        this.f.put(2, p_a_3_);
        this.h();
    }

    public void a(float p_a_1_, float p_a_2_, float p_a_3_, float p_a_4_)
    {
        this.f.position(0);
        this.f.put(p_a_1_);
        this.f.put(p_a_2_);
        this.f.put(p_a_3_);
        this.f.put(p_a_4_);
        this.f.flip();
        this.h();
    }

    public void b(float p_b_1_, float p_b_2_, float p_b_3_, float p_b_4_)
    {
        this.f.position(0);

        if (this.d >= 4)
        {
            this.f.put(0, p_b_1_);
        }

        if (this.d >= 5)
        {
            this.f.put(1, p_b_2_);
        }

        if (this.d >= 6)
        {
            this.f.put(2, p_b_3_);
        }

        if (this.d >= 7)
        {
            this.f.put(3, p_b_4_);
        }

        this.h();
    }

    public void a(int p_a_1_, int p_a_2_, int p_a_3_, int p_a_4_)
    {
        this.e.position(0);

        if (this.d >= 0)
        {
            this.e.put(0, p_a_1_);
        }

        if (this.d >= 1)
        {
            this.e.put(1, p_a_2_);
        }

        if (this.d >= 2)
        {
            this.e.put(2, p_a_3_);
        }

        if (this.d >= 3)
        {
            this.e.put(3, p_a_4_);
        }

        this.h();
    }

    public void a(float[] p_a_1_)
    {
        if (p_a_1_.length < this.c)
        {
            a.warn("Uniform.set called with a too-small value array (expected {}, got {}). Ignoring.", Integer.valueOf(this.c), Integer.valueOf(p_a_1_.length));
        }
        else
        {
            this.f.position(0);
            this.f.put(p_a_1_);
            this.f.position(0);
            this.h();
        }
    }

    public void a(float p_a_1_, float p_a_2_, float p_a_3_, float p_a_4_, float p_a_5_, float p_a_6_, float p_a_7_, float p_a_8_, float p_a_9_, float p_a_10_, float p_a_11_, float p_a_12_, float p_a_13_, float p_a_14_, float p_a_15_, float p_a_16_)
    {
        this.f.position(0);
        this.f.put(0, p_a_1_);
        this.f.put(1, p_a_2_);
        this.f.put(2, p_a_3_);
        this.f.put(3, p_a_4_);
        this.f.put(4, p_a_5_);
        this.f.put(5, p_a_6_);
        this.f.put(6, p_a_7_);
        this.f.put(7, p_a_8_);
        this.f.put(8, p_a_9_);
        this.f.put(9, p_a_10_);
        this.f.put(10, p_a_11_);
        this.f.put(11, p_a_12_);
        this.f.put(12, p_a_13_);
        this.f.put(13, p_a_14_);
        this.f.put(14, p_a_15_);
        this.f.put(15, p_a_16_);
        this.h();
    }

    public void a(Matrix4f p_a_1_)
    {
        this.a(p_a_1_.m00, p_a_1_.m01, p_a_1_.m02, p_a_1_.m03, p_a_1_.m10, p_a_1_.m11, p_a_1_.m12, p_a_1_.m13, p_a_1_.m20, p_a_1_.m21, p_a_1_.m22, p_a_1_.m23, p_a_1_.m30, p_a_1_.m31, p_a_1_.m32, p_a_1_.m33);
    }

    public void b()
    {
        if (!this.h)
        {
            ;
        }

        this.h = false;

        if (this.d <= 3)
        {
            this.i();
        }
        else if (this.d <= 7)
        {
            this.j();
        }
        else
        {
            if (this.d > 10)
            {
                a.warn("Uniform.upload called, but type value ({}) is not a valid type. Ignoring.", (int)this.d);
                return;
            }

            this.k();
        }
    }

    private void i()
    {
        switch (this.d)
        {
            case 0:
                cii.a(this.b, this.e);
                break;

            case 1:
                cii.b(this.b, this.e);
                break;

            case 2:
                cii.c(this.b, this.e);
                break;

            case 3:
                cii.d(this.b, this.e);
                break;

            default:
                a.warn("Uniform.upload called, but count value ({}) is  not in the range of 1 to 4. Ignoring.", (int)this.c);
        }
    }

    private void j()
    {
        switch (this.d)
        {
            case 4:
                cii.a(this.b, this.f);
                break;

            case 5:
                cii.b(this.b, this.f);
                break;

            case 6:
                cii.c(this.b, this.f);
                break;

            case 7:
                cii.d(this.b, this.f);
                break;

            default:
                a.warn("Uniform.upload called, but count value ({}) is not in the range of 1 to 4. Ignoring.", (int)this.c);
        }
    }

    private void k()
    {
        switch (this.d)
        {
            case 8:
                cii.a(this.b, true, this.f);
                break;

            case 9:
                cii.b(this.b, true, this.f);
                break;

            case 10:
                cii.c(this.b, true, this.f);
        }
    }
}
