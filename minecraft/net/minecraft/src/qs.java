package net.minecraft.src;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.block.BlockBone;
import net.minecraft.block.IGrowable;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemLead;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeRepairItem;
import net.minecraft.item.crafting.RecipesMapCloning;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.stats.RecipeBook;
import net.minecraft.stats.RecipeBookServer;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.TupleIntJsonSerializable;
import net.minecraft.util.text.TextComponentTranslation;

public class qs
{
    protected static final Map<String, TupleIntJsonSerializable> a = Maps.<String, TupleIntJsonSerializable>newHashMap();
    public static final List<TupleIntJsonSerializable> b = Lists.<TupleIntJsonSerializable>newArrayList();
    public static final List<TupleIntJsonSerializable> c = Lists.<TupleIntJsonSerializable>newArrayList();
    public static final List<RecipeBookServer> d = Lists.<RecipeBookServer>newArrayList();
    public static final List<RecipeBookServer> e = Lists.<RecipeBookServer>newArrayList();
    public static final TupleIntJsonSerializable f = (new RecipeBook("stat.leaveGame", new TextComponentTranslation("stat.leaveGame", new Object[0]))).c().a();
    public static final TupleIntJsonSerializable g = (new RecipeBook("stat.playOneMinute", new TextComponentTranslation("stat.playOneMinute", new Object[0]), TupleIntJsonSerializable.d)).c().a();
    public static final TupleIntJsonSerializable h = (new RecipeBook("stat.timeSinceDeath", new TextComponentTranslation("stat.timeSinceDeath", new Object[0]), TupleIntJsonSerializable.d)).c().a();
    public static final TupleIntJsonSerializable i = (new RecipeBook("stat.sneakTime", new TextComponentTranslation("stat.sneakTime", new Object[0]), TupleIntJsonSerializable.d)).c().a();
    public static final TupleIntJsonSerializable j = (new RecipeBook("stat.walkOneCm", new TextComponentTranslation("stat.walkOneCm", new Object[0]), TupleIntJsonSerializable.e)).c().a();
    public static final TupleIntJsonSerializable k = (new RecipeBook("stat.crouchOneCm", new TextComponentTranslation("stat.crouchOneCm", new Object[0]), TupleIntJsonSerializable.e)).c().a();
    public static final TupleIntJsonSerializable l = (new RecipeBook("stat.sprintOneCm", new TextComponentTranslation("stat.sprintOneCm", new Object[0]), TupleIntJsonSerializable.e)).c().a();
    public static final TupleIntJsonSerializable m = (new RecipeBook("stat.swimOneCm", new TextComponentTranslation("stat.swimOneCm", new Object[0]), TupleIntJsonSerializable.e)).c().a();
    public static final TupleIntJsonSerializable n = (new RecipeBook("stat.fallOneCm", new TextComponentTranslation("stat.fallOneCm", new Object[0]), TupleIntJsonSerializable.e)).c().a();
    public static final TupleIntJsonSerializable o = (new RecipeBook("stat.climbOneCm", new TextComponentTranslation("stat.climbOneCm", new Object[0]), TupleIntJsonSerializable.e)).c().a();
    public static final TupleIntJsonSerializable p = (new RecipeBook("stat.flyOneCm", new TextComponentTranslation("stat.flyOneCm", new Object[0]), TupleIntJsonSerializable.e)).c().a();
    public static final TupleIntJsonSerializable q = (new RecipeBook("stat.diveOneCm", new TextComponentTranslation("stat.diveOneCm", new Object[0]), TupleIntJsonSerializable.e)).c().a();
    public static final TupleIntJsonSerializable r = (new RecipeBook("stat.minecartOneCm", new TextComponentTranslation("stat.minecartOneCm", new Object[0]), TupleIntJsonSerializable.e)).c().a();
    public static final TupleIntJsonSerializable s = (new RecipeBook("stat.boatOneCm", new TextComponentTranslation("stat.boatOneCm", new Object[0]), TupleIntJsonSerializable.e)).c().a();
    public static final TupleIntJsonSerializable t = (new RecipeBook("stat.pigOneCm", new TextComponentTranslation("stat.pigOneCm", new Object[0]), TupleIntJsonSerializable.e)).c().a();
    public static final TupleIntJsonSerializable u = (new RecipeBook("stat.horseOneCm", new TextComponentTranslation("stat.horseOneCm", new Object[0]), TupleIntJsonSerializable.e)).c().a();
    public static final TupleIntJsonSerializable v = (new RecipeBook("stat.aviateOneCm", new TextComponentTranslation("stat.aviateOneCm", new Object[0]), TupleIntJsonSerializable.e)).c().a();
    public static final TupleIntJsonSerializable w = (new RecipeBook("stat.jump", new TextComponentTranslation("stat.jump", new Object[0]))).c().a();
    public static final TupleIntJsonSerializable x = (new RecipeBook("stat.drop", new TextComponentTranslation("stat.drop", new Object[0]))).c().a();
    public static final TupleIntJsonSerializable y = (new RecipeBook("stat.damageDealt", new TextComponentTranslation("stat.damageDealt", new Object[0]), TupleIntJsonSerializable.f)).a();
    public static final TupleIntJsonSerializable z = (new RecipeBook("stat.damageTaken", new TextComponentTranslation("stat.damageTaken", new Object[0]), TupleIntJsonSerializable.f)).a();
    public static final TupleIntJsonSerializable A = (new RecipeBook("stat.deaths", new TextComponentTranslation("stat.deaths", new Object[0]))).a();
    public static final TupleIntJsonSerializable B = (new RecipeBook("stat.mobKills", new TextComponentTranslation("stat.mobKills", new Object[0]))).a();
    public static final TupleIntJsonSerializable C = (new RecipeBook("stat.animalsBred", new TextComponentTranslation("stat.animalsBred", new Object[0]))).a();
    public static final TupleIntJsonSerializable D = (new RecipeBook("stat.playerKills", new TextComponentTranslation("stat.playerKills", new Object[0]))).a();
    public static final TupleIntJsonSerializable E = (new RecipeBook("stat.fishCaught", new TextComponentTranslation("stat.fishCaught", new Object[0]))).a();
    public static final TupleIntJsonSerializable F = (new RecipeBook("stat.talkedToVillager", new TextComponentTranslation("stat.talkedToVillager", new Object[0]))).a();
    public static final TupleIntJsonSerializable G = (new RecipeBook("stat.tradedWithVillager", new TextComponentTranslation("stat.tradedWithVillager", new Object[0]))).a();
    public static final TupleIntJsonSerializable H = (new RecipeBook("stat.cakeSlicesEaten", new TextComponentTranslation("stat.cakeSlicesEaten", new Object[0]))).a();
    public static final TupleIntJsonSerializable I = (new RecipeBook("stat.cauldronFilled", new TextComponentTranslation("stat.cauldronFilled", new Object[0]))).a();
    public static final TupleIntJsonSerializable J = (new RecipeBook("stat.cauldronUsed", new TextComponentTranslation("stat.cauldronUsed", new Object[0]))).a();
    public static final TupleIntJsonSerializable K = (new RecipeBook("stat.armorCleaned", new TextComponentTranslation("stat.armorCleaned", new Object[0]))).a();
    public static final TupleIntJsonSerializable L = (new RecipeBook("stat.bannerCleaned", new TextComponentTranslation("stat.bannerCleaned", new Object[0]))).a();
    public static final TupleIntJsonSerializable M = (new RecipeBook("stat.brewingstandInteraction", new TextComponentTranslation("stat.brewingstandInteraction", new Object[0]))).a();
    public static final TupleIntJsonSerializable N = (new RecipeBook("stat.beaconInteraction", new TextComponentTranslation("stat.beaconInteraction", new Object[0]))).a();
    public static final TupleIntJsonSerializable O = (new RecipeBook("stat.dropperInspected", new TextComponentTranslation("stat.dropperInspected", new Object[0]))).a();
    public static final TupleIntJsonSerializable P = (new RecipeBook("stat.hopperInspected", new TextComponentTranslation("stat.hopperInspected", new Object[0]))).a();
    public static final TupleIntJsonSerializable Q = (new RecipeBook("stat.dispenserInspected", new TextComponentTranslation("stat.dispenserInspected", new Object[0]))).a();
    public static final TupleIntJsonSerializable R = (new RecipeBook("stat.noteblockPlayed", new TextComponentTranslation("stat.noteblockPlayed", new Object[0]))).a();
    public static final TupleIntJsonSerializable S = (new RecipeBook("stat.noteblockTuned", new TextComponentTranslation("stat.noteblockTuned", new Object[0]))).a();
    public static final TupleIntJsonSerializable T = (new RecipeBook("stat.flowerPotted", new TextComponentTranslation("stat.flowerPotted", new Object[0]))).a();
    public static final TupleIntJsonSerializable U = (new RecipeBook("stat.trappedChestTriggered", new TextComponentTranslation("stat.trappedChestTriggered", new Object[0]))).a();
    public static final TupleIntJsonSerializable V = (new RecipeBook("stat.enderchestOpened", new TextComponentTranslation("stat.enderchestOpened", new Object[0]))).a();
    public static final TupleIntJsonSerializable W = (new RecipeBook("stat.itemEnchanted", new TextComponentTranslation("stat.itemEnchanted", new Object[0]))).a();
    public static final TupleIntJsonSerializable X = (new RecipeBook("stat.recordPlayed", new TextComponentTranslation("stat.recordPlayed", new Object[0]))).a();
    public static final TupleIntJsonSerializable Y = (new RecipeBook("stat.furnaceInteraction", new TextComponentTranslation("stat.furnaceInteraction", new Object[0]))).a();
    public static final TupleIntJsonSerializable Z = (new RecipeBook("stat.craftingTableInteraction", new TextComponentTranslation("stat.workbenchInteraction", new Object[0]))).a();
    public static final TupleIntJsonSerializable aa = (new RecipeBook("stat.chestOpened", new TextComponentTranslation("stat.chestOpened", new Object[0]))).a();
    public static final TupleIntJsonSerializable ab = (new RecipeBook("stat.sleepInBed", new TextComponentTranslation("stat.sleepInBed", new Object[0]))).a();
    public static final TupleIntJsonSerializable ac = (new RecipeBook("stat.shulkerBoxOpened", new TextComponentTranslation("stat.shulkerBoxOpened", new Object[0]))).a();
    private static final TupleIntJsonSerializable[] ad = new TupleIntJsonSerializable[4096];
    private static final TupleIntJsonSerializable[] ae = new TupleIntJsonSerializable[32000];
    private static final TupleIntJsonSerializable[] af = new TupleIntJsonSerializable[32000];
    private static final TupleIntJsonSerializable[] ag = new TupleIntJsonSerializable[32000];
    private static final TupleIntJsonSerializable[] ah = new TupleIntJsonSerializable[32000];
    private static final TupleIntJsonSerializable[] ai = new TupleIntJsonSerializable[32000];

