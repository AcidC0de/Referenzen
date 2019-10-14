package BungeeSystem.Commands;

import BungeeSystem.Main.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_Broadcast extends Command {

	public Command_Broadcast(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			
			if(p.hasPermission("bungeesystem.broadcast")) {
				if(args.length == 0) {
					p.sendMessage(Main.usage + "/gb <Nachricht>");
				} else if(args.length >= 1) {
					
					StringBuilder sb = new StringBuilder();
					for(String message : args) {
						sb.append(message + " ");
					}
					
					ProxyServer.getInstance().broadcast(new TextComponent(Main.prefix + ChatColor.translateAlternateColorCodes('&', sb.toString())));
				}
			} else {
				p.sendMessage(Main.noperm);
			}
			
		} else {
			sender.sendMessage(Main.playeronly);
		}
	}

}
