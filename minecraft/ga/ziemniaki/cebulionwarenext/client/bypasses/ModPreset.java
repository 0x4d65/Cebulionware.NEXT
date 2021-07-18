package ga.ziemniaki.cebulionwarenext.client.bypasses;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.main.Jigsaw;
import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.gui.Level;
import ga.ziemniaki.cebulionwarenext.gui.Notification;
import ga.ziemniaki.cebulionwarenext.module.Module;

public abstract class ModPreset {
	
	public void onEnable() {
		
		Jigsaw.disableModules(Jigsaw.getModulesByCategories(Jigsaw.getModules(), Jigsaw.defaultCategoriesWithTarget));
		
		Jigsaw.getModuleByName("FPS").setToggled(true, true);
		Jigsaw.getModuleByName("Coords").setToggled(true, true);
		Jigsaw.getModuleByName("TPS").setToggled(true, true);
		
		
		//Jigsaw.getGUIMananger().reloadSettingScreens();
		
		Jigsaw.getNotificationManager().addNotification(new Notification(Level.INFO, "[" + getName() + "] - Disabled all bannable mods and changed all settings to bypass!"));
	}
	
	public String getName() {
		return null;
	}
	
	public Module createModule() {
		if(this.getName() == null) {
			return null;
		}
		return new Module(this.getName(), Keyboard.KEY_NONE, Category.PRESETS) {
			
			@Override
			public void onEnable() {
				ModPreset.this.onEnable();
				super.onEnable();
				this.setToggled(false, true);
			}
			
		};
	}
	
}
