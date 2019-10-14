package BungeeSystem.Commands;

import BungeeSystem.Main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_Perms extends Command {

	public Command_Perms(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(args.length == 0) {
				p.sendMessage(Main.noperm);
			} else if(args.length != 0) {
				p.sendMessage(Main.noperm);
			}
		} else {
			sender.sendMessage(Main.playeronly);
		}
		
	}

	
	
}
