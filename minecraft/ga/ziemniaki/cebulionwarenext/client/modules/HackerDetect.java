package ga.ziemniaki.cebulionwarenext.client.modules;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.main.Jigsaw;
import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.client.settings.ClientSettings;
import ga.ziemniaki.cebulionwarenext.client.tools.Utils;
import ga.ziemniaki.cebulionwarenext.gui.ScreenPos;
import ga.ziemniaki.cebulionwarenext.hackerdetect.Hacker;
import ga.ziemniaki.cebulionwarenext.hackerdetect.checks.Accuracy;
import ga.ziemniaki.cebulionwarenext.hackerdetect.checks.AntiKBCheck;
import ga.ziemniaki.cebulionwarenext.hackerdetect.checks.Check;
import ga.ziemniaki.cebulionwarenext.hackerdetect.checks.HighAPS;
import ga.ziemniaki.cebulionwarenext.hackerdetect.checks.Yawrate;
import ga.ziemniaki.cebulionwarenext.module.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;

public class HackerDetect extends Module {

	public static ArrayList<Hacker> hackers = new ArrayList<Hacker>();
	public static ArrayList<Check> checks = new ArrayList<Check>();

	public HackerDetect() {
		super("HackerDetect", Keyboard.KEY_NONE, Category.HIDDEN, "Tries to catch hackers.");
	}

	@Override
	public void onClientLoad() {
		checks.add(new AntiKBCheck());
		checks.add(new HighAPS());
		checks.add(new Yawrate());
		checks.add(new Accuracy());
		super.onClientLoad();
	}

	@Override
	public void onUpdate() {
		for (EntityPlayer player : mc.world.playerEntities) {
			if (player instanceof EntityPlayerSP) {
				continue;
			}
			if(player.getName().equals(mc.player.getName())) {
				continue;
			}
			if(Utils.isBlacklisted(player)) {
				continue;
			}
			if (getHackerByName(player.getName()) != null) {
				getHackerByName(player.getName()).loaded = true;
				continue;
			}
			
			//The player is not in the list, add him to the list and set his loaded flag to true
			
			Hacker hc = new Hacker(player);
			if (!ClientSettings.hackerDetectAutoNotify) {
				hc.muted = true;
			}
			hc.loaded = true;
			hackers.add(hc);
		}
		ArrayList<Hacker> toRemove = new ArrayList<Hacker>();
		for (Hacker hacker : hackers) {
			boolean exists = false;
			for (EntityPlayer p : mc.world.playerEntities) {
				if (p.getName().equals(hacker.player.getName())) {
					hacker.player = p;
					exists = true;
					break;
				}
			}
			if (!exists) {
				toRemove.add(hacker);
				continue;
			}
			hacker.doChecks();
		}
		for(Hacker hacker : toRemove) {
			hacker.loaded = false;
		}
		super.onUpdate();
	}

	public static int getViolations(String name) {
		int i = 0;
		Hacker en = getHackerByName(name);
		i = en.getViolations();
		return i;
	}

	public static Check getCheck(String playerName, String checkName) {
		Hacker en = getHackerByName(playerName);
		for (Check check : en.checks) {
			if (check.getName().equals(checkName)) {
				return check;
			}
		}
		return null;
	}
	
	public static Hacker getHackerByName(String name) {
		for(Hacker hacker : hackers) {
			if(hacker.player.getName().equalsIgnoreCase(name)) {
				return hacker;
			}
		}
		return null;
	}

	@Override
	public boolean getEnableAtStartup() {
		return true;
	}

	@Override
	public boolean dontToggleOnLoadModules() {
		return true;
	}

	public static void updateEnabledChecks() {
		for (Hacker hacker : hackers) {
			hacker.updateEnabledChecks();
		}
	}
	
	public static void muteAll() {
		for(Hacker hacker : hackers) {
			hacker.muted = true;
		}
	}
	
	public static void unmuteAll() {
		for(Hacker hacker : hackers) {
			hacker.muted = false;
		}
	}
	
	public static int getHackingPlayers() {
		int count = 0;
		for(Hacker hacker : hackers) {
			if(hacker.getViolations() > 0) {
				count++;
			}
		}
		return count;
	}
	
	public static boolean hasPlayerViolated() {
		for(Hacker hacker : hackers) {
			if(hacker.getViolations() > 0) {
				return true;
			}
		}
		return false;
	}
	
	public static int getLoadedPlayers() {
		int count = 0;
		for(Hacker hacker : hackers) {
			if(hacker.loaded) {
				count++;
			}
		}
		return count;
	}
	
	@Override
	public void onGui() {
		super.onGui();
		if(!hasPlayerViolated()) {
			return;
		}
		if(getHackingPlayers() == 1) {
			Jigsaw.getUIManager().addToQueue("??j" + getHackingPlayers() + "??r potential hacker detected!", ScreenPos.LEFTUP);
		}
		else {
			Jigsaw.getUIManager().addToQueue("??j" + getHackingPlayers() + "??r potential hackers detected!", ScreenPos.LEFTUP);
		}
		Jigsaw.getUIManager().addToQueue(" ??8- ??7Open 'HackerDetect GUI' for info.", ScreenPos.LEFTUP);
	}
}
