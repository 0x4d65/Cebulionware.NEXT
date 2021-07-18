package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.client.settings.ClientSettings;
import ga.ziemniaki.cebulionwarenext.client.tools.Utils;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.ModSetting;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.SliderSetting;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.ValueFormat;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class Timer extends Module {

	@Override
	public ModSetting[] getModSettings() {
		// timerspeedslider

//		Slider timerSpeedSlider = new BasicSlider("Timer Speed", Timer.getTimer(), 0.05, 10.0, 0.0,
//				ValueDisplay.DECIMAL);
//
//		timerSpeedSlider.addSliderListener(new SliderListener() {
//
//			@Override
//			public void onSliderValueChanged(Slider slider) {
//
//				Timer.setTimer(slider.getValue());
//
//			}
//		});
		SliderSetting timerSpeedSlider = new SliderSetting("Timer Speed", "Timerspeed", 0.1, 10.0, 0.0, ValueFormat.DECIMAL);
		return new ModSetting[] { timerSpeedSlider };
	}

	public Timer() {
		super("Timer", Keyboard.KEY_NONE, Category.WORLD, "Speeds up or slows down minecraft.");
	}

	@Override
	public void onDisable() {

		Utils.resetMcTimerTPS();

		super.onDisable();
	}

	@Override
	public void onEnable() {

		super.onEnable();
	}

	@Override
	public void onUpdate() {
		mc.timer.tickLength = 1000f / (20 * (float) ClientSettings.Timerspeed);
		super.onUpdate();
	}

	public static double getTimer() {
		return ClientSettings.Timerspeed;
	}

	public static void setTimerSpeed(double set) {
		ClientSettings.Timerspeed = set;
	}

}
