package net.minecraft.src;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.BlockBone;
import net.minecraft.block.IGrowable;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraft.world.biome.BiomeDesert;

public class bbb
{
    private final List<bbc> a = Lists.<bbc>newArrayList();
    private final Map<String, Map<String, String>> b = Maps.<String, Map<String, String>>newHashMap();
    private int c;

    public int a()
    {
        return this.c;
    }

    public void a(int p_a_1_)
    {
        this.c = p_a_1_;
    }

    public Map<String, Map<String, String>> b()
    {
        return this.b;
    }

    public List<bbc> c()
    {
        return this.a;
    }

    public void d()
    {
        int i = 0;

        for (bbc bbc : this.a)
        {
            bbc.b(i);
            i += bbc.b();
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append((int)3);
        stringbuilder.append(";");

        for (int i = 0; i < this.a.size(); ++i)
        {
            if (i > 0)
            {
                stringbuilder.append(",");
            }

            stringbuilder.append(this.a.get(i));
        }

        stringbuilder.append(";");
        stringbuilder.append(this.c);

        if (this.b.isEmpty())
        {
            stringbuilder.append(";");
        }
        else
        {
            stringbuilder.append(";");
            int k = 0;

            for (Entry<String, Map<String, String>> entry : this.b.entrySet())
            {
                if (k++ > 0)
                {
                    stringbuilder.append(",");
                }

                stringbuilder.append(((String)entry.getKey()).toLowerCase(Locale.ROOT));
                Map<String, String> map = (Map)entry.getValue();

                if (!map.isEmpty())
                {
                    stringbuilder.append("(");
                    int j = 0;

                    for (Entry<String, String> entry1 : map.entrySet())
                    {
                        if (j++ > 0)
                        {
                            stringbuilder.append(" ");
                        }

                        stringbuilder.append(entry1.getKey());
                        stringbuilder.append("=");
                        stringbuilder.append(entry1.getValue());
                    }

                    stringbuilder.append(")");
                }
            }
        }

        return stringbuilder.toString();
    }

    private static bbc a(int p_a_0_, String p_a_1_, int p_a_2_)
    {
        String[] astring = p_a_0_ >= 3 ? p_a_1_.split("\\*", 2) : p_a_1_.split("x", 2);
        int i = 1;
        int j = 0;

        if (astring.length == 2)
        {
            try
            {
                i = Integer.parseInt(astring[0]);

                if (p_a_2_ + i >= 256)
                {
                    i = 256 - p_a_2_;
                }

                if (i < 0)
                {
                    i = 0;
                }
            }
            catch (Throwable var8)
            {
                return null;
            }
        }

        BlockBone blockbone;

        try
        {
            String s = astring[astring.length - 1];

            if (p_a_0_ < 3)
            {
                astring = s.split(":", 2);

                if (astring.length > 1)
                {
                    j = Integer.parseInt(astring[1]);
                }

                blockbone = BlockBone.c(Integer.parseInt(astring[0]));
            }
            else
            {
                astring = s.split(":", 3);
                blockbone = astring.length > 1 ? BlockBone.b(astring[0] + ":" + astring[1]) : null;

                if (blockbone != null)
                {
                    j = astring.length > 2 ? Integer.parseInt(astring[2]) : 0;
                }
                else
                {
                    blockbone = BlockBone.b(astring[0]);

                    if (blockbone != null)
                    {
                        j = astring.length > 1 ? Integer.parseInt(astring[1]) : 0;
                    }
                }

                if (blockbone == null)
                {
                    return null;
                }
            }

            if (blockbone == IGrowable.a)
            {
                j = 0;
            }

            if (j < 0 || j > 15)
            {
                j = 0;
            }
        }
        catch (Throwable var9)
        {
            return null;
        }

        bbc bbc = new bbc(p_a_0_, i, blockbone, j);
        bbc.b(p_a_2_);
        return bbc;
    }

    private static List<bbc> a(int p_a_0_, String p_a_1_)
    {
        if (p_a_1_ != null && p_a_1_.length() >= 1)
        {
            List<bbc> list = Lists.<bbc>newArrayList();
            String[] astring = p_a_1_.split(",");
            int i = 0;

            for (String s : astring)
            {
                bbc bbc = a(p_a_0_, s, i);

                if (bbc == null)
                {
                    return null;
                }

                list.add(bbc);
                i += bbc.b();
            }

            return list;
        }
        else
        {
            return null;
        }
    }

    public static bbb a(String p_a_0_)
    {
        if (p_a_0_ == null)
        {
            return e();
        }
        else
        {
            String[] astring = p_a_0_.split(";", -1);
            int i = astring.length == 1 ? 0 : IProgressUpdate.a(astring[0], 0);

            if (i >= 0 && i <= 3)
            {
                bbb bbb = new bbb();
                int j = astring.length == 1 ? 0 : 1;
                List<bbc> list = a(i, astring[j++]);

                if (list != null && !list.isEmpty())
                {
                    bbb.c().addAll(list);
                    bbb.d();
                    int k = BiomeColorHelper.a(BiomeDesert.c);

                    if (i > 0 && astring.length > j)
                    {
                        k = IProgressUpdate.a(astring[j++], k);
                    }

                    bbb.a(k);

                    if (i > 0 && astring.length > j)
                    {
                        String[] astring1 = astring[j++].toLowerCase(Locale.ROOT).split(",");

                        for (String s : astring1)
                        {
                            String[] astring2 = s.split("\\(", 2);
                            Map<String, String> map = Maps.<String, String>newHashMap();

                            if (!astring2[0].isEmpty())
                            {
                                bbb.b().put(astring2[0], map);

                                if (astring2.length > 1 && astring2[1].endsWith(")") && astring2[1].length() > 1)
                                {
                                    String[] astring3 = astring2[1].substring(0, astring2[1].length() - 1).split(" ");

                                    for (String s1 : astring3)
                                    {
                                        String[] astring4 = s1.split("=", 2);

                                        if (astring4.length == 2)
                                        {
                                            map.put(astring4[0], astring4[1]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        bbb.b().put("village", Maps.newHashMap());
                    }

                    return bbb;
                }
                else
                {
                    return e();
                }
            }
            else
            {
                return e();
            }
        }
    }

    public static bbb e()
    {
        bbb bbb = new bbb();
        bbb.a(BiomeColorHelper.a(BiomeDesert.c));
        bbb.c().add(new bbc(1, IGrowable.h));
        bbb.c().add(new bbc(2, IGrowable.d));
        bbb.c().add(new bbc(1, IGrowable.c));
        bbb.d();
        bbb.b().put("village", Maps.newHashMap());
        return bbb;
    }
}
