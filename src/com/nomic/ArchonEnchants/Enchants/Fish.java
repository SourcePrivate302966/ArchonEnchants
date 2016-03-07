package com.nomic.ArchonEnchants.Enchants;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Fish implements Listener {

	Material diamondh = Material.DIAMOND_HELMET;
	Material ironh = Material.IRON_HELMET;
	Material chainh = Material.CHAINMAIL_HELMET;
	Material leatherh = Material.LEATHER_HELMET;

	String one = "&bFeast I";

	@EventHandler(priority = EventPriority.NORMAL)
	public void shift(InventoryClickEvent e) {
		if (e.getInventory().getType() != InventoryType.CRAFTING)
			return;
		if (!(e.getWhoClicked() instanceof Player))
			return;
		Player p = (Player) e.getWhoClicked();
		if (e.getCurrentItem() == null)
			return;
		Material m = e.getCurrentItem().getType();
		if (e.getClick() == ClickType.DROP || e.getClick() == ClickType.CONTROL_DROP
				|| e.getClick() == ClickType.DOUBLE_CLICK || e.getClick() == ClickType.MIDDLE
				|| e.getClick() == ClickType.NUMBER_KEY || e.getClick() == ClickType.UNKNOWN)
			return;
		if (!(e.getClick() == ClickType.SHIFT_LEFT || e.getClick() == ClickType.SHIFT_RIGHT)) {
			if (m == diamondh || m == ironh || m == chainh || m == leatherh && e.getSlotType() == SlotType.ARMOR) {
				if (p.getInventory().getHelmet() == null) {
					p.removePotionEffect(PotionEffectType.SATURATION);
				}
			}
			return;
		}
		if (!(m == diamondh || m == ironh || m == chainh || m == leatherh))
			return;
		List<String> lore = e.getCurrentItem().getItemMeta().getLore();
		if (lore == null)
			return;
		if (p.getInventory().getHelmet() == null && e.getSlotType() != SlotType.ARMOR) {
			if (lore.contains(ChatColor.translateAlternateColorCodes('&', one))) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, (1000000 * 20), 0));
			} 
		} else if (p.getInventory().getHelmet() != null && e.getSlotType() == SlotType.ARMOR) {
			p.removePotionEffect(PotionEffectType.SATURATION);
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void hotbar(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getItem() == null)
			return;
		List<String> lore = e.getItem().getItemMeta().getLore();
		if (lore == null)
			return;
		Material m = e.getItem().getType();
		if (!(m == diamondh || m == ironh || m == chainh || m == leatherh))
			return;
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (p.getInventory().getHelmet() == null) {
				if (lore.contains(ChatColor.translateAlternateColorCodes('&', one))) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, (1000000 * 20), 0));
				} 
			}
		}
	}
}
