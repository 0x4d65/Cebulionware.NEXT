package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.main.Jigsaw;
import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.gui.ScreenPos;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class Coords extends Module {

	public Coords() {
		super("Coords", Keyboard.KEY_NONE, Category.PLAYER, "Displays your coordinates.");
	}

	@Override
	public void onGui() {

		Jigsaw.getUIManager().addToQueue(String.valueOf("§jX: §r" + (int) mc.player.posX), ScreenPos.LEFTUP);
		Jigsaw.getUIManager().addToQueue(String.valueOf("§jY: §r" + (int) mc.player.posY), ScreenPos.LEFTUP);
		Jigsaw.getUIManager().addToQueue(String.valueOf("§jZ: §r" + (int) mc.player.posZ), ScreenPos.LEFTUP);

		super.onGui();
	}

}
