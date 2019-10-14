package BungeeSystem.Listener;

import BungeeSystem.Main.Main;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.TabCompleteEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class TabComplete implements Listener {

	private Main main;

	public TabComplete(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onTabComplete(TabCompleteEvent e) {
	    String partialPlayerName = e.getCursor().toLowerCase();
	    
	    int lastSpaceIndex = partialPlayerName.lastIndexOf(' ');
	    if (lastSpaceIndex >= 0) {
	      partialPlayerName = partialPlayerName.substring(lastSpaceIndex + 1);
	    }
	    for (ProxiedPlayer p : this.main.getProxy().getPlayers()) {
	      if (p.getName().toLowerCase().startsWith(partialPlayerName)) {
	        e.getSuggestions().add(p.getName());
	      }
	    }
	}
	
}
