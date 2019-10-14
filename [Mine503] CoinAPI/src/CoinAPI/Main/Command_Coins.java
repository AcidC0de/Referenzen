package CoinAPI.Main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Coins implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("coins")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(args.length == 0) {
					p.sendMessage(Main.prefix + "Deine Coins§8: §e" + CoinAPI.getCoins(p.getUniqueId().toString()));
				} else if(args.length == 1) {
					Player t = Bukkit.getPlayer(args[0]);
					if(t == null) {
						p.sendMessage(Main.prefix + "§cDieser Spieler ist nicht Online!");
						return true;
					}
					p.sendMessage(Main.prefix + "§e" + t.getName() + "'s §7Coins§8: §e" + CoinAPI.getCoins(t.getUniqueId().toString()));
				} else {
					p.sendMessage(Main.prefix + "Nutze§8: §7/Coins <Spieler>");
				}
				
			}
		}
		
		
		return false;
	}
	
}
