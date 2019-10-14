package CoinAPI.Main;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;

import com.mysql.jdbc.Connection;

public class MySQL
{
	  public static String username;
	  public static String password;
	  public static String database;
	  public static String host;
	  public static String port;
	  public static Connection con;
	  
	  public static void connect()
	  {
	    if (!isConnected()) {
	      try
	      {
	        con = (Connection)DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, 
	          username, password);
	        Bukkit.getConsoleSender().sendMessage("§aDie MySQL Verbindung wurde aufgebaut!");
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
	        Bukkit.getConsoleSender().sendMessage("§cDie MySQL Verbindung wurde geschlossen!");
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
	        con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Coin (UUID varchar(64), COINS int)");
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
		public static ResultSet query(String qry) {
			ResultSet rs = null;
			try {
				Statement st = con.createStatement();
				rs = st.executeQuery(qry);
			} catch (SQLException e) {
				connect();
				System.err.println(e);
			}
			return rs;
		}
	}
