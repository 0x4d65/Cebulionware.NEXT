package ga.ziemniaki.cebulionwarenext.client.commands;

import ga.ziemniaki.cebulionwarenext.client.commands.option.CommandOption;
import ga.ziemniaki.cebulionwarenext.client.main.Jigsaw;
import ga.ziemniaki.cebulionwarenext.client.settings.ClientSettings;
import ga.ziemniaki.cebulionwarenext.gui.Level;
import ga.ziemniaki.cebulionwarenext.gui.Notification;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class CommandToggle extends Command {

	@Override
	public void run(String[] commands) {
		String name = "";
		for (int i = 0; i < commands.length; i++) {
			if (i == 0) {
				continue;
			}
			name += commands[i];
			name += " ";
		}
		name = name.trim();
		Module module = Jigsaw.getModuleByName(name);
		if (module == null) {
			Jigsaw.getNotificationManager()
			.addNotification(new Notification(Level.ERROR, "Could not find module \"" + name + "\"!"));
			return;
		}
		module.toggle();

		if (module.isToggled()) {
			if (!(ClientSettings.notificationModulesEnable && Jigsaw.getModuleByName("Notifications").isToggled())) {
				Jigsaw.getNotificationManager()
						.addNotification(new Notification(Level.INFO, "Module " + module.getName() + " was enabled!"));
			}

		} else {
			if (!(ClientSettings.notificationModulesDisable && Jigsaw.getModuleByName("Notifications").isToggled())) {
				Jigsaw.getNotificationManager()
						.addNotification(new Notification(Level.INFO, "Module " + module.getName() + " was disabled!"));
			}
		}
	}

	@Override
	public String getActivator() {
		return "t";
	}

	@Override
	public String getSyntax() {
		return "t <module>";
	}

	@Override
	public String getDesc() {
		return "Toggles a module";
	}

	@Override
	public CommandOption[] setOptions() {
		// TODO Auto-generated method stub
		return null;
	}
}
