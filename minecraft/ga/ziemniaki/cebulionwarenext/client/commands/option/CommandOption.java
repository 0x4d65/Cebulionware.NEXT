package ga.ziemniaki.cebulionwarenext.client.commands.option;

import ga.ziemniaki.cebulionwarenext.client.commands.Command;
import ga.ziemniaki.cebulionwarenext.client.main.Jigsaw;

public abstract class CommandOption {
	
	private Command parent;
	
	public void setParent(Command parent) {
		this.parent = parent;
	}
	
	public abstract void runRaw(String[] args);
	
	public Command getParent() {
		return parent;
	}
	
	public abstract String getSyntax();
	
	public String getDescription() {
		return null;
	}
	
	protected void addResult(String result) {
		Jigsaw.getCommandManager().addResult(result);
	}
	
}
