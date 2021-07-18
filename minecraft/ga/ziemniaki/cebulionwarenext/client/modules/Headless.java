package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.events.UpdateEvent;
import ga.ziemniaki.cebulionwarenext.client.main.Jigsaw;
import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class Headless extends Module {

	public Headless() {
		super("Headless", Keyboard.KEY_NONE, Category.FUN, "Your head is gone for other players!");
	}
	
	@Override
	public void onUpdate(UpdateEvent event) {
		if (Jigsaw.doDisablePacketSwitch()) {
			return;
		}
		event.pitch = 180f;
		super.onUpdate(event);
	}

}
