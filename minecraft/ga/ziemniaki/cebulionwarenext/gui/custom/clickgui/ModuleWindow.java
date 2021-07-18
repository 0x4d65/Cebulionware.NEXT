package ga.ziemniaki.cebulionwarenext.gui.custom.clickgui;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.layout.Layout;

public class ModuleWindow extends ComponentWindow {
	
	public ModuleWindow(Layout layout) {
		super(layout);
	}
	
	protected Category category;
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}

}
