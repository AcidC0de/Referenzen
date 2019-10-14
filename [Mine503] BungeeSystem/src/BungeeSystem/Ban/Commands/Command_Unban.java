package BungeeSystem.Ban.Commands;

import BungeeSystem.Ban.Manager.BanManager;
import BungeeSystem.Ban.Manager.UUIDFetcher;
import BungeeSystem.Main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_Unban extends Command {

	public Command_Unban(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(p.hasPermission("bungeesystem.unban")) {
				if(args.length == 1) {
					String playername = args[0];
					
					if(!BanManager.isBanned(UUIDFetcher.getUUID(playername).toString())) {
						p.sendMessage(Main.bprefix + "Dieser Spieler ist nicht gebannt!");
					} else {
						BanManager.unban(UUIDFetcher.getUUID(playername).toString());
						for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
							if(all.hasPermission("bungeesystem.ban.see")) {
//								all.sendMessage("§8)§7§l§m------------§8( §c§lBAN §8)§7§l§m------------§8(");
//								all.sendMessage("");
//								all.sendMessage("§7§lSpieler entbannt§8: §6" + playername);
//								all.sendMessage("§7§lEntbannt von§8: §6" + p.getName());
//								all.sendMessage("");
//								all.sendMessage("§8)§7§l§m------------§8( §c§lBAN §8)§7§l§m------------§8(");
								all.sendMessage(Main.bprefix + "§c§lDer Spieler §e§l" + playername + " §c§lwurde vom §6§l" + p.getName() + " §c§lentbannt.");
							}
						}
					}
					
				} else {
					p.sendMessage(Main.bprefix + "Nutze§8: §7/Unban <Spieler>");
				}
			} else {
				p.sendMessage(Main.bnoperm);
			}
		} else {
			sender.sendMessage(Main.playeronly);
		}
	}	
}
