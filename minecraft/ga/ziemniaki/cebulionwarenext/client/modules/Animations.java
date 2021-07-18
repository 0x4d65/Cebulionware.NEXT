package ga.ziemniaki.cebulionwarenext.client.modules;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.CheckBtnSetting;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.ModSetting;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class Animations extends Module {

	public Animations() {
		super("Animations", 0, Category.FUN, "Changes some animations.");
	}
	
	@Override
	public ModSetting[] getModSettings() {
		CheckBtnSetting box1 = new CheckBtnSetting("Swing Animation", "swingAnimation");
		CheckBtnSetting box2 = new CheckBtnSetting("Better Bobbing", "betterBobbing");
		return new ModSetting[]{box1, box2};
	}

	public void onToggle() {

		super.onToggle();
	}
}
