package FFA.Util;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import FFA.Stats.MySQL;

public class FileManager
{
  public static File getConfigFile()
  {
    return new File("plugins/FFA", "config.yml");
  }
  
  public static FileConfiguration getConfigFileConfiguration()
  {
    return YamlConfiguration.loadConfiguration(getConfigFile());
  }
  
  public static File getMySQLFile()
  {
    return new File("plugins/FFA/MySQL", "mysql.yml");
  }
  
  public static FileConfiguration getMySQLFileConfiguration()
  {
    return YamlConfiguration.loadConfiguration(getMySQLFile());
  }
  
  public static void setStandardMySQL()
  {
    FileConfiguration cfg = getMySQLFileConfiguration();
    cfg.options().copyDefaults(true);
    cfg.addDefault("username", "root");
    cfg.addDefault("password", "password");
    cfg.addDefault("database", "localhost");
    cfg.addDefault("host", "localhost");
    cfg.addDefault("port", "3306");
    try
    {
      cfg.save(getMySQLFile());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public static void readMySQL()
  {
    FileConfiguration cfg = getMySQLFileConfiguration();
    MySQL.username = cfg.getString("username");
    MySQL.password = cfg.getString("password");
    MySQL.database = cfg.getString("database");
    MySQL.host = cfg.getString("host");
    MySQL.port = cfg.getString("port");
  }
  
	public static File file = new File("plugins//FFA//Map.yml");
	public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
  
	public static void setMap(String name) {
		
		cfg.set("Mapname", name);
		
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getMap() {
		String clan = cfg.getString("Mapname");
		return clan;
	}
	
}

