package net.minecraft.src;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.resources.LegacyV2Adapter;
import net.minecraft.client.resources.Locale;
import net.minecraft.client.resources.ResourcePackFileNotFoundException;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.client.resources.data.BaseMetadataSectionSerializer;
import net.minecraft.util.text.translation.LanguageMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class cfa implements LegacyV2Adapter
{
    private static final Logger b = LogManager.getLogger();
    private final AnimationMetadataSection c;
    private String d;
    protected static final BaseMetadataSectionSerializer a = new BaseMetadataSectionSerializer();
    private final Map<String, Locale> e = Maps.<String, Locale>newHashMap();

    public cfa(AnimationMetadataSection p_i294_1_, String p_i294_2_)
    {
        this.c = p_i294_1_;
        this.d = p_i294_2_;
        LanguageManager.a(a);
    }

    public void a(List<ResourcePackFileNotFoundException> p_a_1_)
    {
        this.e.clear();

        for (ResourcePackFileNotFoundException resourcepackfilenotfoundexception : p_a_1_)
        {
            try
            {
                cfo cfo = (cfo)resourcepackfilenotfoundexception.a(this.c, "language");

                if (cfo != null)
                {
                    for (Locale locale : cfo.a())
                    {
                        if (!this.e.containsKey(locale.a()))
                        {
                            this.e.put(locale.a(), locale);
                        }
                    }
                }
            }
            catch (RuntimeException runtimeexception)
            {
                b.warn("Unable to parse language metadata section of resourcepack: {}", resourcepackfilenotfoundexception.b(), runtimeexception);
            }
            catch (IOException ioexception)
            {
                b.warn("Unable to parse language metadata section of resourcepack: {}", resourcepackfilenotfoundexception.b(), ioexception);
            }
        }
    }

    public void a(IResourcePack p_a_1_)
    {
        List<String> list = Lists.newArrayList("en_us");

        if (!"en_us".equals(this.d))
        {
            list.add(this.d);
        }

        a.a(p_a_1_, list);
        LanguageMap.replaceWith(a.a);
    }

    public boolean a()
    {
        return a.a();
    }

    public boolean b()
    {
        return this.c() != null && this.c().b();
    }

    public void a(Locale p_a_1_)
    {
        this.d = p_a_1_.a();
    }

    public Locale c()
    {
        String s = this.e.containsKey(this.d) ? this.d : "en_us";
        return this.e.get(s);
    }

    public SortedSet<Locale> d()
    {
        return Sets.newTreeSet(this.e.values());
    }

    public Locale a(String p_a_1_)
    {
        return this.e.get(p_a_1_);
    }
}
