package net.minecraft.src;

import com.google.common.collect.ImmutableMap;
import java.util.Collection;
import net.minecraft.block.BlockBone;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockProperties;
import net.minecraft.world.border.IBorderListener;

public interface awt extends IBlockProperties, BlockStateContainer
{
    Collection < IBorderListener<? >> s();

    <T extends Comparable<T>> T c(IBorderListener<T> var1);

    <T extends Comparable<T>, V extends T> awt a(IBorderListener<T> var1, V var2);

    <T extends Comparable<T>> awt a(IBorderListener<T> var1);

    ImmutableMap < IBorderListener<?>, Comparable<? >> t();

    BlockBone u();
}
