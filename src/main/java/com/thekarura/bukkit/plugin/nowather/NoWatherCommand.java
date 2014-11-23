package com.thekarura.bukkit.plugin.nowather;

import java.util.logging.Logger;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NoWatherCommand implements CommandExecutor {
	
	private static Logger log = NoWather.log;
	private static String logPrefix = NoWather.logPrefix;
	private static String msgPrefix = NoWather.msgPrefix;
	
	private NoWather plugin;
	
	private CommandSender sender;
	private Command cmd;
	private String label;
	private String[] args;
	
	public NoWatherCommand(NoWather plugin){
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		this.sender = sender;
		this.cmd = cmd;
		this.label = label;
		this.args = args;
		
		return run();
	}
	
	private boolean run() {
		// 引数がなければヘルプガイドへ
		if (args.length == 0) {
			return helpGuid();
		} else {
			if (args.length >= 1){
				if (args[0].equalsIgnoreCase("toggledownfall")){
					return toglledownfall();
				} else if (args[0].equalsIgnoreCase("reload")) {
					plugin.reloadConfig();
				    plugin.saveConfig();
				    return true;
				} else if (args[0].equalsIgnoreCase("Check")){
					String Prefix = logPrefix;
					String world = "null";
					if (!(args.length >= 2)){
						if (sender instanceof Player){
							Player player = (Player) sender;
							world = player.getWorld().getName();
							Prefix = msgPrefix;
						} else {
							sender.sendMessage(logPrefix + "ワールド名を指定してください。");
							sender.sendMessage(logPrefix + "/NoWather Check [world]");
							return true;
						}
					} else if (args.length >= 2){
						world = args[1];
					}
					Boolean c = plugin.getWorlds(world);
					sender.sendMessage(Prefix+world+" is "+c.toString()+" world.");
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean toglledownfall(){
		if (sender instanceof Player){
			Player player = (Player) sender;
			World world = player.getWorld();
			boolean c = true;
			player.sendMessage(msgPrefix + "天候を切り替えました。");
			plugin.isCommand_();
			world.setStorm(!world.hasStorm());
		} else {
			sender.sendMessage(logPrefix+"コンソールからの実行は出来ません。");
		}
		return true;
	}
	
	private boolean helpGuid(){
		
		String[] guid = {
				msgPrefix + "§e============[NoWather]============",
				msgPrefix + "§d/NoWather §F§ntoggledownfall§r§2 <= 天候を切り替えます。",
				msgPrefix + "§d/NoWather §F§nreload §r§2 <= 設定をリロードします。",
				msgPrefix + "§d/NoWather §F§nCheck§r§2 <= 現在地が有効化どうかを確認します。"
		};
		
		sender.sendMessage(guid);
		
		return true;
	}
	
}
