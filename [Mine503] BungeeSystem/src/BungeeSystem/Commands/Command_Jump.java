package BungeeSystem.Commands;

import BungeeSystem.Main.Data;
import BungeeSystem.Main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_Jump extends Command {

	public Command_Jump(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if (p.hasPermission("bungeesystem.jump")) {
				if (args.length != 1) {
					p.sendMessage(Main.usage + "/jump <Spieler>");
				} else if (args.length == 1) {
					ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
					if (target != null) {
						ServerInfo server = target.getServer().getInfo();
						if (target.getServer().getInfo() != p.getServer().getInfo()) {
							p.connect(server);
							p.sendMessage(Main.prefix + "Du bist zu "+ Data.getRank(target) + target.getName() + " §7gesprungen.§8(§5" + server.getName() + "§8)");
						} else {
							p.sendMessage(Main.prefix + "§cDu befindest dich bereits auf diesem Server!");
						}
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
