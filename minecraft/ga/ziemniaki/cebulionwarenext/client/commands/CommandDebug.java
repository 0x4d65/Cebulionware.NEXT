package ga.ziemniaki.cebulionwarenext.client.commands;

import ga.ziemniaki.cebulionwarenext.client.commands.option.CommandOption;
import ga.ziemniaki.cebulionwarenext.client.main.Jigsaw;

public class CommandDebug extends Command {

	@Override
	public void run(String[] commands) {
		Jigsaw.debugMode = !Jigsaw.debugMode;
	}

	@Override
	public String getActivator() {
		return ".debug";
	}

	@Override
	public String getSyntax() {
		return ".debug";
	}

	@Override
	public String getDesc() {
		return "Turns debug mode on";
	}

	@Override
	public CommandOption[] setOptions() {
		// TODO Auto-generated method stub
		return null;
	}
}
