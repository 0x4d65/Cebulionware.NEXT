package net.minecraft.src;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Locale;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.client.renderer.VertexBufferUploader;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ScreenShotHelper;
import net.minecraft.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ARBFramebufferObject;
import org.lwjgl.opengl.ARBMultitexture;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexBufferObject;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.EXTBlendFuncSeparate;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GLContext;
import oshi.SystemInfo;
import oshi.hardware.Processor;

public class cii
{
    private static final Logger T = LogManager.getLogger();
    public static boolean a;
    public static boolean b;
    public static int c;
    public static int d;
    public static int e;
    public static int f;
    public static int g;
    public static int h;
    public static int i;
    public static int j;
    public static int k;
    private static cii.a U;
    public static boolean l;
    private static boolean V;
    private static boolean W;
    public static int m;
    public static int n;
    public static int o;
    public static int p;
    private static boolean X;
    public static int q;
    public static int r;
    public static int s;
    private static boolean Y;
    public static int t;
    public static int u;
    public static int v;
    public static int w;
    public static int x;
    public static int y;
    public static int z;
    public static int A;
    public static int B;
    public static int C;
    public static int D;
    public static int E;
    public static int F;
    public static int G;
    public static int H;
    public static int I;
    public static int J;
    public static int K;
    public static int L;
    private static boolean Z;
    public static boolean M;
    public static boolean N;
    public static boolean O;
    private static String aa = "";
    private static String ab;
    public static boolean P;
    public static boolean Q;
    private static boolean ac;
    public static int R;
    public static int S;

