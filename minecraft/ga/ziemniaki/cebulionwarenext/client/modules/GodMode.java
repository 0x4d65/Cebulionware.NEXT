package ga.ziemniaki.cebulionwarenext.client.modules;

import ga.ziemniaki.cebulionwarenext.client.events.UpdateEvent;
import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;
import net.minecraft.client.gui.GuiGameOver;

public class GodMode extends Module {

	public static boolean enabled = false;

	public GodMode() {
		super("GodMode", 0, Category.EXPLOITS, "Makes you invisible and invincible when you die on a vanilla server.");
		enabled = false;
	}
	
	@Override
	public void onToggle() {
		enabled = false;
		super.onToggle();
	}
	
	@Override
	public void onUpdate(UpdateEvent event) {
		if(mc.player.getHealth() <= 0 || enabled) {
			enabled = true;
			mc.player.setHealth(mc.player.getMaxHealth());
			if(mc.currentScreen instanceof GuiGameOver) {
				mc.currentScreen = null;
			}
		}
		super.onUpdate(event);
	}
	
}