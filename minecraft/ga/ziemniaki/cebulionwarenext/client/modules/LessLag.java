package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class LessLag extends Module {

	public LessLag() {
		super("LessLag", Keyboard.KEY_NONE, Category.WORLD, "Disables the client from printing unneccesary stack traces and thus saving processing time. I know, what you just read sounded dank af.");
	}

}
