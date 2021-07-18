package net.minecraft.src;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.block.BlockBone;
import net.minecraft.client.renderer.block.model.SimpleBakedModel;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.world.border.IBorderListener;

public class bwr extends DefaultStateMapper
{
    private final IBorderListener<?> a;
    private final String c;
    private final List < IBorderListener<? >> d;

    private bwr(@Nullable IBorderListener<?> p_i584_1_, @Nullable String p_i584_2_, List < IBorderListener<? >> p_i584_3_)
    {
        this.a = p_i584_1_;
        this.c = p_i584_2_;
        this.d = p_i584_3_;
    }

    protected SimpleBakedModel a(awt p_a_1_)
    {
        Map < IBorderListener<?>, Comparable<? >> map = Maps. < IBorderListener<?>, Comparable<? >> newLinkedHashMap(p_a_1_.t());
        String s;

        if (this.a == null)
        {
            s = ((BehaviorProjectileDispense)BlockBone.h.getNameForObject(p_a_1_.u())).toString();
        }
        else
        {
            s = this.a(this.a, map);
        }

        if (this.c != null)
        {
            s = s + this.c;
        }

        for (IBorderListener<?> iborderlistener : this.d)
        {
            map.remove(iborderlistener);
        }

        return new SimpleBakedModel(s, this.a(map));
    }

    private <T extends Comparable<T>> String a(IBorderListener<T> p_a_1_, Map < IBorderListener<?>, Comparable<? >> p_a_2_)
    {
        return p_a_1_.a(p_a_2_.remove(this.a));
    }

    public static class a
    {
        private IBorderListener<?> a;
        private String b;
        private final List < IBorderListener<? >> c = Lists. < IBorderListener<? >> newArrayList();

        public bwr.a a(IBorderListener<?> p_a_1_)
        {
            this.a = p_a_1_;
            return this;
        }

        public bwr.a a(String p_a_1_)
        {
            this.b = p_a_1_;
            return this;
        }

        public bwr.a a(IBorderListener<?>... p_a_1_)
        {
            Collections.addAll(this.c, p_a_1_);
            return this;
        }

        public bwr a()
        {
            return new bwr(this.a, this.b, this.c);
        }
    }
}
