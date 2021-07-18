package ga.ziemniaki.cebulionwarenext.client.commands;

import ga.ziemniaki.cebulionwarenext.client.commands.option.CommandOption;
import ga.ziemniaki.cebulionwarenext.client.commands.option.NoCommandOption;
import ga.ziemniaki.cebulionwarenext.client.main.Jigsaw;

public class CommandCrash extends Command {
	
	@Override
	public String getActivator() {
		return "crash";
	}

	@Override
	public String getDesc() {
		return "Attempts to crash the server using the ServerCrasher module with the ToTheDumpster mode.";
	}

	@Override
	public CommandOption[] setOptions() {
		return new CommandOption[] {
			new NoCommandOption() {
				@Override
				public void run() {
					Jigsaw.getModuleByName("ServerCrasher").setMode("ToTheDumpster");
					Jigsaw.getModuleByName("ServerCrasher").setToggled(true, true);
				}
			}
		};
	}
	
}
