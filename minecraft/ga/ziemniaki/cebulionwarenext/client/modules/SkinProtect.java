package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class SkinProtect extends Module {

	public SkinProtect() {
		super("SkinProtect", Keyboard.KEY_NONE, Category.WORLD, "Hides skins");
	}

}
