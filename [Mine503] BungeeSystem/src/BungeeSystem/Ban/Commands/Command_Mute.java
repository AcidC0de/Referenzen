package BungeeSystem.Ban.Commands;

import BungeeSystem.Ban.Manager.BanManager;
import BungeeSystem.Ban.Manager.MuteManager;
import BungeeSystem.Main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_Mute extends Command {

	public static String reason;
	public static int time;
	
	public Command_Mute(String name) {
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
					if(MuteManager.isMuted(t.getUniqueId().toString())) {
						p.sendMessage(Main.bprefix + "Dieser Spieler ist bereits gemuted.");
						return;
					}
					if(t.hasPermission("bungeesystem.mute.bypass")) {
						p.sendMessage(Main.bprefix + "Du kannst diesen Spieler nicht muten!");
						return;
					}
 					if(t.getName() == p.getName()) {
 						p.sendMessage(Main.bprefix + "Du kannst dich nicht selber muten!");
 						return;
 					}
 					
 					if(args[1].equalsIgnoreCase("1")) {
 						reason = "Spamming";
 						time = 1800;
 					} else if(args[1].equalsIgnoreCase("2")) {
 						reason = "Beleidigung";
 						time = 3600;
 					} else if(args[1].equalsIgnoreCase("3")) {
 						reason = "Respektlosigkeit";
 						time = 3600;
 					} else if(args[1].equalsIgnoreCase("4")) {
 						reason = "Rassiusmus";
 						time = -1;
 					} else if(args[1].equalsIgnoreCase("5")) {
 						reason = "Werbung";
 						time = -1;
 					} else if(args[1].equalsIgnoreCase("6")) {
 						reason = "Radikalismus";
 						time = -1;
 					} else if(args[1].equalsIgnoreCase("7")) {
 						reason = "Drohung";
 						time = 259200;
 					} else if(args[1].equalsIgnoreCase("8")) {
 						reason =  "Provokation";
 						time = 1800;
 					} else if(args[1].equalsIgnoreCase("9")) {
 						reason = "Team-Beleidigung";
 						time = 86400;
 					} else if(args[1].equalsIgnoreCase("10")) {
 						reason = "Wiederholtes Schreiben in CapsLock";
 						time = 1800;
 					} else {
 						p.sendMessage(Main.bprefix + "Dies sind alle ID's§8:");
 						p.sendMessage("");
 						p.sendMessage("§8» §eSpamming §8| §c30 Minuten §8| §fID§8: §b1");
 						p.sendMessage("§8» §eBeleidigung §8| §c1 Stunde §8| §fID§8: §b2");
 						p.sendMessage("§8» §eRespektlosigkeit §8| §c1 Stunde §8| §fID§8: §b3");
 						p.sendMessage("§8» §eRassismus §8| §cPermanent §8| §fID§8: §b4");
 						p.sendMessage("§8» §eWerbung §8| §cPermanent §8| §fID§8: §b5");
 						p.sendMessage("§8» §eRadikalismus §8| §cPermanent §8| §fID§8: §b6");
 						p.sendMessage("§8» §eDrohung §8| §c3 Tage §8| §fID§8: §b7");
 						p.sendMessage("§8» §eProvokation §8| §c30 Minuten §8| §fID§8: §b8");
 						p.sendMessage("§8» §eTeam-Beleidigung §8| §c1 Tag §8| §fID§8: §b9");
 						p.sendMessage("§8» §eWiederholtes Schreiben in CapsLock §8| §c30 Minuten §8| §fID§8: §b10");
 						p.sendMessage("");
 					
 						return;
 					}
 					
 						MuteManager.mute(t.getUniqueId().toString(), t.getName(), reason, p.getName(), time);
 						for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
 							if(all.hasPermission("bungeesystem.ban.see")) {
 								all.sendMessage("§8)§7§l§m------------§8( §c§lMUTE §8)§7§l§m------------§8(");
 								all.sendMessage("");
 								all.sendMessage("§7§lSpieler gemuted§8: §6" + t.getName());
 								all.sendMessage("§7§lGemuted von§8: §6" + p.getName());
 								all.sendMessage("§7§lGrund§8: §4" + MuteManager.getReason(t.getUniqueId().toString()));
 								all.sendMessage("§7§lZeitraum§8: §6" + MuteManager.getRemainingTime(t.getUniqueId().toString()));
 								all.sendMessage("");
 								all.sendMessage("§8)§7§l§m------------§8( §c§lMUTE §8)§7§l§m------------§8(");	
 							}
 						}
 						
				} else {
					p.sendMessage(Main.bprefix + "Nutze§8: §7/Mute <Spieler> <ID>");
					p.sendMessage("");
					p.sendMessage("§8» §eSpamming §8| §c30 Minuten §8| §fID§8: §b1");
					p.sendMessage("§8» §eBeleidigung §8| §c1 Stunde §8| §fID§8: §b2");
					p.sendMessage("§8» §eRespektlosigkeit §8| §c1 Stunde §8| §fID§8: §b3");
					p.sendMessage("§8» §eRassismus §8| §cPermanent §8| §fID§8: §b4");
					p.sendMessage("§8» §eWerbung §8| §cPermanent §8| §fID§8: §b5");
					p.sendMessage("§8» §eRadikalismus §8| §cPermanent §8| §fID§8: §b6");
					p.sendMessage("§8» §eDrohung §8| §c3 Tage §8| §fID§8: §b7");
					p.sendMessage("§8» §eProvokation §8| §c30 Minuten §8| §fID§8: §b8");
					p.sendMessage("§8» §eTeam-Beleidigung §8| §c1 Tag §8| §fID§8: §b9");
					p.sendMessage("§8» §eWiederholtes Schreiben in CapsLock §8| §c30 Minuten §8| §fID§8: §b10");
					p.sendMessage("");
				}
			} else {
				p.sendMessage(Main.bnoperm);
			}
			
		} else {
			sender.sendMessages(Main.playeronly);
		}
		
		
	}

	
	
}

