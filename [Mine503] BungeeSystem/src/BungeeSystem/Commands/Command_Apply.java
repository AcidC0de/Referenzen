package BungeeSystem.Commands;

import BungeeSystem.Main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_Apply extends Command {

	public Command_Apply(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(args.length == 0) {
				p.sendMessage("");
				p.sendMessage(Main.getHeader("BEWERBEN"));
				p.sendMessage("");
				p.sendMessage("§7Du möchtest dich als §eBuilder§7/§2Mod §7bewerben?");
				p.sendMessage("§7Dann schreibe in unserem Forum");
				p.sendMessage("§7eine Bewerbung mit mindestens §e250 §7Wörtern.");
				p.sendMessage("");
				p.sendMessage("§4§l§oWICHTIG§8: §7§oDu musst mind. §e§o14 Jahre §7§oalt sein,");
				p.sendMessage("§7um dich bewerben zu können!");
				p.sendMessage("");
				p.sendMessage("§7Forum§8: §eMine503.de/forum");
				p.sendMessage("");
				p.sendMessage(Main.getHeader("BEWERBEN"));
			}
			if(args.length != 0) {
				p.sendMessage(Main.usage + "/Apply");
			}
		} else {
			sender.sendMessage(Main.playeronly);
		}
	}	
}
