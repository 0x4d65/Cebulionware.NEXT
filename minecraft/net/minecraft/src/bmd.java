package net.minecraft.src;

import java.util.Arrays;
import net.minecraft.client.gui.GuiOptionsRowList;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ScreenShotHelper;
import net.minecraft.util.text.TextFormatting;
import org.apache.commons.lang3.ArrayUtils;

public class bmd extends GuiOptionsRowList
{
    private final GuiContainer u;
    private final GameSettings v;
    private final GuiOptionsRowList.Row[] w;
    private int x;

    public bmd(GuiContainer p_i131_1_, GameSettings p_i131_2_)
    {
        super(p_i131_2_, p_i131_1_.l + 45, p_i131_1_.m, 63, p_i131_1_.m - 32, 20);
        this.u = p_i131_1_;
        this.v = p_i131_2_;
        GLAllocation[] aglallocation = (GLAllocation[])ArrayUtils.clone(p_i131_2_.snooperEnabled.as);
        this.w = new GuiOptionsRowList.Row[aglallocation.length + GLAllocation.d().size()];
        Arrays.sort((Object[])aglallocation);
        int i = 0;
        String s = null;

        for (GLAllocation glallocation : aglallocation)
        {
            String s1 = glallocation.f();

            if (!s1.equals(s))
            {
                s = s1;
                this.w[i++] = new bmd.a(s1);
            }

            int j = p_i131_2_.fancyGraphics.a(LanguageManager.a(glallocation.h()));

            if (j > this.x)
            {
                this.x = j;
            }

            this.w[i++] = new bmd.b(glallocation);
        }
    }

    protected int getSize()
    {
        return this.w.length;
    }

    public GuiOptionsRowList.Row b(int p_b_1_)
    {
        return this.w[p_b_1_];
    }

    protected int getScrollBarX()
    {
        return super.getScrollBarX() + 15;
    }

    /**
     * Gets the width of the list
     */
    public int getListWidth()
    {
        return super.getListWidth() + 32;
    }

    public class a implements GuiOptionsRowList.Row
    {
        private final String b;
        private final int c;

        public a(String p_i129_2_)
        {
            this.b = LanguageManager.a(p_i129_2_);
            this.c = bmd.this.v.fancyGraphics.a(this.b);
        }

        public void func_192634_a(int p_192634_1_, int p_192634_2_, int p_192634_3_, int p_192634_4_, int p_192634_5_, int p_192634_6_, int p_192634_7_, boolean p_192634_8_, float p_192634_9_)
        {
            bmd.this.v.fancyGraphics.a(this.b, bmd.this.v.resourcePacks.l / 2 - this.c / 2, p_192634_3_ + p_192634_5_ - bmd.this.v.fancyGraphics.field_191383_a - 1, 16777215);
        }

        public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY)
        {
            return false;
        }

        public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY)
        {
        }

        public void func_192633_a(int p_192633_1_, int p_192633_2_, int p_192633_3_, float p_192633_4_)
        {
        }
    }

    public class b implements GuiOptionsRowList.Row
    {
        private final GLAllocation b;
        private final String c;
        private final GuiUtilRenderComponents d;
        private final GuiUtilRenderComponents e;

        private b(GLAllocation p_i132_2_)
        {
            this.b = p_i132_2_;
            this.c = LanguageManager.a(p_i132_2_.h());
            this.d = new GuiUtilRenderComponents(0, 0, 0, 75, 20, LanguageManager.a(p_i132_2_.h()));
            this.e = new GuiUtilRenderComponents(0, 0, 0, 50, 20, LanguageManager.a("controls.reset"));
        }

        public void func_192634_a(int p_192634_1_, int p_192634_2_, int p_192634_3_, int p_192634_4_, int p_192634_5_, int p_192634_6_, int p_192634_7_, boolean p_192634_8_, float p_192634_9_)
        {
            boolean flag = bmd.this.u.xSize == this.b;
            bmd.this.v.fancyGraphics.a(this.c, p_192634_2_ + 90 - bmd.this.x, p_192634_3_ + p_192634_5_ / 2 - bmd.this.v.fancyGraphics.field_191383_a / 2, 16777215);
            this.e.h = p_192634_2_ + 190;
            this.e.i = p_192634_3_;
            this.e.l = this.b.j() != this.b.i();
            this.e.a(bmd.this.v, p_192634_6_, p_192634_7_, p_192634_9_);
            this.d.h = p_192634_2_ + 105;
            this.d.i = p_192634_3_;
            this.d.j = ScreenShotHelper.a(this.b.j());
            boolean flag1 = false;

            if (this.b.j() != 0)
            {
                for (GLAllocation glallocation : bmd.this.v.snooperEnabled.as)
                {
                    if (glallocation != this.b && glallocation.j() == this.b.j())
                    {
                        flag1 = true;
                        break;
                    }
                }
            }

            if (flag)
            {
                this.d.j = TextFormatting.WHITE + "> " + TextFormatting.YELLOW + this.d.j + TextFormatting.WHITE + " <";
            }
            else if (flag1)
            {
                this.d.j = TextFormatting.RED + this.d.j;
            }

            this.d.a(bmd.this.v, p_192634_6_, p_192634_7_, p_192634_9_);
        }

        public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY)
        {
            if (this.d.b(bmd.this.v, mouseX, mouseY))
            {
                bmd.this.u.xSize = this.b;
                return true;
            }
            else if (this.e.b(bmd.this.v, mouseX, mouseY))
            {
                bmd.this.v.snooperEnabled.a(this.b, this.b.i());
                GLAllocation.c();
                return true;
            }
            else
            {
                return false;
            }
        }

        public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY)
        {
            this.d.a(x, y);
            this.e.a(x, y);
        }

        public void func_192633_a(int p_192633_1_, int p_192633_2_, int p_192633_3_, float p_192633_4_)
        {
        }
    }
}
