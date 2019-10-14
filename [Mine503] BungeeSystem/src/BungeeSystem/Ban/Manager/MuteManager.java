package BungeeSystem.Ban.Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BungeeSystem.Main.MySQL;

public class MuteManager {
	
	
	public static void mute(String uuid, String playername, String reason, String mutedby, long seconds) {
		long end = 0L;
		if (seconds == -1L) {
			end = -1L;
		} else {
			long current = System.currentTimeMillis();
			long millis = seconds * 1000L;
			end = current + millis;
		}
		MySQL.update("INSERT INTO Mutes (Spielername, UUID, Ende, Grund, MutedBy) VALUES ('" + playername + "','" + uuid + "','" + end + "','" + reason + "','" + mutedby + "')");
	}

	public static void unmute(String uuid) {
		MySQL.update("DELETE FROM Mutes WHERE UUID='" + uuid + "'");
	}

	public static boolean isMuted(String uuid) {
		if (MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT Ende FROM Mutes WHERE UUID='" + uuid + "'");
			try {
				return rs.next();
			} catch (SQLException e) {
				e.printStackTrace();

				return false;
			}
		}
		return false;
	}

	public static String getMutedBy(String uuid) {
		if (MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Mutes WHERE UUID='" + uuid + "'");
			try {
				if (rs.next()) {
					return rs.getString("MutedBy");
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
			ResultSet rs = MySQL.getResult("SELECT * FROM Mutes WHERE UUID='" + uuid + "'");
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
		ResultSet rs = MySQL.getResult("SELECT * FROM Mutes WHERE UUID='" + uuid + "'");
		try {
			if (rs.next()) {
				return Long.valueOf(rs.getLong("Ende"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<String> getMutedPlayers() {
		List list = new ArrayList();
		ResultSet rs = MySQL.getResult("SELECT * FROM Mutes");
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
//		return "§6" + weeks + " §fWoche(n) §6" + days + " §fTag(e) §6" + hours + " §fStunde(n) §6" + minutes
//				+ " §fMinute(n) §6" + seconds + " §fSekunde(n)";
	}
}
