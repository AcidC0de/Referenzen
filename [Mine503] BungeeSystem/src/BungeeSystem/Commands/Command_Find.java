package BungeeSystem.Commands;

import BungeeSystem.Main.Data;
import BungeeSystem.Main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_Find extends Command {

	public Command_Find(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(p.hasPermission("bungeesystem.find")) {
				if(args.length != 1) {
					p.sendMessage(Main.usage + "/Find <Spieler>");
				} else if(args.length == 1) {
					ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
					if(target != null) {
						p.sendMessage(Main.prefix + Data.getRank(target) + target.getName() + " §7befindet sich auf§8: §c" + target.getServer().getInfo().getName());
					} else {
						p.sendMessage(Main.notonline);
					}
				}
			} else {
				p.sendMessage(Main.noperm);
			}
		} else {
			sender.sendMessage(Main.playeronly);
		}
	}

	
	
}
