package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class LiquidInteract extends Module {

	public LiquidInteract() {
		super("LiquidInteract", Keyboard.KEY_NONE, Category.WORLD, "Allows you to place blocks on liquids!");
	}

}
