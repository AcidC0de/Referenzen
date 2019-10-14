package BungeeSystem.Ban.Listener;


import BungeeSystem.Ban.Manager.MuteManager;
import BungeeSystem.Main.Data;
import BungeeSystem.Main.Main;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ChatFilter implements Listener {

	@EventHandler
	public void onChat(ChatEvent e) {
		ProxiedPlayer p = (ProxiedPlayer) e.getSender();
		String msg  = e.getMessage();
		
		for(String werbung : Main.werbung) {
			if ((msg.indexOf(werbung.toLowerCase()) != -1) && (!e.isCancelled())) {
				if(!p.hasPermission("bungeesystem.ban.admin")) {
					e.setCancelled(true);
					p.sendMessage(Main.bprefix + "Bitte mache keine Server Werbung!");
					for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
						if(all.hasPermission("bungeesystem.ban.see")) {
							all.sendMessage(Main.getHeader("§c§lWERBUNG"));
							all.sendMessage("§7Der Spieler §e" + p.getName() + " §7macht Server-Werbung.");
							all.sendMessage("§7Werbung§8: §b" + e.getMessage());
							all.sendMessage("§7Server§8: §6" + p.getServer().getInfo().getName());
							all.sendMessage(Main.getHeader("§c§lWERBUNG"));
						}
					}	
				}
			}
		}
		
		for(String insults : Main.insults) {
			if(!msg.startsWith("/")) {
				if ((msg.indexOf(insults.toLowerCase()) != -1) && (!e.isCancelled())) {
					if(!p.hasPermission("bungeesystem.ban.admin")) {
						e.setCancelled(true);
						MuteManager.mute(p.getUniqueId().toString(), p.getName(), "Unangemessenes Chatverhalten", "CONSOLE", 86400);
						for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
							if(all.hasPermission("bungeesystem.ban.see")) {
								all.sendMessage("§8)§7§l§m------------§8( §c§lMUTE §8)§7§l§m------------§8(");
								all.sendMessage("");
								all.sendMessage("§7§lSpieler gemuted§8: §6" + p.getName());
								all.sendMessage("§7§lGemuted von§8: §6CONSOLE");
								all.sendMessage("§7§lGrund§8: §4" + MuteManager.getReason(p.getUniqueId().toString()));
								all.sendMessage("§7§lZeitraum§8: §6" + MuteManager.getRemainingTime(p.getUniqueId().toString()));
								all.sendMessage("");
								all.sendMessage("§8)§7§l§m------------§8( §c§lMUTE §8)§7§l§m------------§8(");	
							}
						}	
					}
				}	
			}
		}
		
		for(String perminsults : Main.perminsults) {
			if(!msg.startsWith("/")) {
				if ((msg.indexOf(perminsults.toLowerCase()) != -1) && (!e.isCancelled())) {
					if(!p.hasPermission("bungeesystem.ban.admin")) {
						e.setCancelled(true);
						MuteManager.mute(p.getUniqueId().toString(), p.getName(), "Rassismus", "CONSOLE", -1);
						for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
							if(all.hasPermission("bungeesystem.ban.see")) {
								all.sendMessage("§8)§7§l§m------------§8( §c§lMUTE §8)§7§l§m------------§8(");
								all.sendMessage("");
								all.sendMessage("§7§lSpieler gemuted§8: §6" + p.getName());
								all.sendMessage("§7§lGemuted von§8: §6CONSOLE");
								all.sendMessage("§7§lGrund§8: §4" + MuteManager.getReason(p.getUniqueId().toString()));
								all.sendMessage("§7§lZeitraum§8: §6" + MuteManager.getRemainingTime(p.getUniqueId().toString()));
								all.sendMessage("");
								all.sendMessage("§8)§7§l§m------------§8( §c§lMUTE §8)§7§l§m------------§8(");	
							}
						}	
					}
				}	
			}
		}
		
		
		Main.insults.add("hurensohn");
		Main.insults.add("hure");
		Main.insults.add("huso");
		Main.insults.add("huensohn");
		Main.insults.add("huansohn");
		Main.insults.add("pisser");
		Main.insults.add("wichser");
		Main.insults.add("wixxer");
		Main.insults.add("wixer");
		Main.insults.add("bastard");
		Main.insults.add("schlampe");
		Main.insults.add("hundesohn");
		Main.insults.add("krüppel");
		Main.insults.add("faggot");
		Main.insults.add("retard");
		Main.insults.add("lutscher");
		Main.insults.add("misset");
		Main.insults.add("mushi");
		Main.insults.add("muschi");
		Main.insults.add("fotze");
		Main.insults.add("f*tze");
		Main.insults.add("pisser");
		
		Main.perminsults.add("Asylant");
		Main.perminsults.add("nigga");
		Main.perminsults.add("nigger");
		
		Main.werbung.add(".de");
		Main.werbung.add(".eu");
		Main.werbung.add(".net");
		Main.werbung.add(".me");
		Main.werbung.add(".com");
		Main.werbung.add(".at");
		Main.werbung.add(".tk");
		Main.werbung.add(",de");
		Main.werbung.add(",eu");
		Main.werbung.add(",net");
		Main.werbung.add(",me");
		Main.werbung.add(",com");
		Main.werbung.add(",at");
		Main.werbung.add(",tk");
		Main.werbung.add(".minecraft.to");
		Main.werbung.add(".nitrado.net");
		Main.werbung.add(",minecraft.to");
		Main.werbung.add(",nitrado.net");
		Main.werbung.add("Join alle");
		Main.werbung.add("Join Now");
		Main.werbung.add("Free OP");
		Main.werbung.add("FreeOP");
		Main.werbung.add("Free-OP");
		Main.werbung.add("[Punkt]");
		Main.werbung.add("Joint alle");
		Main.werbung.add(". de");
		Main.werbung.add(". eu");
		Main.werbung.add(". net");
		Main.werbung.add(". me");
		Main.werbung.add(". com");
		Main.werbung.add(". at");
		Main.werbung.add(". tk");
		Main.werbung.add(", de");
		Main.werbung.add(", eu");
		Main.werbung.add(", net");
		Main.werbung.add(", me");
		Main.werbung.add(", com");
		Main.werbung.add(", at");
		Main.werbung.add(", tk");
		Main.werbung.add(">>>>>> <<<<<<<");
		
	}
	
}

