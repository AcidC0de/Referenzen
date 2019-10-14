package FFA.Listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import FFA.Main.Data;
import FFA.Util.Items;
import FFA.Util.KillstreakManager;
import FFA.Util.LocationUtil;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();

		KillstreakManager.streak.put(p.getName().toLowerCase(), Integer.valueOf(0));
		
		for(Player all : Bukkit.getOnlinePlayers()) {
			Listener_Scoreboard.setScoreboard(all);
			all.showPlayer(p);
		}
		
		Listener_Scoreboard.setScoreboard(e.getPlayer());
		
//		if(p.hasPermission("ffa.team")) {
//			e.setJoinMessage(Data.prefix + "Das Teammitglied §e" + p.getName() + " §7hat den Server betreten.");
//			for(Player all : Bukkit.getOnlinePlayers()) {
//				all.playSound(all.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
//			}
//		} else {
//			e.setJoinMessage("");
//		}
		
		e.setJoinMessage("");
		
		p.teleport(LocationUtil.getLoc("spawn"));
		
		p.sendMessage(Data.prefix + "§aTeams in beliebiger Größe sind auf diesem Server §eERLAUBT§a!");
		
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
