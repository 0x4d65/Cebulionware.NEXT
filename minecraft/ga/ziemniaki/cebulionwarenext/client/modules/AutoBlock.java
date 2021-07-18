package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.main.Jigsaw;
import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;
import net.minecraft.util.EnumHand;

public class AutoBlock extends Module {

	public AutoBlock() {
		super("AutoBlock", Keyboard.KEY_NONE, Category.COMBAT, "Blocks everytime an aura is attacking, to minimize damage.");
	}

	@Override
	public void onUpdate() {
		if (!doBlock()) {
			return;
		}
		if(mc.player.inventory.getCurrentItem() == null) {
			return;
		}
		mc.playerController.processRightClick(mc.player, mc.world, EnumHand.OFF_HAND);
		mc.player.setActiveHand(EnumHand.OFF_HAND);
//		mc.player.setItemInUse(this.mc.player.getHeldItem(EnumHand.OFF_HAND),
//				this.mc.player.getHeldItem(EnumHand.OFF_HAND).getMaxItemUseDuration());
	}

	public static void startBlock() {
		if(!Jigsaw.getModuleByName("AutoBlock").isToggled()) {
			return;
		}
		if(mc.player.inventory.getCurrentItem() == null) {
			return;
		}
		mc.playerController.processRightClick(mc.player, mc.world, EnumHand.OFF_HAND);
		mc.player.setActiveHand(EnumHand.OFF_HAND);
//		mc.player.setItemInUse(mc.player.getHeldItem(EnumHand.OFF_HAND),
//				mc.player.getHeldItem(EnumHand.OFF_HAND).getMaxItemUseDuration());
	}

	public static void stopBlock() {
		if(!Jigsaw.getModuleByName("AutoBlock").isToggled()) {
			return;
		}
//		mc.player.connection.sendPacket(new CPacketPlayerDigging(Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
		if(mc.player.inventory.getCurrentItem() == null) {
			return;
		}
		mc.playerController.onStoppedUsingItem(mc.player);
	}
	
	public static boolean doBlock() {
//		return !(!KillAura.doBlock() && !TpAura.doBlock() && !ReachAura.doBlock());
		return KillAura.doBlock();
	}

}