package ga.ziemniaki.cebulionwarenext.client.modules;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class NoBreakDelay extends Module {

	public NoBreakDelay() {
		super("NoBreakDelay", 0, Category.PLAYER, "Disables the break delay after breaking a block.");
	}
	
	@Override
	public void onUpdate() {
		mc.playerController.blockHitDelay = 0;
		super.onUpdate();
	}
	
}
