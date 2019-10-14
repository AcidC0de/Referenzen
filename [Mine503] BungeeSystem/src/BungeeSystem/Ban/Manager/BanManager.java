package BungeeSystem.Ban.Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BungeeSystem.Main.MySQL;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BanManager {
	
	public static void ban(String uuid, String playername, String reason, String bannedby, long seconds) {
		long end = 0L;
		if(seconds == -1L) {
			end = -1L;
		} else {
			long current = System.currentTimeMillis();
			long millis = seconds * 1000L;
			end = current + millis;
		}
		MySQL.update("INSERT INTO Bans (Spielername, UUID, Ende, Grund, BannedBy) VALUES ('" + playername + "','" + uuid + "','" + end + "','" + reason + "','" + bannedby + "')");
		
		ProxiedPlayer target = ProxyServer.getInstance().getPlayer(playername);
		if(target != null) {
			target.disconnect(
					"§f§lDu bist auf dem §c§lMINE503 §f§lNetzwerk gebannt!"
					+ "\n\n§7Accountname: §a" + target.getName()
					+ "\n§7Grund §8» §4 " + BanManager.getReason(uuid)
					+ "\n§7Gebannt von §8» §6 " + BanManager.getBannedBy(uuid)
					+ "\n§7Verbleibende Zeit §8» §6 " + BanManager.getRemainingTime(uuid)
					+ "\n\n§aDu kannst im §6Forum §aeinen §6Entbannungsantrag §astellen.");
		}
	}
	
	public static void unban(String uuid) {
		MySQL.update("DELETE FROM Bans WHERE UUID='" + uuid + "'");
	}
	
	public static boolean isBanned(String uuid) {
		if (MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT Ende FROM Bans WHERE UUID='" + uuid + "'");
			try {
				return rs.next();
			} catch (SQLException e) {
				e.printStackTrace();

				return false;
			}
		}
		return false;
	}
	
	public static String getBannedBy(String uuid) {
		if (MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Bans WHERE UUID='" + uuid + "'");
			try {
				if (rs.next()) {
					return rs.getString("BannedBy");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return "";
		}
		return uuid;
	}
	
	public static String getReason(String uuid) {
		if (MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Bans WHERE UUID='" + uuid + "'");
			try {
				if (rs.next()) {
					return rs.getString("Grund");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return "";
		}
		return uuid;
	}

	public static Long getEnd(String uuid) {
		ResultSet rs = MySQL.getResult("SELECT * FROM Bans WHERE UUID='" + uuid + "'");
		try {
			if (rs.next()) {
				return Long.valueOf(rs.getLong("Ende"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<String> getBannedPlayers() {
		List list = new ArrayList();
		ResultSet rs = MySQL.getResult("SELECT * FROM Bans");
		try {
			while (rs.next()) {
				list.add(rs.getString("Spielername"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static String getRemainingTime(String uuid) {
		long current = System.currentTimeMillis();
		long end = getEnd(uuid).longValue();
		if (end == -1L) {
			return "§4§lPERMANENT";
		}
		long millis = end - current;

		long seconds = 0L;
		long minutes = 0L;
		long hours = 0L;
		long days = 0L;
		long weeks = 0L;
		while (millis > 1000L) {
			millis -= 1000L;
			seconds += 1L;
		}
		while (seconds > 60L) {
			seconds -= 60L;
			minutes += 1L;
		}
		while (minutes > 60L) {
			minutes -= 60L;
			hours += 1L;
		}
		while (hours > 24L) {
			hours -= 24L;
			days += 1L;
		}
		while (days > 7L) {
			days -= 7L;
			weeks += 1L;
		}
		return "§b" + weeks + " §7w §b" + days + " §7d §b" + hours + " §7h §b" + minutes + " §7m §b" + seconds + " §7s";
	}
	
}
