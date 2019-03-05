package com.bangui.listeners;

import org.bukkit.BanList.Type;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import com.bangui.inventories.Inventories;
import com.bangui.utils.Utils;

public class ListenerInventory implements Listener {
	private HashMap<UUID, UUID> banTracker = new HashMap<UUID, UUID>();
	
	@EventHandler
	private void invClickEvent(InventoryClickEvent e) {
		String invName = e.getView().getTitle();
		int slot = e.getSlot();
		Player admin = (Player) e.getWhoClicked();
		
		if (invName.equals("Ban Home GUI")) {
			e.setCancelled(true);
			switch (slot) {
			case 12:
				admin.openInventory(Inventories.GUI_BAN_ONLINE().getInv());
				break;
			case 14:
				admin.openInventory(Inventories.GUI_BAN_OFFLINE().getInv());
				break;
			default:
				break;
			}
		}
		
		if (invName.equals("Ban GUI")) {
			e.setCancelled(true);
			switch (slot) {
			case 12:
				admin.openInventory(Inventories.GUI_HOME_BAN().getInv());
				break;
			case 14:
				admin.openInventory(Inventories.GUI_PARDON().getInv());
				break;
			default:
				break;
			}
		}
		
		if (invName.equals("Pardon GUI by Valk")) {
			e.setCancelled(true);
			if (slot < Utils.getOfflineBannedPlayers().size()) {
				OfflinePlayer p = Utils.getOfflineBannedPlayers().get(slot);
				Bukkit.getServer().getBanList(Type.NAME).pardon(p.getName());
				admin.sendMessage(Utils.color("&7Pardoned &f" + p.getName() + "&7."));
				admin.closeInventory();
			}
		}
		
		if (invName.equals("Offline Ban GUI by Valk")) {
			e.setCancelled(true);
			if (slot < Utils.getOfflineNotBannedPlayers().size()) {
				OfflinePlayer p = Utils.getOfflineNotBannedPlayers().get(slot);
				admin.openInventory(banPlayerGUI(admin));
				banTracker.put(admin.getUniqueId(), p.getUniqueId());
			}
		}
		
		if (invName.equals("Online Ban GUI by Valk")) {
			e.setCancelled(true);
			if (slot < Bukkit.getOnlinePlayers().size()) {
				Player p = (Player) Bukkit.getOnlinePlayers().toArray()[slot];
				admin.openInventory(banPlayerGUI(admin));
				banTracker.put(admin.getUniqueId(), p.getUniqueId());
			}
		}
		
		if (invName.equals("Ban Options")) {
			e.setCancelled(true);
			
			OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(banTracker.get(admin.getUniqueId()));
			Player onlinePlayer = Bukkit.getPlayer(banTracker.get(admin.getUniqueId()));
			
			if (offlinePlayer != null) {
				switch (slot) {
				case 10:
					if (offlinePlayer == admin) {
						admin.sendMessage(Utils.color("&7You cannot ban yourself!"));
						admin.closeInventory();
						return;
					}
					admin.sendMessage(Utils.color("&7Banned &f" + offlinePlayer.getName() + "&7 for camping."));
					banPlayer(offlinePlayer.getUniqueId(), "Camping", new Date(System.currentTimeMillis()+30*60*1000), admin.getName());
					admin.closeInventory();
					break;
				case 12:
					if (offlinePlayer == admin) {
						admin.sendMessage(Utils.color("&7You cannot ban yourself!"));
						admin.closeInventory();
						return;
					}
					admin.sendMessage(Utils.color("&7Banned &f" + offlinePlayer.getName() + "&7 for not listening to staff."));
					banPlayer(offlinePlayer.getUniqueId(), "Not Listening to Staff", new Date(System.currentTimeMillis()+24*60*60*1000), admin.getName());
					admin.closeInventory();
					break;
				case 14:
					if (offlinePlayer == admin) {
						admin.sendMessage(Utils.color("&7You cannot ban yourself!"));
						admin.closeInventory();
						return;
					}
					admin.sendMessage(Utils.color("&7Banned &f" + offlinePlayer.getName() + "&7 for abusing."));
					banPlayer(offlinePlayer.getUniqueId(), "Abusing", new Date(System.currentTimeMillis()+2*24*60*60*1000), admin.getName());
					admin.closeInventory();
					break;
				case 16:
					if (offlinePlayer == admin) {
						admin.sendMessage(Utils.color("&7You cannot ban yourself!"));
						admin.closeInventory();
						return;
					}
					admin.sendMessage(Utils.color("&7Banned &f" + offlinePlayer.getName() + "&7 for hacking."));
					banPlayer(offlinePlayer.getUniqueId(), "Hacking", new Date(System.currentTimeMillis()+7*24*60*60*1000), admin.getName());
					admin.closeInventory();
					break;
				case 31:
					admin.openInventory(Inventories.GUI_HOME_BAN().getInv());
					break;
				default:
					e.setCancelled(true);
					break;
				}
				return;
			}
			
			if (onlinePlayer != null) {
				if (onlinePlayer == admin) {
					admin.sendMessage(Utils.color("&7You cannot ban yourself!"));
					admin.closeInventory();
					return;
				}
			
				switch (slot) {
				case 10:
					admin.sendMessage(Utils.color("&7Banned &f" + onlinePlayer.getName() + "&7 for camping."));
					banPlayer(onlinePlayer.getUniqueId(), "Camping", new Date(System.currentTimeMillis()+30*60*1000), admin.getName());
					admin.closeInventory();
					break;
				case 12:
					admin.sendMessage(Utils.color("&7Banned &f" + onlinePlayer.getName() + "&7 for not listening to staff."));
					banPlayer(onlinePlayer.getUniqueId(), "Not Listening to Staff", new Date(System.currentTimeMillis()+24*60*60*1000), admin.getName());
					admin.closeInventory();
					break;
				case 14:
					admin.sendMessage(Utils.color("&7Banned &f" + onlinePlayer.getName() + "&7 for abusing."));
					banPlayer(onlinePlayer.getUniqueId(), "Abusing", new Date(System.currentTimeMillis()+2*24*60*60*1000), admin.getName());
					admin.closeInventory();
					break;
				case 16:
					admin.sendMessage(Utils.color("&7Banned &f" + onlinePlayer.getName() + "&7 for hacking."));
					banPlayer(onlinePlayer.getUniqueId(), "Hacking", new Date(System.currentTimeMillis()+7*24*60*60*1000), admin.getName());
					admin.closeInventory();
					break;
				case 31:
					admin.openInventory(Inventories.GUI_HOME_BAN().getInv());
					break;
				default:
					e.setCancelled(true);
					break;
				}
				return;
			}
		}
	}
	
