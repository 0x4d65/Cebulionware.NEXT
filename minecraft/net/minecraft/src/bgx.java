package net.minecraft.src;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;

public class bgx implements Runnable
{
    private static final bgx a = new bgx();
    private final List<bgy> b = Collections.<bgy>synchronizedList(Lists.newArrayList());
    private volatile long c;
    private volatile long d;
    private volatile boolean e;

    private bgx()
    {
        Thread thread = new Thread(this, "File IO Thread");
        thread.setPriority(1);
        thread.start();
    }

    public static bgx a()
    {
        return a;
    }

    public void run()
    {
        while (true)
        {
            this.c();
        }
    }

    private void c()
    {
        for (int i = 0; i < this.b.size(); ++i)
        {
            bgy bgy = this.b.get(i);
            boolean flag = bgy.a();

            if (!flag)
            {
                this.b.remove(i--);
                ++this.d;
            }

            try
            {
                Thread.sleep(this.e ? 0L : 10L);
            }
            catch (InterruptedException interruptedexception1)
            {
                interruptedexception1.printStackTrace();
            }
        }

        if (this.b.isEmpty())
        {
            try
            {
                Thread.sleep(25L);
            }
            catch (InterruptedException interruptedexception)
            {
                interruptedexception.printStackTrace();
            }
        }
    }

    public void a(bgy p_a_1_)
    {
        if (!this.b.contains(p_a_1_))
        {
            ++this.c;
            this.b.add(p_a_1_);
        }
    }

    public void b() throws InterruptedException
    {
        this.e = true;

        while (this.c != this.d)
        {
            Thread.sleep(10L);
        }

        this.e = false;
    }
}
