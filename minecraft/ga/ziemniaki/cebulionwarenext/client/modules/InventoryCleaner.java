package ga.ziemniaki.cebulionwarenext.client.modules;

import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InventoryCleaner extends Module {
	
	public InventoryCleaner() {
		super("InventoryCleaner", Keyboard.KEY_NONE, Category.HIDDEN, "Cleans your inventory from unwanted items.");
	}

	public static void cleanItem(Item item) {
		for(int i = 0; i < mc.player.inventory.getSizeInventory(); i++) {
			ItemStack stack = mc.player.inventory.getStackInSlot(i);
			if(stack != null && stack.getItem() != null) {
				Item stackItem = stack.getItem();
				if(stackItem.equals(item)) {
					mc.playerController.windowClick(0, i, 2, ClickType.THROW, mc.player);
					return;
				}
			}
		}
	}
	
}
