package ga.ziemniaki.cebulionwarenext.client.modules;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.lwjgl.input.Keyboard;

import ga.ziemniaki.cebulionwarenext.client.AutoBuildBlock;
import ga.ziemniaki.cebulionwarenext.client.AutoBuildConf;
import ga.ziemniaki.cebulionwarenext.client.AutoBuildTools;
import ga.ziemniaki.cebulionwarenext.client.events.BlockPlaceEvent;
import ga.ziemniaki.cebulionwarenext.client.main.Jigsaw;
import ga.ziemniaki.cebulionwarenext.client.module.state.Category;
import ga.ziemniaki.cebulionwarenext.module.Module;

public class AutoBuild extends Module {

	public static AutoBuildConf loaded;
	static ArrayList<AutoBuildBlock> blocks = new ArrayList<AutoBuildBlock>();

	public AutoBuild() {
		super("AutoBuild", Keyboard.KEY_NONE, Category.AI, "Builds stuff automatically! :D");
	}

	@Override
	public void onRightClick() {

		super.onRightClick();
	}

	@Override
	public void onUpdate() {
		
		super.onUpdate();
	}

	@Override
	public void onToggle() {
		blocks.clear();
		loaded = null;
		super.onToggle();
	}

	@Override
	public void onBlockPlace(BlockPlaceEvent blockPlaceEvent) {
		
		super.onBlockPlace(blockPlaceEvent);
	}

	@Override
	public void onModeChanged(String modeBefore, String newMode) {
		blocks.clear();
		loaded = null;
		super.onModeChanged(modeBefore, newMode);
	}

	@Override
	public void onRender() {
		// TODO Need to render select positions!
		super.onRender();
	}

	public static boolean loadConf(String name) {
		try {
			Jigsaw.chatMessage("Loading config: " + name);
			File[] files = Jigsaw.getFileMananger().autoBuildDir.listFiles();
			File rightFile = null;
			for (File file : Jigsaw.getFileMananger().autoBuildDir.listFiles()) {
				if (!file.getName().endsWith(".json") || file.isDirectory()) {
					continue;
				}
				String fileName = file.getName();
				if (fileName.substring(0, fileName.length() - 5).equals(name)) {
					rightFile = file;
				}
				break;
			}
			if (rightFile == null) {
				Jigsaw.chatMessage("Could not find file!");
				return false;
			}
			JSONObject json = (JSONObject) new JSONParser().parse(new FileReader(rightFile));
			loaded = AutoBuildTools.loadFromJson(name, json);
			Jigsaw.chatMessage("Successfully loaded AutoBuild config: " + AutoBuild.loaded.name);
		} catch (Exception e) {
			e.printStackTrace();
			Jigsaw.chatMessage("Error while loading AutoBuild config: " + ", Info: " + e.toString());
			return false;
		}
		return true;
	}

}
