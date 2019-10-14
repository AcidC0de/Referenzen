package BungeeSystem.Wartung;

import java.io.IOException;

import BungeeSystem.Main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class Command_Wartung extends Command {

	public Command_Wartung(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			
			if(args.length == 1) {
				if(p.hasPermission("bungeesystem.wartung")) {
					if(args[0].equalsIgnoreCase("an")) {
						if(getWartung() == false) {
							setWartung(true);
							p.sendMessage(Main.wprefix + "§6§l" + p.getName() + " §7§lhat den §4§lWartungsmodus §a§laktiviert§7§l.");
							for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
								if(!all.hasPermission("bungeesystem.wartung.bypass")) {
									all.disconnect(Main.wprefix + "§cWir befinden uns derzeit in Wartungsarbeiten.");
								}
							}
						} else {
							p.sendMessage(Main.wprefix + "§cDer Wartungsmodus ist bereits aktiviert,");
						}
					} else if(args[0].equalsIgnoreCase("aus")){
						if(getWartung() == true) {
							setWartung(false);
							p.sendMessage(Main.wprefix + "§6§l" + p.getName() + " §7§lhat den §4§lWartungsmodus §c§ldeaktiviert§7§l.");
						} else {
							p.sendMessage(Main.wprefix + "§cDer Wartungsmodus ist bereits deaktiviert,");	
						}
					}	
				} else {
					p.sendMessage(Main.wnoperm);
				}
			} else {
				p.sendMessage(Main.getHeader("WARTUNG"));
				p.sendMessage("§7  §8- §7/Wartung §6<an>");
				p.sendMessage("§7  §8- §7/Wartung §6<aus>");
				p.sendMessage(Main.getHeader("WARTUNG"));
			}
			
		} else {
			sender.sendMessage(Main.playeronly);
		}
	}
	
	public static void setWartung(Boolean wartung) {
		Main.cfg.set("Wartung", wartung);
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(Main.cfg, Main.wartung);	
		} catch (IOException e) {}
	}
	
	public static boolean getWartung() {
		return Main.cfg.getBoolean("Wartung");
	}
	
}
