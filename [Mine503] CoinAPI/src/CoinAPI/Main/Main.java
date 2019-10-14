package CoinAPI.Main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static String prefix = "§8[§eCoinSystem§8] §7";
	
	public void onEnable() {
		
	    FileManager.setStandardMySQL();
	    FileManager.readMySQL();
		MySQL.connect();
		MySQL.createTable();
		
//		getCommand("coins").setExecutor(new Command_Coins());
		Bukkit.getConsoleSender().sendMessage("§aCoinSystem activated.");
	}
	
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§cCoinSystem deactivated.");
	}
	
}
