package net.minecraft.src;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Set;

public enum qg
{
    a("master"),
    b("music"),
    c("record"),
    d("weather"),
    e("block"),
    f("hostile"),
    g("neutral"),
    h("player"),
    i("ambient"),
    j("voice");

    private static final Map<String, qg> k = Maps.<String, qg>newHashMap();
    private final String l;

    private qg(String p_i998_3_)
    {
        this.l = p_i998_3_;
    }

    public String a()
    {
        return this.l;
    }

    public static qg a(String p_a_0_)
    {
        return k.get(p_a_0_);
    }

    public static Set<String> b()
    {
        return k.keySet();
    }

    static {
        for (qg qg : values())
        {
            if (k.containsKey(qg.a()))
            {
                throw new Error("Clash in Sound Category name pools! Cannot insert " + qg);
            }

            k.put(qg.a(), qg);
        }
    }
}
