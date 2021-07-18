package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;
import net.minecraft.client.gui.GuiScreen;

public class AutoRespawn extends Module {

	public AutoRespawn() {
		super("AutoRespawn", Keyboard.KEY_NONE, Category.COMBAT, "Respawns automatically when you die.");
	}

	@Override
	public void onDisable() {

		super.onDisable();
	}

	@Override
	public void onEnable() {

		super.onEnable();
	}

	@Override
	public void onUpdate() {

		if (mc.player.isDead) {
			mc.player.respawnPlayer();
			mc.displayGuiScreen((GuiScreen) null);
		}

		super.onUpdate();
	}

}
