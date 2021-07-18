package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class AutoParkour extends Module {

	public AutoParkour() {
		super("AutoParkour", Keyboard.KEY_NONE, Category.AI,
				"Tries to jump to a block automatically. (Select block by right clicking it)");
	}

}
