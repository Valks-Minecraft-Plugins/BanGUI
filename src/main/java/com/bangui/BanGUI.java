package com.bangui;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.bangui.commands.CommandGUIBan;
import com.bangui.listeners.ListenerInventory;

public class BanGUI extends JavaPlugin {
	public static List<UUID> using = new ArrayList<UUID>();
	
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
