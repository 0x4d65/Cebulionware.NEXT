package ga.ziemniaki.cebulionwarenext.module.group;

import ga.ziemniaki.cebulionwarenext.module.Module;

public class ModuleGroup {

	Module[] modules;
	String name;

	public ModuleGroup(Module[] modules, String name) {
		this.modules = modules;
		this.name = name;
	}

	public Module[] getModules() {
		return this.modules;
	}

	public String getName() {
		return this.name;
	}

}
