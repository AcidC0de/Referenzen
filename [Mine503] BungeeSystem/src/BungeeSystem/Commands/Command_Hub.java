package BungeeSystem.Commands;

import BungeeSystem.Main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_Hub extends Command {

	public Command_Hub(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {		
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			
			if(args.length == 0) {
				p.connect(ProxyServer.getInstance().getServerInfo("Lobby-01"));
				p.sendMessage(Main.prefix + "§7Du wirst zur Lobby teleportiert...");
			} else {
				p.sendMessage(Main.usage + "/Hub");
			}
			
		} else {
			sender.sendMessage(Main.playeronly);
		}
		
	}

	
	
}
