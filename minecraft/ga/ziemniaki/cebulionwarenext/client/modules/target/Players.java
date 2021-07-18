package ga.ziemniaki.cebulionwarenext.client.modules.target;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class Players extends Module {

	public Players() {
		super("Players", Keyboard.KEY_NONE, Category.TARGET);
	}

	@Override
	public boolean isCheckbox() {
		return true;
	}
}
