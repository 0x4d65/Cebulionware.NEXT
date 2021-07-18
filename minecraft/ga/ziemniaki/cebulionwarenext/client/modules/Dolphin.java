package ga.ziemniaki.cebulionwarenext.client.modules;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;
import net.minecraft.block.material.Material;

public class Dolphin extends Module {

	public Dolphin() {
		super("Dolphin", 0, Category.FUN, "Changes some animations.");
	}

	@Override
	public void onUpdate() {
		if (this.mc.world.handleMaterialAcceleration(this.mc.player.getEntityBoundingBox().expand(0.0D, -0.1D, 0.0D), Material.WATER, this.mc.player));
		super.onUpdate();
	}
}
