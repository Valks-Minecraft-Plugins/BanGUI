package com.bangui.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import net.md_5.bungee.api.ChatColor;

public class CommandGUIBan implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("guiban")) {
			Player p = Bukkit.getPlayer(sender.getName());
			if (p != null) {
				p.openInventory(banInv());
			}
			
			return true;
		}
		
		return false;
	}
	
	private Inventory banInv() {
		Inventory inv = Bukkit.createInventory(null, 54, "Ban GUI by Valk");
		
		int n = 0;
		for (Player p : Bukkit.getOnlinePlayers()) {
			
			inv.setItem(n, itemInfo(p));
			n++;
		}
		
		return inv;
	}
	
	private ItemStack itemInfo(Player p) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(p.getName());
		meta.setDisplayName(color("&f" + p.getName()));
		skull.setItemMeta(meta);
		
		return skull;
	}
	
	private String color(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
}
