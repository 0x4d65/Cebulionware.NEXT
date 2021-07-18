package net.minecraft.src;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.minecraft.block.BlockBone;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundList;
import net.minecraft.client.gui.GuiButtonToggle;
import net.minecraft.client.gui.GuiCustomizeSkin;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.gui.advancements.GuiAdvancementTab;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.client.renderer.VertexBufferUploader;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketClientStatus;
import net.minecraft.stats.RecipeBookServer;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.TupleIntJsonSerializable;
import org.lwjgl.input.Mouse;

public class blu extends GuiCustomizeSkin implements GuiAdvancementTab
{
    protected GuiCustomizeSkin field_191802_a;
    protected String field_193938_f = "Select world";
    private blu.b g;
    private blu.c h;
    private blu.a i;
    private blu.d s;
    private final qt t;
    private GuiButtonToggle u;
    private boolean v = true;

    public blu(GuiCustomizeSkin p_i150_1_, qt p_i150_2_)
    {
        this.field_191802_a = p_i150_1_;
        this.t = p_i150_2_;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        this.field_193938_f = LanguageManager.a("gui.stats");
        this.v = true;
        this.field_191806_i.v().sendPacketToServer(new CPacketClientStatus(CPacketClientStatus.State.REQUEST_STATS));
    }

    public void k()
    {
        super.k();

        if (this.u != null)
        {
            this.u.p();
        }
    }

    public void a()
    {
        this.g = new blu.b(this.field_191806_i);
        this.g.d(1, 1);
        this.h = new blu.c(this.field_191806_i);
        this.h.d(1, 1);
        this.i = new blu.a(this.field_191806_i);
        this.i.d(1, 1);
        this.s = new blu.d(this.field_191806_i);
        this.s.d(1, 1);
    }

    public void func_191799_a()
    {
        this.field_191810_m.add(new GuiUtilRenderComponents(0, this.field_191808_k / 2 + 4, this.field_191809_l - 28, 150, 20, LanguageManager.a("gui.done")));
        this.field_191810_m.add(new GuiUtilRenderComponents(1, this.field_191808_k / 2 - 160, this.field_191809_l - 52, 80, 20, LanguageManager.a("stat.generalButton")));
        GuiUtilRenderComponents guiutilrendercomponents = this.b(new GuiUtilRenderComponents(2, this.field_191808_k / 2 - 80, this.field_191809_l - 52, 80, 20, LanguageManager.a("stat.blocksButton")));
        GuiUtilRenderComponents guiutilrendercomponents1 = this.b(new GuiUtilRenderComponents(3, this.field_191808_k / 2, this.field_191809_l - 52, 80, 20, LanguageManager.a("stat.itemsButton")));
        GuiUtilRenderComponents guiutilrendercomponents2 = this.b(new GuiUtilRenderComponents(4, this.field_191808_k / 2 + 80, this.field_191809_l - 52, 80, 20, LanguageManager.a("stat.mobsButton")));

        if (this.i.b() == 0)
        {
            guiutilrendercomponents.l = false;
        }

        if (this.h.b() == 0)
        {
            guiutilrendercomponents1.l = false;
        }

        if (this.s.b() == 0)
        {
            guiutilrendercomponents2.l = false;
        }
    }