    public static void a()
    {
        ContextCapabilities contextcapabilities = GLContext.getCapabilities();
        X = contextcapabilities.GL_ARB_multitexture && !contextcapabilities.OpenGL13;
        Y = contextcapabilities.GL_ARB_texture_env_combine && !contextcapabilities.OpenGL13;

        if (X)
        {
            aa = aa + "Using ARB_multitexture.\n";
            q = 33984;
            r = 33985;
            s = 33986;
        }
        else
        {
            aa = aa + "Using GL 1.3 multitexturing.\n";
            q = 33984;
            r = 33985;
            s = 33986;
        }

        if (Y)
        {
            aa = aa + "Using ARB_texture_env_combine.\n";
            t = 34160;
            u = 34165;
            v = 34167;
            w = 34166;
            x = 34168;
            y = 34161;
            z = 34176;
            A = 34177;
            B = 34178;
            C = 34192;
            D = 34193;
            E = 34194;
            F = 34162;
            G = 34184;
            H = 34185;
            I = 34186;
            J = 34200;
            K = 34201;
            L = 34202;
        }
        else
        {
            aa = aa + "Using GL 1.3 texture combiners.\n";
            t = 34160;
            u = 34165;
            v = 34167;
            w = 34166;
            x = 34168;
            y = 34161;
            z = 34176;
            A = 34177;
            B = 34178;
            C = 34192;
            D = 34193;
            E = 34194;
            F = 34162;
            G = 34184;
            H = 34185;
            I = 34186;
            J = 34200;
            K = 34201;
            L = 34202;
        }

        M = contextcapabilities.GL_EXT_blend_func_separate && !contextcapabilities.OpenGL14;
        Z = contextcapabilities.OpenGL14 || contextcapabilities.GL_EXT_blend_func_separate;
        l = Z && (contextcapabilities.GL_ARB_framebuffer_object || contextcapabilities.GL_EXT_framebuffer_object || contextcapabilities.OpenGL30);

        if (l)
        {
            aa = aa + "Using framebuffer objects because ";

            if (contextcapabilities.OpenGL30)
            {
                aa = aa + "OpenGL 3.0 is supported and separate blending is supported.\n";
                U = cii.a.a;
                c = 36160;
                d = 36161;
                e = 36064;
                f = 36096;
                g = 36053;
                h = 36054;
                i = 36055;
                j = 36059;
                k = 36060;
            }
            else if (contextcapabilities.GL_ARB_framebuffer_object)
            {
                aa = aa + "ARB_framebuffer_object is supported and separate blending is supported.\n";
                U = cii.a.b;
                c = 36160;
                d = 36161;
                e = 36064;
                f = 36096;
                g = 36053;
                i = 36055;
                h = 36054;
                j = 36059;
                k = 36060;
            }
            else if (contextcapabilities.GL_EXT_framebuffer_object)
            {
                aa = aa + "EXT_framebuffer_object is supported.\n";
                U = cii.a.c;
                c = 36160;
                d = 36161;
                e = 36064;
                f = 36096;
                g = 36053;
                i = 36055;
                h = 36054;
                j = 36059;
                k = 36060;
            }
        }
        else
        {
            aa = aa + "Not using framebuffer objects because ";
            aa = aa + "OpenGL 1.4 is " + (contextcapabilities.OpenGL14 ? "" : "not ") + "supported, ";
            aa = aa + "EXT_blend_func_separate is " + (contextcapabilities.GL_EXT_blend_func_separate ? "" : "not ") + "supported, ";
            aa = aa + "OpenGL 3.0 is " + (contextcapabilities.OpenGL30 ? "" : "not ") + "supported, ";
            aa = aa + "ARB_framebuffer_object is " + (contextcapabilities.GL_ARB_framebuffer_object ? "" : "not ") + "supported, and ";
            aa = aa + "EXT_framebuffer_object is " + (contextcapabilities.GL_EXT_framebuffer_object ? "" : "not ") + "supported.\n";
        }

        N = contextcapabilities.OpenGL21;
        V = N || contextcapabilities.GL_ARB_vertex_shader && contextcapabilities.GL_ARB_fragment_shader && contextcapabilities.GL_ARB_shader_objects;
        aa = aa + "Shaders are " + (V ? "" : "not ") + "available because ";

        if (V)
        {
            if (contextcapabilities.OpenGL21)
            {
                aa = aa + "OpenGL 2.1 is supported.\n";
                W = false;
                m = 35714;
                n = 35713;
                o = 35633;
                p = 35632;
            }
            else
            {
                aa = aa + "ARB_shader_objects, ARB_vertex_shader, and ARB_fragment_shader are supported.\n";
                W = true;
                m = 35714;
                n = 35713;
                o = 35633;
                p = 35632;
            }
        }
        else
        {
            aa = aa + "OpenGL 2.1 is " + (contextcapabilities.OpenGL21 ? "" : "not ") + "supported, ";
            aa = aa + "ARB_shader_objects is " + (contextcapabilities.GL_ARB_shader_objects ? "" : "not ") + "supported, ";
            aa = aa + "ARB_vertex_shader is " + (contextcapabilities.GL_ARB_vertex_shader ? "" : "not ") + "supported, and ";
            aa = aa + "ARB_fragment_shader is " + (contextcapabilities.GL_ARB_fragment_shader ? "" : "not ") + "supported.\n";
        }

        O = l && V;
        String s = GL11.glGetString(GL11.GL_VENDOR).toLowerCase(Locale.ROOT);
        a = s.contains("nvidia");
        ac = !contextcapabilities.OpenGL15 && contextcapabilities.GL_ARB_vertex_buffer_object;
        P = contextcapabilities.OpenGL15 || ac;
        aa = aa + "VBOs are " + (P ? "" : "not ") + "available because ";

        if (P)
        {
            if (ac)
            {
                aa = aa + "ARB_vertex_buffer_object is supported.\n";
                S = 35044;
                R = 34962;
            }
            else
            {
                aa = aa + "OpenGL 1.5 is supported.\n";
                S = 35044;
                R = 34962;
            }
        }

        b = s.contains("ati");

        if (b)
        {
            if (P)
            {
                Q = true;
            }
            else
            {
                ScreenShotHelper.a.f.a(16.0F);
            }
        }

        try
        {
            Processor[] aprocessor = (new SystemInfo()).getHardware().getProcessors();
            ab = String.format("%dx %s", aprocessor.length, aprocessor[0]).replaceAll("\\s+", " ");
        }
        catch (Throwable var3)
        {
            ;
        }
    }

    public static boolean b()
    {
        return O;
    }

    public static String c()
    {
        return aa;
    }

    public static int a(int p_a_0_, int p_a_1_)
    {
        return W ? ARBShaderObjects.glGetObjectParameteriARB(p_a_0_, p_a_1_) : GL20.glGetProgrami(p_a_0_, p_a_1_);
    }

    public static void b(int p_b_0_, int p_b_1_)
    {
        if (W)
        {
            ARBShaderObjects.glAttachObjectARB(p_b_0_, p_b_1_);
        }
        else
        {
            GL20.glAttachShader(p_b_0_, p_b_1_);
        }
    }

    public static void a(int p_a_0_)
    {
        if (W)
        {
            ARBShaderObjects.glDeleteObjectARB(p_a_0_);
        }
        else
        {
            GL20.glDeleteShader(p_a_0_);
        }
    }

