package BungeeSystem.Commands;



import BungeeSystem.Main.Data;
import BungeeSystem.Main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_Teamchat extends Command {

	public static String rankcolor;
	
	public Command_Teamchat(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(p.hasPermission("bungeesystem.teamchat")) {
				if(args.length == 0) {
					p.sendMessage(Main.usage + "/Teamchat <Nachricht>");
				} else if(args.length >= 1) {
					StringBuilder builder = new StringBuilder();

					for (int i = 0; i < args.length; i++) {
						builder.append(args[i]);
						builder.append(" ");
					}

					String output = builder.substring(0, builder.length() - 1);
					
					for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
						if(all.hasPermission("bungeesystem.teamchat")) {
							all.sendMessage("§9TeamChat §8§l︳ §7 " + Data.getRank(p) + p.getName() + " §8» §f§o" + output);
						}
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
