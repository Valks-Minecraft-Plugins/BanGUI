package com.bangui.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.bangui.utils.Utils;

public class Inventories {
	public static final Inv GUI_HOME() {
		Inv inv = new Inv(null, 27, "Ban GUI");
		inv.setItem(12, Utils.item("Ban", "Ban Players", Material.STICK));
		inv.setItem(14, Utils.item("Pardon", "Pardon Players", Material.FEATHER));
		return inv;
	}
	
	public static final Inv GUI_HOME_BAN() {
		Inv inv = new Inv(null, 27, "Ban Home GUI");
		inv.setItem(12, Utils.item("Online", "Ban Online Players", Material.SAPLING));
		inv.setItem(14, Utils.item("Offline", "Ban Offline Players", Material.BONE));
		return inv;
	}
	
	public static final Inv GUI_PARDON() {
		int size = 54;
		Inv inv = new Inv(null, size, "Pardon GUI by Valk");
		
		int n = 0;
		for (OfflinePlayer p : Utils.getOfflineBannedPlayers()) {
			if (n < size) {
				inv.setItem(n, Utils.playerItemSkullInfo(p));
				n++;
			} else {
				break;
			}
		}
		
		return inv;
	}
	
	public static final Inv GUI_BAN_OFFLINE() {
		int size = 54;
		
		Inv inv = new Inv(null, size, "Offline Ban GUI by Valk");
		
		int n = 0;
		for (OfflinePlayer p : Utils.getOfflineNotBannedPlayers()) {
			if (n < size) {
				inv.setItem(n, Utils.playerItemSkullInfo(p));
				n++;
			} else {
				break;
			}
		}
		
		return inv;
	}
	
	public static final Inv GUI_BAN_ONLINE() {
		int size = 54;
		
		Inv inv = new Inv(null, size, "Online Ban GUI by Valk");
		
		int n = 0;
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (n < size) {
				inv.setItem(n, Utils.playerItemSkullInfo(p));
				n++;
			} else {
				break;
			}
		}
		
		return inv;
	}
}
