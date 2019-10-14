package BungeeSystem.Ban.Manager;

import java.sql.ResultSet;
import java.sql.SQLException;

import BungeeSystem.Main.MySQL;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class WarnManager implements Listener {

	public static boolean playerExists(String uuid) {
		try {
			ResultSet rs = MySQL.getResult("SELECT * FROM Warns WHERE UUID= '" + uuid + "'");
			if (rs.next()) {
				return rs.getString("UUID") != null;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void createPlayer(String uuid) {
		if (!playerExists(uuid)) {
			MySQL.update("INSERT INTO Warns(UUID, WARNINGS) VALUES ('" + uuid + "', '0');");
		}
	}
	
	public static Integer getStrikes(String uuid) {
		Integer i = Integer.valueOf(0);
		if (playerExists(uuid)) {
			try {
				ResultSet rs = MySQL.getResult("SELECT * FROM Warns WHERE UUID= '" + uuid + "'");
				if (rs.next()) {
					Integer.valueOf(rs.getInt("WARNINGS"));
				}
				i = Integer.valueOf(rs.getInt("WARNINGS"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			createPlayer(uuid);
			getStrikes(uuid);
		}
		return i;
	}

	public static void setStrikes(String uuid, Integer strikes) {
		if (playerExists(uuid)) {
			MySQL.update("UPDATE Warns SET WARNINGS= '" + strikes + "' WHERE UUID= '" + uuid + "';");
		} else {
			createPlayer(uuid);
			setStrikes(uuid, strikes);
		}
	}
	
	public static void removeStrikes(String uuid, Integer strikes) {
		if (playerExists(uuid)) {
			setStrikes(uuid, Integer.valueOf(getStrikes(uuid).intValue() - strikes.intValue()));
		} else {
			createPlayer(uuid);
			removeStrikes(uuid, strikes);
		}
	}
	
	@EventHandler
	public void onJoinCreate(PostLoginEvent e) {
		ProxiedPlayer p = e.getPlayer();
		if (!playerExists(p.getUniqueId().toString())) {
			createPlayer(p.getUniqueId().toString());
		}
	}
	
}
