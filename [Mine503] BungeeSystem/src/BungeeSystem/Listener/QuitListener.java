package BungeeSystem.Listener;

import BungeeSystem.Report.ReportManager;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class QuitListener implements Listener {

	@EventHandler
	public void onQuitWhileReported(PlayerDisconnectEvent e) {
		ProxiedPlayer p = e.getPlayer();
		
		if(ReportManager.isReported(p.getUniqueId().toString())) {
			ReportManager.deleteReport(p.getUniqueId().toString());
		}
	}
	
}
