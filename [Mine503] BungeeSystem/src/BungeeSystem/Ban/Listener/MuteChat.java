package BungeeSystem.Ban.Listener;

import java.util.UUID;

import BungeeSystem.Ban.Manager.BanManager;
import BungeeSystem.Ban.Manager.MuteManager;
import BungeeSystem.Ban.Manager.UUIDFetcher;
import BungeeSystem.Main.Main;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class MuteChat implements Listener {

	@EventHandler
	public void onChat(ChatEvent e) {
		ProxiedPlayer p = (ProxiedPlayer) e.getSender();
		
		if(MuteManager.isMuted(p.getUniqueId().toString())) {
			String uuid = UUIDFetcher.getUUID(p.getName()).toString();
			long current = System.currentTimeMillis();
			long end = MuteManager.getEnd(uuid).longValue();
//			if (((current < end ? 1 : 0) | (end == -1L ? 1 : 0)) != 0) {
			if ((current < end) || (end == -1L)) {
				if(!e.getMessage().startsWith("/")) {
					e.setCancelled(true);
//						p.sendMessage("§8)§7§l§m------------§8( §c§lMUTE §8)§7§l§m------------§8(");
//						p.sendMessage("");
//						p.sendMessage("§cDu bist derzeit gemuted.");
//						p.sendMessage("§7§lGrund§8: §4" + MuteManager.getReason(uuid));
//						p.sendMessage("§7§lGemuted von§8: §6" + MuteManager.getMutedBy(uuid));
//						p.sendMessage("§7§lZeitraum§8: §6" + MuteManager.getRemainingTime(uuid));
//						p.sendMessage("");
//						p.sendMessage("§8)§7§l§m------------§8( §c§lMUTE §8)§7§l§m------------§8(");
						p.sendMessage("§cDu wurdest aus dem Chat gebannt!");
						p.sendMessage("§cGemuted von§8: §6" + MuteManager.getMutedBy(uuid));
						p.sendMessage("§eMutegrund§8: §c" + MuteManager.getReason(uuid));
						p.sendMessage("§aVerbleibende Zeit§8: §e" + MuteManager.getRemainingTime(uuid));
				}
			} else {
				for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
					if(all.hasPermission("bungeesystem.ban.see")) {
//							all.sendMessage("§8)§7§l§m------------§8( §c§lMUTE §8)§7§l§m------------§8(");
//							all.sendMessage("");
//							all.sendMessage("§7§lSpieler entmuted§8: §6" + UUIDFetcher.getName(UUID.fromString(uuid)));
//							all.sendMessage("§7§lEntmuted von§8: §6SYSTEM");
//							all.sendMessage("");
//							all.sendMessage("§8)§7§l§m------------§8( §c§lMUTE §8)§7§l§m------------§8(");
							all.sendMessage(Main.bprefix + "§c§lDer Spieler §e§l" + UUIDFetcher.getName(UUID.fromString(uuid)) + " §c§lwurde vom §6§lSYSTEM §c§lentmuted.");
					}
				}
				MuteManager.unmute(uuid);
			}
		}
	}
	
}
