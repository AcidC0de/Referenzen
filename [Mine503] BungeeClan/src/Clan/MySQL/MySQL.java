package Clan.MySQL;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import Clan.Main.Data;
import Clan.Main.Main;

public class MySQL
{
	  public static String USERNAME = "root";
	  public static String PASSWORD = "S2eKurWnaex8xSJw";
	  public static String DATABASE = "BungeeClans";
	  public static String HOST = "localhost";
	  public static String PORT = "3306";
	  public static Connection con;
	  
		public MySQL(String host, String database, String user, String password, String port) {
			HOST = host;
			DATABASE = database;
			USERNAME = user;
			PASSWORD = password;
			PORT = port;

			connect();
		}
  
  public static void connect()
  {
    if (!isConnected()) {
      try
      {
        con = (Connection)DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE, 
          USERNAME, PASSWORD);
        System.out.println(Data.prefix + "Die MySQL Verbindung wurde aufgebaut!");
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public static void close()
  {
    if (isConnected()) {
      try
      {
        con.close();
        System.out.println(Data.prefix + "Die MySQL Verbindung wurde geschlossen!");
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public static boolean isConnected()
  {
    return con != null;
  }
  
  public static void createTable()
  {
    if (isConnected()) {
      try
      {
        con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Clans (PlayerID int, ClanID int, Clanname VARCHAR(10), Clantag VARCHAR(5), UUID VARCHAR(64), Spielername VARCHAR(100), ClanRank VARCHAR(16))");
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public static void update(String qry)
  {
    if (isConnected()) {
      try
      {
        con.createStatement().executeUpdate(qry);
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public static ResultSet getResult(String qry)
  {
    if (isConnected()) {
      try
      {
        return con.createStatement().executeQuery(qry);
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
    return null;
  }
}