    protected void a(GuiUtilRenderComponents p_a_1_)
    {
        if (p_a_1_.l)
        {
            if (p_a_1_.k == 0)
            {
                this.field_191806_i.a(this.field_191802_a);
            }
            else if (p_a_1_.k == 1)
            {
                this.u = this.g;
            }
            else if (p_a_1_.k == 3)
            {
                this.u = this.h;
            }
            else if (p_a_1_.k == 2)
            {
                this.u = this.i;
            }
            else if (p_a_1_.k == 4)
            {
                this.u = this.s;
            }
            else
            {
                this.u.a(p_a_1_);
            }
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        if (this.v)
        {
            this.c();
            this.a(this.field_193939_q, LanguageManager.a("multiplayer.downloadingStats"), this.field_191808_k / 2, this.field_191809_l / 2, 16777215);
            this.a(this.field_193939_q, c_[(int)(GameSettings.I() / 150L % (long)c_.length)], this.field_191808_k / 2, this.field_191809_l / 2 + this.field_193939_q.field_191383_a * 2, 16777215);
        }
        else
        {
            this.u.a(mouseX, mouseY, partialTicks);
            this.a(this.field_193939_q, this.field_193938_f, this.field_191808_k / 2, 20, 16777215);
            super.drawScreen(mouseX, mouseY, partialTicks);
        }
    }

    public void g()
    {
        if (this.v)
        {
            this.a();
            this.func_191799_a();
            this.u = this.g;
            this.v = false;
        }
    }

    public boolean d()
    {
        return !this.v;
    }

    private void a(int p_a_1_, int p_a_2_, ItemStack p_a_3_)
    {
        this.b(p_a_1_ + 1, p_a_2_ + 1);
        ItemRenderer.D();
        Minecraft.c();
        this.field_191807_j.a(p_a_3_.u(), p_a_1_ + 2, p_a_2_ + 2);
        Minecraft.run();
        ItemRenderer.E();
    }

    private void b(int p_b_1_, int p_b_2_)
    {
        this.c(p_b_1_, p_b_2_, 0, 0);
    }

    private void c(int p_c_1_, int p_c_2_, int p_c_3_, int p_c_4_)
    {
        ItemRenderer.c(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_191806_i.N().a(scaledWidth);
        float f = 0.0078125F;
        float f1 = 0.0078125F;
        int i = 18;
        int j = 18;
        VertexBufferUploader vertexbufferuploader = VertexBufferUploader.a();
        RegionRenderCacheBuilder regionrendercachebuilder = vertexbufferuploader.c();
        regionrendercachebuilder.a(7, VertexFormat.normalElementOffset);
        regionrendercachebuilder.b((double)(p_c_1_ + 0), (double)(p_c_2_ + 18), (double)this.scaleFactor).a((double)((float)(p_c_3_ + 0) * 0.0078125F), (double)((float)(p_c_4_ + 18) * 0.0078125F)).d();
        regionrendercachebuilder.b((double)(p_c_1_ + 18), (double)(p_c_2_ + 18), (double)this.scaleFactor).a((double)((float)(p_c_3_ + 18) * 0.0078125F), (double)((float)(p_c_4_ + 18) * 0.0078125F)).d();
        regionrendercachebuilder.b((double)(p_c_1_ + 18), (double)(p_c_2_ + 0), (double)this.scaleFactor).a((double)((float)(p_c_3_ + 18) * 0.0078125F), (double)((float)(p_c_4_ + 0) * 0.0078125F)).d();
        regionrendercachebuilder.b((double)(p_c_1_ + 0), (double)(p_c_2_ + 0), (double)this.scaleFactor).a((double)((float)(p_c_3_ + 0) * 0.0078125F), (double)((float)(p_c_4_ + 0) * 0.0078125F)).d();
        vertexbufferuploader.b();
    }

    class a extends blu.e
    {
        public a(GameSettings p_i145_2_)
        {
            super(p_i145_2_);
            this.w = Lists.<RecipeBookServer>newArrayList();

            for (RecipeBookServer recipebookserver : qs.e)
            {
                boolean flag = false;
                ItemStack itemstack = recipebookserver.b();

                if (blu.this.t.a(recipebookserver) > 0)
                {
                    flag = true;
                }
                else if (qs.b(itemstack) != null && blu.this.t.a(qs.b(itemstack)) > 0)
                {
                    flag = true;
                }
                else if (qs.a(itemstack) != null && blu.this.t.a(qs.a(itemstack)) > 0)
                {
                    flag = true;
                }
                else if (qs.d(itemstack) != null && blu.this.t.a(qs.d(itemstack)) > 0)
                {
                    flag = true;
                }
                else if (qs.e(itemstack) != null && blu.this.t.a(qs.e(itemstack)) > 0)
                {
                    flag = true;
                }

                if (flag)
                {
                    this.w.add(recipebookserver);
                }
            }

            this.x = new Comparator<RecipeBookServer>()
            {
                public int a(RecipeBookServer p_a_1_, RecipeBookServer p_a_2_)
                {
                    ItemStack itemstack1 = p_a_1_.b();
                    ItemStack itemstack2 = p_a_2_.b();
                    TupleIntJsonSerializable tupleintjsonserializable = null;
                    TupleIntJsonSerializable tupleintjsonserializable1 = null;

                    if (a.this.y == 2)
                    {
                        tupleintjsonserializable = qs.a(BlockBone.a(itemstack1));
                        tupleintjsonserializable1 = qs.a(BlockBone.a(itemstack2));
                    }
                    else if (a.this.y == 0)
                    {
                        tupleintjsonserializable = qs.a(itemstack1);
                        tupleintjsonserializable1 = qs.a(itemstack2);
                    }
                    else if (a.this.y == 1)
                    {
                        tupleintjsonserializable = qs.b(itemstack1);
                        tupleintjsonserializable1 = qs.b(itemstack2);
                    }
                    else if (a.this.y == 3)
                    {
                        tupleintjsonserializable = qs.d(itemstack1);
                        tupleintjsonserializable1 = qs.d(itemstack2);
                    }
                    else if (a.this.y == 4)
                    {
                        tupleintjsonserializable = qs.e(itemstack1);
                        tupleintjsonserializable1 = qs.e(itemstack2);
                    }

                    if (tupleintjsonserializable != null || tupleintjsonserializable1 != null)
                    {
                        if (tupleintjsonserializable == null)
                        {
                            return 1;
                        }

                        if (tupleintjsonserializable1 == null)
                        {
                            return -1;
                        }

                        int i = blu.this.t.a(tupleintjsonserializable);
                        int j = blu.this.t.a(tupleintjsonserializable1);

                        if (i != j)
                        {
                            return (i - j) * a.this.z;
                        }
                    }

                    return ItemStack.a(itemstack1) - ItemStack.a(itemstack2);
                }
            };
        }

        protected void a(int p_a_1_, int p_a_2_, VertexBufferUploader p_a_3_)
        {
            super.a(p_a_1_, p_a_2_, p_a_3_);

            if (this.v == 0)
            {
                blu.this.c(p_a_1_ + 115 - 18 + 1, p_a_2_ + 1 + 1, 18, 18);
            }
            else
            {
                blu.this.c(p_a_1_ + 115 - 18, p_a_2_ + 1, 18, 18);
            }

            if (this.v == 1)
            {
                blu.this.c(p_a_1_ + 165 - 18 + 1, p_a_2_ + 1 + 1, 36, 18);
            }
            else
            {
                blu.this.c(p_a_1_ + 165 - 18, p_a_2_ + 1, 36, 18);
            }

            if (this.v == 2)
            {
                blu.this.c(p_a_1_ + 215 - 18 + 1, p_a_2_ + 1 + 1, 54, 18);
            }
            else
            {
                blu.this.c(p_a_1_ + 215 - 18, p_a_2_ + 1, 54, 18);
            }

            if (this.v == 3)
            {
                blu.this.c(p_a_1_ + 265 - 18 + 1, p_a_2_ + 1 + 1, 90, 18);
            }
            else
            {
                blu.this.c(p_a_1_ + 265 - 18, p_a_2_ + 1, 90, 18);
            }

            if (this.v == 4)
            {
                blu.this.c(p_a_1_ + 315 - 18 + 1, p_a_2_ + 1 + 1, 108, 18);
            }
            else
            {
                blu.this.c(p_a_1_ + 315 - 18, p_a_2_ + 1, 108, 18);
            }
        }

        protected void a(int p_a_1_, int p_a_2_, int p_a_3_, int p_a_4_, int p_a_5_, int p_a_6_, float p_a_7_)
        {
            RecipeBookServer recipebookserver = this.c(p_a_1_);
            ItemStack itemstack = recipebookserver.b();
            blu.this.a(p_a_2_ + 40, p_a_3_, itemstack);
            this.a(qs.a(itemstack), p_a_2_ + 115, p_a_3_, p_a_1_ % 2 == 0);
            this.a(qs.b(itemstack), p_a_2_ + 165, p_a_3_, p_a_1_ % 2 == 0);
            this.a(recipebookserver, p_a_2_ + 215, p_a_3_, p_a_1_ % 2 == 0);
            this.a(qs.d(itemstack), p_a_2_ + 265, p_a_3_, p_a_1_ % 2 == 0);
            this.a(qs.e(itemstack), p_a_2_ + 315, p_a_3_, p_a_1_ % 2 == 0);
        }

        protected String b(int p_b_1_)
        {
            if (p_b_1_ == 0)
            {
                return "stat.crafted";
            }
            else if (p_b_1_ == 1)
            {
                return "stat.used";
            }
            else if (p_b_1_ == 3)
            {
                return "stat.pickup";
            }
            else
            {
                return p_b_1_ == 4 ? "stat.dropped" : "stat.mined";
            }
        }
    }

    class b extends GuiButtonToggle
    {
        public b(GameSettings p_i151_2_)
        {
            super(p_i151_2_, blu.this.field_191808_k, blu.this.field_191809_l, 32, blu.this.field_191809_l - 64, 10);
            this.func_191753_b(false);
        }

        protected int b()
        {
            return qs.c.size();
        }

        protected void a(int p_a_1_, boolean p_a_2_, int p_a_3_, int p_a_4_)
        {
        }

        protected boolean a(int p_a_1_)
        {
            return false;
        }

        protected int k()
        {
            return this.b() * 10;
        }

        protected void a()
        {
            blu.this.c();
        }

        protected void a(int p_a_1_, int p_a_2_, int p_a_3_, int p_a_4_, int p_a_5_, int p_a_6_, float p_a_7_)
        {
            TupleIntJsonSerializable tupleintjsonserializable = qs.c.get(p_a_1_);
            blu.this.c(blu.this.field_193939_q, tupleintjsonserializable.d().getUnformattedText(), p_a_2_ + 2, p_a_3_ + 1, p_a_1_ % 2 == 0 ? 16777215 : 9474192);
            String s = tupleintjsonserializable.a(blu.this.t.a(tupleintjsonserializable));
            blu.this.c(blu.this.field_193939_q, s, p_a_2_ + 2 + 213 - blu.this.field_193939_q.a(s), p_a_3_ + 1, p_a_1_ % 2 == 0 ? 16777215 : 9474192);
        }
    }

    class c extends blu.e
    {
        public c(GameSettings p_i144_2_)
        {
            super(p_i144_2_);
            this.w = Lists.<RecipeBookServer>newArrayList();

            for (RecipeBookServer recipebookserver : qs.d)
            {
                boolean flag = false;
                ItemStack itemstack = recipebookserver.b();

                if (blu.this.t.a(recipebookserver) > 0)
                {
                    flag = true;
                }
                else if (qs.c(itemstack) != null && blu.this.t.a(qs.c(itemstack)) > 0)
                {
                    flag = true;
                }
                else if (qs.a(itemstack) != null && blu.this.t.a(qs.a(itemstack)) > 0)
                {
                    flag = true;
                }
                else if (qs.d(itemstack) != null && blu.this.t.a(qs.d(itemstack)) > 0)
                {
                    flag = true;
                }
                else if (qs.e(itemstack) != null && blu.this.t.a(qs.e(itemstack)) > 0)
                {
                    flag = true;
                }

                if (flag)
                {
                    this.w.add(recipebookserver);
                }
            }

            this.x = new Comparator<RecipeBookServer>()
            {
                public int a(RecipeBookServer p_a_1_, RecipeBookServer p_a_2_)
                {
                    ItemStack itemstack1 = p_a_1_.b();
                    ItemStack itemstack2 = p_a_2_.b();
                    int i = ItemStack.a(itemstack1);
                    int j = ItemStack.a(itemstack2);
                    TupleIntJsonSerializable tupleintjsonserializable = null;
                    TupleIntJsonSerializable tupleintjsonserializable1 = null;

                    if (c.this.y == 0)
                    {
                        tupleintjsonserializable = qs.c(itemstack1);
                        tupleintjsonserializable1 = qs.c(itemstack2);
                    }
                    else if (c.this.y == 1)
                    {
                        tupleintjsonserializable = qs.a(itemstack1);
                        tupleintjsonserializable1 = qs.a(itemstack2);
                    }
                    else if (c.this.y == 2)
                    {
                        tupleintjsonserializable = qs.b(itemstack1);
                        tupleintjsonserializable1 = qs.b(itemstack2);
                    }
                    else if (c.this.y == 3)
                    {
                        tupleintjsonserializable = qs.d(itemstack1);
                        tupleintjsonserializable1 = qs.d(itemstack2);
                    }
                    else if (c.this.y == 4)
                    {
                        tupleintjsonserializable = qs.e(itemstack1);
                        tupleintjsonserializable1 = qs.e(itemstack2);
                    }

                    if (tupleintjsonserializable != null || tupleintjsonserializable1 != null)
                    {
                        if (tupleintjsonserializable == null)
                        {
                            return 1;
                        }

                        if (tupleintjsonserializable1 == null)
                        {
                            return -1;
                        }

                        int k = blu.this.t.a(tupleintjsonserializable);
                        int l = blu.this.t.a(tupleintjsonserializable1);

                        if (k != l)
                        {
                            return (k - l) * c.this.z;
                        }
                    }

                    return i - j;
                }
            };
        }

        protected void a(int p_a_1_, int p_a_2_, VertexBufferUploader p_a_3_)
        {
            super.a(p_a_1_, p_a_2_, p_a_3_);

            if (this.v == 0)
            {
                blu.this.c(p_a_1_ + 115 - 18 + 1, p_a_2_ + 1 + 1, 72, 18);
            }
            else
            {
                blu.this.c(p_a_1_ + 115 - 18, p_a_2_ + 1, 72, 18);
            }

            if (this.v == 1)
            {
                blu.this.c(p_a_1_ + 165 - 18 + 1, p_a_2_ + 1 + 1, 18, 18);
            }
            else
            {
                blu.this.c(p_a_1_ + 165 - 18, p_a_2_ + 1, 18, 18);
            }

            if (this.v == 2)
            {
                blu.this.c(p_a_1_ + 215 - 18 + 1, p_a_2_ + 1 + 1, 36, 18);
            }
            else
            {
                blu.this.c(p_a_1_ + 215 - 18, p_a_2_ + 1, 36, 18);
            }

            if (this.v == 3)
            {
                blu.this.c(p_a_1_ + 265 - 18 + 1, p_a_2_ + 1 + 1, 90, 18);
            }
            else
            {
                blu.this.c(p_a_1_ + 265 - 18, p_a_2_ + 1, 90, 18);
            }

            if (this.v == 4)
            {
                blu.this.c(p_a_1_ + 315 - 18 + 1, p_a_2_ + 1 + 1, 108, 18);
            }
            else
            {
                blu.this.c(p_a_1_ + 315 - 18, p_a_2_ + 1, 108, 18);
            }
        }

        protected void a(int p_a_1_, int p_a_2_, int p_a_3_, int p_a_4_, int p_a_5_, int p_a_6_, float p_a_7_)
        {
            RecipeBookServer recipebookserver = this.c(p_a_1_);
            ItemStack itemstack = recipebookserver.b();
            blu.this.a(p_a_2_ + 40, p_a_3_, itemstack);
            this.a(qs.c(itemstack), p_a_2_ + 115, p_a_3_, p_a_1_ % 2 == 0);
            this.a(qs.a(itemstack), p_a_2_ + 165, p_a_3_, p_a_1_ % 2 == 0);
            this.a(recipebookserver, p_a_2_ + 215, p_a_3_, p_a_1_ % 2 == 0);
            this.a(qs.d(itemstack), p_a_2_ + 265, p_a_3_, p_a_1_ % 2 == 0);
            this.a(qs.e(itemstack), p_a_2_ + 315, p_a_3_, p_a_1_ % 2 == 0);
        }

        protected String b(int p_b_1_)
        {
            if (p_b_1_ == 1)
            {
                return "stat.crafted";
            }
            else if (p_b_1_ == 2)
            {
                return "stat.used";
            }
            else if (p_b_1_ == 3)
            {
                return "stat.pickup";
            }
            else
            {
                return p_b_1_ == 4 ? "stat.dropped" : "stat.depleted";
            }
        }
    }

    class d extends GuiButtonToggle
    {
        private final List<EntitySelectors.ArmoredMob> v = Lists.<EntitySelectors.ArmoredMob>newArrayList();

        public d(GameSettings p_i146_2_)
        {
            super(p_i146_2_, blu.this.field_191808_k, blu.this.field_191809_l, 32, blu.this.field_191809_l - 64, blu.this.field_193939_q.field_191383_a * 4);
            this.func_191753_b(false);

            for (EntitySelectors.ArmoredMob entityselectors$armoredmob : EntitySelectors.HAS_INVENTORY.values())
            {
                if (blu.this.t.a(entityselectors$armoredmob.d) > 0 || blu.this.t.a(entityselectors$armoredmob.e) > 0)
                {
                    this.v.add(entityselectors$armoredmob);
                }
            }
        }

        protected int b()
        {
            return this.v.size();
        }

        protected void a(int p_a_1_, boolean p_a_2_, int p_a_3_, int p_a_4_)
        {
        }

        protected boolean a(int p_a_1_)
        {
            return false;
        }

        protected int k()
        {
            return this.b() * blu.this.field_193939_q.field_191383_a * 4;
        }

        protected void a()
        {
            blu.this.c();
        }

        protected void a(int p_a_1_, int p_a_2_, int p_a_3_, int p_a_4_, int p_a_5_, int p_a_6_, float p_a_7_)
        {
            EntitySelectors.ArmoredMob entityselectors$armoredmob = this.v.get(p_a_1_);
            String s = LanguageManager.a("entity." + EntitySelectors.a(entityselectors$armoredmob.armor) + ".name");
            int i = blu.this.t.a(entityselectors$armoredmob.d);
            int j = blu.this.t.a(entityselectors$armoredmob.e);
            String s1 = LanguageManager.a("stat.entityKills", i, s);
            String s2 = LanguageManager.a("stat.entityKilledBy", s, j);

            if (i == 0)
            {
                s1 = LanguageManager.a("stat.entityKills.none", s);
            }

            if (j == 0)
            {
                s2 = LanguageManager.a("stat.entityKilledBy.none", s);
            }

            blu.this.c(blu.this.field_193939_q, s, p_a_2_ + 2 - 10, p_a_3_ + 1, 16777215);
            blu.this.c(blu.this.field_193939_q, s1, p_a_2_ + 2, p_a_3_ + 1 + blu.this.field_193939_q.field_191383_a, i == 0 ? 6316128 : 9474192);
            blu.this.c(blu.this.field_193939_q, s2, p_a_2_ + 2, p_a_3_ + 1 + blu.this.field_193939_q.field_191383_a * 2, j == 0 ? 6316128 : 9474192);
        }
    }

    abstract class e extends GuiButtonToggle
    {
        protected int v = -1;
        protected List<RecipeBookServer> w;
        protected Comparator<RecipeBookServer> x;
        protected int y = -1;
        protected int z;

        protected e(GameSettings p_i147_2_)
        {
            super(p_i147_2_, blu.this.field_191808_k, blu.this.field_191809_l, 32, blu.this.field_191809_l - 64, 20);
            this.func_191753_b(false);
            this.a(true, 20);
        }

        protected void a(int p_a_1_, boolean p_a_2_, int p_a_3_, int p_a_4_)
        {
        }

        protected boolean a(int p_a_1_)
        {
            return false;
        }

        public int c()
        {
            return 375;
        }

        protected int d()
        {
            return this.b / 2 + 140;
        }

        protected void a()
        {
            blu.this.c();
        }

        protected void a(int p_a_1_, int p_a_2_, VertexBufferUploader p_a_3_)
        {
            if (!Mouse.isButtonDown(0))
            {
                this.v = -1;
            }

            if (this.v == 0)
            {
                blu.this.c(p_a_1_ + 115 - 18, p_a_2_ + 1, 0, 0);
            }
            else
            {
                blu.this.c(p_a_1_ + 115 - 18, p_a_2_ + 1, 0, 18);
            }

            if (this.v == 1)
            {
                blu.this.c(p_a_1_ + 165 - 18, p_a_2_ + 1, 0, 0);
            }
            else
            {
                blu.this.c(p_a_1_ + 165 - 18, p_a_2_ + 1, 0, 18);
            }

            if (this.v == 2)
            {
                blu.this.c(p_a_1_ + 215 - 18, p_a_2_ + 1, 0, 0);
            }
            else
            {
                blu.this.c(p_a_1_ + 215 - 18, p_a_2_ + 1, 0, 18);
            }

            if (this.v == 3)
            {
                blu.this.c(p_a_1_ + 265 - 18, p_a_2_ + 1, 0, 0);
            }
            else
            {
                blu.this.c(p_a_1_ + 265 - 18, p_a_2_ + 1, 0, 18);
            }

            if (this.v == 4)
            {
                blu.this.c(p_a_1_ + 315 - 18, p_a_2_ + 1, 0, 0);
            }
            else
            {
                blu.this.c(p_a_1_ + 315 - 18, p_a_2_ + 1, 0, 18);
            }

            if (this.y != -1)
            {
                int i = 79;
                int j = 18;

                if (this.y == 1)
                {
                    i = 129;
                }
                else if (this.y == 2)
                {
                    i = 179;
                }
                else if (this.y == 3)
                {
                    i = 229;
                }
                else if (this.y == 4)
                {
                    i = 279;
                }

                if (this.z == 1)
                {
                    j = 36;
                }

                blu.this.c(p_a_1_ + i, p_a_2_ + 1, j, 0);
            }
        }

        protected void a(int p_a_1_, int p_a_2_)
        {
            this.v = -1;

            if (p_a_1_ >= 79 && p_a_1_ < 115)
            {
                this.v = 0;
            }
            else if (p_a_1_ >= 129 && p_a_1_ < 165)
            {
                this.v = 1;
            }
            else if (p_a_1_ >= 179 && p_a_1_ < 215)
            {
                this.v = 2;
            }
            else if (p_a_1_ >= 229 && p_a_1_ < 265)
            {
                this.v = 3;
            }
            else if (p_a_1_ >= 279 && p_a_1_ < 315)
            {
                this.v = 4;
            }

            if (this.v >= 0)
            {
                this.d(this.v);
                this.a.U().a(SoundList.a(qf.ic, 1.0F));
            }
        }

        protected final int b()
        {
            return this.w.size();
        }

        protected final RecipeBookServer c(int p_c_1_)
        {
            return this.w.get(p_c_1_);
        }

        protected abstract String b(int var1);

        protected void a(TupleIntJsonSerializable p_a_1_, int p_a_2_, int p_a_3_, boolean p_a_4_)
        {
            if (p_a_1_ != null)
            {
                String s = p_a_1_.a(blu.this.t.a(p_a_1_));
                blu.this.c(blu.this.field_193939_q, s, p_a_2_ - blu.this.field_193939_q.a(s), p_a_3_ + 5, p_a_4_ ? 16777215 : 9474192);
            }
            else
            {
                String s1 = "-";
                blu.this.c(blu.this.field_193939_q, "-", p_a_2_ - blu.this.field_193939_q.a("-"), p_a_3_ + 5, p_a_4_ ? 16777215 : 9474192);
            }
        }

        protected void b(int p_b_1_, int p_b_2_)
        {
            if (p_b_2_ >= this.d && p_b_2_ <= this.e)
            {
                int i = this.c(p_b_1_, p_b_2_);
                int j = (this.b - this.c()) / 2;

                if (i >= 0)
                {
                    if (p_b_1_ < j + 40 || p_b_1_ > j + 40 + 20)
                    {
                        return;
                    }

                    RecipeBookServer recipebookserver = this.c(i);
                    this.a(recipebookserver, p_b_1_, p_b_2_);
                }
                else
                {
                    String s;

                    if (p_b_1_ >= j + 115 - 18 && p_b_1_ <= j + 115)
                    {
                        s = this.b(0);
                    }
                    else if (p_b_1_ >= j + 165 - 18 && p_b_1_ <= j + 165)
                    {
                        s = this.b(1);
                    }
                    else if (p_b_1_ >= j + 215 - 18 && p_b_1_ <= j + 215)
                    {
                        s = this.b(2);
                    }
                    else if (p_b_1_ >= j + 265 - 18 && p_b_1_ <= j + 265)
                    {
                        s = this.b(3);
                    }
                    else
                    {
                        if (p_b_1_ < j + 315 - 18 || p_b_1_ > j + 315)
                        {
                            return;
                        }

                        s = this.b(4);
                    }

                    s = ("" + LanguageManager.a(s)).trim();

                    if (!s.isEmpty())
                    {
                        int k = p_b_1_ + 12;
                        int l = p_b_2_ - 12;
                        int i1 = blu.this.field_193939_q.a(s);
                        blu.this.a(k - 3, l - 3, k + i1 + 3, l + 8 + 3, -1073741824, -1073741824);
                        blu.this.field_193939_q.a(s, (float)k, (float)l, -1);
                    }
                }
            }
        }

        protected void a(RecipeBookServer p_a_1_, int p_a_2_, int p_a_3_)
        {
            if (p_a_1_ != null)
            {
                ItemStack itemstack = p_a_1_.b();
                Items items = new Items(itemstack);
                String s = items.a();
                String s1 = ("" + LanguageManager.a(s + ".name")).trim();

                if (!s1.isEmpty())
                {
                    int i = p_a_2_ + 12;
                    int j = p_a_3_ - 12;
                    int k = blu.this.field_193939_q.a(s1);
                    blu.this.a(i - 3, j - 3, i + k + 3, j + 8 + 3, -1073741824, -1073741824);
                    blu.this.field_193939_q.a(s1, (float)i, (float)j, -1);
                }
            }
        }

        protected void d(int p_d_1_)
        {
            if (p_d_1_ != this.y)
            {
                this.y = p_d_1_;
                this.z = -1;
            }
            else if (this.z == -1)
            {
                this.z = 1;
            }
            else
            {
                this.y = -1;
                this.z = 0;
            }

            Collections.sort(this.w, this.x);
        }
    }
}