    @Nullable
    public static TupleIntJsonSerializable a(BlockBone p_a_0_)
    {
        return ad[BlockBone.a(p_a_0_)];
    }

    @Nullable
    public static TupleIntJsonSerializable a(ItemStack p_a_0_)
    {
        return ae[ItemStack.a(p_a_0_)];
    }

    @Nullable
    public static TupleIntJsonSerializable b(ItemStack p_b_0_)
    {
        return af[ItemStack.a(p_b_0_)];
    }

    @Nullable
    public static TupleIntJsonSerializable c(ItemStack p_c_0_)
    {
        return ag[ItemStack.a(p_c_0_)];
    }

    @Nullable
    public static TupleIntJsonSerializable d(ItemStack p_d_0_)
    {
        return ah[ItemStack.a(p_d_0_)];
    }

    @Nullable
    public static TupleIntJsonSerializable e(ItemStack p_e_0_)
    {
        return ai[ItemStack.a(p_e_0_)];
    }

    public static void a()
    {
        c();
        d();
        e();
        b();
        f();
    }

    private static void b()
    {
        Set<ItemStack> set = Sets.<ItemStack>newHashSet();

        for (RecipeRepairItem reciperepairitem : ShapedRecipes.recipeWidth)
        {
            Items items = reciperepairitem.b();

            if (!items.b())
            {
                set.add(reciperepairitem.b().c());
            }
        }

        for (Items items1 : RecipesMapCloning.a().b().values())
        {
            set.add(items1.c());
        }

        for (ItemStack itemstack : set)
        {
            if (itemstack != null)
            {
                int i = ItemStack.a(itemstack);
                String s = f(itemstack);

                if (s != null)
                {
                    ae[i] = (new RecipeBookServer("stat.craftItem.", s, new TextComponentTranslation("stat.craftItem", new Object[] {(new Items(itemstack)).C()}), itemstack)).a();
                }
            }
        }

        a(ae);
    }

