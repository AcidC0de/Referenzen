package Clan.Main;


import Clan.Commands.Command_Clan;
import Clan.Commands.Command_Clanchat;
import Clan.Listener.JoinListener;
import Clan.Listener.QuitListener;
import Clan.MySQL.MySQL;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

	public static Main instance;
	
	@Override
	public void onEnable() {
		instance = this;
		System.out.println(Data.prefix + "§a§lPlugin wurde erfolgreich aktiviert.");
		
		MySQL.connect();
		MySQL.createTable();
		
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Clan("clans"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Clanchat("clanchat"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Clanchat("cc"));
		
		BungeeCord.getInstance().getPluginManager().registerListener(this, new QuitListener());
		BungeeCord.getInstance().getPluginManager().registerListener(this, new JoinListener());
	}
	
	@Override
	public void onDisable() {
		System.out.println(Data.prefix + "§c§lPlugin wurde erfolgreich deaktiviert.");
	}

	public static String getHeader(String header) {
		return "§8§m----------§r §b§l" + header + " §8§m----------";
	}	
	
}
