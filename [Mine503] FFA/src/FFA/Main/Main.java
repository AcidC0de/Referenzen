package FFA.Main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import FFA.Commands.Command_Build;
import FFA.Commands.Command_Setloc;
import FFA.Commands.Command_Setmap;
import FFA.Listener.BlockChangeEvent;
import FFA.Listener.DeathListener;
import FFA.Listener.Events;
import FFA.Listener.PlayerJoin;
import FFA.Listener.PlayerQuit;
import FFA.Stats.MySQL;
import FFA.Stats.Stats;
import FFA.Util.FileManager;
import FFA.Util.KillstreakManager;

public class Main extends JavaPlugin {

	public static Main instance;
	
	@Override
	public void onEnable() {		
		instance = this;
	    FileManager.setStandardMySQL();
	    FileManager.readMySQL();
		MySQL.connect();
		MySQL.createTable();
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);	
		Bukkit.getPluginManager().registerEvents(new PlayerQuit(), this);	
		Bukkit.getPluginManager().registerEvents(new Stats(), this);	
		Bukkit.getPluginManager().registerEvents(new Events(), this);	
		Bukkit.getPluginManager().registerEvents(new KillstreakManager(), this);	
		Bukkit.getPluginManager().registerEvents(new DeathListener(), this);	
		Bukkit.getPluginManager().registerEvents(new BlockChangeEvent(), this);	
		
		getCommand("build").setExecutor(new Command_Build());
		getCommand("setmap").setExecutor(new Command_Setmap());
		getCommand("setloc").setExecutor(new Command_Setloc());
		
		Bukkit.getConsoleSender().sendMessage(Data.prefix + "§a§lDas Plugin wurde erfolgreich aktiviert.");
	}
	
	@Override
	public void onDisable() {
		MySQL.close();
		Bukkit.getConsoleSender().sendMessage(Data.prefix + "§c§lDas Plugin wurde erfolgreich deaktiviert.");
	}

	public static Main getInstance() {
		return instance;
	}
	
}
