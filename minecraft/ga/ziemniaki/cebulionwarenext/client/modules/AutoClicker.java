package ga.ziemniaki.cebulionwarenext.client.modules;

import java.util.Random;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.WaitTimer;
import ga.ziemniaki.cebulionwarenext.client.main.Jigsaw;
import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.client.settings.ClientSettings;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.ModSetting;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.SliderSetting;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.ValueFormat;
import ga.ziemniaki.cebulionwarenext.module.Module;
import net.minecraft.util.EnumHand;

public class AutoClicker extends Module {

	WaitTimer timer = new WaitTimer();
	Random rand = new Random();

	@Override
	public ModSetting[] getModSettings() {
		return new ModSetting[] { new SliderSetting("Max CPS", "AutoClickermax", 1.0, 20.0, 0.0, ValueFormat.DECIMAL),
				new SliderSetting("Min CPS", "AutoClickermin", 1.0, 20.0, 0.0, ValueFormat.DECIMAL), 
				new SliderSetting("Hit Chance", "AutoClickerhitPercent", 0.0, 1.0, 0.0, ValueFormat.PERCENT) };
	}

	public AutoClicker() {
		super("AutoClicker", Keyboard.KEY_NONE, Category.COMBAT,
				"Automatically clicks when holding down the attack button.");
	}

	@Override
	public void onToggle() {
		timer.reset();
		super.onToggle();
	}

	@Override
	public void onUpdate() {
		if (shouldClick()) {
			int a = 1000 / randInt(ClientSettings.AutoClickermin, ClientSettings.AutoClickermax);
			// Jigsaw.chatMessage(a);
			if (!timer.hasTimeElapsed(a, true)) {
				return;
			}
			// Jigsaw.chatMessage("click");
			if (rand.nextDouble() <= ClientSettings.AutoClickerhitPercent) {
				Jigsaw.click();
			} else {
				mc.player.swingArm(EnumHand.MAIN_HAND);
			}

		} else {
			timer.reset();
		}
		super.onUpdate();
	}

	public static boolean shouldClick() {
		return mc.gameSettings.keyBindAttack.pressed && Jigsaw.getModuleByName("AutoClicker").isToggled();
	}

	public int randInt(int min, int max) {
		return rand.nextInt((max - min) + 1) + min;
	}
}
