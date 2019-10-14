package FFA.Util;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.projectiles.ProjectileSource;

import FFA.Main.Data;

public class KillstreakManager implements Listener {
	public static HashMap<String, Integer> streak = new HashMap();

	public static void check(Player killer, int ks) {
		if (streak.containsKey(killer.getName().toLowerCase())) {
			streak.put(killer.getName().toLowerCase(), Integer.valueOf(((Integer) streak.get(killer.getName().toLowerCase())).intValue() + 1));
			if (((Integer) streak.get(killer.getName().toLowerCase())).intValue() == 10) {
				Bukkit.broadcastMessage("");
				Bukkit.broadcastMessage(Data.prefix + "§7Der Spieler §e" + killer.getName() + "§7 hat eine Killstreak von §a§l10§7!");
				Bukkit.broadcastMessage("");
				killer.getInventory().addItem(Items.createItem(Material.GOLDEN_APPLE, 1, 0, "§cGoldapfel"));
			}
			if (((Integer) streak.get(killer.getName().toLowerCase())).intValue() == 25) {
				Bukkit.broadcastMessage("");
				Bukkit.broadcastMessage(Data.prefix + "§7Der Spieler §e" + killer.getName() + "§7 hat eine Killstreak von §a§l25§7!");
				Bukkit.broadcastMessage("");
				killer.getInventory().addItem(Items.createItem(Material.GOLDEN_APPLE, 1, 0, "§cGoldapfel"));
			}
			if (((Integer) streak.get(killer.getName().toLowerCase())).intValue() == 50) {
				Bukkit.broadcastMessage("");
				Bukkit.broadcastMessage(Data.prefix + "§7Der Spieler §e" + killer.getName() + "§7 hat eine Killstreak von §a§l50§7!");
				Bukkit.broadcastMessage("");
				killer.getInventory().addItem(Items.createItem(Material.GOLDEN_APPLE, 1, 0, "§cGoldapfel"));
			}
			if (((Integer) streak.get(killer.getName().toLowerCase())).intValue() == 100) {
				Bukkit.broadcastMessage("");
				Bukkit.broadcastMessage(Data.prefix + "§7Der Spieler §e" + killer.getName() + "§7 hat eine Killstreak von §a§l100§7!");
				Bukkit.broadcastMessage("");
				killer.getInventory().addItem(Items.createItem(Material.GOLDEN_APPLE, 1, 0, "§cGoldapfel"));
			}
		} else {
			streak.put(killer.getName().toLowerCase(), Integer.valueOf(1));
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (!this.streak.containsKey(e.getPlayer().getName().toLowerCase())) {
			this.streak.put(e.getPlayer().getName().toLowerCase(), Integer.valueOf(0));
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		if (this.streak.containsKey(e.getPlayer().getName().toLowerCase())) {
			this.streak.remove(e.getPlayer().getName().toLowerCase());
		}
	}

	@EventHandler
	public void onKick(PlayerKickEvent e) {
		if (this.streak.containsKey(e.getPlayer().getName().toLowerCase())) {
			this.streak.remove(e.getPlayer().getName().toLowerCase());
		}
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		int ks = 10;
		if (this.streak.containsKey(p.getName().toLowerCase())) {
			this.streak.put(p.getName().toLowerCase(), Integer.valueOf(0));
		}
		if ((p.getKiller() instanceof Player)) {
			if (p.isDead()) {
				Player killer = p.getKiller();
				check(killer, ks);
			}
		} else if ((p.getKiller() instanceof Projectile)) {
			ProjectileSource proj = ((Projectile) p.getKiller()).getShooter();
			if (((proj instanceof Player)) && (p.isDead())) {
				Player killer = (Player) proj;
				check(killer, ks);
			}
		} else if ((p.getKiller() instanceof ThrownPotion)) {
			ProjectileSource thrownpot = ((ThrownPotion) p.getKiller()).getShooter();
			if (((thrownpot instanceof Player)) && (p.isDead())) {
				Player killer = (Player) thrownpot;
				check(killer, ks);
			}
		}
	}
}
