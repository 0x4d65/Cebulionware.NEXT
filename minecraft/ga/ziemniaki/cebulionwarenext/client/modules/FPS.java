package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.main.Jigsaw;
import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.gui.ScreenPos;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class FPS extends Module {

	public FPS() {
		super("FPS", Keyboard.KEY_NONE, Category.PLAYER, "Shows your FPS.");
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}

	@Override
	public void onUpdate() {

		super.onUpdate();
	}

	@Override
	public void onGui() {

		Jigsaw.getUIManager().addToQueue(String.valueOf("§jFPS: §r" + mc.getDebugFPS()), ScreenPos.LEFTUP);

		super.onGui();
	}

}