    public static int b(int p_b_0_)
    {
        return W ? ARBShaderObjects.glCreateShaderObjectARB(p_b_0_) : GL20.glCreateShader(p_b_0_);
    }

    public static void a(int p_a_0_, ByteBuffer p_a_1_)
    {
        if (W)
        {
            ARBShaderObjects.glShaderSourceARB(p_a_0_, p_a_1_);
        }
        else
        {
            GL20.glShaderSource(p_a_0_, p_a_1_);
        }
    }

    public static void c(int p_c_0_)
    {
        if (W)
        {
            ARBShaderObjects.glCompileShaderARB(p_c_0_);
        }
        else
        {
            GL20.glCompileShader(p_c_0_);
        }
    }

    public static int c(int p_c_0_, int p_c_1_)
    {
        return W ? ARBShaderObjects.glGetObjectParameteriARB(p_c_0_, p_c_1_) : GL20.glGetShaderi(p_c_0_, p_c_1_);
    }

    public static String d(int p_d_0_, int p_d_1_)
    {
        return W ? ARBShaderObjects.glGetInfoLogARB(p_d_0_, p_d_1_) : GL20.glGetShaderInfoLog(p_d_0_, p_d_1_);
    }

    public static String e(int p_e_0_, int p_e_1_)
    {
        return W ? ARBShaderObjects.glGetInfoLogARB(p_e_0_, p_e_1_) : GL20.glGetProgramInfoLog(p_e_0_, p_e_1_);
    }

    public static void d(int p_d_0_)
    {
        if (W)
        {
            ARBShaderObjects.glUseProgramObjectARB(p_d_0_);
        }
        else
        {
            GL20.glUseProgram(p_d_0_);
        }
    }

    public static int d()
    {
        return W ? ARBShaderObjects.glCreateProgramObjectARB() : GL20.glCreateProgram();
    }

    public static void e(int p_e_0_)
    {
        if (W)
        {
            ARBShaderObjects.glDeleteObjectARB(p_e_0_);
        }
        else
        {
            GL20.glDeleteProgram(p_e_0_);
        }
    }

    public static void f(int p_f_0_)
    {
        if (W)
        {
            ARBShaderObjects.glLinkProgramARB(p_f_0_);
        }
        else
        {
            GL20.glLinkProgram(p_f_0_);
        }
    }

    public static int a(int p_a_0_, CharSequence p_a_1_)
    {
        return W ? ARBShaderObjects.glGetUniformLocationARB(p_a_0_, p_a_1_) : GL20.glGetUniformLocation(p_a_0_, p_a_1_);
    }

    public static void a(int p_a_0_, IntBuffer p_a_1_)
    {
        if (W)
        {
            ARBShaderObjects.glUniform1ARB(p_a_0_, p_a_1_);
        }
        else
        {
            GL20.glUniform1(p_a_0_, p_a_1_);
        }
    }

    public static void f(int p_f_0_, int p_f_1_)
    {
        if (W)
        {
            ARBShaderObjects.glUniform1iARB(p_f_0_, p_f_1_);
        }
        else
        {
            GL20.glUniform1i(p_f_0_, p_f_1_);
        }
    }

    public static void a(int p_a_0_, FloatBuffer p_a_1_)
    {
        if (W)
        {
            ARBShaderObjects.glUniform1ARB(p_a_0_, p_a_1_);
        }
        else
        {
            GL20.glUniform1(p_a_0_, p_a_1_);
        }
    }

    public static void b(int p_b_0_, IntBuffer p_b_1_)
    {
        if (W)
        {
            ARBShaderObjects.glUniform2ARB(p_b_0_, p_b_1_);
        }
        else
        {
            GL20.glUniform2(p_b_0_, p_b_1_);
        }
    }

    public static void b(int p_b_0_, FloatBuffer p_b_1_)
    {
        if (W)
        {
            ARBShaderObjects.glUniform2ARB(p_b_0_, p_b_1_);
        }
        else
        {
            GL20.glUniform2(p_b_0_, p_b_1_);
        }
    }

    public static void c(int p_c_0_, IntBuffer p_c_1_)
    {
        if (W)
        {
            ARBShaderObjects.glUniform3ARB(p_c_0_, p_c_1_);
        }
        else
        {
            GL20.glUniform3(p_c_0_, p_c_1_);
        }
    }

