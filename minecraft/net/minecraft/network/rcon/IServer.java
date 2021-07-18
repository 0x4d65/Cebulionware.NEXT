package net.minecraft.network.rcon;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementList;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementTreeNode;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.LowerStringMap;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IServer
{
    private static final Logger a = LogManager.getLogger();
    private static final Gson b = (new GsonBuilder()).registerTypeHierarchyAdapter(Advancement.Builder.class, new JsonDeserializer<Advancement.Builder>()
    {
        public Advancement.Builder a(JsonElement p_a_1_, Type p_a_2_, JsonDeserializationContext p_a_3_) throws JsonParseException
        {
            JsonObject jsonobject = LowerStringMap.m(p_a_1_, "advancement");
            return Advancement.Builder.func_192059_a(jsonobject, p_a_3_);
        }
    }).registerTypeAdapter(AdvancementRewards.class, new AdvancementRewards.Deserializer()).registerTypeHierarchyAdapter(ITextComponent.class, new ITextComponent.Serializer()).registerTypeHierarchyAdapter(Style.class, new Style.Serializer()).registerTypeAdapterFactory(new Profiler()).create();
    private static final AdvancementList c = new AdvancementList();
    private final File d;
    private boolean e;

    public IServer(@Nullable File p_i927_1_)
    {
        this.d = p_i927_1_;
        this.func_71326_a();
    }

    public void func_71326_a()
    {
        this.e = false;
        c.func_192087_a();
        Map<BehaviorProjectileDispense, Advancement.Builder> map = this.d();
        this.a(map);
        c.func_192083_a(map);

        for (Advancement advancement : c.func_192088_b())
        {
            if (advancement.func_192068_c() != null)
            {
                AdvancementTreeNode.func_192323_a(advancement);
            }
        }
    }

    public boolean b()
    {
        return this.e;
    }

    private Map<BehaviorProjectileDispense, Advancement.Builder> d()
    {
        if (this.d == null)
        {
            return Maps.<BehaviorProjectileDispense, Advancement.Builder>newHashMap();
        }
        else
        {
            Map<BehaviorProjectileDispense, Advancement.Builder> map = Maps.<BehaviorProjectileDispense, Advancement.Builder>newHashMap();
            this.d.mkdirs();

            for (File file1 : FileUtils.listFiles(this.d, new String[] {"json"}, true))
            {
                String s = FilenameUtils.removeExtension(this.d.toURI().relativize(file1.toURI()).toString());
                String[] astring = s.split("/", 2);

                if (astring.length == 2)
                {
                    BehaviorProjectileDispense behaviorprojectiledispense = new BehaviorProjectileDispense(astring[0], astring[1]);

                    try
                    {
                        Advancement.Builder advancement$builder = (Advancement.Builder)LowerStringMap.a(b, FileUtils.readFileToString(file1, StandardCharsets.UTF_8), Advancement.Builder.class);

                        if (advancement$builder == null)
                        {
                            a.error("Couldn't load custom advancement " + behaviorprojectiledispense + " from " + file1 + " as it's empty or null");
                        }
                        else
                        {
                            map.put(behaviorprojectiledispense, advancement$builder);
                        }
                    }
                    catch (IllegalArgumentException | JsonParseException jsonparseexception)
                    {
                        a.error("Parsing error loading custom advancement " + behaviorprojectiledispense, (Throwable)jsonparseexception);
                        this.e = true;
                    }
                    catch (IOException ioexception)
                    {
                        a.error("Couldn't read custom advancement " + behaviorprojectiledispense + " from " + file1, (Throwable)ioexception);
                        this.e = true;
                    }
                }
            }
            return map;
        }
    }

    private void a(Map<BehaviorProjectileDispense, Advancement.Builder> p_a_1_)
    {
        FileSystem filesystem = null;

        try
        {
            URL url = IServer.class.getResource("/assets/.mcassetsroot");

            if (url != null)
            {
                URI uri = url.toURI();
                Path path;

                if ("file".equals(uri.getScheme()))
                {
                    path = Paths.get(ShapedRecipes.class.getResource("/assets/minecraft/advancements").toURI());
                }
                else
                {
                    if (!"jar".equals(uri.getScheme()))
                    {
                        a.error("Unsupported scheme " + uri + " trying to list all built-in advancements (NYI?)");
                        this.e = true;
                        return;
                    }

                    filesystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
                    path = filesystem.getPath("/assets/minecraft/advancements");
                }

                Iterator<Path> iterator = Files.walk(path).iterator();

                while (iterator.hasNext())
                {
                    Path path1 = iterator.next();

                    if ("json".equals(FilenameUtils.getExtension(path1.toString())))
                    {
                        Path path2 = path.relativize(path1);
                        String s = FilenameUtils.removeExtension(path2.toString()).replaceAll("\\\\", "/");
                        BehaviorProjectileDispense behaviorprojectiledispense = new BehaviorProjectileDispense("minecraft", s);

                        if (!p_a_1_.containsKey(behaviorprojectiledispense))
                        {
                            BufferedReader bufferedreader = null;

                            try
                            {
                                bufferedreader = Files.newBufferedReader(path1);
                                Advancement.Builder advancement$builder = (Advancement.Builder)LowerStringMap.a(b, bufferedreader, Advancement.Builder.class);
                                p_a_1_.put(behaviorprojectiledispense, advancement$builder);
                            }
                            catch (JsonParseException jsonparseexception)
                            {
                                a.error("Parsing error loading built-in advancement " + behaviorprojectiledispense, (Throwable)jsonparseexception);
                                this.e = true;
                            }
                            catch (IOException ioexception)
                            {
                                a.error("Couldn't read advancement " + behaviorprojectiledispense + " from " + path1, (Throwable)ioexception);
                                this.e = true;
                            }
                            finally
                            {
                                IOUtils.closeQuietly((Reader)bufferedreader);
                            }
                        }
                    }
                }

                return;
            }

            a.error("Couldn't find .mcassetsroot");
            this.e = true;
        }
        catch (IOException | URISyntaxException urisyntaxexception)
        {
            a.error("Couldn't get a list of all built-in advancement files", (Throwable)urisyntaxexception);
            this.e = true;
            return;
        }
        finally
        {
            IOUtils.closeQuietly((Closeable)filesystem);
        }
    }

    @Nullable
    public Advancement a(BehaviorProjectileDispense p_a_1_)
    {
        return c.a(p_a_1_);
    }

    public Iterable<Advancement> c()
    {
        return c.func_192089_c();
    }
}
