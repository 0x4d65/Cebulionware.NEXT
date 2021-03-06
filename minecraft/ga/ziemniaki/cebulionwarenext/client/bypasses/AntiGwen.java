package ga.ziemniaki.cebulionwarenext.client.bypasses;

import ga.ziemniaki.cebulionwarenext.client.main.Jigsaw;
import ga.ziemniaki.cebulionwarenext.client.settings.ClientSettings;

public class AntiGwen extends ModPreset {
	
	@Override
	public void onEnable() {
		super.onEnable();
		
		
		ClientSettings.KillauraAPS = 8;
		ClientSettings.KillauraRange = 4.2;
		ClientSettings.longJumpTimerSpeed = 0.7;
		ClientSettings.ExtendedReachrange = 4.2;
		ClientSettings.chestStealDelay = true;
		ClientSettings.KBHorizontal = 0.3;
		ClientSettings.KBVertical = 0.1;
		ClientSettings.flightkick = false;
		
		
		Jigsaw.getModuleByName("AntiBot").setMode("Mineplex");
		Jigsaw.getModuleByName("Flight").setMode("MAC");
		Jigsaw.getModuleByName("AutoSneak").setMode("Client");
		Jigsaw.getModuleByName("AutoSprint").setMode("MultiDir");
		Jigsaw.getModuleByName("FastEat").setMode("NCP");
		Jigsaw.getModuleByName("BunnyHop").setMode("NormalHop");
		Jigsaw.getModuleByName("Speed").setMode("OldNCP");
		Jigsaw.getModuleByName("NoSlowDown").setMode("NCP");
		
		
		Jigsaw.getModuleByName("AutoBlock").setToggled(true, true);
		Jigsaw.getModuleByName("Team").setToggled(true, true);
		Jigsaw.getModuleByName("NonPlayers").setToggled(true, true);
		Jigsaw.getModuleByName("Players").setToggled(true, true);
		Jigsaw.getModuleByName("AntiBot").setToggled(true, true);
		Jigsaw.getModuleByName("Criticals").setToggled(true, true);
		Jigsaw.getModuleByName("ESP").setToggled(true, true);
		Jigsaw.getModuleByName("Tracers").setToggled(true, true);
		Jigsaw.getModuleByName("InvMove").setToggled(true, true);
		Jigsaw.getModuleByName("Animations").setToggled(true, true);
		Jigsaw.getModuleByName("Knockback").setToggled(true, true);
		Jigsaw.getModuleByName("Projectiles").setToggled(true, true);
		Jigsaw.getModuleByName("Jesus").setToggled(true, true);
		Jigsaw.getModuleByName("AutoSprint").setToggled(true, true);
		Jigsaw.getModuleByName("NoSlowDown").setToggled(true, true);
		Jigsaw.getModuleByName("AntiRotate").setToggled(true, true);
		Jigsaw.getModuleByName("ExtendedReach").setToggled(true, true);
		
		
	}
	
	@Override
	public String getName() {
		return "Mineplex";
	}
	
}
