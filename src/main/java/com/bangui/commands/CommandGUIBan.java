package com.bangui.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bangui.BanGUI;
import com.bangui.inventories.Inventories;

public class CommandGUIBan implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("guiban")) {
			Player p = Bukkit.getPlayer(sender.getName());
			if (p == null) {
				return true;
			}
			
			if (p.hasPermission("guiban.admin") || p.isOp()) {
				p.openInventory(Inventories.GUI_HOME().getInv());
				BanGUI.using.add(p.getUniqueId());
			}
			
			return true;
		}
		
		return false;
	}
}
