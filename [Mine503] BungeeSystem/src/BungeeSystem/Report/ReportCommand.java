package BungeeSystem.Report;

import java.util.HashMap;

import BungeeSystem.Main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ReportCommand extends Command {

	HashMap<String, Long> cooldown = new HashMap<>();
	
	public ReportCommand(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if (args.length == 2) {
				ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

				if (!ProxyServer.getInstance().getPlayers().contains(target)) {
					p.sendMessage(Main.rprefix + "Dieser Spieler ist nicht Online!");
					return;
				}

				String reason = args[1];

				if (target.hasPermission("bungeesystem.report.bypass")) {
					p.sendMessage(Main.rprefix + "Du kannst diesen Spieler nicht reporten.");
					return;
				}
				if (target.getName() == p.getName()) {
					p.sendMessage(Main.rprefix + "Du kannst dich nicht selber reporten.");
					return;
				}
				if(this.cooldown.containsKey(p.getName())) {
					long secondsleft = ((Long) this.cooldown.get(p.getName())).longValue() / 1000 + 60L - System.currentTimeMillis() / 1000L;
					if(secondsleft > 0) {
						p.sendMessage(Main.rprefix + "§cBitte warte einen moment bevor du wieder einen Spieler meldest.");
						return;
					}
				}
				cooldown.put(p.getName(), Long.valueOf(System.currentTimeMillis()));
				if (!ReportManager.isReported(target.getUniqueId().toString())) {
					String server = target.getServer().getInfo().getName();
					for (ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
						if (all.hasPermission("bungeesystem.report.use")) {
							if(!Main.getNotitfy().contains(all)) {
								TextComponent clickto = new TextComponent("§7Klicke §e§lHIER §7um den Report zu bearbeiten!");
								if (target == null) {
									return;
								}
								clickto.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/jump " + target.getName()));
								clickto.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a§oKlicke hier um den Report zu bearbeiten!").create()));

								all.sendMessage("§8)§7§l§m------------§8( §c§lREPORT §8)§7§l§m------------§8(");
								all.sendMessage("§7Der Spieler §a" + p.getName() + " §7hat einen §cReport §7erstellt!");
								all.sendMessage("§7Gemeldeter Spieler§8: §e" + target.getName());
								all.sendMessage("§7Grund§8: §b" + reason);
								all.sendMessage("§7Server§8: §6" + server);
								all.sendMessage("§7Ping§8: §e" + target.getPing());
								all.sendMessage("§7Nutze §e/Reports accept " + target.getName() + " §7um diesen zu bearbeiten.");
								all.sendMessage(clickto);
								all.sendMessage("§8)§7§l§m------------§8( §c§lREPORT §8)§7§l§m------------§8(");
								
//								all.sendMessage("§8)§7§l§m------------§8( §c§lREPORT §8)§7§l§m------------§8(");
//								all.sendMessage("");
//								all.sendMessage("§8» §a" + p.getName() + " §7hat §c" + target.getName() + " §7gemeldet!");
//								all.sendMessage("§8» §7Grund§8: §b" + reason);
//								all.sendMessage("§8» §7Server§8: §6" + server);
//								all.sendMessage("");
//								all.sendMessage(clickto);
//								all.sendMessage("");
//								all.sendMessage("§8)§7§l§m------------§8( §c§lREPORT §8)§7§l§m------------§8(");
//								p.sendMessage(Main.rprefix + "Du hast den Spieler §a" + target.getName() + " §7gemeldet.");
							}
						}
					}
					p.sendMessage(Main.rprefix + "Du hast den Spieler §a" + target.getName()+ " §7gemeldet.");
					ReportManager.addReport(target.getUniqueId().toString(), p.getName(), target.getName(), server, reason);
				} else {
//					p.sendMessage(Main.rprefix + "Dieser Spieler wurde bereits von §e" + ReportManager.getReporter(target.getUniqueId().toString()) + " §7gemeldet.");
					String server = target.getServer().getInfo().getName();
					for (ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
						if (all.hasPermission("bungeesystem.report.use")) {
							if(!Main.getNotitfy().contains(all)) {
								TextComponent clickto = new TextComponent("§7Klicke §e§lHIER §7um den Report zu bearbeiten!");
								if (target == null) {
									return;
								}
								clickto.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/jump " + target.getName()));
								clickto.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a§oKlicke hier um den Report zu bearbeiten!").create()));

								all.sendMessage("§8)§7§l§m------------§8( §c§lREPORT §8)§7§l§m------------§8(");
								all.sendMessage("§7Der Spieler §a" + p.getName() + " §7hat einen §cReport §7erstellt!");
								all.sendMessage("§7Gemeldeter Spieler§8: §e" + target.getName());
								all.sendMessage("§7Grund§8: §b" + reason);
								all.sendMessage("§7Server§8: §6" + server);
								all.sendMessage("§7Ping§8: §e" + target.getPing());
								all.sendMessage("§7Nutze §e/Reports accept " + target.getName() + " §7um diesen zu bearbeiten.");
								all.sendMessage(clickto);
								all.sendMessage("§8)§7§l§m------------§8( §c§lREPORT §8)§7§l§m------------§8(");
							}
						}
					}
				}
			} else {
				p.sendMessage(Main.rprefix + "Nutze§8: §7/Report <Spieler> <Grund>");
			}
		} else {
			sender.sendMessage(Main.playeronly);
		}
	}

}
