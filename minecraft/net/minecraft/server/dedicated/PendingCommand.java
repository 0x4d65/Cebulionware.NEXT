package net.minecraft.server.dedicated;

import com.mojang.authlib.GameProfile;
import java.io.File;
import java.io.PrintStream;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.BlockBone;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.BlockMagma;
import net.minecraft.block.BlockQuartz;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockSnowBlock;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.MaterialPortal;
import net.minecraft.client.util.JsonException;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.entity.projectile.EntityLlamaSpit;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemChorusFruit;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemLead;
import net.minecraft.item.ItemSplashPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.rcon.IServer;
import net.minecraft.potion.PotionUtils;
import net.minecraft.src.afa;
import net.minecraft.src.awt;
import net.minecraft.src.no;
import net.minecraft.src.qf;
import net.minecraft.src.qg;
import net.minecraft.src.qs;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.tileentity.TileEntityEndGateway;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.LoggingPrintStream;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldEventListener;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraft.world.storage.loot.LootEntryItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PendingCommand
{
    public static final PrintStream field_73702_a = System.out;
    private static boolean c;
    public static boolean field_73701_b;
    private static final Logger d = LogManager.getLogger();

    public static boolean a()
    {
        return c;
    }

    static void b()
    {
        BlockDoublePlant.HALF.putObject(ItemLead.h, new JsonException()
        {
            protected EntityShulkerBullet a(IWorldEventListener p_a_1_, IPosition p_a_2_, Items p_a_3_)
            {
                afa afa = new afa(p_a_1_, p_a_2_.getX(), p_a_2_.getY(), p_a_2_.getZ());
                afa.field_190555_c = EntityEvokerFangs.a.b;
                return afa;
            }
        });
        BlockDoublePlant.HALF.putObject(ItemLead.j, new JsonException()
        {
            protected EntityShulkerBullet a(IWorldEventListener p_a_1_, IPosition p_a_2_, Items p_a_3_)
            {
                afa afa = new afa(p_a_1_, p_a_2_.getX(), p_a_2_.getY(), p_a_2_.getZ());
                afa.a(p_a_3_);
                afa.field_190555_c = EntityEvokerFangs.a.b;
                return afa;
            }
        });
        BlockDoublePlant.HALF.putObject(ItemLead.i, new JsonException()
        {
            protected EntityShulkerBullet a(IWorldEventListener p_a_1_, IPosition p_a_2_, Items p_a_3_)
            {
                EntityEvokerFangs entityevokerfangs = new EntityEgg(p_a_1_, p_a_2_.getX(), p_a_2_.getY(), p_a_2_.getZ());
                entityevokerfangs.field_190555_c = EntityEvokerFangs.a.b;
                return entityevokerfangs;
            }
        });
        BlockDoublePlant.HALF.putObject(ItemLead.aX, new JsonException()
        {
            protected EntityShulkerBullet a(IWorldEventListener p_a_1_, IPosition p_a_2_, Items p_a_3_)
            {
                return new EntityExpBottle(p_a_1_, p_a_2_.getX(), p_a_2_.getY(), p_a_2_.getZ());
            }
        });
        BlockDoublePlant.HALF.putObject(ItemLead.aG, new JsonException()
        {
            protected EntityShulkerBullet a(IWorldEventListener p_a_1_, IPosition p_a_2_, Items p_a_3_)
            {
                return new EntityThrowable(p_a_1_, p_a_2_.getX(), p_a_2_.getY(), p_a_2_.getZ());
            }
        });
        BlockDoublePlant.HALF.putObject(ItemLead.bV, new JsonException()
        {
            protected EntityShulkerBullet a(IWorldEventListener p_a_1_, IPosition p_a_2_, Items p_a_3_)
            {
                return new EntityTippedArrow(p_a_1_, p_a_2_.getX(), p_a_2_.getY(), p_a_2_.getZ());
            }
            protected float a()
            {
                return super.a() * 0.5F;
            }
            protected float b()
            {
                return super.b() * 1.25F;
            }
        });
        BlockDoublePlant.HALF.putObject(ItemLead.bI, new IBehaviorDispenseItem()
        {
            public Items a(IBlockSource p_a_1_, final Items p_a_2_)
            {
                return (new JsonException()
                {
                    protected EntityShulkerBullet a(IWorldEventListener p_a_1_, IPosition p_a_2_x, Items p_a_3_)
                    {
                        return new EntityWitherSkull(p_a_1_, p_a_2_.getX(), p_a_2_.getY(), p_a_2_.getZ(), p_a_2_.l());
                    }
                    protected float a()
                    {
                        return super.a() * 0.5F;
                    }
                    protected float b()
                    {
                        return super.b() * 1.25F;
                    }
                }).a(p_a_1_, p_a_2_);
            }
        });
        BlockDoublePlant.HALF.putObject(ItemLead.bJ, new IBehaviorDispenseItem()
        {
            public Items a(IBlockSource p_a_1_, final Items p_a_2_)
            {
                return (new JsonException()
                {
                    protected EntityShulkerBullet a(IWorldEventListener p_a_1_, IPosition p_a_2_x, Items p_a_3_)
                    {
                        return new EntityWitherSkull(p_a_1_, p_a_2_.getX(), p_a_2_.getY(), p_a_2_.getZ(), p_a_2_.l());
                    }
                    protected float a()
                    {
                        return super.a() * 0.5F;
                    }
                    protected float b()
                    {
                        return super.b() * 1.25F;
                    }
                }).a(p_a_1_, p_a_2_);
            }
        });
        BlockDoublePlant.HALF.putObject(ItemLead.bU, new BehaviorDefaultDispenseItem()
        {
            public Items b(IBlockSource p_b_1_, Items p_b_2_)
            {
                EnumFacing enumfacing = (EnumFacing)p_b_1_.e().c(BlockDoublePlant.VARIANT);
                double d0 = p_b_1_.getX() + (double)enumfacing.getFrontOffsetX();
                double d1 = (double)((float)(p_b_1_.getBlockPos().getY() + enumfacing.getFrontOffsetY()) + 0.2F);
                double d2 = p_b_1_.getZ() + (double)enumfacing.getFrontOffsetZ();
                EntityList entitylist = ItemSplashPotion.a(p_b_1_.h(), ItemSplashPotion.h(p_b_2_), d0, d1, d2);

                if (entitylist instanceof EnumCreatureType && p_b_2_.t())
                {
                    entitylist.c(p_b_2_.r());
                }

                ItemSplashPotion.a(p_b_1_.h(), (RecipeItemHelper)null, p_b_2_, entitylist);
                p_b_2_.g(1);
                return p_b_2_;
            }
        });
        BlockDoublePlant.HALF.putObject(ItemLead.cm, new BehaviorDefaultDispenseItem()
        {
            public Items b(IBlockSource p_b_1_, Items p_b_2_)
            {
                EnumFacing enumfacing = (EnumFacing)p_b_1_.e().c(BlockDoublePlant.VARIANT);
                double d0 = p_b_1_.getX() + (double)enumfacing.getFrontOffsetX();
                double d1 = (double)((float)p_b_1_.getBlockPos().getY() + 0.2F);
                double d2 = p_b_1_.getZ() + (double)enumfacing.getFrontOffsetZ();
                EntityLlamaSpit entityllamaspit = new EntityLlamaSpit(p_b_1_.h(), d0, d1, d2, p_b_2_);
                p_b_1_.h().a(entityllamaspit);
                p_b_2_.g(1);
                return p_b_2_;
            }
            protected void playDispenseSound(IBlockSource source)
            {
                source.h().sendBlockBreakProgress(1004, source.getBlockPos(), 0);
            }
        });
        BlockDoublePlant.HALF.putObject(ItemLead.bW, new BehaviorDefaultDispenseItem()
        {
            public Items b(IBlockSource p_b_1_, Items p_b_2_)
            {
                EnumFacing enumfacing = (EnumFacing)p_b_1_.e().c(BlockDoublePlant.VARIANT);
                IPosition iposition = BlockDoublePlant.a(p_b_1_);
                double d0 = iposition.getX() + (double)((float)enumfacing.getFrontOffsetX() * 0.3F);
                double d1 = iposition.getY() + (double)((float)enumfacing.getFrontOffsetY() * 0.3F);
                double d2 = iposition.getZ() + (double)((float)enumfacing.getFrontOffsetZ() * 0.3F);
                IWorldEventListener iworldeventlistener = p_b_1_.h();
                Random random = iworldeventlistener.r;
                double d3 = random.nextGaussian() * 0.05D + (double)enumfacing.getFrontOffsetX();
                double d4 = random.nextGaussian() * 0.05D + (double)enumfacing.getFrontOffsetY();
                double d5 = random.nextGaussian() * 0.05D + (double)enumfacing.getFrontOffsetZ();
                iworldeventlistener.a(new EntitySpectralArrow(iworldeventlistener, d0, d1, d2, d3, d4, d5));
                p_b_2_.g(1);
                return p_b_2_;
            }
            protected void playDispenseSound(IBlockSource source)
            {
                source.h().sendBlockBreakProgress(1018, source.getBlockPos(), 0);
            }
        });
        BlockDoublePlant.HALF.putObject(ItemLead.aH, new PendingCommand.a(EntityMinecartChest.b.a));
        BlockDoublePlant.HALF.putObject(ItemLead.aI, new PendingCommand.a(EntityMinecartChest.b.b));
        BlockDoublePlant.HALF.putObject(ItemLead.aJ, new PendingCommand.a(EntityMinecartChest.b.c));
        BlockDoublePlant.HALF.putObject(ItemLead.aK, new PendingCommand.a(EntityMinecartChest.b.d));
        BlockDoublePlant.HALF.putObject(ItemLead.aM, new PendingCommand.a(EntityMinecartChest.b.f));
        BlockDoublePlant.HALF.putObject(ItemLead.aL, new PendingCommand.a(EntityMinecartChest.b.e));
        IBehaviorDispenseItem ibehaviordispenseitem = new BehaviorDefaultDispenseItem()
        {
            private final BehaviorDefaultDispenseItem b = new BehaviorDefaultDispenseItem();
            public Items b(IBlockSource p_b_1_, Items p_b_2_)
            {
                ItemChorusFruit itemchorusfruit = (ItemChorusFruit)p_b_2_.c();
                BlockPos blockpos = p_b_1_.getBlockPos().offset((EnumFacing)p_b_1_.e().c(BlockDoublePlant.VARIANT));
                return itemchorusfruit.a((RecipeItemHelper)null, p_b_1_.h(), blockpos) ? new Items(ItemLead.az) : this.b.a(p_b_1_, p_b_2_);
            }
        };
        BlockDoublePlant.HALF.putObject(ItemLead.aB, ibehaviordispenseitem);
        BlockDoublePlant.HALF.putObject(ItemLead.aA, ibehaviordispenseitem);
        BlockDoublePlant.HALF.putObject(ItemLead.az, new BehaviorDefaultDispenseItem()
        {
            private final BehaviorDefaultDispenseItem b = new BehaviorDefaultDispenseItem();
            public Items b(IBlockSource p_b_1_, Items p_b_2_)
            {
                IWorldEventListener iworldeventlistener = p_b_1_.h();
                BlockPos blockpos = p_b_1_.getBlockPos().offset((EnumFacing)p_b_1_.e().c(BlockDoublePlant.VARIANT));
                awt awt = iworldeventlistener.o(blockpos);
                BlockBone blockbone = awt.u();
                MaterialPortal materialportal = awt.a();
                ItemStack itemstack;

                if (MaterialPortal.h.equals(materialportal) && blockbone instanceof BlockMagma && ((Integer)awt.c(BlockMagma.b)).intValue() == 0)
                {
                    itemstack = ItemLead.aA;
                }
                else
                {
                    if (!MaterialPortal.i.equals(materialportal) || !(blockbone instanceof BlockMagma) || ((Integer)awt.c(BlockMagma.b)).intValue() != 0)
                    {
                        return super.b(p_b_1_, p_b_2_);
                    }

                    itemstack = ItemLead.aB;
                }

                iworldeventlistener.g(blockpos);
                p_b_2_.g(1);

                if (p_b_2_.b())
                {
                    return new Items(itemstack);
                }
                else
                {
                    if (((TileEntityEnchantmentTable)p_b_1_.g()).a(new Items(itemstack)) < 0)
                    {
                        this.b.a(p_b_1_, new Items(itemstack));
                    }

                    return p_b_2_;
                }
            }
        });
        BlockDoublePlant.HALF.putObject(ItemLead.e, new PendingCommand.b()
        {
            protected Items b(IBlockSource p_b_1_, Items p_b_2_)
            {
                IWorldEventListener iworldeventlistener = p_b_1_.h();
                this.b = true;
                BlockPos blockpos = p_b_1_.getBlockPos().offset((EnumFacing)p_b_1_.e().c(BlockDoublePlant.VARIANT));

                if (iworldeventlistener.d(blockpos))
                {
                    iworldeventlistener.a(blockpos, IGrowable.ab.t());

                    if (p_b_2_.a(1, iworldeventlistener.r, (EntityTrackerEntry)null))
                    {
                        p_b_2_.e(0);
                    }
                }
                else if (iworldeventlistener.o(blockpos).u() == IGrowable.W)
                {
                    IGrowable.W.d(iworldeventlistener, blockpos, IGrowable.W.t().a(BlockTrapDoor.FACING, Boolean.valueOf(true)));
                    iworldeventlistener.g(blockpos);
                }
                else
                {
                    this.b = false;
                }

                return p_b_2_;
            }
        });
        BlockDoublePlant.HALF.putObject(ItemLead.be, new PendingCommand.b()
        {
            protected Items b(IBlockSource p_b_1_, Items p_b_2_)
            {
                this.b = true;

                if (ItemCloth.a == ItemCloth.a(p_b_2_.j()))
                {
                    IWorldEventListener iworldeventlistener = p_b_1_.h();
                    BlockPos blockpos = p_b_1_.getBlockPos().offset((EnumFacing)p_b_1_.e().c(BlockDoublePlant.VARIANT));

                    if (ItemEgg.a(p_b_2_, iworldeventlistener, blockpos))
                    {
                        if (!iworldeventlistener.G)
                        {
                            iworldeventlistener.sendBlockBreakProgress(2005, blockpos, 0);
                        }
                    }
                    else
                    {
                        this.b = false;
                    }

                    return p_b_2_;
                }
                else
                {
                    return super.b(p_b_1_, p_b_2_);
                }
            }
        });
        BlockDoublePlant.HALF.putObject(ItemStack.a(IGrowable.W), new BehaviorDefaultDispenseItem()
        {
            protected Items b(IBlockSource p_b_1_, Items p_b_2_)
            {
                IWorldEventListener iworldeventlistener = p_b_1_.h();
                BlockPos blockpos = p_b_1_.getBlockPos().offset((EnumFacing)p_b_1_.e().c(BlockDoublePlant.VARIANT));
                AbstractIllager abstractillager = new AbstractIllager(iworldeventlistener, (double)blockpos.getX() + 0.5D, (double)blockpos.getY(), (double)blockpos.getZ() + 0.5D, (EnumCreatureType)null);
                iworldeventlistener.a(abstractillager);
                iworldeventlistener.a((RecipeItemHelper)null, abstractillager.p, abstractillager.q, abstractillager.r, qf.hW, qg.e, 1.0F, 1.0F);
                p_b_2_.g(1);
                return p_b_2_;
            }
        });
        BlockDoublePlant.HALF.putObject(ItemLead.ci, new PendingCommand.b()
        {
            protected Items b(IBlockSource p_b_1_, Items p_b_2_)
            {
                IWorldEventListener iworldeventlistener = p_b_1_.h();
                EnumFacing enumfacing = (EnumFacing)p_b_1_.e().c(BlockDoublePlant.VARIANT);
                BlockPos blockpos = p_b_1_.getBlockPos().offset(enumfacing);
                BlockSnowBlock blocksnowblock = IGrowable.ce;
                this.b = true;

                if (iworldeventlistener.d(blockpos) && blocksnowblock.b(iworldeventlistener, blockpos, p_b_2_))
                {
                    if (!iworldeventlistener.G)
                    {
                        iworldeventlistener.a(blockpos, blocksnowblock.t().a(BlockSnowBlock.a, EnumFacing.UP), 3);
                        TileEntityChest tileentitychest = iworldeventlistener.r(blockpos);

                        if (tileentitychest instanceof TileEntityEndGateway)
                        {
                            if (p_b_2_.j() == 3)
                            {
                                GameProfile gameprofile = null;

                                if (p_b_2_.o())
                                {
                                    NBTTagCompound nbttagcompound = p_b_2_.p();

                                    if (nbttagcompound.hasKey("SkullOwner", 10))
                                    {
                                        gameprofile = NBTUtil.readGameProfileFromNBT(nbttagcompound.getCompoundTag("SkullOwner"));
                                    }
                                    else if (nbttagcompound.hasKey("SkullOwner", 8))
                                    {
                                        String s = nbttagcompound.getString("SkullOwner");

                                        if (!Tuple.b(s))
                                        {
                                            gameprofile = new GameProfile((UUID)null, s);
                                        }
                                    }
                                }

                                ((TileEntityEndGateway)tileentitychest).a(gameprofile);
                            }
                            else
                            {
                                ((TileEntityEndGateway)tileentitychest).a(p_b_2_.j());
                            }

                            ((TileEntityEndGateway)tileentitychest).b(enumfacing.getOpposite().getHorizontalIndex() * 4);
                            IGrowable.ce.a(iworldeventlistener, blockpos, (TileEntityEndGateway)tileentitychest);
                        }

                        p_b_2_.g(1);
                    }
                }
                else if (ItemArrow.a(p_b_1_, p_b_2_).b())
                {
                    this.b = false;
                }

                return p_b_2_;
            }
        });
        BlockDoublePlant.HALF.putObject(ItemStack.a(IGrowable.aU), new PendingCommand.b()
        {
            protected Items b(IBlockSource p_b_1_, Items p_b_2_)
            {
                IWorldEventListener iworldeventlistener = p_b_1_.h();
                BlockPos blockpos = p_b_1_.getBlockPos().offset((EnumFacing)p_b_1_.e().c(BlockDoublePlant.VARIANT));
                BlockQuartz blockquartz = (BlockQuartz)IGrowable.aU;
                this.b = true;

                if (iworldeventlistener.d(blockpos) && blockquartz.b(iworldeventlistener, blockpos))
                {
                    if (!iworldeventlistener.G)
                    {
                        iworldeventlistener.a(blockpos, blockquartz.t(), 3);
                    }

                    p_b_2_.g(1);
                }
                else
                {
                    Items items = ItemArrow.a(p_b_1_, p_b_2_);

                    if (items.b())
                    {
                        this.b = false;
                    }
                }

                return p_b_2_;
            }
        });

        for (ItemCloth itemcloth : ItemCloth.values())
        {
            BlockDoublePlant.HALF.putObject(ItemStack.a(BlockSkull.a(itemcloth)), new PendingCommand.c());
        }
    }

    public static void c()
    {
        if (!c)
        {
            c = true;
            d();
            SoundCategory.b();
            BlockBone.w();
            BlockFlowerPot.e();
            MobEffects.k();
            EnchantmentHelper.g();
            ItemStack.t();
            PotionUtils.b();
            PotionTypes.a();
            EntitySelectors.c();
            BiomeColorHelper.q();
            b();

            if (!ShapedRecipes.a())
            {
                field_73701_b = true;
                d.error("Errors with built-in recipes!");
            }

            qs.a();

            if (d.isDebugEnabled())
            {
                if ((new IServer((File)null)).b())
                {
                    field_73701_b = true;
                    d.error("Errors with built-in advancements!");
                }

                if (!LootEntryItem.b())
                {
                    field_73701_b = true;
                    d.error("Errors with built-in loot tables");
                }
            }
        }
    }

    private static void d()
    {
        if (d.isDebugEnabled())
        {
            System.setErr(new LoggingPrintStream("STDERR", System.err));
            System.setOut(new LoggingPrintStream("STDOUT", field_73702_a));
        }
        else
        {
            System.setErr(new no("STDERR", System.err));
            System.setOut(new no("STDOUT", field_73702_a));
        }
    }

    public static void a(String p_a_0_)
    {
        field_73702_a.println(p_a_0_);
    }

    public static class a extends BehaviorDefaultDispenseItem
    {
        private final BehaviorDefaultDispenseItem b = new BehaviorDefaultDispenseItem();
        private final EntityMinecartChest.b c;

        public a(EntityMinecartChest.b p_i919_1_)
        {
            this.c = p_i919_1_;
        }

        public Items b(IBlockSource p_b_1_, Items p_b_2_)
        {
            EnumFacing enumfacing = (EnumFacing)p_b_1_.e().c(BlockDoublePlant.VARIANT);
            IWorldEventListener iworldeventlistener = p_b_1_.h();
            double d0 = p_b_1_.getX() + (double)((float)enumfacing.getFrontOffsetX() * 1.125F);
            double d1 = p_b_1_.getY() + (double)((float)enumfacing.getFrontOffsetY() * 1.125F);
            double d2 = p_b_1_.getZ() + (double)((float)enumfacing.getFrontOffsetZ() * 1.125F);
            BlockPos blockpos = p_b_1_.getBlockPos().offset(enumfacing);
            MaterialPortal materialportal = iworldeventlistener.o(blockpos).a();
            double d3;

            if (MaterialPortal.h.equals(materialportal))
            {
                d3 = 1.0D;
            }
            else
            {
                if (!MaterialPortal.a.equals(materialportal) || !MaterialPortal.h.equals(iworldeventlistener.o(blockpos.down()).a()))
                {
                    return this.b.a(p_b_1_, p_b_2_);
                }

                d3 = 0.0D;
            }

            EntityMinecartChest entityminecartchest = new EntityMinecartChest(iworldeventlistener, d0, d1 + d3, d2);
            entityminecartchest.a(this.c);
            entityminecartchest.v = enumfacing.getHorizontalAngle();
            iworldeventlistener.a(entityminecartchest);
            p_b_2_.g(1);
            return p_b_2_;
        }

        protected void playDispenseSound(IBlockSource source)
        {
            source.h().sendBlockBreakProgress(1000, source.getBlockPos(), 0);
        }
    }

    public abstract static class b extends BehaviorDefaultDispenseItem
    {
        protected boolean b = true;

        protected void playDispenseSound(IBlockSource source)
        {
            source.h().sendBlockBreakProgress(this.b ? 1000 : 1001, source.getBlockPos(), 0);
        }
    }

    static class c extends PendingCommand.b
    {
        private c()
        {
        }

        protected Items b(IBlockSource p_b_1_, Items p_b_2_)
        {
            BlockBone blockbone = BlockBone.a(p_b_2_.c());
            IWorldEventListener iworldeventlistener = p_b_1_.h();
            EnumFacing enumfacing = (EnumFacing)p_b_1_.e().c(BlockDoublePlant.VARIANT);
            BlockPos blockpos = p_b_1_.getBlockPos().offset(enumfacing);
            this.b = iworldeventlistener.a(blockbone, blockpos, false, EnumFacing.DOWN, (EntityList)null);

            if (this.b)
            {
                EnumFacing enumfacing1 = iworldeventlistener.d(blockpos.down()) ? enumfacing : EnumFacing.UP;
                awt awt = blockbone.t().a(BlockSkull.FACING, enumfacing1);
                iworldeventlistener.a(blockpos, awt);
                TileEntityChest tileentitychest = iworldeventlistener.r(blockpos);
                Items items = p_b_2_.a(1);

                if (items.o())
                {
                    ((TileEntitySkull)tileentitychest).e(items.p().getCompoundTag("BlockEntityTag"));
                }

                if (items.t())
                {
                    ((TileEntitySkull)tileentitychest).setCustomName(items.r());
                }

                iworldeventlistener.d(blockpos, awt.u());
            }

            return p_b_2_;
        }
    }
}
