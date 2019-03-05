package com.bangui.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bangui.utils.Utils;

public class CommandGUIBan implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("guiban")) {
			Player p = Bukkit.getPlayer(sender.getName());
			if (p == null) {
				return true;
			}
			
			if (!p.isOp()) {
				p.sendMessage(Utils.color("&7You do not have permission to use that."));
				return true;
			}
			
			p.openInventory(Utils.banInv());
			
			return true;
		}
		
		return false;
	}
}
