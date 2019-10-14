package BungeeSystem.Main;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class MySQL
{
	  public static String USERNAME = "root";
	  public static String PASSWORD = "S2eKurWnaex8xSJw";
	  public static String DATABASE = "BungeeSystem";
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
        System.out.println(Main.prefix + "Die MySQL Verbindung wurde aufgebaut!");
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
        System.out.println(Main.prefix + "Die MySQL Verbindung wurde geschlossen!");
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
        con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Reports (UUID VARCHAR(100), Reporter VARCHAR(100), Spielername VARCHAR(100), Server VARCHAR(100), Grund VARCHAR(100))");
        con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Bans (Spielername VARCHAR(100), UUID VARCHAR(100), Ende VARCHAR(100), Grund VARCHAR(100), BannedBy VARCHAR(100))");
        con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Mutes (Spielername VARCHAR(100), UUID VARCHAR(100), Ende VARCHAR(100), Grund VARCHAR(100), MutedBy VARCHAR(100))");
        con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS History (Spielername VARCHAR(100), UUID VARCHAR(100), TYPE VARCHAR(100), Grund VARCHAR(100))");
        con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Warns (UUID VARCHAR(100), WARNINGS int)");
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


