package BungeeSystem.Commands;

import BungeeSystem.Main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_Youtuber extends Command {

	public Command_Youtuber(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("100")) {
					p.sendMessage("§7  Vorraussetzungen für §5Premium§d+§8:");
					p.sendMessage("§8  ➵ §7Dein Kanal muss mindestens §c100 Abonnenten §7besitzen.");
					p.sendMessage("§8  ➵ §7Du benötigst eine angemessene Klickzahl.");
					p.sendMessage("§8  ➵ §7Du musst mindestens §c1 Video §7auf dem Netzwerk aufnehmen.");
					p.sendMessage("§8  ➵ §7Dieses Video muss eine angemessene §cQualität §7und §cLänge §7haben.");
					p.sendMessage("§8  ➵ §7Du erüllst alle Anforderungen? Beantrage den §5Premium§d+ §7Rang im §cForum §7oder §cTs³");
				}
				if(args[0].equalsIgnoreCase("500")) {
					p.sendMessage("§7  Vorraussetzungen für §5Youtuber§8:");
					p.sendMessage("§8  ➵ §7Dein Kanal muss mindestens §c500 Abonnenten §7besitzen.");
					p.sendMessage("§8  ➵ §7Du benötigst eine angemessene Klickzahl.");
					p.sendMessage("§8  ➵ §7Du musst mindestens §c1 Video §7auf dem Netzwerk aufnehmen.");
					p.sendMessage("§8  ➵ §7Dieses Video muss eine angemessene §cQualität §7und §cLänge §7haben.");
					p.sendMessage("§8  ➵ §7Du erüllst alle Anforderungen? Beantrage den §5Youtuber §7Rang im §cForum §7oder §cTs³");
				}
			} else {
				p.sendMessage(Main.prefix + "Nutze §c/Youtuber 100 §7um die Anforderungen für §5Premium§d+ §7zu sehen.");
				p.sendMessage(Main.prefix + "Nutze §c/Youtuber 500 §7um die Anforderungen für §5Youtuber §7zu sehen.");
			}
			
		} else {
			sender.sendMessage(Main.playeronly);
		}
		
	}

	
	
}
