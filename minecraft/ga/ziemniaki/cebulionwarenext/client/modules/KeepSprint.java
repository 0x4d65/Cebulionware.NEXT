package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.events.PacketEvent;
import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class KeepSprint extends Module {

	public KeepSprint() {
		super("KeepSprint", Keyboard.KEY_NONE, Category.MOVEMENT, "Allows you to keep sprinting after you hit someone.");
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
	@Override
	public void onPacketRecieved(PacketEvent event) {
		
		super.onPacketRecieved(event);
	}
}
