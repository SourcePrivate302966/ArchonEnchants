package com.nomic.AEnchants.Enchants;

import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Lightning implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	public void onHit(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player && e.getDamager() instanceof Arrow))
			return;
		Arrow arrow = (Arrow) e.getDamager();
		Player p = (Player) arrow.getShooter();
		Player hit = (Player) e.getEntity();
		Location l = e.getEntity().getLocation();
		if (p.getItemInHand() == null)
			return;
		List<String> lore = p.getItemInHand().getItemMeta().getLore();
		String one = "&bLightning I";
		String two = "&eLightning II";
		String three = "&cLightning III";
		if (lore == null)
			return;
		double health = hit.getHealth();
		if (lore.contains(ChatColor.translateAlternateColorCodes('&', one))) {
			int chance = (1 + new Random().nextInt(32));
			if (chance == 1) {
				l.getWorld().strikeLightning(l);
			}
		} else if (lore.contains(ChatColor.translateAlternateColorCodes('&', two))) {
			int chance = (1 + new Random().nextInt(19));
			if (chance == 1) {
				l.getWorld().strikeLightning(l);
				hit.setHealth(health - 2);
			}
		} else if (lore.contains(ChatColor.translateAlternateColorCodes('&', three))) {
			int chance = (1 + new Random().nextInt(9));
			if (chance == 1) {
				l.getWorld().strikeLightning(l);
				hit.setHealth(health - 4);
			}
		}
	}
}