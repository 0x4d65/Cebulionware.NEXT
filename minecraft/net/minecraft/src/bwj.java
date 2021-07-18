package net.minecraft.src;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import javax.annotation.Nullable;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.client.renderer.block.model.multipart.Multipart;

public class bwj implements Multipart
{
    final Iterable<Multipart> c;

    public bwj(Iterable<Multipart> p_i594_1_)
    {
        this.c = p_i594_1_;
    }

    public Predicate<awt> a(final BlockWorldState p_a_1_)
    {
        return Predicates.or(Iterables.transform(this.c, new Function<Multipart, Predicate<awt>>()
        {
            @Nullable
            public Predicate<awt> a(@Nullable Multipart p_a_1_x)
            {
                return p_a_1_ == null ? null : p_a_1_.a(p_a_1_);
            }
        }));
    }
}
