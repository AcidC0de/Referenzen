package BungeeSystem.Ban.Commands;

import BungeeSystem.Main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_Kick extends Command {

	public Command_Kick(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(p.hasPermission("bungeesystem.kick")) {
				if(args.length >= 2) {
					ProxiedPlayer t = ProxyServer.getInstance().getPlayer(args[0]);
					
					if(t == null) {
						p.sendMessage(Main.bprefix + "Dieser Spieler ist nicht Online!");
						return;
					}
					if(t.getName() == p.getName()) {
						p.sendMessage(Main.bprefix + "Du kannst dich nicht selber kicken!");
						return;
					}
					if(t.hasPermission("bungeesystem.kick.bypass")) {
						p.sendMessage(Main.bprefix + "Du kannst diesen Spieler nicht kicken!");
						return;
					}
					
					String reason = " ";
					for (int i = 1; i < args.length; i++) {
						reason = reason + args[i] + " ";
					}
					
					t.disconnect("§f§lDu wurdest von dem §c§lMINE503 §f§lNetzwerk geworfen!\n\n§7Grund §8» §4" + reason + "\n§7Gekickt von §8» §6" + p.getName());
					for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
						if(all.hasPermission("bungeesystem.ban.see")) {
							all.sendMessage(Main.bprefix + "Der Spieler §6" + t.getName() + " §7wurde von §a" + p.getName() + " §7gekickt! §7Grund§8: §4" + reason + "§7.");
						}
					}
					
				} else {
					p.sendMessage(Main.bprefix + "Nutze§8: §7/Kick <Spieler> <Grund>");
				}
			} else {
				p.sendMessage(Main.bnoperm);
			}
		} else {
			sender.sendMessage(Main.playeronly);
		}
		
	}

	
	
}
