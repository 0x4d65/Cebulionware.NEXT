package net.minecraft.src;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.spectator.ISpectatorMenuObject;
import net.minecraft.client.gui.spectator.ISpectatorMenuRecipient;
import net.minecraft.client.gui.spectator.SpectatorMenu;
import net.minecraft.client.gui.spectator.categories.SpectatorDetails;
import net.minecraft.client.gui.toasts.AdvancementToast;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class bov implements ISpectatorMenuRecipient, SpectatorDetails
{
    private static final Ordering<ServerData> a = Ordering.from(new Comparator<ServerData>()
    {
        public int a(ServerData p_a_1_, ServerData p_a_2_)
        {
            return ComparisonChain.start().compare(p_a_1_.a().getId(), p_a_2_.a().getId()).result();
        }
    });
    private final List<SpectatorDetails> b;

    public bov()
    {
        this(a.sortedCopy(GameSettings.z().v().d()));
    }

    public bov(Collection<ServerData> p_i219_1_)
    {
        this.b = Lists.<SpectatorDetails>newArrayList();

        for (ServerData serverdata : a.sortedCopy(p_i219_1_))
        {
            if (serverdata.b() != World.loadedEntityList)
            {
                this.b.add(new SpectatorMenu(serverdata.a()));
            }
        }
    }

    public List<SpectatorDetails> a()
    {
        return this.b;
    }

    public ITextComponent b()
    {
        return new TextComponentTranslation("spectatorMenu.teleport.prompt", new Object[0]);
    }

    public void a(ISpectatorMenuObject p_a_1_)
    {
        p_a_1_.a(this);
    }

    public ITextComponent J_()
    {
        return new TextComponentTranslation("spectatorMenu.teleport", new Object[0]);
    }

    public void a(float p_a_1_, int p_a_2_)
    {
        GameSettings.z().N().a(AdvancementToast.a);
        ScaledResolution.a(0, 0, 0.0F, 0.0F, 16, 16, 256.0F, 256.0F);
    }

    public boolean K_()
    {
        return !this.b.isEmpty();
    }
}
