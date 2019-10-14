package Clan.Listener;

import java.util.List;

import Clan.Main.Data;
import Clan.MySQL.ClanManager;
import Clan.Util.InviteManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class QuitListener implements Listener {

	@EventHandler
	public void onQuit(PlayerDisconnectEvent e) {
		ProxiedPlayer p = (ProxiedPlayer) e.getPlayer();
		if (InviteManager.invites.containsKey(p.getUniqueId().toString())) {
			InviteManager.removePlayer(p.getUniqueId().toString());
		}

		List members = ClanManager.getMembers(ClanManager.getClanName(p.getUniqueId().toString()));

		for (int i = 0; i < members.size(); i++) {
			ProxiedPlayer t = ProxyServer.getInstance().getPlayer((String) ClanManager.getMembers(ClanManager.getClanName(p.getUniqueId().toString())).get(i));
			if (t != null) {
				t.sendMessage(Data.prefix + "§e" + p.getName() + " §7ist nun §cOffline§7.");
			}
		}
	}
}
