package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class Parkour extends Module {

	boolean jumped = false;
	
	public Parkour() {
		super("Parkour", Keyboard.KEY_NONE, Category.MOVEMENT, "Jumps when you reach the end of a block.");
	}

	@Override
	public void onUpdate() {
		if(jumped) {
			if(mc.player.onGround) {
				jumped = false;
			}
		}
		else {
			if(mc.gameSettings.keyBindJump.pressed) {
				jumped = true;
			}
			else {
				if (mc.world.getCollisionBoxes(mc.player,
						mc.player.boundingBox.offset(mc.player.motionX, mc.player.motionY, mc.player.motionZ))
						.isEmpty()) {
					mc.player.jump();
					jumped = true;
				}
			}
		}
		super.onUpdate();
	}
}
