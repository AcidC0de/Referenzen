package FFA.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import FFA.Main.Data;
import net.minecraft.server.v1_8_R3.Items;

public class Command_Build implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("build")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(p.hasPermission("ffa.build")) {
					if(args.length == 0) {
						if(!Data.build.contains(p)) {
							Data.build.add(p);
							p.getInventory().clear();
							p.sendMessage(Data.prefix + "§aDu kannst nun bauen.");
						} else {
							Data.build.remove(p);
							p.sendMessage(Data.prefix + "§cDu kannst nun nicht mehr bauen.");
						}
					} else if(args.length == 1) {
						Player t = Bukkit.getPlayer(args[0]);
						
						if(t == null) {
							p.sendMessage(Data.notonline);
							return true;
						}
						if(!Data.build.contains(t)) {
							Data.build.add(t);
							p.getInventory().clear();
							p.sendMessage(Data.prefix + "§e" + t.getName() + " §akann nun bauen.");
							t.sendMessage(Data.prefix + "§aDu kannst nun bauen.");
						} else {
							Data.build.remove(t);
							p.sendMessage(Data.prefix + "§e" + t.getName() + " §ckann nun nicht mehr bauen.");
							t.sendMessage(Data.prefix + "§cDu kannst nun nicht mehr bauen.");
						}
					} else {
						p.sendMessage(Data.prefix + "Nutze§8: §7/Build <Spieler>");
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

