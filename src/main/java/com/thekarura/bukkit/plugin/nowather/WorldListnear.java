package com.thekarura.bukkit.plugin.nowather;

import java.util.logging.Logger;

import org.bukkit.event.weather.WeatherChangeEvent;

public class WorldListnear {
	
	private static Logger log = NoWather.log;
	private static String logPrefix = NoWather.logPrefix;
	
	// instance
	private NoWather plugin;
	private WeatherChangeEvent event;
	
	// コマンドで実行したかどうかを判定
	boolean isCommand;
	
	// Constructor
	public WorldListnear(NoWather plugin ,WeatherChangeEvent event) {
		this.plugin = plugin;
		this.event = event;
		this.isCommand = plugin.isCommand();
		onWatherEvent();
	}
	
	/**
	 * 本命
	 */
	public void onWatherEvent(){
		if (plugin.getWorlds(event.getWorld().getName()) && !isCommand){
			event.setCancelled(true);
		}
	}
	
}
