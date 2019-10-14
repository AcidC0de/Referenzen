package Clan.Commands;

import Clan.Main.Data;
import Clan.Main.Main;
import Clan.MySQL.ClanManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_Clanchat extends Command {

	public Command_Clanchat(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		
		
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			
			if(args.length >= 1) {
				if(ClanManager.inClan(p.getUniqueId().toString())) {
					StringBuilder builder = new StringBuilder();

					for (int i = 0; i < args.length; i++) {
						builder.append(args[i]);
						builder.append(" ");
					}

					String output = builder.substring(0, builder.length() - 1);
					
					ProxyServer.getInstance().getScheduler().runAsync(Main.instance, new Runnable() {
						
						@Override
						public void run() {
							int clanID = ClanManager.getClanID(p.getUniqueId().toString());
							int playerIDs = ClanManager.getPlayerID();
							for(int i = 1; i <= playerIDs; i++) {
								String playername = ClanManager.getPlayerID(i);			
								if(ClanManager.getClanIDFromName(playername).equals(Integer.valueOf(clanID))) {
									ProxiedPlayer t = ProxyServer.getInstance().getPlayer(playername);
									if(t != null) {
										t.sendMessage(Data.prefix + " §a" + p.getName() + " §8» §7" + output);
									}
								}
							}
						}
					});
				} else {
					p.sendMessage(Data.prefix + "§cDu bist in keinem Clan.");
				}
			} else {
				p.sendMessage(Data.usage + "/Clanchat <Nachricht>");
			}
			
		} else {
			sender.sendMessage(Data.playeronly);
		}
		
	}

	
	
}
