package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class Fullbright extends Module {
	private float gammabefore = 0.5f;

	public Fullbright() {
		super("Fullbright", Keyboard.KEY_NONE, Category.RENDER, "Makes everything bright.");
	}

	@Override
	public void onDisable() {
		mc.gameSettings.gammaSetting = gammabefore;
		super.onDisable();
	}

	@Override
	public void onEnable() {
		super.onEnable();
	}

	@Override
	public void onUpdate() {

		mc.gameSettings.gammaSetting = 100;
		
		super.onUpdate();
	}

}
