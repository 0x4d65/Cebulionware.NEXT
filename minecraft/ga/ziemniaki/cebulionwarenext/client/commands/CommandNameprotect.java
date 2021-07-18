package ga.ziemniaki.cebulionwarenext.client.commands;

import ga.ziemniaki.cebulionwarenext.client.commands.option.CommandOption;
import ga.ziemniaki.cebulionwarenext.client.main.Jigsaw;
import ga.ziemniaki.cebulionwarenext.client.modules.NameProtect;

public class CommandNameprotect extends Command {

	@Override
	public void run(String[] commands) {
		if (commands.length < 3) {
			addResult("§cEnter two names!");
			return;
		}
		addResult(commands[1] + " = " + commands[2]);
		((NameProtect) Jigsaw.getModuleByName("NameProtect")).replacements.put(commands[1], commands[2]);
		return;
	}

	@Override
	public String getActivator() {
		return "nameprotect";
	}

	@Override
	public String getSyntax() {
		return "nameprotect <name> <replacement>";
	}

	@Override
	public String getDesc() {
		return "Adds a player as a fakehacker for the mod \"fakehackers\"";
	}

	@Override
	public CommandOption[] setOptions() {
		// TODO Auto-generated method stub
		return null;
	}
}
