package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.ModSetting;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.SliderSetting;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.ValueFormat;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class ExtendedReach extends Module {

	@Override
	public ModSetting[] getModSettings() {
		// reach range slider

//		Slider reachRangeSlider = new BasicSlider("Extendedreach Range", ClientSettings.ExtendedReachrange, 3.5, 7.0,
//				0.0, ValueDisplay.DECIMAL);
//
//		reachRangeSlider.addSliderListener(new SliderListener() {
//
//			@Override
//			public void onSliderValueChanged(Slider slider) {
//
//				ClientSettings.ExtendedReachrange = (float) slider.getValue();
//
//			}
//		});
		SliderSetting reachRangeSlider = new SliderSetting("Reach", "ExtendedReachrange", 3.5, 7.0, 0.0, ValueFormat.DECIMAL);
		return new ModSetting[] { reachRangeSlider };
	}

	public ExtendedReach() {
		super("ExtendedReach", Keyboard.KEY_NONE, Category.COMBAT,
				"Enables you to place blocks and hit entites further away. Warning! Don't mine blocks more than 5 blocks away!");
	}

	@Override
	public void onDisable() {

		super.onDisable();
	}

	@Override
	public void onEnable() {

		super.onEnable();
	}

	@Override
	public void onUpdate() {

		super.onUpdate();
	}

}
