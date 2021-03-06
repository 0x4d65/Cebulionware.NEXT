package ga.ziemniaki.cebulionwarenext.client.modules;

import java.util.HashMap;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.commands.option.CommandOption;
import ga.ziemniaki.cebulionwarenext.client.commands.option.MultipleCustomOptionsActivator;
import ga.ziemniaki.cebulionwarenext.client.commands.option.SingleCustomOptionActivator;
import ga.ziemniaki.cebulionwarenext.client.commands.option.SinglePredeterminedOption;
import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.client.tools.Utils;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class NameProtect extends Module {

	public static HashMap<String, String> replacements = new HashMap<String, String>();

	public NameProtect() {
		super("NameProtect", Keyboard.KEY_NONE, Category.PLAYER, "Enables you to rename players and things in all text.");
	}

	@Override
	public void onClientLoad() {
		super.onClientLoad();
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}
	
	@Override
	public CommandOption[] getCommandOptions() {
		return new CommandOption[] {
			new MultipleCustomOptionsActivator("add", "name", "newName") {
				@Override
				public void run(String[] args) {
					if(!NameProtect.this.isToggled()) {
						NameProtect.this.setToggled(true, true);
						addResult("§7" + NameProtect.this.getName() + " was enabled!");
					}
					addResult(Utils.overideNameProtect(args[0]) + " was renamed to " + args[1]);
					NameProtect.replacements.put(args[0], args[1]);
				}
			},
			new SingleCustomOptionActivator("remove", "name") {
				@Override
				public void run(String arg) {
					if(!NameProtect.replacements.containsKey(arg)) {
						addResult(arg + " is not in the list!");
						return;
					}
					NameProtect.replacements.remove(arg);
					addResult(arg + " was removed from the list!");
				}
			},
			new SinglePredeterminedOption("list") {
				@Override
				public void run() {
					addResult(NameProtect.replacements.size() + " names in the list:");
					for(String name : NameProtect.replacements.keySet()) {
						addResult(Utils.overideNameProtect(name) + " = " + NameProtect.replacements.get(name));
					}
				}
			},
			new SinglePredeterminedOption("clear") {
				@Override
				public void run() {
					NameProtect.replacements.clear();
					addResult("List was cleared completely!");
				}
			}
		};
	}
	
}
