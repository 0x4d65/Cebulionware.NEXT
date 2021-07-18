package ga.ziemniaki.cebulionwarenext.gui.custom.clickgui;

import ga.ziemniaki.cebulionwarenext.client.main.Jigsaw;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class CheckBtnModLinker extends ModSetting {

	private String name;
	private Module module;
	
	public CheckBtnModLinker(String moduleName) {
		this.name = moduleName;
		
		this.module = Jigsaw.getModuleByName(moduleName);
		
		System.out.println("NO VALUE FOR: " + name);
	}
	
	@Override
	public Component createComponent() {
		

		ModCheckBtn modCheckBtn = new ModCheckBtn();
		
		modCheckBtn.setMod(module);
		modCheckBtn.setTitle(module.getName());
		
		return modCheckBtn;
		
		
	}
	
	public String getName() {
		return name;
	}

}