	private void banPlayer(UUID id, String reason, Date time, String who) {
		Player onlinePlayer = Bukkit.getPlayer(id);
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(id);
		
		if (onlinePlayer != null) {
			Bukkit.getBanList(Type.NAME).addBan(onlinePlayer.getName(), reason, time, who);
			onlinePlayer.kickPlayer(onlinePlayer.getName());
			return;
		}
		
		if (offlinePlayer != null) {
			Bukkit.getBanList(Type.NAME).addBan(offlinePlayer.getName(), reason, time, who);
			return;
		}
	}
	
	private Inventory banPlayerGUI(Player admin) {
		int size = 45;
		Inventory inv = Bukkit.createInventory(admin, size, "Ban Options");
		for (int n = 0; n < size; n++) {
			inv.setItem(n, Utils.item("&8---", "&8---", Material.BLACK_STAINED_GLASS_PANE));
		}
		inv.setItem(10, Utils.item("Camping", "30 Minutes", Material.FEATHER));
		inv.setItem(12, Utils.item("Not Listening to Staff", "1 Day", Material.STICK));
		inv.setItem(14, Utils.item("Abusing", "2 Days", Material.COAL));
		inv.setItem(16, Utils.item("Hacking", "7 Days", Material.BLAZE_POWDER));
		inv.setItem(31, Utils.item("Back", "Go Back", Material.ANVIL));
		return inv;
	}
}