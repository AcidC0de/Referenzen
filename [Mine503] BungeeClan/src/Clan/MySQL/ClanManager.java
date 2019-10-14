package Clan.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClanManager {

	public static void createClan(int playerid, int clanid, String clanname, String clantag, String uuid, String name, String rank) {
			MySQL.update("INSERT INTO Clans (PlayerID, ClanID, Clanname, Clantag, UUID, Spielername, ClanRank) VALUES ('" + playerid + "','" + clanid + "','" + clanname + "','" + clantag + "','" + uuid + "','" + name + "','" + rank + "')");
	}
	
	public static void deleteClan(String clan) {
		MySQL.update("DELETE FROM Clans WHERE Clanname='" + clan + "'");
	}
	
	public static void addPlayer(int playerid, int clanid, String clanname, String clantag, String uuid, String name, String rank) {
		MySQL.update("INSERT INTO Clans (PlayerID, ClanID, Clanname, Clantag, UUID, Spielername, ClanRank) VALUES ('" + playerid + "','" + clanid + "','" + clanname + "','" + clantag + "','" + uuid + "','" + name + "','" + rank + "')");
	}
	
	public static void removePlayer(String uuid) {
		MySQL.update("DELETE FROM Clans WHERE UUID='" + uuid + "'");
	}
	
	public static void removePlayerOffline(String playername) {
		MySQL.update("DELETE FROM Clans WHERE Spielername='" + playername + "'");
	}
	
	public static void setClanRank(String uuid, String rank) {
		MySQL.update("UPDATE Clans SET ClanRank='" + rank + "' WHERE UUID='" + uuid + "'");
	}
	
	public static void setClanRankOffline(String playername, String rank) {
		MySQL.update("UPDATE Clans SET ClanRank='" + rank + "' WHERE Spielername='" + playername + "'");
	}
	
	public static void changeName(String name, String uuid) {
		MySQL.update("UPDATE Clans SET Clanname='" + name + "' WHERE UUID='" + uuid + "'");
	}
	
	public static void changeTag(String tag, String uuid) {
		MySQL.update("UPDATE Clans SET Clantag='" + tag + "' WHERE UUID='" + uuid + "'");
	}
	
	public static boolean inClan(String uuid) {
		if(MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT UUID FROM Clans WHERE UUID='" + uuid + "'");
			try {
				return rs.next();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static boolean inClanOffline(String playername) {
		if(MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT Spielername FROM Clans WHERE Spielername='" + playername + "'");
			try {
				return rs.next();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static boolean clanNameExist(String clanname) {
		if(MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT Clanname FROM Clans WHERE Clanname='" + clanname + "'");
			try {
				return rs.next();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static boolean clanTagExist(String clantag) {
		if(MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT Clantag FROM Clans WHERE Clantag='" + clantag + "'");
			try {
				return rs.next();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
		
	public static String getClanName(String uuid) {
		if(MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Clans WHERE UUID='" + uuid + "'");
			try {
				if(rs.next()) {
					return rs.getString("Clanname");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return uuid;
	}
	
	public static String getClanNameFromString(String playername) {
		if(MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Clans WHERE Spielername='" + playername + "'");
			try {
				if(rs.next()) {
					return rs.getString("Clanname");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "null";
	}
	
	public static String getClanNameFromID(Integer clanid) {
		if(MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT Clanname FROM Clans WHERE ClanID='" + clanid + "'");
			try {
				if(rs.next()) {
					return rs.getString("Clanname");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "null";
	}
	
	public static String getClanTag(String uuid) {
		if(MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Clans WHERE UUID='" + uuid + "'");
			try {
				if(rs.next()) {
					return rs.getString("Clantag");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return uuid;
	}	
	
	public static String getClanTagFromString(String playername) {
		if(MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Clans WHERE Spielername='" + playername + "'");
			try {
				if(rs.next()) {
					return rs.getString("Clantag");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "null";
	}
	
	public static String getClanTagFromID(Integer clanid) {
		if(MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT Clantag FROM Clans WHERE ClanID='" + clanid + "'");
			try {
				if(rs.next()) {
					return rs.getString("Clantag");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "null";
	}
	
	public static String getClanRank(String uuid) {
		if(MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Clans WHERE UUID='" + uuid + "'");
			try {
				if(rs.next()) {
					return rs.getString("ClanRank");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return uuid;
	}
	
	public static String getClanRankOffline(String playername) {
		if(MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT ClanRank FROM Clans WHERE Spielername='" + playername + "'");
			try {
				if(rs.next()) {
					return rs.getString("ClanRank");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "null";
	}
	
	public static Integer getClanID(String uuid) {
		if(MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT ClanID FROM Clans WHERE UUID='" + uuid + "'");
			try {
				if(rs.next()) {
					return Integer.valueOf(rs.getInt("ClanID"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Integer.valueOf(-1);
	}
	
	public static Integer getClanIDFromName(String playername) {
		if(MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT ClanID FROM Clans WHERE Spielername='" + playername + "'");
			try {
				if(rs.next()) {
					return Integer.valueOf(rs.getInt("ClanID"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Integer.valueOf(-1);
	}
	
	public static Integer getClanIDs() {
		int clanID = 0;
		if(MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Clans ORDER BY ClanID DESC");
			try {
				if(rs.next()) {
					return Integer.valueOf(rs.getInt("ClanID"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}	
		return Integer.valueOf(clanID);
	}

	public static String getPlayerID(int playerid) {
		if(MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT Spielername FROM Clans WHERE PlayerID='" + playerid + "'");
			try {
				if(rs.next()) {
					return rs.getString("Spielername");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "null";
	}
	
	public static Integer getPlayerID() {
		int clanID = 0;
		if(MySQL.isConnected()) {
			ResultSet rs = MySQL.getResult("SELECT * FROM Clans ORDER BY PlayerID DESC");
			try {
				if(rs.next()) {
					return rs.getInt("PlayerID");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}	
		return clanID;
	}
	
	public static List<String> getMembers(String clan) {
		List list = new ArrayList<>();
		ResultSet rs = MySQL.getResult("SELECT * FROM Clans WHERE Clanname='" + clan + "'");
		try {
			while(rs.next()) {
				list.add(rs.getString("Spielername"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
