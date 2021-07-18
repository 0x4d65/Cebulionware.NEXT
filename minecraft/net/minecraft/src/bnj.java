package net.minecraft.src;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.gui.GuiOptionsRowList;
import net.minecraft.client.gui.ServerListEntryLanDetected;
import net.minecraft.client.gui.ServerListEntryNormal;
import net.minecraft.client.gui.ServerSelectionList;
import net.minecraft.client.multiplayer.ThreadLanServerPing;
import net.minecraft.client.settings.GameSettings;

public class bnj extends GuiOptionsRowList
{
    private final ServerListEntryLanDetected u;
    private final List<bni> v = Lists.<bni>newArrayList();
    private final List<ServerSelectionList> w = Lists.<ServerSelectionList>newArrayList();
    private final GuiOptionsRowList.Row x = new ServerListEntryNormal();
    private int y = -1;

    public bnj(ServerListEntryLanDetected p_i67_1_, GameSettings p_i67_2_, int p_i67_3_, int p_i67_4_, int p_i67_5_, int p_i67_6_, int p_i67_7_)
    {
        super(p_i67_2_, p_i67_3_, p_i67_4_, p_i67_5_, p_i67_6_, p_i67_7_);
        this.u = p_i67_1_;
    }

    public GuiOptionsRowList.Row b(int p_b_1_)
    {
        if (p_b_1_ < this.v.size())
        {
            return this.v.get(p_b_1_);
        }
        else
        {
            p_b_1_ = p_b_1_ - this.v.size();

            if (p_b_1_ == 0)
            {
                return this.x;
            }
            else
            {
                --p_b_1_;
                return this.w.get(p_b_1_);
            }
        }
    }

    protected int getSize()
    {
        return this.v.size() + 1 + this.w.size();
    }

    public void c(int p_c_1_)
    {
        this.y = p_c_1_;
    }

    protected boolean a(int p_a_1_)
    {
        return p_a_1_ == this.y;
    }

    public int e()
    {
        return this.y;
    }

    public void a(bsf p_a_1_)
    {
        this.v.clear();

        for (int i = 0; i < p_a_1_.c(); ++i)
        {
            this.v.add(new bni(this.u, p_a_1_.a(i)));
        }
    }

    public void a(List<ThreadLanServerPing> p_a_1_)
    {
        this.w.clear();

        for (ThreadLanServerPing threadlanserverping : p_a_1_)
        {
            this.w.add(new ServerSelectionList(this.u, threadlanserverping));
        }
    }

    protected int getScrollBarX()
    {
        return super.getScrollBarX() + 30;
    }

    /**
     * Gets the width of the list
     */
    public int getListWidth()
    {
        return super.getListWidth() + 85;
    }
}
