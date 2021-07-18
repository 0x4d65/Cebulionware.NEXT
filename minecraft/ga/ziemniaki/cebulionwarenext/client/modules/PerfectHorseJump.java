package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class PerfectHorseJump extends Module {

	public PerfectHorseJump() {
		super("PerfectHorseJump", Keyboard.KEY_NONE, Category.MOVEMENT, "Your horse gets a perfect jump all the time.");
	}

}
