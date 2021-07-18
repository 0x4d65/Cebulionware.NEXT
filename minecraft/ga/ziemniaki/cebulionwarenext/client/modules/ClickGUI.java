package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.main.Jigsaw;
import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.DisplayClickGui;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class ClickGUI extends Module {

	public ClickGUI() {
		super("ClickGUI", Keyboard.KEY_LCONTROL, Category.HIDDEN, "Well, this is kind of self explanatory.");
	}

	@Override
	public void onDisable() {

		super.onDisable();
	}

	@Override
	public void onEnable() {

//		if (!(mc.currentScreen instanceof GuiManagerDisplayScreen) && !Jigsaw.ghostMode) {
//			mc.displayGuiScreen(new GuiManagerDisplayScreen(Jigsaw.getGUIMananger()));
//		}
		if(!(mc.currentScreen instanceof DisplayClickGui) && !Jigsaw.ghostMode) {
			mc.displayGuiScreen(new DisplayClickGui(false));
		}
		setToggled(false, true);
		super.onEnable();
	}

	@Override
	public void onRender() {

	}
}
