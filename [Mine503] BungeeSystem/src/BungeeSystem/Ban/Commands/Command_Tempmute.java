package BungeeSystem.Ban.Commands;

import java.util.List;

import BungeeSystem.Ban.Manager.BanManager;
import BungeeSystem.Ban.Manager.MuteManager;
import BungeeSystem.Ban.Manager.MuteUnit;
import BungeeSystem.Main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_Tempmute extends Command {

	public Command_Tempmute(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if (p.hasPermission("bungeesystem.ban.tempmute")) {
				if (args.length >= 4) {
					ProxiedPlayer t = ProxyServer.getInstance().getPlayer(args[0]);

					if (MuteManager.isMuted(t.getUniqueId().toString())) {
						p.sendMessage(Main.bprefix + "Dieser Spieler ist bereits gemuted.");
						return;
					}
					if (t.hasPermission("bungeesystem.mute.bypass")) {
						p.sendMessage(Main.bprefix + "Du kannst diesen Spieler nicht muten!");
						return;
					}
					if (t == null) {
						p.sendMessage(Main.bprefix + "Dieser Spieler ist nicht Online!");
						return;
					}
					if (t.getName() == p.getName()) {
						p.sendMessage(Main.bprefix + "Du kannst dich nicht selber muten!");
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
					List unitList = MuteUnit.getUnitsAsString();
					if (unitList.contains(unitString.toLowerCase())) {
						MuteUnit unit = MuteUnit.getUnit(unitString);
			            long seconds = value * unit.getToSecond();
			            MuteManager.mute(t.getUniqueId().toString(), t.getName(), reason1, p.getName(), seconds);
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
			            return;
					}
					p.sendMessage(Main.bprefix + "§7Diese EINHEIT existiert nicht! [Sekunde - sec | Minute - min | Stunde - hour | Tag - day | Woche - week]");
					return;
				} else {
					p.sendMessage(Main.bprefix + "Nutze§7; §7/Tempmute <Spieler> <Zahlenwert> <Zeitangabe> <Grund>");
				}
			} else {
				p.sendMessage(Main.bnoperm);
			}
		} else {
			sender.sendMessage(Main.playeronly);
		}

	}
	
}
