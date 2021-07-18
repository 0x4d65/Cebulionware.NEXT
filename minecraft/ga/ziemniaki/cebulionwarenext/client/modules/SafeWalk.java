package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class SafeWalk extends Module {

	public SafeWalk() {
		super("SafeWalk", Keyboard.KEY_NONE, Category.MOVEMENT, "Prevents you from falling from blocks, just like if you were sneaking.");
	}

}
