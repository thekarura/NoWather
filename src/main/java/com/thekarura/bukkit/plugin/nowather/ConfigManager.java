package com.thekarura.bukkit.plugin.nowather;

import java.io.File;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import com.thekarura.bukkit.plugin.nowather.util.FileHandler;

public class ConfigManager {
	
	// instance
	private FileConfiguration config;
	
	private File plugin_dir = new File("plugins\\NoWather");
	private File plugin_fil = new File(plugin_dir.toString()+"config.yml");
	private List<String> worlds;
	
	// Constructor
	public ConfigManager(NoWather plugin){
		this.config = plugin.getConfig();
	}
	
	/**
	 * Configの読み込みをします。
	 * @param 上書きをするかどうか
	 */
	public void load(boolean Overwrite){
		if (!plugin_dir.exists()){
			plugin_dir.mkdirs();
		}
		if (!plugin_fil.exists()){
			FileHandler.inJarFileCopy(plugin_dir, "config.yml", Overwrite);
		}
		worlds = config.getStringList("worlds");
		
	}
	
	/**
	 * 禁止ワールド一覧を返します。
	 * @return
	 */
	public List<String> getWorlds(){
		return this.worlds;
	}
	
}
