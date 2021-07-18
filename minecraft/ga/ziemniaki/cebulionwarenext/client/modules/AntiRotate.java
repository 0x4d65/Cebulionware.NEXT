package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.events.PacketEvent;
import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketPlayerPosLook;

public class AntiRotate extends Module {

	public AntiRotate() {
		super("AntiRotate", Keyboard.KEY_NONE, Category.MOVEMENT, "Prevents the server from controlling your rotation.");
	}

	@Override
	public void onPacketRecieved(PacketEvent event) {
		Packet packetIn = event.getPacket();
		if(mc.player != null) {
			if (packetIn instanceof SPacketPlayerPosLook) {
				SPacketPlayerPosLook packet = (SPacketPlayerPosLook) packetIn;
				packet.yaw = mc.player.rotationYawHead;
				packet.pitch = mc.player.rotationPitch;
				packetIn = packet;
			}
		}
		super.onPacketRecieved(event);
	}

}
