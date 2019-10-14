package CoinAPI.Main;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class CoinAPI implements Listener {

	public static boolean playerExists(String uuid) {
		try {
			ResultSet rs = MySQL.getResult("SELECT * FROM Coin WHERE UUID= '" + uuid + "'");
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
			MySQL.update("INSERT INTO Coin(UUID, COINS) VALUES ('" + uuid + "', '0');");
		}
	}

	public static Integer getCoins(String uuid) {
		Integer i = Integer.valueOf(0);
		if (playerExists(uuid)) {
			try {
				ResultSet rs = MySQL.getResult("SELECT * FROM Coin WHERE UUID= '" + uuid + "'");
				if (rs.next()) {
					Integer.valueOf(rs.getInt("COINS"));
				}
				i = Integer.valueOf(rs.getInt("COINS"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			createPlayer(uuid);
			getCoins(uuid);
		}
		return i;
	}

	public static void setCoins(String uuid, Integer coins) {
		if (playerExists(uuid)) {
			MySQL.update("UPDATE Coin SET COINS= '" + coins + "' WHERE UUID= '" + uuid + "';");
		} else {
			createPlayer(uuid);
			setCoins(uuid, coins);
		}
	}

	public static void addCoins(String uuid, Integer coins) {
		if (playerExists(uuid)) {
			setCoins(uuid, Integer.valueOf(getCoins(uuid).intValue() + coins.intValue()));
		} else {
			createPlayer(uuid);
			addCoins(uuid, coins);
		}
	}

	public static void removeCoins(String uuid, Integer coins) {
		if (playerExists(uuid)) {
			setCoins(uuid, Integer.valueOf(getCoins(uuid).intValue() - coins.intValue()));
		} else {
			createPlayer(uuid);
			removeCoins(uuid, coins);
		}
	}

	@EventHandler
	public void onJoinCreate(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (!playerExists(p.getUniqueId().toString())) {
			createPlayer(p.getUniqueId().toString());
		}
	}

}

