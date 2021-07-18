package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.events.UpdateEvent;
import ga.ziemniaki.cebulionwarenext.client.main.Jigsaw;
import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.client.settings.ClientSettings;
import ga.ziemniaki.cebulionwarenext.client.tools.Utils;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.ModSetting;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.SliderSetting;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.ValueFormat;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class Spin extends Module {

	private float spin = 0;

	@Override
	public ModSetting[] getModSettings() {
//		BasicSlider slider1 = new BasicSlider("Spin Speed", ClientSettings.Spinspeed, 10, 180, 0, ValueDisplay.DECIMAL);
//		slider1.addSliderListener(new SliderListener() {
//			@Override
//			public void onSliderValueChanged(Slider slider) {
//				ClientSettings.Spinspeed = slider.getValue();
//			}
//		});
		SliderSetting slider1 = new SliderSetting("Spin Speed", "Spinspeed", 10, 180, 0.0, ValueFormat.DECIMAL);
		return new ModSetting[] { slider1 };
	}

	public Spin() {
		super("Spin", Keyboard.KEY_NONE, Category.FUN, "You do 360s for other players!");
	}
	
	@Override
	public void onUpdate(UpdateEvent event) {
		if(Jigsaw.doDisablePacketSwitch()) {
			return;
		}
		spin += ClientSettings.Spinspeed;
		spin = Utils.normalizeAngle(spin);
		event.yaw = spin;
		super.onUpdate(event);
	}

}
