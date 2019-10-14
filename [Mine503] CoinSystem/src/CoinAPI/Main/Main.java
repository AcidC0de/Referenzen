package CoinAPI.Main;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements CommandExecutor {
	
	public static String prefix = "§eCoinSystem §8§l︳ §7";
	
	public void onEnable() {
		
	    FileManager.setStandardMySQL();
	    FileManager.readMySQL();
		MySQL.connect();
		MySQL.createTable();
		getCommand("coins").setExecutor(new Command_Coins());
		getCommand("setcoins").setExecutor(new Command_Setcoins());
		Bukkit.getPluginManager().registerEvents(new CoinManager(), this);
		Bukkit.getConsoleSender().sendMessage("§eCoinSystem by AcidC0de §aAktiviert.");
	}
	
	public void onDisable() {
		MySQL.close();
		Bukkit.getConsoleSender().sendMessage("§eCoinSystem by AcidC0de §cDeaktiviert.");	
	}

	public static String getHeader(String string) {
		return "§8)§7§l§m------------§8(  §e§l" + string + " §8)§7§l§m------------§8(";
	}
	
}
