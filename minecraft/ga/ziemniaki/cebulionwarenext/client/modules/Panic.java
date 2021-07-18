package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.main.Jigsaw;
import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class Panic extends Module {

	public Panic() {
		super("Panic", Keyboard.KEY_NONE, Category.GLOBAL,
				"Disables all mods instantly.");
	}

	@Override
	public void onEnable() {
		for(Module m : Jigsaw.getModulesByCategories(Jigsaw.getToggledModules(), Jigsaw.defaultCategories)) {
			m.setToggled(false, true);
		}
		this.setToggled(false, false);
	}

}
