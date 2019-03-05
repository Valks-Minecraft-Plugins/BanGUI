package com.bangui.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Utils {
	public static Inventory banInv() {
		int size = 54;
		Inventory inv = Bukkit.createInventory(null, size, "Ban GUI by Valk");
		
		for (int n = 0; n < size; n++) {
			ItemStack black_glass_pane = Utils.item("&8---", "&8---", Material.STAINED_GLASS_PANE);
			black_glass_pane.setDurability((short) 15);
			inv.setItem(n, black_glass_pane);
		}
		
		int n = 0;
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (n < size) {
				inv.setItem(n, playerItemSkullInfo(p));
				n++;
			} else {
				break;
			}
		}
		
		return inv;
	}
	
	private static ItemStack playerItemSkullInfo(Player p) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(p.getName());
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
