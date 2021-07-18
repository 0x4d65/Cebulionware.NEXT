package ga.ziemniaki.cebulionwarenext.client.commands.option;

public abstract class CommandOptionOnephrase extends CommandOption {

	@Override
	public void runRaw(String[] args) {
		run();
	}
	
	public abstract void run();
	
}
