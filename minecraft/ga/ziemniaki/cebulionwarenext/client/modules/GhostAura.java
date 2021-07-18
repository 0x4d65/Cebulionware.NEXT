package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.WaitTimer;
import ga.ziemniaki.cebulionwarenext.client.main.Jigsaw;
import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.client.modules.target.AuraUtils;
import ga.ziemniaki.cebulionwarenext.client.tools.Utils;
import ga.ziemniaki.cebulionwarenext.module.Module;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;

public class GhostAura extends Module {
	WaitTimer timer = new WaitTimer();
	EntityLivingBase en = null;

	public GhostAura() {
		super("GhostAura", Keyboard.KEY_NONE, Category.COMBAT, "An aura that bypasses most Anti-Cheats and looks real.");
	}

	@Override
	public void onToggle() {
		en = null;
		super.onToggle();
	}

	@Override
	public void onUpdate() {
		if (mc.currentScreen != null) {
			return;
		}
		if (en != null && mc.player.getDistanceToEntity(en) <= AuraUtils.getRange()) {
			double xAim = (en.posX - 0.5) + (en.posX - en.lastTickPosX) * 2.5;
			double yAim = en.posY + (en.getEyeHeight() - en.height / 1.5) - (en.posY - en.lastTickPosY) * 1.5;
			double zAim = (en.posZ - 0.5) + (en.posZ - en.lastTickPosZ) * 2.5;
			Utils.facePos(new Vec3d(xAim, yAim, zAim));
		}
		if (!timer.hasTimeElapsed(1000 / AuraUtils.getAPS(), true)) {
			return;
		}
		en = Utils.getClosestEntity((float) AuraUtils.getRange());
		if (en == null) {
			return;
		}
		AutoBlock.stopBlock();
		Criticals.crit(mc.player.posX, mc.player.posY, mc.player.posZ);
		Jigsaw.click();
		AutoBlock.startBlock();
		super.onUpdate();
	}
}
