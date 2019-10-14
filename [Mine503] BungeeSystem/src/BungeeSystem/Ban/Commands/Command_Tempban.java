package BungeeSystem.Ban.Commands;

import java.util.List;

import BungeeSystem.Ban.Manager.BanManager;
import BungeeSystem.Ban.Manager.BanUnit;
import BungeeSystem.Main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_Tempban extends Command {

	public Command_Tempban(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if (p.hasPermission("bungeesystem.ban.tempban")) {
				if (args.length >= 4) {
					ProxiedPlayer t = ProxyServer.getInstance().getPlayer(args[0]);

					if (BanManager.isBanned(t.getUniqueId().toString())) {
						p.sendMessage(Main.bprefix + "Dieser Spieler ist bereits gebannt.");
						return;
					}
					if (t.hasPermission("bungeesystem.ban.bypass")) {
						p.sendMessage(Main.bprefix + "Du kannst diesen Spieler nicht bannen!");
						return;
					}
					if (t == null) {
						p.sendMessage(Main.bprefix + "Dieser Spieler ist nicht Online!");
						return;
					}
					if (t.getName() == p.getName()) {
						p.sendMessage(Main.bprefix + "Du kannst dich nicht selber bannen!");
						return;
					}

					try {
						String reason = " ";
						for (int i = 1; i < args.length; i++) {
							reason = reason + args[i] + " ";
						}
					} catch (NumberFormatException e) {
						sender.sendMessage(Main.prefix + "§7Der ZAHLENWERT muss eine ZAHL sein. [z.B.: 5, 65 oder 99]");
						return;
					}
					long value = Integer.valueOf(args[1]).intValue();
					if (value >= 365L) {
						sender.sendMessage(Main.prefix + "§7Dein Wert muss unter 365 liegen!");
					}
					String unitString = args[2];
					String reason1 = "";
					for (int i1 = 3; i1 < args.length; i1++) {
						reason1 = reason1 + args[i1] + " ";
					}
					List unitList = BanUnit.getUnitsAsString();
					if (unitList.contains(unitString.toLowerCase())) {
						BanUnit unit = BanUnit.getUnit(unitString);
			            long seconds = value * unit.getToSecond();
			            BanManager.ban(t.getUniqueId().toString(), t.getName(), reason1, p.getName(), seconds);
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
			            return;
					}
					p.sendMessage(Main.bprefix + "§7Diese EINHEIT existiert nicht! [Sekunde - sec | Minute - min | Stunde - hour | Tag - day | Woche - week]");
					return;
				} else {
					p.sendMessage(Main.bprefix + "Nutze§7; §7/Tempban <Spieler> <Zahlenwert> <Zeitangabe> <Grund>");
				}
			} else {
				p.sendMessage(Main.noperm);
			}
		} else {
			sender.sendMessage(Main.playeronly);
		}

	}

}
