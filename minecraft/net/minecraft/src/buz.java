package net.minecraft.src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.ImageObserver;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.ItemModelMesher;

public class buz implements ItemModelMesher
{
    private int[] a;
    private int b;
    private int c;

    @Nullable
    public BufferedImage a(BufferedImage p_a_1_)
    {
        if (p_a_1_ == null)
        {
            return null;
        }
        else
        {
            this.b = 64;
            this.c = 64;
            BufferedImage bufferedimage = new BufferedImage(this.b, this.c, 2);
            Graphics graphics = bufferedimage.getGraphics();
            graphics.drawImage(p_a_1_, 0, 0, (ImageObserver)null);
            boolean flag = p_a_1_.getHeight() == 32;

            if (flag)
            {
                graphics.setColor(new Color(0, 0, 0, 0));
                graphics.fillRect(0, 32, 64, 32);
                graphics.drawImage(bufferedimage, 24, 48, 20, 52, 4, 16, 8, 20, (ImageObserver)null);
                graphics.drawImage(bufferedimage, 28, 48, 24, 52, 8, 16, 12, 20, (ImageObserver)null);
                graphics.drawImage(bufferedimage, 20, 52, 16, 64, 8, 20, 12, 32, (ImageObserver)null);
                graphics.drawImage(bufferedimage, 24, 52, 20, 64, 4, 20, 8, 32, (ImageObserver)null);
                graphics.drawImage(bufferedimage, 28, 52, 24, 64, 0, 20, 4, 32, (ImageObserver)null);
                graphics.drawImage(bufferedimage, 32, 52, 28, 64, 12, 20, 16, 32, (ImageObserver)null);
                graphics.drawImage(bufferedimage, 40, 48, 36, 52, 44, 16, 48, 20, (ImageObserver)null);
                graphics.drawImage(bufferedimage, 44, 48, 40, 52, 48, 16, 52, 20, (ImageObserver)null);
                graphics.drawImage(bufferedimage, 36, 52, 32, 64, 48, 20, 52, 32, (ImageObserver)null);
                graphics.drawImage(bufferedimage, 40, 52, 36, 64, 44, 20, 48, 32, (ImageObserver)null);
                graphics.drawImage(bufferedimage, 44, 52, 40, 64, 40, 20, 44, 32, (ImageObserver)null);
                graphics.drawImage(bufferedimage, 48, 52, 44, 64, 52, 20, 56, 32, (ImageObserver)null);
            }

            graphics.dispose();
            this.a = ((DataBufferInt)bufferedimage.getRaster().getDataBuffer()).getData();
            this.b(0, 0, 32, 16);

            if (flag)
            {
                this.a(32, 0, 64, 32);
            }

            this.b(0, 16, 64, 32);
            this.b(16, 48, 48, 64);
            return bufferedimage;
        }
    }

    public void a()
    {
    }

    private void a(int p_a_1_, int p_a_2_, int p_a_3_, int p_a_4_)
    {
        for (int i = p_a_1_; i < p_a_3_; ++i)
        {
            for (int j = p_a_2_; j < p_a_4_; ++j)
            {
                int k = this.a[i + j * this.b];

                if ((k >> 24 & 255) < 128)
                {
                    return;
                }
            }
        }

        for (int l = p_a_1_; l < p_a_3_; ++l)
        {
            for (int i1 = p_a_2_; i1 < p_a_4_; ++i1)
            {
                this.a[l + i1 * this.b] &= 16777215;
            }
        }
    }

    private void b(int p_b_1_, int p_b_2_, int p_b_3_, int p_b_4_)
    {
        for (int i = p_b_1_; i < p_b_3_; ++i)
        {
            for (int j = p_b_2_; j < p_b_4_; ++j)
            {
                this.a[i + j * this.b] |= -16777216;
            }
        }
    }
}
