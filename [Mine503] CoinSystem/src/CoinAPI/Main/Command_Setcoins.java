package CoinAPI.Main;

import java.math.BigInteger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Setcoins implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("setcoins")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				
				if(args.length == 3) {
					Player t = Bukkit.getPlayer(args[1]);
					if(args[0].equalsIgnoreCase("set")) {
						Integer i = Integer.valueOf(args[2]);
						
						if(i > 500000000 || i < 0) {
							p.sendMessage(Main.prefix + "§cDie Anzahl muss zwischen §e1 §cund §e500.000.000 §cliegen.");
							return true;
						}
						
						CoinManager.setCoins(t.getUniqueId().toString(), i);
						
						p.sendMessage(Main.prefix + "Du hast die Coins von §e" + t.getName() + " §7auf §a" + SplitAPI.splitNum(i) + " §7gesetzt.");		
					}
					if(args[0].equalsIgnoreCase("add")) {
						Integer i = Integer.valueOf(args[2]);
						
						if(i > 500000000 || i < 0) {
							p.sendMessage(Main.prefix + "§cDie Anzahl muss zwischen §e1 §cund §e500.000.000 §cliegen.");
							return true;
						}
						
						CoinManager.addCoins(t.getUniqueId().toString(), i);
						
						p.sendMessage(Main.prefix + "Du hast §e" + t.getName() + " §a" + SplitAPI.splitNum(i) + " §7Coins hinzugefügt.");		
					}
					if(args[0].equalsIgnoreCase("remove")) {
						Integer i = Integer.valueOf(args[2]);
						
						if(i > 500000000 || i < 0) {
							p.sendMessage(Main.prefix + "§cDie Anzahl muss zwischen §e1 §cund §e500.000.000 §cliegen.");
							return true;
						}
						
						CoinManager.removeCoins(t.getUniqueId().toString(), i);
						
						p.sendMessage(Main.prefix + "Du hast §e" + t.getName() + " §a" + SplitAPI.splitNum(i) + " §7Coins entfernt.");				
					}
				} else {
					p.sendMessage(Main.prefix + "Nutze§8: §7/Setcoins <Add:Remove:Set> <Spieler> <Anzahl>");
				}
			}
		}
		
		return false;
	}
	
}
