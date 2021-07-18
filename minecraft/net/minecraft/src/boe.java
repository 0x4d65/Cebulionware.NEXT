package net.minecraft.src;

import java.util.List;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.resources.ResourcePackListEntryFound;
import net.minecraft.client.settings.GameSettings;

public class boe extends bod
{
    public boe(GameSettings p_i161_1_, int p_i161_2_, int p_i161_3_, List<ResourcePackListEntryFound> p_i161_4_)
    {
        super(p_i161_1_, p_i161_2_, p_i161_3_, p_i161_4_);
    }

    protected String e()
    {
        return LanguageManager.a("resourcePack.selected.title");
    }
}