    public static void c(int p_c_0_, FloatBuffer p_c_1_)
    {
        if (W)
        {
            ARBShaderObjects.glUniform3ARB(p_c_0_, p_c_1_);
        }
        else
        {
            GL20.glUniform3(p_c_0_, p_c_1_);
        }
    }

    public static void d(int p_d_0_, IntBuffer p_d_1_)
    {
        if (W)
        {
            ARBShaderObjects.glUniform4ARB(p_d_0_, p_d_1_);
        }
        else
        {
            GL20.glUniform4(p_d_0_, p_d_1_);
        }
    }

    public static void d(int p_d_0_, FloatBuffer p_d_1_)
    {
        if (W)
        {
            ARBShaderObjects.glUniform4ARB(p_d_0_, p_d_1_);
        }
        else
        {
            GL20.glUniform4(p_d_0_, p_d_1_);
        }
    }

    public static void a(int p_a_0_, boolean p_a_1_, FloatBuffer p_a_2_)
    {
        if (W)
        {
            ARBShaderObjects.glUniformMatrix2ARB(p_a_0_, p_a_1_, p_a_2_);
        }
        else
        {
            GL20.glUniformMatrix2(p_a_0_, p_a_1_, p_a_2_);
        }
    }

    public static void b(int p_b_0_, boolean p_b_1_, FloatBuffer p_b_2_)
    {
        if (W)
        {
            ARBShaderObjects.glUniformMatrix3ARB(p_b_0_, p_b_1_, p_b_2_);
        }
        else
        {
            GL20.glUniformMatrix3(p_b_0_, p_b_1_, p_b_2_);
        }
    }

    public static void c(int p_c_0_, boolean p_c_1_, FloatBuffer p_c_2_)
    {
        if (W)
        {
            ARBShaderObjects.glUniformMatrix4ARB(p_c_0_, p_c_1_, p_c_2_);
        }
        else
        {
            GL20.glUniformMatrix4(p_c_0_, p_c_1_, p_c_2_);
        }
    }

    public static int b(int p_b_0_, CharSequence p_b_1_)
    {
        return W ? ARBVertexShader.glGetAttribLocationARB(p_b_0_, p_b_1_) : GL20.glGetAttribLocation(p_b_0_, p_b_1_);
    }

    public static int e()
    {
        return ac ? ARBVertexBufferObject.glGenBuffersARB() : GL15.glGenBuffers();
    }

    public static void g(int p_g_0_, int p_g_1_)
    {
        if (ac)
        {
            ARBVertexBufferObject.glBindBufferARB(p_g_0_, p_g_1_);
        }
        else
        {
            GL15.glBindBuffer(p_g_0_, p_g_1_);
        }
    }

    public static void a(int p_a_0_, ByteBuffer p_a_1_, int p_a_2_)
    {
        if (ac)
        {
            ARBVertexBufferObject.glBufferDataARB(p_a_0_, p_a_1_, p_a_2_);
        }
        else
        {
            GL15.glBufferData(p_a_0_, p_a_1_, p_a_2_);
        }
    }

    public static void g(int p_g_0_)
    {
        if (ac)
        {
            ARBVertexBufferObject.glDeleteBuffersARB(p_g_0_);
        }
        else
        {
            GL15.glDeleteBuffers(p_g_0_);
        }
    }

    public static boolean f()
    {
        return P && GameSettings.z().snooperEnabled.w;
    }

    public static void h(int p_h_0_, int p_h_1_)
    {
        if (l)
        {
            switch (U)
            {
                case a:
                    GL30.glBindFramebuffer(p_h_0_, p_h_1_);
                    break;

                case b:
                    ARBFramebufferObject.glBindFramebuffer(p_h_0_, p_h_1_);
                    break;

                case c:
                    EXTFramebufferObject.glBindFramebufferEXT(p_h_0_, p_h_1_);
            }
        }
    }

    public static void i(int p_i_0_, int p_i_1_)
    {
        if (l)
        {
            switch (U)
            {
                case a:
                    GL30.glBindRenderbuffer(p_i_0_, p_i_1_);
                    break;

                case b:
                    ARBFramebufferObject.glBindRenderbuffer(p_i_0_, p_i_1_);
                    break;

                case c:
                    EXTFramebufferObject.glBindRenderbufferEXT(p_i_0_, p_i_1_);
            }
        }
    }

    public static void h(int p_h_0_)
    {
        if (l)
        {
            switch (U)
            {
                case a:
                    GL30.glDeleteRenderbuffers(p_h_0_);
                    break;

                case b:
                    ARBFramebufferObject.glDeleteRenderbuffers(p_h_0_);
                    break;

                case c:
                    EXTFramebufferObject.glDeleteRenderbuffersEXT(p_h_0_);
            }
        }
    }

