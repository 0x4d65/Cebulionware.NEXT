package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.client.tools.Utils;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class XRay extends Module {

	public XRay() {
		super("XRay", Keyboard.KEY_X, Category.RENDER, "Only renders ores");
	}

	@Override
	public void onDisable() {

		mc.gameSettings.gammaSetting = 0.5f;
		mc.renderGlobal.loadRenderers();
		super.onDisable();
	}

	@Override
	public void onEnable() {

		mc.gameSettings.gammaSetting = 100f;
		mc.renderGlobal.loadRenderers();
		super.onEnable();
	}

	@Override
	public void onUpdate() {
		Utils.spectator = true;
		mc.gameSettings.gammaSetting = 100f;
		super.onUpdate();
	}

}
