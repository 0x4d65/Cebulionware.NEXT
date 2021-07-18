package net.minecraft.src;

import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.client.renderer.VertexBufferUploader;
import net.minecraft.client.renderer.debug.DebugRendererNeighborsUpdate;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.world.IWorldEventListener;

public class byk implements DebugRendererNeighborsUpdate.a
{
    private final GameSettings a;

    public byk(GameSettings p_i377_1_)
    {
        this.a = p_i377_1_;
    }

    public void a(float p_a_1_, long p_a_2_)
    {
        RecipeItemHelper recipeitemhelper = this.a.fboEnable;
        double d0 = recipeitemhelper.M + (recipeitemhelper.p - recipeitemhelper.M) * (double)p_a_1_;
        double d1 = recipeitemhelper.N + (recipeitemhelper.q - recipeitemhelper.N) * (double)p_a_1_;
        double d2 = recipeitemhelper.O + (recipeitemhelper.r - recipeitemhelper.O) * (double)p_a_1_;
        IWorldEventListener iworldeventlistener = this.a.fboEnable.l;
        Iterable<BlockPos> iterable = BlockPos.func_191532_a(IProgressUpdate.c(recipeitemhelper.p - 6.0D), IProgressUpdate.c(recipeitemhelper.q - 6.0D), IProgressUpdate.c(recipeitemhelper.r - 6.0D), IProgressUpdate.c(recipeitemhelper.p + 6.0D), IProgressUpdate.c(recipeitemhelper.q + 6.0D), IProgressUpdate.c(recipeitemhelper.r + 6.0D));
        ItemRenderer.m();
        ItemRenderer.a(ItemRenderer.r.l, ItemRenderer.l.j, ItemRenderer.r.e, ItemRenderer.l.n);
        ItemRenderer.d(2.0F);
        ItemRenderer.z();
        ItemRenderer.a(false);

        for (BlockPos blockpos : iterable)
        {
            awt awt = iworldeventlistener.o(blockpos);

            if (awt.u() != IGrowable.a)
            {
                Vec2f vec2f = awt.c(iworldeventlistener, blockpos).g(0.002D).d(-d0, -d1, -d2);
                double d3 = vec2f.ZERO;
                double d4 = vec2f.ONE;
                double d5 = vec2f.UNIT_X;
                double d6 = vec2f.NEGATIVE_UNIT_X;
                double d7 = vec2f.UNIT_Y;
                double d8 = vec2f.NEGATIVE_UNIT_Y;
                float f = 1.0F;
                float f1 = 0.0F;
                float f2 = 0.0F;
                float f3 = 0.5F;

                if (awt.d(iworldeventlistener, blockpos, EnumFacing.WEST) == IBlockState.a)
                {
                    VertexBufferUploader vertexbufferuploader = VertexBufferUploader.a();
                    RegionRenderCacheBuilder regionrendercachebuilder = vertexbufferuploader.c();
                    regionrendercachebuilder.a(5, VertexFormat.uvOffsetsById);
                    regionrendercachebuilder.b(d3, d4, d5).a(1.0F, 0.0F, 0.0F, 0.5F).d();
                    regionrendercachebuilder.b(d3, d4, d8).a(1.0F, 0.0F, 0.0F, 0.5F).d();
                    regionrendercachebuilder.b(d3, d7, d5).a(1.0F, 0.0F, 0.0F, 0.5F).d();
                    regionrendercachebuilder.b(d3, d7, d8).a(1.0F, 0.0F, 0.0F, 0.5F).d();
                    vertexbufferuploader.b();
                }

                if (awt.d(iworldeventlistener, blockpos, EnumFacing.SOUTH) == IBlockState.a)
                {
                    VertexBufferUploader vertexbufferuploader1 = VertexBufferUploader.a();
                    RegionRenderCacheBuilder regionrendercachebuilder1 = vertexbufferuploader1.c();
                    regionrendercachebuilder1.a(5, VertexFormat.uvOffsetsById);
                    regionrendercachebuilder1.b(d3, d7, d8).a(1.0F, 0.0F, 0.0F, 0.5F).d();
                    regionrendercachebuilder1.b(d3, d4, d8).a(1.0F, 0.0F, 0.0F, 0.5F).d();
                    regionrendercachebuilder1.b(d6, d7, d8).a(1.0F, 0.0F, 0.0F, 0.5F).d();
                    regionrendercachebuilder1.b(d6, d4, d8).a(1.0F, 0.0F, 0.0F, 0.5F).d();
                    vertexbufferuploader1.b();
                }

                if (awt.d(iworldeventlistener, blockpos, EnumFacing.EAST) == IBlockState.a)
                {
                    VertexBufferUploader vertexbufferuploader2 = VertexBufferUploader.a();
                    RegionRenderCacheBuilder regionrendercachebuilder2 = vertexbufferuploader2.c();
                    regionrendercachebuilder2.a(5, VertexFormat.uvOffsetsById);
                    regionrendercachebuilder2.b(d6, d4, d8).a(1.0F, 0.0F, 0.0F, 0.5F).d();
                    regionrendercachebuilder2.b(d6, d4, d5).a(1.0F, 0.0F, 0.0F, 0.5F).d();
                    regionrendercachebuilder2.b(d6, d7, d8).a(1.0F, 0.0F, 0.0F, 0.5F).d();
                    regionrendercachebuilder2.b(d6, d7, d5).a(1.0F, 0.0F, 0.0F, 0.5F).d();
                    vertexbufferuploader2.b();
                }

                if (awt.d(iworldeventlistener, blockpos, EnumFacing.NORTH) == IBlockState.a)
                {
                    VertexBufferUploader vertexbufferuploader3 = VertexBufferUploader.a();
                    RegionRenderCacheBuilder regionrendercachebuilder3 = vertexbufferuploader3.c();
                    regionrendercachebuilder3.a(5, VertexFormat.uvOffsetsById);
                    regionrendercachebuilder3.b(d6, d7, d5).a(1.0F, 0.0F, 0.0F, 0.5F).d();
                    regionrendercachebuilder3.b(d6, d4, d5).a(1.0F, 0.0F, 0.0F, 0.5F).d();
                    regionrendercachebuilder3.b(d3, d7, d5).a(1.0F, 0.0F, 0.0F, 0.5F).d();
                    regionrendercachebuilder3.b(d3, d4, d5).a(1.0F, 0.0F, 0.0F, 0.5F).d();
                    vertexbufferuploader3.b();
                }

                if (awt.d(iworldeventlistener, blockpos, EnumFacing.DOWN) == IBlockState.a)
                {
                    VertexBufferUploader vertexbufferuploader4 = VertexBufferUploader.a();
                    RegionRenderCacheBuilder regionrendercachebuilder4 = vertexbufferuploader4.c();
                    regionrendercachebuilder4.a(5, VertexFormat.uvOffsetsById);
                    regionrendercachebuilder4.b(d3, d4, d5).a(1.0F, 0.0F, 0.0F, 0.5F).d();
                    regionrendercachebuilder4.b(d6, d4, d5).a(1.0F, 0.0F, 0.0F, 0.5F).d();
                    regionrendercachebuilder4.b(d3, d4, d8).a(1.0F, 0.0F, 0.0F, 0.5F).d();
                    regionrendercachebuilder4.b(d6, d4, d8).a(1.0F, 0.0F, 0.0F, 0.5F).d();
                    vertexbufferuploader4.b();
                }

                if (awt.d(iworldeventlistener, blockpos, EnumFacing.UP) == IBlockState.a)
                {
                    VertexBufferUploader vertexbufferuploader5 = VertexBufferUploader.a();
                    RegionRenderCacheBuilder regionrendercachebuilder5 = vertexbufferuploader5.c();
                    regionrendercachebuilder5.a(5, VertexFormat.uvOffsetsById);
                    regionrendercachebuilder5.b(d3, d7, d5).a(1.0F, 0.0F, 0.0F, 0.5F).d();
                    regionrendercachebuilder5.b(d3, d7, d8).a(1.0F, 0.0F, 0.0F, 0.5F).d();
                    regionrendercachebuilder5.b(d6, d7, d5).a(1.0F, 0.0F, 0.0F, 0.5F).d();
                    regionrendercachebuilder5.b(d6, d7, d8).a(1.0F, 0.0F, 0.0F, 0.5F).d();
                    vertexbufferuploader5.b();
                }
            }
        }

        ItemRenderer.a(true);
        ItemRenderer.y();
        ItemRenderer.l();
    }
}
