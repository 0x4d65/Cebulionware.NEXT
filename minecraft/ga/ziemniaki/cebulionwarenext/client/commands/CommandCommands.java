package ga.ziemniaki.cebulionwarenext.client.commands;

import ga.ziemniaki.cebulionwarenext.client.commands.option.CommandOption;
import ga.ziemniaki.cebulionwarenext.client.commands.option.NoCommandOption;
import ga.ziemniaki.cebulionwarenext.client.main.Jigsaw;

public class CommandCommands extends Command {

	@Override
	public String getActivator() {
		return "commands";
	}

	@Override
	public String getDesc() {
		return "Lists all commands";
	}

	@Override
	public CommandOption[] setOptions() {
		return new CommandOption[] {
				new NoCommandOption() {
					@Override
					public void run() {
						addResult("§6These are all available commands:");
						for (Command cmd : Jigsaw.getCommandManager().commands) {
							addResult("§b§l" + cmd.getActivator());
						}
					}
				}
		};
	}
}
