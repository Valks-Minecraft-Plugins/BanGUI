package com.bangui.listeners;

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ListenerInventory implements Listener {
	@EventHandler
	private void invClickEvent(InventoryClickEvent e) {
		if (e.getInventory().getName().equals("Ban GUI by Valk")) {
			e.setCancelled(true);
			
			Player p = (Player) Bukkit.getOnlinePlayers().toArray()[e.getSlot()];
			Bukkit.getBanList(Type.NAME).addBan(p.getName(), "Valk is a cat.", null, e.getWhoClicked().getName());
			p.kickPlayer(p.getName());
		}
	}
}