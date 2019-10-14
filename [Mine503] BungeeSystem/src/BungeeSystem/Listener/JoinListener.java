package BungeeSystem.Listener;

import java.util.List;
import java.util.concurrent.TimeUnit;

import BungeeSystem.Main.Main;
import BungeeSystem.Report.ReportManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class JoinListener implements Listener {

	@EventHandler
	public void onJoinReport(PostLoginEvent e) {
		List list = ReportManager.getReportedPlayers();

		if (list.size() >= 0) {
			for (ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
				if (all.hasPermission("bungeesystem.report.use")) {
					all.sendMessage(Main.rprefix + "Es sind noch §6" + list.size() + " §7Reports offen.");
				}
			}
		}
	}
}
