package BungeeSystem.Commands;

import java.util.HashSet;
import java.util.Set;

import org.spawl.bungeepackets.BungeePackets;
import org.spawl.bungeepackets.effect.SoundEffect;

import com.google.common.collect.ImmutableSet;

import BungeeSystem.Main.Data;
import BungeeSystem.Main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_Msg extends Command {

	public Command_Msg(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;

			if (args.length >= 2) {
				ProxiedPlayer t = ProxyServer.getInstance().getPlayer(args[0]);

				if (!ProxyServer.getInstance().getPlayers().contains(t)) {
					p.sendMessage(Main.msgprefix + "Dieser Spieler ist nicht Online!");
					return;
				}

				if (t.getName() == p.getName()) {
					p.sendMessage(Main.msgprefix + "§cDu kannst dir nicht selber schreiben!");
					return;
				}

				StringBuilder builder = new StringBuilder();

				for (int i = 1; i < args.length; i++) {
					builder.append(args[i]);
					builder.append(" ");
				}

				String output = builder.substring(0, builder.length() - 1);

				p.sendMessage(Main.msgprefix + "§7An " + Data.getRank(t) + t.getName() + " §8» §f" + output);
				t.sendMessage(Main.msgprefix + "§7Von " + Data.getRank(p) + p.getName() + " §8» §f" + output);
				BungeePackets.playSound(p, SoundEffect.RANDOM_LEVELUP, 1.0F, 1.0F);
				BungeePackets.playSound(t, SoundEffect.RANDOM_LEVELUP, 1.0F, 1.0F);

				if ((Command_Reply.replyhash.containsKey(p)) || (Command_Reply.replyhash.containsKey(t))) {
					Command_Reply.replyhash.remove(p);
					Command_Reply.replyhash.remove(t);
					Command_Reply.replyhash.put(p, t);
					Command_Reply.replyhash.put(t, p);
				}
				Command_Reply.replyhash.put(p, t);
				Command_Reply.replyhash.put(t, p);

			} else {
				p.sendMessage(Main.msgprefix + "Nutze§8: §7/Msg <Spieler> <Nachricht>");
			}
		} else {
			sender.sendMessage(Main.playeronly);
		}
	}
}
