package ga.ziemniaki.cebulionwarenext.hackerdetect.checks;

import ga.ziemniaki.cebulionwarenext.hackerdetect.Hacker;

public class ConstantAPS extends Check {

	@Override
	public CheckState check(Hacker en) {
		if (en.player.aps > 2 && en.player.aps == en.player.preAps && en.player.aps != 0) {
			return CheckState.VIOLATION;
		}
		return CheckState.RESET;
	}

	@Override
	public String getName() {
		return "AutoClicker/KillAura (Constant APS)";
	}

	@Override
	public int getMaxViolations() {
		return 100;
	}

	@Override
	public String getPrefix() {
		return "§7 has a §cstrange §cAPS §cpattern. §rHas the player been mining a block for a long time?";
	}

	@Override
	public boolean getMentionName() {
		return false;
	}
}
