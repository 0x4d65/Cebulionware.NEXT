package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;
import net.minecraft.network.play.client.CPacketPlayer;

public class NoFall extends Module {

	public NoFall() {
		super("NoFall", Keyboard.KEY_NONE, Category.PLAYER, "Disables fall damage.");
	}

	@Override
	public void onUpdate() {
		if (mc.player.fallDistance > 2.0f) {
			for(int i = 0; i < 1; i++) {
				sendPacket(new CPacketPlayer(true));
			}
		}
		super.onUpdate();
	}
}
