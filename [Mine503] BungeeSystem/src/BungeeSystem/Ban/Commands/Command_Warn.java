package BungeeSystem.Ban.Commands;

import BungeeSystem.Ban.Manager.BanManager;
import BungeeSystem.Ban.Manager.WarnManager;
import BungeeSystem.Main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_Warn extends Command {

	public Command_Warn(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(p.hasPermission("bungeesystem.warn")) {
				if(args.length == 1) {
					ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
					
					if(target == null) {
						p.sendMessage(Main.bprefix + "Dieser Spieler ist nicht Online!");
						return;
					}
					if(target.getName() == p.getName()) {
						p.sendMessage(Main.bprefix + "Du kannst dich nicht selber Verwarnen!");
						return;
					}
					if(target.hasPermission("bungeesystem.warn.bypass")) {
						p.sendMessage(Main.bprefix + "Du kannst diesen Spieler nicht Verwarnen!");
						return;
					}
					
					if(WarnManager.getStrikes(target.getUniqueId().toString()) == 0) {
						p.sendMessage(Main.bprefix + "Der Spieler §a" + target.getName() + " §7hat nun §61 §7Verwarnung!");
						WarnManager.setStrikes(target.getUniqueId().toString(), 1);
						for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
							if(all.hasPermission("bungeesystem.ban.see")) {
								all.sendMessage(Main.bprefix + "Der Spieler §6" + target.getName() + " §7wurde vom Netzwerk gekickt! Grund§8: §41te Verwarnung");
							}
						}
						target.disconnect("§f§lDu wurdest von dem §c§lMINE503 §f§lNetzwerk geworfen!\n\n§7Grund §8» §41te Verwarnung\n§7Verwarnt von §8» §6" + p.getName() + "\n\n§7Du hast nun §61 §fVerwarnung!\n§cBei §63 §cVerwarnungen wirst du §4§lPERMANENT §cgebannt.");
					} else if(WarnManager.getStrikes(target.getUniqueId().toString()) == 1) {
						p.sendMessage(Main.bprefix + "Der Spieler §a" + target.getName() + " §7hat nun §62 §7Verwarnungen!");
						WarnManager.setStrikes(target.getUniqueId().toString(), 2);
						
						BanManager.ban(target.getUniqueId().toString(), target.getName(), "2te Verwarnung", "CONSOLE", 86400);
						for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
							if(all.hasPermission("bungeesystem.ban.see")) {
 								all.sendMessage("§8)§7§l§m------------§8( §c§lWARN §8)§7§l§m------------§8(");
 								all.sendMessage("");
 								all.sendMessage("§7§lSpieler gebannt§8: §6" + target.getName());
 								all.sendMessage("§7§lGebannt von§8: §6" + p.getName());
 								all.sendMessage("§7§lGrund§8: §4" + BanManager.getReason(target.getUniqueId().toString()));
 								all.sendMessage("§7§lZeitraum§8: §6" + BanManager.getRemainingTime(target.getUniqueId().toString()));
 								all.sendMessage("");
 								all.sendMessage("§8)§7§l§m------------§8( §c§lWARN §8)§7§l§m------------§8(");
							}
						}
						
					} else if(WarnManager.getStrikes(target.getUniqueId().toString()) == 2) {
						p.sendMessage(Main.bprefix + "Der Spieler §a" + target.getName() + " §7hat nun §63 §7Verwarnungen!");
						WarnManager.setStrikes(target.getUniqueId().toString(), 3);
						
						BanManager.ban(target.getUniqueId().toString(), target.getName(), "3te Verwarnung", "CONSOLE", -1);
						for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
							if(all.hasPermission("bungeesystem.ban.see")) {
 								all.sendMessage("§8)§7§l§m------------§8( §c§lWARN §8)§7§l§m------------§8(");
 								all.sendMessage("");
 								all.sendMessage("§7§lSpieler gebannt§8: §6" + target.getName());
 								all.sendMessage("§7§lGebannt von§8: §6" + p.getName());
 								all.sendMessage("§7§lGrund§8: §4" + BanManager.getReason(target.getUniqueId().toString()));
 								all.sendMessage("§7§lZeitraum§8: §6" + BanManager.getRemainingTime(target.getUniqueId().toString()));
 								all.sendMessage("");
 								all.sendMessage("§8)§7§l§m------------§8( §c§lWARN §8)§7§l§m------------§8(");
							}
						}
						
					}
				} else if(args.length == 2) {
					if(args[0].equalsIgnoreCase("reset")) {
						String playername = args[1];
						if(WarnManager.getStrikes(getUUID(playername)) != 0) {
							WarnManager.setStrikes(getUUID(playername), 0);
							if(BanManager.isBanned(getUUID(playername))) {
								BanManager.unban(getUUID(playername));
							}
							p.sendMessage(Main.bprefix + "Du hast die Verwarnungen von §a" + playername + " §7wieder auf §60 §7gesetzt.");
						}
					}
				} else {
					p.sendMessage(Main.bprefix + "Nutze§8: §7/Warn (reset) <Spieler>");
				}
			} else {
				p.sendMessage(Main.bnoperm);
			}
		} else {
			sender.sendMessage(Main.playeronly);
		}
		
	}

	private String getUUID(String playername)
	{
		return ProxyServer.getInstance().getPlayer(playername).getUniqueId().toString();
	}
	
}
