package BungeeSystem.Commands;

import java.util.HashMap;

import org.spawl.bungeepackets.BungeePackets;
import org.spawl.bungeepackets.effect.SoundEffect;

import BungeeSystem.Main.Data;
import BungeeSystem.Main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_Reply extends Command {

	public Command_Reply(String name) {
		super(name);
	}

	public static HashMap<ProxiedPlayer, ProxiedPlayer> replyhash = new HashMap();
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			
			if(replyhash.containsKey(p)) {
				ProxiedPlayer t = (ProxiedPlayer) replyhash.get(p);
				if(replyhash.containsValue(t)) {
					if(t != null) {
						if(args.length >= 1) {
							StringBuilder builder = new StringBuilder();

							for (int i = 0; i < args.length; i++) {
								builder.append(args[i]);
								builder.append(" ");
							}

							String output = builder.substring(0, builder.length() - 1);
							
							p.sendMessage(Main.msgprefix + "§7An " + Data.getRank(t) + t.getName() + " §8» §f" + output);
							t.sendMessage(Main.msgprefix + "§7Von " + Data.getRank(p) + p.getName() + " §8» §f" + output);
							BungeePackets.playSound(p, SoundEffect.RANDOM_LEVELUP, 1.0F, 1.0F);
							BungeePackets.playSound(t, SoundEffect.RANDOM_LEVELUP, 1.0F, 1.0F);
							
			                if ((replyhash.containsKey(p)) || (replyhash.containsKey(t)))
			                {
			                  replyhash.remove(p);
			                  replyhash.remove(t);
			                  replyhash.put(p, t);
			                  replyhash.put(t, p);
			                }
			                replyhash.put(p, t);
			                replyhash.put(t, p);
						} else {
							p.sendMessage(Main.msgprefix + "Nutze§8; §7/Reply <Nachricht>");
						}
 					} else {
						p.sendMessage(Main.msgprefix + "§cDieser Spieler ist nicht Online!");
						replyhash.remove(p);
						replyhash.remove(t);
					}
				}
			} else {
				p.sendMessage(Main.msgprefix + "§cDu hast niemanden dem du Antworten kannst.");
			}
		} else {
			sender.sendMessage(Main.playeronly);
		}
	}
}
