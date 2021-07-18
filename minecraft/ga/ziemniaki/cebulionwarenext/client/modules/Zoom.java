package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class Zoom extends Module {

	float fovbefore;

	public Zoom() {
		super("Zoom", Keyboard.KEY_Z, Category.RENDER);
	}

	@Override
	public void onDisable() {

		mc.gameSettings.fovSetting = fovbefore;

		super.onDisable();
	}

	@Override
	public void onEnable() {

		fovbefore = mc.gameSettings.fovSetting;

		mc.gameSettings.fovSetting = 15;

		super.onEnable();
	}

	@Override
	public void onUpdate() {

		super.onUpdate();
	}

}
