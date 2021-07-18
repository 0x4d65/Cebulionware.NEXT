package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.ModSetting;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.SliderSetting;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.ValueFormat;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class NameTags extends Module {
	@Override
	public ModSetting[] getModSettings() {
		// nameTagsSlider

//		Slider nameTagsSlider = new BasicSlider("Size", ClientSettings.Nametagssize, 2.0, 10.0, 0.0,
//				ValueDisplay.INTEGER);
//
//		nameTagsSlider.addSliderListener(new SliderListener() {
//
//			@Override
//			public void onSliderValueChanged(Slider slider) {
//
//				ClientSettings.Nametagssize = (float) (slider.getValue());
//
//			}
//		});
		SliderSetting nameTagsSlider = new SliderSetting("Tag Size", "Nametagssize", 2.0, 10.0, 0.0, ValueFormat.DECIMAL);
		return new ModSetting[] { nameTagsSlider };
	}

	public NameTags() {
		super("NameTags", Keyboard.KEY_NONE, Category.RENDER, "Changes nametags.");
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}

	@Override
	public void onUpdate() {

		super.onUpdate();
	}

}
