package BungeeSystem.Report;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BungeeSystem.Main.MySQL;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ReportManager {

	public static void addReport(String uuid, String reporter, String spielername, String server, String grund) {
		MySQL.update("INSERT INTO Reports (UUID, Reporter, Spielername, Server, Grund) VALUES ('" + uuid + "','" + reporter + "','" + spielername + "','" + server + "','" + grund + "')");
	}
	
	public static void deleteReport(String uuid) {
		MySQL.update("DELETE FROM Reports WHERE UUID='" + uuid + "'");
	}
	
	public static boolean isReported(String uuid) {
		if (MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT Spielername FROM Reports WHERE UUID='" + uuid + "'");
			try {
				return rs.next();
			} catch (SQLException e) {
				e.printStackTrace();

				return false;
			}
		}
		return false;
	}
	
	public static String getReason(String uuid) {
		if(MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Reports WHERE UUID='" + uuid + "'");
			try {
				if(rs.next()) {
					return rs.getString("Grund");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return "";
		}
		return uuid;
	}
	
	public static List<String> getReportedPlayers() {
		List list = new ArrayList<>();
		ResultSet rs = MySQL.getResult("SELECT * FROM Reports");
		try {
			while(rs.next()) {
				list.add(rs.getString("Spielername"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static String getReporter(String uuid) {
		if (MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Reports WHERE UUID='" + uuid + "'");
			try {
				if (rs.next()) {
					return rs.getString("Reporter");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return "";
		}
		return uuid;
	}
	
}
