package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;
import net.minecraft.network.play.client.CPacketChatMessage;

public class ChatSpam extends Module {

	public ChatSpam() {
		super("ChatSpam", Keyboard.KEY_NONE, Category.MISC, "Spams the chat.");
	}

	@Override
	public void onDisable() {

		super.onDisable();
	}

	@Override
	public void onEnable() {

		super.onEnable();
	}

	@Override
	public void onUpdate() {

		mc.player.connection.sendPacket(new CPacketChatMessage("Spam"));

		super.onUpdate();
	}

}
