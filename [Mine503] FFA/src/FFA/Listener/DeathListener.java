package FFA.Listener;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import CoinAPI.Main.CoinManager;
import CoinAPI.Main.SplitAPI;
import FFA.Main.Data;
import FFA.Main.Main;
import FFA.Stats.Stats;
import FFA.Util.Items;

public class DeathListener implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		Player k = p.getKiller();

		Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
			public void run() {
				p.spigot().respawn();
				giveItems(p);
			}
		}, 1L);

		if(k instanceof Player) {
			if(k != p) {
				e.getDrops().clear();
				Stats.addKills(k.getUniqueId().toString(), Integer.valueOf(1));
				Stats.addDeaths(p.getUniqueId().toString(), Integer.valueOf(1));
				try {
					Listener_Scoreboard.setScoreboard(p);
				} catch (Exception ex) {
				}
				try {
					Listener_Scoreboard.setScoreboard(k);
				} catch (Exception ex) {
				}
				k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
				k.sendMessage(Data.prefix + "Du hast den Spieler §e" + p.getName() + " §7getötet.");
				int hp = (int) (k.getHealth() / 2);
				p.sendMessage(Data.prefix + "§e" + k.getName() + " §7hat dich getötet. §8(§c" + hp + "❤§8)");
				CoinManager.addCoins(k.getUniqueId().toString(), Integer.valueOf(10));
				k.getInventory().addItem(Items.createItemUnbreakable(Material.ARROW, 1, 0, "§cPfeile"));
				k.setHealth(20.0);
				k.sendMessage(CoinAPI.Main.Main.prefix + "§7Du hast §e10 Coins §7erhalten.");
				e.setDeathMessage(null);
			} else {
				e.getDrops().clear();
				Stats.addDeaths(p.getUniqueId().toString(), Integer.valueOf(1));
				p.sendMessage(Data.prefix + "Du bist gestorben.");
				try {
					Listener_Scoreboard.setScoreboard(p);
				} catch (Exception ex) {
				}
				e.setDeathMessage(null);
			}
		} else {
			e.getDrops().clear();
			Stats.addDeaths(p.getUniqueId().toString(), Integer.valueOf(1));
			p.sendMessage(Data.prefix + "Du bist gestorben.");
			try {
				Listener_Scoreboard.setScoreboard(p);
			} catch (Exception ex) {
			}
			e.setDeathMessage(null);
		}
		e.setDeathMessage(null);		
	}
	
	public static void giveItems(Player p) {
		p.getInventory().clear();
		
		p.getInventory().setItem(0, Items.createItemUnbreakable(Material.STONE_SWORD, 1, 0, "§cSteinschwert"));
		p.getInventory().setItem(1, Items.createItemUnbreakable(Material.BOW, 1, 0, "§cBogen"));
		p.getInventory().setItem(2, Items.createItemUnbreakable(Material.FISHING_ROD, 1, 0, "§cAngel"));
		p.getInventory().setItem(8, Items.createItemUnbreakable(Material.ARROW, 4, 0, "§cPfeile"));
		
		p.getInventory().setHelmet(Items.createItemUnbreakable(Material.IRON_HELMET, 1, 0, "§cEisenhelm"));
		p.getInventory().setChestplate(Items.createItemUnbreakable(Material.IRON_CHESTPLATE, 1, 0, "§cEisenbrust"));
		p.getInventory().setLeggings(Items.createItemUnbreakable(Material.IRON_LEGGINGS, 1, 0, "§cEisenhose"));
		p.getInventory().setBoots(Items.createItemUnbreakable(Material.IRON_BOOTS, 1, 0, "§cEisenschuhe"));
	}
	
}
