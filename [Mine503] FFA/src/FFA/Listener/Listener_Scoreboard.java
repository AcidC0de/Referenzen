package FFA.Listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import ClanAPI.Main.ClanManager;
import CoinAPI.Main.CoinManager;
import FFA.Stats.Stats;
import FFA.Util.FileManager;
import FFA.Util.KillstreakManager;

public class Listener_Scoreboard {
	
	public static void setScoreboard(Player p) {
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = board.getObjective("ffa");	
		
		if (board.getObjective("ffa") == null) {
			obj = board.registerNewObjective("ffa", "dummy");
		} else {
			obj = board.getObjective("ffa");
		}
		
		obj.setDisplayName("§6» §e§lMINE503 §8- §e§lFFA");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);

		
		obj.getScore("    ").setScore(12);
		obj.getScore("§fKills:").setScore(11);
		obj.getScore("§3" + Stats.getKills(p.getUniqueId().toString())).setScore(10);
		obj.getScore("   ").setScore(9);
		obj.getScore("§fTode:").setScore(8);
		obj.getScore("§c" + Stats.getDeaths(p.getUniqueId().toString())).setScore(7);
		obj.getScore("  ").setScore(6);
		obj.getScore("§fMap:").setScore(5);
		obj.getScore("§5" + FileManager.getMap()).setScore(4);
		obj.getScore(" ").setScore(3);
		obj.getScore("§fKillstreak:").setScore(2);
		obj.getScore("§2" + ((Integer) KillstreakManager.streak.get(p.getName().toLowerCase()))).setScore(1);	
		
		for(Player all : Bukkit.getOnlinePlayers()) {
//			PermissionUser user = PermissionsEx.getUser(all);
			if (all.hasPermission("tablist.admin")) {
				Team team = board.getTeam("00001Admin");
				if (team == null) {
					team = board.registerNewTeam("00001Admin");
				}
				team.setPrefix("§4§lAdmin §8• §7");
				if(ClanManager.inClan(all.getUniqueId().toString())) {
					team.setSuffix(" §8[§e" + ClanManager.getClanTag(all.getUniqueId().toString()) + "§8]");
				} else {
					team.setSuffix("");
				}
				team.addPlayer(all);
			 } else if(all.hasPermission("tablist.dev")) {
				Team team = board.getTeam("00003Dev");
				if (team == null) {
					team = board.registerNewTeam("00003Dev");
				}
				team.setPrefix("§bDev §8• §7");
				if(ClanManager.inClan(all.getUniqueId().toString())) {
					team.setSuffix(" §8[§e" + ClanManager.getClanTag(all.getUniqueId().toString()) + "§8]");
				} else {
					team.setSuffix("");
				}
				team.addPlayer(all);
			 } else if(all.hasPermission("tablist.srmod")) {
				Team team = board.getTeam("00004SrMod");
				if (team == null) {
					team = board.registerNewTeam("00004SrMod");
				}
				team.setPrefix("§2SrMod §8• §7");
				if(ClanManager.inClan(all.getUniqueId().toString())) {
					team.setSuffix(" §8[§e" + ClanManager.getClanTag(all.getUniqueId().toString()) + "§8]");
				} else {
					team.setSuffix("");
				}
				team.addPlayer(all);
			 } else if(all.hasPermission("tablist.mod")) {
				Team team = board.getTeam("00005Mod");
				if (team == null) {
					team = board.registerNewTeam("00005Mod");
				}
				team.setPrefix("§2Mod §8• §7");
				if(ClanManager.inClan(all.getUniqueId().toString())) {
					team.setSuffix(" §8[§e" + ClanManager.getClanTag(all.getUniqueId().toString()) + "§8]");
				} else {
					team.setSuffix("");
				}
				team.addPlayer(all);
			 } else if(all.hasPermission("tablist.builder")) {
				Team team = board.getTeam("00007Builder");
				if (team == null) {
					team = board.registerNewTeam("00007Builder");
				}
				team.setPrefix("§eBuilder §8• §7");
				if(ClanManager.inClan(all.getUniqueId().toString())) {
					team.setSuffix(" §8[§e" + ClanManager.getClanTag(all.getUniqueId().toString()) + "§8]");
				} else {
					team.setSuffix("");
				}
				team.addPlayer(all);
			 } else if(all.hasPermission("tablist.youtuber")) {
				Team team = board.getTeam("00008YouTuber");
				if (team == null) {
					team = board.registerNewTeam("00008YouTuber");
				}
				team.setPrefix("§5");
				if(ClanManager.inClan(all.getUniqueId().toString())) {
					team.setSuffix(" §8[§e" + ClanManager.getClanTag(all.getUniqueId().toString()) + "§8]");
				} else {
					team.setSuffix("");
				}
				team.addPlayer(all);
			 } else if(all.hasPermission("tablist.premiumplus")) {
				Team team = board.getTeam("00009Premiumplus");
				if (team == null) {
					team = board.registerNewTeam("00009Premiumplus");
				}
				team.setPrefix("§d");
				if(ClanManager.inClan(all.getUniqueId().toString())) {
					team.setSuffix(" §8[§e" + ClanManager.getClanTag(all.getUniqueId().toString()) + "§8]");
				} else {
					team.setSuffix("");
				}
				team.addPlayer(all);
			 } else if(all.hasPermission("tablist.premium")) {
				Team team = board.getTeam("00012Premium");
				if (team == null) {
					team = board.registerNewTeam("00012Premium");
				}
				team.setPrefix("§6");
				if(ClanManager.inClan(all.getUniqueId().toString())) {
					team.setSuffix(" §8[§e" + ClanManager.getClanTag(all.getUniqueId().toString()) + "§8]");
				} else {
					team.setSuffix("");
				}
				team.addPlayer(all);
			 } else if(all.hasPermission("tablist.spieler")) {
				Team team = board.getTeam("00013Spieler");
				if (team == null) {
					team = board.registerNewTeam("00013Spieler");
				}
				team.setPrefix("§a");
				if(ClanManager.inClan(all.getUniqueId().toString())) {
					team.setSuffix(" §8[§e" + ClanManager.getClanTag(all.getUniqueId().toString()) + "§8]");
				} else {
					team.setSuffix("");
				}
				team.addPlayer(all);
			}
			
			p.setScoreboard(board);
		}
		
	}
	
}
