package BungeeSystem.Ban.Commands;

import BungeeSystem.Ban.Manager.BanManager;
import BungeeSystem.Ban.Manager.UUIDFetcher;
import BungeeSystem.Main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_Ban extends Command {

	public static String reason;
	public static int time;
	
	public Command_Ban(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {	
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(p.hasPermission("bungeesystem.ban")) {
				if(args.length == 2) {
					ProxiedPlayer t = ProxyServer.getInstance().getPlayer(args[0]);
					
					if (!ProxyServer.getInstance().getPlayers().contains(t)) {
						p.sendMessage(Main.bprefix + "Dieser Spieler ist nicht Online!");
						return;
					}
					if(BanManager.isBanned(t.getUniqueId().toString())) {
						p.sendMessage(Main.bprefix + "Dieser Spieler ist bereits gebannt.");
						return;
					}
					if(t.hasPermission("bungeesystem.ban.bypass")) {
						p.sendMessage(Main.bprefix + "Du kannst diesen Spieler nicht bannen!");
						return;
					}
 					if(t.getName() == p.getName()) {
 						p.sendMessage(Main.bprefix + "Du kannst dich nicht selber bannen!");
 						return;
 					}
 					
					if (args[1].equalsIgnoreCase("1")) {
						reason = "Hacking";
						time = 2592000;
					} else if (args[1].equalsIgnoreCase("2")) {
						reason = "Teaming";
						time = 3600;
					} else if (args[1].equalsIgnoreCase("3")) {
						reason = "Betrug";
						time = 259200;
					} else if (args[1].equalsIgnoreCase("4")) {
						reason = "Rang-Ausnutzung";
						time = 86400;
					} else if (args[1].equalsIgnoreCase("5")) {
						reason = "Mehrfache Beleidigung";
						time = 43200;
					} else if (args[1].equalsIgnoreCase("6")) {
						reason = "Belästigung";
						time = 1800;
					} else if (args[1].equalsIgnoreCase("7")) {
						reason = "BugUsing";
						time = 604800;
					} else if (args[1].equalsIgnoreCase("8")) {
						reason = "Trolling";
						time = 43200;
					} else if (args[1].equalsIgnoreCase("9")) {
						reason = "Report-Abuse";
						time = 7200;
					} else if (args[1].equalsIgnoreCase("10") && p.hasPermission("bungeesystem.ban.admin")) {
						reason = "Ban einss Admins";
						time = -1;
					} else if (args[1].equalsIgnoreCase("11") && p.hasPermission("bungeesystem.ban.admin")) {
						reason = "Werbung";
						time = -1;
					} else if (args[1].equalsIgnoreCase("12") && p.hasPermission("bungeesystem.ban.admin")) {
						reason = "Rassismus";
						time = -1;
					} else {
						p.sendMessage(Main.bprefix + "Dies sind alle ID's§8:");
						p.sendMessage("");
						p.sendMessage("§8» §eHacking §8| §c30 Tage §8| §fID§8: §b1");
						p.sendMessage("§8» §eTeaming §8| §c1 Stunde §8| §fID§8: §b2");
						p.sendMessage("§8» §eBetrug §8| §c3 Tage §8| §fID§8: §b3");
						p.sendMessage("§8» §eAusnutzen des Ranges §8| §c1 Tag §8| §fID§8: §b4");
						p.sendMessage("§8» §eMehrfache Beleidigung §8| §c6 Stunden §8| §fID§8: §b5");
						p.sendMessage("§8» §eBelästigung §8| §c30 Minuten - 1 Stunde §8| §fID§8: §b6");
						p.sendMessage("§8» §eBugUsing §8| §c1 Woche §8| §fID§8: §b7");
						p.sendMessage("§8» §eTrolling §8| §c3 Tage §8| §fID§8: §b8");
						p.sendMessage("§8» §eReport-Abuse §8| §c2 Stunden §8| §fID§8: §b9");
						if (p.hasPermission("bungeesystem.ban.admin")) {
							p.sendMessage("§8» §eBan eines Admins §8| §cPermanent §8| §fID§8: §b10 §8| §4§lAdmin Only");
							p.sendMessage("§8» §eWerbung §8| §cPermanent §8| §fID§8: §b11 §8| §4§lAdmin Only");
							p.sendMessage("§8» §eRassismus §8| §cPermanent §8| §fID§8: §b12 §8| §4§lAdmin Only");
						}
						p.sendMessage("");

						return;
					}
					
					
					
 					
 						BanManager.ban(t.getUniqueId().toString(), t.getName(), reason, p.getName(), time);
 						for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
 							if(all.hasPermission("bungeesystem.ban.see")) {
 								all.sendMessage("§8)§7§l§m------------§8( §c§lBAN §8)§7§l§m------------§8(");
 								all.sendMessage("");
 								all.sendMessage("§7§lSpieler gebannt§8: §6" + t.getName());
 								all.sendMessage("§7§lGebannt von§8: §6" + p.getName());
 								all.sendMessage("§7§lGrund§8: §4" + BanManager.getReason(t.getUniqueId().toString()));
 								all.sendMessage("§7§lZeitraum§8: §6" + BanManager.getRemainingTime(t.getUniqueId().toString()));
 								all.sendMessage("");
 								all.sendMessage("§8)§7§l§m------------§8( §c§lBAN §8)§7§l§m------------§8(");	
 							}
 						}
 						
				} else {
					p.sendMessage(Main.bprefix + "Nutze§8: §7/Ban <Spieler> <ID>");
					p.sendMessage("");
					p.sendMessage("§8» §eHacking §8| §c30 Tage §8| §fID§8: §b1");
					p.sendMessage("§8» §eTeaming §8| §c1 Stunde §8| §fID§8: §b2");
					p.sendMessage("§8» §eBetrug §8| §c3 Tage §8| §fID§8: §b3");
					p.sendMessage("§8» §eAusnutzen des Ranges §8| §c1 Tag §8| §fID§8: §b4");
					p.sendMessage("§8» §eMehrfache Beleidigung §8| §c6 Stunden §8| §fID§8: §b5");
					p.sendMessage("§8» §eBelästigung §8| §c30 Minuten - 1 Stunde §8| §fID§8: §b6");
					p.sendMessage("§8» §eBugUsing §8| §c1 Woche §8| §fID§8: §b7");
					p.sendMessage("§8» §eTrolling §8| §c3 Tage §8| §fID§8: §b8");
					p.sendMessage("§8» §eReport-Abuse §8| §c2 Stunden §8| §fID§8: §b9");
					if(p.hasPermission("bungeesystem.ban.admin")) {
						p.sendMessage("§8» §eBan eines Admins §8| §cPermanent §8| §fID§8: §b10 §8| §4§lAdmin Only");
						p.sendMessage("§8» §eWerbung §8| §cPermanent §8| §fID§8: §b11 §8| §4§lAdmin Only");
						p.sendMessage("§8» §eRassismus §8| §cPermanent §8| §fID§8: §b12 §8| §4§lAdmin Only");	
					}
					p.sendMessage("");
				}
			} else {
				p.sendMessage(Main.bnoperm);
			}
			
		}else {
			sender.sendMessages(Main.playeronly);
		}
		
		
	}

	
	
}
