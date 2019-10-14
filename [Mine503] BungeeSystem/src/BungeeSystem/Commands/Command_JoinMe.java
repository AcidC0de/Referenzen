package BungeeSystem.Commands;

import java.util.HashMap;

import BungeeSystem.Main.Data;
import BungeeSystem.Main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_JoinMe extends Command {

	public static HashMap<String, Long> used = new HashMap<>();
	
	public static String prefix = "§8§l│ §e§lJoinME §8» §7";
	
	public Command_JoinMe(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(p.hasPermission("bungeesystem.joinme")) {
				if(args.length == 0) {
					if(!p.hasPermission("bungeesystem.joinme.nodelay")) {
						if (this.used.containsKey(p.getName())) {
							long secondsleft = ((Long) this.used.get(p.getName())).longValue() / 1000 + 120L - System.currentTimeMillis() / 1000L;
							if (secondsleft > 0) {
								p.sendMessage(Main.prefix + "§cBitte warte einen moment bevor du wieder JoinME verwendest.");
								return;
							}
						}
						used.put(p.getName(), Long.valueOf(System.currentTimeMillis()));	
						for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
							TextComponent msg = new TextComponent("§aKLICKE HIER, um den Server zu betreten.");
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§c§oVerbinden").create()));
							msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/jump " + p.getName()));
							all.sendMessage("§8§m------------------------------------");
							all.sendMessage("");
							all.sendMessage(Data.getRank(p) + p.getName() + " §7spielt auf §c§o" + p.getServer().getInfo().getName() + "§7!");
							all.sendMessage("");
							all.sendMessage(msg);
							all.sendMessage("");
							all.sendMessage("§8§m------------------------------------");	
						}
					} else if(p.hasPermission("bungeesystem.joinme.nodelay")) {
						for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
							TextComponent msg = new TextComponent("§aKLICKE HIER, um den Server zu betreten.");
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§c§oVerbinden").create()));
							msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/jump " + p.getName()));
							all.sendMessage("§8§m------------------------------------");
							all.sendMessage("");
							all.sendMessage(Data.getRank(p) + p.getName() + " §7spielt auf §c§o" + p.getServer().getInfo().getName() + "§7!");
							all.sendMessage("");
							all.sendMessage(msg);
							all.sendMessage("");
							all.sendMessage("§8§m------------------------------------");	
						}
					}
				} else if((args.length == 1) && (args[0].equalsIgnoreCase("clear") && p.hasPermission("bungeesystem.joinme.admin"))) {
					used.clear();
					p.sendMessage(Main.prefix + "Du hast alle §bJoinMe §7Einträge geleert.");
				}
			} else {
				p.sendMessage(Main.noperm);
			}		
		} else {
			sender.sendMessage(Main.playeronly);
		}
		
	}

//	used.put(p.getName(), Long.valueOf(System.currentTimeMillis()));
//	for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
//		TextComponent msg = new TextComponent(prefix + "§aKLICKE HIER, um den Server zu betreten.");
//		msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§c§oVerbinden").create()));
//		msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/jump " + p.getName()));
//		all.sendMessage("§8§m------------------------------------");
//		all.sendMessage("");
//		all.sendMessage(prefix + "§b§o" + p.getName() + " §7spielt auf §a§o" + p.getServer().getInfo().getName() + "§7!");
//		all.sendMessage("");
//		all.sendMessage(msg);
//		all.sendMessage("");
//		all.sendMessage("§8§m------------------------------------");
//	}
//} else if(p.hasPermission("bungeesystem.joinme.nodelay")){
//	for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
//		TextComponent msg = new TextComponent(prefix + "§aKLICKE HIER, um den Server zu betreten.");
//		msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§c§oVerbinden").create()));
//		msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/jump " + p.getName()));
//		all.sendMessage("§8§m------------------------------------");
//		all.sendMessage("");
//		all.sendMessage(prefix + "§b§o" + p.getName() + " §7spielt auf §a§o" + p.getServer().getInfo().getName() + "§7!");
//		all.sendMessage("");
//		all.sendMessage(msg);
//		all.sendMessage("");
//		all.sendMessage("§8§m------------------------------------");
//	}
//}
}
