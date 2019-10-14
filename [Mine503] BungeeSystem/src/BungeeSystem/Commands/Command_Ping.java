package BungeeSystem.Commands;

import BungeeSystem.Main.Data;
import BungeeSystem.Main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_Ping extends Command {

	public Command_Ping(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			
			if(args.length == 0) {
				p.sendMessage(Main.prefix + "Dein Ping§8: §a" + p.getPing());
			} else if(args.length == 1) {
				ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
				if(target != null) {
					p.sendMessage(Main.prefix + Data.getRank(target) + target.getName() + "'s §7Ping§8: §a" + target.getPing());
				} else {
					p.sendMessage(Main.notonline);
				}
			} else if(args.length > 1){
				p.sendMessage(Main.usage + "/Ping <Spieler>");
			}
			
		} else {
			sender.sendMessage(Main.playeronly);
		}
	}	
}
