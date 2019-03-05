package com.bangui.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Utils {
	public static List<OfflinePlayer> getOfflineNotBannedPlayers(){
		List<OfflinePlayer> bannedPlayers = new ArrayList<OfflinePlayer>();
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			if (!p.isBanned()) {
				bannedPlayers.add(p);
			}
		}
		return bannedPlayers;
	}
	
	public static List<OfflinePlayer> getOfflineBannedPlayers() {
		List<OfflinePlayer> bannedPlayers = new ArrayList<OfflinePlayer>();
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			if (p.isBanned()) {
				bannedPlayers.add(p);
			}
		}
		return bannedPlayers;
	}
	
	public static ItemStack playerItemSkullInfo(OfflinePlayer p) {
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwningPlayer(p);
		meta.setDisplayName(Utils.color("&f" + p.getName()));
		skull.setItemMeta(meta);
		
		return skull;
	}
	
	public static ItemStack playerItemSkullInfo(Player p) {
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwningPlayer(p);
		meta.setDisplayName(Utils.color("&f" + p.getName()));
		skull.setItemMeta(meta);
		
		return skull;
	}
	
	public static ItemStack item(String name, String lore, Material material) {
		ItemStack item = new ItemStack(material);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.WHITE + color(name));
		im.addItemFlags(ItemFlag.values());
		List<String> list = new ArrayList<String>();
		for (String element : lore.split("\n")) {
			list.add(ChatColor.GRAY + color(element));
		}
		im.setLore(list);
		item.setItemMeta(im);
		return item;
	}
	
	public static String color(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
}
