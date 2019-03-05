package com.bangui;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.bangui.commands.CommandGUIBan;
import com.bangui.listeners.ListenerInventory;

public class BanGUI extends JavaPlugin {
	@Override
	public void onEnable() {
		registerCommands();
		registerListeners();
	}
	
	private void registerCommands() {
		getCommand("guiban").setExecutor(new CommandGUIBan());
	}
	
	private void registerListeners() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new ListenerInventory(), this);
	}
}
