package BungeeSystem.Motd;

import java.util.UUID;

import BungeeSystem.Wartung.Command_Wartung;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyPing implements Listener {

	@EventHandler
	public void ping(ProxyPingEvent e) {
		ServerPing ping = e.getResponse();
		ServerPing.Players players = ping.getPlayers();
		ServerPing.Protocol version = ping.getVersion();
		
		if(Command_Wartung.getWartung() == true) {
			version.setName("§cWartungsarbeiten");
			version.setProtocol(2);
			players.setSample(new ServerPing.PlayerInfo[] { new ServerPing.PlayerInfo("§4§lDas Netzwerk befindet sich derzeit in Wartungsarbeiten.", UUID.randomUUID()) });
			ping.setDescription("§6Mine503 §8● §fMinigame Network §8● §7[§c1.8.x§7] \n§c§lWartungsarbeiten");
		} else if(Command_Wartung.getWartung() == false) {
//			version.setName("§cWartungsmodus §7" + ping.getPlayers().getOnline() + "§8/§7" + ping.getPlayers().getMax());
//			version.setName("§7" + ping.getPlayers().getOnline() + "§8/§71");
//			version.setProtocol(1);
//			players.setSample(new ServerPing.PlayerInfo[] { new ServerPing.PlayerInfo("\n§6§lMine503.de §8× §f§lDein Netzwerk \n§7Status §8» §cWartungsarbeiten \n§7Forum §8» §fMine503.de/Forum \n§7Teamspeak §8» §fMine503.de \n", UUID.randomUUID()) });
//			ping.setDescription("§e§lMINE503 §f§lNetzwerk §8[§61.8.x§8] §8» §cWartungsarbeiten! \n §8➥ §c§lEröffnung§8: §eTBA");
//			ping.setDescription("§6§lMINE503§f.§6§lDE §f- §fDein §6Minecraft §fNetzwerk! §f- §8[§a1§7.§a8§7.§ax§8] \n §8§l» §4§lWartungen §7| §c§lOffline");
			ping.setDescription("§6Mine503 §8● §fMinigame Network §8● §7[§c1.8.x§7] \n§8➥ §b§lOPEN BETA");	
		}
		
		e.setResponse(ping);
	}
	
}