    private static void c()
    {
        for (BlockBone blockbone : BlockBone.h)
        {
            ItemStack itemstack = ItemStack.a(blockbone);

            if (itemstack != ItemLead.a)
            {
                int i = BlockBone.a(blockbone);
                String s = f(itemstack);

                if (s != null && blockbone.o())
                {
                    ad[i] = (new RecipeBookServer("stat.mineBlock.", s, new TextComponentTranslation("stat.mineBlock", new Object[] {(new Items(blockbone)).C()}), itemstack)).a();
                    e.add((RecipeBookServer)ad[i]);
                }
            }
        }

        a(ad);
    }

    private static void d()
    {
        for (ItemStack itemstack : ItemStack.field_190928_g)
        {
            if (itemstack != null)
            {
                int i = ItemStack.a(itemstack);
                String s = f(itemstack);

                if (s != null)
                {
                    af[i] = (new RecipeBookServer("stat.useItem.", s, new TextComponentTranslation("stat.useItem", new Object[] {(new Items(itemstack)).C()}), itemstack)).a();

                    if (!(itemstack instanceof ItemBoat))
                    {
                        d.add((RecipeBookServer)af[i]);
                    }
                }
            }
        }

        a(af);
    }

    private static void e()
    {
        for (ItemStack itemstack : ItemStack.field_190928_g)
        {
            if (itemstack != null)
            {
                int i = ItemStack.a(itemstack);
                String s = f(itemstack);

                if (s != null && itemstack.m())
                {
                    ag[i] = (new RecipeBookServer("stat.breakItem.", s, new TextComponentTranslation("stat.breakItem", new Object[] {(new Items(itemstack)).C()}), itemstack)).a();
                }
            }
        }

        a(ag);
    }

