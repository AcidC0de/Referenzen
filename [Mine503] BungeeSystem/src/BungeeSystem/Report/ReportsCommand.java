package BungeeSystem.Report;

import java.util.List;

import BungeeSystem.Main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ReportsCommand extends Command {

	public ReportsCommand(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(p.hasPermission("bungeesystem.report.use")) {
				if(args.length == 0) {
					List list = ReportManager.getReportedPlayers();
					if (list.size() == 0) {
						p.sendMessage(Main.rprefix + "Es gibt momentan keine offenen Reports!");
						return;
					}
					p.sendMessage("ß8)ß7ßlßm------------ß8( ßcßlREPORT ß8)ß7ßlßm------------ß8(");
					p.sendMessage("");
					p.sendMessage("ß8ª ß7Es gibt derzeit ß6" + list.size() + " ß7Offene Reports.");
					p.sendMessage("");
					for (String allReported : ReportManager.getReportedPlayers()) {
						p.sendMessage("ß8ª ßc" + allReported + " ß8ª ß7Grundß8: ßb" + ReportManager.getReason(getUUID(allReported)));
					}
					p.sendMessage("");
					p.sendMessage("ß8)ß7ßlßm------------ß8( ßcßlREPORT ß8)ß7ßlßm------------ß8(");
					return;
				} 
				else if(args.length == 2) {
					if(args[0].equalsIgnoreCase("remove")) {
						ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[1]);
						if (target == null) {
							p.sendMessage(Main.rprefix + "Dieser Spieler ist nicht Online!");
							return;
						}
						if (!ReportManager.isReported(target.getUniqueId().toString())) {
							p.sendMessage(Main.rprefix + "Dieser Spieler wurde nicht Reportet.");
							return;
						}
						
						ProxiedPlayer reporter = ProxyServer.getInstance().getPlayer(ReportManager.getReporter(target.getUniqueId().toString()));
						if(reporter != null) {
							reporter.sendMessage(Main.rprefix + "ßeDein Report gegen ßa" + target.getName() + " ßewurde abgeschlossen.");
						}
						
						ReportManager.deleteReport(target.getUniqueId().toString());
						p.sendMessage(Main.rprefix + "Du hast den Report von ßa" + target.getName() + " ß7erfolgreich erledigt.");
						
						return;
					}
					if(args[0].equalsIgnoreCase("accept")) {
						ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[1]);
						if (target == null) {
							p.sendMessage(Main.rprefix + "Dieser Spieler ist nicht Online!");
							return;
						}
						if (!ReportManager.isReported(target.getUniqueId().toString())) {
							p.sendMessage(Main.rprefix + "Dieser Spieler wurde nicht Reportet.");
							return;
						}
						
						ProxiedPlayer reporter = ProxyServer.getInstance().getPlayer(ReportManager.getReporter(target.getUniqueId().toString()));
						if(reporter != null) {
							reporter.sendMessage(Main.rprefix + "ßeDein Report gegen ßa" + target.getName() + " ßewird nun bearbeitet.");
						}
						ServerInfo s = target.getServer().getInfo();
						if (target.getServer().getInfo() != p.getServer().getInfo()) {
							p.connect(s);
							p.sendMessage(Main.rprefix + "Du wurdest zu ßa" + target.getName() + " ß7verschoben.");
							
						} else {
							p.sendMessage(Main.rprefix + "ßcDu befindest dich bereits auf diesem Server!");
						}
						return;
					}
				} 
				else if(args.length == 1) {
					if(args[0].equalsIgnoreCase("help")) {
						p.sendMessage(Main.rprefix + "ßc/Report <Spieler> <Grund>");
						p.sendMessage(Main.rprefix + "ßc/Reports Help ß8| ß7Report Hilfe");
						p.sendMessage(Main.rprefix + "ßc/Reports ß8| ß7Report-Liste.");
						p.sendMessage(Main.rprefix + "ßc/Reports remove <Spieler> ß8| ß7Report abschlieﬂen.");
						p.sendMessage(Main.rprefix + "ßc/Reports accept <Spieler> ß8| ß7Report annehmen.");
					}
				} 
				else {
					p.sendMessage(Main.rprefix + "ßc/Report <Spieler> <Grund>");
					p.sendMessage(Main.rprefix + "ßc/Reports Help ß8| ß7Report Hilfe");
					p.sendMessage(Main.rprefix + "ßc/Reports ß8| ß7Report-Liste.");
					p.sendMessage(Main.rprefix + "ßc/Reports remove <Spieler> ß8| ß7Report abschlieﬂen.");
					p.sendMessage(Main.rprefix + "ßc/Reports accept <Spieler> ß8| ß7Report annehmen.");
				}
			} else {
				p.sendMessage(Main.rnoperm);
			}
			
		} else {
			sender.sendMessage(Main.playeronly);
		}
		
	}

	private String getUUID(String playername) {
		return ProxyServer.getInstance().getPlayer(playername).getUniqueId().toString();
	}
	
	
	
}
