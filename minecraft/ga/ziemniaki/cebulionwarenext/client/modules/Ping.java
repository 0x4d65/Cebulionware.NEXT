package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.WaitTimer;
import ga.ziemniaki.cebulionwarenext.client.events.PacketEvent;
import ga.ziemniaki.cebulionwarenext.client.events.UpdateEvent;
import ga.ziemniaki.cebulionwarenext.client.main.Jigsaw;
import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.gui.ScreenPos;
import ga.ziemniaki.cebulionwarenext.module.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;

public class Ping extends Module {

	public static WaitTimer timer = new WaitTimer();
	
	public static long lastTime;

	public Ping() {
		super("Ping", Keyboard.KEY_NONE, Category.HIDDEN);
	}

	@Override
	public void onToggle() {
		timer.reset();
		super.onToggle();
	}

	@Override
	public void onPacketRecieved(PacketEvent event) {
		Packet packetIn = event.getPacket();
		if(packetIn instanceof CPacketChatMessage) {
			return;
		}
		lastTime = timer.getTime();
		timer.reset();
		super.onPacketRecieved(event);
	}

	@Override
	public void onGui() {
		if(mc.isSingleplayer()) {
			return;
		}
		if (timer.getTime() > 1000) {
			Jigsaw.getUIManager().addToQueue(String.valueOf("§jPing: §4§l" + timer.getTime()), ScreenPos.LEFTUP);
		}
		super.onGui();
	}
	
	@Override
	public void onUpdate(UpdateEvent event) {
		
		for(EntityPlayer player : mc.world.playerEntities) {
			
			if(player instanceof EntityPlayerSP) {
				continue;
			}
			
			if(player.getName().equals("JigsawDev")) {
//				System.out.println(player.predictionError);
			}
		}
		
		super.onUpdate(event);
	}
	
	@Override
	public void onRender() {
		super.onRender();
		
//		for(EntityPlayer player : mc.world.playerEntities) {
//			
//			if(player instanceof EntityPlayerSP) {
//				continue;
//			}
//			
//			double x = player.predictedPosX;
//			
//			double y = player.predictedPosY;
//			
//			double z = player.predictedPosZ;
//			
//			Vec3 vec = RenderTools.getRenderPos(x, y, z);
//			
//			double xPos = vec.x;
//			double yPos = vec.y;
//			double zPos = vec.z;
//			
//			RenderTools.drawSolidEntityESP(xPos, yPos, zPos, player.width / 2, player.height, 0.8f, 0.1f, 0.1f, 0.2f);
//			RenderTools.drawOutlinedEntityESP(xPos, yPos, zPos, player.width / 2, player.height, 0.8f, 0.1f, 0.1f, 0.5f);
//		}
		
		
	}

	@Override
	public boolean dontToggleOnLoadModules() {
		return true;
	}

	@Override
	public boolean getEnableAtStartup() {
		return true;
	}

}
