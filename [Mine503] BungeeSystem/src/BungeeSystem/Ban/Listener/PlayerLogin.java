package BungeeSystem.Ban.Listener;

import java.util.UUID;

import BungeeSystem.Ban.Manager.BanManager;
import BungeeSystem.Ban.Manager.UUIDFetcher;
import BungeeSystem.Main.Main;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerLogin implements Listener {

	@EventHandler
	public void onJoin(LoginEvent e) {
		String uuid = UUIDFetcher.getUUID(e.getConnection().getName()).toString();
		if(BanManager.isBanned(uuid)) {
			long current = System.currentTimeMillis();
			long end = BanManager.getEnd(uuid).longValue();
			if ((current < end) || (end == -1L)) {
				e.setCancelled(true);
				e.setCancelReason(					
						"§f§lDu bist auf dem §c§lMINE503 §f§lNetzwerk gebannt!"
						+ "\n\n§7Accountname: §a" + e.getConnection().getName()
						+ "\n§7Grund §8» §4 " + BanManager.getReason(uuid)
						+ "\n§7Gebannt von §8» §6 " + BanManager.getBannedBy(uuid)
						+ "\n§7Verbleibende Zeit §8» §6 " + BanManager.getRemainingTime(uuid)
						+ "\n\n§aDu kannst im §6Forum §aeinen §6Entbannungsantrag §astellen.");
			} else {
				e.setCancelled(false);
				for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
					if(all.hasPermission("bungeesystem.ban.see")) {
//							all.sendMessage("§8)§7§l§m------------§8( §c§lBAN §8)§7§l§m------------§8(");
//							all.sendMessage("");
//							all.sendMessage("§7§lSpieler entbannt§8: §6" + UUIDFetcher.getName(UUID.fromString(uuid)));
//							all.sendMessage("§7§lEntbannt von§8: §6SYSTEM");
//							all.sendMessage("");
//							all.sendMessage("§8)§7§l§m------------§8( §c§lBAN §8)§7§l§m------------§8(");
							all.sendMessage(Main.bprefix + "§c§lDer Spieler §e§l" + UUIDFetcher.getName(UUID.fromString(uuid)) + " §c§lwurde vom §6§lSYSTEM §c§lentbannt.");
					}
				}
				BanManager.unban(uuid);
			}
		}
	}
	
	private String getUUID(String playername)
	{
		return ProxyServer.getInstance().getPlayer(playername).getUniqueId().toString();
	}
	
}
