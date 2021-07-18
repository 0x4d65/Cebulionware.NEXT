package ga.ziemniaki.cebulionwarenext.client.modules.target;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.events.UpdateEvent;
import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class MAC_Bypass extends Module {

	public MAC_Bypass() {
		super("AntiBot(Gwen)", Keyboard.KEY_NONE, Category.TARGET, "Tries to not hit the fake player on Mineplex.");
	}

	@Override
	public boolean isCheckbox() {
		return true;
	}
	
	@Override
	public void onUpdate(UpdateEvent event) {
		super.onUpdate(event);
		
		
	}

}
