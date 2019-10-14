package Clan.Util;

import java.util.HashMap;
import java.util.UUID;

public class InviteManager {

	public static HashMap<String, String> invites = new HashMap<>();
	
	public static void addPlayer(String target, String player) {
		if(!invites.containsKey(target)) {
			invites.put(target, player);
		}
	}
	
	public static void removePlayer(String uuid) {
		if(invites.containsKey(uuid)) {
			invites.remove(uuid);
		}
	}
	
	public static String getClan(String uuid) {
		return invites.get(uuid);
	}
	
}
