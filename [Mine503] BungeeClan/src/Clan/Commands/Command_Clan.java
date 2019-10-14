package Clan.Commands;

import java.util.HashMap;
import java.util.List;

import Clan.Main.Data;
import Clan.Main.Main;
import Clan.MySQL.ClanManager;
import Clan.Util.InviteManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_Clan extends Command {

	public Command_Clan(String name) {
		super(name);
	}

	public static HashMap<String, String> playersInClan = new HashMap();
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		
		if((sender instanceof ProxiedPlayer) && (!((ProxiedPlayer) sender).getServer().getInfo().getName().equals("Factions-01"))) {
			if(sender instanceof ProxiedPlayer) {
				ProxiedPlayer p = (ProxiedPlayer) sender;
				
				if(args.length == 0 || args.length > 3) {
					p.sendMessage(Main.getHeader("CLAN"));
					p.sendMessage("§7- /clans §bcreate §7<Name> <Tag");
					p.sendMessage("§7- /clans §bdelete");
					p.sendMessage("§7- /clans §binvite §7<Name>");
					p.sendMessage("§7- /clans §bkick §7<Name>");
					p.sendMessage("§7- /clans §bleave");
					p.sendMessage("§7- /clans §binfo");
					p.sendMessage("§7- /clans §buinfo §7<Spieler>");
					p.sendMessage("§7- /clans §bjoin §7<Clanname>");
					p.sendMessage("§7- /clans §bdeny §7<Clanname>");
					p.sendMessage("§7- /clans §bsetname §7<Spieler>");
					p.sendMessage("§7- /clans §bsettag §7<Spieler>");
					p.sendMessage("§7- /clans §bpromote §7<Spieler>");
					p.sendMessage("§7- /clans §bdemote §7<Spieler>");
					p.sendMessage("§7- /cc <Nachricht>");
					p.sendMessage(Main.getHeader("CLAN"));
				} else if(args.length == 1) {
					if(args[0].equalsIgnoreCase("create")) {
						p.sendMessage(Data.usage + "/Clans create <Name> <Tag>");
					}
					if(args[0].equalsIgnoreCase("invite")) {
						p.sendMessage(Data.usage + "/Clans invite <Spieler>");
					}
					if(args[0].equalsIgnoreCase("uinfo")) {
						p.sendMessage(Data.usage + "/Clans uinfo <Spieler>");
					}
					if(args[0].equalsIgnoreCase("kick")) {
						p.sendMessage(Data.usage + "/Clans kick <Spieler>");
					}
					if(args[0].equalsIgnoreCase("demote")) {
						p.sendMessage(Data.usage + "/Clans demote <Spieler>");
					}
					if(args[0].equalsIgnoreCase("promote")) {
						p.sendMessage(Data.usage + "/Clans promote <Spieler>");
					}
					if(args[0].equalsIgnoreCase("leave")) {
						if(ClanManager.inClan(p.getUniqueId().toString())) {
							if(!ClanManager.getClanRank(p.getUniqueId().toString()).equalsIgnoreCase("leader")) {
		
								for(int i = 0; i < ClanManager.getMembers(ClanManager.getClanName(p.getUniqueId().toString())).size(); i++) {
									ProxiedPlayer t = ProxyServer.getInstance().getPlayer((String) ClanManager.getMembers(ClanManager.getClanName(p.getUniqueId().toString())).get(i));
									if (t != null) {
										t.sendMessage(Data.prefix + "§e" + p.getName() + " §chat den Clan verlassen.");
									}
								}
								
//								p.sendMessage(Data.prefix + "§aDu hast den Clan §e" + ClanManager.getClanName(p.getUniqueId().toString()) + " §averlassen.");
								ClanManager.removePlayer(p.getUniqueId().toString());
							} else {
								p.sendMessage(Data.usage + "/Clans delete");
							}
						} else {
							p.sendMessage(Data.prefix + "§cDu bist in keinem Clan.");
						}
					}
					if(args[0].equalsIgnoreCase("delete")) {
						if(ClanManager.inClan(p.getUniqueId().toString())) {
							if(ClanManager.getClanRank(p.getUniqueId().toString()).equalsIgnoreCase("leader")) {
								
								for(int i = 0; i < ClanManager.getMembers(ClanManager.getClanName(p.getUniqueId().toString())).size(); i++) {
									ProxiedPlayer t = ProxyServer.getInstance().getPlayer((String) ClanManager.getMembers(ClanManager.getClanName(p.getUniqueId().toString())).get(i));
									if (t != null) {
										t.sendMessage(Data.prefix + "§e" + p.getName() + " §chat den Clan gelöscht.");
									}
								}
								
								ClanManager.deleteClan(ClanManager.getClanName(p.getUniqueId().toString()));
//								p.sendMessage(Data.prefix + "§cDu hast deinen Clan erfolgreich gelöscht.");
							} else {
								p.sendMessage(Data.prefix + "§cNur der Clan-Leader kann diesen Befehl nutzen.");
							}
						} else {
							p.sendMessage(Data.prefix + "§cDu bist in keinem Clan.");
						}
					}
					if(args[0].equalsIgnoreCase("join")) {
						p.sendMessage(Data.usage + "/Clans join <Clan>");
					}
					if(args[0].equalsIgnoreCase("deny")) {
						p.sendMessage(Data.usage + "/Clans deny <Clan>");
					}
					if(args[0].equalsIgnoreCase("info")) {
						if(ClanManager.inClan(p.getUniqueId().toString())) {
							ProxyServer.getInstance().getScheduler().runAsync(Main.instance, new Runnable() {
								
								@Override
								public void run() {
									int clanID = ClanManager.getClanID(p.getUniqueId().toString()).intValue();
									int playerIDs = ClanManager.getPlayerID();
									String playername;
									for(int i = 1; i <= playerIDs; i++) {
										playername = ClanManager.getPlayerID(i);
										if(ClanManager.getClanIDFromName(playername).equals(Integer.valueOf(clanID))) {
											String rank = ClanManager.getClanRankOffline(playername);
											playersInClan.put(playername, rank);
										}
									}
									
									p.sendMessage(Main.getHeader("MITGLIEDER"));
									p.sendMessage("§fName: §6" + ClanManager.getClanName(p.getUniqueId().toString()));
									p.sendMessage("§fClan-Tag: §6" + ClanManager.getClanTag(p.getUniqueId().toString()));
									List members = ClanManager.getMembers(ClanManager.getClanName(p.getUniqueId().toString()));
									p.sendMessage("§fMitglieder: §e" + members.size() + " §7Mitglied(er) §8(§cMax. 25§8)");
									p.sendMessage(" ");
									p.sendMessage("§4Clanleader");
									for(String player : playersInClan.keySet()) {
										if(((String)playersInClan.get(player)).equals("leader")) {
											if(ProxyServer.getInstance().getPlayer(player) != null) {
												p.sendMessage("  §7• §a" + player);
											} else {
												p.sendMessage("  §7• " + player + " §c✕");
											}
										}
									}
									p.sendMessage(" ");
									p.sendMessage("§2Clanmoderatoren");
									for(String player2 : playersInClan.keySet()) {
										if(((String)playersInClan.get(player2)).equals("mod")) {
											if(ProxyServer.getInstance().getPlayer(player2) != null) {
												p.sendMessage("  §7• §a" + player2);
											} else {
												p.sendMessage("  §7• " + player2 + " §c✕");
											}
										}
									}
									p.sendMessage(" ");
									p.sendMessage("§7Clanmitglieder");
									for(String player3 : playersInClan.keySet()) {
										if(((String)playersInClan.get(player3)).equals("member")) {
											if(ProxyServer.getInstance().getPlayer(player3) != null) {
												p.sendMessage("  §7• §a" + player3);
											} else {
												p.sendMessage("  §7• " + player3 + " §c✕");
											}
										}
									}
									p.sendMessage(" ");
									p.sendMessage(Main.getHeader("MITGLIEDER"));
									playersInClan.clear();				
								}
							});
						} else {
							p.sendMessage(Data.prefix + "§cDu bist in keinem Clan.");
						}
					}
					
				} else if(args.length == 2) {
					if (args[0].equalsIgnoreCase("invite")) {
						ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[1]);
						if (ClanManager.inClan(p.getUniqueId().toString())) {
							if ((ClanManager.getClanRank(p.getUniqueId().toString()).equalsIgnoreCase("leader"))
									|| (ClanManager.getClanRank(p.getUniqueId().toString()).equalsIgnoreCase("mod"))) {
								if (target != null) {
									if (!ClanManager.inClan(target.getUniqueId().toString())) {
										if (!InviteManager.invites.containsKey(target.getUniqueId().toString())) {
											p.sendMessage(Data.prefix + "§aDu hast §e" + target.getName() + " §ain den Clan eingeladen.");
											target.sendMessage(" ");
											target.sendMessage(Data.prefix + "§7Du wurdest in den Clan §e" + ClanManager.getClanName(p.getUniqueId().toString()) + " §7eingeladen.");
											target.sendMessage(Data.prefix + "§7Einladung annehmen§8: §a/Clan join " + ClanManager.getClanName(p.getUniqueId().toString()));
											target.sendMessage(Data.prefix + "§7Einladung ablehnen§8: §c/Clan deny " + ClanManager.getClanName(p.getUniqueId().toString()));
											target.sendMessage(" ");
											InviteManager.addPlayer(target.getUniqueId().toString(), p.getUniqueId().toString());
										} else {
											p.sendMessage(Data.prefix + "§cDu hast diesen Spieler bereits eingeladen.");
										}
									} else {
										p.sendMessage(Data.prefix + "§cDieser Spieler ist bereits in einem anderen Clan.");
									}
								} else {
									p.sendMessage(Data.notonline);
								}
							} else {
								p.sendMessage(Data.prefix + "§cDu musst min. §eClan-Mod §csein um Spieler eizuladen.");
							}
						} else {
							p.sendMessage(Data.prefix + "§cDu bist in keinem Clan.");
						}
					}
					if(args[0].equalsIgnoreCase("join")) {
						if(InviteManager.invites.containsKey(p.getUniqueId().toString())) {
							String name = args[1];
							int clanID = ClanManager.getClanID(InviteManager.getClan(p.getUniqueId().toString())).intValue();
							String clanname = ClanManager.getClanNameFromID(clanID);
							if(name.equalsIgnoreCase(clanname)) {
								List members = ClanManager.getMembers(ClanManager.getClanNameFromID(clanID));
								if(members.size() < 25) {
									String clantag = ClanManager.getClanTagFromID(clanID);
									int playerID = ClanManager.getPlayerID();
									playerID++;
									ClanManager.addPlayer(playerID, clanID, clanname, clantag, p.getUniqueId().toString(), p.getName(), "member");
									InviteManager.removePlayer(p.getUniqueId().toString());
									
									for(int i = 0; i < ClanManager.getMembers(ClanManager.getClanName(p.getUniqueId().toString())).size(); i++) {
										ProxiedPlayer t = ProxyServer.getInstance().getPlayer((String) ClanManager.getMembers(ClanManager.getClanName(p.getUniqueId().toString())).get(i));
										if (t != null) {
											t.sendMessage(Data.prefix + "§e" + p.getName() + " §aist dem Clan beigetreten.");
										}
									}
									
//									p.sendMessage(Data.prefix + "§aDu bist dem Clan §e" + clanname + " §abeigetreten.");
									
								} else {
									p.sendMessage(Data.prefix + "§cDieser Clan ist bereits voll.");
								}
							} else {
								p.sendMessage(Data.prefix + "§cDu hast keine Anfrage von diesem Clan bekommen.");	
							}
						} else {
							p.sendMessage(Data.prefix + "§cDu hast keine Anfrage von diesem Clan bekommen.");
						}
					}
					if(args[0].equalsIgnoreCase("deny")) {
						if(InviteManager.invites.containsKey(p.getUniqueId().toString())) {
							String name = args[1];
							int clanID = ClanManager.getClanID(InviteManager.getClan(p.getUniqueId().toString())).intValue();
							String clanname = ClanManager.getClanNameFromID(clanID);
							if(name.equalsIgnoreCase(clanname)) {
								ClanManager.removePlayer(p.getUniqueId().toString());
								InviteManager.removePlayer(p.getUniqueId().toString());
								p.sendMessage(Data.prefix + "§cDu hast die Anfrage von dem Clan §e" + clanname + " §cabgelehnt.");
							} else {
								p.sendMessage(Data.prefix + "§cDu hast keine Anfrage von diesem Clan bekommen.");	
							}
						} else {
							p.sendMessage(Data.prefix + "§cDu hast keine Anfrage von diesem Clan bekommen.");
						}
					}
					if(args[0].equalsIgnoreCase("kick")) {
						if(ClanManager.inClan(p.getUniqueId().toString())) {
							if(ClanManager.getClanRank(p.getUniqueId().toString()).equalsIgnoreCase("leader")) {
								String playername = args[1];
								if(ClanManager.inClanOffline(playername)) {
									int pClanID = ClanManager.getClanID(p.getUniqueId().toString()).intValue();
									int tClanID = ClanManager.getClanIDFromName(playername).intValue();
									if(pClanID == tClanID) {
										
										for(int i = 0; i < ClanManager.getMembers(ClanManager.getClanName(p.getUniqueId().toString())).size(); i++) {
											ProxiedPlayer t = ProxyServer.getInstance().getPlayer((String) ClanManager.getMembers(ClanManager.getClanName(p.getUniqueId().toString())).get(i));
											if (t != null) {
												t.sendMessage(Data.prefix + "§e" + p.getName() + " §chat §e" + playername + " §caus dem Clan geworfen.");
											}
										}
										
										ClanManager.removePlayerOffline(playername);
//										p.sendMessage(Data.prefix + "§cDu hast §e" + playername + " §caus dem Clan geworfen.");
									} else {
										p.sendMessage(Data.prefix + "§cDieser Spieler ist nicht in deinem Clan.");
									}
								} else {
									p.sendMessage(Data.prefix + "§cDieser Spieler ist nicht in deinem Clan.");
								}
							} else {
								p.sendMessage(Data.prefix + "§cNur der Clan-Leader kann diesen Befehl nutzen.");
							}
						} else {
							p.sendMessage(Data.prefix + "§cDu bist in keinem Clan.");
						}
					}
					if(args[0].equalsIgnoreCase("demote")) {
						if(ClanManager.inClan(p.getUniqueId().toString())) {
							if(ClanManager.getClanRank(p.getUniqueId().toString()).equalsIgnoreCase("leader")) {
								String playername = args[1];
								if(playername.equalsIgnoreCase(p.getName())) {
									p.sendMessage(Data.prefix + "§cDu kannst dich nicht selbst demoten.");
									return;
								}
								if(ClanManager.inClanOffline(playername)) {
									if(ClanManager.getClanRankOffline(playername).equalsIgnoreCase("leader")) {
										ClanManager.setClanRankOffline(playername, "mod");
										
										for(int i = 0; i < ClanManager.getMembers(ClanManager.getClanName(p.getUniqueId().toString())).size(); i++) {
											ProxiedPlayer t = ProxyServer.getInstance().getPlayer((String) ClanManager.getMembers(ClanManager.getClanName(p.getUniqueId().toString())).get(i));
											if (t != null) {
												t.sendMessage(Data.prefix + "§e" + playername + " §cwurde zu §cClan-Mod §cdemoted.");
											}
										}
										
//										p.sendMessage(Data.prefix + "§aDu hast §e" + playername + " §azu §cClan-Mod §agedemoted.");
									}
									else if(ClanManager.getClanRankOffline(playername).equalsIgnoreCase("mod")) {
										ClanManager.setClanRankOffline(playername, "member");
										
										for(int i = 0; i < ClanManager.getMembers(ClanManager.getClanName(p.getUniqueId().toString())).size(); i++) {
											ProxiedPlayer t = ProxyServer.getInstance().getPlayer((String) ClanManager.getMembers(ClanManager.getClanName(p.getUniqueId().toString())).get(i));
											if (t != null) {
												t.sendMessage(Data.prefix + "§e" + playername + " §cwurde zu §bClan-Member §cdemoted.");
											}
										}
										
//										p.sendMessage(Data.prefix + "§aDu hast §e" + playername + " §azu §bClan-Member §agedemoted.");
									}
									else if(ClanManager.getClanRankOffline(playername).equalsIgnoreCase("member")) {
										p.sendMessage(Data.prefix + "§cDieser Spieler ist bereits §bClan-Member§c.");
									}
								} else {
									p.sendMessage(Data.prefix + "§cDieser Spieler ist nicht in deinem Clan.");
								}
							} else {
								p.sendMessage(Data.prefix + "§cNur der Clan-Leader kann diesen Befehl nutzen.");
							}
						} else {
							p.sendMessage(Data.prefix + "§cDu bist in keinem Clan.");
						}
					}
					if(args[0].equalsIgnoreCase("promote")) {
						if(ClanManager.inClan(p.getUniqueId().toString())) {
							if(ClanManager.getClanRank(p.getUniqueId().toString()).equalsIgnoreCase("leader")) {
								String playername = args[1];
								if(playername.equalsIgnoreCase(p.getName())) {
									p.sendMessage(Data.prefix + "§cDu kannst dich nicht selbst promoten.");
									return;
								}
								if(ClanManager.inClanOffline(playername)) {
									if(ClanManager.getClanRankOffline(playername).equalsIgnoreCase("leader")) {									
										p.sendMessage(Data.prefix + "§cDieser Spieler ist bereits §4Clan-Leader§c.");
									}
									else if(ClanManager.getClanRankOffline(playername).equalsIgnoreCase("member")) {
										ClanManager.setClanRankOffline(playername, "mod");
										
										for(int i = 0; i < ClanManager.getMembers(ClanManager.getClanName(p.getUniqueId().toString())).size(); i++) {
											ProxiedPlayer t = ProxyServer.getInstance().getPlayer((String) ClanManager.getMembers(ClanManager.getClanName(p.getUniqueId().toString())).get(i));
											if (t != null) {
												t.sendMessage(Data.prefix + "§e" + playername + " §awurde zu §cClan-Mod §apromoted.");
											}
										}
										
//										p.sendMessage(Data.prefix + "§aDu hast §e" + playername + " §azu §cClan-Mod §apromoted.");
									}
									else if(ClanManager.getClanRankOffline(playername).equalsIgnoreCase("mod")) {
										ClanManager.setClanRankOffline(playername, "leader");
										
										for(int i = 0; i < ClanManager.getMembers(ClanManager.getClanName(p.getUniqueId().toString())).size(); i++) {
											ProxiedPlayer t = ProxyServer.getInstance().getPlayer((String) ClanManager.getMembers(ClanManager.getClanName(p.getUniqueId().toString())).get(i));
											if (t != null) {
												t.sendMessage(Data.prefix + "§e" + playername + " §awurde zu §4Clan-Leader §apromoted.");
											}
										}
										
//										p.sendMessage(Data.prefix + "§aDu hast §e" + playername + " §azu §4Clan-Leader §apromoted.");
									}
								} else {
									p.sendMessage(Data.prefix + "§cDieser Spieler ist nicht in deinem Clan.");
								}
							} else {
								p.sendMessage(Data.prefix + "§cNur der Clan-Leader kann diesen Befehl nutzen.");
							}
						} else {
							p.sendMessage(Data.prefix + "§cDu bist in keinem Clan.");
						}
					}
					if(args[0].equalsIgnoreCase("uinfo")) {
						String spieler = args[1];
						if(ClanManager.inClanOffline(spieler)) {
							ProxyServer.getInstance().getScheduler().runAsync(Main.instance, new Runnable() {
								
								@Override
								public void run() {
									int clanID = ClanManager.getClanIDFromName(spieler).intValue();
									int playerIDs = ClanManager.getPlayerID();
									String playername;
									for(int i = 1; i <= playerIDs; i++) {
										playername = ClanManager.getPlayerID(i);
										if(ClanManager.getClanIDFromName(playername).equals(Integer.valueOf(clanID))) {
											String rank = ClanManager.getClanRankOffline(playername);
											playersInClan.put(playername, rank);
										}
									}
									
									p.sendMessage(Main.getHeader("MITGLIEDER"));
									p.sendMessage("§fName: §6" + ClanManager.getClanNameFromString(spieler));
									p.sendMessage("§fClan-Tag: §6" + ClanManager.getClanTagFromString(spieler));
									List members = ClanManager.getMembers(ClanManager.getClanNameFromString(spieler));
									p.sendMessage("§fMitglieder: §e" + members.size() + " §7Mitglied(er)");
									p.sendMessage(" ");
									p.sendMessage("§4Clanleader");
									for(String player : playersInClan.keySet()) {
										if(((String)playersInClan.get(player)).equals("leader")) {
											if(ProxyServer.getInstance().getPlayer(player) != null) {
												p.sendMessage("  §7• §a" + player);
											} else {
												p.sendMessage("  §7• " + player + " §c✕");
											}
										}
									}
									p.sendMessage(" ");
									p.sendMessage("§2Clanmoderatoren");
									for(String player2 : playersInClan.keySet()) {
										if(((String)playersInClan.get(player2)).equals("mod")) {
											if(ProxyServer.getInstance().getPlayer(player2) != null) {
												p.sendMessage("  §7• §a" + player2);
											} else {
												p.sendMessage("  §7• " + player2 + " §c✕");
											}
										}
									}
									p.sendMessage(" ");
									p.sendMessage("§7Clanmitglieder");
									for(String player3 : playersInClan.keySet()) {
										if(((String)playersInClan.get(player3)).equals("member")) {
											if(ProxyServer.getInstance().getPlayer(player3) != null) {
												p.sendMessage("  §7• §a" + player3);
											} else {
												p.sendMessage("  §7• " + player3 + " §c✕");
											}
										}
									}
									p.sendMessage(" ");
									p.sendMessage(Main.getHeader("MITGLIEDER"));
									playersInClan.clear();				
								}
							});
						} else {
							p.sendMessage(Data.prefix + "§cDieser Spieler ist in keinem Clan.");
						}
					}
					if(args[0].equalsIgnoreCase("setname")) {
						if(ClanManager.inClan(p.getUniqueId().toString())) {
							String clanname = args[1];
							
							if((clanname.length() >= 2) && (clanname.length() <= 10)) {
								if(!ClanManager.clanNameExist(clanname)) {
									if(isValid(clanname)) {
										ClanManager.changeName(clanname, p.getUniqueId().toString());
										p.sendMessage(Data.prefix + "§7Neuer Clan-Name§8: §e" + clanname);
									} else {
										p.sendMessage(Data.prefix + "§cDer Clan-Name darf keine Sonderzeichen enthalten.");
									}
								} else {
									p.sendMessage(Data.prefix + "§cEs gibt bereits einen Clan mit diesem Namen.");	
								}
							} else {
								p.sendMessage(Data.prefix + "§cDer Clan-Name muss zwischen §e2 §cund §e10 §cZeichen lang sein.");
							}
						} else {
							p.sendMessage(Data.prefix + "§cDu bist in keinem Clan.");
						}
					}
					if(args[0].equalsIgnoreCase("settag")) {
						if(ClanManager.inClan(p.getUniqueId().toString())) {
							String clantag = args[1];
							
							if((clantag.length() >= 2) && (clantag.length() <= 5)) {
								if(!ClanManager.clanTagExist(clantag)) {
									if(isValid(clantag)) {
										ClanManager.changeTag(clantag, p.getUniqueId().toString());
										p.sendMessage(Data.prefix + "§7Neuer Clan-Tag§8: §e" + clantag);
									} else {
										p.sendMessage(Data.prefix + "§cDer Clan-Tag darf keine Sonderzeichen enthalten.");
									}
								} else {
									p.sendMessage(Data.prefix + "§cEs gibt bereits einen Clan mit diesem Tag.");	
								}
							} else {
								p.sendMessage(Data.prefix + "§cDer Clan-Tag muss zwischen §e2 §cund §e5 §cZeichen lang sein.");
							}
						} else {
							p.sendMessage(Data.prefix + "§cDu bist in keinem Clan.");
						}
					}
				} else if(args.length == 3) {
					if(args[0].equalsIgnoreCase("create")) {
						if(!ClanManager.inClan(p.getUniqueId().toString())) {
							String clanname = args[1];
							String clantag = args[2];
							
							if((clanname.length() >= 2) && (clanname.length() <= 10)) {
								if((clantag.length() >= 2) && (clantag.length() <= 5)) {
									if(!ClanManager.clanNameExist(clanname)) {
										if(!ClanManager.clanTagExist(clantag)) {
											if(isValid(clanname)) {
												if(isValid(clantag)) {
													int clanID = ClanManager.getClanIDs().intValue();
													int playerID = ClanManager.getPlayerID();
													clanID++;
													playerID++;
													ClanManager.createClan(playerID, clanID, clanname, clantag, p.getUniqueId().toString(), p.getName(), "leader");
													p.sendMessage(Data.prefix + "§aDu hast den Clan §e" + clanname + " §7[§e" + clantag + "§7] §aerstellt. §8(§b§lID§8: §e" + clanID + "§8)");
												} else {
													p.sendMessage(Data.prefix + "§cDer Clan-Tag darf keine Sonderzeichen enthalten.");
												}
											} else {
												p.sendMessage(Data.prefix + "§cDer Clan-Name darf keine Sonderzeichen enthalten.");
											}
										} else {
											p.sendMessage(Data.prefix + "§cEs gibt bereits einen Clan mit diesem Tag.");
										}
									} else {
										p.sendMessage(Data.prefix + "§cEs gibt bereits einen Clan mit diesem Namen.");
									}
								} else {
									p.sendMessage(Data.prefix + "§cDer Clan-Tag muss zwischen §e2 §cund §e5 §cZeichen lang sein.");
								}
							} else {
								p.sendMessage(Data.prefix + "§cDer Clan-Name muss zwischen §e2 §cund §e10 §cZeichen lang sein.");
							}
						} else {
							p.sendMessage(Data.prefix + "§cDu bist bereits in einem Clan.");
						}
					}
				}
			} else {
				sender.sendMessage(Data.playeronly);
			}
		}
		
	}
	
	public boolean isValid(String code) {
		return code.matches("[aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789_-]*");
	}

	
	
}
