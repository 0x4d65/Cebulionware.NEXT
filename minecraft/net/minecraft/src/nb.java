package net.minecraft.src;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nullable;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.EntityList;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ReportedException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class nb
{
    private static final Logger a = LogManager.getLogger();
    private static final Map < Class <? extends EntityList > , Integer > b = Maps. < Class <? extends EntityList > , Integer > newHashMap();
    private final EntityList c;
    private final Map < Integer, nb.a<? >> d = Maps. < Integer, nb.a<? >> newHashMap();
    private final ReadWriteLock e = new ReentrantReadWriteLock();
    private boolean f = true;
    private boolean g;

    public nb(EntityList p_i913_1_)
    {
        this.c = p_i913_1_;
    }

    public static <T> DataSerializer<T> a(Class <? extends EntityList > p_a_0_, DataSerializers<T> p_a_1_)
    {
        if (a.isDebugEnabled())
        {
            try
            {
                Class<?> oclass = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName());

                if (!oclass.equals(p_a_0_))
                {
                    a.debug("defineId called for: {} from {}", p_a_0_, oclass, new RuntimeException());
                }
            }
            catch (ClassNotFoundException var5)
            {
                ;
            }
        }

        int j;

        if (b.containsKey(p_a_0_))
        {
            j = ((Integer)b.get(p_a_0_)).intValue() + 1;
        }
        else
        {
            int i = 0;
            Class<?> oclass1 = p_a_0_;

            while (oclass1 != EntityList.class)
            {
                oclass1 = oclass1.getSuperclass();

                if (b.containsKey(oclass1))
                {
                    i = ((Integer)b.get(oclass1)).intValue() + 1;
                    break;
                }
            }

            j = i;
        }

        if (j > 254)
        {
            throw new IllegalArgumentException("Data value id is too big with " + j + "! (Max is " + 254 + ")");
        }
        else
        {
            b.put(p_a_0_, Integer.valueOf(j));
            return p_a_1_.getSerializer(j);
        }
    }

    public <T> void a(DataSerializer<T> p_a_1_, T p_a_2_)
    {
        int i = p_a_1_.a();

        if (i > 254)
        {
            throw new IllegalArgumentException("Data value id is too big with " + i + "! (Max is " + 254 + ")");
        }
        else if (this.d.containsKey(Integer.valueOf(i)))
        {
            throw new IllegalArgumentException("Duplicate id value for " + i + "!");
        }
        else if (EntityDataManager.b(p_a_1_.b()) < 0)
        {
            throw new IllegalArgumentException("Unregistered serializer " + p_a_1_.b() + " for " + i + "!");
        }
        else
        {
            this.c(p_a_1_, p_a_2_);
        }
    }

    private <T> void c(DataSerializer<T> p_c_1_, T p_c_2_)
    {
        nb.a<T> a = new nb.a<T>(p_c_1_, p_c_2_);
        this.e.writeLock().lock();
        this.d.put(Integer.valueOf(p_c_1_.a()), a);
        this.f = false;
        this.e.writeLock().unlock();
    }

    private <T> nb.a<T> c(DataSerializer<T> p_c_1_)
    {
        this.e.readLock().lock();
        nb.a<T> a;

        try
        {
            a = (nb.a)this.d.get(Integer.valueOf(p_c_1_.a()));
        }
        catch (Throwable throwable)
        {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Getting synched entity data");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("Synched entity data");
            crashreportcategory.addCrashSection("Data ID", p_c_1_);
            throw new ReportedException(crashreport);
        }

        this.e.readLock().unlock();
        return a;
    }

    public <T> T a(DataSerializer<T> p_a_1_)
    {
        return (T)this.c(p_a_1_).b();
    }

    public <T> void b(DataSerializer<T> p_b_1_, T p_b_2_)
    {
        nb.a<T> a = this.<T>c(p_b_1_);

        if (ObjectUtils.notEqual(p_b_2_, a.b()))
        {
            a.a(p_b_2_);
            this.c.a(p_b_1_);
            a.a(true);
            this.g = true;
        }
    }

    public <T> void b(DataSerializer<T> p_b_1_)
    {
        this.c(p_b_1_).c = true;
        this.g = true;
    }

    public boolean a()
    {
        return this.g;
    }

    public static void a(List < nb.a<? >> p_a_0_, PacketBuffer p_a_1_) throws IOException
    {
        if (p_a_0_ != null)
        {
            int i = 0;

            for (int j = p_a_0_.size(); i < j; ++i)
            {
                nb.a<?> a = (nb.a)p_a_0_.get(i);
                a(p_a_1_, a);
            }
        }

        p_a_1_.writeByte(255);
    }

    @Nullable
    public List < nb.a<? >> b()
    {
        List < nb.a<? >> list = null;

        if (this.g)
        {
            this.e.readLock().lock();

            for (nb.a<?> a : this.d.values())
            {
                if (a.c())
                {
                    a.a(false);

                    if (list == null)
                    {
                        list = Lists. < nb.a<? >> newArrayList();
                    }

                    list.add(a.d());
                }
            }

            this.e.readLock().unlock();
        }

        this.g = false;
        return list;
    }

    public void a(PacketBuffer p_a_1_) throws IOException
    {
        this.e.readLock().lock();

        for (nb.a<?> a : this.d.values())
        {
            a(p_a_1_, a);
        }

        this.e.readLock().unlock();
        p_a_1_.writeByte(255);
    }

    @Nullable
    public List < nb.a<? >> c()
    {
        List < nb.a<? >> list = null;
        this.e.readLock().lock();

        for (nb.a<?> a : this.d.values())
        {
            if (list == null)
            {
                list = Lists. < nb.a<? >> newArrayList();
            }

            list.add(a.d());
        }

        this.e.readLock().unlock();
        return list;
    }

    private static <T> void a(PacketBuffer p_a_0_, nb.a<T> p_a_1_) throws IOException
    {
        DataSerializer<T> dataserializer = p_a_1_.a();
        int i = EntityDataManager.b(dataserializer.b());

        if (i < 0)
        {
            throw new EncoderException("Unknown serializer type " + dataserializer.b());
        }
        else
        {
            p_a_0_.writeByte(dataserializer.a());
            p_a_0_.writeVarIntToBuffer(i);
            dataserializer.b().a(p_a_0_, p_a_1_.b());
        }
    }

    @Nullable
    public static List < nb.a<? >> b(PacketBuffer p_b_0_) throws IOException
    {
        List < nb.a<? >> list = null;
        int i;

        while ((i = p_b_0_.readUnsignedByte()) != 255)
        {
            if (list == null)
            {
                list = Lists. < nb.a<? >> newArrayList();
            }

            int j = p_b_0_.readVarIntFromBuffer();
            DataSerializers<?> dataserializers = EntityDataManager.a(j);

            if (dataserializers == null)
            {
                throw new DecoderException("Unknown serializer type " + j);
            }

            list.add(new nb.a(dataserializers.getSerializer(i), dataserializers.a(p_b_0_)));
        }

        return list;
    }

    public void a(List < nb.a<? >> p_a_1_)
    {
        this.e.writeLock().lock();

        for (nb.a<?> a : p_a_1_)
        {
            nb.a<?> a = (nb.a)this.d.get(Integer.valueOf(a.a().a()));

            if (a != null)
            {
                this.a(a, a);
                this.c.a(a.a());
            }
        }

        this.e.writeLock().unlock();
        this.g = true;
    }

    protected <T> void a(nb.a<T> p_a_1_, nb.a<?> p_a_2_)
    {
        p_a_1_.a(p_a_2_.b());
    }

    public boolean d()
    {
        return this.f;
    }

    public void e()
    {
        this.g = false;
        this.e.readLock().lock();

        for (nb.a<?> a : this.d.values())
        {
            a.a(false);
        }

        this.e.readLock().unlock();
    }

    public static class a<T>
    {
        private final DataSerializer<T> a;
        private T b;
        private boolean c;

        public a(DataSerializer<T> p_i912_1_, T p_i912_2_)
        {
            this.a = p_i912_1_;
            this.b = p_i912_2_;
            this.c = true;
        }

        public DataSerializer<T> a()
        {
            return this.a;
        }

        public void a(T p_a_1_)
        {
            this.b = p_a_1_;
        }

        public T b()
        {
            return this.b;
        }

        public boolean c()
        {
            return this.c;
        }

        public void a(boolean p_a_1_)
        {
            this.c = p_a_1_;
        }

        public nb.a<T> d()
        {
            return new nb.a<T>(this.a, this.a.b().a(this.b));
        }
    }
}
