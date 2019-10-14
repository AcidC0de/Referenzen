package FFA.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import FFA.Main.Data;
import FFA.Util.LocationUtil;

public class Command_Setloc implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("setloc")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(p.hasPermission("ffa.setloc")) {
					if(args.length != 1) {
						p.sendMessage(Data.prefix + "Nutze§8: §7/Setloc <Name>");
					} else if(args.length == 1) {
						String name = args[0];
						
						LocationUtil.setLoc(p.getLocation(), name);
						p.sendMessage(Data.prefix + "§aDu hast erfolgreich die Location §2" + name + " §agesetzt.");
					}
				} else {
					p.sendMessage(Data.noperm);
				}
			} else {
				sender.sendMessage(Data.playeronly);
			}
		}
		
		return false;
	}
	
}
