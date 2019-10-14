package BungeeSystem.Commands;

import BungeeSystem.Main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_Bungee extends Command {

	public Command_Bungee(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		ProxiedPlayer p = (ProxiedPlayer) sender;

		if(args.length >= 0) {
			sender.sendMessage(Main.prefix + "§3BungeeSystem §fby §bAcidC0de§f.");
		}

	}

}
