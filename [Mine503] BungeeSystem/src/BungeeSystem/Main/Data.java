package BungeeSystem.Main;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Data {

	public static String getRank(ProxiedPlayer p) {
		String a = "§cError";
		
		if(p.hasPermission("prefix.admin")) {
			a = "§4§lAdmin §8• §7";
			return a;
		}
		if(p.hasPermission("prefix.mod")) {
			a = "§2Mod §8• §7";
			return a;
		}
		if(p.hasPermission("prefix.builder")) {
			a = "§eBuilder §8• §7";
			return a;
		}
		if(p.hasPermission("prefix.youtuber")) {
			a = "§5";
			return a;
		}
		if(p.hasPermission("prefix.premiumplus")) {
			a = "§d";
			return a;
		}
		if(p.hasPermission("prefix.premium")) {
			a = "§6";
			return a;
		}
		if(p.hasPermission("prefix.spieler")) {
			a = "§a";
			return a;
		}
		return a;
	}
	
}
