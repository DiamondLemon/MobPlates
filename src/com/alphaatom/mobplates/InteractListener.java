package com.alphaatom.mobplates;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Item;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;

public class InteractListener implements Listener {
	
	public static MobPlates plugin;
	
	public InteractListener(MobPlates instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onEntityInteract(EntityInteractEvent event) {
		Material mat = event.getBlock().getType();
		if (mat == Material.STONE_PLATE || mat == Material.WOOD_PLATE) {
			int ex = event.getBlock().getX();
			int why = event.getBlock().getY();
			int zed = event.getBlock().getZ();
			if (typeExists(ex, why, zed)) {
				String location = String.valueOf(ex) + "." + String.valueOf(why) + "." + String.valueOf(zed) + ".type";
				FileConfiguration conf = MobPlates.config;
				String type = conf.getString(location);
				Entity e = event.getEntity();
				if (type.contains("blaze") && e instanceof Blaze) {
					return;
				}
				if (type.contains("cavespider") && e instanceof CaveSpider) {
					return;
				}
				if (type.contains("chicken") && e instanceof Chicken) {
					return;
				}
				if (type.contains("cow") && e instanceof Cow) {
					return;
				}
				if (type.contains("creeper") && e instanceof Creeper) {
					return;
				}
				if (type.contains("enderman") && e instanceof Enderman) {
					return;
				}
				if (type.contains("ghast") && e instanceof Ghast) {
					return;
				}
				if (type.contains("irongolem") && e instanceof IronGolem) {
					return;
				}
				if (type.contains("item") && e instanceof Item) {
					return;
				}
				if (type.contains("magmacube") && e instanceof MagmaCube) {
					return;
				}
				if (type.contains("mooshroom") && e instanceof MushroomCow) {
					return;
				}
				if (type.contains("ocelot") && e instanceof Ocelot) {
					return;
				}
				if (type.contains("pig") && e instanceof Pig) {
					return;
				}
				if (type.contains("pigman") && e instanceof PigZombie) {
					return;
				}
				if (type.contains("sheep") && e instanceof Sheep) {
					return;
				}
				if (type.contains("silverfish") && e instanceof Silverfish) {
					return;
				}
				if (type.contains("skeleton") && e instanceof Skeleton) {
					return;
				}
				if (type.contains("slime") && e instanceof Slime) {
					return;
				}
				if (type.contains("snowman") && e instanceof Snowman) {
					return;
				}
				if (type.contains("spider") && e instanceof Spider) {
					return;
				}
				if (type.contains("squid") && e instanceof Squid) {
					return;
				}
				if (type.contains("villager") && e instanceof Villager) {
					return;
				}
				if (type.contains("wolf") && e instanceof Wolf) {
					return;
				}
				if (type.contains("zombie") && e instanceof Zombie) {
					return;
				}
				event.setCancelled(true);
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
