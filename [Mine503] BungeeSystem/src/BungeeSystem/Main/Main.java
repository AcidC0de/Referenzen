package BungeeSystem.Main;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.yaml.snakeyaml.Yaml;

import BungeeSystem.Ban.Commands.Command_Ban;
import BungeeSystem.Ban.Commands.Command_Check;
import BungeeSystem.Ban.Commands.Command_Kick;
import BungeeSystem.Ban.Commands.Command_Mute;
import BungeeSystem.Ban.Commands.Command_Tempban;
import BungeeSystem.Ban.Commands.Command_Tempmute;
import BungeeSystem.Ban.Commands.Command_Unban;
import BungeeSystem.Ban.Commands.Command_Unmute;
import BungeeSystem.Ban.Commands.Command_Warn;
import BungeeSystem.Ban.Listener.ChatFilter;
import BungeeSystem.Ban.Listener.MuteChat;
import BungeeSystem.Ban.Listener.PlayerLogin;
import BungeeSystem.Ban.Manager.WarnManager;
import BungeeSystem.Commands.Command_Apply;
import BungeeSystem.Commands.Command_Broadcast;
import BungeeSystem.Commands.Command_Bungee;
import BungeeSystem.Commands.Command_Find;
import BungeeSystem.Commands.Command_Hub;
import BungeeSystem.Commands.Command_JoinMe;
import BungeeSystem.Commands.Command_Jump;
import BungeeSystem.Commands.Command_List;
import BungeeSystem.Commands.Command_Msg;
import BungeeSystem.Commands.Command_Perms;
import BungeeSystem.Commands.Command_Ping;
import BungeeSystem.Commands.Command_Reply;
import BungeeSystem.Commands.Command_Teamchat;
import BungeeSystem.Commands.Command_Youtuber;
import BungeeSystem.Listener.QuitListener;
import BungeeSystem.Listener.TabComplete;
import BungeeSystem.Listener.TabFooter;
import BungeeSystem.Motd.ProxyPing;
import BungeeSystem.Report.ReportCommand;
import BungeeSystem.Report.ReportsCommand;
import BungeeSystem.Wartung.Command_Wartung;
import BungeeSystem.Wartung.WartungListener;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class Main extends Plugin {

	public static ArrayList<ProxiedPlayer> notify = new ArrayList<>();
	
	public static File wartung;
	public static Configuration cfg;
	
	public static ArrayList<String> werbung = new ArrayList<>();
	public static ArrayList<String> insults = new ArrayList<>();
	public static ArrayList<String> perminsults = new ArrayList<>();
	
	public static Main instance;
	public static String rprefix = "§9Report §8§l︳ §7";
	public static String fprefix = "§9Friend §8§l︳ §7";
	public static String bprefix = "§9Ban §8§l︳ §7";
	public static String wprefix = "§9Wartung §8§l︳ §7";
	public static String msgprefix = "§d§lPRIVAT §8§l︳ §7";
	public static String prefix = "§9System §8§l︳ §7";
	public static String noperm = prefix + "§cDu hast keine Berechtigung für diesen Befehl!";
	public static String fnoperm = fprefix + "§cDu hast keine Berechtigung für diesen Befehl!";
	public static String rnoperm = rprefix + "§cDu hast keine Berechtigung für diesen Befehl!";
	public static String bnoperm = bprefix + "§cDu hast keine Berechtigung für diesen Befehl!";
	public static String wnoperm = wprefix + "§cDu hast keine Berechtigung für diesen Befehl!";
	public static String usage = prefix + "Nutze§8: §7";
	public static String notonline = prefix + "§cDieser Spieler ist nicht Online!";
	public static String playeronly = "Du musst ein Spieler sein um diesen Befehl auszuführen!";
	
	public static int automsg = 0;
	
	@Override
	public void onEnable() {
		
		System.out.println("§8§m-----------------------------");
		System.out.println("");
		System.out.println("      §6§lBungeeSystem §8- §a§lAktiviert");
		System.out.println("");
		System.out.println("      §7§lAutor: §b§lAcidC0de");
		System.out.println("");
		System.out.println("§8§m-----------------------------");
		
		MySQL.connect();
		MySQL.createTable();
		
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Hub("hub"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Hub("lobby"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Hub("leave"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Hub("l"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Jump("jump"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Jump("goto"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Ping("ping"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Broadcast("gb"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Broadcast("gbroadcast"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Broadcast("globalbroadcast"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Broadcast("alert"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Teamchat("teamchat"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Teamchat("tc"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_JoinMe("joinme"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_List("list"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_List("glist"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Find("find"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Bungee("bungee"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Perms("perms"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new ReportCommand("report"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new ReportsCommand("reports"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Ban("ban"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Mute("mute"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Tempban("tempban"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Tempmute("tempmute"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Kick("kick"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Check("check"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Unban("unban"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Unmute("unmute"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Warn("warn"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Apply("apply"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Apply("bewerben"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Msg("msg"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Msg("tell"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Reply("reply"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Reply("r"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Youtuber("youtuber"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Youtuber("yt"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Youtuber("ytber"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Wartung("gwartung"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Command_Wartung("wartung"));
		
		BungeeCord.getInstance().getPluginManager().registerListener(this, new ProxyPing());
		BungeeCord.getInstance().getPluginManager().registerListener(this, new QuitListener());
		BungeeCord.getInstance().getPluginManager().registerListener(this, new PlayerLogin());
		BungeeCord.getInstance().getPluginManager().registerListener(this, new MuteChat());
		BungeeCord.getInstance().getPluginManager().registerListener(this, new ChatFilter());
		BungeeCord.getInstance().getPluginManager().registerListener(this, new WarnManager());
//		BungeeCord.getInstance().getPluginManager().registerListener(this, new JoinListener());
		BungeeCord.getInstance().getPluginManager().registerListener(this, new TabFooter());
		BungeeCord.getInstance().getPluginManager().registerListener(this, new TabComplete(this));
		BungeeCord.getInstance().getPluginManager().registerListener(this, new WartungListener());
		
//		ProxyServer.getInstance().getScheduler().schedule(this, new Runnable() {
//			
//			@Override
//			public void run() {
//				if(Main.automsg == 1) {
//					for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
//						all.sendMessage(" ");
//						all.sendMessage("              §7Interesse am §5Youtuber §7Rang?");
//						all.sendMessage("                      §7Nutze §a/Youtuber");
//						all.sendMessage(" ");
//					}
//				}
//				if(Main.automsg == 2) {
//					for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
//						all.sendMessage(" ");
//						all.sendMessage("              §7Unsere §aWebsite §7findet ihr unter§8:");
//						all.sendMessage("                      §aMine503.de");
//						all.sendMessage(" ");
//					}
//				}
//				if(Main.automsg == 3) {
//					for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
//						all.sendMessage(" ");
//						all.sendMessage("              §7Interesse an dem §6Premium §7Rang oder §eCoins§7?");
//						all.sendMessage("                      §7Besuche §ahttps://patreon.com/Mine503");
//						all.sendMessage(" ");
//					}
//				}
//				if(Main.automsg == 4) {
//					for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
//						all.sendMessage(" ");
//						all.sendMessage("              §7Interesse ins §cServerteam §7zu kommen?");
//						all.sendMessage("                      §7Nutze §a/Team");
//						all.sendMessage(" ");
//					}
//					Main.automsg = 0;
//				}
//				Main.automsg = Main.automsg + 1;
//			}
//		}, 0L, 10L, TimeUnit.SECONDS);
		
		try {
			if(!getDataFolder().exists()) {
				getDataFolder().mkdir();
			}
			wartung = new File(getDataFolder().getPath(), "wartung.yml");
			if(!wartung.exists()) {
				wartung.createNewFile();
			}
			cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(wartung);
//			cfg.set("Wartung", false);
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfg, wartung);
		} catch (IOException e) {
			
		}
	}
	
	@Override
	public void onDisable() {
		System.out.println("§8§m-----------------------------");
		System.out.println("");
		System.out.println("      §6§lBungeeSystem §8- §c§lDeaktiviert");
		System.out.println("");
		System.out.println("      §7§lAutor: §b§lAcidC0de");
		System.out.println("");
		System.out.println("§8§m-----------------------------");
	}
	
	public static Main getIstance() {
		return instance;
	}
	
	public static ArrayList<ProxiedPlayer> getNotitfy() {
		return notify;
	}
	
	public static String getHeader(String header) {
		return "§8§m----------§r §9§l" + header + " §8§m----------";
	}	
}
