package ga.ziemniaki.cebulionwarenext.client.modules.target;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;
import net.minecraft.entity.EntityLivingBase;

public class Team extends Module {

	public Team() {
		super("Team", Keyboard.KEY_NONE, Category.TARGET);
	}
	
	public static boolean isOnTeam(EntityLivingBase en) {
		if(mc.player.getDisplayName().getUnformattedText().startsWith("§")) {
			if(mc.player.getDisplayName().getUnformattedText().length() <= 2
					|| en.getDisplayName().getUnformattedText().length() <= 2) {
				return false;
			}
			if(mc.player.getDisplayName().getUnformattedText().substring(0, 2).equals(en.getDisplayName().getUnformattedText().substring(0, 2))) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean isCheckbox() {
		return true;
	}
}
