package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class AutoWalk extends Module {

	public AutoWalk() {
		super("AutoWalk", Keyboard.KEY_NONE, Category.MOVEMENT, "Walks automatically.");
	}

	@Override
	public void onDisable() {

		mc.gameSettings.keyBindForward.unpressKey();
		super.onDisable();
	}

	@Override
	public void onEnable() {

		super.onEnable();
	}

	@Override
	public void onUpdate() {

		mc.gameSettings.keyBindForward.pressed = true;

		super.onUpdate();
	}

}
