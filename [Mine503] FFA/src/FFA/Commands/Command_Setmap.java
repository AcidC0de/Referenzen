package FFA.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import FFA.Listener.Listener_Scoreboard;
import FFA.Main.Data;
import FFA.Util.FileManager;

public class Command_Setmap implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("setmap")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				
				if(p.hasPermission("ffa.setmap")) {
					if(args.length == 1) {
						String name = args[0];
						
						FileManager.setMap(name);
						p.sendMessage(Data.prefix + "Neuer Map-Name§8: §a" + name);	
						for(Player all : Bukkit.getOnlinePlayers()) {
							Listener_Scoreboard.setScoreboard(all);	
						}
					} else {
						p.sendMessage(Data.prefix + "Nutze§8: §7/Setmap <Name>");
					}	
				}		
			} else {
				sender.sendMessage(Data.playeronly);
			}
		}
		
		return false;
	}
	
}
