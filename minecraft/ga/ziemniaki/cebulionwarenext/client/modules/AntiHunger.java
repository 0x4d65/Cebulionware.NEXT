package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.events.PacketEvent;
import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;

public class AntiHunger extends Module {
	
	public AntiHunger() {
		super("AntiHunger", Keyboard.KEY_NONE, Category.PLAYER, "Note: Requires you to stand still! Makes you less hungry and makes potions stay longer. Also enables you to stay in water for longer. It disables natural regen.");
	}
	
	@Override
	public void onPacketSent(PacketEvent event) {
		Packet packet = event.getPacket();
		if(packet instanceof CPacketPlayer
				&& !mc.player.isHandActive()) {
			event.cancel();
		}
		super.onPacketSent(event);
	}
	
}
