package BungeeSystem.Wartung;

import java.util.concurrent.TimeUnit;

import BungeeSystem.Main.Main;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class WartungListener implements Listener {

//	@EventHandler
//	public void onJoin(LoginEvent e) {
//		String player = e.getConnection().getName();
//		
//		if(Command_gWartung.getWartung() == true) {
//			for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
//				if(!all.hasPermission("bungeesystem.wartung.bypass")) {
//					e.setCancelled(true);
//					e.setCancelReason(Main.wprefix + "§cWir befinden uns derzeit in Wartungsarbeiten.");
//				}
//			}
//		}
//		
//	}
	
//	@EventHandler
//	public void onJoin(PostLoginEvent e) {
//		ProxiedPlayer p = e.getPlayer();
//		
//		if(Command_gWartung.getWartung() == true) {
//			if(!p.hasPermission("bungeesystem.wartung.bypass")) {
//				ProxyServer.getInstance().getScheduler().schedule(Main.instance, new Runnable() {
//					
//					@Override
//					public void run() {
//					
//						if((p == null) || (!p.isConnected())) {
//							return;
//						}
//						
//						p.disconnect("§8§m------------§r §6§lMine503 §8§m------------"
//						+ "\n§r"
//						+ "\n§r §cDas Netzwerk ist derzeit im Wartungsmodus." + "\n§r");
//						
//					}
//				}, 500L, TimeUnit.MILLISECONDS);	
//			}
//		}
//		
//	}
	
	@EventHandler
	public void onJoin(ServerConnectEvent e) {
		ProxiedPlayer p = e.getPlayer();
		
		if(Command_Wartung.getWartung() == true) {
			if(!p.hasPermission("bungeesystem.wartung.bypass")) {
				e.setCancelled(true);
				p.disconnect(
				"§8§m------------§r §6§lMine503 §8§m------------"
				+ "\n§r"
				+ "\n§r §cDas Netzwerk ist derzeit im Wartungsmodus." + "\n§r");
			} else {
				e.setCancelled(false);
			}
		}
		
	}
	
}
