package ga.ziemniaki.cebulionwarenext.client.bypasses;

import ga.ziemniaki.cebulionwarenext.client.main.Jigsaw;
import ga.ziemniaki.cebulionwarenext.client.settings.ClientSettings;

public class AntiWatchdog extends ModPreset {
	
	@Override
	public void onEnable() {
		super.onEnable();
		
		
		ClientSettings.KillauraAPS = 9;
		ClientSettings.KillauraRange = 3.9;
		ClientSettings.longJumpTimerSpeed = 0.7;
		ClientSettings.killauraHitRatio = 0.908;
		ClientSettings.ExtendedReachrange = 3.9;
		ClientSettings.chestStealDelay = true;
		ClientSettings.KBHorizontal = 0.3;
		ClientSettings.KBVertical = 0.1;
		ClientSettings.flightkick = false;
//		ClientSettings.killauraMoreKnockback = true;
		ClientSettings.Blockrange = 3.5;
		ClientSettings.killauraSmoothRotsMaxRotation = 140f;
		ClientSettings.killauraAttackDelay = 73;
		
		
		Jigsaw.getModuleByName("AntiBot").setMode("Hypixel");
		Jigsaw.getModuleByName("Flight").setMode("Hypixel");
		Jigsaw.getModuleByName("AutoSneak").setMode("Client");
		Jigsaw.getModuleByName("AutoSprint").setMode("Forwards");
		Jigsaw.getModuleByName("FastEat").setMode("NCP");
		Jigsaw.getModuleByName("BunnyHop").setMode("SlowHop");
		Jigsaw.getModuleByName("Speed").setMode("NewNCP");
		Jigsaw.getModuleByName("NoSlowDown").setMode("Vanilla");
		
		
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
		Jigsaw.getModuleByName("Criticals").setToggled(true, true);
		Jigsaw.getModuleByName("Knockback").setToggled(true, true);
		Jigsaw.getModuleByName("Projectiles").setToggled(true, true);
		Jigsaw.getModuleByName("Criticals").setToggled(true, true);
		Jigsaw.getModuleByName("Jesus").setToggled(true, true);
		Jigsaw.getModuleByName("AutoSprint").setToggled(true, true);
		Jigsaw.getModuleByName("NoSlowDown").setToggled(true, true);
		Jigsaw.getModuleByName("AntiRotate").setToggled(true, true);
		Jigsaw.getModuleByName("ExtendedReach").setToggled(true, true);
		
		
	}
	
	@Override
	public String getName() {
		return "Hypixel";
	}
	
}