    public static void i(int p_i_0_)
    {
        if (l)
        {
            switch (U)
            {
                case a:
                    GL30.glDeleteFramebuffers(p_i_0_);
                    break;

                case b:
                    ARBFramebufferObject.glDeleteFramebuffers(p_i_0_);
                    break;

                case c:
                    EXTFramebufferObject.glDeleteFramebuffersEXT(p_i_0_);
            }
        }
    }

    public static int g()
    {
        if (!l)
        {
            return -1;
        }
        else
        {
            switch (U)
            {
                case a:
                    return GL30.glGenFramebuffers();

                case b:
                    return ARBFramebufferObject.glGenFramebuffers();

                case c:
                    return EXTFramebufferObject.glGenFramebuffersEXT();

                default:
                    return -1;
            }
        }
    }

    public static int h()
    {
        if (!l)
        {
            return -1;
        }
        else
        {
            switch (U)
            {
                case a:
                    return GL30.glGenRenderbuffers();

                case b:
                    return ARBFramebufferObject.glGenRenderbuffers();

                case c:
                    return EXTFramebufferObject.glGenRenderbuffersEXT();

                default:
                    return -1;
            }
        }
    }

    public static void a(int p_a_0_, int p_a_1_, int p_a_2_, int p_a_3_)
    {
        if (l)
        {
            switch (U)
            {
                case a:
                    GL30.glRenderbufferStorage(p_a_0_, p_a_1_, p_a_2_, p_a_3_);
                    break;

                case b:
                    ARBFramebufferObject.glRenderbufferStorage(p_a_0_, p_a_1_, p_a_2_, p_a_3_);
                    break;

                case c:
                    EXTFramebufferObject.glRenderbufferStorageEXT(p_a_0_, p_a_1_, p_a_2_, p_a_3_);
            }
        }
    }

    public static void b(int p_b_0_, int p_b_1_, int p_b_2_, int p_b_3_)
    {
        if (l)
        {
            switch (U)
            {
                case a:
                    GL30.glFramebufferRenderbuffer(p_b_0_, p_b_1_, p_b_2_, p_b_3_);
                    break;

                case b:
                    ARBFramebufferObject.glFramebufferRenderbuffer(p_b_0_, p_b_1_, p_b_2_, p_b_3_);
                    break;

                case c:
                    EXTFramebufferObject.glFramebufferRenderbufferEXT(p_b_0_, p_b_1_, p_b_2_, p_b_3_);
            }
        }
    }

    public static int j(int p_j_0_)
    {
        if (!l)
        {
            return -1;
        }
        else
        {
            switch (U)
            {
                case a:
                    return GL30.glCheckFramebufferStatus(p_j_0_);

                case b:
                    return ARBFramebufferObject.glCheckFramebufferStatus(p_j_0_);

                case c:
                    return EXTFramebufferObject.glCheckFramebufferStatusEXT(p_j_0_);

                default:
                    return -1;
            }
        }
    }

    public static void a(int p_a_0_, int p_a_1_, int p_a_2_, int p_a_3_, int p_a_4_)
    {
        if (l)
        {
            switch (U)
            {
                case a:
                    GL30.glFramebufferTexture2D(p_a_0_, p_a_1_, p_a_2_, p_a_3_, p_a_4_);
                    break;

                case b:
                    ARBFramebufferObject.glFramebufferTexture2D(p_a_0_, p_a_1_, p_a_2_, p_a_3_, p_a_4_);
                    break;

                case c:
                    EXTFramebufferObject.glFramebufferTexture2DEXT(p_a_0_, p_a_1_, p_a_2_, p_a_3_, p_a_4_);
            }
        }
    }

    public static void k(int p_k_0_)
    {
        if (X)
        {
            ARBMultitexture.glActiveTextureARB(p_k_0_);
        }
        else
        {
            GL13.glActiveTexture(p_k_0_);
        }
    }

    public static void l(int p_l_0_)
    {
        if (X)
        {
            ARBMultitexture.glClientActiveTextureARB(p_l_0_);
        }
        else
        {
            GL13.glClientActiveTexture(p_l_0_);
        }
    }

    public static void a(int p_a_0_, float p_a_1_, float p_a_2_)
    {
        if (X)
        {
            ARBMultitexture.glMultiTexCoord2fARB(p_a_0_, p_a_1_, p_a_2_);
        }
        else
        {
            GL13.glMultiTexCoord2f(p_a_0_, p_a_1_, p_a_2_);
        }
    }

