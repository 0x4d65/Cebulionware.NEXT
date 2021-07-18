package net.minecraft.src;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.Arrays;
import it.unimi.dsi.fastutil.Swapper;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntComparator;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class cgz<T>
{
    private static final boolean b = Boolean.parseBoolean(System.getProperty("SuffixArray.printComparisons", "false"));
    private static final boolean c = Boolean.parseBoolean(System.getProperty("SuffixArray.printArray", "false"));
    private static final Logger d = LogManager.getLogger();
    protected final List<T> a = Lists.<T>newArrayList();
    private final IntList e = new IntArrayList();
    private final IntList f = new IntArrayList();
    private IntList g = new IntArrayList();
    private IntList h = new IntArrayList();
    private int i;

    public void a(T p_a_1_, String p_a_2_)
    {
        this.i = Math.max(this.i, p_a_2_.length());
        int i = this.a.size();
        this.a.add(p_a_1_);
        this.f.add(this.e.size());

        for (int j = 0; j < p_a_2_.length(); ++j)
        {
            this.g.add(i);
            this.h.add(j);
            this.e.add(p_a_2_.charAt(j));
        }

        this.g.add(i);
        this.h.add(p_a_2_.length());
        this.e.add(-1);
    }

    public void a()
    {
        int i = this.e.size();
        int[] aint = new int[i];
        final int[] aint1 = new int[i];
        final int[] aint2 = new int[i];
        int[] aint3 = new int[i];
        IntComparator intcomparator = new IntComparator()
        {
            public int compare(int p_compare_1_, int p_compare_2_)
            {
                return aint1[p_compare_1_] == aint1[p_compare_2_] ? Integer.compare(aint2[p_compare_1_], aint2[p_compare_2_]) : Integer.compare(aint1[p_compare_1_], aint1[p_compare_2_]);
            }
            public int a(Integer p_a_1_, Integer p_a_2_)
            {
                return this.compare(p_a_1_.intValue(), p_a_2_.intValue());
            }
        };
        Swapper swapper = (p_a_3_, p_a_4_) ->
        {
            if (p_a_3_ != p_a_4_)
            {
                int i2 = p_a_0_[p_a_3_];
                p_a_0_[p_a_3_] = p_a_0_[p_a_4_];
                p_a_0_[p_a_4_] = i2;
                i2 = p_a_1_[p_a_3_];
                p_a_1_[p_a_3_] = p_a_1_[p_a_4_];
                p_a_1_[p_a_4_] = i2;
                i2 = p_a_2_[p_a_3_];
                p_a_2_[p_a_3_] = p_a_2_[p_a_4_];
                p_a_2_[p_a_4_] = i2;
            }
        };

        for (int j = 0; j < i; ++j)
        {
            aint[j] = this.e.getInt(j);
        }

        int k1 = 1;

        for (int k = Math.min(i, this.i); k1 * 2 < k; k1 *= 2)
        {
            for (int l = 0; l < i; aint3[l] = l++)
            {
                aint1[l] = aint[l];
                aint2[l] = l + k1 < i ? aint[l + k1] : -2;
            }

            Arrays.quickSort(0, i, intcomparator, swapper);

            for (int l1 = 0; l1 < i; ++l1)
            {
                if (l1 > 0 && aint1[l1] == aint1[l1 - 1] && aint2[l1] == aint2[l1 - 1])
                {
                    aint[aint3[l1]] = aint[aint3[l1 - 1]];
                }
                else
                {
                    aint[aint3[l1]] = l1;
                }
            }
        }

        IntList intlist1 = this.g;
        IntList intlist = this.h;
        this.g = new IntArrayList(intlist1.size());
        this.h = new IntArrayList(intlist.size());

        for (int i1 = 0; i1 < i; ++i1)
        {
            int j1 = aint3[i1];
            this.g.add(intlist1.getInt(j1));
            this.h.add(intlist.getInt(j1));
        }

        if (c)
        {
            this.b();
        }
    }

    private void b()
    {
        for (int i2 = 0; i2 < this.g.size(); ++i2)
        {
            d.debug("{} {}", Integer.valueOf(i2), this.a(i2));
        }

        d.debug("");
    }

    private String a(int p_a_1_)
    {
        int i2 = this.h.getInt(p_a_1_);
        int j2 = this.f.getInt(this.g.getInt(p_a_1_));
        StringBuilder stringbuilder = new StringBuilder();

        for (int k2 = 0; j2 + k2 < this.e.size(); ++k2)
        {
            if (k2 == i2)
            {
                stringbuilder.append('^');
            }

            int l2 = ((Integer)this.e.get(j2 + k2)).intValue();

            if (l2 == -1)
            {
                break;
            }

            stringbuilder.append((char)l2);
        }

        return stringbuilder.toString();
    }

    private int a(String p_a_1_, int p_a_2_)
    {
        int i2 = this.f.getInt(this.g.getInt(p_a_2_));
        int j2 = this.h.getInt(p_a_2_);

        for (int k2 = 0; k2 < p_a_1_.length(); ++k2)
        {
            int l2 = this.e.getInt(i2 + j2 + k2);

            if (l2 == -1)
            {
                return 1;
            }

            char c0 = p_a_1_.charAt(k2);
            char c1 = (char)l2;

            if (c0 < c1)
            {
                return -1;
            }

            if (c0 > c1)
            {
                return 1;
            }
        }

        return 0;
    }

    public List<T> a(String p_a_1_)
    {
        int i2 = this.g.size();
        int j2 = 0;
        int k2 = i2;

        while (j2 < k2)
        {
            int l2 = j2 + (k2 - j2) / 2;
            int i3 = this.a(p_a_1_, l2);

            if (b)
            {
                d.debug("comparing lower \"{}\" with {} \"{}\": {}", p_a_1_, Integer.valueOf(l2), this.a(l2), Integer.valueOf(i3));
            }

            if (i3 > 0)
            {
                j2 = l2 + 1;
            }
            else
            {
                k2 = l2;
            }
        }

        if (j2 >= 0 && j2 < i2)
        {
            int i4 = j2;
            k2 = i2;

            while (j2 < k2)
            {
                int j4 = j2 + (k2 - j2) / 2;
                int j3 = this.a(p_a_1_, j4);

                if (b)
                {
                    d.debug("comparing upper \"{}\" with {} \"{}\": {}", p_a_1_, Integer.valueOf(j4), this.a(j4), Integer.valueOf(j3));
                }

                if (j3 >= 0)
                {
                    j2 = j4 + 1;
                }
                else
                {
                    k2 = j4;
                }
            }

            int k4 = j2;
            IntSet intset = new IntOpenHashSet();

            for (int k3 = i4; k3 < k4; ++k3)
            {
                intset.add(this.g.getInt(k3));
            }

            int[] aint4 = intset.toIntArray();
            java.util.Arrays.sort(aint4);
            Set<T> set = Sets.<T>newLinkedHashSet();

            for (int l3 : aint4)
            {
                set.add(this.a.get(l3));
            }

            return Lists.newArrayList(set);
        }
        else
        {
            return Collections.<T>emptyList();
        }
    }
}
