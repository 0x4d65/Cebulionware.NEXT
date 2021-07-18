package ga.ziemniaki.cebulionwarenext.client.modules.target;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class NoArmor2 extends Module {

	public NoArmor2() {
		super("Skip Unarmored Players", Keyboard.KEY_NONE, Category.TARGET);
	}

	@Override
	public boolean isCheckbox() {
		return true;
	}

}
