package BungeeSystem.Commands;

import BungeeSystem.Main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_List extends Command {

	public Command_List(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			int online = ProxyServer.getInstance().getOnlineCount();
			int online2 = p.getServer().getInfo().getPlayers().size();
			p.sendMessage(Main.getHeader("Mine503"));
			p.sendMessage(" ");
			p.sendMessage("§7  Spieler auf dem Netzwerk§8: §a" + online);
			p.sendMessage("§7  Spieler auf §c" + p.getServer().getInfo().getName() + "§8: §a" + online2);
			p.sendMessage(" ");
			p.sendMessage(Main.getHeader("Mine503"));
		} else {
			int online = ProxyServer.getInstance().getOnlineCount();
			sender.sendMessage(Main.getHeader("Mine503"));
			sender.sendMessage(" ");
			sender.sendMessage("§7  Spieler auf dem Netzwerk§8: §a" + online);
			sender.sendMessage(" ");
			sender.sendMessage(Main.getHeader("Mine503"));	
		}
	}

	
	
}