    public static void c(int p_c_0_, int p_c_1_, int p_c_2_, int p_c_3_)
    {
        if (Z)
        {
            if (M)
            {
                EXTBlendFuncSeparate.glBlendFuncSeparateEXT(p_c_0_, p_c_1_, p_c_2_, p_c_3_);
            }
            else
            {
                GL14.glBlendFuncSeparate(p_c_0_, p_c_1_, p_c_2_, p_c_3_);
            }
        }
        else
        {
            GL11.glBlendFunc(p_c_0_, p_c_1_);
        }
    }

    public static boolean j()
    {
        return l && GameSettings.z().snooperEnabled.h;
    }

    public static String k()
    {
        return ab == null ? "<unknown>" : ab;
    }

    public static void m(int p_m_0_)
    {
        ItemRenderer.z();
        ItemRenderer.a(false);
        VertexBufferUploader vertexbufferuploader = VertexBufferUploader.a();
        RegionRenderCacheBuilder regionrendercachebuilder = vertexbufferuploader.c();
        GL11.glLineWidth(4.0F);
        regionrendercachebuilder.a(1, VertexFormat.uvOffsetsById);
        regionrendercachebuilder.b(0.0D, 0.0D, 0.0D).b(0, 0, 0, 255).d();
        regionrendercachebuilder.b((double)p_m_0_, 0.0D, 0.0D).b(0, 0, 0, 255).d();
        regionrendercachebuilder.b(0.0D, 0.0D, 0.0D).b(0, 0, 0, 255).d();
        regionrendercachebuilder.b(0.0D, (double)p_m_0_, 0.0D).b(0, 0, 0, 255).d();
        regionrendercachebuilder.b(0.0D, 0.0D, 0.0D).b(0, 0, 0, 255).d();
        regionrendercachebuilder.b(0.0D, 0.0D, (double)p_m_0_).b(0, 0, 0, 255).d();
        vertexbufferuploader.b();
        GL11.glLineWidth(2.0F);
        regionrendercachebuilder.a(1, VertexFormat.uvOffsetsById);
        regionrendercachebuilder.b(0.0D, 0.0D, 0.0D).b(255, 0, 0, 255).d();
        regionrendercachebuilder.b((double)p_m_0_, 0.0D, 0.0D).b(255, 0, 0, 255).d();
        regionrendercachebuilder.b(0.0D, 0.0D, 0.0D).b(0, 255, 0, 255).d();
        regionrendercachebuilder.b(0.0D, (double)p_m_0_, 0.0D).b(0, 255, 0, 255).d();
        regionrendercachebuilder.b(0.0D, 0.0D, 0.0D).b(127, 127, 255, 255).d();
        regionrendercachebuilder.b(0.0D, 0.0D, (double)p_m_0_).b(127, 127, 255, 255).d();
        vertexbufferuploader.b();
        GL11.glLineWidth(1.0F);
        ItemRenderer.a(true);
        ItemRenderer.y();
    }

    public static void a(File p_a_0_)
    {
        String s = p_a_0_.getAbsolutePath();

        if (Util.getOSType() == Util.EnumOS.OSX)
        {
            try
            {
                T.info(s);
                Runtime.getRuntime().exec(new String[] {"/usr/bin/open", s});
                return;
            }
            catch (IOException ioexception1)
            {
                T.error("Couldn't open file", (Throwable)ioexception1);
            }
        }
        else if (Util.getOSType() == Util.EnumOS.WINDOWS)
        {
            String s1 = String.format("cmd.exe /C start \"Open file\" \"%s\"", s);

            try
            {
                Runtime.getRuntime().exec(s1);
                return;
            }
            catch (IOException ioexception)
            {
                T.error("Couldn't open file", (Throwable)ioexception);
            }
        }

        boolean flag = false;

        try
        {
            Class<?> oclass = Class.forName("java.awt.Desktop");
            Object object = oclass.getMethod("getDesktop").invoke((Object)null);
            oclass.getMethod("browse", URI.class).invoke(object, p_a_0_.toURI());
        }
        catch (Throwable throwable)
        {
            T.error("Couldn't open link", throwable);
            flag = true;
        }

        if (flag)
        {
            T.info("Opening via system class!");
            Sys.openURL("file://" + s);
        }
    }

    static enum a
    {
        a,
        b,
        c;
    }
}
