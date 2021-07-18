package net.minecraft.src;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.spectator.ISpectatorMenuObject;
import net.minecraft.client.gui.spectator.ISpectatorMenuRecipient;
import net.minecraft.client.gui.spectator.categories.SpectatorDetails;
import net.minecraft.client.gui.toasts.AdvancementToast;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.resources.ResourceIndexFolder;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class bow implements ISpectatorMenuRecipient, SpectatorDetails
{
    private final List<SpectatorDetails> a = Lists.<SpectatorDetails>newArrayList();

    public bow()
    {
        GameSettings gamesettings = GameSettings.z();

        for (bhh bhh : gamesettings.viewBobbing.af().g())
        {
            this.a.add(new bow.a(bhh));
        }
    }

    public List<SpectatorDetails> a()
    {
        return this.a;
    }

    public ITextComponent b()
    {
        return new TextComponentTranslation("spectatorMenu.team_teleport.prompt", new Object[0]);
    }

    public void a(ISpectatorMenuObject p_a_1_)
    {
        p_a_1_.a(this);
    }

    public ITextComponent J_()
    {
        return new TextComponentTranslation("spectatorMenu.team_teleport", new Object[0]);
    }

    public void a(float p_a_1_, int p_a_2_)
    {
        GameSettings.z().N().a(AdvancementToast.a);
        ScaledResolution.a(0, 0, 16.0F, 0.0F, 16, 16, 256.0F, 256.0F);
    }

    public boolean K_()
    {
        for (SpectatorDetails spectatordetails : this.a)
        {
            if (spectatordetails.K_())
            {
                return true;
            }
        }

        return false;
    }

    class a implements SpectatorDetails
    {
        private final bhh b;
        private final BehaviorProjectileDispense c;
        private final List<ServerData> d;

        public a(bhh p_i218_2_)
        {
            this.b = p_i218_2_;
            this.d = Lists.<ServerData>newArrayList();

            for (String s : p_i218_2_.d())
            {
                ServerData serverdata = GameSettings.z().v().a(s);

                if (serverdata != null)
                {
                    this.d.add(serverdata);
                }
            }

            if (this.d.isEmpty())
            {
                this.c = ResourceIndexFolder.a();
            }
            else
            {
                String s1 = ((ServerData)this.d.get((new Random()).nextInt(this.d.size()))).a().getName();
                this.c = MovementInputFromOptions.e(s1);
                MovementInputFromOptions.a(this.c, s1);
            }
        }

        public void a(ISpectatorMenuObject p_a_1_)
        {
            p_a_1_.a(new bov(this.d));
        }

        public ITextComponent J_()
        {
            return new TextComponentString(this.b.c());
        }

        public void a(float p_a_1_, int p_a_2_)
        {
            int i = -1;
            String s = Gui.b(this.b.e());

            if (s.length() >= 2)
            {
                i = GameSettings.z().fancyGraphics.b(s.charAt(1));
            }

            if (i >= 0)
            {
                float f = (float)(i >> 16 & 255) / 255.0F;
                float f1 = (float)(i >> 8 & 255) / 255.0F;
                float f2 = (float)(i & 255) / 255.0F;
                ScaledResolution.a(1, 1, 15, 15, IProgressUpdate.b(f * p_a_1_, f1 * p_a_1_, f2 * p_a_1_) | p_a_2_ << 24);
            }

            GameSettings.z().N().a(this.c);
            ItemRenderer.c(p_a_1_, p_a_1_, p_a_1_, (float)p_a_2_ / 255.0F);
            ScaledResolution.a(2, 2, 8.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
            ScaledResolution.a(2, 2, 40.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
        }

        public boolean K_()
        {
            return !this.d.isEmpty();
        }
    }
}
