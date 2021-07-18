package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.CheckBtnSetting;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.ModSetting;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class Notifications extends Module {

	@Override
	public ModSetting[] getModSettings() {
		CheckBtnSetting box1 = new CheckBtnSetting("On Hack Enabled", "notificationModulesEnable");
		CheckBtnSetting box2 = new CheckBtnSetting("On Hack Disable", "notificationModulesDisable");
		return new ModSetting[]{box1, box2};
	}
	
	public Notifications() {
		super("Notifications", Keyboard.KEY_NONE, Category.GLOBAL);
	}

}
