package BungeeSystem.Listener;

import java.util.concurrent.TimeUnit;

import BungeeSystem.Main.Main;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class TabFooter implements Listener {
	
	@EventHandler
	public void onJoin(PostLoginEvent e) {
//		ProxiedPlayer p = e.getPlayer();
		for(ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
			
			String Header = "§8» §6§lMINE503.DE §8«\n§7Du möchtest einen Spieler melden? §c/report §7<Spieler>";
			String Footer = "§7Teamspeak§8: §eMine503.de\n§7Forum§8: §eMine503.de/Forum\n§7Spenden§8: §epatreon.com/Mine503";
//			p.setTabHeader(new TextComponent("§8» §6§lMINE503.DE §8«\n§aDu spielst auf: §a" +  + "\n§7Du möchtest einen Spieler melden? §c/report §7<Spieler>"), new TextComponent("§7Teamspeak§8: §eMine503.de\n§7Forum§8: §eMine503.de/Forum"));
			p.setTabHeader(new TextComponent(Header), new TextComponent(Footer));	
		}
	}
	
//	@EventHandler
//	public void onConnect(ServerConnectEvent e) {
//		try {
//			for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
//				String header = "§6Mine503.de §8- §a" + all.getServer().getInfo().getName() + "\n§7Du möchtest einen Spieler melden? §c/report §7<Spieler>";
//				String footer = "§7Füge deine Freunde hinzu, erstelle Partys oder gründe\n§7deinen eigenen Clan mit§8: §a/friend§7, §d/party§7 und §b/clans";
//				all.setTabHeader(new TextComponent(header), new TextComponent(footer));
//			}
//		} catch (Exception ex) {}
//	}
//	
//	@EventHandler
//	public void onDisconnect(ServerDisconnectEvent e) {
//		try {
//			for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
//				String header = "§6Mine503.de §8- §a" + all.getServer().getInfo().getName() + "\n§7Du möchtest einen Spieler melden? §c/report §7<Spieler>";
//				String footer = "§7Füge deine Freunde hinzu, erstelle Partys oder gründe\n§7deinen eigenen Clan mit§8: §a/friend§7, §d/party§7 und §b/clans";
//				all.setTabHeader(new TextComponent(header), new TextComponent(footer));
//			}
//		} catch (Exception ex) {}
//	}
//	
//	@EventHandler
//	public void onServerSwitch(ServerSwitchEvent e) {
//		String server = e.getPlayer().getServer().getInfo().getName();
//		try {
//			ProxyServer.getInstance().getScheduler().schedule(Main.instance, new Runnable() {
//				
//				@Override
//				public void run() {
//					for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
//						String header = "§6Mine503.de §8- §a" + all.getServer().getInfo().getName() + "\n§7Du möchtest einen Spieler melden? §c/report §7<Spieler>";
//						String footer = "§7Füge deine Freunde hinzu, erstelle Partys oder gründe\n§7deinen eigenen Clan mit§8: §a/friend§7, §d/party§7 und §b/clans";
//						all.setTabHeader(new TextComponent(header), new TextComponent(footer));
//					}
//				}
//			}, 15L, TimeUnit.MILLISECONDS);
//		} catch (Exception ex) {}
//	}
}
