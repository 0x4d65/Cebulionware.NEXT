package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.client.settings.ClientSettings;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.ModSetting;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.SliderSetting;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.ValueFormat;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class MultiUse extends Module {
	@Override
	public ModSetting[] getModSettings() {
		SliderSetting slider1 = new SliderSetting("Amount", "MultiUseamount", 1, 1000, ValueFormat.INT);
		return new ModSetting[] { slider1 };
	}

	public MultiUse() {
		super("MultiUse", Keyboard.KEY_NONE, Category.FUN, "When you use something, it uses it again many times.");
	}

	@Override
	public void onRightClick() {
		for (int i = 0; i < ClientSettings.MultiUseamount; i++) {
			mc.rightClickMouse();
		}
		super.onRightClick();
	}

}
