package ga.ziemniaki.cebulionwarenext.client.events;

import net.minecraft.network.Packet;

public class PacketEvent extends Event {
	
	private Packet packet;
	
	public PacketEvent(Packet packet) {
		this.packet = packet;
	}
	
	public Packet getPacket() {
		return packet;
	}
	
}
