package com.thekarura.bukkit.plugin.nowather;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class NoWather extends JavaPlugin implements Listener {
	
	// ====== Logger ====== //
	public final static Logger log = Logger.getLogger("NoWather");
	public final static String logPrefix = log.getName();
	public final static String msgPrefix = "§D"+logPrefix+"§R";
	
	// ====== instance ====== //
	private static NoWather instance;
	private ConfigManager cm;
	
	// コマンド確認
	private boolean isCommand = false;
	
	// ====== 起動処理時 ====== //
	public void onEnable(){
		
		instance = this;
		
		// ++ ConfigManager ++ //
		cm = new ConfigManager(this);
		cm.load(false);
		
		// ++ Commands ++ //
		getCommand("NoWather").setExecutor(new NoWatherCommand(this));
		
		// ++ Listnear ++ //
		getServer().getPluginManager().registerEvents(this, this);
		
		log.info(logPrefix + "onEnable has been invoked!");
	}
	
	// ====== 動作停止時 ====== //
	public void onDisable(){
		log.info(logPrefix + "onDisable has been invoked!");
	}
	
	@EventHandler
	public void onWatherEvent(WeatherChangeEvent event){
		new WorldListnear(this, event);
	}
	
	/**
	 * 一定時間isCommandの値をtrueへ変更します。
	 */
	public void isCommand_(){
		isCommand = true;
		new BukkitRunnable(){
			@Override
			public void run() {
				isCommand = false;
			}
		}.runTaskLater(instance, 20);
	}
	
	/**
	 * ワールド名から禁止にしているワールドかを検索します。
	 * @return
	 */
	public boolean getWorlds(String world){
		for (String world_ : getConfigManager().getWorlds()){
			if (world_.equals(world)){
				return true;
			}
		}
		return false;
	}
	
	// ===== Getters ===== //
	
	/**
	 * Pluginのインスタンスを返します。
	 * @return instance;
	 */
	public static NoWather getInstance(){
		return instance;
	}
	
	/**
	 * このJarFileの階層を返します。
	 * @return JarFileの階層
	 */
	public static File getPluginJarFile() {
		return instance.getFile();
	}
	
	/**
	 * ConfigManagerを返します。
	 * @return ConfigManager
	 */
	public ConfigManager getConfigManager(){
		return this.cm;
	}
	
	public boolean isCommand(){
		return this.isCommand;
	}
	
}
