package com.alphaatom.mobplates;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PInteractListener extends JavaPlugin implements Listener {

	public static MobPlates plugin;
	
	public PInteractListener(MobPlates instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Action action = event.getAction();
		if (event.isBlockInHand() && action == Action.RIGHT_CLICK_BLOCK) {
			int ex2 = event.getClickedBlock().getX();
			int why2 = event.getClickedBlock().getY();
			int zed2 = event.getClickedBlock().getZ();
			why2++;
			if (typeExists(ex2, why2, zed2)) {
				String typeof2 = String.valueOf(ex2) + "." + String.valueOf(why2) + "." + String.valueOf(zed2) + ".type";
				String owner2 = String.valueOf(ex2) + "." + String.valueOf(why2) + "." + String.valueOf(zed2) + ".owner";
				FileConfiguration conf1 = MobPlates.config;
				conf1.set(owner2, null);
				conf1.set(typeof2, null);
				return;
			}
			return;
		}
		if (action == Action.PHYSICAL) {
				int ex = event.getClickedBlock().getX();
				int why = event.getClickedBlock().getY();
				int zed = event.getClickedBlock().getZ();
				if (typeExists(ex, why, zed)) {
					String location = String.valueOf(ex) + "." + String.valueOf(why) + "." + String.valueOf(zed) + ".type";
					FileConfiguration conf = MobPlates.config;
					String type = conf.getString(location);
					if (type.contains("player")) {
						return;
					} else {
						event.setCancelled(true);
					}
				}
			}
		if (action == Action.LEFT_CLICK_BLOCK) {
			if (event.getClickedBlock().getType() == Material.WOOD_PLATE || event.getClickedBlock().getType() == Material.STONE_PLATE) {
				Player player = event.getPlayer();
				if (CommandEnabled.CommandEnabled.get(player) == "remove") {
					FileConfiguration confi = MobPlates.config;
					int ex1 = event.getClickedBlock().getX();
					int why1 = event.getClickedBlock().getY();
					int zed1 = event.getClickedBlock().getZ();
					String typeof = String.valueOf(ex1) + "." + String.valueOf(why1) + "." + String.valueOf(zed1) + ".type";
					String owner = String.valueOf(ex1) + "." + String.valueOf(why1) + "." + String.valueOf(zed1) + ".owner";
					if (typeExists(ex1, why1, zed1)) {
						if (confi.getString(owner) == player.getName() || player.hasPermission("mobplates.destroy") || player.isOp()) {
							confi.set(owner, null);
							confi.set(typeof, null);
							CommandEnabled.CommandEnabled.put(player, "off");
							player.sendMessage(ChatColor.DARK_AQUA + "Successfully removed plating!");
							event.setCancelled(true);
							return;
						}
						player.sendMessage(ChatColor.RED + "You don't own this pressure plate!");
						CommandEnabled.CommandEnabled.put(player, "off");
						event.setCancelled(true);
						return;
					}
					player.sendMessage(ChatColor.RED + "That pressure plate is not registered!");
					CommandEnabled.CommandEnabled.put(player, "off");
					event.setCancelled(true);
					return;
				}
				if (CommandEnabled.CommandEnabled.get(player) != "off" && CommandEnabled.CommandEnabled.get(player) != null) {
					int ex = event.getClickedBlock().getX();
					int why = event.getClickedBlock().getY();
					int zed = event.getClickedBlock().getZ();
					if (typeExists(ex, why, zed)) {
						player.sendMessage(ChatColor.RED + "That pressure plate is already registered!");
						CommandEnabled.CommandEnabled.put(player, "off");
						event.setCancelled(true);
						return;
					} else {
						FileConfiguration conf = MobPlates.config;
						String location = String.valueOf(ex) + "." + String.valueOf(why) + "." + String.valueOf(zed) + ".type";
						String path = String.valueOf(ex) + "." + String.valueOf(why) + "." + String.valueOf(zed) + ".owner";
						conf.set(location, CommandEnabled.CommandEnabled.get(player));
						conf.set(path, event.getPlayer().getName());
						event.setCancelled(true);
						CommandEnabled.CommandEnabled.put(player, "off");
						plugin.saveConfig();
						player.sendMessage(ChatColor.DARK_AQUA + "Successfully created pressure plate!");
					}
				}
			} else {
				Player player = event.getPlayer();
					if (CommandEnabled.CommandEnabled.get(player) != "off" && CommandEnabled.CommandEnabled.get(player) != null) {
						player.sendMessage(ChatColor.RED + "That isn't a pressure plate!");
						CommandEnabled.CommandEnabled.put(player, "off");
						event.setCancelled(true);
					}
			}
		} 
	}
	
	public boolean typeExists(int x, int y, int z) {
		FileConfiguration configuration = MobPlates.config;
		String location = String.valueOf(x) + "." + String.valueOf(y) + "." + String.valueOf(z) + ".type";
		if (configuration.getString(location) != null) {
		return true;
		} else {
			return false;
		}
	}
	
}
