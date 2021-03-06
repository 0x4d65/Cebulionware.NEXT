package ga.ziemniaki.cebulionwarenext.client.modules;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.events.UpdateEvent;
import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.client.settings.ClientSettings;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.ModSetting;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.SliderSetting;
import ga.ziemniaki.cebulionwarenext.gui.custom.clickgui.ValueFormat;
import ga.ziemniaki.cebulionwarenext.module.Module;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.BlockPos;

public class Step extends Module {
	@Override
	public ModSetting[] getModSettings() {
//		BasicSlider heightSlider = new BasicSlider("Step Height", ClientSettings.stepHeight, 1, 9, 0,
//				ValueDisplay.DECIMAL);
//		SliderListener listener = new SliderListener() {
//
//			@Override
//			public void onSliderValueChanged(Slider slider) {
//				ClientSettings.stepHeight = (float) slider.getValue();
//			}
//		};
//		heightSlider.addSliderListener(listener);
		SliderSetting heightSlider = new SliderSetting("Vanilla Mode - Height", "stepHeight", 1, 9, 0.0, ValueFormat.DECIMAL);
		return new ModSetting[] { heightSlider };
	}

	public Step() {
		super("Step", Keyboard.KEY_NONE, Category.MOVEMENT, "Allows you to step up full blocks like a half slab");
	}

	@Override
	public void onDisable() {

		if (this.currentMode.equals("Vanilla")) {
			mc.player.stepHeight = 0.6f;
		}

		super.onDisable();
	}

	@Override
	public void onEnable() {

		super.onEnable();
	}

	@Override
	public void onUpdate(UpdateEvent event) {
		if (this.currentMode.equals("Vanilla")) {
			mc.player.stepHeight = (float) ClientSettings.stepHeight;
		}
		else {
			if(mc.player.onGround
					&& !mc.player.isOnLadder()
					&& (mc.player.movementInput.moveForward != 0.0F || mc.player.movementInput.moveStrafe != 0.0F)
					&& canStep() && !mc.player.movementInput.jump
					&& mc.player.isCollidedHorizontally)
				{
					sendPacket(new CPacketPlayer.Position(event.x, event.y + 0.41999998688698D, event.z, mc.player.onGround));
					sendPacket(new CPacketPlayer.Position(event.x, event.y + 0.7531999805212D, event.z, mc.player.onGround));
					sendPacket(new CPacketPlayer.Position(event.x, event.y + 1.00133597911214D, event.z, mc.player.onGround));
					sendPacket(new CPacketPlayer.Position(event.x, event.y + 1.16610926093821D, event.z, mc.player.onGround));
					sendPacket(new CPacketPlayer.Position(event.x, event.y + 1.24918707874468D, event.z, mc.player.onGround));
					sendPacket(new CPacketPlayer.Position(event.x, event.y + 1.1707870772188D, event.z, mc.player.onGround));
					mc.player.setPosition(event.x, event.y + 1, event.z);
				}
		}
		super.onUpdate();
	}

	@Override
	public void onRender() {

		super.onRender();
	}
	
	@Override
	protected void onModeChanged(String modeBefore, String newMode) {
		mc.player.stepHeight = 0.6f;
		super.onModeChanged(modeBefore, newMode);
	}

	@Override
	public String[] getModes() {
		return new String[] { "Vanilla", "Packet" };
	}
	
	private boolean canStep()
	{
		ArrayList<BlockPos> collisionBlocks = new ArrayList<BlockPos>();
		
		Entity player = mc.player;
		BlockPos pos1 =
			new BlockPos(player.getEntityBoundingBox().minX - 0.001D,
				player.getEntityBoundingBox().minY - 0.001D,
				player.getEntityBoundingBox().minZ - 0.001D);
		BlockPos pos2 =
			new BlockPos(player.getEntityBoundingBox().maxX + 0.001D,
				player.getEntityBoundingBox().maxY + 0.001D,
				player.getEntityBoundingBox().maxZ + 0.001D);
		
		if(mc.world.isAreaLoaded(pos1, pos2))
			for(int x = pos1.getX(); x <= pos2.getX(); x++)
				for(int y = pos1.getY(); y <= pos2.getY(); y++)
					for(int z = pos1.getZ(); z <= pos2.getZ(); z++)
						if(y > player.posY - 1.0D && y <= player.posY)
							collisionBlocks.add(new BlockPos(x, y, z));
		
		BlockPos belowPlayerPos =
			new BlockPos(player.posX, player.posY - 1.0D, player.posZ);
		for(BlockPos collisionBlock : collisionBlocks)
			if(!(mc.world.getBlockState(collisionBlock.add(0, 1, 0))
				.getBlock() instanceof BlockFenceGate))
				if(mc.world
					.getBlockState(collisionBlock.add(0, 1, 0))
					.getCollisionBoundingBox(mc.world, belowPlayerPos) != null)
					return true;
		
		return true;
	}

}
