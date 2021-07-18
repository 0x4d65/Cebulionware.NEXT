package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.main.Jigsaw;
import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.console.GuiJigsawConsole;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class Console extends Module {

	public Console() {
		super("Console", Keyboard.KEY_U, Category.GLOBAL, "Opens the Console");
	}
	
	@Override
	public void onEnable() {
		if(!(mc.currentScreen instanceof GuiJigsawConsole) && !Jigsaw.ghostMode) {
			mc.displayGuiScreen(new GuiJigsawConsole());
		}
		setToggled(false, true);
		super.onEnable();
	}

}
