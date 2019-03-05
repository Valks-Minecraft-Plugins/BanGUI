package com.bangui.listeners;

import org.bukkit.BanList.Type;

import java.util.Date;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.bangui.utils.Utils;

public class ListenerInventory implements Listener {
	private HashMap<Player, Player> banTracker = new HashMap<Player, Player>();
	
	@EventHandler
	private void invClickEvent(InventoryClickEvent e) {
		if (e.getInventory().getName().equals("Ban GUI by Valk")) {
			e.setCancelled(true);
			int slot = e.getSlot();
			if (slot < Bukkit.getOfflinePlayers().length) {
				Player p = (Player) Bukkit.getOnlinePlayers().toArray()[slot];
				Player admin = (Player) e.getWhoClicked();
				admin.openInventory(banPlayerGUI(admin));
				banTracker.put(admin, p);
			}
		}
		
		if (e.getInventory().getName().equals("Ban Options")) {
			e.setCancelled(true);
			
			Player admin = (Player) e.getInventory().getHolder();
			Player p = banTracker.get(admin);

			switch (e.getSlot()) {
			case 10:
				banPlayer(p.getName(), "Camping", new Date(System.currentTimeMillis()+30*60*1000), admin.getName());
				admin.closeInventory();
				break;
			case 12:
				banPlayer(p.getName(), "Not Listening to Staff", new Date(System.currentTimeMillis()+24*60*60*1000), admin.getName());
				admin.closeInventory();
				break;
			case 14:
				banPlayer(p.getName(), "Abusing", new Date(System.currentTimeMillis()+2*24*60*60*1000), admin.getName());
				admin.closeInventory();
				break;
			case 16:
				banPlayer(p.getName(), "Hacking", new Date(System.currentTimeMillis()+7*24*60*60*1000), admin.getName());
				admin.closeInventory();
				break;
			case 31:
				admin.openInventory(Utils.banInv());
				break;
			default:
				e.setCancelled(true);
				break;
			}
		}
	}
	
	private void banPlayer(String playerName, String reason, Date time, String who) {
		Player p = Bukkit.getPlayer(playerName);
		Bukkit.getBanList(Type.NAME).addBan(p.getName(), reason, time, who);
		p.kickPlayer(p.getName());
	}
	
	private Inventory banPlayerGUI(Player admin) {
		int size = 36;
		Inventory inv = Bukkit.createInventory(admin, size, "Ban Options");
		for (int n = 0; n < size; n++) {
			ItemStack black_glass_pane = Utils.item("&8---", "&8---", Material.STAINED_GLASS_PANE);
			black_glass_pane.setDurability((short) 15);
			inv.setItem(n, black_glass_pane);
		}
		inv.setItem(10, Utils.item("Camping", "30 Minutes", Material.FEATHER));
		inv.setItem(12, Utils.item("Not Listening to Staff", "1 Day", Material.STICK));
		inv.setItem(14, Utils.item("Abusing", "2 Days", Material.COAL));
		inv.setItem(16, Utils.item("Hacking", "7 Days", Material.BLAZE_POWDER));
		inv.setItem(31, Utils.item("Back", "Go Back", Material.ANVIL));
		return inv;
	}
}