    private static void f()
    {
        for (ItemStack itemstack : ItemStack.field_190928_g)
        {
            if (itemstack != null)
            {
                int i = ItemStack.a(itemstack);
                String s = f(itemstack);

                if (s != null)
                {
                    ah[i] = (new RecipeBookServer("stat.pickup.", s, new TextComponentTranslation("stat.pickup", new Object[] {(new Items(itemstack)).C()}), itemstack)).a();
                    ai[i] = (new RecipeBookServer("stat.drop.", s, new TextComponentTranslation("stat.drop", new Object[] {(new Items(itemstack)).C()}), itemstack)).a();
                }
            }
        }

        a(ag);
    }

    private static String f(ItemStack p_f_0_)
    {
        BehaviorProjectileDispense behaviorprojectiledispense = ItemStack.field_190928_g.getNameForObject(p_f_0_);
        return behaviorprojectiledispense != null ? behaviorprojectiledispense.toString().replace(':', '.') : null;
    }

    private static void a(TupleIntJsonSerializable[] p_a_0_)
    {
        a(p_a_0_, IGrowable.j, IGrowable.i);
        a(p_a_0_, IGrowable.l, IGrowable.k);
        a(p_a_0_, IGrowable.aZ, IGrowable.aU);
        a(p_a_0_, IGrowable.am, IGrowable.al);
        a(p_a_0_, IGrowable.aD, IGrowable.aC);
        a(p_a_0_, IGrowable.bc, IGrowable.bb);
        a(p_a_0_, IGrowable.ck, IGrowable.cj);
        a(p_a_0_, IGrowable.aF, IGrowable.aE);
        a(p_a_0_, IGrowable.bK, IGrowable.bJ);
        a(p_a_0_, IGrowable.T, IGrowable.U);
        a(p_a_0_, IGrowable.bL, IGrowable.bM);
        a(p_a_0_, IGrowable.cO, IGrowable.cP);
        a(p_a_0_, IGrowable.c, IGrowable.d);
        a(p_a_0_, IGrowable.ak, IGrowable.d);
    }

    private static void a(TupleIntJsonSerializable[] p_a_0_, BlockBone p_a_1_, BlockBone p_a_2_)
    {
        int i = BlockBone.a(p_a_1_);
        int j = BlockBone.a(p_a_2_);

        if (p_a_0_[i] != null && p_a_0_[j] == null)
        {
            p_a_0_[j] = p_a_0_[i];
        }
        else
        {
            b.remove(p_a_0_[i]);
            e.remove(p_a_0_[i]);
            c.remove(p_a_0_[i]);
            p_a_0_[i] = p_a_0_[j];
        }
    }

    public static TupleIntJsonSerializable a(EntitySelectors.ArmoredMob p_a_0_)
    {
        String s = EntitySelectors.a(p_a_0_.armor);
        return s == null ? null : (new TupleIntJsonSerializable("stat.killEntity." + s, new TextComponentTranslation("stat.entityKill", new Object[] {new TextComponentTranslation("entity." + s + ".name", new Object[0])}))).a();
    }

    public static TupleIntJsonSerializable b(EntitySelectors.ArmoredMob p_b_0_)
    {
        String s = EntitySelectors.a(p_b_0_.armor);
        return s == null ? null : (new TupleIntJsonSerializable("stat.entityKilledBy." + s, new TextComponentTranslation("stat.entityKilledBy", new Object[] {new TextComponentTranslation("entity." + s + ".name", new Object[0])}))).a();
    }

    @Nullable
    public static TupleIntJsonSerializable a(String p_a_0_)
    {
        return a.get(p_a_0_);
    }
}
