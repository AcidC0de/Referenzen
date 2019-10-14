package FFA.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import FFA.Main.Data;

public class PlayerQuit implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		
		Player p = e.getPlayer();
		
//		if(p.hasPermission("ffa.team")) {
//			e.setQuitMessage(Data.prefix + "Das Teammitglied §e" + p.getName() + " §7hat den Server verlassen.");
//		} else {
//			e.setQuitMessage("");
//		}
		
		e.setQuitMessage("");
		
	}
	
}
