package CoinAPI.Main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		if(!MySQL.isConnected()) {
			MySQL.connect();
		}
	}
	
}
