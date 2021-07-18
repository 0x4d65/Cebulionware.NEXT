package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class AutoJump extends Module {

	public AutoJump() {
		super("AutoJump", Keyboard.KEY_NONE, Category.MOVEMENT, "Jumps all the time.");
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

		if (mc.player.onGround) {
			mc.player.jump();
		}

		super.onUpdate();
	}

}
