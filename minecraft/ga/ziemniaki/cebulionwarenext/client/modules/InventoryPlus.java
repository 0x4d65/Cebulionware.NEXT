package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.events.PacketEvent;
import ga.ziemniaki.cebulionwarenext.client.main.Jigsaw;
import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketCloseWindow;

public class InventoryPlus extends Module {

	public InventoryPlus() {
		super("Inventory+", Keyboard.KEY_NONE, Category.PLAYER,
				"Enables you to use your crafting area as inventory.");
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
	public void onPacketSent(PacketEvent event) {
		Packet packet = event.getPacket();
		if (packet instanceof CPacketCloseWindow) {
			event.cancel();
		}
		super.onPacketSent(event);
	}

	public static boolean shouldCancel() {
		return Jigsaw.getModuleByName("Inventory+").isToggled();
	}

}
