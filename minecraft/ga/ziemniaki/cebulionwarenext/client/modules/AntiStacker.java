package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class AntiStacker extends Module {
	
	int timer;

	public AntiStacker() {
		super("AntiStacker", Keyboard.KEY_NONE, Category.PLAYER,
				"Automatically dismounts from the entity you are riding.");
	}

	@Override
	public void onEnable() {
		timer = 4;
		super.onEnable();
	}

	@Override
	public void onUpdate() {
		if (timer < 4) {
			timer++;
			if (timer >= 1) {
				mc.gameSettings.keyBindSneak.unpressKey();
			}
			return;
		}
		if (mc.player.isRiding()) {
			mc.gameSettings.keyBindSneak.pressed = true;
			timer = 0;
		}
		super.onUpdate();
	}

}
