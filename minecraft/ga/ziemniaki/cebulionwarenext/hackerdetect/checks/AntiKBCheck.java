package ga.ziemniaki.cebulionwarenext.hackerdetect.checks;

import ga.ziemniaki.cebulionwarenext.hackerdetect.Hacker;

public class AntiKBCheck extends Check {

	@Override
	public CheckState check(Hacker en) {
		if (en.player.hurtResistantTime > 6 && en.player.hurtResistantTime < 12
				&& en.player.lastTickPosX == en.player.posX && en.player.posZ == en.player.lastTickPosZ
				&& !mc.world.checkBlockCollision(en.player.boundingBox.expand(0.05, 0, 0.05))) {
			return CheckState.VIOLATION;
		}
		if (en.player.hurtResistantTime > 6 && en.player.hurtResistantTime < 12
				&& en.player.lastTickPosY == en.player.posY) {
			return CheckState.VIOLATION;
		}
		return CheckState.RESET;
	}

	@Override
	public String getName() {
		return "AntiKnockback";
	}

}
